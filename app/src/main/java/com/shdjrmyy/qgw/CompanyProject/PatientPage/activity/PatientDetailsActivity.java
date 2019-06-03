package com.shdjrmyy.qgw.CompanyProject.PatientPage.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.Base;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.BaseActivity;
import com.shdjrmyy.qgw.CompanyProject.PatientPage.bean.HRecordBean;
import com.shdjrmyy.qgw.CompanyProject.PatientPage.bean.PatientBean;
import com.shdjrmyy.qgw.CompanyProject.R;
import com.shdjrmyy.qgw.CompanyProject.UtilsFolder.GsonCallBack;
import com.shdjrmyy.qgw.CompanyProject.UtilsFolder.NetWorkStateReceiver;
import com.shdjrmyy.qgw.CompanyProject.ViewFolder.FuncUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;
import org.litepal.crud.DataSupport;


public class PatientDetailsActivity extends BaseActivity {


    private Toolbar toolbar;
    private ImageView hz_icon;
    private TextView hz_name;
    private TextView hz_old;
    private TextView hz_bq;
    private TextView hz_idCard;
    private TextView hz_xm;
    private TextView hz_age;
    private TextView hz_brithday;
    private TextView hz_sex;
    private TextView hz_mobile;
    private TextView hz_num;

    private String idCard;
    private String name;
    private int age;
    private String brithday;
    private String sex;
    private String mobile;
    private String num;
    private int id;
    private String provinceID;
    private String cityID;
    private String regionID;

    private int id_http;
    private String diagnosis;
    private String diseasesID;
    private String hospitalNum;
    private String inTime;
    private String outTime;
    private int patientID;
    private String patientSay;
    private String treat;
    private String procedure;

    private int totalCount;
    private int size;
    private int pageNo = 0;
    private int pageCount = 8;

    private int DETAIL = 1;
    private int EDIT = 2;

    @Override
    public int setView() {
        return R.layout.activity_patient_details;
    }

    @Override
    public void initView() {
        FuncUtil.iniSystemBar(this, R.color.white);
        toolbar = findViewById(R.id.title_bar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        hz_icon = findViewById(R.id.hz_icon);
        hz_name = findViewById(R.id.hz_name);
        hz_bq = findViewById(R.id.hz_bq);
        hz_old = findViewById(R.id.hz_old);
        hz_idCard = findViewById(R.id.hz_idCard);
        hz_xm = findViewById(R.id.hz_xm);
        hz_age = findViewById(R.id.hz_age);
        hz_brithday = findViewById(R.id.hz_brithday);
        hz_sex = findViewById(R.id.hz_sex);
        hz_mobile = findViewById(R.id.hz_mobile);
        hz_num = findViewById(R.id.hz_num);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.patientmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.option_edit:
                Intent intentEdit = new Intent(this, UpdatePatientActivity.class);
                intentEdit.putExtra("id", id);
                intentEdit.putExtra("idCard", idCard);
                intentEdit.putExtra("name", name);
                intentEdit.putExtra("age", age);
                intentEdit.putExtra("brithday", brithday);
                intentEdit.putExtra("sex", sex);
                intentEdit.putExtra("mobile", mobile);
                intentEdit.putExtra("num", num);
                intentEdit.putExtra("provinceID", provinceID);
                intentEdit.putExtra("cityID", cityID);
                intentEdit.putExtra("regionID", regionID);
                startActivityForResult(intentEdit, EDIT);
                return true;
            case R.id.option_other:
                Intent intentOther = new Intent(this, DetailsActivity.class);
                intentOther.putExtra("id",id);
                startActivityForResult(intentOther, DETAIL);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == DETAIL && resultCode == 4) {
            Intent intent = new Intent();
            setResult(5, intent);
            finish();
        }
        if (requestCode == EDIT && resultCode == 3) {
            Intent intent = new Intent();
            setResult(3, intent);
            finish();
        }
    }

    @Override
    public void setData() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 10000);
        idCard = intent.getStringExtra("idCard");
        name = intent.getStringExtra("name");
        age = intent.getIntExtra("age", 0);
        brithday = intent.getStringExtra("brithday");
        sex = intent.getStringExtra("sex");
        mobile = intent.getStringExtra("mobile");
        num = intent.getStringExtra("num");
        provinceID = intent.getStringExtra("provinceID");
        cityID = intent.getStringExtra("cityID");
        regionID = intent.getStringExtra("regionID");
        if (age <= 16) {
            if (sex.equals("男"))
                hz_icon.setImageResource(R.mipmap.nanx);
            else {
                hz_icon.setImageResource(R.mipmap.nvx);
            }
        } else {
            if (sex.equals("男"))
                hz_icon.setImageResource(R.mipmap.nan);
            else {
                hz_icon.setImageResource(R.mipmap.nv);
            }
        }
        hz_name.setText(name);
        hz_old.setText(String.valueOf(age));
        hz_bq.setText(String.valueOf(id));
        hz_idCard.setText(idCard);
        hz_xm.setText(name);
        hz_age.setText(String.valueOf(age));
        hz_brithday.setText(brithday);
        hz_sex.setText(sex);
        hz_mobile.setText(mobile);
        hz_num.setText(num);
        initHttp();
    }

    private void initHttp() {
        DataSupport.deleteAll(PatientBean.class);
        do {
            OkHttpUtils.post().url(Base.url)
                    .addParams("act", "hospitalRecordList")
                    .addParams("data", (new HRecord(Base.getMD5Str(), Base.getTimeSpan(), String.valueOf(pageNo), String.valueOf(pageCount), id).toJson()))
                    .build().execute(new GsonCallBack<HRecordBean>() {
                @Override
                public void onSuccess(HRecordBean response) throws JSONException {
                    totalCount = response.getData().getTotalCount();
                    size = response.getData().getList().size();
                    if (!(totalCount == 0)) {
                        for (int i = 0; i < size; i++) {
                            PatientBean patientBean = new PatientBean();
                            id_http = response.getData().getList().get(i).getId();
                            diagnosis = response.getData().getList().get(i).getDiagnosis();
                            diseasesID = response.getData().getList().get(i).getDiseasesID();
                            hospitalNum = response.getData().getList().get(i).getHospitalNum();
                            inTime = response.getData().getList().get(i).getInTime();
                            outTime = response.getData().getList().get(i).getOutTime();
                            patientID = response.getData().getList().get(i).getPatientID();
                            patientSay = response.getData().getList().get(i).getPatientSay();
                            treat = response.getData().getList().get(i).getTreat();
                            procedure = response.getData().getList().get(i).getProcedure();

                            patientBean.setId(id_http);
                            patientBean.setDiagnosis(diagnosis);
                            patientBean.setDiseasesID(diseasesID);
                            patientBean.setHospitalNum(hospitalNum);
                            patientBean.setInTime(inTime);
                            patientBean.setOutTime(outTime);
                            patientBean.setPatientID(patientID);
                            patientBean.setPatientSay(patientSay);
                            patientBean.setTreat(treat);
                            patientBean.setProcedure(procedure);
                            patientBean.save();
                        }
                    }
                }

                @Override
                public void onError(Exception e) {

                }
            });
            pageNo = pageNo + pageCount;
        } while (totalCount < size);

    }

    static class HRecord {

        String pageNo;
        String pageCount;
        int id;
        String appKey;
        String timeSpan;


        public HRecord(String appKey, String timeSpan, String pageNo, String pageCount, int id) {
            this.appKey = appKey;
            this.timeSpan = timeSpan;
            this.pageNo = pageNo;
            this.pageCount = pageCount;
            this.id = id;

        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }


}
