package com.shdjrmyy.qgw.CompanyProject.FollowUpPage.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shdjrmyy.qgw.CompanyProject.R;
import com.shdjrmyy.qgw.CompanyProject.FollowUpPage.bean.FollowPlan;

import java.util.List;

/**
 * Created by Administrator on 2017/12/22.
 */

public class AddPlanAdapter extends BaseQuickAdapter<FollowPlan, BaseViewHolder> {


    public AddPlanAdapter(int layoutResId, @Nullable List<FollowPlan> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FollowPlan item) {
        helper.setText(R.id.timeNo,String.valueOf(item.getTimeNo()));
    }
}
