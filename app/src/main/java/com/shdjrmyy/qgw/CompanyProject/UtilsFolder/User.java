package com.shdjrmyy.qgw.CompanyProject.UtilsFolder;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/11/16.
 */

public class User extends DataSupport {
    /**
     * UserID : 1
     * HospitalID : 1
     * LoginName : admin
     * Name : admin
     * Sex : 1
     * National : 1
     * Brithday : 2016-10-11
     * Mobile : 18854889207
     * IDCard :
     * PhotoID : 0
     * PhotoUrl :
     * Address :
     */

    private int UserID;
    private int HospitalID;
    private String LoginName;
    private String Name;
    private int Sex;
    private String National;
    private String Brithday;
    private String Mobile;
    private String IDCard;
    private int PhotoID;
    private String PhotoUrl;
    private String Address;

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
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

}
