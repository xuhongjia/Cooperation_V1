package cn.project.aoyolo.cooperation_v1.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Myy on 2015/11/2.
 */
public abstract class CommonAdapter<T> extends BaseAdapter
{
    private Context c=null;
    private int layoutID;
    private List<T> list;
    public CommonAdapter(Context c,int layoutId,List<T> list)
    {
        this.c=c;
        this.layoutID=layoutId;
        this.list=list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public abstract  View setItem(View convertView,int position,ViewHolder viewHolder);
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view=convertView;
        ViewHolder viewHolder=null;
        if(view==null)
        {
            convertView= LayoutInflater.from(c).inflate(layoutID,null);
            view=convertView;
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder=(ViewHolder)view.getTag();
        }


        return setItem(view,position,viewHolder);
    }

    public class  ViewHolder
    {
        private SparseArray<View> list=new SparseArray<View>();
        private View convertView=null;
        public ViewHolder(View convertView)
        {
            this.convertView=convertView;
        }
        public View getView(int resouceId)
        {
            View view =list.get(resouceId);
            if(view==null)
            {
                view=convertView.findViewById(resouceId);
                list.append(resouceId,view);
            }
            return view;
        }

    }
}
