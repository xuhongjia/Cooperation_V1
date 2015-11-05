package cn.project.aoyolo.cooperation_v1;

import android.os.Handler;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.kymjs.kjframe.http.HttpCallBack;

import cn.project.aoyolo.cooperation_v1.API.UserApi;
import cn.project.aoyolo.cooperation_v1.entity.GeneralResponse;
import cn.project.aoyolo.cooperation_v1.entity.User;

/**
 * Created by Myy on 2015/11/5.
 */
public class LoginManager {
    public static final int LOGIN_SUCCESS=0;
    public static final int LOGIN_FAILED=1;
    private static LoginManager _loginManager;
    private LoginManager(){

    }
    public static LoginManager getInstence(){
        if(_loginManager==null)
        {
            _loginManager=new LoginManager();
        }
        return _loginManager;
    }

    /**
     * 手机号登陆
     * @param phone 账户
     * @param password 密码
     * @param handler 由于网络请求是异步的，用handler传递结果，并记录到UserManager的User里，返回的是what的空message，LOGIN_SUCCESS为成功，LOGIN_FAILED为失败
     * @return
     */
    public void login(String phone,String password, final Handler handler){
        UserApi.Login(phone, password, new HttpCallBack() {
            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);
                GeneralResponse<User> response = new Gson().fromJson(t,new TypeToken<GeneralResponse<User>>(){}.getType());
                if(response.isSuccess()) {
                    User user = response.getData();
                    UserManager.getInstance().setUser(user);
                    handler.sendEmptyMessageDelayed(LOGIN_SUCCESS, 1000);
                }else
                {
                    handler.sendEmptyMessageDelayed(LOGIN_FAILED, 1000);
                }
            }
            @Override
            public void onFailure(int errorNo, String strMsg) {
                super.onFailure(errorNo, strMsg);
            }
        });

    }

}
