package cn.project.aoyolo.cooperation_v1.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import cn.project.aoyolo.cooperation_v1.R;
import cn.project.aoyolo.cooperation_v1.utils.ImageLoader;

/**
 * Created by Myy on 2015/11/2.
 */
public class ChoosePhotoAdapter extends CommonAdapter<String>
{
    public ChoosePhotoAdapter(Context c, int layoutId, List<String> list) {
        super(c, layoutId, list);
    }

    @Override
    public View setItem(View convertView, int position, ViewHolder viewHolder) {
        ImageView view=(ImageView)viewHolder.getView(R.id.myinfo_imageView);
        ImageLoader.getInstance().LoadImage(view,(String)getItem(position));
        return convertView;
    }
}
