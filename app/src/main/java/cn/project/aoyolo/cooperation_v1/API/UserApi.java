package cn.project.aoyolo.cooperation_v1.API;

import org.kymjs.kjframe.http.HttpCallBack;
import org.kymjs.kjframe.http.HttpParams;
import org.kymjs.kjframe.http.Request;

import cn.project.aoyolo.cooperation_v1.UserManager;
import cn.project.aoyolo.cooperation_v1.entity.User;
import cn.project.aoyolo.cooperation_v1.utils.HttpParamsObject;

/**
 * Created by Myy on 2015/11/5.
 */
public class UserApi extends Api {
    public static void Login(String phone,String password,HttpCallBack httpCallBack){
        String url = NURL.concat("/action/user/login");
        HttpParams params = new HttpParams();
        params.put("account",phone);
        params.put("password",password);
        httpBuilder.httpMethod(Request.HttpMethod.POST).url(url).params(params).callback(httpCallBack).useCache(true).request();
    }
    public static void Register(User user,HttpCallBack httpCallBack){
        String url = NURL.concat("/action/user/registered");
        HttpParamsObject params = new HttpParamsObject(user);
        httpBuilder.httpMethod(Request.HttpMethod.POST).url(url).params(params).callback(httpCallBack).useCache(true).request();
    }
    public static void update_pwd(String phone,String password,HttpCallBack httpCallBack){
        String url = NURL.concat("/action/user/update_pwd");
        HttpParams params = new HttpParams();
        params.put("account",phone);
        params.put("password",password);
        httpBuilder.httpMethod(Request.HttpMethod.POST).url(url).params(params).callback(httpCallBack).useCache(true).request();
    }
}
