package com.zlkj.jianjie.fragment;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zlkj.jianjie.R;
import com.zlkj.jianjie.base.BaseFragment;
import com.zlkj.jianjie.myview.MyListView;
import com.zlkj.jianjie.myview.PullToRefreshView;
import com.zlkj.jianjie.viewholder.CallBackData;
import com.zlkj.jianjie.viewholder.ListViewAdapter;
import com.zlkj.jianjie.viewholder.XjrViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by gulong on 2016/9/7.
 * 信用卡
 */
public class XinYongkaFragment extends BaseFragment {

    private Context mContext;

    private ListViewAdapter<String> mylistAdapter;

    private List<String> myList=new ArrayList<String>();

    @Bind(R.id.myactionbar_back)
    ImageView myimage;

    @Bind(R.id.myactionbar_titile)
    TextView myactionTitle;

    @Bind(R.id.xinyongka_mylistview)
    MyListView xykMyList;

    @Bind(R.id.xyk_pulltorefreshview)
    PullToRefreshView mypulltorefreshview;

    @Override
    protected int getLayoutId() {
        return R.layout.xinyongka_fragment;
    }

    @Override
    protected void init() {
        mContext=getContext();
        myactionTitle.setText("信用卡");
        myimage.setVisibility(View.GONE);

        for (int i = 0; i < 5; i++) {
            myList.add(""+i);
        }

    }

    @Override
    protected void initData() {

        setMyListInfo();

    }


    //设置adapter的信息
    private void setMyListInfo(){

        mylistAdapter=new ListViewAdapter<String>(myList, R.layout.xinyongka_listview, new CallBackData() {
            @Override
            public <T> void setData(XjrViewHolder viewHolder, T data) {

            }
        });

        xykMyList.setAdapter(mylistAdapter);

    }


}
