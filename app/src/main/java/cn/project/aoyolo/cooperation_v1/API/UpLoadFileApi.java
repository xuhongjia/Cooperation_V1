package cn.project.aoyolo.cooperation_v1.API;


import org.kymjs.kjframe.KJHttp;
import org.kymjs.kjframe.http.HttpCallBack;
import org.kymjs.kjframe.http.HttpParams;
import org.kymjs.kjframe.http.Request;

import java.io.File;

import cn.project.aoyolo.cooperation_v1.entity.GeneralResponse;

/**
 * Created by Myy on 2015/11/3.
 */
public class UpLoadFileApi extends Api{
    public static void upLoadOneImg(File file,HttpCallBack requestCallBack){
        HttpParams params =new HttpParams();
        params.put("pics", file);
        String url = NURL.concat("/action/upload/filesUpload");
        httpBuilder.httpMethod(Request.HttpMethod.POST).url(url).params(params).useCache(true).callback(requestCallBack).request();
    }

}
