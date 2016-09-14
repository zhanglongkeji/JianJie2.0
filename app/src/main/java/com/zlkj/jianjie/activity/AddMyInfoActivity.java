package com.zlkj.jianjie.activity;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zlkj.jianjie.R;
import com.zlkj.jianjie.base.BaseActivity;
import com.zlkj.jianjie.myview.MyListView;
import com.zlkj.jianjie.myview.MyPopupListeners;
import com.zlkj.jianjie.myview.MyPopupWindows;
import com.zlkj.jianjie.util.AppUtil;
import com.zlkj.jianjie.util.StringUtils;
import com.zlkj.jianjie.viewholder.CallBackData;
import com.zlkj.jianjie.viewholder.ListViewAdapter;
import com.zlkj.jianjie.viewholder.XjrViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by gulong on 2016/9/13.
 *  完善申请信息
 */
public class AddMyInfoActivity extends BaseActivity {

    private Context mContext;

    private List<String> mylistqx1=new ArrayList<String>();//期限
    private List<String> mylistqx2=new ArrayList<String>();//教育程度
    private List<String> mylistqx3=new ArrayList<String>();//是否缴纳社保
    private List<String> mylistqx4=new ArrayList<String>();//车辆情况
    private List<String> mylistqx5=new ArrayList<String>();//职业类别

    private List<String> mylistqx6=new ArrayList<String>();//工资发放形式
    private List<String> mylistqx7=new ArrayList<String>();//当前单位工龄
    private List<String> mylistqx8=new ArrayList<String>();//经营年限
    private List<String> mylistqx9=new ArrayList<String>();//是否办理过营业执照


    private ListViewAdapter<String> qxAdpater;
    private MyPopupWindows   qxpopupWindows;
    private int flages=0;

    @Bind(R.id.addmyinfo_onclick_text1)
    TextView addinfoText1;
    @Bind(R.id.addmyinfo_onclick_text2)
    TextView addinfoText2;
    @Bind(R.id.addmyinfo_onclick_text3)
    TextView addinfoText3;
    @Bind(R.id.addmyinfo_onclick_text4)
    TextView addinfoText4;
    @Bind(R.id.addmyinfo_onclick_text5)
    TextView addinfoText5;

    @Bind(R.id.addmyinfo_onclick_text11)//银行卡发放工资/月
            EditText yhkfsgzMonth;
    @Bind(R.id.addmyinfo_onclick_text13)//总经营流水
            EditText zjyliushui;
    @Bind(R.id.addmyinfo_onclick_text14)//现金结算经营收入/月
            EditText xjjssrMonth;

    @Bind(R.id.addmyinfo_onclick_text17)//现金收入   -无固定职业
            EditText xjsrMonth;

    @Bind(R.id.add_myinfo_name) //姓名
            EditText addmyinfoName;
    @Bind(R.id.add_myinfo_card) //身份证
            EditText addmyinfoCard;
    @Bind(R.id.add_myinfo_sqmoney) //申请金额
            EditText addmyinfoSqmoney;
    @Bind(R.id.add_myinfo_kjszhke) //可接受最高月还款额(元)
            EditText addmyinfoKjszgje;

    @Bind(R.id.add_myinfo_sbz_layout) //上班族下面的显示
            LinearLayout sbzLayout;

    @Bind(R.id.add_myinfo_wgdzy_layout) //无固定职业下面的显示
            RelativeLayout wgdzyLayout;

    @Bind(R.id.add_myinfo_gth_layout) //个体户下面的显示
            LinearLayout gthLayout;


    @Bind(R.id.addmyinfo_onclick_text10)//工资发放形式值
            TextView gzfxsText;

    @Bind(R.id.addmyinfo_onclick_text12)//当前单位工龄
            TextView dqdwglText;

    @Bind(R.id.addmyinfo_onclick_text15)//经营年限
            TextView jjnxText;

    @Bind(R.id.addmyinfo_onclick_text16)//是否经办过营业执照
            TextView isblyyzzText;


    @Bind(R.id.myznpp_details_countrelayoutpu)
    RelativeLayout mylinlayout;

