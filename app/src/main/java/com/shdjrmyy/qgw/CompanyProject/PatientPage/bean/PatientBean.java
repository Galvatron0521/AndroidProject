package com.shdjrmyy.qgw.CompanyProject.PatientPage.bean;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/11/30.
 */

public class PatientBean extends DataSupport {

    /**
     * id : 1
     * patientID : 1
     * hospitalNum : 001
     * diseasesID : 1
     * inTime : 2017-07-01 13:46:28
     * outTime : 2017-08-01 14:05:00
     * diagnosis : 诊断
     * patientSay : 主诉
     * procedure : 手术
     * treat : 特点
     */

    private int id;
    private int patientID;
    private String hospitalNum;
    private String diseasesID;
    private String inTime;
    private String outTime;
    private String diagnosis;
    private String patientSay;
    private String procedure;
    private String treat;

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

    public String getHospitalNum() {
        return hospitalNum;
    }

    public void setHospitalNum(String hospitalNum) {
        this.hospitalNum = hospitalNum;
    }

    public String getDiseasesID() {
        return diseasesID;
    }

    public void setDiseasesID(String diseasesID) {
        this.diseasesID = diseasesID;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getPatientSay() {
        return patientSay;
    }

    public void setPatientSay(String patientSay) {
        this.patientSay = patientSay;
    }

    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

    public String getTreat() {
        return treat;
    }

    public void setTreat(String treat) {
        this.treat = treat;
    }
}
