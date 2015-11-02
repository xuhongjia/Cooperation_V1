package cn.project.aoyolo.cooperation_v1.widget;

/**
 * Created by yubin on 2015/10/28.
 */
import java.util.List;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class CommonFragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {

    List<Fragment> pages;

    public CommonFragmentPagerAdapter(FragmentManager fm, List<Fragment> pages) {
        super(fm);
        // TODO Auto-generated constructor stub
        this.pages = pages;
    }

    @Override
    public Fragment getItem(int arg0) {
        // TODO Auto-generated method stub
        return pages.get(arg0);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return pages.size();
    }

}