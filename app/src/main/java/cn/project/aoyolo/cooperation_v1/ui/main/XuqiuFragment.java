package cn.project.aoyolo.cooperation_v1.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.amap.api.location.AMapLocation;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import cn.project.aoyolo.cooperation_v1.API.GdMapApi;
import cn.project.aoyolo.cooperation_v1.API.GdMapListener;
import cn.project.aoyolo.cooperation_v1.BaseFragment;
import cn.project.aoyolo.cooperation_v1.R;
import cn.project.aoyolo.cooperation_v1.ui.fuwu.ChooseCityActivity;
import cn.project.aoyolo.cooperation_v1.ui.xuqiu.ActivityDemandGroupbuying;
import cn.project.aoyolo.cooperation_v1.ui.xuqiu.ActivityDemandHousekeeping;
import cn.project.aoyolo.cooperation_v1.ui.xuqiu.ActivityDemandRecruit;
import cn.project.aoyolo.cooperation_v1.ui.xuqiu.ActivityDemandRental;
import cn.project.aoyolo.cooperation_v1.ui.xuqiu.ActivityDemandSalas;
import cn.project.aoyolo.cooperation_v1.ui.xuqiu.ActivityDemandTrain;

/**
 * Created by yubin on 2015/10/28.
 * pangrihui
 */

public class XuqiuFragment extends BaseFragment {
    private GdMapApi GdMapApi;
    private PopupWindow popupwindow;
    private PopupWindow popupwindow2;
    private Button button;
    private Button button2;
    private static String address;
    private boolean firstDingWei=true;
    @ViewInject(R.id.tv_fuwu_mapadress)
    TextView tv_fuwu_mapadress;
    @ViewInject(R.id.edt_fuwu_minprice)
    EditText edt_fuwu_minprice;
    @ViewInject(R.id.edt_fuwu_maxprice)
    EditText edt_fuwu_maxprice;
    @ViewInject(R.id.tv_fuwu_chooseadress)
    TextView tv_fuwu_chooseadress;
    @ViewInject(R.id.tv_fuwu_leibie)
    TextView tv_fuwu_leibie;



    public View onCreateView(LayoutInflater inflater, ViewGroup root, Bundle b) {
        View view = inflater.inflate(R.layout.xuqiu_fragment, null);
        ViewUtils.inject(this, view); //使用Xutils
        initView(view);
        return view;
    }

