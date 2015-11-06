package cn.project.aoyolo.cooperation_v1.API;
import com.google.gson.Gson;

import org.kymjs.kjframe.http.HttpCallBack;
import org.kymjs.kjframe.http.HttpParams;
import org.kymjs.kjframe.http.Request;

import cn.project.aoyolo.cooperation_v1.entity.DistanceEntity;
import cn.project.aoyolo.cooperation_v1.entity.QueryEntity;
import cn.project.aoyolo.cooperation_v1.entity.DetailsEntity;
import cn.project.aoyolo.cooperation_v1.utils.HttpParamsObject;

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
    public static void queryLastedCommon(QueryEntity queryEntity , HttpCallBack httpCallBack){
        String url = NURL.concat("/action/common/queryLastedCommon");
        String s=new Gson().toJson(queryEntity);
        HttpParams param = new HttpParams();
        param.put("params",s);
        httpBuilder.httpMethod(Request.HttpMethod.POST).url(url).params(param).callback(httpCallBack).useCache(true).request();
    }
    public static void queryShortDistance(DistanceEntity distanceEntity , HttpCallBack httpCallBack){
        String url = NURL.concat("/action/common/queryShortDistance");
        String s=new Gson().toJson(distanceEntity);
        HttpParams param = new HttpParams();
        param.put("params",s);
        httpBuilder.httpMethod(Request.HttpMethod.POST).url(url).params(param).callback(httpCallBack).useCache(true).request();
    }
    public static void selectDetailInfo(DetailsEntity ditailsEntity , HttpCallBack httpCallBack){
        String url = NURL.concat("/action/common/selectDetailInfo");
        String s=new Gson().toJson(ditailsEntity);
        HttpParams param = new HttpParams();
        param.put("params",s);
        httpBuilder.httpMethod(Request.HttpMethod.POST).url(url).params(param).callback(httpCallBack).useCache(true).request();
    }
}
