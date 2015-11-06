package cn.project.aoyolo.cooperation_v1.ui.my.login;

import android.app.Activity;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import cn.project.aoyolo.cooperation_v1.BaseActivity;
import cn.project.aoyolo.cooperation_v1.R;
import cn.project.aoyolo.cooperation_v1.UserManager;
import cn.project.aoyolo.cooperation_v1.entity.User;
import cn.project.aoyolo.cooperation_v1.utils.ImageUrlLoaderWithCache;
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
    @ViewInject(R.id.my_info_name)
    private TextView my_info_name;
    @ViewInject(R.id.my_info_phone)
    private TextView my_info_phone;
    @ViewInject(R.id.my_info_sex)
    private TextView my_info_sex;
    @ViewInject(R.id.my_info_age)
    private TextView my_info_age;
    @ViewInject(R.id.my_info_address)
    private TextView my_info_address;
    @ViewInject(R.id.my_info_job)
    private TextView my_info_job;
    @ViewInject(R.id.my_info_credit)
    private TextView my_info_credit;
    @ViewInject(R.id.imageView)
    private ImageView imageView;
    @ViewInject(R.id.textView)
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);
        ViewUtils.inject(this);
        textView.setVisibility(View.GONE);
        initData();
        initView();
        initChooseDialog();
    }
    public void initData(){
        if(UserManager.isLogin())
        {
            User user = UserManager.getInstance().getUser();
            ImageUrlLoaderWithCache.getInstence().ImageLoad(user.getImg(),imageView);
            my_info_name.setText(user.getName());
            my_info_phone.setText(user.getAccount());
            my_info_address.setText(user.getAddress());
            my_info_age.setText(user.getAge().toString());
            my_info_job.setText(user.getJob());
            switch (user.getSex())
            {
                case 0:
                    my_info_sex.setText("男");
                    break;
                case 1:
                    my_info_sex.setText("女");
                    break;
                case 2:
                    my_info_sex.setText("未设置");
                    break;
                default:
                    break;
            }
            my_info_credit.setText(user.getCredit().toString());
        }
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
