package com.shdjrmyy.qgw.CompanyProject.MainPage;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.Base;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.BaseFragment;
import com.shdjrmyy.qgw.CompanyProject.HomePage.activity.SearchActivity;
import com.shdjrmyy.qgw.CompanyProject.PatientPage.bean.ResultBean;
import com.shdjrmyy.qgw.CompanyProject.R;
import com.shdjrmyy.qgw.CompanyProject.UtilsFolder.GsonCallBack;
import com.shdjrmyy.qgw.CompanyProject.PatientPage.activity.AddPatientActivity;
import com.shdjrmyy.qgw.CompanyProject.PatientPage.activity.PatientDetailsActivity;
import com.shdjrmyy.qgw.CompanyProject.PatientPage.adapter.PatientListAdapter;
import com.shdjrmyy.qgw.CompanyProject.PatientPage.bean.NewPatientBean;
import com.shdjrmyy.qgw.CompanyProject.ViewFolder.FuncUtil;
import com.zhy.http.okhttp.OkHttpUtils;


import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 16001 on 2017/10/17 0017.
 */

public class PatientInfoFragment extends BaseFragment implements View.OnClickListener {

    private RecyclerView PatientRecycler;
    private TextView tv_search;

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


    @Override
    protected int setLayout() {
        return R.layout.fragment_patient_info;
    }

    @Override
    protected void initView(View view) {
        FuncUtil.iniSystemBar(getActivity(), R.color.white);
        PatientRecycler = view.findViewById(R.id.PatientRecycler);
        tv_search = view.findViewById(R.id.tv_search);
        view.findViewById(R.id.add).setOnClickListener(this);
        patientListAdapter = new PatientListAdapter();
        manager = new LinearLayoutManager(getActivity());
        PatientRecycler.setLayoutManager(manager);
        PatientRecycler.setAdapter(patientListAdapter);
        tv_search.setOnClickListener(this);
    }

    @Override
    protected void initData() {
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
                    listBean.setAge(age);
                    listBean.setBrithday(birthday);
                    listBean.setId(id);
                    listBean.setIdCard(idCard);
                    listBean.setMobile(mobile);
                    listBean.setName(name);
                    listBean.setNum(num);
                    listBean.setSex(sex);
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
                                        listBean.setAge(age);
                                        listBean.setBrithday(birthday);
                                        listBean.setId(id);
                                        listBean.setIdCard(idCard);
                                        listBean.setMobile(mobile);
                                        listBean.setName(name);
                                        listBean.setNum(num);
                                        listBean.setSex(sex);

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
                        Intent intent = new Intent(getActivity(), PatientDetailsActivity.class);
                        intent.putExtra("id", totalList.get(position).getId());
                        intent.putExtra("idCard", totalList.get(position).getIdCard());
                        intent.putExtra("name", totalList.get(position).getName());
                        intent.putExtra("age", totalList.get(position).getAge());
                        intent.putExtra("brithday", totalList.get(position).getBrithday());
                        intent.putExtra("sex", totalList.get(position).getSex());
                        intent.putExtra("mobile", totalList.get(position).getMobile());
                        intent.putExtra("num", totalList.get(position).getNum());
                        startActivityForResult(intent, 1);
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
                String data = response.getData().getData();
                Toast.makeText(getActivity(), data, Toast.LENGTH_SHORT).show();
                pageNo = 0;
                pageCount = 8;
                totalList.clear();
                initData();
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add:
                Intent intent = new Intent(getContext(), AddPatientActivity.class);
                startActivityForResult(intent, 2);
                break;
            case R.id.tv_search:
                Intent intentSearch = new Intent(getContext(), SearchActivity.class);
                startActivity(intentSearch);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 2) {

        }
        if (requestCode == 2 && resultCode == 0) {
            pageNo = 0;
            pageCount = 8;
            totalList.clear();
            initData();
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

    @Override
    public String getTitle() {
        return null;
    }

}