    @Bind(R.id.mytouming_cungt3)//遮层
            ImageView mytoumingcun;


    @Bind(R.id.myactionbar_titile)
    TextView mytitle;

    @OnClick(R.id.myactionbar_back)
    public void closeAct(){
        finish();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.add_myinfo;
    }

    @Override
    protected void init() {
        mContext=this;
        mytitle.setText("完善申请信息");

    }

    @Override
    protected void initData() {
        mylistqx2.add("硕士及以上");
        mylistqx2.add("本科");
        mylistqx2.add("大专");
        mylistqx2.add("中专/高中及以上");


        mylistqx3.add("缴纳社保");
        mylistqx3.add("未缴纳社保");

        mylistqx4.add("无车");
        mylistqx4.add("无车，准备购买");
        mylistqx4.add("名下有车");

        mylistqx1.add("不限");
        mylistqx1.add("1个月内");
        mylistqx1.add("3个月");
        mylistqx1.add("6个月");
        mylistqx1.add("12个月");
        mylistqx1.add("1年以上");

        mylistqx5.add("上班族");
        mylistqx5.add("个体户");
        mylistqx5.add("无固定职业");
        mylistqx5.add("企业主");
        mylistqx5.add("学生");


        mylistqx6.add("银行卡发放");
        mylistqx6.add("现金发放");
        mylistqx6.add("部分银行卡，部分现金");


        mylistqx7.add("不足3个月");
        mylistqx7.add("3~5个月");
        mylistqx7.add("6~11个月");
        mylistqx7.add("1~3年");
        mylistqx7.add("4~7年");
        mylistqx7.add("7年已上");

        mylistqx8.add("不足3个月");
        mylistqx8.add("3个月");
        mylistqx8.add("半年");
        mylistqx8.add("1年");
        mylistqx8.add("2年");
        mylistqx8.add("3年");
        mylistqx8.add("5年已上");

        mylistqx9.add("没办理过");
        mylistqx9.add("已办理，注册不满6个月");
        mylistqx9.add("已办理，注册6-11个月");
        mylistqx9.add("已办理，注册满1年");
        mylistqx9.add("已办理，注册满2年");
        mylistqx9.add("已办理，注册满3年");
        mylistqx9.add("已办理，注册满4年");
        mylistqx9.add("已办理，5年已上");



    }


