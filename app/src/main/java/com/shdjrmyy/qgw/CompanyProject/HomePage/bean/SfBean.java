package com.shdjrmyy.qgw.CompanyProject.HomePage.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 16001 on 2017/10/20 0020.
 * 随访
 */

@Entity
public class SfBean {

    public static final int SF_CALL = 1;
    public static final int SF_MF = 2;

    @Id(autoincrement = true)
    private Long _id;

    long date = 0;
    String title;
    int type = SF_MF;
    long hz;

    public SfBean(long date, String title, int type, long hz) {
        this.date = date;
        this.title = title;
        this.type = type;
        this.hz = hz;
    }

    @Generated(hash = 2110650301)
    public SfBean(Long _id, long date, String title, int type, long hz) {
        this._id = _id;
        this.date = date;
        this.title = title;
        this.type = type;
        this.hz = hz;
    }
    @Generated(hash = 1845538131)
    public SfBean() {
    }
    public Long get_id() {
        return this._id;
    }
    public void set_id(Long _id) {
        this._id = _id;
    }
    public long getDate() {
        return this.date;
    }
    public void setDate(long date) {
        this.date = date;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
   
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public long getHz() {
        return this.hz;
    }
    public void setHz(long hz) {
        this.hz = hz;
    }


}
