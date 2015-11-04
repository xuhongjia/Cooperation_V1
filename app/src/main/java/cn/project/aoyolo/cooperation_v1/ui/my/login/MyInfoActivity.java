package cn.project.aoyolo.cooperation_v1.ui.my.login;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import cn.project.aoyolo.cooperation_v1.BaseActivity;
import cn.project.aoyolo.cooperation_v1.R;
import cn.project.aoyolo.cooperation_v1.widget.RoundCornerImageView;

/**
 * 我的信息
 * Created by Hy on 2015/11/2.
 */
public class MyInfoActivity extends BaseActivity
{

    private RoundCornerImageView headerView;//头像
    private Dialog chooseDialog;//选择照相或者相片弹出窗
    private Button btCancle,btTakePhoto,btChoosePhoto;//取消，照相，选择照片
    private final static int TAKE_PHOTO=1;
    private Bitmap bitmap;//保存相片
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);
        initView();
        initChooseDialog();
    }

    private void initView() {
        headerView=(RoundCornerImageView)findViewById(R.id.imageView);
        initChooseDialog();
        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!chooseDialog.isShowing())
                    chooseDialog.show();
            }
        });
    }

    /**
     * 初始化选择功能弹出窗口
     */
    private void initChooseDialog() {

        View view= LayoutInflater.from(MyInfoActivity.this).inflate(R.layout.myinfo_choose_layout,null);
        ViewGroup.LayoutParams lp=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        btCancle=(Button)view.findViewById(R.id.cancel);
        btTakePhoto=(Button)view.findViewById(R.id.btTakePhoto);
        btChoosePhoto=(Button)view.findViewById(R.id.btChoosePhoto);
        initBtCancle();
        initBtChoosePhoto();
        initTakePhoto();
        chooseDialog=new AlertDialog.Builder(MyInfoActivity.this).setView(view).create();
        chooseDialog.setCanceledOnTouchOutside(true);//点击屏幕外消失
        Window window=chooseDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);

    }

    /**
     * 照相
     */
    private void initTakePhoto()
    {
        btTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent("android.media.action.IMAGE_CAPTURE"), TAKE_PHOTO);
            }
        });
    }

    /**
     * 从相册中选择
     */
    private void initBtChoosePhoto() {
        btChoosePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MyInfoActivity.this, ChoosePhoto.class);
                startActivity(i);
            }
        });

    }

    /**
     * 取消
     */
    private void initBtCancle() {
        btCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chooseDialog.isShowing())
                    chooseDialog.dismiss();
            }
        });

    }

    /**
     * 返回键
     * @param view
     */
    public void back(View view)
    {
        finish();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode==TAKE_PHOTO)
            if(resultCode==RESULT_OK)
            {
                bitmap=(Bitmap)data.getExtras().get("data");
                headerView.setImageBitmap(bitmap);
                if(chooseDialog.isShowing())
                    chooseDialog.dismiss();
            }

    }
}
