package cn.project.aoyolo.cooperation_v1.API;

import android.util.Log;

import org.kymjs.kjframe.http.HttpCallBack;
import org.kymjs.kjframe.http.HttpParams;
import org.kymjs.kjframe.http.Request;

/**
 * Created by Administrator on 2015/11/6.
 */
public class XuQiuApi extends Api{
    public static void getDemandMain(String params , HttpCallBack httpCallBack){
        String url = NURL.concat("/action/common/queryLastedCommon");
        HttpParams param = new HttpParams();
        param.put("params",params);
        Log.e("params",params);
        httpBuilder.httpMethod(Request.HttpMethod.POST).url(url).params(param).useCache(true).callback(httpCallBack).request();
    }
}
