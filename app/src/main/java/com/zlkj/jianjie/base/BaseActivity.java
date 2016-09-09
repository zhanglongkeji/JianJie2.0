package com.zlkj.jianjie.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.widget.Toast;

import butterknife.ButterKnife;

/**
 * Created by gulong on 2016/8/23.
 */
public abstract class BaseActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        init();
        initData();
    }


    //布局文件id
    protected abstract int getLayoutId();

    //初始化
    protected abstract void init();

    //初始化数据
    protected abstract void initData();


    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        //解绑
        ButterKnife.unbind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
      //  MobclickAgent.onResume(this);       //统计时长

    }

    @Override
    protected void onPause() {
        super.onPause();
       // MobclickAgent.onPause(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }


    private long exitTime = 0;


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime) > 2000){
                Toast.makeText(getApplicationContext(), "再按一次退出应用", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }


        return super.onKeyDown(keyCode, event);
    }
}
