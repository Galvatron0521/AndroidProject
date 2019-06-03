package com.shdjrmyy.qgw.CompanyProject.PatientPage.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shdjrmyy.qgw.CompanyProject.R;
import com.shdjrmyy.qgw.CompanyProject.FollowUpPage.bean.FollowBean;


/**
 * Created by Administrator on 2017/12/25.
 */

public class FollowWayAdapter extends BaseQuickAdapter<FollowBean.DataBean.ListBean, BaseViewHolder> {


    public FollowWayAdapter() {
        super(R.layout.item_followway, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, FollowBean.DataBean.ListBean item) {

        helper.setText(R.id.follow_Name, item.getFollowName());
        helper.setText(R.id.follow_Description, item.getFollowDescript());
        helper.setChecked(R.id.check, item.isCheck());
        helper.addOnClickListener(R.id.content);
    }
}
