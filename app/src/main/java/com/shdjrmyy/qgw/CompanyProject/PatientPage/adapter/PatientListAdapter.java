package com.shdjrmyy.qgw.CompanyProject.PatientPage.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shdjrmyy.qgw.CompanyProject.R;
import com.shdjrmyy.qgw.CompanyProject.PatientPage.bean.NewPatientBean;


/**
 * Created by Administrator on 2017/11/20.
 */

public class PatientListAdapter extends BaseQuickAdapter<NewPatientBean.DataBean.ListBean, BaseViewHolder> {

    public PatientListAdapter() {
        super(R.layout.item_patient, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewPatientBean.DataBean.ListBean item) {
        if (item.getAge() <= 16) {
            if (item.getSex().equals("男"))
                helper.setImageResource(R.id.hz_icon, R.mipmap.nanx);
            else {
                helper.setImageResource(R.id.hz_icon, R.mipmap.nvx);
            }
        } else {
            if (item.getSex().equals("男"))
                helper.setImageResource(R.id.hz_icon, R.mipmap.nan);
            else {
                helper.setImageResource(R.id.hz_icon, R.mipmap.nv);
            }
        }
        helper.setText(R.id.hz_name, item.getName());
        helper.setText(R.id.hz_old, item.getAge() + "岁");
        helper.setText(R.id.hz_bq, item.getMobile());
        helper.addOnClickListener(R.id.content);
        helper.addOnClickListener(R.id.right);
    }
}
