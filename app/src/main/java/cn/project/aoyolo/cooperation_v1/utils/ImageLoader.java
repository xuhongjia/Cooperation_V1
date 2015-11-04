package cn.project.aoyolo.cooperation_v1.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.media.Image;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.LruCache;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 图片加载器
 * Created by HY on 2015/11/2.
 */
public class ImageLoader {
    private static ImageLoader instance = null;
    private static ExecutorService loadPool = Executors.newFixedThreadPool(5);//解码图片
    private static LruCache<String, Bitmap> imageCache = null;//缓存图片
    private static Handler handler = null; //刷新图片

    /**
     * 获得实例
     */
    public static ImageLoader getInstance() {
        if (instance == null) {
            synchronized (ImageLoader.class) {
                if (instance == null)
                    instance = new ImageLoader();
                initImageCache();
                initHandler();

            }

        }
        return instance;
    }

    /**
     * 初始化handler，用于刷新imageView
     */
    private static void initHandler() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                ImageEntity entity = (ImageEntity) msg.obj;
                entity.imageView.setImageBitmap(entity.bitmap);
            }
        };
    }

    /**
     * 初始化图片缓存
     */
    private static void initImageCache() {
        int runMemory = (int) Runtime.getRuntime().maxMemory();//获得最大内存
        int cacheMemory = runMemory / 5;
        imageCache = new LruCache<String, Bitmap>(cacheMemory) {
            @Override
            protected int sizeOf(String key, Bitmap value) {

                return value.getHeight() * value.getRowBytes();
            }
        };
    }

    /**
     * 加载图片
     *
     * @param imageView
     * @param path
     */
    public void LoadImage(final ImageView imageView, final String path) {
        loadPool.execute(new Runnable() {
            @Override
            public void run() {
                //先看看是否缓存
                imageView.setTag(path);
                Bitmap bitmap = imageCache.get(path);
                //如果有则直接使用
                if (bitmap != null) {
                    refreshImageView(path, imageView, bitmap);
                } else {
                    //没有则获得压缩图片
                    bitmap = getCompressBitmap(path, imageView);
                    if(bitmap!=null)
                        refreshImageView(path,imageView,bitmap);
                }
            }


        });


    }

    /**
     * 获得压缩图片，仅适用本地相片，不是和网络相片
     *
     * @param path
     * @return
     */
    public  Bitmap getCompressBitmap(String path, ImageView imageView) {
        //获得需要显示的尺寸
        ImageSize displaySize = getDisplaySize(imageView);
        //获得相片尺寸
        ImageSize imageSize = getImageSize(path);
        //获得压缩图片
        Bitmap bitmap=getCompressImage(path,displaySize,imageSize);
        //添加至缓存
        imageCache.put(path, bitmap);
        return bitmap;
    }

    /**
     * 获得压缩图片，此方法可用于网络请求时调用
     * @param bis
     * @return
     */
    public Bitmap getCompressBitmap(BufferedInputStream bis,ImageView imageView)
    {
        Bitmap bitmap=null;
        ImageSize displaySize=getDisplaySize(imageView);
        ImageSize imageSize=getImageSize(bis);
        bitmap=getCompressBitmap(bis,displaySize,imageSize);
        return bitmap;
    }

    private Bitmap getCompressBitmap(BufferedInputStream bis, ImageSize displaySize, ImageSize imageSize) {
        int displayHeight=displaySize.height,displayWidth=displaySize.width;
        int imageWidth=imageSize.width,imageHeight=imageSize.height;
        int inSampleSize=0;//缩放比例
        if(imageWidth>displayWidth||imageHeight>displayHeight)
        {
            int ratioWidth=(int)(imageWidth*1.0f)/displayWidth;
            int ratioHeight=(int)(imageHeight*1.0f)/displayHeight;
            inSampleSize=Math.max(ratioHeight,ratioWidth);
        }
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inSampleSize=inSampleSize;

        return BitmapFactory.decodeStream(bis,null,options);

    }

    /**
     * 根据输入流来获得图片真实的尺寸
     * @param bis
     * @return
     */
    private ImageSize getImageSize(BufferedInputStream bis) {
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        Rect rect=new Rect(0,0,0,0);
        BitmapFactory.decodeStream(bis,null,options);
        ImageSize imageSize=new ImageSize();
        imageSize.width=options.outWidth;
        imageSize.height=options.outHeight;
        return imageSize;
    }

    /**
     * 获得缩放比列压缩图片
     * @param path
     * @param displaySize
     * @param imageSize
     * @return
     */
    private Bitmap getCompressImage(String path,ImageSize displaySize, ImageSize imageSize) {
        int displayHeight=displaySize.height,displayWidth=displaySize.width;
        int imageWidth=imageSize.width,imageHeight=imageSize.height;
        int inSampleSize=0;//缩放比例
        if(imageWidth>displayWidth||imageHeight>displayHeight)
        {
            int ratioWidth=(int)(imageWidth*1.0f)/displayWidth;
            int ratioHeight=(int)(imageHeight*1.0f)/displayHeight;
            inSampleSize=Math.max(ratioHeight,ratioWidth);
        }
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inSampleSize=inSampleSize;

        return BitmapFactory.decodeFile(path,options);
    }

    /**
     * 获得相片尺寸
     *
     * @param path
     * @return
     */
    private ImageSize getImageSize(String path) {

        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        BitmapFactory.decodeFile(path,options);
        int width=options.outWidth;
        int height=options.outHeight;
        ImageSize size=new ImageSize();
        size.height=height;
        size.width=width;
        return size;
    }

    /**
     * 获得显示的尺寸
     *
     * @param imageView
     */
    private ImageSize getDisplaySize(ImageView imageView) {
        int width = imageView.getWidth();
        DisplayMetrics metircs = imageView.getContext().getResources().getDisplayMetrics();
        if (width == 0) {
            width = imageView.getLayoutParams().width;
        }
        if (width == 0) {
            width = getValueByAttribute(imageView,"mMaxWidth");
        }
        if (width == 0) {
            width = metircs.widthPixels;
        }
        int height = imageView.getHeight();
        if (height == 0) {
            height = imageView.getLayoutParams().height;
        }
        if (height == 0) {
            height = getValueByAttribute(imageView,"mMaxHeight");
        }
        if (height == 0) {
            height = metircs.heightPixels;
        }
        ImageSize size = new ImageSize();
        size.width = width;
        size.height = height;
        return size;
    }

    /**
     * 根据属性获得值
     * @param imageView
     * @param attri
     * @return
     */
    private int getValueByAttribute(ImageView imageView, String attri) {
        Class c=imageView.getClass();
        int value=0;
        try {
            Field fiels=c.getDeclaredField(attri);
            fiels.setAccessible(true);
            value=fiels.getInt(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }


    /**
     * 刷新图片
     *
     * @param path
     * @param imageView
     * @param bitmap
     */
    private void refreshImageView(String path, ImageView imageView, Bitmap bitmap) {
        ImageEntity entity = new ImageEntity();
        entity.bitmap = bitmap;
        entity.imageView = imageView;
        Message msg = Message.obtain();
        msg.obj = entity;
        if (path.equals((String) imageView.getTag()))
            handler.sendMessage(msg);
    }

    /**
     * 保存图片
     */
    class ImageEntity {
        ImageView imageView;
        Bitmap bitmap;
    }

    class ImageSize {
        int height;
        int width;
    }

}
