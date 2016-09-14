package com.zlkj.jianjie.util;



import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.WindowManager;


/**
 * 屏幕显示相关工具�?
 */
public class ScreenUtil
{
    private static float currentDensity = 0;
    private static float scaledDensity = 0;
    private final static int iconSize = 48;
    private final static int notification_height = 25;
    /**
     * 大屏幕的高度
     */
    private final static int LARDGE_SCREEN_HEIGHT = 960;
    /**
     * 大屏幕的宽度
     */
    final static int LARDGE_SCREEN_WIDTH = 720;
    private final static int M9_SCREEN_WIDTH = 640;

    /**
     * 获取屏幕信息，如大小、屏幕密度�?字体缩放�?
     * 
     * @return DisplayMetrics
     */
    public static DisplayMetrics getMetrics()
    {
        // Log.e("getMetrics", "getMetrics");
        DisplayMetrics metrics = new DisplayMetrics();
        Context ctx = Global.getApplicationContext();
        if (ctx == null)
        {
            Log.e("ScreenUtil.getMetrics", "ApplicationContext is null!");
            return metrics;
        }
        final WindowManager windowManager = (WindowManager) ctx
                .getSystemService(Context.WINDOW_SERVICE);
        final Display display = windowManager.getDefaultDisplay();
        display.getMetrics(metrics);
        boolean isPortrait = display.getWidth() < display.getHeight();
        final int width = isPortrait ? display.getWidth() : display.getHeight();
        final int height = isPortrait ? display.getHeight() : display
                .getWidth();
        metrics.widthPixels = width;
        metrics.heightPixels = height;
        return metrics;
    }

    /**
     * 获取屏幕密度
     * 
     * @return float
     */
    public static float getDensity()
    {
        DisplayMetrics metrics = getMetrics();
        return metrics.density;
    }

    /**
     * 获取图标大小
     * 
     * @return int
     */
    public static int getIconSize()
    {
        float density = getDensity();
        return (int) (iconSize * density);
    }

    /**
     * 获取通知栏高�?
     * 
     * @return int
     */
    public static int getNotificationHeight()
    {
        return notification_height;
    }

    /**
     * 获取壁纸高宽
     * 
     * @return int[]
     */
    public static int[] getWallpaperWH()
    {
        int[] wh = getScreenWH();
        return new int[]
        { wh[0] * 2, wh[1] };
    }

    /**
     * 重新计算cellLayout上单元格的高宽，防止因屏幕高度获取不对引发的错误
     * 
     * @return int[]
     */
    public static int[] getScreenWH()
    {
        int[] screenWH =
        { 320, 480 };
        Context ctx = Global.getApplicationContext();
        if (ctx == null)
        {
            Log.e("ScreenUtil.getScreenWH", "ApplicationContext is null!");
            return screenWH;
        }
        final WindowManager windowManager = (WindowManager) ctx
                .getSystemService(Context.WINDOW_SERVICE);
        final Display display = windowManager.getDefaultDisplay();
        boolean isPortrait = display.getWidth() < display.getHeight();
        final int width = isPortrait ? display.getWidth() : display.getHeight();
        final int height = isPortrait ? display.getHeight() : display
                .getWidth();
        screenWH[0] = width;
        screenWH[1] = height;
        return screenWH;
    }

    /**
     * 是否大屏�?
     * 
     * @return boolean
     */
    public static boolean isLargeScreen()
    {
        int w = getScreenWH()[0];
        if (w >= 480)
            return true;
        else
            return false;
    }

    /**
     * 是否是更大的屏幕 高度大于 960 宽度不等�?40
     * 
     * @return boolean
     */
    public static boolean isExLardgeScreen()
    {
        int[] wh = getScreenWH();
        // return wh[0] >= LARDGE_SCREEN_WIDTH && wh[1] >= LARDGE_SCREEN_HEIGHT;
        return wh[1] >= LARDGE_SCREEN_HEIGHT && wh[0] != M9_SCREEN_WIDTH;
    }

    /**
     * 是否高清屏，宽度大于960
     * 
     * @return boolean
     */
    public static boolean isSuperLargeScreen()
    {
        int[] wh = getScreenWH();
        return wh[0] > 960;
    }

    /**
     * 是否小屏�?
     * 
     * @return boolean
     */
    public static boolean isLowScreen()
    {
        int w = getScreenWH()[0];
        if (w < 320)
            return true;
        else
            return false;
    }

    /**
     * 返回屏幕尺寸(�?
     * 
     * @param context
     * @return int
     */
    public static int getCurrentScreenWidth(Context context)
    {
        DisplayMetrics metrics = getDisplayMetrics(context);
        boolean isLand = isOrientationLandscape(context);
        if (isLand)
        {
            return metrics.heightPixels;
        }
        return metrics.widthPixels;
    }

