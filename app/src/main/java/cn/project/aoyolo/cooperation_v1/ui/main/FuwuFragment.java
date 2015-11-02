package cn.project.aoyolo.cooperation_v1.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import cn.project.aoyolo.cooperation_v1.BaseFragment;
import cn.project.aoyolo.cooperation_v1.R;

/**
 * Created by yubin on 2015/10/28.
 */
public class FuwuFragment extends BaseFragment {
    private PopupWindow popupwindow;
    private PopupWindow popupwindow2;
    private Button button;
    private Button button2;
    public View onCreateView(LayoutInflater inflater, ViewGroup root, Bundle b) {
        View view = inflater.inflate(R.layout.fuwu_fragment, null);
        button = (Button)view. findViewById(R.id.spinner_order);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupwindow2 != null && popupwindow2.isShowing()) {
                    popupwindow2.dismiss();
                    popupwindow2 = null;
                }
                if (popupwindow != null&&popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    return;
                } else {
                    initmPopupWindowView();
                    popupwindow.showAsDropDown(v, 0, 5);
                }
            }
        });
        button2=(Button)view. findViewById(R.id.spinner_address);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    popupwindow = null;
                }
                if (popupwindow2 != null&&popupwindow2.isShowing()) {
                    popupwindow2.dismiss();
                    return;
                } else {
                    initmPopupWindowView2();
                    popupwindow2.showAsDropDown(v, 0, 5);
                }
            }
        });
        return view;
    }
    public void initmPopupWindowView() {
        // // 获取自定义布局文件pop.xml的视图
        View customView =getActivity().getLayoutInflater().inflate(R.layout.popupwindow_1,
                null, false);
// 创建PopupWindow实例
        popupwindow = new PopupWindow(customView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,true);
// 设置动画效果 [R.style.AnimationFade 是自己事先定义好的]
        popupwindow.setAnimationStyle(R.style.AnimationFade);
// 自定义view添加触摸事件
        customView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    popupwindow = null;
                }
                return false;
            }
        });
    }
    public void initmPopupWindowView2() {
        // // 获取自定义布局文件pop.xml的视图
        View customView = getActivity().getLayoutInflater().inflate(R.layout.popupwindow_2,
                null, false);
// 创建PopupWindow实例
        popupwindow2 = new PopupWindow(customView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,true);
// 设置动画效果 [R.style.AnimationFade 是自己事先定义好的]
        popupwindow2.setAnimationStyle(R.style.AnimationFade);
// 自定义view添加触摸事件
        customView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popupwindow2 != null && popupwindow2.isShowing()) {
                    popupwindow2.dismiss();
                    popupwindow2 = null;
                }
                return false;
            }
        });
    }
}
