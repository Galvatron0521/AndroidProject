package com.shdjrmyy.qgw.CompanyProject.LoginPage;

/**
 * Created by 16001 on 2017/10/24 0024.
 */

public class LoginResult {



    /**
     * data : {"user":{"UserID":1,"RoleID":9999,"HospitalID":1,"LoginName":"admin","Name":"admin","Sex":1,"National":"1","Brithday":"2016-10-11","Mobile":"18854889207","degree":0,"IDCard":"","PhotoID":0,"PhotoUrl":"","Address":"","Password":"e10adc3949ba59abbe56e057f20f883e","FirstLoginTime":1474273862000,"CreateTime":1472716156000,"UpdateTime":1501117119000,"DelFlag":0,"DelTime":null,"Remark":"333"}}
     * status : 0
     */

    private DataBean data;
    private String status;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class DataBean {
        /**
         * user : {"UserID":1,"RoleID":9999,"HospitalID":1,"LoginName":"admin","Name":"admin","Sex":1,"National":"1","Brithday":"2016-10-11","Mobile":"18854889207","degree":0,"IDCard":"","PhotoID":0,"PhotoUrl":"","Address":"","Password":"e10adc3949ba59abbe56e057f20f883e","FirstLoginTime":1474273862000,"CreateTime":1472716156000,"UpdateTime":1501117119000,"DelFlag":0,"DelTime":null,"Remark":"333"}
         */

        private UserBean user;

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean {
            /**
             * UserID : 1
             * RoleID : 9999
             * HospitalID : 1
             * LoginName : admin
             * Name : admin
             * Sex : 1
             * National : 1
             * Brithday : 2016-10-11
             * Mobile : 18854889207
             * degree : 0
             * IDCard :
             * PhotoID : 0
             * PhotoUrl :
             * Address :
             * Password : e10adc3949ba59abbe56e057f20f883e
             * FirstLoginTime : 1474273862000
             * CreateTime : 1472716156000
             * UpdateTime : 1501117119000
             * DelFlag : 0
             * DelTime : null
             * Remark : 333
             */

            private int UserID;
            private int RoleID;
            private int HospitalID;
            private String LoginName;
            private String Name;
            private int Sex;
            private String National;
            private String Brithday;
            private String Mobile;
            private int degree;
            private String IDCard;
            private int PhotoID;
            private String PhotoUrl;
            private String Address;
            private String Password;
            private long FirstLoginTime;
            private long CreateTime;
            private long UpdateTime;
            private int DelFlag;
            private Object DelTime;
            private String Remark;

            public int getUserID() {
                return UserID;
            }

            public void setUserID(int UserID) {
                this.UserID = UserID;
            }

            public int getRoleID() {
                return RoleID;
            }

            public void setRoleID(int RoleID) {
                this.RoleID = RoleID;
            }

            public int getHospitalID() {
                return HospitalID;
            }

            public void setHospitalID(int HospitalID) {
                this.HospitalID = HospitalID;
            }

            public String getLoginName() {
                return LoginName;
            }

            public void setLoginName(String LoginName) {
                this.LoginName = LoginName;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public int getSex() {
                return Sex;
            }

            public void setSex(int Sex) {
                this.Sex = Sex;
            }

            public String getNational() {
                return National;
            }

            public void setNational(String National) {
                this.National = National;
            }

            public String getBrithday() {
                return Brithday;
            }

            public void setBrithday(String Brithday) {
                this.Brithday = Brithday;
            }

            public String getMobile() {
                return Mobile;
            }

            public void setMobile(String Mobile) {
                this.Mobile = Mobile;
            }

            public int getDegree() {
                return degree;
            }

            public void setDegree(int degree) {
                this.degree = degree;
            }

            public String getIDCard() {
                return IDCard;
            }

            public void setIDCard(String IDCard) {
                this.IDCard = IDCard;
            }

            public int getPhotoID() {
                return PhotoID;
            }

            public void setPhotoID(int PhotoID) {
                this.PhotoID = PhotoID;
            }

            public String getPhotoUrl() {
                return PhotoUrl;
            }

            public void setPhotoUrl(String PhotoUrl) {
                this.PhotoUrl = PhotoUrl;
            }

            public String getAddress() {
                return Address;
            }

            public void setAddress(String Address) {
                this.Address = Address;
            }

            public String getPassword() {
                return Password;
            }

            public void setPassword(String Password) {
                this.Password = Password;
            }

            public long getFirstLoginTime() {
                return FirstLoginTime;
            }

            public void setFirstLoginTime(long FirstLoginTime) {
                this.FirstLoginTime = FirstLoginTime;
            }

            public long getCreateTime() {
                return CreateTime;
            }

            public void setCreateTime(long CreateTime) {
                this.CreateTime = CreateTime;
            }

            public long getUpdateTime() {
                return UpdateTime;
            }

            public void setUpdateTime(long UpdateTime) {
                this.UpdateTime = UpdateTime;
            }

            public int getDelFlag() {
                return DelFlag;
            }

            public void setDelFlag(int DelFlag) {
                this.DelFlag = DelFlag;
            }

            public Object getDelTime() {
                return DelTime;
            }

            public void setDelTime(Object DelTime) {
                this.DelTime = DelTime;
            }

            public String getRemark() {
                return Remark;
            }

            public void setRemark(String Remark) {
                this.Remark = Remark;
            }
        }
    }
}
