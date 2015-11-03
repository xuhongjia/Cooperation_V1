package cn.project.aoyolo.cooperation_v1.ui.main;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;

import cn.project.aoyolo.cooperation_v1.ActivityManager;
import cn.project.aoyolo.cooperation_v1.BaseFragment;
import cn.project.aoyolo.cooperation_v1.R;
import cn.project.aoyolo.cooperation_v1.ui.my.ChooseCityActivity;

/**
 * Created by yubin on 2015/10/28.
 */
public class FuwuFragment extends BaseFragment implements
        AMapLocationListener ,View.OnClickListener {
    private LocationManagerProxy mLocationManagerProxy;
    private PopupWindow popupwindow;
    private PopupWindow popupwindow2;
    private Button button;
    private Button button2;
    private Button button3;
    public View onCreateView(LayoutInflater inflater, ViewGroup root, Bundle b) {
        View view = inflater.inflate(R.layout.fuwu_fragment, null);
        init();
        initView(view);
        return view;
    }
    public void initmPopupWindowView() {
        // // 获取自定义布局文件pop.xml的视图
        View customView =getActivity().getLayoutInflater().inflate(R.layout.popupwindow_1,
                null, false);
// 创建PopupWindow实例
        popupwindow = new PopupWindow(customView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,true);
// 设置动画效果 [R.style.AnimationFade 是自己事先定义好的]
//        popupwindow.setAnimationStyle(R.style.AnimationFade);
// 自定义view添加触摸事件
        customView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    popupwindow = null;
                }
                return false;
            }
        });
        TextView tv_fuwu_new=(TextView) customView.findViewById(R.id.tv_fuwu_new);
        TextView tv_fuwu_pingjia=(TextView) customView.findViewById(R.id.tv_fuwu_pingjia);
        TextView tv_fuwu_chengjiao=(TextView) customView.findViewById(R.id.tv_fuwu_chengjiao);
        TextView tv_fuwu_jiageup=(TextView) customView.findViewById(R.id.tv_fuwu_jiageup);
        TextView tv_fuwu_jiagedown=(TextView) customView.findViewById(R.id.tv_fuwu_jiagedown);
        tv_fuwu_new.setOnClickListener(this);
        tv_fuwu_pingjia.setOnClickListener(this);
        tv_fuwu_chengjiao.setOnClickListener(this);
        tv_fuwu_jiageup.setOnClickListener(this);
        tv_fuwu_jiagedown.setOnClickListener(this);
    }
    public void initmPopupWindowView2() {
        // // 获取自定义布局文件pop.xml的视图
        View customView = getActivity().getLayoutInflater().inflate(R.layout.popupwindow_2,
                null, false);
// 创建PopupWindow实例
        popupwindow2 = new PopupWindow(customView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,true);
// 设置动画效果 [R.style.AnimationFade 是自己事先定义好的]
//        popupwindow2.setAnimationStyle(R.style.AnimationFade);
// 自定义view添加触摸事件
        customView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popupwindow2 != null && popupwindow2.isShowing()) {
                    popupwindow2.dismiss();
                    popupwindow2 = null;
                }
                return false;
            }
        });
    }
    public void init(){
// 初始化定位，只采用网络定位
        mLocationManagerProxy = LocationManagerProxy.getInstance(getContext());
        mLocationManagerProxy.setGpsEnable(false);
        // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
        // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用removeUpdates()方法来取消定位请求
        // 在定位结束后，在合适的生命周期调用destroy()方法
        // 其中如果间隔时间为-1，则定位只定一次,
        // 在单次定位情况下，定位无论成功与否，都无需调用removeUpdates()方法移除请求，定位sdk内部会移除
        mLocationManagerProxy.requestLocationData(
                LocationProviderProxy.AMapNetwork, 60 * 1000, 15, this);
    }
    public void initView( View view){
        button = (Button)view. findViewById(R.id.spinner_order);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupwindow2 != null && popupwindow2.isShowing()) {
                    popupwindow2.dismiss();
                    popupwindow2 = null;
                }
                if (popupwindow != null&&popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    return;
                } else {
                    initmPopupWindowView();
                    popupwindow.showAsDropDown(v, 0, 5);
                }
            }
        });
        button2=(Button)view. findViewById(R.id.spinner_address);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    popupwindow = null;
                }
                if (popupwindow2 != null && popupwindow2.isShowing()) {
                    popupwindow2.dismiss();
                    return;
                } else {
                    initmPopupWindowView2();
                    popupwindow2.showAsDropDown(v, 0, 5);
                }
            }
        });
        button3=(Button)view. findViewById(R.id.spinner_type);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ChooseCityActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if(amapLocation != null && amapLocation.getAMapException().getErrorCode() == 0){
            //获取位置信息
            Double geoLat = amapLocation.getLatitude();
            Double geoLng = amapLocation.getLongitude();
        }
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
    @Override
    public void onPause() {
        super.onPause();
        // 移除定位请求
        mLocationManagerProxy.removeUpdates(this);
        // 销毁定位
        mLocationManagerProxy.destroy();
    }

    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_fuwu_new:
                button.setText("服务最新");
                popupwindow.dismiss();
                popupwindow = null;
                break;
            case R.id.tv_fuwu_pingjia:
                button.setText("评价数最多");
                popupwindow.dismiss();
                popupwindow = null;
                popupwindow = null;
                break;
            case R.id.tv_fuwu_chengjiao:
                button.setText("成交量优先");
                popupwindow.dismiss();
                popupwindow = null;
                break;
            case R.id.tv_fuwu_jiageup:
                button.setText("价格从低到高");
                popupwindow.dismiss();
                popupwindow = null;
                break;
            case R.id.tv_fuwu_jiagedown:
                button.setText("价格从高到低");
                popupwindow.dismiss();
                popupwindow = null;
                break;
            default:
                break;
        }
    }
}
