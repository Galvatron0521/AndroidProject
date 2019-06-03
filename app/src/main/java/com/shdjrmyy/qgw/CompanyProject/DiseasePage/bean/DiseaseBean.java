package com.shdjrmyy.qgw.CompanyProject.DiseasePage.bean;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/12/30.
 */

public class DiseaseBean extends DataSupport {
    private String diseaseNo;
    private String diseaseName;

    public String getDiseaseNo() {
        return diseaseNo;
    }

    public void setDiseaseNo(String diseaseNo) {
        this.diseaseNo = diseaseNo;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }
}
