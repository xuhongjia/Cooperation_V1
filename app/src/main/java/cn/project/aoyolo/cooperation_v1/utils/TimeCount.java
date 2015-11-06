package cn.project.aoyolo.cooperation_v1.utils;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;

/**
 * Created by Myy on 2015/11/5.
 */
public class TimeCount extends CountDownTimer {
    View view1;
    View view2;

    public TimeCount(long millisInFuture, long countDownInterval, View view1,View view2) {
        super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        this.view1 = view1;
        this.view2 = view2;
    }

    @Override
    public void onFinish() {//计时完毕时触发
        ((Button) view1).setText("重新验证");
        view1.setEnabled(true);
        view2.setEnabled(false);
    }

    @Override
    public void onTick(long millisUntilFinished) {//计时过程显示
        view1.setEnabled(false);
        ((Button) view1).setText(millisUntilFinished / 1000 + "秒");
    }
}
