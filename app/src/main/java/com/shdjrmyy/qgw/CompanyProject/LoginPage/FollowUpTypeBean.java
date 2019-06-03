package com.shdjrmyy.qgw.CompanyProject.LoginPage;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2018/1/30.
 */

public class FollowUpTypeBean extends DataSupport {
    private String typeDetailCode;
    private String typeDetailName;

    public String getTypeDetailCode() {
        return typeDetailCode;
    }

    public void setTypeDetailCode(String typeDetailCode) {
        this.typeDetailCode = typeDetailCode;
    }

    public String getTypeDetailName() {
        return typeDetailName;
    }

    public void setTypeDetailName(String typeDetailName) {
        this.typeDetailName = typeDetailName;
    }
}
