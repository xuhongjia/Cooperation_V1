package cn.project.aoyolo.cooperation_v1.utils;

import android.graphics.Bitmap;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 网络请求
 * Created by Hy on 2015/11/3.
 */
public class HttpUtils
{
    private static ExecutorService executePool= Executors.newFixedThreadPool(5);//所有网络操作在此请求
    private static Bitmap bitmap;
    private static int i=0;
    private static String result;



    /**
     * 通过网络路径加载图片
     * @param url 相片网络路径
     * @param imageView 用于显示相片的ImageView
     * @return
     */
    public static Bitmap downLoadBitmap(final String url, final ImageView imageView)
    {
        executePool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    InputStream is=new URL(url).openStream();
                    BufferedInputStream bis=new BufferedInputStream(is);
                    //获得压缩图片
                    bitmap= ImageLoader.getInstance().getCompressBitmap(bis,imageView);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        return bitmap;
    }

    /**
     * 通过网络路径加载图片,多路径使用
     * @param url
     * @param imageView
     * @return
     */
    public static List<Bitmap> downLoadBitmap(final String[] url, final ImageView[] imageView)
    {
        final List<Bitmap> list=new ArrayList<Bitmap>();
        if(url!=null&&url.length>0)
        {
            for (i=0;i<url.length;i++)
            {
                executePool.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            InputStream is=new URL(url[i]).openStream();
                            BufferedInputStream bis=new BufferedInputStream(is);
                            //获得压缩图片
                            bitmap= ImageLoader.getInstance().getCompressBitmap(bis,imageView[i]);
                            list.add(bitmap);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }

        return list;
    }

    public static String downLoadDate(final String params,final String url)
    {

        executePool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpURLConnection con=(HttpURLConnection)new URL(url).openConnection();
                    con.setRequestMethod("POST");
                    con.setDoOutput(true);//post必须设置输出流为true
                    con.setDoInput(true);
                    con.setUseCaches(false);//post不允许使用缓存
                    con.setConnectTimeout(5000);//如果不设置，会一直占用线程
                    con.setReadTimeout(5000);
                    con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");


                    OutputStream os=con.getOutputStream();//进行连接并向对方传递数据
                    DataOutputStream oos=new DataOutputStream(os);
                    String content=URLEncoder.encode(params,"UTF-8");
                    oos.writeBytes(params);
                    oos.flush();
                    oos.close();
                    //获得返回数据
                    InputStream is=con.getInputStream();
                    InputStreamReader isr=new InputStreamReader(is);
                    BufferedReader reader=new BufferedReader(isr);
                    result=reader.readLine();
                    is.close();
                    isr.close();
                    reader.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        return result;
    }
}
