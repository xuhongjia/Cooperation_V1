package cn.project.aoyolo.cooperation_v1;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

/**
 * 用于管理整个app的activity活动栈
 * Created by Hy on 2015/11/2.
 */
public class ActivityManager
{
    private static List<Activity> activityList=new LinkedList<Activity>();

    /**
     * 活动列表管理
     * @param activity
     */
    public static void addActivity(Activity activity)
    {
        activityList.add(activity);
    }

    public static void removeActivity(Activity activity)
    {
        activityList.remove(activity);
    }

    public static Activity getTopActivity()
    {
        return  activityList.get(activityList.size()-1);
    }
}
