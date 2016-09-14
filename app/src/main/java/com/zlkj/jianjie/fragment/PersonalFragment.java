package com.zlkj.jianjie.fragment;

import android.content.Context;
import android.content.Intent;

import com.zlkj.jianjie.R;
import com.zlkj.jianjie.activity.HomeMainActivity;
import com.zlkj.jianjie.activity.LoginActivity;
import com.zlkj.jianjie.base.BaseFragment;

import butterknife.OnClick;

/**
 * Created by gulong on 2016/9/7.
 * 个人中心
 */
public class PersonalFragment extends BaseFragment {

    private Context mContext;


    @Override
    protected int getLayoutId() {
        return R.layout.personal_fragment;
    }

    @Override
    protected void init() {
        mContext=getContext();



    }

    @Override
    protected void initData() {

    }

    //账户明细
    @OnClick(R.id.personal_account_money)
    public void accountMoneyOnClick(){
        AccountDetailsFragment accountDetailsFragment=new AccountDetailsFragment();
        HomeMainActivity.showFragmetnByFrg(accountDetailsFragment);

    }


    @OnClick(R.id.personal_fragment_toprelayout)
    public void mytopImgOnClick(){
            startActivity(new Intent(mContext, LoginActivity.class));
    }



}
