package com.zlkj.jianjie.viewholder;

import java.util.List;

/**
 * @author
 *@version
 */
public class ListViewAdapter<T extends Object> extends BaseViewAdapter<T> {
    private CallBackData mCallBackData;
    
    @SuppressWarnings("unchecked")
	public ListViewAdapter(List showItems,int layoutid,CallBackData callbackData) {
        super(showItems,layoutid);
        this.mCallBackData = callbackData;
    }
    @Override
    public void setShowData(XjrViewHolder viewHolder,T data) {
        mCallBackData.setData(viewHolder, data);
    }
}