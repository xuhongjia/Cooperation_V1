package cn.project.aoyolo.cooperation_v1.widget.Common;

/**
 * Created by yubin on 2015/11/5.
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.lidroid.xutils.BitmapUtils;
import java.util.Set;
public class ViewHolder {
    private final SparseArray<View> mViews;
    private BitmapUtils bitmapUtils;
    private int mPosition;
    private View mConvertView;
    private Set<Integer> clickableIds;

    private ViewHolder(Context context, ViewGroup parent, int layoutId,
                       int position, Set<Integer> clickableIds) {
        bitmapUtils = new BitmapUtils(context);
        this.mPosition = position;
        this.mViews = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);
        // setTag
        mConvertView.setTag(this);
        this.clickableIds = clickableIds;
    }

    /**
     * 拿到一个ViewHolder对象
     *
     * @param context
     * @param convertView
     * @param parent
     * @param layoutId
     * @param position
     * @return
     */
    public static ViewHolder get(Context context, View convertView,
                                 ViewGroup parent, int layoutId, int position, Set<Integer> clickableIds) {
        if (convertView == null) {
            return new ViewHolder(context, parent, layoutId, position, clickableIds);
        }

        return (ViewHolder) convertView.getTag();
    }

    public View getConvertView() {
        return mConvertView;
    }

    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    //当设置某id的控件可点击时,加入可点击列表.否则删掉.
    public void setViewClickable(int rId) {
        clickableIds.add(rId);
    }

    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolder setText(int viewId, String text) {
        TextView view = getView(viewId);
        if (view != null) {
            view.setText(text);
        }
        return this;
    }


    public ViewHolder setChecked(int resId, boolean isChecked) {
        CompoundButton view = getView(resId);
        if (view != null) {
            view.setChecked(isChecked);
        }
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param drawableId
     * @return
     */
    public ViewHolder setImageResource(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        if (view != null) {
            view.setImageResource(drawableId);
        }
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param bm
     * @return
     */
    public ViewHolder setImageBitmap(int viewId, Bitmap bm) {
        ImageView view = getView(viewId);
        if (view != null) {
            view.setImageBitmap(bm);
        }
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param url
     * @return
     */
    public ViewHolder setImageByUrl(int viewId, String url) {
        if (url == null || url.length() <= 0) {
            return this;
        }
        //liuzw.crush#699
        bitmapUtils.display(getView(viewId),url);
//        ImageLoader.getInstance(3, Type.LIFO).loadImage(url,
//                (ImageView) getView(viewId));
        return this;
    }

    public int getPosition() {
        return mPosition;
    }

}

