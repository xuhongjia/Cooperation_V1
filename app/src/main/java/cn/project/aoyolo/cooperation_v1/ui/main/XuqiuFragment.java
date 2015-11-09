package cn.project.aoyolo.cooperation_v1.ui.main;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import org.kymjs.kjframe.http.HttpCallBack;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.project.aoyolo.cooperation_v1.API.GdMapApi;
import cn.project.aoyolo.cooperation_v1.API.GdMapListener;
import cn.project.aoyolo.cooperation_v1.API.XuQiuApi;
import cn.project.aoyolo.cooperation_v1.BaseFragment;
import cn.project.aoyolo.cooperation_v1.R;
import cn.project.aoyolo.cooperation_v1.entity.Common;
import cn.project.aoyolo.cooperation_v1.entity.GeneralResponse;
import cn.project.aoyolo.cooperation_v1.entity.QueryEntity;
import cn.project.aoyolo.cooperation_v1.ui.fuwu.ChooseCityActivity;
import cn.project.aoyolo.cooperation_v1.ui.xuqiu.ActivityDemandGroupbuying;
import cn.project.aoyolo.cooperation_v1.ui.xuqiu.ActivityDemandHousekeeping;
import cn.project.aoyolo.cooperation_v1.ui.xuqiu.ActivityDemandRecruit;
import cn.project.aoyolo.cooperation_v1.ui.xuqiu.ActivityDemandRental;
import cn.project.aoyolo.cooperation_v1.ui.xuqiu.ActivityDemandSalas;
import cn.project.aoyolo.cooperation_v1.ui.xuqiu.ActivityDemandTrain;
import cn.project.aoyolo.cooperation_v1.utils.ShowTimeByCalender;
import cn.project.aoyolo.cooperation_v1.widget.Common.CommonAdapter;
import cn.project.aoyolo.cooperation_v1.widget.Common.ViewHolder;

/**
 * Created by yubin on 2015/10/28.
 * pangrihui
 */

public class XuqiuFragment extends BaseFragment {
    private PopupWindow popupwindow;
    private PopupWindow popupwindow2;
    private Button button;
    private Button button2;
    private Gson gson = new Gson();
    private boolean state_dingwei=false;  //true表示Activity重新onResume,重新调用init()定位
    private boolean first_dingwei=false;  //第一次定位
    private final List<Common> common=new ArrayList<Common>();
    private QueryEntity queryEntity=new QueryEntity();
    private CommonAdapter<Common> commonAdapter;
    private SimpleDateFormat format;
    private ShowTimeByCalender showTimeByCalender=new ShowTimeByCalender();
    private int index;   //查询数目索引
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
    @ViewInject(R.id.listview_demand)
    ListView listview_demand;


    public View onCreateView(LayoutInflater inflater, ViewGroup root, Bundle b) {
        View view = inflater.inflate(R.layout.xuqiu_fragment, null);
        ViewUtils.inject(this, view); //使用Xutils
        first_dingwei=true;
        index=20;
        format=new SimpleDateFormat("yyyy年MM月dd日");
        init();
        initView(view);
        return view;
    }

