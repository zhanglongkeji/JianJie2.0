package com.zlkj.jianjie.activity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.RadioButton;

import com.zlkj.jianjie.R;
import com.zlkj.jianjie.base.BaseActivity;
import com.zlkj.jianjie.fragment.FaXianFragment;
import com.zlkj.jianjie.fragment.HomepageFragment;
import com.zlkj.jianjie.fragment.LunTanFragment;
import com.zlkj.jianjie.fragment.PersonalFragment;
import com.zlkj.jianjie.fragment.XinYongkaFragment;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by gulong on 2016/9/7.
 * 大框架activity
 */
public class HomeMainActivity  extends BaseActivity{


    public static Context mContext;

    public static FragmentManager fragmentManager;
    public static Fragment currentFragment;

    private HomepageFragment  homepageFragment;


    private FaXianFragment faXianFragment;
    private LunTanFragment lunTanFragment;
    private PersonalFragment personalFragment;
    private XinYongkaFragment xinYongkaFragment;

    @Bind(R.id.main_tabbar_homepage)
    RadioButton radionone;


    @Override
    protected int getLayoutId() {
        return R.layout.homemai_activity;
    }

    @Override
    protected void init() {
        mContext=this;
        fragmentManager = getSupportFragmentManager();


        radionone.setChecked(true);
        showHomeMainFragment();

    }

    @Override
    protected void initData() {

    }


    //首页点击
    @OnClick(R.id.main_tabbar_homepage)
    public void homeOnClickInfo(){
        showHomeMainFragment();
    }

    //发现点击
    @OnClick(R.id.main_tabbar_faxian)
    public void faxianOnClickInfo(){

        faXianFragment=new FaXianFragment();

        showFragmetnByFrg(faXianFragment);

    }

    //论坛点击
    @OnClick(R.id.main_tabbar_luntan)
    public void luntanOnClickInfo(){

        lunTanFragment=new LunTanFragment();

        showFragmetnByFrg(lunTanFragment);

    }

    //信用卡点击
    @OnClick(R.id.main_tabbar_money)
    public void xykOnClickInfo(){

        xinYongkaFragment=new XinYongkaFragment();
        showFragmetnByFrg(xinYongkaFragment);

    }

    //个人中心点击
    @OnClick(R.id.main_tabbar_personal)
    public void personalOnClickInfo(){

        personalFragment=new PersonalFragment();
        showFragmetnByFrg(personalFragment);
    }


    //首页的fragment
    private void showHomeMainFragment(){

        homepageFragment = new HomepageFragment();
        currentFragment = homepageFragment;
        fragmentManager.beginTransaction()
                .replace(R.id.homepage_main_fragment, homepageFragment).commit();

    }


    //切换fragment的方法
    public static void showFragmetnByFrg(Fragment fragment){

        fragmentManager.beginTransaction().addToBackStack(null).hide(currentFragment).replace(R.id.homepage_main_fragment, fragment).commitAllowingStateLoss();
        currentFragment = fragment;

    }





}
