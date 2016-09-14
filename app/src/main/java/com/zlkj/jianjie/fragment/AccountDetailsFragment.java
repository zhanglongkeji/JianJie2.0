package com.zlkj.jianjie.fragment;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.zlkj.jianjie.R;
import com.zlkj.jianjie.activity.HomeMainActivity;
import com.zlkj.jianjie.base.BaseFragment;
import com.zlkj.jianjie.myview.MyListView;
import com.zlkj.jianjie.util.AppUtil;
import com.zlkj.jianjie.viewholder.CallBackData;
import com.zlkj.jianjie.viewholder.ListViewAdapter;
import com.zlkj.jianjie.viewholder.XjrViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by gulong on 2016/9/9.
 * 账户明细
 */
public class AccountDetailsFragment extends BaseFragment{

    private ListViewAdapter<String> mylistviewAdapter;
    private Context mContext;

    private List<String> mylist=new ArrayList<String>();
    private Dialog dialog;

    private Dialog dialogyqok;

    private Dialog dialogtixian;

    private ListViewAdapter<String> mylistDialogAdapter;


    @Bind(R.id.account_details_mylistinfo)
    MyListView myListinfo;

    @Bind(R.id.myactionbar_titile)
    TextView acitonTitle;

    @Bind(R.id.myactionbar_righttext)
    TextView rightText;


    @OnClick(R.id.myactionbar_back)
    public void myacitonBack(){
        HomeMainActivity.fragmentManager.popBackStack();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.account_details;
    }

    @Override
    protected void init() {
        mContext=getContext();
        acitonTitle.setText("账户明细");
        rightText.setText("提现");
        rightText.setVisibility(View.VISIBLE);

        for (int i = 0; i < 8; i++) {
            mylist.add(""+i);
        }

    }

    @Override
    protected void initData() {

        addMyListInfo();

    }

    //设置listview的数据
    private void addMyListInfo(){

        mylistviewAdapter=new ListViewAdapter<String>(mylist, R.layout.accoun_details_list, new CallBackData() {
            @Override
            public <T> void setData(XjrViewHolder viewHolder, T data) {

            }
        });

        myListinfo.setAdapter(mylistviewAdapter);

        myListviewOnClick();

    }


    //列表点击事件
    private void myListviewOnClick(){

        myListinfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position==1){
                    showMyaccountDialong();
                }else if(position==2){
                    showMyjkokDialong();
                }else if(position==3){
                    showMytixianDialong();
                }


            }
        });

    }

    //琅琊榜 弹出的Dialog
    private void showMyaccountDialong(){

        View myview= LayoutInflater.from(mContext).inflate(R.layout.myaccount_details_dialog,null);
        MyListView mylistview= (MyListView) myview.findViewById(R.id.myaccount_details_dialog_mylistview);

        TextView mytexttiele= (TextView) myview.findViewById(R.id.mydialong_titileinfos);

        TextView mytext= (TextView) myview.findViewById(R.id.myaccount_details_dialong_btnok);

       mylistDialogAdapter=new ListViewAdapter<>(mylist, R.layout.account_details_dailog_lisitem, new CallBackData() {
           @Override
           public <T> void setData(XjrViewHolder viewHolder, T data) {

           }
       });

        mylistview.setAdapter(mylistDialogAdapter);

        dialog= AppUtil.newDialong(mContext,myview,R.style.tigerDialog);
        dialog.show();

        mytext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }


    //邀请借款 或者注册成功成功 弹出的Dialog
    private void showMyjkokDialong(){

        View myview= LayoutInflater.from(mContext).inflate(R.layout.myaccount_details_dialog,null);
        MyListView mylistview2= (MyListView) myview.findViewById(R.id.myaccount_details_dialog_mylistview);

        TextView mytexttiele= (TextView) myview.findViewById(R.id.mydialong_titileinfos);
        TextView mytext= (TextView) myview.findViewById(R.id.myaccount_details_dialong_btnok);

        mytexttiele.setText("邀请借款成功");

        mylistDialogAdapter=new ListViewAdapter<>(mylist, R.layout.account_details_dailog_lisitem3, new CallBackData() {
            @Override
            public <T> void setData(XjrViewHolder viewHolder, T data) {

            }
        });

        mylistview2.setAdapter(mylistDialogAdapter);

        dialogyqok= AppUtil.newDialong(mContext,myview,R.style.tigerDialog);
        dialogyqok.show();

        mytext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    //提现 弹出的Dialog
    private void showMytixianDialong(){

        View myview= LayoutInflater.from(mContext).inflate(R.layout.myaccount_details_dialog,null);
        MyListView mylistview2= (MyListView) myview.findViewById(R.id.myaccount_details_dialog_mylistview);
        TextView mytexttiele= (TextView) myview.findViewById(R.id.mydialong_titileinfos);

        TextView mytext= (TextView) myview.findViewById(R.id.myaccount_details_dialong_btnok);

        mytexttiele.setText("提现");

        mylistDialogAdapter=new ListViewAdapter<>(mylist, R.layout.account_details_dailog_lisitem2, new CallBackData() {
            @Override
            public <T> void setData(XjrViewHolder viewHolder, T data) {

            }
        });

        mylistview2.setAdapter(mylistDialogAdapter);

        dialogyqok= AppUtil.newDialong(mContext,myview,R.style.tigerDialog);
        dialogyqok.show();

        mytext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    //提现
    @OnClick(R.id.myactionbar_righttext)
    public void myactiorightOnClick(){
            TiXianFragment tiXianFragment=new TiXianFragment();
        HomeMainActivity.showFragmetnByFrg(tiXianFragment);
    }



}
