package com.zlkj.jianjie.fragment;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zlkj.jianjie.R;
import com.zlkj.jianjie.activity.HomeMainActivity;
import com.zlkj.jianjie.base.BaseFragment;
import com.zlkj.jianjie.util.AppUtil;
import com.zlkj.jianjie.util.StringUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by gulong on 2016/9/9.
 * 提现
 */
public class TiXianFragment extends BaseFragment {


    @Bind(R.id.tixian_editText)
    EditText txEditVal;

    @Bind(R.id.myactionbar_titile)
    TextView myactiontitle;

    @OnClick(R.id.myactionbar_back)//返回
    public void closeInfo(){
        HomeMainActivity.fragmentManager.popBackStack();
    }

    private Context mContext;

    private Dialog txdialog;
    private Dialog txzfbdialog;

    @Override
    protected int getLayoutId() {
        return R.layout.tixian_fragment;
    }

    @Override
    protected void init() {

        mContext=getContext();
        myactiontitle.setText("提现");

    }

    @Override
    protected void initData() {

    }

    //提现
    @OnClick(R.id.tixian_btnok)
    public void txBtnOnClick(){
        String txVal=txEditVal.getText().toString().trim();

        if(StringUtils.isStringEmpty(txVal)){
            AppUtil.showShort(mContext,"提现金额不能为空");
            return;
        }

        if(Double.parseDouble(txVal)<10){
            showDialog();
            return;
        }

        showzfbDialog();


    }


    //弹出提现支付宝对话框
    private void showzfbDialog(){

        View zfbView= LayoutInflater.from(mContext).inflate(R.layout.mydialong_tixian,null);
        txzfbdialog=AppUtil.newDialong(mContext,zfbView,R.style.tigerDialog);

        txzfbdialog.show();



    }

    //不满十元显示的dialog
    private void showDialog(){

        View oneTexView= LayoutInflater.from(mContext).inflate(R.layout.mydialong_onetext,null);

        TextView mytex= (TextView) oneTexView.findViewById(R.id.mydialog_onetx_close);


        txdialog=AppUtil.newDialong(mContext,oneTexView,R.style.tigerDialog);

        txdialog.show();

        mytex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txdialog.dismiss();
            }
        });


    }



}
