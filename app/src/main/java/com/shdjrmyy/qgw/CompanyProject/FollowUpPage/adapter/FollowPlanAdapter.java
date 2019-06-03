package com.shdjrmyy.qgw.CompanyProject.FollowUpPage.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shdjrmyy.qgw.CompanyProject.R;
import com.shdjrmyy.qgw.CompanyProject.FollowUpPage.bean.FollowBean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/25.
 */

public class FollowPlanAdapter extends BaseQuickAdapter<FollowBean.DataBean.ListBean, BaseViewHolder> {


    public FollowPlanAdapter() {
        super(R.layout.item_follow_plan, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, FollowBean.DataBean.ListBean item) {
        helper.setText(R.id.follow_Name, item.getFollowName());
        helper.setText(R.id.follow_Description, item.getFollowDescript());
        helper.addOnClickListener(R.id.content);
        helper.addOnClickListener(R.id.right);
    }
}
