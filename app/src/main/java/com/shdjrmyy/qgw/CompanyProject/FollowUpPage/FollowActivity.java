package com.shdjrmyy.qgw.CompanyProject.FollowUpPage;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.shdjrmyy.qgw.CompanyProject.R;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.BaseActivity;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.BaseFragment;
import com.shdjrmyy.qgw.CompanyProject.UtilsFolder.NetWorkStateReceiver;
import com.shdjrmyy.qgw.CompanyProject.ViewFolder.FuncUtil;
import com.shdjrmyy.qgw.CompanyProject.ViewFolder.ViewPagerAdapter;

import java.util.ArrayList;

public class FollowActivity extends BaseActivity {


    private TabLayout tab;
    private ViewPager vp;


    @Override
    public int setView() {
        return R.layout.activity_sf;
    }

    @Override
    public void initView() {
        FuncUtil.iniSystemBar(this, R.color.white);
        initTB("随访", "");
        tab = findViewById(R.id.tabs);
        vp = findViewById(R.id.vp);
        ArrayList<BaseFragment> fragments = new ArrayList<>();
        fragments.add(new MyFollowFragment());
        fragments.add(new FollowFragment());
        vp.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), fragments));
        tab.setupWithViewPager(vp);
    }

    @Override
    public void setData() {

    }
}
