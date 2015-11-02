package cn.project.aoyolo.cooperation_v1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * Created by Myy on 2015/11/2.
 */
public abstract class BaseFragment extends Fragment{
    protected BaseActivity mContext = null;

    //视图创建完成
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = (BaseActivity) getActivity();
        onInitView(view);
    }
    public void onInitView(View view){

    }
    //界面重新显示出来
    public void onActive(){

    }
}
