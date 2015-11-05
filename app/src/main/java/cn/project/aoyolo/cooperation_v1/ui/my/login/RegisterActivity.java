package cn.project.aoyolo.cooperation_v1.ui.my.login;


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
import cn.project.aoyolo.cooperation_v1.R;
import cn.project.aoyolo.cooperation_v1.utils.MobUtils;
import cn.project.aoyolo.cooperation_v1.utils.NetWorkUtils;


/**
 * 注册
 * Created by Hy on 2015/11/3.
 */
public class RegisterActivity extends BaseActivity {
    private TextView tvTitle;
    private EditText etPhone, etPsw, etValid;
    private Button btRegister, btValid;
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
        initBtValid();
        initBtRegister();
    }

    private void initBtRegister() {
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = etPhone.getText().toString();
                String validNumber = etValid.getText().toString();
                if (NetWorkUtils.isNetworkAlive())
                    MobUtils.validCode(phoneNumber, validNumber);
                else
                    showToast("请链接网络");
            }
        });
    }

    /**
     * 验证验证码回调
     */
    private void validCallback() {
        if (isValidTrue) {
            //登录
            showToast("正确");
        } else {
            showToast("验证码不对");

        }
    }

    /**
     * 获取验证码
     */
    private void initBtValid() {
        btValid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btValid.isEnabled())
                    btValid.setEnabled(false);
                String phoneNumber = etPhone.getText().toString();
                if (NetWorkUtils.isNetworkAlive())
                    MobUtils.getVerifyCode(phoneNumber);
                else
                    showToast("请链接网络");


            }
        });
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
