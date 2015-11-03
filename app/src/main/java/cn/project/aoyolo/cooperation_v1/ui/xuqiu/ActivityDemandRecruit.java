package cn.project.aoyolo.cooperation_v1.ui.xuqiu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import cn.project.aoyolo.cooperation_v1.MainActivity;
import cn.project.aoyolo.cooperation_v1.R;

/**
 * Created by pangrihui on 2015/11/2.
 */
public class ActivityDemandRecruit extends AppCompatActivity {
    private boolean biaozhi=true;  //文本未展开标识设置为true,点击按钮展开后改为false
    private int NOMAL_VALUE=3; //详情文本框默认显示3行
    private int MAX_VALUE=6; //详情文本框最大显示6行
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_demand_recruit);
        ViewUtils.inject(this); //使用Xutils
    }

    @ViewInject(value=R.id.btn_demand_recruit_btn)
    Button button;
    @ViewInject(value=R.id.tv_demand_recruit_require)
    TextView textView;
    @ViewInject(value=R.id.collect)
    ImageView imageView;
    @OnClick(value ={R.id.btn_demand_recruit_btn,R.id.back,R.id.collect,R.id.btn_demand_accept})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_demand_recruit_btn:{
               //详情文本框文字多的话可以点击按钮显示更多内容，最大显示6行，默认3行
                if (biaozhi) {
                    textView.setMaxLines(MAX_VALUE);
                    textView.requestLayout();
                    button.setText("收回");
                    biaozhi = false;
                } else {
                    textView.setMaxLines(NOMAL_VALUE);
                    textView.requestLayout();
                    button.setText("更多");
                    biaozhi = true;
                }
            }
            case R.id.back:{
                //返回主界面
                Intent intent=new Intent(this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                finish();
                break;
            }
            case R.id.collect:{
                //收藏
                imageView.setImageResource(R.mipmap.collect_allready);
                break;
            }
            case R.id.btn_demand_accept:{
                // 用户点击接单按钮处理

                break;
            }
        }
    }
}
