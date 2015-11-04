package cn.project.aoyolo.cooperation_v1.ui.xuqiu;

/**
 * Created by aoyolo.com on 2015/11/4.
 */

        import android.content.Context;
        import android.util.AttributeSet;
        import android.widget.ImageView;
        import android.widget.LinearLayout;

        import cn.project.aoyolo.cooperation_v1.R;

/**
 * 图片浏览指示标
 * @author sunday
 *
 */
public class MarkView extends LinearLayout {
    private ImageView[] mImageView;
    private Context context;

    public MarkView(Context context){
        super(context);
        this.context = context;
    }

    public MarkView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public void setMarkCount(int iCount) {
        mImageView = new ImageView[iCount];
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        for (int i = 0; i < iCount; i++) {
            ImageView image = new ImageView(context);
            image.setImageResource(R.mipmap.unselected_dot);
            image.setLayoutParams(p);
            mImageView[i] = image;
            image.setId(i);
            addView(image);
        }
    }

    public void setMark(int position) {
        for (int i = 0; i < mImageView.length; i++) {
            if (i == position) {
                mImageView[i].setImageResource(R.mipmap.select_dot);
            } else {
                mImageView[i].setImageResource(R.mipmap.unselected_dot);
            }
        }
    }

}

