package com.zlkj.jianjie.fragment;

import android.content.Context;
import android.widget.TextView;

import com.zlkj.jianjie.R;
import com.zlkj.jianjie.base.BaseFragment;

import butterknife.Bind;

/**
 * Created by gulong on 2016/9/7.
 * 论坛
 */
public class LunTanFragment extends BaseFragment {

    private Context mContext;

    @Bind(R.id.myactionbar_titile)
    TextView myactionTitle;

    @Override
    protected int getLayoutId() {
        return R.layout.luntan_fragment;
    }

    @Override
    protected void init() {
        mContext=getContext();

        myactionTitle.setText("论坛");

    }

    @Override
    protected void initData() {

    }
}
