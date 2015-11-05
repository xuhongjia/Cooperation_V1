package cn.project.aoyolo.cooperation_v1.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


import cn.project.aoyolo.cooperation_v1.ActivityManager;

/**
 * 判断网络状态
 * Created by Hy on 2015/11/5.
 */
public class NetWorkUtils
{
    private static ConnectivityManager manager=(ConnectivityManager) ActivityManager.getTopActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

    public static boolean isNetworkAlive()
    {
        NetworkInfo info=manager.getActiveNetworkInfo();
        return info==null?false:true;
    }

}
