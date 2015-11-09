package cn.project.aoyolo.cooperation_v1.utils;

import android.util.Log;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by pangrihui on 2015/11/8.
 */
public class ShowTimeByCalender {
    Date date=new Date();

    public String getTime(long fabu_milli){
        String timeString;
        long now_milli=date.getTime();
        Calendar  now_c1  =  getCal(fabu_milli);
        Calendar  fabu_c2  =  getCal(now_milli);

        int[]  p1  =  {  now_c1.get(Calendar.YEAR), now_c1.get(Calendar.MONTH), now_c1.get(Calendar.DAY_OF_MONTH)  };
        int[]  p2  =  {  fabu_c2.get(Calendar.YEAR), fabu_c2.get(Calendar.MONTH), fabu_c2.get(Calendar.DAY_OF_MONTH)  };
        int year=p1[0]-p2[0];
        int month=p1[1]-p2[1];
        int day=p1[2]-p2[2];
        int hour=p1[3]-p2[3];
        int min=p1[4]-p2[4];
        if(min<0){
            min=60+min;
            hour--;
        }
        if(hour<0){
            hour=12+hour;
            day--;
        }
        if(day<0){
            day=30+day;
            month--;
        }
        if(month<0){
            month=12+month;
            year--;
        }
        if(year>0){
            timeString=year+"年前";
        }
        else if(month>0){
            timeString=month+"月前";
        }
        else if(day>0){
            timeString=day+"天前";
        }
        else if (hour>0){
            timeString=hour+"时前";
        }
        else if(min>5){
            timeString=min+"分前";
        }
        else {
            timeString="刚刚";
        }
        return  timeString;
    }
    public static  Calendar  getCal(long milli)  {
        Calendar  cal  =  Calendar.getInstance();
        cal.clear();
        cal.setTimeInMillis(milli);
        return  cal;
    }
}
