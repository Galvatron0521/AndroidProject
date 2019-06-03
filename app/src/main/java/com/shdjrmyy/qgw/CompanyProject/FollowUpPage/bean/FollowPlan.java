package com.shdjrmyy.qgw.CompanyProject.FollowUpPage.bean;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/12/22.
 */

public class FollowPlan extends DataSupport {
    private int timeNo;

    public int getTimeNo() {
        return timeNo;
    }

    public void setTimeNo(int timeNo) {
        this.timeNo = timeNo;
    }
}
