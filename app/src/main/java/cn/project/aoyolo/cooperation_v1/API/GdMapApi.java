package cn.project.aoyolo.cooperation_v1.API;

/**
 * Created by pangrihui on 2015/11/5.
 */

import android.content.Context;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;

public class GdMapApi {

    private LocationManagerProxy mLocationManagerProxy;
    private static GdMapApi _GDmap;

    private GdMapApi(Context context) {
        mLocationManagerProxy = LocationManagerProxy.getInstance(context
                .getApplicationContext());
    }

    public static GdMapApi getInstance(Context context) {

        if (_GDmap == null) {
            _GDmap = new GdMapApi(context);
        }
        return _GDmap;
    }

   /* // 设置时间间隔为-1，只定位一次
    private void getResult(final AjaxCallBack<String> callBack) {
        mLocationManagerProxy.requestLocationData(
                LocationProviderProxy.AMapNetwork, -1, 15, new GdMapListener() {
                    public void onLocationChanged(AMapLocation amapLocation) {
                        // TODO Auto-generated method stub
                        int errorCode = amapLocation.getAMapException()
                                .getErrorCode();
                        if (amapLocation != null && errorCode == 0) {

                            StringBuffer result = new StringBuffer();
                            String desc = "";
                            // 获取位置信息
                            Bundle locBundle = amapLocation.getExtras();
                            // 分割字符串
                            if (locBundle != null) {
                                desc = locBundle.getString("desc");
                                String[] resultStrArray = desc.split(" ");
                                for (int i = 0; i < resultStrArray.length; i++) {
                                    if (i > 1) {
                                        result.append(resultStrArray[i]);
                                    }
                                }
                            }
                            textView.setText(result.toString());
                            callBack.onSuccess(result.toString());
                            // 定位成功后，移除定位
							*//* mLocationManagerProxy.removeUpdates(this); *//*

                        } else {
                            callBack.onFailure(null, errorCode, amapLocation
                                    .getAMapException().getErrorMessage());

                        }
                    }
                });
        mLocationManagerProxy.setGpsEnable(false);

    }*/

    public void getLocation(GdMapListener listener) {
        mLocationManagerProxy.requestLocationData(
                LocationProviderProxy.AMapNetwork, -1, 15, listener);
    }

    private GdMapListener mapListener;

    public void setOnLocationListener(GdMapListener listener) {
        mapListener = listener;
    }

    public void requestLocation() {
        mLocationManagerProxy.requestLocationData(
                LocationProviderProxy.AMapNetwork, -1, 15, mapListener);
    }
    public void removeUpdates(){
        // 移除定位请求
        mLocationManagerProxy.removeUpdates(mapListener);
        // 销毁定位
        mLocationManagerProxy.destroy();
    }
}
