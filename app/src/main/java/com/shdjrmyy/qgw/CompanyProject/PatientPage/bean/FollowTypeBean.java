package com.shdjrmyy.qgw.CompanyProject.PatientPage.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/4.
 */

public class FollowTypeBean {

    /**
     * status : 0
     * data : {"followVisitList":[{"typeDetailCode":"1","typeCode":"followType","typeDetailName":"电话随访"},{"typeDetailCode":"2","typeCode":"followType","typeDetailName":"短信随访"},{"typeDetailCode":"3","typeCode":"followType","typeDetailName":"邮件随访"},{"typeDetailCode":"4","typeCode":"followType","typeDetailName":"见面随访"}]}
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
        private List<FollowVisitListBean> followVisitList;

        public List<FollowVisitListBean> getFollowVisitList() {
            return followVisitList;
        }

        public void setFollowVisitList(List<FollowVisitListBean> followVisitList) {
            this.followVisitList = followVisitList;
        }

        public static class FollowVisitListBean {
            /**
             * typeDetailCode : 1
             * typeCode : followType
             * typeDetailName : 电话随访
             */

            private String typeDetailCode;
            private String typeCode;
            private String typeDetailName;

            public String getTypeDetailCode() {
                return typeDetailCode;
            }

            public void setTypeDetailCode(String typeDetailCode) {
                this.typeDetailCode = typeDetailCode;
            }

            public String getTypeCode() {
                return typeCode;
            }

            public void setTypeCode(String typeCode) {
                this.typeCode = typeCode;
            }

            public String getTypeDetailName() {
                return typeDetailName;
            }

            public void setTypeDetailName(String typeDetailName) {
                this.typeDetailName = typeDetailName;
            }
        }
    }
}
