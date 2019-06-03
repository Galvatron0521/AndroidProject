package com.shdjrmyy.qgw.CompanyProject.FollowUpPage.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/25.
 */

public class FollowBean {

    /**
     * status : 0
     * data : {"pageCount":8,"totalCount":8,"pageNo":0,"list":[{"id":74,"moduleCode":null,"followName":"随访方案2","followDescript":"随访方案2","followMaxNum":0,"followMaxYear":4,"followMaxMonth":4,"followMaxDay":4,"followDayNum":4,"createUser":null,"createTime":1505194837000,"updateUser":null,"updateTime":1505194837000,"delFlag":0,"remark":null},{"id":75,"moduleCode":null,"followName":"随访方案3","followDescript":"随访方案1","followMaxNum":1,"followMaxYear":0,"followMaxMonth":0,"followMaxDay":0,"followDayNum":1,"createUser":null,"createTime":1506042434000,"updateUser":null,"updateTime":1506042434000,"delFlag":0,"remark":null},{"id":76,"moduleCode":null,"followName":"随访方案4","followDescript":"随访方案2","followMaxNum":0,"followMaxYear":4,"followMaxMonth":4,"followMaxDay":4,"followDayNum":4,"createUser":null,"createTime":1506067722000,"updateUser":null,"updateTime":1506067722000,"delFlag":0,"remark":null},{"id":77,"moduleCode":null,"followName":"随访方案5","followDescript":"随访方案12","followMaxNum":1,"followMaxYear":0,"followMaxMonth":0,"followMaxDay":0,"followDayNum":1,"createUser":"1","createTime":1514880204000,"updateUser":null,"updateTime":1514880204000,"delFlag":0,"remark":null},{"id":78,"moduleCode":null,"followName":"随访方案6","followDescript":"随访方案12","followMaxNum":1,"followMaxYear":0,"followMaxMonth":0,"followMaxDay":0,"followDayNum":1,"createUser":"1","createTime":1514880218000,"updateUser":null,"updateTime":1514880218000,"delFlag":0,"remark":null},{"id":79,"moduleCode":null,"followName":"随访方案7","followDescript":"随访方案12","followMaxNum":1,"followMaxYear":0,"followMaxMonth":0,"followMaxDay":0,"followDayNum":1,"createUser":"1","createTime":1515810605000,"updateUser":null,"updateTime":1515810605000,"delFlag":0,"remark":null},{"id":80,"moduleCode":null,"followName":"测试随访方案","followDescript":"测试随访方案","followMaxNum":1,"followMaxYear":0,"followMaxMonth":0,"followMaxDay":0,"followDayNum":11,"createUser":"1","createTime":1515810782000,"updateUser":null,"updateTime":1515813818000,"delFlag":0,"remark":null},{"id":81,"moduleCode":null,"followName":"随访方案1","followDescript":"随访方案122","followMaxNum":1,"followMaxYear":0,"followMaxMonth":0,"followMaxDay":0,"followDayNum":1,"createUser":"1","createTime":1515980432000,"updateUser":null,"updateTime":1515980432000,"delFlag":0,"remark":null}]}
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
         * pageCount : 8
         * totalCount : 8
         * pageNo : 0
         * list : [{"id":74,"moduleCode":null,"followName":"随访方案2","followDescript":"随访方案2","followMaxNum":0,"followMaxYear":4,"followMaxMonth":4,"followMaxDay":4,"followDayNum":4,"createUser":null,"createTime":1505194837000,"updateUser":null,"updateTime":1505194837000,"delFlag":0,"remark":null},{"id":75,"moduleCode":null,"followName":"随访方案3","followDescript":"随访方案1","followMaxNum":1,"followMaxYear":0,"followMaxMonth":0,"followMaxDay":0,"followDayNum":1,"createUser":null,"createTime":1506042434000,"updateUser":null,"updateTime":1506042434000,"delFlag":0,"remark":null},{"id":76,"moduleCode":null,"followName":"随访方案4","followDescript":"随访方案2","followMaxNum":0,"followMaxYear":4,"followMaxMonth":4,"followMaxDay":4,"followDayNum":4,"createUser":null,"createTime":1506067722000,"updateUser":null,"updateTime":1506067722000,"delFlag":0,"remark":null},{"id":77,"moduleCode":null,"followName":"随访方案5","followDescript":"随访方案12","followMaxNum":1,"followMaxYear":0,"followMaxMonth":0,"followMaxDay":0,"followDayNum":1,"createUser":"1","createTime":1514880204000,"updateUser":null,"updateTime":1514880204000,"delFlag":0,"remark":null},{"id":78,"moduleCode":null,"followName":"随访方案6","followDescript":"随访方案12","followMaxNum":1,"followMaxYear":0,"followMaxMonth":0,"followMaxDay":0,"followDayNum":1,"createUser":"1","createTime":1514880218000,"updateUser":null,"updateTime":1514880218000,"delFlag":0,"remark":null},{"id":79,"moduleCode":null,"followName":"随访方案7","followDescript":"随访方案12","followMaxNum":1,"followMaxYear":0,"followMaxMonth":0,"followMaxDay":0,"followDayNum":1,"createUser":"1","createTime":1515810605000,"updateUser":null,"updateTime":1515810605000,"delFlag":0,"remark":null},{"id":80,"moduleCode":null,"followName":"测试随访方案","followDescript":"测试随访方案","followMaxNum":1,"followMaxYear":0,"followMaxMonth":0,"followMaxDay":0,"followDayNum":11,"createUser":"1","createTime":1515810782000,"updateUser":null,"updateTime":1515813818000,"delFlag":0,"remark":null},{"id":81,"moduleCode":null,"followName":"随访方案1","followDescript":"随访方案122","followMaxNum":1,"followMaxYear":0,"followMaxMonth":0,"followMaxDay":0,"followDayNum":1,"createUser":"1","createTime":1515980432000,"updateUser":null,"updateTime":1515980432000,"delFlag":0,"remark":null}]
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
             * id : 74
             * moduleCode : null
             * followName : 随访方案2
             * followDescript : 随访方案2
             * followMaxNum : 0
             * followMaxYear : 4
             * followMaxMonth : 4
             * followMaxDay : 4
             * followDayNum : 4
             * createUser : null
             * createTime : 1505194837000
             * updateUser : null
             * updateTime : 1505194837000
             * delFlag : 0
             * remark : null
             */

