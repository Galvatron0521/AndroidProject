package com.shdjrmyy.qgw.CompanyProject.FollowUpPage.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/16.
 */

public class AllFollowBean {

    /**
     * status : 0
     * data : {"pageCount":100,"totalCount":9,"pageNo":0,"list":[{"name":"张沥青","followName":"随访方案1","id":57,"patientID":1,"followID":73,"followType":"1","startTime":1505232000000,"descript":"电话随访11111","endFlag":0,"endTime":null,"endReason":null,"followMan":null,"createUser":null,"createTime":1505194881000,"updateUser":null,"updateTime":1512790610000,"delFlag":0,"remark":null},{"name":"刘东强","followName":"随访方案21","id":60,"patientID":2,"followID":76,"followType":"3","startTime":1506096000000,"descript":"66666","endFlag":0,"endTime":null,"endReason":null,"followMan":null,"createUser":null,"createTime":1506157425000,"updateUser":null,"updateTime":1506157425000,"delFlag":0,"remark":null},{"name":"王尼玛","followName":"随访方案1","id":61,"patientID":23,"followID":73,"followType":"1","startTime":1512144000000,"descript":"77777","endFlag":0,"endTime":1512057600000,"endReason":"123123","followMan":"123","createUser":"1","createTime":1512779415000,"updateUser":null,"updateTime":1512779432000,"delFlag":0,"remark":null},{"name":"王尼玛","followName":"随访方案2","id":62,"patientID":23,"followID":74,"followType":"1","startTime":1512057600000,"descript":"88888","endFlag":0,"endTime":1513008000000,"endReason":"豆腐干","followMan":"豆腐干","createUser":"1","createTime":1512779524000,"updateUser":null,"updateTime":1513933140000,"delFlag":0,"remark":null},{"name":"张沥青","followName":"随访方案2","id":63,"patientID":1,"followID":74,"followType":"1","startTime":1512057600000,"descript":"11111","endFlag":0,"endTime":1512057600000,"endReason":"123123","followMan":"123123","createUser":"1","createTime":1514018262000,"updateUser":null,"updateTime":1514018278000,"delFlag":0,"remark":null},{"name":"张沥青","followName":"随访方案2","id":64,"patientID":1,"followID":74,"followType":"1","startTime":1512057600000,"descript":"22222","endFlag":0,"endTime":1512057600000,"endReason":"123123","followMan":"123123","createUser":"1","createTime":1514162351000,"updateUser":null,"updateTime":1514162909000,"delFlag":0,"remark":null},{"name":"张沥青","followName":"随访方案2","id":65,"patientID":1,"followID":74,"followType":"1","startTime":1512144000000,"descript":"3333","endFlag":0,"endTime":1512144000000,"endReason":"123","followMan":"123","createUser":"1","createTime":1514165162000,"updateUser":null,"updateTime":1514168089000,"delFlag":0,"remark":null},{"name":"邓成功","followName":"随访方案2","id":66,"patientID":46,"followID":74,"followType":"1","startTime":1512057600000,"descript":"123123","endFlag":1,"endTime":1512057600000,"endReason":"12312","followMan":"1","createUser":"1","createTime":1514444093000,"updateUser":null,"updateTime":1514513579000,"delFlag":0,"remark":null},{"name":"张沥青","followName":"随访方案2","id":67,"patientID":1,"followID":74,"followType":"1","startTime":1515945600000,"descript":"测试","endFlag":0,"endTime":null,"endReason":null,"followMan":null,"createUser":"1","createTime":1516001787000,"updateUser":null,"updateTime":1516001787000,"delFlag":0,"remark":null}]}
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
         * pageCount : 100
         * totalCount : 9
         * pageNo : 0
         * list : [{"name":"张沥青","followName":"随访方案1","id":57,"patientID":1,"followID":73,"followType":"1","startTime":1505232000000,"descript":"电话随访11111","endFlag":0,"endTime":null,"endReason":null,"followMan":null,"createUser":null,"createTime":1505194881000,"updateUser":null,"updateTime":1512790610000,"delFlag":0,"remark":null},{"name":"刘东强","followName":"随访方案21","id":60,"patientID":2,"followID":76,"followType":"3","startTime":1506096000000,"descript":"66666","endFlag":0,"endTime":null,"endReason":null,"followMan":null,"createUser":null,"createTime":1506157425000,"updateUser":null,"updateTime":1506157425000,"delFlag":0,"remark":null},{"name":"王尼玛","followName":"随访方案1","id":61,"patientID":23,"followID":73,"followType":"1","startTime":1512144000000,"descript":"77777","endFlag":0,"endTime":1512057600000,"endReason":"123123","followMan":"123","createUser":"1","createTime":1512779415000,"updateUser":null,"updateTime":1512779432000,"delFlag":0,"remark":null},{"name":"王尼玛","followName":"随访方案2","id":62,"patientID":23,"followID":74,"followType":"1","startTime":1512057600000,"descript":"88888","endFlag":0,"endTime":1513008000000,"endReason":"豆腐干","followMan":"豆腐干","createUser":"1","createTime":1512779524000,"updateUser":null,"updateTime":1513933140000,"delFlag":0,"remark":null},{"name":"张沥青","followName":"随访方案2","id":63,"patientID":1,"followID":74,"followType":"1","startTime":1512057600000,"descript":"11111","endFlag":0,"endTime":1512057600000,"endReason":"123123","followMan":"123123","createUser":"1","createTime":1514018262000,"updateUser":null,"updateTime":1514018278000,"delFlag":0,"remark":null},{"name":"张沥青","followName":"随访方案2","id":64,"patientID":1,"followID":74,"followType":"1","startTime":1512057600000,"descript":"22222","endFlag":0,"endTime":1512057600000,"endReason":"123123","followMan":"123123","createUser":"1","createTime":1514162351000,"updateUser":null,"updateTime":1514162909000,"delFlag":0,"remark":null},{"name":"张沥青","followName":"随访方案2","id":65,"patientID":1,"followID":74,"followType":"1","startTime":1512144000000,"descript":"3333","endFlag":0,"endTime":1512144000000,"endReason":"123","followMan":"123","createUser":"1","createTime":1514165162000,"updateUser":null,"updateTime":1514168089000,"delFlag":0,"remark":null},{"name":"邓成功","followName":"随访方案2","id":66,"patientID":46,"followID":74,"followType":"1","startTime":1512057600000,"descript":"123123","endFlag":1,"endTime":1512057600000,"endReason":"12312","followMan":"1","createUser":"1","createTime":1514444093000,"updateUser":null,"updateTime":1514513579000,"delFlag":0,"remark":null},{"name":"张沥青","followName":"随访方案2","id":67,"patientID":1,"followID":74,"followType":"1","startTime":1515945600000,"descript":"测试","endFlag":0,"endTime":null,"endReason":null,"followMan":null,"createUser":"1","createTime":1516001787000,"updateUser":null,"updateTime":1516001787000,"delFlag":0,"remark":null}]
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
             * name : 张沥青
             * followName : 随访方案1
             * id : 57
             * patientID : 1
             * followID : 73
             * followType : 1
             * startTime : 1505232000000
             * descript : 电话随访11111
             * endFlag : 0
             * endTime : null
             * endReason : null
             * followMan : null
             * createUser : null
             * createTime : 1505194881000
             * updateUser : null
             * updateTime : 1512790610000
             * delFlag : 0
             * remark : null
             */

