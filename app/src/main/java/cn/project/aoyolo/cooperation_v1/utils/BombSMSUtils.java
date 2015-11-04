package cn.project.aoyolo.cooperation_v1.utils;

import android.content.Context;

import cn.bmob.sms.BmobSMS;
import cn.bmob.sms.listener.RequestSMSCodeListener;
import cn.bmob.sms.listener.VerifySMSCodeListener;

/**
 * Created by Myy on 2015/11/4.
 */
public class BombSMSUtils
{
    private static Context c=null;
    private static String appKey="733446061e2c37f58d427bec85b38992";

    /**
     * 初始化
     * @param c
     */
    public static void getInstance(Context c)
    {
        BombSMSUtils.c=c;
        BmobSMS.initialize(c,appKey);
    }

    /**
     * 发送注册验证码
     */
    public static void sendRegisterSMS(String phoneNumber,RequestSMSCodeListener callback)
    {

        BmobSMS.requestSMSCode(c, phoneNumber, "一键注册", callback);
    }

    /**
     * 重置密码验证码
     * @param phoneNumber
     * @param callback
     */
    public static  void sendFindPswSMS(String phoneNumber,RequestSMSCodeListener callback)
    {
        BmobSMS.requestSMSCode(c,phoneNumber,"重置密码",callback);
    }

    /**
     * 验证验证码
     * @param phoneNumber
     * @param validNumber
     * @param callback
     */
    public static void verifyCode(String phoneNumber,String validNumber,VerifySMSCodeListener callback)
    {
        BmobSMS.verifySmsCode(c,phoneNumber,validNumber,callback);
    }
}
