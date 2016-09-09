package com.zlkj.jianjie.fragment;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.zlkj.jianjie.R;
import com.zlkj.jianjie.activity.HomeMainActivity;
import com.zlkj.jianjie.adapter.GirdDropDownAdapter;
import com.zlkj.jianjie.base.BaseFragment;
import com.zlkj.jianjie.myview.DropDownMenu;
import com.zlkj.jianjie.myview.MyListView;
import com.zlkj.jianjie.myview.PullToRefreshView;
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


    private String citys[] = { "武汉", "北京", "上海", "成都", "广州", "深圳", "重庆", "天津", "西安", "南京", "杭州"};
    private String ages[] = { "18岁以下", "18-22岁", "23-26岁", "27-35岁", "35岁以上"};
    private String sexs[] = { "男", "女"};
    private String constellations[] = {"白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座", "水瓶座", "双鱼座"};




    private GirdDropDownAdapter cityAdapter;
    private GirdDropDownAdapter ageAdapter;
    private GirdDropDownAdapter sexAdapter;
    private GirdDropDownAdapter constellationAdapter;

    private ListViewAdapter<String> myListviewAdapter;
    private List<String> mystring=new ArrayList<String>();

    @Bind(R.id.zhinengpp_pulltoresreshview)
    PullToRefreshView znppPulltoresreshview;

    @Bind(R.id.zhinengpp_dropdowenu)
    DropDownMenu myzndroodown;

    @Bind(R.id.myactionbar_titile)
    TextView myacitontitle;

    @Bind(R.id.zhinengpp_mylistview)
    MyListView znppMyList;

    @OnClick(R.id.myactionbar_back)
    public void closeFragment(){
        HomeMainActivity.fragmentManager.popBackStack();
    }

    private Context mContext;

    private List<View> myCountlistview=new ArrayList<View>();

    private ListView cityView;
    private ListView ageView;
    private ListView sexView;
    private ListView constellation;

    private int constellationPosition = 0;

    private String headers[] = {"有无信用卡", "还款方式 ", "默认排序 ", "筛选  "};

    @Override
    protected int getLayoutId() {
        return R.layout.zhinengpip_fragment;
    }

    @Override
    protected void init() {
        mContext=getContext();

        myacitontitle.setText("智能匹配");

    }

    @Override
    protected void initData() {


        for (int i = 0; i < 10; i++) {
            mystring.add(""+i);
        }

        setListAdapter();

        setListviewdata();
        listviewOnClick();

    }


    //设置适配器的数据
    private  void setListAdapter(){

        myListviewAdapter=new ListViewAdapter<>(mystring, R.layout.zhinengpip_listview, new CallBackData() {
            @Override
            public <T> void setData(XjrViewHolder viewHolder, T data) {

            }
        });

        znppMyList.setAdapter(myListviewAdapter);

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
        constellationAdapter = new GirdDropDownAdapter(mContext, Arrays.asList(constellations));
        constellation.setAdapter(sexAdapter);

        myCountlistview.add(cityView);
        myCountlistview.add(ageView);
        myCountlistview.add(sexView);
        myCountlistview.add(constellation);

        TextView contentView = new TextView(mContext);
        contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        contentView.setGravity(Gravity.CENTER);
        contentView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);

        myzndroodown.setDropDownMenu(Arrays.asList(headers),myCountlistview,contentView);

    }

    //点击事件
    private void listviewOnClick(){

        cityView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cityAdapter.setCheckItem(position);
                myzndroodown.setTabText(position == 0 ? headers[0] : citys[position]);
                myzndroodown.closeMenu();
            }
        });


        ageView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ageAdapter.setCheckItem(position);
                myzndroodown.setTabText(position == 0 ? headers[1] : ages[position]);
                myzndroodown.closeMenu();
            }
        });

        sexView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sexAdapter.setCheckItem(position);
                myzndroodown.setTabText(position == 0 ? headers[2] : sexs[position]);
                myzndroodown.closeMenu();
            }
        });

        constellation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                constellationAdapter.setCheckItem(position);
                myzndroodown.setTabText(position == 0 ? headers[2] : sexs[position]);
                myzndroodown.closeMenu();
            }
        });

        znppPulltoresreshview.setOnHeaderRefreshListener(new PullToRefreshView.OnHeaderRefreshListener() {
            @Override
            public void onHeaderRefresh(PullToRefreshView view) {
                znppPulltoresreshview.onHeaderRefreshComplete();
            }
        });

        znppPulltoresreshview.setOnFooterRefreshListener(new PullToRefreshView.OnFooterRefreshListener() {
            @Override
            public void onFooterRefresh(PullToRefreshView view) {
                znppPulltoresreshview.onFooterRefreshComplete();
            }
        });


        znppMyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ZhiNengPiDetailsFragment zhiNengPiDetailsFragment=new ZhiNengPiDetailsFragment();
                HomeMainActivity.showFragmetnByFrg(zhiNengPiDetailsFragment);

            }
        });

    }


    @Override
    public void onDestroy() {

        if (myzndroodown.isShowing()) {
            myzndroodown.closeMenu();
        } else {
            super.onDestroy();
        }

    }
}