    //选择的dialog
    private void setXuanzeInfo(List<String> xzlist, final int st){


        View qixLisiview=  LayoutInflater.from(mContext).inflate(R.layout.addmyinfo_popuwindos_layout,null);
        final MyListView qixlist= (MyListView) qixLisiview.findViewById(R.id.addpopuwindos_listview);


        qxAdpater=new ListViewAdapter<String>(xzlist, R.layout.qixian_layout, new CallBackData() {
            @Override
            public <T> void setData(XjrViewHolder viewHolder, T data) {

                String st= (String) data;
                TextView mytex1= (TextView) viewHolder.getView(R.id.qixian_layout_text);

                mytex1.setText(st);

            }
        });

        qixlist.setAdapter(qxAdpater);

        qxpopupWindows=new MyPopupWindows(mContext,qixLisiview,myPopupListeners);
        qxpopupWindows.setListenerCancle(R.id.qixan_close_popuwindos);//点击外部
        qxpopupWindows.setListener(R.id.addinfo_popuwindos_cancel);//取消

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
                String names= (String) qixlist.getItemAtPosition(position);


                if(st==1){
                    addinfoText1.setText(""+names);
                }else if(st==2){
                    addinfoText2.setText(""+names);
                }else if(st==3){
                    addinfoText3.setText(""+names);
                }else if(st==4){
                    addinfoText4.setText(""+names);
                }else if(st==5){      //职业类别

                    addinfoText5.setText(""+names);

                    if(names.equals("上班族")){

                        sbzLayout.setVisibility(View.VISIBLE);
                        gthLayout.setVisibility(View.GONE);
                        wgdzyLayout.setVisibility(View.GONE);

                        flages=1;

                    }else if(names.equals("个体户")){
                        gthLayout.setVisibility(View.VISIBLE);
                        sbzLayout.setVisibility(View.GONE);
                        wgdzyLayout.setVisibility(View.GONE);
                        flages=2;

                    }else if(names.equals("无固定职业")){
                        wgdzyLayout.setVisibility(View.VISIBLE);
                        gthLayout.setVisibility(View.GONE);
                        sbzLayout.setVisibility(View.GONE);
                        flages=3;

                    }else if(names.equals("企业主")){
                        gthLayout.setVisibility(View.VISIBLE);
                        sbzLayout.setVisibility(View.GONE);
                        wgdzyLayout.setVisibility(View.GONE);
                        flages=4;
                    }else if(names.equals("学生")){
                        gthLayout.setVisibility(View.GONE);
                        sbzLayout.setVisibility(View.GONE);
                        wgdzyLayout.setVisibility(View.GONE);
                        flages=5;
                    }

                }else if(st==6){
                    gzfxsText.setText(""+names);
                }else if(st==7){
                    dqdwglText.setText(""+names);
                }else if(st==8){
                    jjnxText.setText(""+names);
                }else if(st==9){
                    isblyyzzText.setText(""+names);
                }

                qxpopupWindows.dismiss();

            }
        });

    }

    MyPopupListeners myPopupListeners=new MyPopupListeners() {
        @Override
        public void OnPopItemSelect(View view) {
            switch (view.getId()){
                case R.id.addinfo_popuwindos_cancel:

                    qxpopupWindows.dismiss();
                    break;
            }

        }
    };


    //提交
    @OnClick(R.id.addmyinfo_btnok)
    public void addbtnokOnClick(){

        String tx1=addmyinfoName.getText().toString();//姓名
        String tx2=addmyinfoCard.getText().toString();//身份证
        String tx3=addmyinfoSqmoney.getText().toString();//申请金额
        String tx4=addmyinfoKjszgje.getText().toString();//可接受最高月还款
        String tx5=addinfoText1.getText().toString();//贷款申请期限
        String tx6=addinfoText2.getText().toString();//教育程度
        String tx7=addinfoText3.getText().toString();//现单位是否缴纳社保
        String tx8=addinfoText4.getText().toString();//车辆情况
        String tx9=addinfoText5.getText().toString();//职业类别

        String tx10=gzfxsText.getText().toString();//工资发放形式
        String tx11=yhkfsgzMonth.getText().toString();//银行卡发放工资   { 上班族}
        String tx12=dqdwglText.getText().toString();//当前单位工龄

        String tx13=zjyliushui.getText().toString();//经营流水
        String tx14=xjjssrMonth.getText().toString();//现金结算经营收入
        String tx15=jjnxText.getText().toString();//经营年限                {个体户}
        String tx16=isblyyzzText.getText().toString();//是否办理过营业执照

        String tx17=xjsrMonth.getText().toString();//现金收入               {无固定职业}


        if(isStringValnull(tx1)){
            AppUtil.showShort(mContext,"请填写姓名");
            return;
        }
        if(isStringValnull(tx2)){
            AppUtil.showShort(mContext,"请填写身份证号");
            return;
        }

        if(isStringValnull(tx3)){
            AppUtil.showShort(mContext,"请填写申请金额");
            return;
        }
        if(isStringValnull(tx4)){
            AppUtil.showShort(mContext,"请选择申请期限");
            return;
        }
        if(isStringValnull(tx5)){
            AppUtil.showShort(mContext,"请填写可接受的最高还款额");
            return;
        }
        if(isStringValnull(tx6)){
            AppUtil.showShort(mContext,"请选择教育程度");
            return;
        }
        if(isStringValnull(tx7)){
            AppUtil.showShort(mContext,"请选择是否缴纳社保");
            return;
        }
        if(isStringValnull(tx8)){
            AppUtil.showShort(mContext,"请选择车辆情况");
            return;
        }
        if(isStringValnull(tx9)){
            AppUtil.showShort(mContext,"请选择职业类别");
            return;
        }


        if(flages==1){  //上班族
            if(isStringValnull(tx10)){
                AppUtil.showShort(mContext,"请选择工资发放形式");
                return;
            }
            if(isStringValnull(tx11)){
                AppUtil.showShort(mContext,"请填写银行卡发放工资");
                return;
            }
            if(isStringValnull(tx12)){
                AppUtil.showShort(mContext,"请选择当前单位工龄");
                return;
            }
        }else if(flages==2){  //个体户
            if(isStringValnull(tx13)){
                AppUtil.showShort(mContext,"请填写总经营流水");
                return;
            }
            if(isStringValnull(tx14)){
                AppUtil.showShort(mContext,"请填写现金结算经营收入");
                return;
            }
            if(isStringValnull(tx15)){
                AppUtil.showShort(mContext,"请选择经营年限");
                return;
            }
            if(isStringValnull(tx16)){
                AppUtil.showShort(mContext,"请选择是否办理过营业执照");
                return;
            }

        }else  if(flages==3){  //无固定职业
            if(isStringValnull(tx17)){
                AppUtil.showShort(mContext,"请填写现金收入");
                return;
            }


        }else  if(flages==4){ //企业主 和个体户一样
            if(isStringValnull(tx13)){
                AppUtil.showShort(mContext,"请填写总经营流水");
                return;
            }
            if(isStringValnull(tx14)){
                AppUtil.showShort(mContext,"请填写现金结算经营收入");
                return;
            }
            if(isStringValnull(tx15)){
                AppUtil.showShort(mContext,"请选择经营年限");
                return;
            }
            if(isStringValnull(tx16)){
                AppUtil.showShort(mContext,"请选择是否办理过营业执照");
                return;
            }

        }else  if(flages==5){  //学生

        }


        AppUtil.showShort(mContext,"提交成功");

    }



    //贷款期限点击
    @OnClick(R.id.addmyinfo_onclick1)
    public void addmuyinfOnclick1(){

        setXuanzeInfo(mylistqx1,1);

        showPopuwindos();

    }

    //教育程度点击
    @OnClick(R.id.addmyinfo_onclick2)
    public void addmuyinfOnclick2(){

        setXuanzeInfo(mylistqx2,2);

        showPopuwindos();

    }

    //是否缴纳社保
    @OnClick(R.id.addmyinfo_onclick3)
    public void addmuyinfOnclick3(){
        setXuanzeInfo(mylistqx3,3);

        showPopuwindos();
    }

    //车辆情况
    @OnClick(R.id.addmyinfo_onclick4)
    public void addmuyinfOnclick4(){
        setXuanzeInfo(mylistqx4,4);

        showPopuwindos();
    }

    //职业类别
    @OnClick(R.id.addmyinfo_onclick5)
    public void addmuyinfOnclick5(){
        setXuanzeInfo(mylistqx5,5);

        showPopuwindos();
    }

    //工资发放形式
    @OnClick(R.id.addmyinfo_onclick10)
    public void  gzfaxsLayout(){
        setXuanzeInfo(mylistqx6,6);
        showPopuwindos();

    }

    //当前单位工龄
    @OnClick(R.id.addmyinfo_onclick12)
    public void  dqdwglLayout(){
        setXuanzeInfo(mylistqx7,7);
        showPopuwindos();
    }

    //经营年限
    @OnClick(R.id.addmyinfo_onclick15)
    public void  jynxLayout(){
        setXuanzeInfo(mylistqx8,8);
        showPopuwindos();
    }

    //是否办过营业执照
    @OnClick(R.id.addmyinfo_onclick16)
    public void  isyyzzLayout(){
        setXuanzeInfo(mylistqx9,9);
        showPopuwindos();
    }


    private void showPopuwindos(){
        mytoumingcun.setVisibility(View.VISIBLE);
        mytoumingcun.bringToFront();
        qxpopupWindows.showAtLocation(mylinlayout, Gravity.BOTTOM,0,0);
    }



    //判断是否选择
    private boolean isStringValnull(String names){
        if(StringUtils.isStringEmpty(names) || "请选择".equals(names)|| "请输入".equals(names)){
                return  true;
        }else{
            return false;
        }

    }


}
