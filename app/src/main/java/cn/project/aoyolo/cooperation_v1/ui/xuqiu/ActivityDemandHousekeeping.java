package cn.project.aoyolo.cooperation_v1.ui.xuqiu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import cn.project.aoyolo.cooperation_v1.MainActivity;
import cn.project.aoyolo.cooperation_v1.R;

public class ActivityDemandHousekeeping extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_demand_housekeeping);
        ViewUtils.inject(this); //使用Xutils
    }

    @ViewInject(value=R.id.collect)
    ImageView imageView;

    @OnClick(value ={R.id.back,R.id.collect,R.id.btn_demand_accept})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.back: {
                //返回主界面
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                finish();
                break;
            }
            case R.id.collect: {
                //收藏
                imageView.setImageResource(R.mipmap.collect_allready);
                break;
            }
            case R.id.btn_demand_accept: {
                // 用户点击接单按钮处理

                break;
            }
        }
    }
}
