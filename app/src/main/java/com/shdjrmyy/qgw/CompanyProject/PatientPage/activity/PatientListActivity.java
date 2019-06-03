package com.shdjrmyy.qgw.CompanyProject.PatientPage.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.Base;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.BaseActivity;
import com.shdjrmyy.qgw.CompanyProject.PatientPage.adapter.PatientListAdapter;
import com.shdjrmyy.qgw.CompanyProject.PatientPage.bean.NewPatientBean;
import com.shdjrmyy.qgw.CompanyProject.PatientPage.bean.ResultBean;
import com.shdjrmyy.qgw.CompanyProject.R;
import com.shdjrmyy.qgw.CompanyProject.UtilsFolder.GsonCallBack;
import com.shdjrmyy.qgw.CompanyProject.UtilsFolder.NetWorkStateReceiver;
import com.shdjrmyy.qgw.CompanyProject.ViewFolder.FuncUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


public class PatientListActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView PatientRecycler;
    private List<NewPatientBean.DataBean.ListBean> totalList = new ArrayList<>();
    private LinearLayoutManager manager;
    private PatientListAdapter patientListAdapter;
    private int pageNo = 0;
    private int pageCount = 8;
    private int size;

    private String idCard;
    private String name;
    private int age;
    private String birthday;
    private String sex;
    private String mobile;
    private String num;
    private int id;
    private String provinceID;
    private String cityID;
    private String regionID;

    private int ADD = 1;
    private int DETAIL = 2;


    @Override

    public int setView() {
        return R.layout.activity_patient_list;
    }

    @Override
    public void initView() {
        FuncUtil.iniSystemBar(this, R.color.white);
        initTB("患者列表", "添加");
        PatientRecycler = findViewById(R.id.PatientRecycler);
        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.other).setOnClickListener(this);
        patientListAdapter = new PatientListAdapter();
        manager = new LinearLayoutManager(PatientListActivity.this);
        PatientRecycler.setLayoutManager(manager);
        PatientRecycler.setAdapter(patientListAdapter);
    }

    @Override
    public void setData() {
        final List<NewPatientBean.DataBean.ListBean> PatientList = new ArrayList<>();
        OkHttpUtils.post().url(Base.url)
                .addParams("act", "patientslist")
                .addParams("data", new Patient(Base.getMD5Str(), Base.getTimeSpan(), String.valueOf(pageNo), String.valueOf(pageCount)).toJson())
                .build().execute(new GsonCallBack<NewPatientBean>() {
            @Override
            public void onSuccess(final NewPatientBean response) throws JSONException {
                size = response.getData().getList().size();
                for (int i = 0; i < response.getData().getList().size(); i++) {
                    NewPatientBean.DataBean.ListBean listBean = new NewPatientBean.DataBean.ListBean();
                    age = response.getData().getList().get(i).getAge();
                    birthday = response.getData().getList().get(i).getBrithday();
                    id = response.getData().getList().get(i).getId();
                    idCard = response.getData().getList().get(i).getIdCard();
                    mobile = response.getData().getList().get(i).getMobile();
                    name = response.getData().getList().get(i).getName();
                    num = response.getData().getList().get(i).getNum();
                    sex = response.getData().getList().get(i).getSex();
                    provinceID = response.getData().getList().get(i).getProvinceID();
                    cityID = response.getData().getList().get(i).getCityID();
                    regionID = response.getData().getList().get(i).getRegionID();
                    listBean.setAge(age);
                    listBean.setBrithday(birthday);
                    listBean.setId(id);
                    listBean.setIdCard(idCard);
                    listBean.setMobile(mobile);
                    listBean.setName(name);
                    listBean.setNum(num);
                    listBean.setSex(sex);
                    listBean.setProvinceID(provinceID);
                    listBean.setCityID(cityID);
                    listBean.setRegionID(regionID);

                    PatientList.add(listBean);
                    totalList.add(listBean);
                }
                patientListAdapter.setNewData(PatientList);
            }

            @Override
            public void onError(Exception e) {

            }
        });
        initLoadMore();
        initClick();
    }

    private void initLoadMore() {
        patientListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                PatientRecycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        final List<NewPatientBean.DataBean.ListBean> PatientList = new ArrayList<>();
                        if (!(size < pageCount)) {
                            pageNo = pageNo + size;
                            OkHttpUtils.post().url(Base.url)
                                    .addParams("act", "patientslist")
                                    .addParams("data", new Patient(Base.getMD5Str(), Base.getTimeSpan(), String.valueOf(pageNo), String.valueOf(pageCount)).toJson())
                                    .build().execute(new GsonCallBack<NewPatientBean>() {
                                @Override
                                public void onSuccess(final NewPatientBean response) throws JSONException {
                                    size = response.getData().getList().size();
                                    Log.i("page", "onSuccess: " + response.getData().getList().size());
                                    for (int i = 0; i < response.getData().getList().size(); i++) {
                                        NewPatientBean.DataBean.ListBean listBean = new NewPatientBean.DataBean.ListBean();
                                        age = response.getData().getList().get(i).getAge();
                                        birthday = response.getData().getList().get(i).getBrithday();
                                        id = response.getData().getList().get(i).getId();
                                        idCard = response.getData().getList().get(i).getIdCard();
                                        mobile = response.getData().getList().get(i).getMobile();
                                        name = response.getData().getList().get(i).getName();
                                        num = response.getData().getList().get(i).getNum();
                                        sex = response.getData().getList().get(i).getSex();
                                        provinceID = response.getData().getList().get(i).getProvinceID();
                                        cityID = response.getData().getList().get(i).getCityID();
                                        regionID = response.getData().getList().get(i).getRegionID();
                                        listBean.setAge(age);
                                        listBean.setBrithday(birthday);
                                        listBean.setId(id);
                                        listBean.setIdCard(idCard);
                                        listBean.setMobile(mobile);
                                        listBean.setName(name);
                                        listBean.setNum(num);
                                        listBean.setSex(sex);
                                        listBean.setProvinceID(provinceID);
                                        listBean.setCityID(cityID);
                                        listBean.setRegionID(regionID);

                                        PatientList.add(listBean);
                                        totalList.add(listBean);
                                    }
                                    patientListAdapter.addData(PatientList);
                                    patientListAdapter.loadMoreComplete();
                                }

                                @Override
                                public void onError(Exception e) {

                                }
                            });
                        } else {
                            patientListAdapter.loadMoreEnd();
                        }
                    }
                }, 2000);

            }
        }, PatientRecycler);
    }

    private void initClick() {
        patientListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.content:
                        Intent intent = new Intent(PatientListActivity.this, PatientDetailsActivity.class);
                        intent.putExtra("id", totalList.get(position).getId());
                        intent.putExtra("idCard", totalList.get(position).getIdCard());
                        intent.putExtra("name", totalList.get(position).getName());
                        intent.putExtra("age", totalList.get(position).getAge());
                        intent.putExtra("brithday", totalList.get(position).getBrithday());
                        intent.putExtra("sex", totalList.get(position).getSex());
                        intent.putExtra("mobile", totalList.get(position).getMobile());
                        intent.putExtra("num", totalList.get(position).getNum());
                        intent.putExtra("provinceID", totalList.get(position).getProvinceID());
                        intent.putExtra("cityID", totalList.get(position).getCityID());
                        intent.putExtra("regionID", totalList.get(position).getRegionID());
                        startActivityForResult(intent, DETAIL);
                        break;
                    case R.id.right:
                        initDelete(position);
                        patientListAdapter.remove(position);
                        patientListAdapter.notifyDataSetChanged();
                        break;
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD && resultCode == 0) {
            pageNo = 0;
            pageCount = 8;
            totalList.clear();
            setData();
        }
        if (requestCode == DETAIL && resultCode == 3) {
            pageNo = 0;
            pageCount = 8;
            totalList.clear();
            setData();
        }
        if (requestCode == DETAIL && resultCode == 5) {
            pageNo = 0;
            pageCount = 8;
            totalList.clear();
            setData();
        }
    }

    private void initDelete(int pos) {
        int hospitalID = totalList.get(pos).getHospitalID();
        String name = totalList.get(pos).getName();
        String sex = totalList.get(pos).getSex();
        String mobile = totalList.get(pos).getMobile();
        int id = totalList.get(pos).getId();
        String num = totalList.get(pos).getNum();
        String idCard = totalList.get(pos).getIdCard();
        OkHttpUtils.post().url(Base.url).addParams("act", "DeletePatient")
                .addParams("data", new DeletePatient(Base.getMD5Str(), Base.getTimeSpan(), Base.getUserID(), hospitalID, name, sex,
                        mobile, idCard, id, num, 1).toJson())
                .build().execute(new GsonCallBack<ResultBean>() {
            @Override
            public void onSuccess(ResultBean response) throws JSONException {
                String status = response.getStatus();
                if ("0".equals(status)) {
                    String data = response.getData().getData();
                    Toast.makeText(PatientListActivity.this, data, Toast.LENGTH_SHORT).show();
                    pageNo = 0;
                    pageCount = 8;
                    totalList.clear();
                    setData();
                } else {
                    String data = response.getData().getData();
                    Toast.makeText(PatientListActivity.this, data, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.other:
                Intent intent = new Intent(this, AddPatientActivity.class);
                startActivityForResult(intent, ADD);
                break;
        }
    }


    static class Patient {
        private String appKey;
        private String timeSpan;
        private String pageNo;
        private String pageCount;


        public Patient(String appKey, String timeSpan, String pageNo, String pageCount) {
            this.appKey = appKey;
            this.timeSpan = timeSpan;
            this.pageNo = pageNo;
            this.pageCount = pageCount;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }

    static class DeletePatient {
        String appKey;
        String timeSpan;
        int userID;
        int hospitalID;
        String name;
        String sex;
        String mobile;
        String idCard;
        int id;
        int delFlag;
        String num;


        public DeletePatient(String appKey, String timeSpan, int userID, int hospitalID, String name,
                             String sex, String mobile, String idCard, int id, String num, int delFlag) {
            this.appKey = appKey;
            this.timeSpan = timeSpan;
            this.userID = userID;
            this.hospitalID = hospitalID;
            this.name = name;
            this.sex = sex;
            this.mobile = mobile;
            this.idCard = idCard;
            this.id = id;
            this.num = num;
            this.delFlag = delFlag;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }
}
