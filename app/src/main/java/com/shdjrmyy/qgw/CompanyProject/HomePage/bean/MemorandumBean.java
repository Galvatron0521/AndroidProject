package com.shdjrmyy.qgw.CompanyProject.HomePage.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/2/10.
 */

public class MemorandumBean {

    /**
     * status : 0
     * data : {"list":[{"calendarid":32,"userid":1,"title":"名称","calendardescribe":"6666","start":1504137600000,"end":1504224000000,"delflag":"0","calendartype":null,"remark2":0,"remark3":0,"remark4":1505800776000},{"calendarid":33,"userid":1,"title":"生日","calendardescribe":"19808080","start":1504051200000,"end":1504137600000,"delflag":"0","calendartype":null,"remark2":0,"remark3":0,"remark4":1505800799000},{"calendarid":34,"userid":1,"title":"明天放假","calendardescribe":"1","start":1506729600000,"end":1506816000000,"delflag":"0","calendartype":null,"remark2":0,"remark3":0,"remark4":1505800837000},{"calendarid":39,"userid":1,"title":"lingzhengqu","calendardescribe":"发个","start":1517184000000,"end":1517270400000,"delflag":"0","calendartype":null,"remark2":0,"remark3":0,"remark4":1517041100000},{"calendarid":41,"userid":1,"title":"测试","calendardescribe":"测试","start":1517072400000,"end":1517113800000,"delflag":"0","calendartype":null,"remark2":0,"remark3":0,"remark4":1517041103000},{"calendarid":44,"userid":1,"title":"册数","calendardescribe":"测试这个好不好使","start":1512000000000,"end":1512086400000,"delflag":"0","calendartype":null,"remark2":0,"remark3":0,"remark4":1514442290000},{"calendarid":45,"userid":1,"title":" 是大是大非","calendardescribe":"是大法师的风骚的","start":1515434400000,"end":1515436200000,"delflag":"0","calendartype":null,"remark2":0,"remark3":0,"remark4":1515376559000}]}
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
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * calendarid : 32
             * userid : 1
             * title : 名称
             * calendardescribe : 6666
             * start : 1504137600000
             * end : 1504224000000
             * delflag : 0
             * calendartype : null
             * remark2 : 0
             * remark3 : 0
             * remark4 : 1505800776000
             */

            private int calendarid;
            private int userid;
            private String title;
            private String calendardescribe;

            public int getCalendarid() {
                return calendarid;
            }

            public void setCalendarid(int calendarid) {
                this.calendarid = calendarid;
            }

            public int getUserid() {
                return userid;
            }

            public void setUserid(int userid) {
                this.userid = userid;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getCalendardescribe() {
                return calendardescribe;
            }

            public void setCalendardescribe(String calendardescribe) {
                this.calendardescribe = calendardescribe;
            }

            public long getStart() {
                return start;
            }

            public void setStart(long start) {
                this.start = start;
            }

            public long getEnd() {
                return end;
            }

            public void setEnd(long end) {
                this.end = end;
            }

            public String getDelflag() {
                return delflag;
            }

            public void setDelflag(String delflag) {
                this.delflag = delflag;
            }

            public String getCalendartype() {
                return calendartype;
            }

            public void setCalendartype(String calendartype) {
                this.calendartype = calendartype;
            }

            public int getRemark2() {
                return remark2;
            }

            public void setRemark2(int remark2) {
                this.remark2 = remark2;
            }

            public int getRemark3() {
                return remark3;
            }

            public void setRemark3(int remark3) {
                this.remark3 = remark3;
            }

            public long getRemark4() {
                return remark4;
            }

            public void setRemark4(long remark4) {
                this.remark4 = remark4;
            }

            private long start;
            private long end;
            private String delflag;
            private String calendartype;
            private int remark2;
            private int remark3;
            private long remark4;
        }
    }
}
