package com.zlkj.jianjie.fragment;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zlkj.jianjie.R;
import com.zlkj.jianjie.activity.HomeMainActivity;
import com.zlkj.jianjie.base.BaseFragment;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by gulong on 2016/9/7.
 * 首页
 */
public class HomepageFragment extends BaseFragment {


    private Context mContext;

    private ZhiNengFragment zhiNengFragment;

    @Bind(R.id.myactionbar_titile)
    TextView myacitontitle;

    @Bind(R.id.myactionbar_back)
    ImageView myimageaction;


    @Override
    protected int getLayoutId() {

        return R.layout.homepage_fragment;

    }


    @Override
    protected void init() {
        mContext=getContext();
        myacitontitle.setText("简借");
        myimageaction.setVisibility(View.GONE);

    }

    @Override
    protected void initData() {

    }

    //智能匹配
    @OnClick(R.id.homepage_znpp)
    public void gotoZnpp(){

        zhiNengFragment=new ZhiNengFragment();

        HomeMainActivity.showFragmetnByFrg(zhiNengFragment);

    }



}
