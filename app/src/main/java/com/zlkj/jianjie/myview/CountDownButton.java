package com.zlkj.jianjie.myview;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.zlkj.jianjie.R;

/**
 * 倒计时
 */

public class CountDownButton extends CountDownTimer
{
    public static final int TIME_COUNT_FUTURE = 60000;
    public static final int TIME_COUNT_INTERVAL = 1000;

    private Context mContext;
    private TextView mButton;
    private String mOriginalText;

     private int mOriginalBackgroundend;
     private int mTickBackgroundstart;
    // private int mOriginalTextColor;

    public CountDownButton()
    {
        super(TIME_COUNT_FUTURE, TIME_COUNT_INTERVAL);
    }

    public CountDownButton(long millisInFuture, long countDownInterval)
    {
        super(millisInFuture, countDownInterval);
    }

    public void init(Context context, TextView register_btn_sendverificationcode,int startimg,int endimg)
    {
        this.mContext = context;
        this.mButton = (TextView) register_btn_sendverificationcode;
        this.mOriginalText = mButton.getText().toString();
        this.mOriginalBackgroundend =endimg;
        // context.getResources().getDrawable(R.color.unChecked);
         this.mTickBackgroundstart = startimg;
        // this.mOriginalTextColor = mButton.getCurrentTextColor();
    }

    public void setTickDrawable(Drawable tickDrawable)
    {
        // this.mTickBackground = tickDrawable;
    }

    @Override
    public void onFinish()
    {
        if (mContext != null && mButton != null)
        {
            mButton.setText(mOriginalText);
            mButton.setTextColor(mContext.getResources().getColor(
                    R.color.text_blue_color));
             mButton.setBackgroundResource(mOriginalBackgroundend);
            mButton.setClickable(true);
            mButton.setEnabled(true);
        }
    }

    @Override
    public void onTick(long millisUntilFinished)
    {
        if (mContext != null && mButton != null)
        {
            mButton.setClickable(false);
            mButton.setEnabled(false);
            mButton.setBackgroundResource(mTickBackgroundstart);
            mButton.setTextColor(mContext.getResources().getColor(
                    R.color.white));
            mButton.setText(millisUntilFinished / 1000 + "秒后重发");
        }
    }

   /* public void init(MobilePhoneBindingActivity context,
            ImageView phonebinding_btn_sendverificationcode)
    {
        // TODO Auto-generated method stub

    }*/
}