package com.shdjrmyy.qgw.CompanyProject.HomePage.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.Base;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.BaseActivity;
import com.shdjrmyy.qgw.CompanyProject.PatientPage.activity.PatientDetailsActivity;
import com.shdjrmyy.qgw.CompanyProject.PatientPage.adapter.PatientListAdapter;
import com.shdjrmyy.qgw.CompanyProject.PatientPage.bean.NewPatientBean;
import com.shdjrmyy.qgw.CompanyProject.PatientPage.bean.ResultBean;
import com.shdjrmyy.qgw.CompanyProject.R;
import com.shdjrmyy.qgw.CompanyProject.UtilsFolder.GsonCallBack;
import com.shdjrmyy.qgw.CompanyProject.ViewFolder.FuncUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivity implements View.OnClickListener, TextWatcher {


    private RecyclerView recyclerView;
    private EditText search_ed;
    private TextView Search;
    private View back;
    private View search_bg;
    private View search_icon;

    private List<NewPatientBean.DataBean.ListBean> totalList = new ArrayList<>();
    private LinearLayoutManager manager;
    private PatientListAdapter patientListAdapter;
    private int pageNo = 0;
    private int pageCount = 8;
    private int size;
    private String patientName;

    private String idCard;
    private String name;
    private int age;
    private String birthday;
    private String sex;
    private String mobile;
    private String num;
    private int id;

    @Override
    public int setView() {
        return R.layout.activity_search;
    }


    @Override
    public void initView() {
        FuncUtil.iniSystemBar(this, R.color.white);
        Search = findViewById(R.id.Search);
        search_ed = findViewById(R.id.search_ed);
        search_bg = findViewById(R.id.search_bg);
        search_icon = findViewById(R.id.search_icon);
        recyclerView = findViewById(R.id.RecyclerView);
        back = findViewById(R.id.back);
        back.setOnClickListener(this);
        patientListAdapter = new PatientListAdapter();
        manager = new LinearLayoutManager(SearchActivity.this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(patientListAdapter);
        Search.setOnClickListener(this);
        search_ed.addTextChangedListener(this);
        initAnimate();
    }

    @Override
    public void setData() {
    }

    private void initLoadMore() {
        patientListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                recyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        final List<NewPatientBean.DataBean.ListBean> PatientList = new ArrayList<>();
                        if (!(size < pageCount)) {
                            pageNo = pageNo + size;
                            OkHttpUtils.post().url(Base.url)
                                    .addParams("act", "patientslist")
                                    .addParams("data", new Patient(Base.getMD5Str(), Base.getTimeSpan(), String.valueOf(pageNo), String.valueOf(pageCount), patientName).toJson())
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
        }, recyclerView);
    }

    private void initClick() {
        patientListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.content:
                        Intent intent = new Intent(SearchActivity.this, PatientDetailsActivity.class);
                        intent.putExtra("id", totalList.get(position).getId());
                        intent.putExtra("idCard", totalList.get(position).getIdCard());
                        intent.putExtra("name", totalList.get(position).getName());
                        intent.putExtra("age", totalList.get(position).getAge());
                        intent.putExtra("brithday", totalList.get(position).getBrithday());
                        intent.putExtra("sex", totalList.get(position).getSex());
                        intent.putExtra("mobile", totalList.get(position).getMobile());
                        intent.putExtra("num", totalList.get(position).getNum());
                        startActivity(intent);
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
                Toast.makeText(SearchActivity.this, data, Toast.LENGTH_SHORT).show();
                pageNo = 0;
                pageCount = 8;
                totalList.clear();
                setData();
            }

            @Override
            public void onError(Exception e) {

            }
        });

    }

    public void initAnimate() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1, 0.9f, 1, 1,
                Animation.RELATIVE_TO_PARENT, 1, Animation.RELATIVE_TO_PARENT, 1);
        scaleAnimation.setDuration(500);
        scaleAnimation.setFillAfter(true);
        search_bg.startAnimation(scaleAnimation);

        TranslateAnimation tan = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.1f,
                Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f
        );
        tan.setDuration(500);
        tan.setFillAfter(true);
        search_ed.startAnimation(tan);
        search_icon.startAnimation(tan);
        tan.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                back.animate().alphaBy(0).alpha(1).setDuration(200).start();
                showJP(search_ed);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.Search:
                patientName = search_ed.getText().toString();
                if (!TextUtils.isEmpty(patientName.trim())) {
                    Log.i("123456", "onClick: " + 15451);
                    final List<NewPatientBean.DataBean.ListBean> PatientList = new ArrayList<>();
                    OkHttpUtils.post().url(Base.url)
                            .addParams("act", "patientslist")
                            .addParams("data", new Patient(Base.getMD5Str(), Base.getTimeSpan(), String.valueOf(pageNo),
                                    String.valueOf(pageCount), patientName).toJson())
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
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        pageNo = 0;
        pageCount = 8;
        totalList.clear();
    }

    static class Patient {
        private String appKey;
        private String timeSpan;
        private String pageNo;
        private String pageCount;
        private String name;


        public Patient(String appKey, String timeSpan, String pageNo, String pageCount, String name) {
            this.appKey = appKey;
            this.timeSpan = timeSpan;
            this.pageNo = pageNo;
            this.pageCount = pageCount;
            this.name = name;
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
