package com.shdjrmyy.qgw.CompanyProject.DiseasePage;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shdjrmyy.qgw.CompanyProject.DiseasePage.bean.DiseaseListBean;
import com.shdjrmyy.qgw.CompanyProject.R;

/**
 * Created by Administrator on 2017/11/11.
 */

public class DiseasesListAdapter extends BaseQuickAdapter<DiseaseListBean.DataBean.ListBean, BaseViewHolder> {

    public DiseasesListAdapter() {
        super(R.layout.item_diseases_list, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, DiseaseListBean.DataBean.ListBean item) {
        helper.setText(R.id.bzNum, item.getId());
        helper.setText(R.id.bzName, item.getName());
        helper.setText(R.id.bzDetail, item.getDescript());
        helper.addOnClickListener(R.id.right);
        helper.addOnClickListener(R.id.content);
    }
}
