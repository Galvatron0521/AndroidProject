package com.shdjrmyy.qgw.CompanyProject.PatientPage.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/1.
 */

public class URLBean {

    /**
     * data : {"CRFlist":["?act=patientsField&moduleCode=SP020105&moduleName=诊断","?act=patientsField&moduleCode=SP020107&moduleName=入院病史和检查","?act=patientsField&moduleCode=SP020109&moduleName=主诉","?act=patientsField&moduleCode=SP020122&moduleName=人口学及一般临床资料访谈","?act=patientsField&moduleCode=SP020118&moduleName=实验室检查","?act=patientsField&moduleCode=SP020123&moduleName=治疗信息"],"LBlist":["?act=scaleRecordList&moduleCode=SP020113&moduleName=Hamilton抑郁评定量表（HAMD）","?act=scaleRecordList&moduleCode=SP020114&moduleName=汉密尔顿焦虑量表（HAMA）","?act=scaleRecordList&moduleCode=SP020115&moduleName=锥体外系副反应量表","?act=scaleRecordList&moduleCode=SP020116&moduleName=WHO伤残评定量表（WHODAS2.0）（自评）","?act=scaleRecordList&moduleCode=SP020119&moduleName=PANSS量表","?act=scaleRecordList&moduleCode=SP020120&moduleName=副反应量表(TESS)","?act=scaleRecordList&moduleCode=SP020121&moduleName=临床疗效总评量表（CGI）"]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<String> CRFlist;
        private List<String> LBlist;

        public List<String> getCRFlist() {
            return CRFlist;
        }

        public void setCRFlist(List<String> CRFlist) {
            this.CRFlist = CRFlist;
        }

        public List<String> getLBlist() {
            return LBlist;
        }

        public void setLBlist(List<String> LBlist) {
            this.LBlist = LBlist;
        }
    }
}