    /**
     * 返回屏幕尺寸(�?
     * 
     * @param context
     * @return int
     */
    public static int getCurrentScreenHeight(Context context)
    {
        DisplayMetrics metrics = getDisplayMetrics(context);
        boolean isLand = isOrientationLandscape(context);
        if (isLand)
        {
            return metrics.widthPixels;
        }
        return metrics.heightPixels;
    }

    /**
     * 返回屏幕尺寸
     * 
     * @param context
     * @return DisplayMetrics
     */
    public static DisplayMetrics getDisplayMetrics(Context context)
    {
        return context.getResources().getDisplayMetrics();
    }

    /**
     * 判断是否横屏
     * 
     * @param context
     * @return boolean
     */
    public static boolean isOrientationLandscape(Context context)
    {
        if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            return true;
        }
        return false;
    }

    /**
     * 获取view的bitmap<br>
     * 在内存溢出的情况下，返回null
     * 
     * @param v
     * @return Bitmap
     */
    public static Bitmap getViewBitmap(View v)
    {
        v.clearFocus();
        v.setPressed(false);
        boolean willNotCache = v.willNotCacheDrawing();
        v.setWillNotCacheDrawing(false);
        // Reset the drawing cache background color to fully transparent
        // for the duration of this operation
        int color = v.getDrawingCacheBackgroundColor();
        v.setDrawingCacheBackgroundColor(0);
        if (color != 0)
        {
            v.destroyDrawingCache();
        }
        v.buildDrawingCache();
        Bitmap cacheBitmap = v.getDrawingCache();
        if (cacheBitmap == null)
        {
            // Log.e(Global.TAG, "failed getViewBitmap(" + v + ")", new
            // RuntimeException());
            return null;
        }
        Bitmap bitmap = null;
        try
        {
            bitmap = Bitmap.createBitmap(cacheBitmap);
        }
        catch (Throwable th)
        {
            th.printStackTrace();
        }
        // Restore the view
        v.destroyDrawingCache();
        v.setWillNotCacheDrawing(willNotCache);
        v.setDrawingCacheBackgroundColor(color);
        return bitmap;
    }

    /**
     * 获取view的cache *
     * 注意:此处仅获取view的缓存，没有create新的bitmap，所以建议不要调用Bitmap.recycle()显式回收
     * 
     * @param v
     * @return Bitmap
     */
    public static Bitmap getViewCache(View v)
    {
        Bitmap cacheBitmap = v.getDrawingCache();
        if (cacheBitmap != null)
            return cacheBitmap;
        v.clearFocus();
        v.setPressed(false);
        v.setWillNotCacheDrawing(false);
        v.setDrawingCacheEnabled(true);
        int color = v.getDrawingCacheBackgroundColor();
        if (color != 0)
        {
            v.destroyDrawingCache();
            v.setDrawingCacheBackgroundColor(0);
        }
        v.buildDrawingCache();
        cacheBitmap = v.getDrawingCache();
        if (cacheBitmap == null)
        {
            // Log.e(Global.TAG, "failed getViewBitmap(" + v + ")", new
            // RuntimeException());
            return null;
        }
        // Log.d(Global.TAG, "rebuild the cache");
        return cacheBitmap;
    }

    /**
     * dp转px
     * 
     * @param context
     * @param dipValue
     * @return int
     */
    public static int dip2px(Context context, float dipValue)
    {
        if (currentDensity > 0)
            return (int) (dipValue * currentDensity + 0.5f);
        currentDensity = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * currentDensity + 0.5f);
    }

    /**
     * sp转px
     * 
     * @param context
     * @param spValue
     * @return int
     */
    public static int sp2px(Context context, float spValue)
    {
        if (scaledDensity > 0)
            return (int) (spValue * scaledDensity + 0.5f);
        scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * scaledDensity + 0.5f);
    }

    /**
     * This methode can be used to calculate the height and set it for views
     * with wrap_content as height. This should be done before
     * ExpandCollapseAnimation is created.
     * 
     * @param context
     * @param view
     */
    public static void setHeightForWrapContent(Context context, View view)
    {
        int screenWidth = getCurrentScreenWidth(context);
        int heightMeasureSpec = MeasureSpec.makeMeasureSpec(0,
                MeasureSpec.UNSPECIFIED);
        int widthMeasureSpec = MeasureSpec.makeMeasureSpec(screenWidth,
                MeasureSpec.EXACTLY);
        view.measure(widthMeasureSpec, heightMeasureSpec);
        int height = view.getMeasuredHeight();
        view.getLayoutParams().height = height;
    }
}
