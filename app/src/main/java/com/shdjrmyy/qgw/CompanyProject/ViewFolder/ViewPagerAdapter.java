package com.shdjrmyy.qgw.CompanyProject.ViewFolder;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.shdjrmyy.qgw.CompanyProject.BaseFolder.BaseFragment;

import java.util.List;

/**
 * Created by Simple on 2016-11-21.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final List<BaseFragment> fragments;

    public ViewPagerAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getTitle();
    }
}
