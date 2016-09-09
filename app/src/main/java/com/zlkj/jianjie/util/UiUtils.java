package com.zlkj.jianjie.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;

public class UiUtils {

    public static Context getApplicationContext() {
        return MyApplication.mContext;
    }

    public static Resources getResources() {
        return getApplicationContext().getResources();
    }


    public static String[] getStringArray(int id) {
        return getResources().getStringArray(id);
    }


    public static int dip2px(int dip) {
        return (int) (getResources().getDisplayMetrics().density * dip + 0.5f);
    }

    public static int px2dip(int px) {
        float density = getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5f);
    }

  //初始化不含有上下午的布局文件
    public static View getViewFromXml(int layoutId) {
        return View.inflate(getApplicationContext(), layoutId, null);
    }
  //初始化含有上下午的布局文件
    public static View getViewFromXml(Context context,int layoutId) {
        return View.inflate(context, layoutId, null);
    }

    //px转sp
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    //dp转px
    public static int dp2px(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) ((dp * displayMetrics.density) + 0.5);
    }
}
