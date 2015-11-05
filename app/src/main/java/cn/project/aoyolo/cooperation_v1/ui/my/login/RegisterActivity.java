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


import cn.project.aoyolo.cooperation_v1.BaseActivity;
import cn.project.aoyolo.cooperation_v1.LoginManager;
import cn.project.aoyolo.cooperation_v1.R;
import cn.project.aoyolo.cooperation_v1.utils.MobUtils;
import cn.project.aoyolo.cooperation_v1.utils.NetWorkUtils;


/**
 * 注册
 * Created by Hy on 2015/11/3.
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private TextView tvTitle;
    private EditText etPhone, etPsw, etValid;
    private Button btRegister, btValid;
    private View login_form,forget_passeord,mProgressView;;
    public static Handler handler;
    private Thread thread;
    private int countdown = 60;
    private boolean isCountdown = false;
    private static final int COUNTDOWN = 0, SENDSMS = 1, VALIDSMSTRUE = 2, VALIDSMSFALSE = 3;
    private boolean isValidTrue = false, isThreadRun = false,isThreadBreak=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        initView();
        initHandler();
        initThread();
        MobUtils.init(this);
    }

    /**
     * 用于倒计时
     */
    private void initThread() {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        if(isThreadBreak)
                            break;
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.sendEmptyMessage(0);
                }

            }
        });
    }

    /**
     * 更新倒计时时间
     */
    private void initHandler() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case COUNTDOWN:
                        countDown();
                        break;
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

    /**
     * 倒数
     */
    private void countDown() {
        countdown--;
        if (countdown <= 0 && isCountdown) {
            btValid.setText("获取验证码");
            btValid.setEnabled(true);
            countdown = 60;
            isCountdown = false;
            btRegister.setEnabled(false);
        } else if (isCountdown) {
            btValid.setText(countdown + "");
            btValid.setEnabled(false);
        }
    }

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
    }
    //全部按钮事件
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btValid: {

                if (btValid.isEnabled())
                    btValid.setEnabled(false);
                String phoneNumber = etPhone.getText().toString();
                if (NetWorkUtils.isNetworkAlive())
                    MobUtils.getVerifyCode(phoneNumber);
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
        }
    }
    /**
     * 验证验证码回调
     */
    private void validCallback() {
        Handler handler = new Handler()
        {
            @Override
            public void handleMessage(Message msg)
            {
                switch (msg.what)
                {
                    case LoginManager.LOGIN_SUCCESS:
                        showProgress(false);
                        finish();
                        overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
                        break;
                    case LoginManager.LOGIN_FAILED:
                        showProgress(false);
                        break;
                    default:
                        break;
                }
            }
        };
        String phone = etPhone.getText().toString().trim();
        String password = etPsw.getText().toString().trim();
        if (isValidTrue) {
            //登录
            LoginManager.getInstence().login(phone,password,handler);
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
                    if (!btValid.isEnabled())
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
        btValid.setEnabled(false);
        if (!isThreadRun) {
            thread.start();
            isThreadRun = true;
        }
        isCountdown = true;


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        MobUtils.UnRegsiterHandler();
        isThreadBreak=true;
    }


}
