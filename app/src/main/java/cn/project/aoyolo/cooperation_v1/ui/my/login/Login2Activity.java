package cn.project.aoyolo.cooperation_v1.ui.my.login;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import cn.project.aoyolo.cooperation_v1.R;
import cn.project.aoyolo.cooperation_v1.utils.Blur;

public class Login2Activity extends AppCompatActivity {

    @ViewInject(R.id.backgroup)
    private ImageView backgroup;
    @ViewInject(R.id.app_logo)
    private ImageView app_logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        ViewUtils.inject(this);
        ViewTreeObserver observer =
                app_logo.getViewTreeObserver();
        if (observer != null) {
            observer.addOnGlobalLayoutListener(
                    mLayoutListener);
        }
    }
    private ViewTreeObserver.OnPreDrawListener mPreDrawListener =
            new ViewTreeObserver.OnPreDrawListener() {

                @Override
                public boolean onPreDraw() {
                    ViewTreeObserver observer =  app_logo.getViewTreeObserver();
                    if(observer != null) {
                        observer.removeOnPreDrawListener(this);
                    }
                    Drawable drawable = backgroup.getDrawable();
                    if (drawable != null &&
                            drawable instanceof BitmapDrawable) {
                        Bitmap bitmap =
                                ((BitmapDrawable) drawable).getBitmap();
                        if (bitmap != null) {
                            blur(bitmap, app_logo,25);
                        }
                    }
                    return true;
                }
            };

    private ViewTreeObserver.OnGlobalLayoutListener mLayoutListener =
            new ViewTreeObserver.OnGlobalLayoutListener() {

                @Override
                public void onGlobalLayout() {
                    ViewTreeObserver observer =   app_logo.getViewTreeObserver();
                    if(observer != null) {
                        observer.addOnPreDrawListener(
                                mPreDrawListener);
                    }
                }
            };
    @Override
    protected void onResume() {
        super.onResume();
    }

    private void blur(Bitmap bkg, View view, int radius) {
//        Bitmap overlay = Bitmap.createBitmap(
//                view.getMeasuredWidth(),
//                view.getMeasuredHeight(),
//                Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(overlay);
//        Paint paint = new Paint();
//        paint.setFlags(Paint.FILTER_BITMAP_FLAG);
//        canvas.drawBitmap(bkg, -view.getLeft(),
//                -view.getTop(), paint);
//        RenderScript rs = RenderScript.create(this);
//        Allocation overlayAlloc = Allocation.createFromBitmap(
//                rs, overlay);
//        ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(
//                rs, overlayAlloc.getElement());
//        blur.setInput(overlayAlloc);
//        blur.setRadius(radius);
//        blur.forEach(overlayAlloc);
//        overlayAlloc.copyTo(overlay);
        Bitmap overlay = Blur.fastblur(this,bkg,radius);
        view.setBackground(new BitmapDrawable(
                getResources(), overlay));
    }
}
