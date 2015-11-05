package cn.project.aoyolo.cooperation_v1.ui.my.login;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import org.kymjs.kjframe.http.HttpCallBack;

import cn.project.aoyolo.cooperation_v1.API.UserApi;
import cn.project.aoyolo.cooperation_v1.BaseActivity;
import cn.project.aoyolo.cooperation_v1.LoginManager;
import cn.project.aoyolo.cooperation_v1.R;
import cn.project.aoyolo.cooperation_v1.UserManager;
import cn.project.aoyolo.cooperation_v1.entity.User;
import cn.project.aoyolo.cooperation_v1.utils.MobUtils;
import cn.project.aoyolo.cooperation_v1.utils.NetWorkUtils;
import cn.project.aoyolo.cooperation_v1.utils.TimeCount;


/**
 * 注册
 * Created by Hy on 2015/11/3.
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private TextView tvTitle;
    private EditText etPhone, etPsw, etValid;
    private Button btRegister, btValid;
    private View login_form,forget_passeord,mProgressView,Register_view,Register_head;;
    public static Handler handler;
    //private Thread thread;
    private int countdown = 60;
    private static final int COUNTDOWN = 0, SENDSMS = 1, VALIDSMSTRUE = 2, VALIDSMSFALSE = 3;
    private boolean isValidTrue = false, isThreadRun = false,isThreadBreak=false;
    private TimeCount time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        initView();
        initHandler();
        //initThread();
        MobUtils.init(this);
    }

//    /**
//     * 用于倒计时
//     */
//    private void initThread() {
//        thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    try {
//                        if(isThreadBreak)
//                            break;
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    handler.sendEmptyMessage(0);
//                }
//
//            }
//        });
//    }

    /**
     * 更新倒计时时间
     */
    private void initHandler() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
//                    case COUNTDOWN:
//                        countDown();
//                        break;
                    case SENDSMS:
                        sendCallback();
                        break;
                    case VALIDSMSTRUE:
                        isValidTrue = true;
                        validCallback();
                        break;
                    case VALIDSMSFALSE:
                        isValidTrue = false;
                        validCallback();
                        break;
                    default:
                        return;
                }
            }
        };
    }

//    /**
//     * 倒数
//     */
//    private void countDown() {
//        countdown--;
//        if (countdown <= 0 && isCountdown) {
//            btValid.setText("获取验证码");
//            btValid.setEnabled(true);
//            countdown = 60;
//            isCountdown = false;
//            btRegister.setEnabled(false);
//        } else if (isCountdown) {
//            btValid.setText(countdown + "");
//            btValid.setEnabled(false);
//        }
//    }

    private void initView() {
        tvTitle = (TextView) findViewById(R.id.my_info);
        tvTitle.setText("手机号注册");
        etPhone = (EditText) findViewById(R.id.etPhone);
        etPsw = (EditText) findViewById(R.id.etPsw);
        etValid = (EditText) findViewById(R.id.etValid);
        initEtPhone();
        btRegister = (Button) findViewById(R.id.btRegister);
        btValid = (Button) findViewById(R.id.btValid);
        btValid.setOnClickListener(this);
        btRegister.setOnClickListener(this);
        forget_passeord = findViewById(R.id.forget_passeord);
        login_form = findViewById(R.id.login_form);
        forget_passeord.setOnClickListener(this);
        login_form.setOnClickListener(this);
        mProgressView = findViewById(R.id.register_progress);
        Register_view = findViewById(R.id.register_view);
        Register_head = findViewById(R.id.register_head);
        time=new TimeCount(60000, 1000,btValid,btRegister);
    }
    //全部按钮事件
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btValid: {
                String phoneNumber = etPhone.getText().toString();
                if (NetWorkUtils.isNetworkAlive()) {
                    MobUtils.getVerifyCode(phoneNumber);
                    time.start();
                    btRegister.setEnabled(true);
                }
                else
                    showToast("请链接网络");
            }
            break;
            case R.id.btRegister: {
                showProgress(true);
                String phoneNumber = etPhone.getText().toString();
                String validNumber = etValid.getText().toString();
                if (NetWorkUtils.isNetworkAlive())
                    MobUtils.validCode(phoneNumber, validNumber);
                else {
                    showProgress(false);
                    showToast("请链接网络");
                }
            }
            break;
            case R.id.forget_passeord:
                //跳转到忘记密码界面
                break;
            case R.id.login_form:
                startActivity(new Intent(this,LoginActivity.class));
                finish();
                break;
            default:
                break;
        }
    }
    /**
     * 显示Progress.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            Register_view.setVisibility(show ? View.GONE : View.VISIBLE);
            Register_head.setVisibility(show ? View.GONE : View.VISIBLE);
            Register_head.animate().setDuration(shortAnimTime).alpha(show?0:1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    Register_head.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });
            Register_view.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    Register_view.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            Register_view.setVisibility(show ? View.GONE : View.VISIBLE);
            Register_head.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
    /**
     * 验证验证码回调
     */
    private void validCallback() {
        String phone = etPhone.getText().toString().trim();
        String password = etPsw.getText().toString().trim();
        if (isValidTrue) {
            //注册
            final User user = new User();
            user.setAccount(phone);
            user.setPassword(password);
            user.setImg(UserManager.DEFAULT_HEADER);
            user.setAge(0);
            user.setCredit(100);
            user.setJob("未设置");
            user.setName("匿名");
            user.setAddress("未设置");
            user.setSex(0);
            user.setToken("无");
            UserApi.Register(user, new HttpCallBack() {
                @Override
                public void onSuccess(String t) {
                    super.onSuccess(t);
                    UserManager.getInstance().setUser(user);
                    showProgress(false);
                    finish();
                }
                @Override
                public void onFailure(int errorNo, String strMsg) {
                    super.onFailure(errorNo, strMsg);
                }
            });
            showToast("正确");
        } else {
            showToast("验证码不对");
        }
    }

    private void initEtPhone() {
        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            //当输入字符达到11个时，注册按钮可用
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 11) {
                    if (!btValid.isEnabled()&&(btValid.getText().toString().trim().equals("获取验证码")||btValid.getText().toString().trim().equals("重新验证")))
                        btValid.setEnabled(true);
                } else {
                    btValid.setEnabled(false);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
    public void back(View view) {
        finish();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }
    /**
     * 发送短信回调
     */
    public void sendCallback() {
        showToast("验证码已发送");
        if (!btRegister.isEnabled())
            btRegister.setEnabled(true);
        //btValid.setEnabled(false);
        if (!isThreadRun) {
            isThreadRun = true;
        }
        //isCountdown = true;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        MobUtils.UnRegsiterHandler();
        isThreadBreak=true;
    }


}
