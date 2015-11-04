package cn.project.aoyolo.cooperation_v1.ui.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.event.OnClick;

import cn.project.aoyolo.cooperation_v1.BaseActivity;
import cn.project.aoyolo.cooperation_v1.R;

public class FenleiActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fenlei);
        ViewUtils.inject(this);
    }
    //按钮事件
    @OnClick({R.id.back,R.id.fenlei_jiazheng,
            R.id.fenlei_peixun,R.id.fenlei_qyzp,R.id.fenlei_tuangou,R.id.fenlei_wpjy,R.id.fenlei_zufang})
    public void onClick(View view){
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        switch (view.getId())
        {
            case R.id.back:
                finish();
                break;
            case R.id.fenlei_jiazheng:
                startActivity(new Intent(this, JzfenleiActivity.class));
                break;
            case R.id.fenlei_peixun:
                startActivity(new Intent(this,PxfenleiActivity.class));
                break;
            case R.id.fenlei_qyzp:
                startActivity(new Intent(this,ZpfenleiActivity.class));
                break;
            case R.id.fenlei_tuangou:
                startActivity(new Intent(this,TgfenleiActivity.class));
                break;
            case R.id.fenlei_wpjy:
                startActivity(new Intent(this,JyfenleiActivity.class));
                break;
            case R.id.fenlei_zufang:
                startActivity(new Intent(this,ZffenleiActivity.class));
                break;
            default:
                break;
        }
    }
}
