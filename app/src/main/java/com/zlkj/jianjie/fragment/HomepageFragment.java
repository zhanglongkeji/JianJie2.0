package com.zlkj.jianjie.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zlkj.jianjie.R;
import com.zlkj.jianjie.activity.HomeMainActivity;
import com.zlkj.jianjie.adapter.BannerMainGalleryAdapter;
import com.zlkj.jianjie.base.BaseFragment;
import com.zlkj.jianjie.myview.Myviewpagers;
import com.zlkj.jianjie.util.AppUtil;
import com.zlkj.jianjie.util.StringUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by gulong on 2016/9/7.
 * 首页
 */
public class HomepageFragment extends BaseFragment {


    private Context mContext;

    private ZhiNengFragment zhiNengFragment;

    private BannerMainGalleryAdapter bannerAdapter;
    private ArrayList<ImageView> portImg;

    private ArrayList<String> imgurllist=new ArrayList<String>();

    private int preSelImgIndex = 0;// 存储上一个选择项的Index

    @Bind(R.id.ll_focus_indicator_container)
    LinearLayout ll_focus_indicator_container;

    @Bind(R.id.homepage_mylistviewpage) //首页banner图
            Myviewpagers mPager;

    @Bind(R.id.homepage_jkmoney_edittext)//借款金额
    EditText homejkmoney;

    @Bind(R.id.myactionbar_titile)
    TextView myacitontitle;

    @Bind(R.id.myactionbar_back)
    ImageView myimageaction;

    private Dialog dialog;

    @Override
    protected int getLayoutId() {

        return R.layout.homepage_fragment;

    }


    @Override
    protected void init() {
        mContext=getContext();
        myacitontitle.setText("简借");
        myimageaction.setVisibility(View.GONE);

    }

    @Override
    protected void initData() {

        imgurllist.add("http://admin.jiujijianghu.com//uploads/picture/20160825/aff362564bd616ca17962bafe46a505c.jpg");
        imgurllist.add("http://admin.jiujijianghu.com//uploads/picture/20160825/90f5da006c416a330bfc474e0f07f39f.jpg");

        showBannerInfo(imgurllist);

    }


    //申请借款点击
    @OnClick(R.id.homepage_shenqignjiek)
    public void sqjkOnClick(){
        String jkmoney= homejkmoney.getText().toString();
        if(StringUtils.isStringEmpty(jkmoney)){
            showdialong();

            return;
        }

        zhiNengFragment=new ZhiNengFragment();
        Bundle bundle=new Bundle();
        bundle.putString("jkmoneyInfo",jkmoney);
        zhiNengFragment.setArguments(bundle);
        HomeMainActivity.showFragmetnByFrg(zhiNengFragment);

    }

    //输入金额提示对话框
    private void showdialong(){
        View myview= LayoutInflater.from(mContext).inflate(R.layout.mydialong_onetext,null);
        TextView mydiane= (TextView) myview.findViewById(R.id.mydialog_onetext1);
        mydiane.setText("请输入借款金额");
        dialog=AppUtil.newDialong(mContext,myview,R.style.tigerDialog);

        dialog.show();
        myview.findViewById(R.id.mydialog_onetx_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }


    //设置banner图
    private void showBannerInfo(final ArrayList<String> imgs){
        if(bannerAdapter==null){
            bannerAdapter=new BannerMainGalleryAdapter(mContext, imgs);
        }

        InitFocusIndicatorContainer(imgs);
        mPager.setAdapter(bannerAdapter);
        mPager.setFocusable(true);

        mPager.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> arg0, View arg1,int selIndex, long arg3) {
                selIndex = selIndex % imgs.size();
                // // 修改上一次选中项的背景
                portImg.get(preSelImgIndex).setImageResource(
                        R.mipmap.page_control_n);

                // 修改当前选中项的背景
                portImg.get(selIndex).setImageResource(
                        R.mipmap.page_control_h);
                preSelImgIndex = selIndex;
            }

            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        //banner图点击
        mPager.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               /* startActivity(new Intent(mContext, BannerGotoActivity.class)
                        .putExtra("Mytitlename",nameUrl[position])
                        .putExtra("UrlImg",imgUrl[position])
                );*/


            }
        });

    }


    private void InitFocusIndicatorContainer(ArrayList<String> list2) {
        portImg = new ArrayList<ImageView>();
        for (int i = 0; i < list2.size(); i++) {
            ImageView localImageView = new ImageView(mContext);
            localImageView.setId(i);
            ImageView.ScaleType localScaleType = ImageView.ScaleType.FIT_XY;
            localImageView.setScaleType(localScaleType);
            LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(
                    30, 30);
            localImageView.setLayoutParams(localLayoutParams);
            localImageView.setPadding(6, 6, 6, 6);
            localImageView.setImageResource(R.mipmap.page_control_n);
            portImg.add(localImageView);
            this.ll_focus_indicator_container.addView(localImageView);
        }
    }




}
