package cn.project.aoyolo.cooperation_v1.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import cn.project.aoyolo.cooperation_v1.MainActivity;
import cn.project.aoyolo.cooperation_v1.R;
import cn.project.aoyolo.cooperation_v1.ui.my.login.LoginActivity;

/**
 *
 * 登录弹出窗口
 * Created by Hy on 2015/11/3.
 */
public class LoginDialog extends AlertDialog
{

    private Context c;
    private Button btCancle,btLogin,btRegister;
    private TextView tvWeiBo,tvWeiXin,tvQQ;
    private View view;//总布局

    public LoginDialog(Context c) {
        super(c);
        this.c=c;
        init();
    }

    public LoginDialog(Context c, int themeResId) {
        super(c, themeResId);
        this.c=c;
        init();
    }

    public LoginDialog(Context c, boolean cancelable, OnCancelListener cancelListener) {
        super(c, cancelable, cancelListener);
        this.c=c;
        init();
    }

    private void init()
    {
        view= LayoutInflater.from(c).inflate(R.layout.choose_login_layout,null);
        ViewGroup.LayoutParams lp=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        initCancel();
        initWeiBoLogin();
        initWeiXinLogin();
        initQQLogin();
        intLogin();
        initRegister();
        this.setView(view);
        this.setCanceledOnTouchOutside(true);
    }

    /**
     * qq登录
     */
    private void initQQLogin()
    {
        tvQQ=(TextView)view.findViewById(R.id.btnLoginQQ);
    }

    /**
     * 注册手机号
     */
    private void initRegister() {
        btRegister=(Button)view.findViewById(R.id.btRegister);
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.handler.sendEmptyMessage(0);//跳转注册页面
                if(LoginDialog.this.isShowing())
                {
                    LoginDialog.this.dismiss();
                }
            }
        });
    }

    /**
     * 手机号登录
     */
    private void intLogin() {
        btLogin=(Button)view.findViewById(R.id.btLogin);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c.startActivity(new Intent(c, LoginActivity.class));
                if(LoginDialog.this.isShowing())
                {
                    LoginDialog.this.dismiss();
                }
            }
        });
    }

    /**
     * 微信登陆
     */
    private void initWeiXinLogin() {
        tvWeiXin=(TextView)view.findViewById(R.id.btnLoginWeixin);
    }

    /**
     * 微博登录
     */
    private void initWeiBoLogin() {
        tvWeiBo=(TextView)view.findViewById(R.id.btnLoginWeibo);

    }

    /**
     * 取消
     */
    private void initCancel() {
        btCancle=(Button)view.findViewById(R.id.btCancel);
        btCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LoginDialog.this.isShowing())
                    LoginDialog.this.dismiss();
            }
        });

    }

}
