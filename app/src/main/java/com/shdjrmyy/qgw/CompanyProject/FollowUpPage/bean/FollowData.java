package com.shdjrmyy.qgw.CompanyProject.FollowUpPage.bean;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/12/23.
 */

public class FollowData extends DataSupport{
    private String id;
    private String addYear;
    private String addMonth;
    private String addDay;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddYear() {
        return addYear;
    }

    public void setAddYear(String addYear) {
        this.addYear = addYear;
    }

    public String getAddMonth() {
        return addMonth;
    }

    public void setAddMonth(String addMonth) {
        this.addMonth = addMonth;
    }

    public String getAddDay() {
        return addDay;
    }

    public void setAddDay(String addDay) {
        this.addDay = addDay;
    }
}
