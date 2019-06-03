package com.shdjrmyy.qgw.CompanyProject.PatientPage.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/22.
 */

public class HRecordBean {

    /**
     * status : 0
     * data : {"pageCount":10,"totalCount":4,"pageNo":0,"list":[{"id":1,"patientID":1,"hospitalNum":"001","diseasesID":"1","inTime":"2017-07-01 13:46:28","outTime":"2017-08-01 14:05:00","diagnosis":"诊断","patientSay":"主诉","procedure":"手术","treat":"特点"},{"id":2,"patientID":1,"hospitalNum":"002","diseasesID":"1","inTime":"2017-08-01 14:08:58","outTime":"2017-08-01 14:09:05","diagnosis":"1","patientSay":"1","procedure":"1","treat":"1"},{"id":3,"patientID":1,"hospitalNum":"003","diseasesID":"1","inTime":"2017-08-01 14:40:07","outTime":"2017-08-01 14:40:08","diagnosis":"","patientSay":"","procedure":"","treat":""},{"id":7,"patientID":1,"hospitalNum":"12312","diseasesID":"1","inTime":"2017-10-30 17:27:23","outTime":"2017-10-30 17:27:25","diagnosis":"123","patientSay":"123","procedure":"123","treat":"123"}]}
     */

    private String status;
    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * pageCount : 10
         * totalCount : 4
         * pageNo : 0
         * list : [{"id":1,"patientID":1,"hospitalNum":"001","diseasesID":"1","inTime":"2017-07-01 13:46:28","outTime":"2017-08-01 14:05:00","diagnosis":"诊断","patientSay":"主诉","procedure":"手术","treat":"特点"},{"id":2,"patientID":1,"hospitalNum":"002","diseasesID":"1","inTime":"2017-08-01 14:08:58","outTime":"2017-08-01 14:09:05","diagnosis":"1","patientSay":"1","procedure":"1","treat":"1"},{"id":3,"patientID":1,"hospitalNum":"003","diseasesID":"1","inTime":"2017-08-01 14:40:07","outTime":"2017-08-01 14:40:08","diagnosis":"","patientSay":"","procedure":"","treat":""},{"id":7,"patientID":1,"hospitalNum":"12312","diseasesID":"1","inTime":"2017-10-30 17:27:23","outTime":"2017-10-30 17:27:25","diagnosis":"123","patientSay":"123","procedure":"123","treat":"123"}]
         */

        private int pageCount;
        private int totalCount;
        private int pageNo;
        private List<ListBean> list;

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getPageNo() {
            return pageNo;
        }

        public void setPageNo(int pageNo) {
            this.pageNo = pageNo;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 1
             * patientID : 1
             * hospitalNum : 001
             * diseasesID : 1
             * inTime : 2017-07-01 13:46:28
             * outTime : 2017-08-01 14:05:00
             * diagnosis : 诊断
             * patientSay : 主诉
             * procedure : 手术
             * treat : 特点
             */

            private int id;
            private int patientID;
            private String hospitalNum;
            private String diseasesID;
            private String inTime;
            private String outTime;
            private String diagnosis;
            private String patientSay;
            private String procedure;
            private String treat;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getPatientID() {
                return patientID;
            }

            public void setPatientID(int patientID) {
                this.patientID = patientID;
            }

            public String getHospitalNum() {
                return hospitalNum;
            }

            public void setHospitalNum(String hospitalNum) {
                this.hospitalNum = hospitalNum;
            }

            public String getDiseasesID() {
                return diseasesID;
            }

            public void setDiseasesID(String diseasesID) {
                this.diseasesID = diseasesID;
            }

            public String getInTime() {
                return inTime;
            }

            public void setInTime(String inTime) {
                this.inTime = inTime;
            }

            public String getOutTime() {
                return outTime;
            }

            public void setOutTime(String outTime) {
                this.outTime = outTime;
            }

            public String getDiagnosis() {
                return diagnosis;
            }

            public void setDiagnosis(String diagnosis) {
                this.diagnosis = diagnosis;
            }

            public String getPatientSay() {
                return patientSay;
            }

            public void setPatientSay(String patientSay) {
                this.patientSay = patientSay;
            }

            public String getProcedure() {
                return procedure;
            }

            public void setProcedure(String procedure) {
                this.procedure = procedure;
            }

            public String getTreat() {
                return treat;
            }

            public void setTreat(String treat) {
                this.treat = treat;
            }
        }
    }
}
