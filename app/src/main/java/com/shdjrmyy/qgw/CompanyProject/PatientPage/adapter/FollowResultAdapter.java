package com.shdjrmyy.qgw.CompanyProject.PatientPage.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shdjrmyy.qgw.CompanyProject.PatientPage.bean.FollowResultBean;
import com.shdjrmyy.qgw.CompanyProject.R;

/**
 * Created by Administrator on 2018/1/3.
 */

public class FollowResultAdapter extends BaseQuickAdapter<FollowResultBean.DataBean.ListBean, BaseViewHolder> {


    public FollowResultAdapter() {
        super(R.layout.item_follow_result, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, FollowResultBean.DataBean.ListBean item) {
        helper.setText(R.id.FlupMode, item.getFollowType());
        helper.setText(R.id.FlupDescribe, item.getDescript());
    }
}
