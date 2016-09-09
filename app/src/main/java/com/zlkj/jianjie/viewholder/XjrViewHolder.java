package com.zlkj.jianjie.viewholder;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;

/**
 * @author
 *@version
 */
@SuppressWarnings("rawtypes")
public class XjrViewHolder {
	  //我们view的缓存容器
    
    SparseArray mSparseArray = new SparseArray();
    private  static XjrViewHolder mXjrViewHolder;
    private View mViewItem;
    private  Context mContext;
    private XjrViewHolder(View viewItem,Context context,int layoutid) {
        //如果我们当前的view是空的就创建
        if (viewItem == null) {
            mViewItem = View.inflate(context, layoutid, null);
            mViewItem.setTag(this);
        } else {
        this.mViewItem = viewItem;
        }
        this.mContext = context;
        
    }
    //得到一个view
    @SuppressWarnings("unchecked")
    public View getView(int viewid) {
        View view = (View) mSparseArray.get(viewid);
        if (view == null) {
            view = mViewItem.findViewById(viewid);
            mSparseArray.put(viewid,view);
        }
        return view;
    }
    
    //得到一个viewhodler方法
    public static XjrViewHolder getInstance(View viewItem,Context context,int layoutid) {
        if (viewItem == null) {
            viewItem = View.inflate(context, layoutid, null);
            XjrViewHolder  xjrViewHolder = new XjrViewHolder(viewItem, context, layoutid);
           viewItem.setTag(xjrViewHolder);
            mXjrViewHolder = xjrViewHolder;
        } else {
            mXjrViewHolder = (XjrViewHolder) viewItem.getTag();
        }
        return  mXjrViewHolder;
    }
    //拿到我们的缓存的view
    public View getRootView() {
        return  this.mViewItem;
    }
}
