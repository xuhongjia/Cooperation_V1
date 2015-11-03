package cn.project.aoyolo.cooperation_v1.ui.my;

import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import org.kymjs.kjframe.http.HttpCallBack;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cn.project.aoyolo.cooperation_v1.API.UpLoadFileApi;
import cn.project.aoyolo.cooperation_v1.BaseActivity;
import cn.project.aoyolo.cooperation_v1.R;
import cn.project.aoyolo.cooperation_v1.entity.HomeMaking;

public class JzfenleiActivity extends BaseActivity {
    @ViewInject(R.id.img_head)
    private ImageButton img_head;
    @ViewInject(R.id.jzType)
    private RadioGroup jzType;
    @ViewInject(R.id.name)
    private EditText name;
    @ViewInject(R.id.xzChoose)
    private TextView xzChoose;
    @ViewInject(R.id.note)
    private EditText note;
    @ViewInject(R.id.phone)
    private EditText phone;
    @ViewInject(R.id.stopTime)
    private TextView stopTime;
    private HomeMaking homeMaking;
    private  Bitmap photo;
    Handler handler;
    private AlertDialog myDialog = null;
    private String[] xinzi = new String[]{"1000-2000","2001-3000","3001-4000"};
    private List<String> data = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jzfenlei);
        ViewUtils.inject(this);
        init();
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg){
                if(msg.what==1){
                    Toast.makeText(JzfenleiActivity.this,"成功",Toast.LENGTH_LONG).show();
                }
            }
        };
    }
    //初始化数据
    public void init(){
        for(String s : xinzi)
        {
            data.add(s);
        }
    }
    @OnClick({R.id.img_head,R.id.xzChoose,R.id.stopTime,R.id.send})
    public void onClick(View view){
        switch (view.getId())
        {
            case R.id.img_head:
                setImg();
                break;
            case R.id.xzChoose:
                xzChoose();
                break;
            case R.id.stopTime:
                break;
            case R.id.send:
                send();
                break;
            default:
                break;
        }
    }
    //选择薪资水平
    public void xzChoose(){

        myDialog = new AlertDialog.Builder(this).create();
        myDialog.show();
        myDialog.getWindow().setContentView(R.layout.my_alert_dialog);
        ListView listView =(ListView)myDialog.getWindow().findViewById(R.id.alert_list_view);
        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                xzChoose.setText(data.get(position).toString().trim());
                myDialog.dismiss();
            }
        });
    }
    //发送提交请求
    public void send(){
        File file = new File(Environment.getExternalStorageDirectory(),IMAGE_FILE_NAME);
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            photo.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        UpLoadFileApi.upLoadOneImg(file, new HttpCallBack() {
            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);
                homeMaking.setImg(t.trim());
                homeMaking.setName(name.getText().toString().trim());
            }
            @Override
            public void onFailure( int errorNo,String strMsg) {
                super.onFailure(errorNo,strMsg);
            }
        });
    }
    //返回事件
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        } else {
            switch (requestCode) {
                case IMAGE_REQUEST_CODE:
                    resizeImage(data.getData());
                    break;
                case CAMERA_REQUEST_CODE:
                    if (isSdcardExisting()) {
                        resizeImage(getImageUri());
                    } else {
                        Toast.makeText(this, "未找到存储卡，无法存储照片！",
                                Toast.LENGTH_LONG).show();
                    }
                    break;

                case RESIZE_REQUEST_CODE:
                    if (data != null) {
                        showResizeImage(data);
                    }
                    break;
            }
        }
    }
    private void showResizeImage(Intent data)  {
        Bundle extras = data.getExtras();
        if (extras != null) {
            photo = extras.getParcelable("data");
            Drawable drawable = new BitmapDrawable(img_head.getResources(),photo);
            img_head.setImageDrawable(drawable);
        }
    }
}
