package com.shdjrmyy.qgw.CompanyProject.PatientPage.adapter;


import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shdjrmyy.qgw.CompanyProject.R;
import com.shdjrmyy.qgw.CompanyProject.PatientPage.bean.LinkBean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/29.
 */

public class LinksAdapter extends BaseQuickAdapter<LinkBean, BaseViewHolder> {

    public LinksAdapter(int layoutResId, @Nullable List<LinkBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LinkBean item) {
        helper.setText(R.id.link_title,item.getTitle());
    }
}
