package com.zlkj.jianjie.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zlkj.jianjie.R;
import com.zlkj.jianjie.activity.ApplicanQuestionActivity;
import com.zlkj.jianjie.activity.HomeMainActivity;
import com.zlkj.jianjie.base.BaseFragment;
import com.zlkj.jianjie.myview.MyListView;
import com.zlkj.jianjie.myview.MyPopupListeners;
import com.zlkj.jianjie.myview.MyPopupWindows;
import com.zlkj.jianjie.util.AppUtil;
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

    private List<String> mylistqx=new ArrayList<String>();
    private MyPopupWindows   qxpopupWindows;

    private ListViewAdapter<String> qxAdpater;


    @Bind(R.id.myznpp_details_countrelayout)
    RelativeLayout mylinlayout;

    @Bind(R.id.mytouming_cungt2)//遮层
            ImageView mytoumingcun;

    @Bind(R.id.znpp_details_mylistview)
    MyListView myListview;

    @Bind(R.id.myactionbar_titile)
    TextView myacitontitle;

    @Bind(R.id.znpp_details_qixian_text)//期限
            TextView qixianText;

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

        for (int i = 0; i < 3; i++) {
            myspinnerList.add(i+"个月");
            myList.add(""+i);
        }

    }

    @Override
    protected void initData() {

        setZhppListview();
        setQixinaInfo();

    }


    private void setZhppListview(){

        listViewAdapter=new ListViewAdapter<>(myList, R.layout.znpp_sqqk_list_item, new CallBackData() {
            @Override
            public <T> void setData(XjrViewHolder viewHolder, T data) {

            }
        });

        myListview.setAdapter(listViewAdapter);


    }

    //期限选择点击
    @OnClick(R.id.znpp_details_qxrelayout)
    public void qixianOnclick(){

        mytoumingcun.setVisibility(View.VISIBLE);
        mytoumingcun.bringToFront();
        qxpopupWindows.showAtLocation(mylinlayout, Gravity.BOTTOM,0,0);

    }


    private void setQixinaInfo(){

        mylistqx.add(0,"不限");

        for (int i = 1; i < 5; i++) {
            mylistqx.add(i,i+"个月");
        }

        View qixLisiview=  LayoutInflater.from(mContext).inflate(R.layout.qixian_listview_layout,null);
        final MyListView qixlist= (MyListView) qixLisiview.findViewById(R.id.qixianlayout_listview);


        qxAdpater=new ListViewAdapter<String>(mylistqx, R.layout.qixian_layout, new CallBackData() {
            @Override
            public <T> void setData(XjrViewHolder viewHolder, T data) {

                String st= (String) data;
                TextView mytex1= (TextView) viewHolder.getView(R.id.qixian_layout_text);

                mytex1.setText(st);

            }
        });

        qixlist.setAdapter(qxAdpater);

        qxpopupWindows=new MyPopupWindows(mContext,qixLisiview,myPopupListeners);
        qxpopupWindows.setListenerCancle(R.id.qixan_clolse_popuwinds);
        qxpopupWindows.setListener(R.id.qixian_layout_canceltext);

        qxpopupWindows.setOutsideTouchable(true);// 触摸popupwindow外部，popupwindow消失。这个要求你的popupwindow要有背景图片才可以成功，如上
        qxpopupWindows.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

                mytoumingcun.setVisibility(View.GONE);

            }
        });

        //期限选择
        qixlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name= (String) qixlist.getItemAtPosition(position);
                qixianText.setText(name+"");
                qxpopupWindows.dismiss();
            }
        });

    }

    MyPopupListeners myPopupListeners=new MyPopupListeners() {
        @Override
        public void OnPopItemSelect(View view) {
            switch (view.getId()){
                case R.id.qixian_layout_canceltext:

                    qxpopupWindows.dismiss();
                    break;
            }

        }
    };


    //立即申请
    @OnClick(R.id.znpp_details_lijisq_btn)
    public void ljsqOnClikc(){

        startActivity(new Intent(mContext, ApplicanQuestionActivity.class));

    }


    //利率范围后面图标点击
    @OnClick(R.id.znpp_details_lilvfw_img)
    public void znppLilvOnClick(){

        View myview= LayoutInflater.from(mContext).inflate(R.layout.mydialong_onetext,null);
        TextView mydiane= (TextView) myview.findViewById(R.id.mydialog_onetext1);
        mydiane.setText("以上利率仅供参考，最终费用由借贷平台根\n" +"据您的个人信用等级评估决定");
        final Dialog dialog= AppUtil.newDialong(mContext,myview,R.style.tigerDialog);

        dialog.show();
        myview.findViewById(R.id.mydialog_onetx_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


    }



}
