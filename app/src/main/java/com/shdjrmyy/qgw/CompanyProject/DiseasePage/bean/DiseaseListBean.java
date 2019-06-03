package com.shdjrmyy.qgw.CompanyProject.DiseasePage.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/11.
 */

public class DiseaseListBean {

    /**
     * status : 0
     * data : {"pageCount":100,"totalCount":7,"pageNo":0,"list":[{"id":"1","name":"脑卒中","descript":"脑卒中","createUser":null,"createTime":null,"updateUser":null,"updateTime":1501204381000,"delFlag":0,"remark":null},{"id":"2","name":"胃疼","descript":"吗丁啉专治魏延","createUser":null,"createTime":1509355728000,"updateUser":null,"updateTime":1515464365000,"delFlag":0,"remark":null},{"id":"3","name":"荨麻疹","descript":"荨麻疹俗称风疹块。是由于皮肤、黏膜小血管","createUser":"true","createTime":1607537520000,"updateUser":null,"updateTime":1513328674000,"delFlag":0,"remark":null},{"id":"123213","name":"胃炎","descript":"胃炎（gastritis）是各种原因引起的胃黏膜炎症，为最常见的消化系统疾病之一。按临床发病的缓急，一般可分为急性和慢性胃炎两大类型；按病因不同可分为幽门螺杆菌相关性胃炎、应激性胃炎、自身免疫性胃炎等。不同病因引起的胃炎其病理改变亦不同，通常包括三个过程即上皮损伤、黏膜炎症反应和上皮再生。急性胃炎根据其病理改变又可分为单纯性、糜烂出血性、腐蚀性、化脓性胃炎等，慢性胃炎根据其病理改变可分为非萎缩性、萎缩性和特殊类型胃炎三大类。各型胃炎的诊断和鉴别诊断主要依据胃镜检查。","createUser":null,"createTime":1512174106000,"updateUser":null,"updateTime":1513562950000,"delFlag":0,"remark":null},{"id":"6","name":"骨质增生","descript":"骨质增生症多发于中年以上。一般认为由于中","createUser":"true","createTime":1607537520000,"updateUser":null,"updateTime":1513325744000,"delFlag":0,"remark":null},{"id":"0008","name":"123","descript":"123123","createUser":null,"createTime":1514007411000,"updateUser":null,"updateTime":1514007411000,"delFlag":0,"remark":null},{"id":"7","name":"147","descript":"258369","createUser":"admin123","createTime":1607537520000,"updateUser":null,"updateTime":1515378452000,"delFlag":0,"remark":null}]}
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
         * totalCount : 7
         * pageNo : 0
         * list : [{"id":"1","name":"脑卒中","descript":"脑卒中","createUser":null,"createTime":null,"updateUser":null,"updateTime":1501204381000,"delFlag":0,"remark":null},{"id":"2","name":"胃疼","descript":"吗丁啉专治魏延","createUser":null,"createTime":1509355728000,"updateUser":null,"updateTime":1515464365000,"delFlag":0,"remark":null},{"id":"3","name":"荨麻疹","descript":"荨麻疹俗称风疹块。是由于皮肤、黏膜小血管","createUser":"true","createTime":1607537520000,"updateUser":null,"updateTime":1513328674000,"delFlag":0,"remark":null},{"id":"123213","name":"胃炎","descript":"胃炎（gastritis）是各种原因引起的胃黏膜炎症，为最常见的消化系统疾病之一。按临床发病的缓急，一般可分为急性和慢性胃炎两大类型；按病因不同可分为幽门螺杆菌相关性胃炎、应激性胃炎、自身免疫性胃炎等。不同病因引起的胃炎其病理改变亦不同，通常包括三个过程即上皮损伤、黏膜炎症反应和上皮再生。急性胃炎根据其病理改变又可分为单纯性、糜烂出血性、腐蚀性、化脓性胃炎等，慢性胃炎根据其病理改变可分为非萎缩性、萎缩性和特殊类型胃炎三大类。各型胃炎的诊断和鉴别诊断主要依据胃镜检查。","createUser":null,"createTime":1512174106000,"updateUser":null,"updateTime":1513562950000,"delFlag":0,"remark":null},{"id":"6","name":"骨质增生","descript":"骨质增生症多发于中年以上。一般认为由于中","createUser":"true","createTime":1607537520000,"updateUser":null,"updateTime":1513325744000,"delFlag":0,"remark":null},{"id":"0008","name":"123","descript":"123123","createUser":null,"createTime":1514007411000,"updateUser":null,"updateTime":1514007411000,"delFlag":0,"remark":null},{"id":"7","name":"147","descript":"258369","createUser":"admin123","createTime":1607537520000,"updateUser":null,"updateTime":1515378452000,"delFlag":0,"remark":null}]
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
             * name : 脑卒中
             * descript : 脑卒中
             * createUser : null
             * createTime : null
             * updateUser : null
             * updateTime : 1501204381000
             * delFlag : 0
             * remark : null
             */

            private String id;
            private String name;
            private String descript;
            private String createUser;
            private String createTime;
            private String updateUser;
            private String updateTime;
            private int delFlag;
            private String remark;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDescript() {
                return descript;
            }

            public void setDescript(String descript) {
                this.descript = descript;
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
