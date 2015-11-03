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
import cn.project.aoyolo.cooperation_v1.ui.my.FenleiActivity;

/**
 * Created by yubin on 2015/10/28.
 */
public class MyFragment extends BaseFragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup root, Bundle b) {
        View view = inflater.inflate(R.layout.my_fragment, null);
        ViewUtils.inject(this,view);
        return view;
    }
    @OnClick({R.id.action_b})
    public void onClick(View view){
        switch (view.getId())
        {
            case R.id.action_b:
                Intent intent = new Intent(mContext, FenleiActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
