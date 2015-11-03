package cn.project.aoyolo.cooperation_v1.activity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.LinkedList;
import java.util.List;


import cn.project.aoyolo.cooperation_v1.BaseActivity;
import cn.project.aoyolo.cooperation_v1.R;
import cn.project.aoyolo.cooperation_v1.adapter.ChoosePhotoAdapter;

/**
 * 我的信息里边选择照片
 * Created by Hy on 2015/11/2.
 */
public class ChoosePhoto extends BaseActivity
{
    private GridView gridView;
    private ProgressDialog progressDialog=null;//扫描相片时显示进度条
    private List<String> pathList=new LinkedList<String>();//
    private Handler handler=null;
    private ChoosePhotoAdapter adapter=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.myindo_choose_photo_layout);
        initView();
        intHandler();
    }

    private void intHandler() {
        handler=new Handler()
        {
            @Override
            public void handleMessage(Message msg) {
                if(progressDialog.isShowing())
                    progressDialog.dismiss();
                if(pathList.size()>0)
                {
                    adapter=new ChoosePhotoAdapter(ChoosePhoto.this,R.layout.myinfo_choose_photo_item,pathList);
                    gridView.setAdapter(adapter);

                }
                else
                {
                    showToast("不存在照片");
                }
            }
        };
    }

    private void initView() {
        gridView=(GridView)findViewById(R.id.my_info_gridView);
        scanImage();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //上传图片至服务器
                //返回的我的信息页面
            }
        });
    }

    /**
     * 扫描相片
     */
    private void scanImage() {
        //判断存储卡是否存在
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
        {
            progressDialog=ProgressDialog.show(this,null,"正在扫描相片");
            //开始扫描相片
            Thread thread=new Thread(new Runnable() {
                @Override
                public void run() {
                    Uri uri= MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    ContentResolver resolver=getContentResolver();
                    Cursor cursor=resolver.query(uri,null,MediaStore.Images.Media.MIME_TYPE+"=? or "
                            +MediaStore.Images.Media.MIME_TYPE+"=?"
                            ,new String[]{"image/png","image/jpeg"},MediaStore.Images.Media.DATE_MODIFIED
                    );
                    while(cursor.moveToNext())
                    {
                        String path=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                        pathList.add(path);
                    }
                    handler.sendEmptyMessage(0);
                }
            });
            thread.start();
        }
        else
        {
            showToast("请检查sd卡状态");
        }
    }


}
