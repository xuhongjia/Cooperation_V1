package cn.project.aoyolo.cooperation_v1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import cn.project.aoyolo.cooperation_v1.BaseActivity;
import cn.project.aoyolo.cooperation_v1.R;
import cn.project.aoyolo.cooperation_v1.widget.RoundCornerImageView;

/**
 * 我的信息
 * Created by Hy on 2015/11/2.
 */
public class MyInfoActivity extends BaseActivity
{

    private RoundCornerImageView headerView;//头像
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);
        initView();
    }

    private void initView() {
        headerView=(RoundCornerImageView)findViewById(R.id.imageView);
        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyInfoActivity.this,ChoosePhoto.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 返回键
     * @param view
     */
    public void back(View view)
    {
        finish();

    }
}
