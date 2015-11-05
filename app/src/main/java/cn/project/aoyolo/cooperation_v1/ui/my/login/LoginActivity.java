package cn.project.aoyolo.cooperation_v1.ui.my.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import cn.project.aoyolo.cooperation_v1.LoginManager;
import cn.project.aoyolo.cooperation_v1.R;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity  {


    //注入控件
    @ViewInject(R.id.phone)
    private AutoCompleteTextView phoneView;
    @ViewInject(R.id.password)
    private EditText mPasswordView;
    @ViewInject(R.id.login_progress)
    private View mProgressView;
    @ViewInject(R.id.login_form)
    private View mLoginFormView;
    private TextInputLayout text_phone;
    @ViewInject(R.id.phone_sign_in_button)
    private Button mPhoneSignInButton;
    @ViewInject(R.id.my_info)
    private TextView title;
    @ViewInject(R.id.header)
    private View header;
    //Handler回调
    private Handler handler ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ViewUtils.inject(this);
        init();
    }
    //初始化数据
    protected  void init(){
        title.setText("用户登录");
        // Set up the login form.
        phoneView = (AutoCompleteTextView) findViewById(R.id.phone);
        text_phone=(TextInputLayout)findViewById(R.id.text_phone);
        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
        mPhoneSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(phoneView.getWindowToken(),0);
                inputMethodManager.hideSoftInputFromWindow(mPasswordView.getWindowToken(),0);
                attemptLogin();
            }
        });
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg)
            {
                switch (msg.what)
                {
                    case LoginManager.LOGIN_SUCCESS:
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
    }

    //登陆事件
    private void attemptLogin() {
        // 清空错误
        phoneView.setError(null);
        mPasswordView.setError(null);

        // 获取控件数据
        String phone = phoneView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // 检查密码
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError("密码不合法");
            focusView = mPasswordView;
            cancel = true;
        }
        // 验证手机号
        if (TextUtils.isEmpty(phone)) {
            phoneView.setError("手机好不能为空");
            focusView = phoneView;
            cancel = true;
        } else if (phone.length()<11) {
            phoneView.setError("请输入正确的手机号码");
            focusView = phoneView;
            cancel = true;
        }

        if (cancel) {
            //焦点在错误的输入框
            focusView.requestFocus();
        } else {
            showProgress(true);
            //登陆
            LoginManager.getInstence().login(phone,password,handler);
        }
    }

    //验证密码
    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 5;
    }

    /**
     * 显示Progress.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            header.setVisibility(show ? View.GONE : View.VISIBLE);
            header.animate().setDuration(shortAnimTime).alpha(show?0:1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    header.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
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
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            header.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

}

