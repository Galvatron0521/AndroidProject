package com.shdjrmyy.qgw.CompanyProject.HomePage.fragment;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.view.View;

import com.shdjrmyy.qgw.CompanyProject.FollowUpPage.MyFollowFragment;
import com.shdjrmyy.qgw.CompanyProject.HomePage.activity.CRFManageActivity;
import com.shdjrmyy.qgw.CompanyProject.HomePage.activity.SearchActivity;
import com.shdjrmyy.qgw.CompanyProject.R;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.BaseFragment;
import com.shdjrmyy.qgw.CompanyProject.DiseasePage.DiseasesListActivity;
import com.shdjrmyy.qgw.CompanyProject.FollowUpPage.FollowActivity;
import com.shdjrmyy.qgw.CompanyProject.PatientPage.activity.PatientListActivity;
import com.shdjrmyy.qgw.CompanyProject.ViewFolder.NoScrollViewPager;
import com.shdjrmyy.qgw.CompanyProject.ViewFolder.ViewPagerAdapter;

import java.util.ArrayList;

/**
 * Created by 16001 on 2017/10/17 0017.
 */

public class HomePageFragment extends BaseFragment implements View.OnClickListener {
    @Override
    protected int setLayout() {
        return R.layout.fragment_homepage;
    }


    TabLayout tabs;
    NoScrollViewPager vp;

    @Override
    protected void initView(View view) {
        tabs = view.findViewById(R.id.tabs);
        vp = view.findViewById(R.id.vp);
        ArrayList<BaseFragment> fragments = new ArrayList<>();
        fragments.add(new DistributionFragment());
        fragments.add(new MemorandumFragment());
        fragments.add(new MyFollowFragment());
        vp.setOffscreenPageLimit(2);
        vp.setAdapter(new ViewPagerAdapter(getChildFragmentManager(), fragments));
        tabs.setupWithViewPager(vp);
        view.findViewById(R.id.patientList).setOnClickListener(this);
        view.findViewById(R.id.ed_search).setOnClickListener(this);
        view.findViewById(R.id.follow).setOnClickListener(this);
        view.findViewById(R.id.CRFManage).setOnClickListener(this);
        view.findViewById(R.id.diseasesList).setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.patientList:
                Intent HZList = new Intent(getContext(), PatientListActivity.class);
                startActivity(HZList);
                break;
            case R.id.ed_search:
                Intent Search = new Intent(getContext(), SearchActivity.class);
                Search.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(Search);
                break;
            case R.id.follow:
                Intent Sf = new Intent(getContext(), FollowActivity.class);
                startActivity(Sf);
                break;
            case R.id.CRFManage:
                Intent CRF = new Intent(getContext(), CRFManageActivity.class);
                startActivity(CRF);
                break;
            case R.id.diseasesList:
                Intent BzList = new Intent(getContext(), DiseasesListActivity.class);
                startActivity(BzList);
                break;
        }
    }
}