    public void init(){
        Date dt= new Date();
        //数据请求
        queryEntity.setFlag(1);
        queryEntity.setIndex(index);
        queryEntity.setSearchTime(dt.getTime());
        getDemandMainDate();
        //为listview 绑定数据
        commonAdapter=new CommonAdapter<Common>(getContext(),common,R.layout.listview_item_mian) {
            @Override
            public void convert(ViewHolder helper, Common item) {
                helper.setText(R.id.tv_circle_username,item.getName());
                String time= showTimeByCalender.getTime(item.getStartTime());
                helper.setText(R.id.tv_circle_time,time);
                helper.setText(R.id.tv_circle_endtime,format.format(item.getEndTime()));
               /* helper.setText(R.id.tv_circle_content,""); */  //实体无数据
                helper.setText(R.id.tv_circle_price,""+item.getPrice());
               /* helper.setText(R.id.tv_circle_state,""); */  //实体无数据
                helper.setText(R.id.tv_circle_discuss, "" + item.getEvaluationNumber());
               /* helper.setImageByUrl(R.id.img_circle_user,"");*/  //实体无数据

            }
        };
        listview_demand.setAdapter(commonAdapter);
        initListView();
    }
    //跳转订单详情界面
    private void initListView()
    {
        listview_demand.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub

                goToGoodsDetails(index);
            }
        });
    }
    /**
     * 传递商品
     * @param index
     */
    private void goToGoodsDetails(int index)
    {
        int type=common.get(index).getType();
        //0:Goods,1:GroupBuying,2:HomeMalking,3:Recruiting,4:RentHouse,5:Train
        Intent intent;
       switch (type){
           case 0:{
               break;
           }
           case 1:{
               intent=new Intent(getActivity(),ActivityDemandGroupbuying.class);
               startActivity(intent);
               break;
           }
           case 2:{
               intent=new Intent(getActivity(),ActivityDemandHousekeeping.class);
               startActivity(intent);
               break;
           }
           case 3:{
               intent=new Intent(getActivity(),ActivityDemandRecruit.class);
               startActivity(intent);
               break;
           }
           case 4:{
               intent=new Intent(getActivity(),ActivityDemandRental.class);
               startActivity(intent);
               break;
           }
           case 5:{
               intent=new Intent(getActivity(),ActivityDemandTrain.class);
               startActivity(intent);
               break;
           }
       }
     /*   //订单数据
        intent.putExtra("ordergoods", (Serializable)common.get(index));*/
        getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public void dingwei(){
        //定位
        GdMapApi.getInstance(mContext).setOnLocationListener(new GdMapListener() {
            public void onLocationChanged(AMapLocation location) {
                if (location != null && location.getExtras() != null) {
                    String string = locBundleString(location.getExtras());
                    tv_fuwu_mapadress.setText(string);

                } else {
                    tv_fuwu_mapadress.setText("定位失败,点击再试试");
                }
               /* tv_fuwu_mapadress.setVisibility(View.INVISIBLE);*/
            }
        });
        GdMapApi.getInstance(mContext).requestLocation();
    }

    //重定位
    public void agianDingwei() {
        tv_fuwu_mapadress.setText("定位中...");
        if(state_dingwei){
            GdMapApi.getInstance(mContext).requestLocation();
        }
        else {
            //如果定位被移除，就重新调用init()
            dingwei();
            state_dingwei=false;
        }
    }
    //获取定位地址进行地址分割
    private String locBundleString( Bundle adressString){
        StringBuffer result = new StringBuffer();
        // 获取位置信息
        Bundle locBundle = adressString;
        // 分割字符串
        if (locBundle != null) {
            String desc = locBundle.getString("desc");
            String[] resultStrArray = desc.split(" ");
            for (int i = 1; i < resultStrArray.length-2; i++) {
                //阉割了，只留城市和区
                result.append(resultStrArray[i]);
                if(i==1&&first_dingwei){
                    tv_fuwu_chooseadress.setText(resultStrArray[i]);
                    first_dingwei=false;
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
                if (first_dingwei) {
                    tv_fuwu_mapadress.setText("定位中...");
                    //定位
                    dingwei();
                }

            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();
        //移除和销毁定位
        GdMapApi.getInstance(mContext).removeUpdates();
    }

    @Override
    public void onResume() {
        super.onResume();
        //恢复定位
    /*    init();*/
        state_dingwei=true;
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


    }
    public void initmPopupWindowView2() {
        // 获取自定义布局文件pop.xml的视图
        View customView2 = getActivity().getLayoutInflater().inflate(R.layout.popupwindow_2,
                null, false);
        ViewUtils.inject(this, customView2); //使用Xutils
        // 创建PopupWindow实例
        popupwindow2 = new PopupWindow(customView2, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,true);
        popupwindow2.setBackgroundDrawable(new BitmapDrawable());




    }


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
            case R.id.btn_fuwu_dingwei: {              //定位
                agianDingwei();
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
            case R.id.tv_fuwu_mapadress:                 //定位
                tv_fuwu_chooseadress.setText("定位中");
                first_dingwei=true;
                agianDingwei();

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
                startActivityForResult(intent,1);
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
        if (requestCode == 1) {
            if(resultCode == 1){
                String address_by_choose =data.getStringExtra("address");
                tv_fuwu_chooseadress.setText(address_by_choose);}
        }
    }
    public void getDemandMainDate(){
        XuQiuApi.getDemandMain(gson.toJson(queryEntity), new HttpCallBack() {
            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);
                GeneralResponse<List<Common>> response = gson.fromJson(t, new TypeToken<GeneralResponse <List<Common>>>() {
                }.getType());
                if (response.isSuccess()) {
                    common.clear();
                    common.addAll(response.getData());
                    commonAdapter.notifyDataSetChanged();
                    Log.e("success", t);
                } else {
                    Log.e("erroe","不成功");
                }
            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                super.onFailure(errorNo, strMsg);
                Log.e("fail", "失败");
            }
        });

    }
}

