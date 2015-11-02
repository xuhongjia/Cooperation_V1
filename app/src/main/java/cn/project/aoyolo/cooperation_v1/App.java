package cn.project.aoyolo.cooperation_v1;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by Myy on 2015/11/2.
 */
public class App extends Application
{
    @Override
    public void onCreate() {
        super.onCreate();
        //bugly初始化
        CrashReport.initCrashReport(this, "900011031", false);

    }
}
