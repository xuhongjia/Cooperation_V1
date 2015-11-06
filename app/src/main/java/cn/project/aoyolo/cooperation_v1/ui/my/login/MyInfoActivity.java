package cn.project.aoyolo.cooperation_v1.ui.my.login;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

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
    private Bitmap photo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);
        ViewUtils.inject(this);
        textView.setVisibility(View.GONE);
        initData();
//        initView();
//        initChooseDialog();
    }
    public void initData(){
        if(UserManager.isLogin())
        {
            User user = UserManager.getInstance().getUser();
            ImageUrlLoaderWithCache.getInstence().ImageLoad(user.getImg(), imageView);
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

    //点击事件
    @OnClick({R.id.imageView,R.id.my_info_name,R.id.my_info_phone,R.id.my_info_sex,R.id.my_info_age,R.id.my_info_address,
            R.id.my_info_job,R.id.my_info_back})
    public void onClick(View view){
        switch (view.getId())
        {
            case R.id.imageView:
                setImg();
                break;
            case R.id.my_info_name:
                break;
            case R.id.my_info_phone:
                break;
            case R.id.my_info_sex:
                break;
            case R.id.my_info_age:
                break;
            case R.id.my_info_address:
                break;
            case R.id.my_info_job:
                break;
            case R.id.my_info_back:
                finish();
                break;
            default:
                break;
        }
    }
//    //初始化界面
//    private void initView() {
//        initChooseDialog();
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!chooseDialog.isShowing())
//                    chooseDialog.show();
//            }
//        });
//    }
//
//    /**
//     * 初始化选择功能弹出窗口
//     */
//    private void initChooseDialog() {
//
//        View view= LayoutInflater.from(MyInfoActivity.this).inflate(R.layout.myinfo_choose_layout,null);
//        ViewGroup.LayoutParams lp=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
//        view.setLayoutParams(lp);
//        btCancle=(Button)view.findViewById(R.id.cancel);
//        btTakePhoto=(Button)view.findViewById(R.id.btTakePhoto);
//        btChoosePhoto=(Button)view.findViewById(R.id.btChoosePhoto);
//        initBtCancle();
//        initBtChoosePhoto();
//        initTakePhoto();
//        chooseDialog=new AlertDialog.Builder(MyInfoActivity.this).setView(view).create();
//        chooseDialog.setCanceledOnTouchOutside(true);//点击屏幕外消失
//        Window window=chooseDialog.getWindow();
//        window.setGravity(Gravity.BOTTOM);
//
//    }
//
//    /**
//     * 照相
//     */
//    private void initTakePhoto()
//    {
//        btTakePhoto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivityForResult(new Intent("android.media.action.IMAGE_CAPTURE"), TAKE_PHOTO);
//            }
//        });
//    }
//
//    /**
//     * 从相册中选择
//     */
//    private void initBtChoosePhoto() {
//        btChoosePhoto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(MyInfoActivity.this, ChoosePhoto.class);
//                startActivity(i);
//            }
//        });
//
//    }
//
//    /**
//     * 取消
//     */
//    private void initBtCancle() {
//        btCancle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (chooseDialog.isShowing())
//                    chooseDialog.dismiss();
//            }
//        });
//
//    }
//
//    /**
//     * 返回键
//     * @param view
//     */
//    public void back(View view)
//    {
//        finish();
//
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
//        if(requestCode==TAKE_PHOTO)
//            if(resultCode==RESULT_OK)
//            {
//                bitmap=(Bitmap)data.getExtras().get("data");
//                imageView.setImageBitmap(bitmap);
//                if(chooseDialog.isShowing())
//                    chooseDialog.dismiss();
//            }
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
            Drawable drawable = new BitmapDrawable(imageView.getResources(),photo);
            imageView.setImageDrawable(drawable);
        }
    }
}
