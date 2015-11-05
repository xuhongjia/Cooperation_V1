package cn.project.aoyolo.cooperation_v1.ui.main;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;

import cn.project.aoyolo.cooperation_v1.BaseFragment;
import cn.project.aoyolo.cooperation_v1.R;
import cn.project.aoyolo.cooperation_v1.ui.fuwu.ChooseCityActivity;

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
    private static String address;
    private TextView tvGroup[];
    private TextView tv_fuwu_new;
    private TextView tv_fuwu_pingjia;
    private TextView tv_fuwu_chengjiao;
    private TextView tv_fuwu_jiageup;
    private TextView tv_fuwu_jiagedown;
    private EditText edt_fuwu_minprice;
    private EditText edt_fuwu_maxprice;
    private Button btn_fuwu_dingwei;
    private Button btn_fuwu_ok;
    private Button btn_fuwu_reset;
    private Button btn_fuwu_choose;
    public View onCreateView(LayoutInflater inflater, ViewGroup root, Bundle b) {
        View view = inflater.inflate(R.layout.fuwu_fragment, null);
        init();
        initView(view);
        return view;
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
                LocationProviderProxy.AMapNetwork, -1, 15, this);
    }
    public void initView( View view){
        button = (Button)view. findViewById(R.id.spinner_order);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupwindow2 != null && popupwindow2.isShowing()) {
                    popupwindow2.dismiss();
                }
                if (popupwindow != null&&popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    return;
                } else if(popupwindow != null&&!popupwindow.isShowing()){
                    popupwindow.showAsDropDown(v, 0, 5);
                }
                else {
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
                }
                if (popupwindow2 != null && popupwindow2.isShowing()) {
                    popupwindow2.dismiss();
                    return;
                }
                else if(popupwindow2 != null&&!popupwindow2.isShowing()){
                    popupwindow2.showAsDropDown(v, 0, 5);
                }
                else {
                    initmPopupWindowView2();
                    popupwindow2.showAsDropDown(v, 0, 5);
                }
            }
        });
        button3=(Button)view. findViewById(R.id.spinner_type);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if(amapLocation != null && amapLocation.getAMapException().getErrorCode() == 0){
            //获取位置信息
            address=amapLocation.getCity()+amapLocation.getDistrict();
           if(popupwindow2!=null) {
            tvGroup[4].setText(address);}
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
                break;
            case R.id.tv_fuwu_pingjia:
                button.setText("评价数最多");
                popupwindow.dismiss();
                break;
            case R.id.tv_fuwu_chengjiao:
                button.setText("成交量优先");
                popupwindow.dismiss();
                break;
            case R.id.tv_fuwu_jiageup:
                button.setText("价格从低到高");
                popupwindow.dismiss();
                break;
            case R.id.tv_fuwu_jiagedown:
                button.setText("价格从高到低");
                popupwindow.dismiss();
                break;
            case R.id.tv_fuwu_price100:
                edt_fuwu_minprice.setText("0");
                edt_fuwu_maxprice.setText("100");
                break;
            case R.id.tv_fuwu_price500:
                edt_fuwu_minprice.setText("100");
                edt_fuwu_maxprice.setText("500");
                break;
            case R.id.tv_fuwu_price500up:
                edt_fuwu_minprice.setText("500");
                edt_fuwu_maxprice.setText("");
                break;
            case R.id.btn_fuwu_dingwei:
                tvGroup[4].setText("定位中...");
                init_again();
                break;
            case R.id.tv_fuwu_mapadress:
                tvGroup[3].setText(tvGroup[4].getText());
                init_again();
                break;
            case R.id.tv_fuwu_jianzhi:
               tvGroup[5].setText("兼职");
                break;
            case R.id.tv_fuwu_zhaopin:
                tvGroup[5].setText("企业招聘");
                break;
            case R.id.tv_fuwu_jiaoyi:
                tvGroup[5].setText("物品交易");
                break;
            case R.id.tv_fuwu_jiazheng:
                tvGroup[5].setText("家政");
                break;
            case R.id.tv_fuwu_peixun:
                tvGroup[5].setText("培训");
                break;
            case R.id.tv_fuwu_zufang:
                tvGroup[5].setText("租房");
                break;
            case R.id.tv_fuwu_tuangou:
                tvGroup[5].setText("团购");
                break;
            case R.id.btn_fuwu_choose:
                Intent intent = new Intent(mContext, ChooseCityActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.btn_fuwu_reset:
                edt_fuwu_minprice.setText("");
                edt_fuwu_maxprice.setText("");
                tvGroup[3].setText("深圳市宝安区");
                tvGroup[5].setText("兼职");
                break;
            case R.id.btn_fuwu_ok:
                popupwindow2.dismiss();
                break;
            default:
                break;
        }
    }
    public void init_again(){
        //先清除定位信息
        mLocationManagerProxy.removeUpdates(this);
       // 初始化定位，只采用网络定位
        mLocationManagerProxy = LocationManagerProxy.getInstance(getContext());
        mLocationManagerProxy.setGpsEnable(false);
        // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
        // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用removeUpdates()方法来取消定位请求
        // 在定位结束后，在合适的生命周期调用destroy()方法
        // 其中如果间隔时间为-1，则定位只定一次,
        // 在单次定位情况下，定位无论成功与否，都无需调用removeUpdates()方法移除请求，定位sdk内部会移除
        mLocationManagerProxy.requestLocationData(
                LocationProviderProxy.AMapNetwork, -1, 15, this);
    }
    public void initmPopupWindowView() {
        // // 获取自定义布局文件pop.xml的视图
        View customView =getActivity().getLayoutInflater().inflate(R.layout.popupwindow_1,
                null, false);
        // 创建PopupWindow实例
        popupwindow = new PopupWindow(customView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,true);
        popupwindow.setBackgroundDrawable(new BitmapDrawable());
        tv_fuwu_new=(TextView) customView.findViewById(R.id.tv_fuwu_new);
        tv_fuwu_pingjia=(TextView) customView.findViewById(R.id.tv_fuwu_pingjia);
        tv_fuwu_chengjiao=(TextView) customView.findViewById(R.id.tv_fuwu_chengjiao);
        tv_fuwu_jiageup=(TextView) customView.findViewById(R.id.tv_fuwu_jiageup);
        tv_fuwu_jiagedown=(TextView) customView.findViewById(R.id.tv_fuwu_jiagedown);
        tv_fuwu_new.setOnClickListener(this);
        tv_fuwu_pingjia.setOnClickListener(this);
        tv_fuwu_chengjiao.setOnClickListener(this);
        tv_fuwu_jiageup.setOnClickListener(this);
        tv_fuwu_jiagedown.setOnClickListener(this);
    }
    public void initmPopupWindowView2() {
        // 获取自定义布局文件pop.xml的视图
        View customView2 = getActivity().getLayoutInflater().inflate(R.layout.popupwindow_2,
                null, false);
        // 创建PopupWindow实例
        popupwindow2 = new PopupWindow(customView2, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,true);
        popupwindow2.setBackgroundDrawable(new BitmapDrawable());
        int tvID[] = new int[]{R.id.tv_fuwu_price100,R.id.tv_fuwu_price500,R.id.tv_fuwu_price500up,R.id.tv_fuwu_chooseadress,R.id.tv_fuwu_mapadress,R.id.tv_fuwu_leibie,R.id.tv_fuwu_jianzhi,R.id.tv_fuwu_zhaopin,R.id.tv_fuwu_jiaoyi
                ,R.id.tv_fuwu_jiazheng,R.id.tv_fuwu_peixun,R.id.tv_fuwu_zufang,R.id.tv_fuwu_tuangou};
        tvGroup = new TextView[tvID.length];
        for (int i = 0; i < tvID.length; ++i) {
            tvGroup[i] = (TextView) customView2.findViewById(tvID[i]);
            tvGroup[i].setOnClickListener(this);
        }
        edt_fuwu_minprice=(EditText) customView2.findViewById(R.id.edt_fuwu_minprice);
        edt_fuwu_maxprice=(EditText) customView2.findViewById(R.id.edt_fuwu_maxprice);
        btn_fuwu_dingwei=(Button) customView2.findViewById(R.id.btn_fuwu_dingwei);
        btn_fuwu_ok=(Button) customView2.findViewById(R.id.btn_fuwu_ok);
        btn_fuwu_reset=(Button) customView2.findViewById(R.id.btn_fuwu_reset);
        btn_fuwu_choose=(Button) customView2.findViewById(R.id.btn_fuwu_choose);
        edt_fuwu_minprice.setOnClickListener(this);
        edt_fuwu_maxprice.setOnClickListener(this);
        btn_fuwu_dingwei.setOnClickListener(this);
        btn_fuwu_ok.setOnClickListener(this);
        btn_fuwu_reset.setOnClickListener(this);
        btn_fuwu_choose.setOnClickListener(this);
        if(address==""){
            tvGroup[4].setText("定位失败");}
        else{
            tvGroup[4].setText(address);
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if(resultCode == 1){
            String address_by_choose =data.getStringExtra("address");
            tvGroup[3].setText(address_by_choose);}
        }
    }
}