    public void init(){
        GdMapApi.getInstance(mContext).setOnLocationListener(new GdMapListener() {
            public void onLocationChanged(AMapLocation location) {
                if (location != null && location.getExtras() != null) {
                    String string = locBundleString(location.getExtras());
                    address = string;

                } else {
                    address = "定位失败,点击再试试";
                }
                tv_fuwu_mapadress.setText(address);
               /* tv_fuwu_mapadress.setVisibility(View.INVISIBLE);*/
            }
        });
        GdMapApi.getInstance(mContext).requestLocation();
    }
    public void init_again() {
        tv_fuwu_mapadress.setText("定位中...");
        GdMapApi.requestLocation();
    }
    private String locBundleString( Bundle adressString){
        StringBuffer result = new StringBuffer();
        // 获取位置信息
        Bundle locBundle = adressString;
        // 分割字符串
        if (locBundle != null) {
            String desc = locBundle.getString("desc");
            String[] resultStrArray = desc.split(" ");
            for (int i = 1; i < resultStrArray.length; i++) {
                //不要了省
                result.append(resultStrArray[i]);
                if(i==1){
                    tv_fuwu_chooseadress.setText(resultStrArray[i]);
                }
            }
        }
        return  result.toString();
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
                } else if (popupwindow2 != null && !popupwindow2.isShowing()) {
                    popupwindow2.showAsDropDown(v, 0, 5);
                } else {
                    initmPopupWindowView2();
                    popupwindow2.showAsDropDown(v, 0, 5);
                }
                if (firstDingWei) {
                    tv_fuwu_mapadress.setText("定位中...");
                    //定位
                /*  init();*/
                    firstDingWei=false;
                }

            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();
        //移除和销毁定位
      /*  GdMapApi.removeUpdates();*/
    }

    @Override
    public void onResume() {
        super.onResume();
      /*  //重新定位
        init();*/
    }

    public void onDestroy() {
        super.onDestroy();

    }

    public void initmPopupWindowView() {
        // // 获取自定义布局文件pop.xml的视图
        View customView =getActivity().getLayoutInflater().inflate(R.layout.popupwindow_1,
                null, false);
        ViewUtils.inject(this, customView); //使用Xutils
        // 创建PopupWindow实例
        popupwindow = new PopupWindow(customView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,true);
        popupwindow.setBackgroundDrawable(new BitmapDrawable());
        // 自定义view添加触摸事件
//        customView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (popupwindow != null && popupwindow.isShowing()) {
//                    popupwindow.dismiss();
//                }
//                return false;
//            }
//        });
    }
    public void initmPopupWindowView2() {
        // 获取自定义布局文件pop.xml的视图
        View customView2 = getActivity().getLayoutInflater().inflate(R.layout.popupwindow_2,
                null, false);
        ViewUtils.inject(this, customView2); //使用Xutils
        // 创建PopupWindow实例
        popupwindow2 = new PopupWindow(customView2, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,true);
        popupwindow2.setBackgroundDrawable(new BitmapDrawable());

        // 自定义view添加触摸事件
//        customView2.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (popupwindow2 != null && popupwindow2.isShowing()) {
//                    popupwindow2.dismiss();
//                }
//                return false;
//            }
//        });


    }
   /* public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0 && resultCode == 0) {
            String address_by_choose =data.getStringExtra("address");

        }
    }*/

    @OnClick(value={R.id.btn_xuqiufragement_btn1,R.id.btn_xuqiufragement_btn2,R.id.btn_xuqiufragement_btn3,R.id.btn_xuqiufragement_btn4,R.id.btn_xuqiufragement_btn5,R.id.btn_xuqiufragement_btn6,
            R.id.spinner_order,R.id.spinner_address,R.id.btn_fuwu_dingwei,R.id.tv_fuwu_new,R.id.tv_fuwu_pingjia,R.id.tv_fuwu_chengjiao,R.id.tv_fuwu_jiageup,R.id.tv_fuwu_jiagedown,R.id.tv_fuwu_price100,
            R.id.tv_fuwu_price500up,R.id.tv_fuwu_mapadress,R.id.tv_fuwu_jianzhi,R.id.tv_fuwu_zhaopin,R.id.tv_fuwu_jiaoyi,R.id.tv_fuwu_jiaoyi,R.id.tv_fuwu_jiazheng,R.id.tv_fuwu_peixun,R.id.tv_fuwu_zufang,
            R.id.tv_fuwu_tuangou,R.id.btn_fuwu_choose,R.id.btn_fuwu_reset,R.id.btn_fuwu_ok})
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.btn_xuqiufragement_btn1: {
                Intent intent = new Intent(getActivity(), ActivityDemandRecruit.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                break;
            }


            case R.id.btn_xuqiufragement_btn2: {
                Intent intent = new Intent(getActivity(), ActivityDemandSalas.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                break;
            }


            case R.id.btn_xuqiufragement_btn3: {
                Intent intent = new Intent(getActivity(), ActivityDemandTrain.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                break;
            }


            case R.id.btn_xuqiufragement_btn4: {
                Intent intent = new Intent(getActivity(), ActivityDemandHousekeeping.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                break;
            }


            case R.id.btn_xuqiufragement_btn5: {
                Intent intent = new Intent(getActivity(), ActivityDemandRental.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                break;
            }

            case R.id.btn_xuqiufragement_btn6: {
                Intent intent = new Intent(getActivity(), ActivityDemandGroupbuying.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                break;
            }
            case R.id.spinner_order: {
                if (popupwindow2 != null && popupwindow2.isShowing()) {
                    popupwindow2.dismiss();
                }
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    return;
                } else if (popupwindow != null && !popupwindow.isShowing()) {
                    popupwindow.showAsDropDown(view, 0, 5);
                } else {
                    initmPopupWindowView();
                    popupwindow.showAsDropDown(view, 0, 5);
                }
            }
            case R.id.spinner_address: {
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                }
                if (popupwindow2 != null && popupwindow2.isShowing()) {
                    popupwindow2.dismiss();
                    return;
                } else if (popupwindow2 != null && !popupwindow2.isShowing()) {
                    popupwindow2.showAsDropDown(view, 0, 5);
                } else {
                    initmPopupWindowView2();
                    popupwindow2.showAsDropDown(view, 0, 5);
                }
            }
            case R.id.btn_fuwu_dingwei: {
                init_again();
                break;
            }

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
            case R.id.tv_fuwu_mapadress:
                tv_fuwu_chooseadress.setText("定位中");
                init_again();
                break;
            case R.id.tv_fuwu_jianzhi:
                tv_fuwu_leibie.setText("兼职");
                break;
            case R.id.tv_fuwu_zhaopin:
                tv_fuwu_leibie.setText("企业招聘");
                break;
            case R.id.tv_fuwu_jiaoyi:
                tv_fuwu_leibie.setText("物品交易");
                break;
            case R.id.tv_fuwu_jiazheng:
                tv_fuwu_leibie.setText("家政");
                break;

            case R.id.tv_fuwu_peixun:
                tv_fuwu_leibie.setText("培训");
                break;
            case R.id.tv_fuwu_zufang:
                tv_fuwu_leibie.setText("租房");
                break;
            case R.id.tv_fuwu_tuangou:
                tv_fuwu_leibie.setText("团购");
                break;
            case R.id.btn_fuwu_choose:
                Intent intent = new Intent(mContext, ChooseCityActivity.class);
                startActivityForResult(intent,2);
                break;
            case R.id.btn_fuwu_reset:
                edt_fuwu_minprice.setText("");
                edt_fuwu_maxprice.setText("");
                tv_fuwu_chooseadress.setText("深圳市宝安区");
                tv_fuwu_leibie.setText("兼职");
                break;
            case R.id.btn_fuwu_ok:
                popupwindow2.dismiss();
                break;
            default:
                break;
        }

    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 2) {
            if(resultCode == 2){
                String address_by_choose =data.getStringExtra("address");
                tv_fuwu_chooseadress.setText(address_by_choose);}
        }
    }
}

