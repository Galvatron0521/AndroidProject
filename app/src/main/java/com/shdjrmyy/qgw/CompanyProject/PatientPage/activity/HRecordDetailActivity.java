package com.shdjrmyy.qgw.CompanyProject.PatientPage.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.Base;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.BaseActivity;
import com.shdjrmyy.qgw.CompanyProject.DiseasePage.bean.DiseaseListBean;
import com.shdjrmyy.qgw.CompanyProject.DiseasePage.bean.DiseaseBean;
import com.shdjrmyy.qgw.CompanyProject.R;
import com.shdjrmyy.qgw.CompanyProject.UtilsFolder.GsonCallBack;
import com.shdjrmyy.qgw.CompanyProject.UtilsFolder.NetWorkStateReceiver;
import com.shdjrmyy.qgw.CompanyProject.ViewFolder.FuncUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class HRecordDetailActivity extends BaseActivity implements View.OnClickListener {

    private ImageView back;
    private TextView title;
    private TextView other;
    private TextView hz_hospitalNum;
    private TextView hz_diseases;
    private TextView hz_inTime;
    private TextView hz_outTime;
    private TextView hz_patientSay;
    private TextView hz_diagnosis;
    private TextView hz_procedure;
    private TextView hz_treat;

    private int size;
    private int pageNo = 0;
    private int pageCount = 8;
    private String name;
    private String diseaseId;
    private String tv_diseaseName;
    private List<String> diseaseName = new ArrayList<>();
    private List<DiseaseBean> diseaseList = new ArrayList<>();

    private int id;
    private String diagnosis;
    private String diseasesID;
    private String hospitalNum;
    private String inTime;
    private String outTime;
    private String procedure;
    private String patientSay;
    private String treat;

    @Override
    public int setView() {
        return R.layout.activity_hrecord_detail;
    }

    public void initView() {
        FuncUtil.iniSystemBar(this, R.color.white);
        back = findViewById(R.id.back);
        title = findViewById(R.id.title);
        other = findViewById(R.id.other);
        hz_hospitalNum = findViewById(R.id.hz_hospitalNum);
        hz_diseases = findViewById(R.id.hz_diseases);
        hz_inTime = findViewById(R.id.hz_inTime);
        hz_outTime = findViewById(R.id.hz_outTime);
        hz_patientSay = findViewById(R.id.hz_patientSay);
        hz_diagnosis = findViewById(R.id.hz_diagnosis);
        hz_procedure = findViewById(R.id.hz_procedure);
        hz_treat = findViewById(R.id.hz_treat);
        title.setText("入院详细记录");
        other.setText("编辑");
        back.setOnClickListener(this);
        other.setOnClickListener(this);
    }

    @Override
    public void setData() {
        do {
            diseaseList.clear();
            OkHttpUtils.post().url(Base.url).addParams("act", "DiseasesList")
                    .addParams("data", new Disease(Base.getMD5Str(), Base.getTimeSpan(), "SP0401",
                            String.valueOf(pageNo), String.valueOf(pageCount)).toJson())
                    .build().execute(new GsonCallBack<DiseaseListBean>() {
                @Override
                public void onSuccess(final DiseaseListBean response) throws JSONException {
                    size = response.getData().getTotalCount();
                    for (int i = 0; i < response.getData().getList().size(); i++) {
                        DiseaseBean diseaseBean = new DiseaseBean();
                        diseaseId = response.getData().getList().get(i).getId();
                        name = response.getData().getList().get(i).getName();
                        diseaseBean.setDiseaseNo(diseaseId);
                        diseaseBean.setDiseaseName(name);
                        diseaseList.add(diseaseBean);
                        diseaseName.add(name);
                    }
                    pageCount = size;
                    if (size == pageCount) {
                        initData();
                    }
                }

                @Override
                public void onError(Exception e) {

                }

            });
        } while (size > pageCount);

    }

    private void initData() {
        if (!(diseaseList.size() == 0)) {
            Intent intent = getIntent();
            id = intent.getIntExtra("id", 0);
            diagnosis = intent.getStringExtra("diagnosis");
            diseasesID = intent.getStringExtra("diseasesID");
            hospitalNum = intent.getStringExtra("hospitalNum");
            inTime = intent.getStringExtra("inTime");
            outTime = intent.getStringExtra("outTime");
            procedure = intent.getStringExtra("procedure");
            patientSay = intent.getStringExtra("patientSay");
            treat = intent.getStringExtra("treat");
            for (int i = 0; i < diseaseList.size(); i++) {
                if (diseasesID.equals(diseaseList.get(i).getDiseaseNo())) {
                    tv_diseaseName = diseaseList.get(i).getDiseaseName();
                }
                continue;
            }
            hz_hospitalNum.setText(hospitalNum);
            hz_diseases.setText(tv_diseaseName);
            hz_inTime.setText(inTime);
            hz_outTime.setText(outTime);
            hz_patientSay.setText(patientSay);
            hz_diagnosis.setText(diagnosis);
            hz_procedure.setText(procedure);
            hz_treat.setText(treat);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.other:
                Intent intent = new Intent(this, EditHRecordActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("diagnosis", diagnosis);
                intent.putExtra("diseasesID", tv_diseaseName);
                intent.putExtra("hospitalNum", hospitalNum);
                intent.putExtra("inTime", inTime);
                intent.putExtra("outTime", outTime);
                intent.putExtra("procedure", procedure);
                intent.putExtra("patientSay", patientSay);
                intent.putExtra("treat", treat);
                startActivityForResult(intent, 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 0) {
            Intent intent = new Intent();
            setResult(2, intent);
            finish();
        }
    }

    static class Disease {
        String moduleCode;
        String pageCount;
        String pageNo;
        String appKey;
        String timeSpan;


        public Disease(String appKey, String timeSpan, String moduleCode, String pageNo, String pageCount) {
            this.appKey = appKey;
            this.pageCount = pageCount;
            this.pageNo = pageNo;
            this.timeSpan = timeSpan;
            this.moduleCode = moduleCode;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }
}
