package com.zlkj.jianjie.fragment;

import android.content.Context;
import android.widget.TextView;

import com.zlkj.jianjie.R;
import com.zlkj.jianjie.base.BaseFragment;

import butterknife.Bind;

/**
 * Created by gulong on 2016/9/7.
 * 个人中心
 */
public class PersonalFragment extends BaseFragment {

    private Context mContext;


    @Bind(R.id.myactionbar_titile)
    TextView myactionTitle;

    @Override
    protected int getLayoutId() {
        return R.layout.personal_fragment;
    }

    @Override
    protected void init() {
        mContext=getContext();

        myactionTitle.setText("个人中心");

    }

    @Override
    protected void initData() {

    }
}
