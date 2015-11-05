package cn.project.aoyolo.cooperation_v1.API;

import org.kymjs.kjframe.http.HttpCallBack;
import org.kymjs.kjframe.http.HttpParams;
import org.kymjs.kjframe.http.Request;

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
}
