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
import com.zlkj.jianjie.util.StringUtils;
import com.zlkj.jianjie.viewholder.CallBackData;
import com.zlkj.jianjie.viewholder.ListViewAdapter;
import com.zlkj.jianjie.viewholder.XjrViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by gulong on 2016/9/14.
 * 申请人资历
 */
public class ApplicanQuestionActivity extends BaseActivity{


    private Context mContext;

    private ListViewAdapter<String> qxAdpater;

    private int flages=0;
    private MyPopupWindows   qxpopupWindows;

    private List<String> mylistqx1=new ArrayList<String>();//职业类别
    private List<String> mylistqx2=new ArrayList<String>();//是否有本地公积金
    private List<String> mylistqx3=new ArrayList<String>();//是否有本地社保
    private List<String> mylistqx4=new ArrayList<String>();//名下房产类型
    private List<String> mylistqx5=new ArrayList<String>();//名下是否有车

    private List<String> mylistqx6=new ArrayList<String>();//您的信用情况

    private List<String> mylistqx7=new ArrayList<String>();//工资发放形式
    private List<String> mylistqx8=new ArrayList<String>();//当前单位工龄

    private List<String> mylistqx9=new ArrayList<String>();//经营年限
    private List<String> mylistqx10=new ArrayList<String>();//是否办理过营业执照

    @Bind(R.id.applican_question_sbz_layout)//上班族的布局
            LinearLayout applicaLinlayout1;
    @Bind(R.id.applican_question_gth_layout) //个体户
            LinearLayout applicaLinlayout2;
    @Bind(R.id.applican_question_wgdzy_layout)//无固定职业
            RelativeLayout applicaLinlayout3;


    @Bind(R.id.applican_question_count_relayout)
    RelativeLayout appquesRealyout;

    @Bind(R.id.applicaqustion_mytouming)
    ImageView mytoumingcun; //遮层

    @Bind(R.id.applican_question_text1) //职业身份
            TextView zysftext;
    @Bind(R.id.applican_question_text2) //是否有本地公积金
            TextView isbdgjjtext;
    @Bind(R.id.applican_question_text3) //是否有本地社保
            TextView isbdsbtext;
    @Bind(R.id.applican_question_text4) //名下房产类型
            TextView mxfclxtext;
    @Bind(R.id.applican_question_text5) //名下是否有车
            TextView mxisyctext;
    @Bind(R.id.applican_question_text6) //您的信用情况
            TextView youxyqktext;
    @Bind(R.id.applican_question_text7) //年龄
            EditText agetext;


    @Bind(R.id.applican_question_text10) //工资发放形式
            TextView gzffxstext;
    @Bind(R.id.applican_question_onclick_text11) //银行卡发放工资      {上班族}
            EditText yhkffgztext;
    @Bind(R.id.applican_question_onclick_text12) //当前单位工龄
            TextView dqdwglintext;


    @Bind(R.id.applican_question_onclick_text13) //总经营流水
            EditText ajyliustext;
    @Bind(R.id.applican_question_onclick_text14) //现金结算经营收入
            EditText xjjsjesrtext;                                      //{个体户}
    @Bind(R.id.applican_question_onclick_text15) //经营年限
            TextView jinynxtext;
    @Bind(R.id.applican_question_onclick_text16) //是否办理过营业执照
            TextView isbanlgyytext;

    @Bind(R.id.applican_question_onclick_text17) //无固定职业
            EditText wugdzytext;


    @Bind(R.id.myactionbar_titile)
    TextView myaciontTitile;

    //关闭
    @OnClick(R.id.myactionbar_back)
    public void closeActivityinfo(){
        finish();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.applica_question_layout;
    }

    @Override
    protected void init() {
        mContext=this;
        myaciontTitile.setText("申请人资质");



    }

    @Override
    protected void initData() {

        mylistqx2.add("否，不缴纳");
        mylistqx2.add("是，有本地公积金");

        mylistqx3.add("否，没有");
        mylistqx3.add("是，有本地社保");

        mylistqx4.add("无房产");
        mylistqx4.add("小产权房");
        mylistqx4.add("经适/限价房");
        mylistqx4.add("房改/危改房");
        mylistqx4.add("商铺");
        mylistqx4.add("厂房");
        mylistqx4.add("商住两用房");

        mylistqx5.add("无车");
        mylistqx5.add("无车，准备购买");
        mylistqx5.add("名下有车");

        mylistqx6.add("1年内逾期超过3次或超过90天");
        mylistqx6.add("1年内逾期少于3次且少于90天");
        mylistqx6.add("无信用卡或贷款");
        mylistqx6.add("信用良好，无逾期");


        mylistqx1.add("上班族");
        mylistqx1.add("个体户");
        mylistqx1.add("无固定职业");
        mylistqx1.add("企业主");
        mylistqx1.add("学生");


        mylistqx7.add("银行卡发放");
        mylistqx7.add("现金发放");
        mylistqx7.add("部分银行卡，部分现金");


        mylistqx9.add("不足3个月");
        mylistqx9.add("3~5个月");
        mylistqx9.add("6~11个月");
        mylistqx9.add("1~3年");
        mylistqx9.add("4~7年");
        mylistqx9.add("7年已上");

        mylistqx8.add("不足3个月");
        mylistqx8.add("3个月");
        mylistqx8.add("半年");
        mylistqx8.add("1年");
        mylistqx8.add("2年");
        mylistqx8.add("3年");
        mylistqx8.add("5年已上");

        mylistqx10.add("没办理过");
        mylistqx10.add("已办理，注册不满6个月");
        mylistqx10.add("已办理，注册6-11个月");
        mylistqx10.add("已办理，注册满1年");
        mylistqx10.add("已办理，注册满2年");
        mylistqx10.add("已办理，注册满3年");
        mylistqx10.add("已办理，注册满4年");
        mylistqx10.add("已办理，5年已上");


    }



