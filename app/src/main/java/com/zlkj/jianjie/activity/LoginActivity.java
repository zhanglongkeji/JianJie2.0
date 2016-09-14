package com.zlkj.jianjie.activity;

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;

import com.zlkj.jianjie.R;
import com.zlkj.jianjie.base.BaseActivity;
import com.zlkj.jianjie.myview.CountDownButton;
import com.zlkj.jianjie.util.AppUtil;
import com.zlkj.jianjie.util.StringUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by gulong on 2016/9/14.
 * 登录
 */
public class LoginActivity extends BaseActivity {


    private Context mContext;

    @Bind(R.id.login_btncode)
    TextView loginCodeText;

    @Bind(R.id.login_tel)  //手机号
    EditText loginTelEdit;
    @Bind(R.id.login_code) //验证码
    EditText loginCodeEdit;

    @Bind(R.id.myactionbar_titile)
    TextView myactionTitle;

    @OnClick(R.id.myactionbar_back)
    public void  closemyAcy(){
        finish();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.login_activity;
    }

    @Override
    protected void init() {
        mContext=this;
        myactionTitle.setText("登录");


    }

    @Override
    protected void initData() {

    }


    //点击获取验证码
    @OnClick(R.id.login_btncode)
    public void btncodeOnClick(){

        getVerificationCode();

    }

    // 获取验证码后的倒计时
    private void getVerificationCode()
    {

        CountDownButton downButton = new CountDownButton();
        downButton.init(mContext, loginCodeText,R.drawable.yuanjiao_img_retore_gray,R.drawable.yuanjiao_img_retore_blue);
        downButton.start();

    }


    //登录点击
    @OnClick(R.id.btn_login)
    public void loginBtnokOnClick(){
        String mytel=loginTelEdit.getText().toString().trim();
        String mycode=loginCodeEdit.getText().toString().trim();

        if(StringUtils.isStringEmpty(mytel)){
            AppUtil.showShort(mContext,"请输入手机号");
            return;
        }

        if(StringUtils.isStringEmpty(mycode)){
            AppUtil.showShort(mContext,"请输入验证码");
            return;
        }

        if(!StringUtils.checkPhoneNum(mytel)){
            AppUtil.showShort(mContext,"手机号格式不正确");
            return;
        }


        AppUtil.showShort(mContext,"登录成功");


    }


}
