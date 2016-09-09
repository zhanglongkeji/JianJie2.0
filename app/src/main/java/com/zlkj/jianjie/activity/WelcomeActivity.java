package com.zlkj.jianjie.activity;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zlkj.jianjie.R;
import com.zlkj.jianjie.base.BaseActivity;
import com.zlkj.jianjie.util.MyApplication;
import com.zlkj.jianjie.util.StringUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 启动页
 */
public class WelcomeActivity extends BaseActivity {


    private Context mContext;


    private  TimeCount timcount;

    @Bind(R.id.location_centel)
    TextView btn_Nextgo;

    @Bind(R.id.welcome_linearLayout)
    RelativeLayout weRelayout;

    @Bind(R.id.textview_dialog)
    TextView textDialogVal;


    //跳过
    @OnClick(R.id.location_centel_layout)
    public void btnOnClick(){

        timcount.cancel();
        Intent intent=new Intent(mContext,HomeMainActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void init() {

        mContext=this;

    }

    @Override
    protected void initData() {

        if(StringUtils.isNetworkAvailable(mContext)){

            timcount=new TimeCount(4000, 1000);
            timcount.start();

        }else{
            Toast.makeText(mContext, "网络异常", Toast.LENGTH_SHORT);
        }

    }



    //定义一个倒计时的内部类
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }
        @Override
        public void onFinish() {

        }
        @Override
        public void onTick(long millisUntilFinished){
            //计时过程显示
            if((millisUntilFinished /1000)<=1){
                textDialogVal.setText(millisUntilFinished /1000+"");

                NextActityGo();

            }else{
                textDialogVal.setText(millisUntilFinished /1000+"");
            }

        }
    }


    //判断是否首次登陆
    private void NextActityGo(){
        if (MyApplication.appCache.isFirstStartApp()) { //是第一次启动

            try
            {
                startActivity(new Intent(mContext,HomeMainActivity.class));
                MyApplication.appCache.setFirstStartApp(false);
                finish();

            }
            catch (Exception e)
            {

                e.printStackTrace();
            }


        } else {

            try {
                startActivity(new Intent(mContext,HomeMainActivity.class));

                finish();

            } catch (Exception e) {

                e.printStackTrace();
            }

        }

    }


}
