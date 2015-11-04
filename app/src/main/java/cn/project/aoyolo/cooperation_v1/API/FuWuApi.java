package cn.project.aoyolo.cooperation_v1.API;

import org.kymjs.kjframe.http.HttpCallBack;
import org.kymjs.kjframe.http.HttpParams;
import org.kymjs.kjframe.http.Request;

/**
 * Created by Myy on 2015/11/3.
 */
public class FuWuApi extends Api {
    public static void addJzFuWu(String params , HttpCallBack httpCallBack){
        String url = NURL.concat("/action/homeMaking/addHomeMaking");
        HttpParams param = new HttpParams();
        param.put("params",params);
        httpBuilder.httpMethod(Request.HttpMethod.POST).url(url).params(param).useCache(true).callback(httpCallBack).request();
    }
}