            private int id;
            private String moduleCode;
            private String followName;
            private String followDescript;
            private int followMaxNum;
            private int followMaxYear;
            private int followMaxMonth;
            private int followMaxDay;
            private int followDayNum;
            private String createUser;
            private String createTime;
            private String updateUser;
            private String updateTime;
            private int delFlag;
            private String remark;
            private boolean Check;

            public boolean isCheck() {
                return Check;
            }

            public void setCheck(boolean check) {
                Check = check;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public Object getModuleCode() {
                return moduleCode;
            }

            public void setModuleCode(String moduleCode) {
                this.moduleCode = moduleCode;
            }

            public String getFollowName() {
                return followName;
            }

            public void setFollowName(String followName) {
                this.followName = followName;
            }

            public String getFollowDescript() {
                return followDescript;
            }

            public void setFollowDescript(String followDescript) {
                this.followDescript = followDescript;
            }

            public int getFollowMaxNum() {
                return followMaxNum;
            }

            public void setFollowMaxNum(int followMaxNum) {
                this.followMaxNum = followMaxNum;
            }

            public int getFollowMaxYear() {
                return followMaxYear;
            }

            public void setFollowMaxYear(int followMaxYear) {
                this.followMaxYear = followMaxYear;
            }

            public int getFollowMaxMonth() {
                return followMaxMonth;
            }

            public void setFollowMaxMonth(int followMaxMonth) {
                this.followMaxMonth = followMaxMonth;
            }

            public int getFollowMaxDay() {
                return followMaxDay;
            }

            public void setFollowMaxDay(int followMaxDay) {
                this.followMaxDay = followMaxDay;
            }

            public int getFollowDayNum() {
                return followDayNum;
            }

            public void setFollowDayNum(int followDayNum) {
                this.followDayNum = followDayNum;
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
