package cn.project.aoyolo.cooperation_v1.ui.xuqiu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import cn.project.aoyolo.cooperation_v1.R;

public class ActivityDemandGroupbuying extends AppCompatActivity {
  /*  private ViewFlipper flipper;
    private int[] resId = {R.mipmap.demand_sales_1,R.mipmap.demand_sales_2,R.mipmap.demand_sales_3,R.mipmap.demand_sales_4};*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_demand_groupbuying);
     /*   flipper = (ViewFlipper) findViewById(R.id.vfliper_demand_groupbuying_fliper);

        *//*
        * 动态导入的方式为ViewFlipper加入子View
        * *//*
        for (int i = 0; i < resId.length; i++) {
            flipper.addView(getImageView(resId[i]));

        }
        *//*
        * 为ViewFlipper去添加动画效果
        * *//*
        flipper.setInAnimation(this, R.anim.inputodown);
        flipper.setOutAnimation(this, R.anim.outdowntoup);
        flipper.setFlipInterval(5000);
        flipper.startFlipping();*/
    }
   /* private ImageView getImageView(int resId){
        ImageView image = new ImageView(this);
        image.setBackgroundResource(resId);
        return image;
    }*/
}
