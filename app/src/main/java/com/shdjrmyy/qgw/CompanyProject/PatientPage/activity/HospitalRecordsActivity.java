package com.shdjrmyy.qgw.CompanyProject.PatientPage.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.shdjrmyy.qgw.CompanyProject.PatientPage.bean.ResultBean;
import com.shdjrmyy.qgw.CompanyProject.R;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.Base;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.BaseActivity;
import com.shdjrmyy.qgw.CompanyProject.UtilsFolder.GsonCallBack;
import com.shdjrmyy.qgw.CompanyProject.PatientPage.adapter.HRecordAdapter;
import com.shdjrmyy.qgw.CompanyProject.PatientPage.bean.HRecordBean;
import com.shdjrmyy.qgw.CompanyProject.PatientPage.bean.PatientBean;
import com.shdjrmyy.qgw.CompanyProject.UtilsFolder.NetWorkStateReceiver;
import com.shdjrmyy.qgw.CompanyProject.ViewFolder.FuncUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;


public class HospitalRecordsActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView hz_hRecord;

    private HRecordAdapter hRecordAdapter;
    private LinearLayoutManager manager;
    private PatientBean patientBean;
    private List<HRecordBean.DataBean.ListBean> addList = new ArrayList<>();
    private int size;
    private int pageNo = 0;
    private int pageCount = 8;
    private int totalCount;

    private int id;
    private String diagnosis;
    private String diseasesID;
    private String hospitalNum;
    private String inTime;
    private String outTime;
    private int patientID;
    private String patientSay;
    private String treat;
    private String procedure;

    private int id_http;
    private String title;


    @Override
    public int setView() {
        return R.layout.activity_hospital_records;
    }

    @Override
    public void initView() {
        FuncUtil.iniSystemBar(this, R.color.white);
        hz_hRecord = findViewById(R.id.hz_hRecord);
        Intent intent = getIntent();
        id_http = intent.getIntExtra("id", 10000);
        title = intent.getStringExtra("title");
        initTB(title, "添加");
        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.other).setOnClickListener(this);
        hRecordAdapter = new HRecordAdapter();
        manager = new LinearLayoutManager(HospitalRecordsActivity.this);
        hz_hRecord.setLayoutManager(manager);
        hz_hRecord.setAdapter(hRecordAdapter);
        hz_hRecord.addItemDecoration(new DividerItemDecoration(HospitalRecordsActivity.this, LinearLayoutManager.VERTICAL));
    }

    @Override
    public void setData() {
        final List<HRecordBean.DataBean.ListBean> listBeans = new ArrayList<>();
        DataSupport.deleteAll(PatientBean.class);
        OkHttpUtils.post().url(Base.url)
                .addParams("act", "hospitalRecordList")
                .addParams("data", (new HospitalRecords(Base.getMD5Str(), Base.getTimeSpan(), String.valueOf(pageNo), String.valueOf(pageCount), id_http).toJson()))
                .build().execute(new GsonCallBack<HRecordBean>() {
            @Override
            public void onSuccess(final HRecordBean response) throws JSONException {
                size = response.getData().getList().size();
                totalCount = response.getData().getTotalCount();
                if (!(totalCount == 0)) {
                    patientBean = new PatientBean();
                    for (int i = 0; i < size; i++) {
                        HRecordBean.DataBean.ListBean listBean = new HRecordBean.DataBean.ListBean();
                        id = response.getData().getList().get(i).getId();
                        diagnosis = response.getData().getList().get(i).getDiagnosis();
                        diseasesID = response.getData().getList().get(i).getDiseasesID();
                        hospitalNum = response.getData().getList().get(i).getHospitalNum();
                        inTime = response.getData().getList().get(i).getInTime();
                        outTime = response.getData().getList().get(i).getOutTime();
                        patientID = response.getData().getList().get(i).getPatientID();
                        patientSay = response.getData().getList().get(i).getPatientSay();
                        treat = response.getData().getList().get(i).getTreat();
                        procedure = response.getData().getList().get(i).getProcedure();
                        listBean.setId(id);
                        listBean.setDiagnosis(diagnosis);
                        listBean.setDiseasesID(diseasesID);
                        listBean.setHospitalNum(hospitalNum);
                        listBean.setInTime(inTime);
                        listBean.setOutTime(outTime);
                        listBean.setPatientID(patientID);
                        listBean.setPatientSay(patientSay);
                        listBean.setTreat(treat);
                        listBean.setProcedure(procedure);
                        listBeans.add(listBean);
                        addList.add(listBean);

                        patientBean.setId(id);
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
                    hRecordAdapter.setNewData(listBeans);
                }
            }

            @Override
            public void onError(Exception e) {

            }
        });
        initLoadMore();
        initClick();
    }

    private void initLoadMore() {
        hRecordAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                hz_hRecord.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (!(size < pageCount)) {
                            pageNo = pageNo + size;
                            final List<HRecordBean.DataBean.ListBean> listBeans = new ArrayList<>();
                            OkHttpUtils.post().url(Base.url)
                                    .addParams("act", "hospitalRecordList")
                                    .addParams("data", (new HospitalRecords(Base.getMD5Str(), Base.getTimeSpan(), String.valueOf(pageNo), String.valueOf(pageCount), id_http).toJson()))
                                    .build().execute(new GsonCallBack<HRecordBean>() {
                                @Override
                                public void onSuccess(final HRecordBean response) throws JSONException {
                                    size = response.getData().getList().size();
                                    totalCount = response.getData().getTotalCount();
                                    if (!(totalCount == 0)) {
                                        patientBean = new PatientBean();
                                        for (int i = 0; i < size; i++) {
                                            HRecordBean.DataBean.ListBean listBean = new HRecordBean.DataBean.ListBean();
                                            id = response.getData().getList().get(i).getId();
                                            diagnosis = response.getData().getList().get(i).getDiagnosis();
                                            diseasesID = response.getData().getList().get(i).getDiseasesID();
                                            hospitalNum = response.getData().getList().get(i).getHospitalNum();
                                            inTime = response.getData().getList().get(i).getInTime();
                                            outTime = response.getData().getList().get(i).getOutTime();
                                            patientID = response.getData().getList().get(i).getPatientID();
                                            patientSay = response.getData().getList().get(i).getPatientSay();
                                            treat = response.getData().getList().get(i).getTreat();
                                            procedure = response.getData().getList().get(i).getProcedure();
                                            listBean.setId(id);
                                            listBean.setDiagnosis(diagnosis);
                                            listBean.setDiseasesID(diseasesID);
                                            listBean.setHospitalNum(hospitalNum);
                                            listBean.setInTime(inTime);
                                            listBean.setOutTime(outTime);
                                            listBean.setPatientID(patientID);
                                            listBean.setPatientSay(patientSay);
                                            listBean.setTreat(treat);
                                            listBean.setProcedure(procedure);
                                            listBeans.add(listBean);
                                            addList.add(listBean);

                                            patientBean.setId(id);
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
                                        hRecordAdapter.getData().addAll(listBeans);
                                        hRecordAdapter.loadMoreComplete();
                                    }
                                }

                                @Override
                                public void onError(Exception e) {

                                }
                            });
                        } else {
                            hRecordAdapter.loadMoreEnd();
                        }
                    }
                }, 2000);
            }
        }, hz_hRecord);
    }

    private void initClick() {
        hRecordAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.content:
                        Intent intent = new Intent(HospitalRecordsActivity.this, HRecordDetailActivity.class);
                        intent.putExtra("id", addList.get(position).getId());
                        intent.putExtra("diagnosis", addList.get(position).getDiagnosis());
                        intent.putExtra("diseasesID", addList.get(position).getDiseasesID());
                        Log.i("1213223", "onItemChildClick: " + addList.get(position).getDiseasesID());
                        intent.putExtra("hospitalNum", addList.get(position).getHospitalNum());
                        intent.putExtra("inTime", addList.get(position).getInTime());
                        intent.putExtra("outTime", addList.get(position).getOutTime());
                        intent.putExtra("procedure", addList.get(position).getProcedure());
                        intent.putExtra("patientSay", addList.get(position).getPatientSay());
                        intent.putExtra("treat", addList.get(position).getTreat());
                        startActivityForResult(intent, 2);
                        break;
                    case R.id.right:
                        initDelete(position);
                        hRecordAdapter.remove(position);
                        hRecordAdapter.notifyDataSetChanged();
                        break;
                }
            }
        });
    }

    private void initDelete(int position) {
        int deleteID = addList.get(position).getId();
        OkHttpUtils.post().url(Base.url)
                .addParams("act", "deleteHospitalRecord")
                .addParams("data", (new HRDelete(Base.getMD5Str(), Base.getTimeSpan(), Base.getUserID(), String.valueOf(deleteID)).toJson()))
                .build().execute(new GsonCallBack<ResultBean>() {
            @Override
            public void onSuccess(ResultBean response) throws JSONException {
                String data = response.getData().getData();
                Toast.makeText(HospitalRecordsActivity.this, data, Toast.LENGTH_SHORT).show();
                pageNo = 0;
                pageCount = 8;
                addList.clear();
                setData();
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                Intent intentThree= new Intent();
                setResult(3,intentThree);
                finish();
                break;
            case R.id.other:
                Intent intent = new Intent(this, AddHRecordActivity.class);
                intent.putExtra("id", id_http);
                startActivityForResult(intent, 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 0) {
            addList.clear();
            setData();
        }
        if (requestCode == 2 && resultCode == 2) {
            addList.clear();
            setData();
        }
    }

    static class HospitalRecords {

        String pageNo;
        String pageCount;
        int id;
        String appKey;
        String timeSpan;


        public HospitalRecords(String appKey, String timeSpan, String pageNo, String pageCount, int id) {
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

    static class HRDelete {

        int userID;
        String ids;
        String appKey;
        String timeSpan;


        public HRDelete(String appKey, String timeSpan, int userID, String ids) {
            this.appKey = appKey;
            this.timeSpan = timeSpan;
            this.userID = userID;
            this.ids = ids;

        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }

}
