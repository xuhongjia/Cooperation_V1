package cn.project.aoyolo.cooperation_v1.ui.my.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import cn.project.aoyolo.cooperation_v1.R;

public class MyInfoItemActivity extends AppCompatActivity {

    @ViewInject(R.id.my_title)
    private TextView my_title;
    @ViewInject(R.id.my_back)
    private TextView my_back;
    @ViewInject(R.id.my_name)
    private EditText my_name;
    @ViewInject(R.id.my_sex)
    private View my_sex;
    @ViewInject(R.id.my_age)
    private View my_age;
    @ViewInject(R.id.my_address)
    private View my_address;
    @ViewInject(R.id.my_job)
    private View my_job;
    @ViewInject(R.id.my_finish)
    private TextView my_finish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info_item);
        ViewUtils.inject(this);
        switchInitView(getIntent().getIntExtra("flag", 0));
    }

    //点击事件
    @OnClick({R.id.my_back})
    public void onClick(View view)
    {
        switch(view.getId()){
            case R.id.my_back:
                finish();
                break;
            default:
                break;
        };
    }
    /**
     * 1:name,2:sex,3:age,4:address,5:job
     * @param flag
     */
    private void switchInitView(int flag){
        switch (flag)
        {
            case 1:
                chooseView(flag);
                my_title.setText("名称");
                break;
            case 2:
                my_title.setText("性别");
                my_finish.setVisibility(View.GONE);
                chooseView(flag);
                break;
            case 3:
                my_title.setText("年龄");
                chooseView(flag);
                break;
            case 4:
                my_title.setText("地址");
                chooseView(flag);
                break;
            case 5:
                my_title.setText("职业");
                chooseView(flag);
                break;
            default:
                break;
        }
    }
    private void chooseView(int i){
        if(i==1)
        {
            my_finish.setVisibility(View.VISIBLE);
            my_name.setVisibility(View.VISIBLE);
            my_sex.setVisibility(View.GONE);
            my_age.setVisibility(View.GONE);
            my_address.setVisibility(View.GONE);
            my_job.setVisibility(View.GONE);
        }
        else if(i==2)
        {
            my_finish.setVisibility(View.GONE);
            my_name.setVisibility(View.GONE);
            my_sex.setVisibility(View.VISIBLE);
            my_age.setVisibility(View.GONE);
            my_address.setVisibility(View.GONE);
            my_job.setVisibility(View.GONE);
        }
        else if(i==3)
        {
            my_finish.setVisibility(View.VISIBLE);
            my_name.setVisibility(View.GONE);
            my_sex.setVisibility(View.GONE);
            my_age.setVisibility(View.VISIBLE);
            my_address.setVisibility(View.GONE);
            my_job.setVisibility(View.GONE);
        }
        else if(i==4)
        {
            my_finish.setVisibility(View.VISIBLE);
            my_name.setVisibility(View.GONE);
            my_sex.setVisibility(View.GONE);
            my_age.setVisibility(View.GONE);
            my_address.setVisibility(View.VISIBLE);
            my_job.setVisibility(View.GONE);
        }
        else if(i==5)
        {
            my_finish.setVisibility(View.VISIBLE);
            my_name.setVisibility(View.GONE);
            my_sex.setVisibility(View.GONE);
            my_age.setVisibility(View.GONE);
            my_address.setVisibility(View.GONE);
            my_job.setVisibility(View.VISIBLE);
        }
    }
}
