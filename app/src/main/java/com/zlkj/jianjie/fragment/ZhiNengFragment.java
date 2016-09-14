package com.zlkj.jianjie.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zlkj.jianjie.R;
import com.zlkj.jianjie.activity.AddMyInfoActivity;
import com.zlkj.jianjie.activity.HomeMainActivity;
import com.zlkj.jianjie.adapter.ConstellationAdapter;
import com.zlkj.jianjie.adapter.GirdDropDownAdapter;
import com.zlkj.jianjie.adapter.TagAdapter;
import com.zlkj.jianjie.base.BaseFragment;
import com.zlkj.jianjie.flowlayout.FlowTagLayout;
import com.zlkj.jianjie.flowlayout.OnTagSelectListener;
import com.zlkj.jianjie.myview.MyListView;
import com.zlkj.jianjie.myview.MyPopupListeners;
import com.zlkj.jianjie.myview.MyPopupWindows;
import com.zlkj.jianjie.myview.PullToRefreshView;
import com.zlkj.jianjie.util.AppUtil;
import com.zlkj.jianjie.util.StringUtils;
import com.zlkj.jianjie.viewholder.CallBackData;
import com.zlkj.jianjie.viewholder.ListViewAdapter;
import com.zlkj.jianjie.viewholder.XjrViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by gulong on 2016/9/7.
 * 智能匹配
 */
public class ZhiNengFragment extends BaseFragment{


    private String citys[] = { "不限", "有信用卡", "无信用卡"};
    private String ages[] = { "不限", "分期还款", "到期还款"};
    private String sexs[] = { "默认排序", "通过率", "放款速度"};
    private String constellations[] = {"白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座", "水瓶座", "双鱼座"};


    private List<View> myCountlistview=new ArrayList<View>();
    private ListView cityView;
    private ListView ageView;
    private ListView sexView;
    private ListView constellation;


    private TagAdapter<String> mMobileTagAdapter;
    private TagAdapter<String> mMobileTagAdapter2;
    private TagAdapter<String> mMobileTagAdapter3;

    private MyPopupWindows popupWindows1;
    private MyPopupWindows popupWindows2;
    private MyPopupWindows popupWindows3;
    private MyPopupWindows popupWindows4;

    private MyPopupWindows qxpopupWindows;

    private GirdDropDownAdapter cityAdapter;
    private GirdDropDownAdapter ageAdapter;
    private GirdDropDownAdapter sexAdapter;
    // private GirdDropDownAdapter constellationAdapter;

    private ConstellationAdapter constellationAdapter;

    private ListViewAdapter<String> myListviewAdapter;
    private List<String> mystring=new ArrayList<String>();


    @Bind(R.id.mytouming_cungt)
    ImageView mytoumingcun;

    @Bind(R.id.zhinengpp_mycounglayout)
    LinearLayout mylinlayout;

    @Bind(R.id.zhinengpp_pulltoresreshview)
    PullToRefreshView znppPulltoresreshview;

    /*@Bind(R.id.zhinengpp_dropdowenu)
    DropDownMenu myzndroodown;*/

    @Bind(R.id.myactionbar_titile)
    TextView myacitontitle;

    @Bind(R.id.zhinengpp_mylistview)
    MyListView znppMyList;

    @Bind(R.id.zhinengpp_xuanzecount_layout)
    LinearLayout znppxuanzeLyout;

    @Bind(R.id.zhinengpp_touming_img)
    ImageView toumingImg;

    @Bind(R.id.zhinengpp_ywxyk_text) //有无信用卡
            TextView znppYwxyk;

    @Bind(R.id.zhinengpp_hkfs_text)//还款方式
            TextView znpphkfs;

    @Bind(R.id.zhinengpp_morempx_text)//默认排序
            TextView znppmrpx;

    @Bind(R.id.zhinengpp_qixian_textval)//期限
            TextView myqixainTextval;

    @Bind(R.id.zhinengpp_fragment_shurumoney)//借款金额
    TextView znppjkMoney;


    @OnClick(R.id.myactionbar_back)
    public void closeFragment(){
        HomeMainActivity.fragmentManager.popBackStack();
    }

    private Context mContext;


    private int city_index;
    private int sex_index;
    private int age_index;

    private int constellationPosition = 0;

    private List<String[]> myitems=new ArrayList<String[]>();

    private String headers[] = {"有无信用卡", "还款方式 ", "默认排序 ", "筛选  "};


    private List<String> mylistqx=new ArrayList<String>();


    private ListViewAdapter<String> qxAdpater;

    private String jkmoneyInfo;

    @Override
    protected int getLayoutId() {
        return R.layout.zhinengpip_fragment;
    }

    @Override
    protected void init() {
        mContext=getContext();
        jkmoneyInfo=getArguments().getString("jkmoneyInfo");

        if(StringUtils.isNotStringEmpty(jkmoneyInfo)){
            znppjkMoney.setText(jkmoneyInfo);
        }

        myacitontitle.setText("智能匹配");

    }