    //职业身份
    @OnClick(R.id.applican_question_onclick1)
    public void  appQuestionLay1(){

        showmyappQuestionPop(mylistqx1,1);
        showPopuwindosmy();

    }

    //是否有本地公积金
    @OnClick(R.id.applican_question_onclick2)
    public void   appQuestionLay2(){
        showmyappQuestionPop(mylistqx2,2);
        showPopuwindosmy();
    }

    //是否有本地社保
    @OnClick(R.id.applican_question_onclick3)
    public void   appQuestionLay3(){
        showmyappQuestionPop(mylistqx3,3);
        showPopuwindosmy();
    }

    //名下房产类型
    @OnClick(R.id.applican_question_onclick4)
    public void   appQuestionLay4(){
        showmyappQuestionPop(mylistqx4,4);
        showPopuwindosmy();
    }

    //名下是否有车
    @OnClick(R.id.applican_question_onclick5)
    public void   appQuestionLay5(){
        showmyappQuestionPop(mylistqx5,5);
        showPopuwindosmy();
    }

    //您的信用情况
    @OnClick(R.id.applican_question_onclick6)
    public void   appQuestionLay6(){
        showmyappQuestionPop(mylistqx6,6);
        showPopuwindosmy();
    }



    //工资发放形式
    @OnClick(R.id.applican_question_onclick10)
    public void   appQuestionLay10(){
        showmyappQuestionPop(mylistqx7,7);
        showPopuwindosmy();
    }

    //当前单位工龄
    @OnClick(R.id.applican_question_onclick12)
    public void   appQuestionLay12(){
        showmyappQuestionPop(mylistqx8,8);
        showPopuwindosmy();
    }

    //经营年限
    @OnClick(R.id.applican_question_onclick15)
    public void   appQuestionLay15(){
        showmyappQuestionPop(mylistqx9,9);
        showPopuwindosmy();
    }

    //是否经办过营业执照
    @OnClick(R.id.applican_question_onclick16)
    public void   appQuestionLay16(){
        showmyappQuestionPop(mylistqx10,10);
        showPopuwindosmy();
    }



    //选择的对话框
    public void showmyappQuestionPop(List<String> xzlist, final int sty){

        View qixLisiview=  LayoutInflater.from(mContext).inflate(R.layout.addmyinfo_popuwindos_layout,null);
        final MyListView mycounglist= (MyListView) qixLisiview.findViewById(R.id.addpopuwindos_listview);


        qxAdpater=new ListViewAdapter<String>(xzlist, R.layout.qixian_layout, new CallBackData() {
            @Override
            public <T> void setData(XjrViewHolder viewHolder, T data) {

                String st= (String) data;
                TextView mytex1= (TextView) viewHolder.getView(R.id.qixian_layout_text);

                mytex1.setText(st);

            }
        });

        mycounglist.setAdapter(qxAdpater);

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


        mycounglist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String mynames= (String) mycounglist.getItemAtPosition(position);

                if(sty==1){
                    zysftext.setText(""+mynames);

                    if(mynames.equals("上班族")){

                        applicaLinlayout1.setVisibility(View.VISIBLE);
                        applicaLinlayout2.setVisibility(View.GONE);
                        applicaLinlayout3.setVisibility(View.GONE);
                        flages=1;

                    }else if(mynames.equals("个体户")){
                        applicaLinlayout2.setVisibility(View.VISIBLE);
                        applicaLinlayout1.setVisibility(View.GONE);
                        applicaLinlayout3.setVisibility(View.GONE);
                        flages=2;

                    }else if(mynames.equals("无固定职业")){
                        applicaLinlayout3.setVisibility(View.VISIBLE);
                        applicaLinlayout1.setVisibility(View.GONE);
                        applicaLinlayout2.setVisibility(View.GONE);
                        flages=3;

                    }else if(mynames.equals("企业主")){
                        applicaLinlayout2.setVisibility(View.VISIBLE);
                        applicaLinlayout1.setVisibility(View.GONE);
                        applicaLinlayout3.setVisibility(View.GONE);
                        flages=4;
                    }else if(mynames.equals("学生")){
                        applicaLinlayout1.setVisibility(View.GONE);
                        applicaLinlayout2.setVisibility(View.GONE);
                        applicaLinlayout3.setVisibility(View.GONE);
                        flages=5;
                    }

                }else if(sty==2){
                    isbdgjjtext.setText(""+mynames);
                }else if(sty==3){
                    isbdsbtext.setText(""+mynames);
                }else if(sty==4){
                    mxfclxtext.setText(""+mynames);
                }else if(sty==5){
                    mxisyctext.setText(""+mynames);
                }else if(sty==6){
                    youxyqktext.setText(""+mynames);
                }else if(sty==7){
                    gzffxstext.setText(""+mynames);
                }else if(sty==8){
                    dqdwglintext.setText(""+mynames);
                }else if(sty==9){
                    jinynxtext.setText(""+mynames);
                }else if(sty==10){
                    isbanlgyytext.setText(""+mynames);
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

    //弹出的选择popuwindow
    private void showPopuwindosmy(){

        mytoumingcun.setVisibility(View.VISIBLE);
        mytoumingcun.bringToFront();


        qxpopupWindows.showAtLocation(appquesRealyout, Gravity.BOTTOM,0,0);
    }

    //判断是否选择
    private boolean ismyStringValnull(String names){
        if(StringUtils.isStringEmpty(names) || "请选择".equals(names)|| "请输入".equals(names)){
            return  true;
        }else{
            return false;
        }

    }






}
