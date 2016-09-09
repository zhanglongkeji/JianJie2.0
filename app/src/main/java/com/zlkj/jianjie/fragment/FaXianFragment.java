package com.zlkj.jianjie.fragment;

import android.content.Context;
import android.widget.TextView;

import com.zlkj.jianjie.R;
import com.zlkj.jianjie.base.BaseFragment;

import butterknife.Bind;

/**
 * Created by gulong on 2016/9/7.
 * 发现功能
 */
public class FaXianFragment extends BaseFragment {

    private Context mContext;

    @Bind(R.id.myactionbar_titile)
    TextView myactionTitle;

    @Override
    protected int getLayoutId() {
        return R.layout.faxian_fragment;
    }

    @Override
    protected void init() {
        mContext=getContext();
        myactionTitle.setText("发现");

    }

    @Override
    protected void initData() {

    }
}