            private String name;
            private String followName;
            private int id;
            private int patientID;
            private int followID;
            private String followType;
            private String startTime;
            private String descript;
            private int endFlag;
            private String endTime;
            private String endReason;
            private String followMan;
            private String createUser;
            private String createTime;
            private String updateUser;
            private String updateTime;
            private int delFlag;
            private String remark;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getFollowName() {
                return followName;
            }

            public void setFollowName(String followName) {
                this.followName = followName;
            }

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

            public int getFollowID() {
                return followID;
            }

            public void setFollowID(int followID) {
                this.followID = followID;
            }

            public String getFollowType() {
                return followType;
            }

            public void setFollowType(String followType) {
                this.followType = followType;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getDescript() {
                return descript;
            }

            public void setDescript(String descript) {
                this.descript = descript;
            }

            public int getEndFlag() {
                return endFlag;
            }

            public void setEndFlag(int endFlag) {
                this.endFlag = endFlag;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public String getEndReason() {
                return endReason;
            }

            public void setEndReason(String endReason) {
                this.endReason = endReason;
            }

            public String getFollowMan() {
                return followMan;
            }

            public void setFollowMan(String followMan) {
                this.followMan = followMan;
            }

            public String getCreateUser() {
                return createUser;
            }

            public void setCreateUser(String createUser) {
                this.createUser = createUser;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getUpdateUser() {
                return updateUser;
            }

            public void setUpdateUser(String updateUser) {
                this.updateUser = updateUser;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public int getDelFlag() {
                return delFlag;
            }

            public void setDelFlag(int delFlag) {
                this.delFlag = delFlag;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }
        }
    }
}
