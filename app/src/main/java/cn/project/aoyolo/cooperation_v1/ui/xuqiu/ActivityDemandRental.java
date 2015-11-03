package cn.project.aoyolo.cooperation_v1.ui.xuqiu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import cn.project.aoyolo.cooperation_v1.MainActivity;
import cn.project.aoyolo.cooperation_v1.R;

public class ActivityDemandRental extends AppCompatActivity {

    private int[] resId = {R.mipmap.demand_ceshi_1, R.mipmap.demand_ceshi_2, R.mipmap.demand_ceshi_3};
    private float startX=0;  //记录手指触目位置
    private int biaozhi=0; //标识滑动方向
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_demand_rental);
        ViewUtils.inject(this); //使用Xutils
        init();

    }
    @ViewInject(value=R.id.vfliper_demand_sales_fliper)
    ViewFlipper flipper;

    @OnClick(value={R.id.back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.back:{
                //返回主界面
                Intent intent=new Intent(this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                finish();
                break;
            }
        }
    }

    public void init(){
        //设置ViewFlipper
        SetViewFlipper();

    }
    //设置ViewFlipper
    private void SetViewFlipper() {
        //动态导入的方式为ViewFlipper加入子View
        for (int i = 0; i < resId.length; i++) {
            ImageView image = new ImageView(this);
            image.setBackgroundResource(resId[i]);
            flipper.addView(image);
        }

        // 为ViewFlipper去添加动画效果
        flipper.setInAnimation(this, R.anim.push_left_in);
        flipper.setOutAnimation(this, R.anim.push_left_out);
        flipper.setFlipInterval(5000);
        flipper.startFlipping();
    }
    //手势滑动画图
    public boolean onTouchEvent(MotionEvent event) {
// TODO Auto-generated method stub
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                if(biaozhi==0){
                    flipper.stopFlipping();
                }
                break;
            case MotionEvent.ACTION_MOVE://判断向左滑动还是向右滑动
                if (event.getX() - startX > 80) {
                    biaozhi=2;
                } else if (startX - event.getX() > 80) {
                    biaozhi=3;
                }
                break;
            case MotionEvent.ACTION_UP:
                if(biaozhi==2){
                    flipper.setInAnimation(this, R.anim.push_right_in);
                    flipper.setOutAnimation(this, R.anim.push_right_out);
                    flipper.showPrevious();
                }
                else if(biaozhi==3){
                    flipper.setInAnimation(this, R.anim.push_left_in);
                    flipper.setOutAnimation(this, R.anim.push_left_out);
                    flipper.showNext();
                }
                biaozhi=1;    //必须变回1
                /*flipper.startFlipping();*/   //滑动后取消自动换图
                break;
        }
        return super.onTouchEvent(event);
    }
}
