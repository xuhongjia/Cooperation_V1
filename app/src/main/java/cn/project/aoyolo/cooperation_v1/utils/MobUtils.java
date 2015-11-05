package cn.project.aoyolo.cooperation_v1.utils;

import android.content.Context;

import cn.project.aoyolo.cooperation_v1.ui.my.login.RegisterActivity;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * 短信验证助手
 * Created by Hy on 2015/11/4.
 */
public class MobUtils {
    private final static String appKey = "be9a1b1d478c";
    private final static String appSecret = "1eb699bf94a7cdb5905e4cbf0b484a39";
    private static EventHandler handler = null;
    private static String country = "86";
    private static final int SENDSMS = 1, VALIDSMSTRUE = 2, VALIDSMSFALSE = 3;

    public static void init(Context c) {

        try {
            SMSSDK.initSDK(c, appKey, appSecret);

            initHandler();
            SMSSDK.registerEventHandler(handler);
        } catch (Exception e) {

        }

    }

    private static void initHandler() {
        handler = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {


                if (result == SMSSDK.RESULT_COMPLETE) {

                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {//提交验证码成功
                        RegisterActivity.handler.sendEmptyMessage(VALIDSMSTRUE);
                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        RegisterActivity.handler.sendEmptyMessage(SENDSMS);
                    } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {//返回支持发送验证码的国家列表


                    }
                } else {
                    ((Throwable) data).printStackTrace();
                    RegisterActivity.handler.sendEmptyMessage(VALIDSMSFALSE);
                }
            }


        };

    }

    /**
     * 撤销监听
     */
    public static void UnRegsiterHandler() {
        SMSSDK.unregisterAllEventHandler();
    }

    /**
     * 获取短信验证码
     *
     * @param phone
     */
    public static void getVerifyCode(String phone) {
        SMSSDK.getVerificationCode(country, phone);
    }

    /**
     * 验证验证码
     *
     * @param phone
     * @param code
     */
    public static void validCode(String phone, String code) {
        SMSSDK.submitVerificationCode(country, phone, code);
    }


}
