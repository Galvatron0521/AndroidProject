package com.shdjrmyy.qgw.CompanyProject.PatientPage.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shdjrmyy.qgw.CompanyProject.R;
import com.shdjrmyy.qgw.CompanyProject.PatientPage.bean.HRecordBean;

/**
 * Created by Administrator on 2017/11/22.
 */

public class HRecordAdapter extends BaseQuickAdapter<HRecordBean.DataBean.ListBean, BaseViewHolder> {


    public HRecordAdapter() {
        super(R.layout.item_hz_hrecord, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, HRecordBean.DataBean.ListBean item) {
        helper.setText(R.id.hz_patientID, String.valueOf(item.getHospitalNum()));
        helper.setText(R.id.hz_inTime, item.getInTime());
        helper.setText(R.id.hz_outTime, item.getOutTime());
        helper.addOnClickListener(R.id.content);
        helper.addOnClickListener(R.id.right);
    }
}
