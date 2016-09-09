package com.zlkj.jianjie.util;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by gulong on 2016/8/25.
 */
public class AppUtil {


/*
    //弹出的Dialog对话框
    public static CustomProgressDialog showProgress(final Context context,String hintText) {

        CustomProgressDialog window = new CustomProgressDialog(context, hintText);
        // window.getWindow().setGravity(Gravity.CENTER);
        window.show();

        // 默认可取消的模式，并在取消之时�?知用户，线程仍在后台加载�?
        window.setCancelable(false);
        window.setOnCancelListener(new DialogInterface.OnCancelListener() {

            @Override
            public void onCancel(DialogInterface arg0) {
                AppUtil.showShort(context, context.getString(R.string.auto_transferred));
            }
        });
        return window;
    }*/








    //根据数据排序生成字符串
   public static String  mapNexttoStringarr(TreeMap<String,String> myhash){

        String  myCount="";

       Iterator titer=myhash.entrySet().iterator();

       while(titer.hasNext()){
           Map.Entry ent=(Map.Entry )titer.next();
           String keyt=ent.getKey().toString();
           String valuet=ent.getValue().toString();
           myCount+=(keyt+valuet);
       }

       return myCount;
    }



    //首字母排序
    public static  String[] getMapkeyByval(String strs[]){

       // String mys[]=new String[strs.length];

        for (int i = 0; i < strs.length-1; i++) {
            for (int j = i + 1; j < strs.length; j++) {
                int intTemp = strs[i].compareToIgnoreCase(strs[j]);
                String strTemp = "";
                if (intTemp > 0) {
                    strTemp = strs[j];
                    strs[j] = strs[i];
                    strs[i] = strTemp;
                }
            }

        }

        return strs;

    }



    public static boolean isShow = true;

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, CharSequence message)
    {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, int message)
    {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }


    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, CharSequence message)
    {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, int message)
    {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }


    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param message
     * @param duration
     */
    public static void show(Context context, CharSequence message, int duration)
    {
        if (isShow)
            Toast.makeText(context, message, duration).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param message
     * @param duration
     */
    public static void show(Context context, int message, int duration)
    {
        if (isShow)
            Toast.makeText(context, message, duration).show();
    }


    //创建一个Dialog
    public static Dialog newDialong(View relayoutview, Context context, int syle){
        Dialog dialog=new Dialog(context,syle);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.setContentView(relayoutview);

        return  dialog;
    }

    //获取view
    public static View createMyView(Context context,int rayount){
        View svc= LayoutInflater.from(context).inflate(rayount,null,false);


        return svc;
    }
    //获取view id
    public static View getMyViewById(View vcw,int rayount){

        return  vcw.findViewById(rayount);
    }

}
