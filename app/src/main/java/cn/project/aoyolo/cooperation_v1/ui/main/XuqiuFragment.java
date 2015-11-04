package cn.project.aoyolo.cooperation_v1.ui.main;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import com.lidroid.xutils.ViewUtils;
        import com.lidroid.xutils.view.annotation.event.OnClick;
        import cn.project.aoyolo.cooperation_v1.BaseFragment;
        import cn.project.aoyolo.cooperation_v1.R;
        import cn.project.aoyolo.cooperation_v1.ui.xuqiu.ActivityDemandGroupbuying;
        import cn.project.aoyolo.cooperation_v1.ui.xuqiu.ActivityDemandHousekeeping;
        import cn.project.aoyolo.cooperation_v1.ui.xuqiu.ActivityDemandRecruit;
        import cn.project.aoyolo.cooperation_v1.ui.xuqiu.ActivityDemandRental;
        import cn.project.aoyolo.cooperation_v1.ui.xuqiu.ActivityDemandSalas;
        import cn.project.aoyolo.cooperation_v1.ui.xuqiu.ActivityDemandTrain;

/**
 * Created by yubin on 2015/10/28.
 */
public class XuqiuFragment extends BaseFragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup root, Bundle b) {
        View view = inflater.inflate(R.layout.xuqiu_fragment, null);
        ViewUtils.inject(this, view); //使用Xutils
        return view;
    }

    @OnClick(value={R.id.btn_xuqiufragement_btn1,R.id.btn_xuqiufragement_btn2,R.id.btn_xuqiufragement_btn3,R.id.btn_xuqiufragement_btn4,R.id.btn_xuqiufragement_btn5,R.id.btn_xuqiufragement_btn6})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_xuqiufragement_btn1:{
                Intent intent=new Intent(getActivity(), ActivityDemandRecruit.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                break;
            }
        }
        switch (view.getId()){
            case R.id.btn_xuqiufragement_btn2:{
                Intent intent=new Intent(getActivity(), ActivityDemandSalas.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                break;
            }
        }
        switch (view.getId()){
            case R.id.btn_xuqiufragement_btn3:{
                Intent intent=new Intent(getActivity(), ActivityDemandTrain.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                break;
            }
        }
        switch (view.getId()){
            case R.id.btn_xuqiufragement_btn4:{
                Intent intent=new Intent(getActivity(), ActivityDemandHousekeeping.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                break;
            }
        }
        switch (view.getId()){
            case R.id.btn_xuqiufragement_btn5:{
                Intent intent=new Intent(getActivity(), ActivityDemandRental.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                break;
            }
        }
        switch (view.getId()){
            case R.id.btn_xuqiufragement_btn6:{
                Intent intent=new Intent(getActivity(), ActivityDemandGroupbuying.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                break;
            }
        }
    }
}
