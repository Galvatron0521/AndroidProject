package com.shdjrmyy.qgw.CompanyProject.HomePage.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 16001 on 2017/10/20 0020.
 * 患者
 */
@Entity
public class HzBean {

    @Id(autoincrement = true)
    private Long _id;

    String name;
    String number;
    String sex;
    String old;
    String telphone;
    long brithday;
    String sheng;
    String shi;
    String qu;
    String idCard;
    String bq;
    @Generated(hash = 1671491804)
    public HzBean(Long _id, String name, String number, String sex, String old,
            String telphone, long brithday, String sheng, String shi, String qu,
            String idCard, String bq) {
        this._id = _id;
        this.name = name;
        this.number = number;
        this.sex = sex;
        this.old = old;
        this.telphone = telphone;
        this.brithday = brithday;
        this.sheng = sheng;
        this.shi = shi;
        this.qu = qu;
        this.idCard = idCard;
        this.bq = bq;
    }
    public HzBean(String name, String number, String sex, String old,
                  String telphone, long brithday, String sheng, String shi, String qu,
                  String idCard,String bq) {
        this.name = name;
        this.number = number;
        this.sex = sex;
        this.old = old;
        this.telphone = telphone;
        this.brithday = brithday;
        this.sheng = sheng;
        this.shi = shi;
        this.qu = qu;
        this.idCard = idCard;
        this.bq = bq;
    }
    @Generated(hash = 198086411)
    public HzBean() {
    }
    public Long get_id() {
        return this._id;
    }
    public void set_id(Long _id) {
        this._id = _id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNumber() {
        return this.number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getOld() {
        return this.old;
    }
    public void setOld(String old) {
        this.old = old;
    }
    public String getTelphone() {
        return this.telphone;
    }
    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }
    public long getBrithday() {
        return this.brithday;
    }
    public void setBrithday(long brithday) {
        this.brithday = brithday;
    }
    public String getSheng() {
        return this.sheng;
    }
    public void setSheng(String sheng) {
        this.sheng = sheng;
    }
    public String getShi() {
        return this.shi;
    }
    public void setShi(String shi) {
        this.shi = shi;
    }
    public String getQu() {
        return this.qu;
    }
    public void setQu(String qu) {
        this.qu = qu;
    }
    public String getIdCard() {
        return this.idCard;
    }
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getBq() {
        return bq;
    }

    public void setBq(String bq) {
        this.bq = bq;
    }
}
