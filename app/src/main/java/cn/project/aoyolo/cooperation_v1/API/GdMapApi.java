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
