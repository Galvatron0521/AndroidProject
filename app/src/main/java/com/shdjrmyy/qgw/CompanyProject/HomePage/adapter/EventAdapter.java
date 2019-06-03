package com.shdjrmyy.qgw.CompanyProject.HomePage.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shdjrmyy.qgw.CompanyProject.HomePage.bean.BwlBean;
import com.shdjrmyy.qgw.CompanyProject.R;

import java.util.List;

/**
 * Created by Administrator on 2018/2/10.
 */

public class EventAdapter extends BaseQuickAdapter<BwlBean, BaseViewHolder> {

    public EventAdapter(int layoutResId, @Nullable List<BwlBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BwlBean item) {
        helper.setText(R.id.events_title, item.getTitle());
        helper.setText(R.id.events_content, item.getContent());
        helper.addOnClickListener(R.id.content);
        helper.addOnClickListener(R.id.right);
        helper.addOnClickListener(R.id.left);
    }
}
