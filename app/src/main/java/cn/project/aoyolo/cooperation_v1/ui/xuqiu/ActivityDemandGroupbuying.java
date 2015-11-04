package cn.project.aoyolo.cooperation_v1.ui.xuqiu;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import cn.project.aoyolo.cooperation_v1.MainActivity;
import cn.project.aoyolo.cooperation_v1.R;

public class ActivityDemandGroupbuying extends AppCompatActivity implements ImageBrowsingViewFlipper.IImageBrowsingMark{
    private Drawable[] imgs ;
    private MarkView markView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_demand_groupbuying);
        ViewUtils.inject(this); //使用Xutils
        init();

    }

    @ViewInject(value = R.id.collect)
    ImageView imageView;

    @OnClick(value = {R.id.back, R.id.collect, R.id.btn_demand_accept})
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

    public void init() {
        ImageBrowsingViewFlipper ibvf = (ImageBrowsingViewFlipper) findViewById(R.id.viewflipper);
        imgs = new Drawable[5];
        imgs[0] =  getResources().getDrawable(R.drawable.img1);
        imgs[1] =  getResources().getDrawable(R.drawable.img2);
        imgs[2] =  getResources().getDrawable(R.drawable.img3);
        imgs[3] =  getResources().getDrawable(R.drawable.img4);
        imgs[4] =  getResources().getDrawable(R.drawable.img5);
        //设置图片浏览指示标接口
        ibvf.setmImgBrowsingMark(this);
        //设置图片
        ibvf.setImgsDraw(imgs);
        markView = (MarkView) findViewById(R.id.markView);
        markView.setMarkCount(imgs.length);
        //起始位置设置为0
        markView.setMark(0);

        // 向左滑动左侧进入的渐变效果（alpha 0.1  -> 1.0）
        Animation lInAnim = AnimationUtils.loadAnimation(this, R.anim.lunbotu_left_in);
        // 向左滑动右侧滑出的渐变效果（alpha 1.0  -> 0.1）
        Animation lOutAnim = AnimationUtils.loadAnimation(this, R.anim.lunbotu_left_out);

        ibvf.setInAnimation(lInAnim);
        ibvf.setOutAnimation(lOutAnim);
        // 设置自动播放功能
        ibvf.setAutoStart(true);
        if (ibvf.isAutoStart() && !ibvf.isFlipping()) {
            ibvf.startFlipping();
        }
    }

    @Override
    public MarkView getMarkView() {
        return markView;
    }

}