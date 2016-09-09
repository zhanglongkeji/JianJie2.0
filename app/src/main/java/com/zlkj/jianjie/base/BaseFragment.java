package com.zlkj.jianjie.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zlkj.jianjie.util.UiUtils;

import butterknife.ButterKnife;

/**
 * @author
 *@version
 */
public abstract class BaseFragment extends Fragment {

	private Context mContext;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		mContext = getActivity();
		
		View view = UiUtils.getViewFromXml(mContext, getLayoutId());
		
		ButterKnife.bind(this,view);
		init();
		initData();
		return view;
	}

	//获取上下文
	public Context getContext(){
		return mContext;
	}
	
	//布局ID
	protected abstract int getLayoutId();
	
	//初始化
    protected abstract void init();
    
    //初始化数据
    protected abstract void initData();

    
    @Override
    public void onDestroyView() {
    	super.onDestroyView();
    	//解绑
    	ButterKnife.unbind(this);
    }


	@Override
	public void onResume() {
		super.onResume();
		//MobclickAgent.onPageStart("HomeMainActivity");
	}

	@Override
	public void onPause() {
		super.onPause();
		//MobclickAgent.onPageEnd("HomeMainActivity");
	}
}
