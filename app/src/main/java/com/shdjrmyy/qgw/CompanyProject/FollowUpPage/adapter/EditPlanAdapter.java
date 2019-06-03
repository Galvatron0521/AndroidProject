package com.shdjrmyy.qgw.CompanyProject.FollowUpPage.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shdjrmyy.qgw.CompanyProject.FollowUpPage.bean.EditPlan;
import com.shdjrmyy.qgw.CompanyProject.R;

import java.util.List;

/**
 * Created by Administrator on 2018/1/20.
 */

public class EditPlanAdapter extends BaseQuickAdapter<EditPlan, BaseViewHolder> {


    public EditPlanAdapter(int layoutResId, @Nullable List<EditPlan> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, EditPlan item) {
        helper.setText(R.id.timeNo, String.valueOf(item.getTimeNo()));
        helper.setText(R.id.add_year, String.valueOf(item.getFollowMaxYear()));
        helper.setText(R.id.add_month, String.valueOf(item.getFollowMaxMonth()));
        helper.setText(R.id.add_day, String.valueOf(item.getFollowMaxDay()));
    }
}