package cn.project.aoyolo.cooperation_v1.ui.xuqiu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cn.project.aoyolo.cooperation_v1.R;

/**
 * Created by aoyolo.com on 2015/11/2.
 */
public class ActivityDemandRecruitDetails extends AppCompatActivity {
    private boolean biaozhi=true;  //文本框默认显示3行，未展开标识设置为true,点击按钮展开后改为false
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_demand_recruit);
        final TextView textView=(TextView)findViewById(R.id.tv_demand_recruit_require);
        final Button button =(Button)findViewById(R.id.btn_demand_recruit_btn);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (biaozhi) {
                    textView.setMaxLines(6);
                    textView.requestLayout();
                    button.setText("收回");
                    biaozhi = false;
                } else {
                    textView.setMaxLines(3);
                    textView.requestLayout();
                    button.setText("更多");
                    biaozhi = true;
                }
            }
        });
    }
}
