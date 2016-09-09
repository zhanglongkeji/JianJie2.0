package com.zlkj.jianjie.viewholder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @version
 */

public abstract class BaseViewAdapter<T extends Object> extends BaseAdapter {
	private List<T> mShowItems = new ArrayList<T>();
	private int mLayoutId;

	public BaseViewAdapter(List<T> showItems, int layoutid) {
		this.mShowItems = showItems;
		this.mLayoutId = layoutid;
	}
	

	@Override
	public int getCount() {
		return mShowItems.size();
	}

	@Override
	public Object getItem(int position) {
		return mShowItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		XjrViewHolder xjrViewHolder = XjrViewHolder.getInstance(convertView,
				parent.getContext(), mLayoutId);
		setShowData(xjrViewHolder, mShowItems.get(position));
		return xjrViewHolder.getRootView();
	}

	public abstract void setShowData(XjrViewHolder viewHolder, T data);
}