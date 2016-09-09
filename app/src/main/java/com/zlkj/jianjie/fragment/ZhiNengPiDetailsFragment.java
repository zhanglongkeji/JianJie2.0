package com.zlkj.jianjie.fragment;

import android.content.Context;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zlkj.jianjie.R;
import com.zlkj.jianjie.activity.HomeMainActivity;
import com.zlkj.jianjie.adapter.MySpinnerAdapter;
import com.zlkj.jianjie.base.BaseFragment;
import com.zlkj.jianjie.myview.MyListView;
import com.zlkj.jianjie.myview.MyPopupWindows;
import com.zlkj.jianjie.myview.XCDropDownListView;
import com.zlkj.jianjie.viewholder.CallBackData;
import com.zlkj.jianjie.viewholder.ListViewAdapter;
import com.zlkj.jianjie.viewholder.XjrViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by gulong on 2016/9/8.
 * 智能匹配详情
 */
public class ZhiNengPiDetailsFragment extends BaseFragment{


    private Context mContext;

    private List<String> myspinnerList=new ArrayList<String>();

    private ArrayList<String> myList=new ArrayList<String>();
    private ListViewAdapter<String> listViewAdapter;

    private MySpinnerAdapter mySpinnerAdapter;

    private MyPopupWindows popupWindows;

    private PopupWindow popupWindow;

    @Bind(R.id.znpp_details_qxrelayout)
    RelativeLayout znppRealyout;

    @Bind(R.id.znpp_details_mylistview)
    MyListView myListview;

    @Bind(R.id.myactionbar_titile)
    TextView myacitontitle;

    @Bind(R.id.znpp_details_qixiantext)
    XCDropDownListView myqixianText;


    @OnClick(R.id.myactionbar_back)
    public void closeFragment(){
        HomeMainActivity.fragmentManager.popBackStack();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.znpp_details;
    }

    @Override
    protected void init() {
        mContext=getContext();
        myacitontitle.setText("智能匹配");

        for (int i = 0; i < 6; i++) {
            myspinnerList.add(i+"个月");
            myList.add(""+i);
        }

    }

    @Override
    protected void initData() {

        setZhppListview();

    }


    private void setZhppListview(){

        listViewAdapter=new ListViewAdapter<>(myList, R.layout.znpp_sqqk_listview, new CallBackData() {
            @Override
            public <T> void setData(XjrViewHolder viewHolder, T data) {

            }
        });

        myListview.setAdapter(listViewAdapter);


        myqixianText.setItemsData(myList);

    }



}
