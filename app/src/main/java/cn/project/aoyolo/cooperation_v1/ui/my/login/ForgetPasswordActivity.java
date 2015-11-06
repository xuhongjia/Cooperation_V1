package cn.project.aoyolo.cooperation_v1.ui.my.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import org.kymjs.kjframe.http.HttpCallBack;

import cn.project.aoyolo.cooperation_v1.API.UserApi;
import cn.project.aoyolo.cooperation_v1.BaseActivity;
import cn.project.aoyolo.cooperation_v1.LoginManager;
import cn.project.aoyolo.cooperation_v1.R;
import cn.project.aoyolo.cooperation_v1.UserManager;
import cn.project.aoyolo.cooperation_v1.entity.GeneralResponse;
import cn.project.aoyolo.cooperation_v1.entity.User;
import cn.project.aoyolo.cooperation_v1.utils.MobUtils;
import cn.project.aoyolo.cooperation_v1.utils.NetWorkUtils;
import cn.project.aoyolo.cooperation_v1.utils.TimeCount;
import cn.smssdk.SMSSDK;

public class ForgetPasswordActivity extends BaseActivity {

    @ViewInject(R.id.etPhone)
    private EditText etPhone;
    @ViewInject(R.id.etPsw)
    private EditText etPsw;
    @ViewInject(R.id.etValid)
    private EditText etValid;
    @ViewInject(R.id.btValid)
    private Button btValid;
    @ViewInject(R.id.bt_rest_password)
    private Button bt_rest_password;
    @ViewInject(R.id.my_info)
    private TextView tvTitle;
    @ViewInject(R.id.forget_password_view)
    private View forget_password_view;
    @ViewInject(R.id.forget_progress)
    private View mProgressView;
    @ViewInject(R.id.forget_head)
    private View forget_head;
    private TimeCount time;
    private Handler handler;
    private static final int  SENDSMS = 1, VALIDSMSTRUE = 2, VALIDSMSFALSE = 3;
    private boolean isValidTrue = false, isThreadRun = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        ViewUtils.inject(this);
        init();
        initHandler();
        MobUtils.init(ForgetPasswordActivity.this, handler);
    }
    //初始化数据
    private void init(){
        time=new TimeCount(60000,1000,btValid);
        initEtPhone();
    }
    //初始化handler
    private void initHandler(){
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
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
     * 验证验证码回调
     */
    private void validCallback() {
        String phone = etPhone.getText().toString().trim();
        String password = etPsw.getText().toString().trim();
        if (isValidTrue) {
            UserApi.update_pwd(phone, password, new HttpCallBack() {
                @Override
                public void onSuccess(String t) {
                    super.onSuccess(t);
                    GeneralResponse<User> response = new Gson().fromJson(t,new TypeToken<GeneralResponse<User>>(){}.getType());
                    UserManager.getInstance().setUser(response.getData());
                    MobUtils.UnRegsiterHandler();
                    finish();
                    overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                }
                @Override
                public void onFailure(int errorNo, String strMsg) {
                    super.onFailure(errorNo, strMsg);
                }
            });
            showToast("正确");
        } else {
            showToast("验证码不对");
            showProgress(false);
        }
    }
    @OnClick({R.id.btValid,R.id.bt_rest_password,})
    private void onClick(View view){
        switch (view.getId())
        {
            case R.id.btValid:
            {
                String phoneNumber = etPhone.getText().toString();
                if (NetWorkUtils.isNetworkAlive()) {
                    MobUtils.getVerifyCode(phoneNumber);
                    time.start();
                    bt_rest_password.setEnabled(true);
                } else
                    showToast("请链接网络");
            }
            break;
            case R.id.bt_rest_password:
            {
                showProgress(true);
                String phoneNumber = etPhone.getText().toString();
                String validNumber = etValid.getText().toString();
                if (NetWorkUtils.isNetworkAlive())
                    MobUtils.validCode(phoneNumber, validNumber);
                else {
                    showProgress(false);
                    showToast("请链接网络");
                }
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(etPhone.getWindowToken(),0);
                inputMethodManager.hideSoftInputFromWindow(etPsw.getWindowToken(),0);
                inputMethodManager.hideSoftInputFromWindow(etValid.getWindowToken(),0);
            }
            break;
            default:
                break;
        }
    }
    //返回按钮事件
    public void back(View view) {
        finish();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
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
                    if (!btValid.isEnabled() && (btValid.getText().toString().trim().equals("获取验证码") || btValid.getText().toString().trim().equals("重新验证")))
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

    /**
     * 显示Progress.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            forget_password_view.setVisibility(show ? View.GONE : View.VISIBLE);
            forget_head.setVisibility(show ? View.GONE : View.VISIBLE);
            forget_head.animate().setDuration(shortAnimTime).alpha(show?0:1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    forget_head.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });
            forget_password_view.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    forget_password_view.setVisibility(show ? View.GONE : View.VISIBLE);
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
            forget_password_view.setVisibility(show ? View.GONE : View.VISIBLE);
            forget_head.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    /**
     * 发送短信回调
     */
    public void sendCallback() {
        showToast("验证码已发送");
        if (!bt_rest_password.isEnabled())
            bt_rest_password.setEnabled(true);
        //btValid.setEnabled(false);
        if (!isThreadRun) {
            isThreadRun = true;
        }
        //isCountdown = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
