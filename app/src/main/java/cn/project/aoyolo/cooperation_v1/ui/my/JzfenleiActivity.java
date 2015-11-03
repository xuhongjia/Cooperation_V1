package cn.project.aoyolo.cooperation_v1.ui.my;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import cn.project.aoyolo.cooperation_v1.BaseActivity;
import cn.project.aoyolo.cooperation_v1.R;

public class JzfenleiActivity extends BaseActivity {
    @ViewInject(R.id.img_head)
    private ImageButton img_head;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jzfenlei);
        ViewUtils.inject(this);

    }
    @OnClick({R.id.img_head})
    public void onClick(View view){
        switch (view.getId())
        {
            case R.id.img_head:
                setImg();
                break;
            default:
                break;
        }
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
