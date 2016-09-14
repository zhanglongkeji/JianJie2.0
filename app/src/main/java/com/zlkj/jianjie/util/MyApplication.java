package com.zlkj.jianjie.util;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;


public class MyApplication extends Application
{


    private static final String TAG = MyApplication.class.getName();
    //private PushAgent mPushAgent;



    //public static String url="http://apitest.zljianjie.com/?";//测试
    public static String url="http://api.zljianjie.com/?";//正式


    public static String channelid="7";//渠道ID
    public static String productid="3";//产品ID

    public static String MyCountKey="ZHANGLONGjiuji";

    public static boolean isLogin = true;
    public static boolean isLock = false;

    public static Context mContext;

    public static AppCache appCache;



    @Override
    public void onCreate()
    {

       // Global.setApplicationContext(this);
        //Fresco.initialize(instance);

        
        super.onCreate();


        mContext = this;
        appCache = new AppCache(mContext);
        Fresco.initialize(mContext);

    }










}