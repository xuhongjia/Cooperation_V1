package cn.project.aoyolo.cooperation_v1.widget;

/**
 * Created by yubin on 2015/11/3.
 */
public interface OnWheelClickedListener {
    /**
     * Callback method to be invoked when current item clicked
     * @param wheel the wheel view
     * @param itemIndex the index of clicked item
     */
    void onItemClicked(WheelView wheel, int itemIndex);
}
