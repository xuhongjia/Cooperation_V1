package cn.project.aoyolo.cooperation_v1.activity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.WindowManager;
import android.widget.GridView;

import java.util.LinkedList;
import java.util.List;

import cn.project.aoyolo.cooperation_v1.BaseActivity;
import cn.project.aoyolo.cooperation_v1.R;

/**
 * 我的信息里边选择照片
 * Created by Hy on 2015/11/2.
 */
public class ChoosePhoto extends BaseActivity
{
    private GridView gridView;
    private ProgressDialog progressDialog=null;//扫描相片时显示进度条
    private List<String> pathList=new LinkedList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.myindo_choose_photo_layout);
        initView();
    }

    private void initView() {
        gridView=(GridView)findViewById(R.id.my_info_gridView);
        scanImage();
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
                    Cursor cursor=resolver.query(uri,null,MediaStore.Images.Media.MIME_TYPE+" or "
                            +MediaStore.Images.Media.MIME_TYPE
                            ,new String[]{".png",".jpeg"},MediaStore.Images.Media.DATE_MODIFIED
                    );
                    while(cursor.moveToNext())
                    {
                        String path=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                        pathList.add(path);
                    }
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
