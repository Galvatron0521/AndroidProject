package com.shdjrmyy.qgw.CompanyProject.FollowUpPage.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shdjrmyy.qgw.CompanyProject.R;
import com.shdjrmyy.qgw.CompanyProject.FollowUpPage.bean.AllFollowBean;


/**
 * Created by Administrator on 2018/1/16.
 */

public class MyFollowAdapter extends BaseQuickAdapter<AllFollowBean.DataBean.ListBean, BaseViewHolder> {


    public MyFollowAdapter() {
        super(R.layout.item_all_follow, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, AllFollowBean.DataBean.ListBean item) {
        helper.setText(R.id.follow_Name, item.getName());
        helper.setText(R.id.follow_Type, item.getFollowType());
        helper.setText(R.id.follow_startTime, item.getStartTime());
    }
}
