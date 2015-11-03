package cn.project.aoyolo.cooperation_v1.ui.my;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import cn.project.aoyolo.cooperation_v1.API.UpLoadFileApi;
import cn.project.aoyolo.cooperation_v1.BaseActivity;
import cn.project.aoyolo.cooperation_v1.R;
import cn.project.aoyolo.cooperation_v1.entity.GeneralResponse;
import cn.project.aoyolo.cooperation_v1.entity.HomeMaking;

public class JzfenleiActivity extends BaseActivity {
    @ViewInject(R.id.img_head)
    private ImageButton img_head;
    @ViewInject(R.id.jzType)
    private RadioGroup jzType;
    @ViewInject(R.id.name)
    private EditText name;
    private HomeMaking homeMaking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jzfenlei);
        ViewUtils.inject(this);

    }
    @OnClick({R.id.img_head,R.id.xzChoose,R.id.stopTime,R.id.send})
    public void onClick(View view){
        switch (view.getId())
        {
            case R.id.img_head:
                setImg();
                break;
            case R.id.xzChoose:
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
    //发送提交请求
    public void send(){
        img_head.getDrawingCache(true);
        Bitmap bitmap = img_head.getDrawingCache();
        img_head.getDrawingCache(false);
        File file = new File(Environment.getExternalStorageDirectory(),IMAGE_FILE_NAME);
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        UpLoadFileApi.upLoadOneImg(file, new RequestCallBack<GeneralResponse<String>>() {
            @Override
            public void onSuccess(ResponseInfo<GeneralResponse<String>> responseInfo) {
                GeneralResponse<String> result =responseInfo.result;
                if(result.equals("")||result==null||result.isSuccess())
                {
                    Toast.makeText(JzfenleiActivity.this, "上传图片失败....", Toast.LENGTH_SHORT).show();
                }else
                {
                    homeMaking.setImg(result.getData());
                    homeMaking.setName(name.getText().toString().trim());
                }
            }
            @Override
            public void onFailure(HttpException error, String msg) {

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
            Bitmap photo = extras.getParcelable("data");
            Drawable drawable = new BitmapDrawable(img_head.getResources(),photo);
            img_head.setImageDrawable(drawable);
        }
    }
}
