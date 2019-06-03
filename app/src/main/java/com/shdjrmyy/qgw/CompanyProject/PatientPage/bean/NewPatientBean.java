package com.shdjrmyy.qgw.CompanyProject.PatientPage.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/9.
 */

public class NewPatientBean {

    /**
     * data : {"list":[{"age":11,"brithday":"2016-01-01","cityID":"370900","createTime":1472716156000,"degree":0,"delFlag":0,"hospitalID":1,"id":1,"idCard":"","mobile":"18854889207","name":"患者1","num":"001","provinceID":"370000","regionID":"370901","sex":"男","updateTime":1507952263000},{"age":11,"brithday":"2016-02-18","cityID":"370100","createTime":1472716156000,"degree":0,"delFlag":0,"hospitalID":1,"id":2,"mobile":"18854889207","name":"患者2","num":"002","provinceID":"370000","regionID":"370124","sex":"男","updateTime":1507952263000},{"age":22,"brithday":"2016-02-19","cityID":"110100","createTime":1505879057000,"degree":0,"delFlag":0,"hospitalID":0,"id":19,"idCard":"","mobile":"18854889200","name":"患者3","num":"003","provinceID":"110000","regionID":"","sex":"女","updateTime":1509355779000}],"pageCount":8,"pageNo":0}
     * totalCount : 3
     */

    private DataBean data;


    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * list : [{"age":11,"brithday":"2016-01-01","cityID":"370900","createTime":1472716156000,"degree":0,"delFlag":0,"hospitalID":1,"id":1,"idCard":"","mobile":"18854889207","name":"患者1","num":"001","provinceID":"370000","regionID":"370901","sex":"男","updateTime":1507952263000},{"age":11,"brithday":"2016-02-18","cityID":"370100","createTime":1472716156000,"degree":0,"delFlag":0,"hospitalID":1,"id":2,"mobile":"18854889207","name":"患者2","num":"002","provinceID":"370000","regionID":"370124","sex":"男","updateTime":1507952263000},{"age":22,"brithday":"2016-02-19","cityID":"110100","createTime":1505879057000,"degree":0,"delFlag":0,"hospitalID":0,"id":19,"idCard":"","mobile":"18854889200","name":"患者3","num":"003","provinceID":"110000","regionID":"","sex":"女","updateTime":1509355779000}]
         * pageCount : 8
         * pageNo : 0
         */

        private int pageCount;
        private int pageNo;
        private List<ListBean> list;
        private int totalCount;

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
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
             * age : 11
             * brithday : 2016-01-01
             * cityID : 370900
             * createTime : 1472716156000
             * degree : 0
             * delFlag : 0
             * hospitalID : 1
             * id : 1
             * idCard :
             * mobile : 18854889207
             * name : 患者1
             * num : 001
             * provinceID : 370000
             * regionID : 370901
             * sex : 男
             * updateTime : 1507952263000
             */

            private int age;
            private String brithday;
            private String cityID;
            private long createTime;
            private int degree;
            private int delFlag;
            private int hospitalID;
            private int id;
            private String idCard;
            private String mobile;
            private String name;
            private String num;
            private String provinceID;
            private String regionID;
            private String sex;
            private long updateTime;

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }

            public String getBrithday() {
                return brithday;
            }

            public void setBrithday(String brithday) {
                this.brithday = brithday;
            }

            public String getCityID() {
                return cityID;
            }

            public void setCityID(String cityID) {
                this.cityID = cityID;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public int getDegree() {
                return degree;
            }

            public void setDegree(int degree) {
                this.degree = degree;
            }

            public int getDelFlag() {
                return delFlag;
            }

            public void setDelFlag(int delFlag) {
                this.delFlag = delFlag;
            }

            public int getHospitalID() {
                return hospitalID;
            }

            public void setHospitalID(int hospitalID) {
                this.hospitalID = hospitalID;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getIdCard() {
                return idCard;
            }

            public void setIdCard(String idCard) {
                this.idCard = idCard;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public String getProvinceID() {
                return provinceID;
            }

            public void setProvinceID(String provinceID) {
                this.provinceID = provinceID;
            }

            public String getRegionID() {
                return regionID;
            }

            public void setRegionID(String regionID) {
                this.regionID = regionID;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public long getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(long updateTime) {
                this.updateTime = updateTime;
            }
        }
    }
}