    @Override
    protected void initData() {

        for (int i = 0; i < 10; i++) {
            mystring.add(""+i);
        }

        setListAdapter();

        setListviewdata();
        setQiXianInfo();
    }


    //设置期限
    private void setQiXianInfo(){

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
                // backgroundAlpha(1f);
            }
        });
        // qxpopupWindows.setAnimationStyle(R.style.popwin_anim_style);

        //期限选择
        qixlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name= (String) qixlist.getItemAtPosition(position);
                myqixainTextval.setText(name+"");
                qxpopupWindows.dismiss();
            }
        });

    }

    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha)
    {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getActivity().getWindow().setAttributes(lp);
    }

    //设置适配器的数据
    private  void setListAdapter(){

        myListviewAdapter=new ListViewAdapter<>(mystring, R.layout.zhinengpip_listview, new CallBackData() {
            @Override
            public <T> void setData(XjrViewHolder viewHolder, T data) {

            }
        });

        znppMyList.setAdapter(myListviewAdapter);

        znppMyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ZhiNengPiDetailsFragment zhiNengPiDetailsFragment=new ZhiNengPiDetailsFragment();
                HomeMainActivity.showFragmetnByFrg(zhiNengPiDetailsFragment);

            }
        });


    }


    //设置数据
    private void setListviewdata(){
        cityView = new ListView(mContext);
        cityAdapter = new GirdDropDownAdapter(mContext, Arrays.asList(citys));
        cityView.setDividerHeight(0);
        cityView.setAdapter(cityAdapter);


        ageView = new ListView(mContext);
        ageView.setDividerHeight(0);
        ageAdapter = new GirdDropDownAdapter(mContext, Arrays.asList(ages));
        ageView.setAdapter(ageAdapter);


        sexView = new ListView(mContext);
        sexView.setDividerHeight(0);
        sexAdapter = new GirdDropDownAdapter(mContext, Arrays.asList(sexs));
        sexView.setAdapter(sexAdapter);


        constellation = new ListView(mContext);
        constellation.setDividerHeight(0);

        constellationAdapter=new ConstellationAdapter(mContext,Arrays.asList(constellations));
        constellation.setAdapter(constellationAdapter);


        setFlowlayoutInfo();


        popupWindows1=new MyPopupWindows(mContext,cityView,null);
        popupWindows2=new MyPopupWindows(mContext,ageView,null);
        popupWindows3=new MyPopupWindows(mContext,sexView,null);


        cityView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                toumingImg.setVisibility(View.GONE);
                znppYwxyk.setText(citys[position]+"");
                cityAdapter.setCheckItem(position);
                popupWindows1.dismiss();

            }
        });

        ageView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                toumingImg.setVisibility(View.GONE);
                znpphkfs.setText(ages[position]+"");
                ageAdapter.setCheckItem(position);
                popupWindows3.dismiss();

            }
        });

        sexView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                toumingImg.setVisibility(View.GONE);
                znppmrpx.setText(sexs[position]+"");
                sexAdapter.setCheckItem(position);
                popupWindows4.dismiss();

            }
        });

        popuwindowInfo();

    }

    //筛选的布局
    private void setFlowlayoutInfo(){

        View flowView= LayoutInflater.from(mContext).inflate(R.layout.flowlayout_count,null);
        RelativeLayout jbxinxi= (RelativeLayout) flowView.findViewById(R.id.flowlayout_jibenxinxi);

        FlowTagLayout mMobileFlowTagLayout= (FlowTagLayout) flowView.findViewById(R.id.flowlayout_flone);
        FlowTagLayout mMobileFlowTagLayout2= (FlowTagLayout) flowView.findViewById(R.id.flowlayout_flone2);
        FlowTagLayout mMobileFlowTagLayout3= (FlowTagLayout) flowView.findViewById(R.id.flowlayout_flone3);

        mMobileTagAdapter = new TagAdapter<>(mContext);
        mMobileTagAdapter2 = new TagAdapter<>(mContext);
        mMobileTagAdapter3 = new TagAdapter<>(mContext);

        mMobileFlowTagLayout.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_MULTI);
        mMobileFlowTagLayout2.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_MULTI);
        mMobileFlowTagLayout3.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_MULTI);

        mMobileFlowTagLayout.setAdapter(mMobileTagAdapter);
        mMobileFlowTagLayout2.setAdapter(mMobileTagAdapter2);
        mMobileFlowTagLayout3.setAdapter(mMobileTagAdapter3);

        jbxinxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindows4.dismiss();
                startActivity(new Intent(mContext, AddMyInfoActivity.class));
            }
        });

        mMobileFlowTagLayout.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList) {

                if (selectedList != null && selectedList.size() > 0) {
                    StringBuilder sb = new StringBuilder();

                    for (int i : selectedList) {
                        sb.append(parent.getAdapter().getItem(i));
                        sb.append(":");
                    }
                    AppUtil.showShort(mContext, "移动研发:" + sb.toString());

                }else{
                    AppUtil.showShort(mContext, "没有选择标签");

                }

            }
        });

        mMobileFlowTagLayout2.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList) {

                if (selectedList != null && selectedList.size() > 0) {
                    StringBuilder sb = new StringBuilder();

                    for (int i : selectedList) {
                        sb.append(parent.getAdapter().getItem(i));
                        sb.append(":");
                    }
                    AppUtil.showShort(mContext, "移动研发222:" + sb.toString());

                }else{
                    AppUtil.showShort(mContext, "没有选择标签222");

                }

            }
        });

        mMobileFlowTagLayout3.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList) {

                if (selectedList != null && selectedList.size() > 0) {
                    StringBuilder sb = new StringBuilder();

                    for (int i : selectedList) {
                        sb.append(parent.getAdapter().getItem(i));
                        sb.append(":");
                    }
                    AppUtil.showShort(mContext, "移动研发333:" + sb.toString());

                }else{
                    AppUtil.showShort(mContext, "没有选择标签333");

                }

            }
        });


        List<String> dataSource = new ArrayList<>();
        dataSource.add("不限");
        dataSource.add("上班族");
        dataSource.add("个体户");
        dataSource.add("企业主");
        dataSource.add("学生");
        dataSource.add("自由职业");
        mMobileTagAdapter.onlyAddAll(dataSource);

        List<String> dataSource2 = new ArrayList<>();
        dataSource2.add("不限");
        dataSource2.add("信用良好，无逾期");
        dataSource2.add("1年内逾期超过3次");
        dataSource2.add("1年内逾期少于3次");
        dataSource2.add("学生");
        dataSource2.add("自由职业");
        mMobileTagAdapter2.onlyAddAll(dataSource2);


        List<String> dataSource3 = new ArrayList<>();
        dataSource3.add("不限");
        dataSource3.add("旅游消费");
        dataSource3.add("助学进修");
        dataSource3.add("购车消费");
        dataSource3.add("婚庆服务");
        dataSource3.add("医疗服务");
        dataSource3.add("装修建材");
        dataSource3.add("百货消费");
        mMobileTagAdapter3.onlyAddAll(dataSource3);

        popupWindows4=new MyPopupWindows(mContext,flowView,null);


    }




    //有无信用卡选择
    @OnClick(R.id.zhinengpp_ywxiyk)
    public void zhinengppOnClick(){

        popupWindows2.dismiss();
        popupWindows3.dismiss();
        popupWindows4.dismiss();

        popupWindows1.setOutsideTouchable(true);
        popupWindows1.showAsDropDown(znppxuanzeLyout);
        toumingImg.setVisibility(View.VISIBLE);
        toumingImg.bringToFront();


    }


    //还款方式选择
    @OnClick(R.id.zhinengpp_hkfs_layout)
    public void zhineHkfsOnClick(){

        popupWindows1.dismiss();
        popupWindows3.dismiss();
        popupWindows4.dismiss();

        popupWindows2.setOutsideTouchable(true);
        popupWindows2.showAsDropDown(znppxuanzeLyout);
        toumingImg.setVisibility(View.VISIBLE);
        toumingImg.bringToFront();


    }
    //默认选择选择
    @OnClick(R.id.zhinengpp_mrpx_layout)
    public void zhimrxzOnClick(){

        popupWindows2.dismiss();
        popupWindows1.dismiss();
        popupWindows4.dismiss();

        popupWindows3.setOutsideTouchable(true);
        popupWindows3.showAsDropDown(znppxuanzeLyout);
        toumingImg.setVisibility(View.VISIBLE);
        toumingImg.bringToFront();

    }
    //筛选选择
    @OnClick(R.id.zhinengpp_shaix_layout)
    public void zhinshaixOnClick(){

        popupWindows2.dismiss();
        popupWindows3.dismiss();
        popupWindows1.dismiss();
        popupWindows4.setOutsideTouchable(true);
        popupWindows4.showAsDropDown(znppxuanzeLyout);
        toumingImg.setVisibility(View.VISIBLE);
        toumingImg.bringToFront();

    }

    //消失出去画布
    private void popuwindowInfo(){

        popupWindows1.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                toumingImg.setVisibility(View.GONE);
            }
        });

        popupWindows2.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                toumingImg.setVisibility(View.GONE);
            }
        });
        popupWindows3.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                toumingImg.setVisibility(View.GONE);
            }
        });
        popupWindows4.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                toumingImg.setVisibility(View.GONE);
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


    //期限选择打开
    @OnClick(R.id.zhinengpp_qixian_relayout)
    public void zhinengppqixanrelayout(){

        mytoumingcun.setVisibility(View.VISIBLE);
        mytoumingcun.bringToFront();
        qxpopupWindows.showAtLocation(mylinlayout,Gravity.BOTTOM,0,0);
    }



}
