package cn.project.aoyolo.cooperation_v1.API;

import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.io.File;

import cn.project.aoyolo.cooperation_v1.entity.GeneralResponse;

/**
 * Created by Myy on 2015/11/3.
 */
public class UpLoadFileApi extends Api{
    public static void upLoadOneImg(File file,RequestCallBack<GeneralResponse<String>> requestCallBack){
        RequestParams params = new RequestParams();
        params.addBodyParameter("pics",file);
        String url = NURL.concat("/action/upload/filesUpload");
       http.send(HttpRequest.HttpMethod.POST,url,params,requestCallBack);
    }
}
