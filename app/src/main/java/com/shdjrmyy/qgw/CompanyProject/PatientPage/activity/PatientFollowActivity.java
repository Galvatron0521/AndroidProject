package com.shdjrmyy.qgw.CompanyProject.PatientPage.activity;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.Base;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.BaseActivity;
import com.shdjrmyy.qgw.CompanyProject.LoginPage.FollowUpTypeBean;
import com.shdjrmyy.qgw.CompanyProject.PatientPage.adapter.FollowResultAdapter;
import com.shdjrmyy.qgw.CompanyProject.PatientPage.bean.FollowResultBean;
import com.shdjrmyy.qgw.CompanyProject.R;
import com.shdjrmyy.qgw.CompanyProject.UtilsFolder.GsonCallBack;
import com.shdjrmyy.qgw.CompanyProject.ViewFolder.FuncUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;


public class PatientFollowActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private int ID;
    private int loop = 0;
    private FollowResultAdapter resultAdapter;
    private LinearLayoutManager layoutManager;
    private List<FollowUpTypeBean> upTypeBeans = new ArrayList<>();
    private List<FollowResultBean.DataBean.ListBean> totalList = new ArrayList<>();

    private int id;
    private String name;
    private int patientID;
    private String followName;
    private String descript;
    private String startTime;
    private String endTime;
    private String endReason;
    private String createTime;
    private String createUser;
    private int followID;
    private String followMan;
    private String followType;
    private String updateTime;
    private String updateUser;

    private int size;
    private int pageNo = 0;
    private int pageCount = 8;
    private String typeCode;
    private String typeName;

    @Override
    public int setView() {
        return R.layout.activity_follow;
    }

    @Override
    public void initView() {
        FuncUtil.iniSystemBar(this, R.color.white);
        initTB("随访记录", "添加");
        recyclerView = findViewById(R.id.follow_recyler);
        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.other).setOnClickListener(this);
        Intent intent = getIntent();
        ID = intent.getIntExtra("id", 0);
        Log.i("123456", "initView: " + ID);
        resultAdapter = new FollowResultAdapter();
        layoutManager = new LinearLayoutManager(PatientFollowActivity.this);
        recyclerView.addItemDecoration(new DividerItemDecoration(PatientFollowActivity.this, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(resultAdapter);

    }

    @Override
    public void setData() {
        loop = 0;
        final List<FollowResultBean.DataBean.ListBean> listBeans = new ArrayList<>();
        OkHttpUtils.post().url(Base.url).addParams("act", "FollowInformationIndex")
                .addParams("data", new followInfo(Base.getMD5Str(), Base.getTimeSpan(), ID, 0, String.valueOf(pageNo), String.valueOf(pageCount)).toJson())
                .build().execute(new GsonCallBack<FollowResultBean>() {
            @Override
            public void onSuccess(final FollowResultBean response) throws JSONException {
                do {
                    upTypeBeans.clear();
                    upTypeBeans = DataSupport.findAll(FollowUpTypeBean.class);
                    typeCode = upTypeBeans.get(loop).getTypeDetailCode();
                    typeName = upTypeBeans.get(loop).getTypeDetailName();
                    size = response.getData().getList().size();
                    for (int i = 0; i < response.getData().getList().size(); i++) {
                        FollowResultBean.DataBean.ListBean listBean = new FollowResultBean.DataBean.ListBean();
                        id = response.getData().getList().get(i).getId();
                        name = response.getData().getList().get(i).getName();
                        patientID = response.getData().getList().get(i).getPatientID();
                        followName = response.getData().getList().get(i).getFollowName();
                        descript = response.getData().getList().get(i).getDescript();
                        startTime = response.getData().getList().get(i).getStartTime();
                        endTime = String.valueOf(response.getData().getList().get(i).getEndTime());
                        endReason = String.valueOf(response.getData().getList().get(i).getEndReason());
                        createTime = response.getData().getList().get(i).getCreateTime();
                        createUser = String.valueOf(response.getData().getList().get(i).getCreateUser());
                        followID = response.getData().getList().get(i).getFollowID();
                        followMan = String.valueOf(response.getData().getList().get(i).getFollowMan());
                        followType = response.getData().getList().get(i).getFollowType();
                        updateTime = response.getData().getList().get(i).getUpdateTime();
                        updateUser = String.valueOf(response.getData().getList().get(i).getUpdateUser());

                        if (typeCode.equals(followType)) {
                            listBean.setId(id);
                            listBean.setName(name);
                            listBean.setPatientID(patientID);
                            listBean.setFollowName(followName);
                            listBean.setStartTime(startTime);
                            listBean.setEndTime(endTime);
                            listBean.setEndReason(endReason);
                            listBean.setCreateTime(createTime);
                            listBean.setCreateUser(createUser);
                            listBean.setFollowID(followID);
                            listBean.setFollowMan(followMan);
                            listBean.setUpdateTime(updateTime);
                            listBean.setUpdateUser(updateUser);
                            listBean.setFollowType(typeName);
                            listBean.setDescript(descript);
                            listBeans.add(listBean);
                            totalList.add(listBean);
                        }
                    }
                    loop++;
                } while (loop <= 3);
                resultAdapter.setNewData(listBeans);
            }

            @Override
            public void onError(Exception e) {

            }
        });
        initLoadMore();
        initClick();
    }

    private void initLoadMore() {
        resultAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                recyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loop = 0;
                        if (!(size < pageCount)) {
                            pageNo = pageNo + size;
                            final List<FollowResultBean.DataBean.ListBean> listBeans = new ArrayList<>();
                            OkHttpUtils.post().url(Base.url).addParams("act", "FollowInformationIndex")
                                    .addParams("data", new followInfo(Base.getMD5Str(), Base.getTimeSpan(), ID, 0, String.valueOf(pageNo), String.valueOf(pageCount)).toJson())
                                    .build().execute(new GsonCallBack<FollowResultBean>() {
                                @Override
                                public void onSuccess(final FollowResultBean response) throws JSONException {
                                    upTypeBeans = DataSupport.findAll(FollowUpTypeBean.class);
                                    do {
                                        typeCode = upTypeBeans.get(loop).getTypeDetailCode();
                                        typeName = upTypeBeans.get(loop).getTypeDetailName();
                                        size = response.getData().getList().size();
                                        for (int i = 0; i < response.getData().getList().size(); i++) {
                                            FollowResultBean.DataBean.ListBean listBean = new FollowResultBean.DataBean.ListBean();
                                            id = response.getData().getList().get(i).getId();
                                            name = response.getData().getList().get(i).getName();
                                            patientID = response.getData().getList().get(i).getPatientID();
                                            followName = response.getData().getList().get(i).getFollowName();
                                            descript = response.getData().getList().get(i).getDescript();
                                            startTime = response.getData().getList().get(i).getStartTime();
                                            endTime = String.valueOf(response.getData().getList().get(i).getEndTime());
                                            endReason = String.valueOf(response.getData().getList().get(i).getEndReason());
                                            createTime = response.getData().getList().get(i).getCreateTime();
                                            createUser = String.valueOf(response.getData().getList().get(i).getCreateUser());
                                            followID = response.getData().getList().get(i).getFollowID();
                                            followMan = String.valueOf(response.getData().getList().get(i).getFollowMan());
                                            followType = response.getData().getList().get(i).getFollowType();
                                            updateTime = response.getData().getList().get(i).getUpdateTime();
                                            updateUser = String.valueOf(response.getData().getList().get(i).getUpdateUser());
                                            if (typeCode.equals(followType)) {
                                                listBean.setId(id);
                                                listBean.setName(name);
                                                listBean.setPatientID(patientID);
                                                listBean.setFollowName(followName);
                                                listBean.setStartTime(startTime);
                                                listBean.setEndTime(endTime);
                                                listBean.setEndReason(endReason);
                                                listBean.setCreateTime(createTime);
                                                listBean.setCreateUser(createUser);
                                                listBean.setFollowID(followID);
                                                listBean.setFollowMan(followMan);
                                                listBean.setUpdateTime(updateTime);
                                                listBean.setUpdateUser(updateUser);
                                                listBean.setFollowType(typeName);
                                                listBean.setDescript(descript);
                                                listBeans.add(listBean);
                                                totalList.add(listBean);
                                            }
                                        }
                                        loop++;
                                    } while (loop <= 3);

                                    resultAdapter.addData(listBeans);
                                    resultAdapter.loadMoreComplete();
                                }

                                @Override
                                public void onError(Exception e) {
                                }
                            });
                        } else {
                            resultAdapter.loadMoreEnd();
                        }
                    }
                }, 2000);
            }
        }, recyclerView);
    }

    private void initClick() {
        resultAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(PatientFollowActivity.this, FlupDetailsActivity.class);
                intent.putExtra("Id", totalList.get(position).getId());
                intent.putExtra("name", totalList.get(position).getName());
                intent.putExtra("patientID", ID);
                intent.putExtra("followName", totalList.get(position).getFollowName());
                intent.putExtra("descript", totalList.get(position).getDescript());
                intent.putExtra("startTime", totalList.get(position).getStartTime());
                intent.putExtra("endTime", totalList.get(position).getEndTime());
                intent.putExtra("endReason", totalList.get(position).getEndReason());
                intent.putExtra("createTime", totalList.get(position).getCreateTime());
                intent.putExtra("createUser", totalList.get(position).getCreateUser());
                intent.putExtra("followID", totalList.get(position).getFollowID());
                intent.putExtra("followMan", totalList.get(position).getFollowMan());
                intent.putExtra("followType", totalList.get(position).getFollowType());
                intent.putExtra("updateTime", totalList.get(position).getUpdateTime());
                intent.putExtra("updateUser", totalList.get(position).getUpdateUser());
                Log.i("stop", "onItemClick: " + totalList.get(position).getEndReason() + totalList.get(position).getEndTime());
                startActivityForResult(intent, 2);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.other:
                Intent intent = new Intent(PatientFollowActivity.this, AddPatientFollowActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("patientID", ID);
                startActivityForResult(intent, 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 0) {
            pageNo = 0;
            pageCount = 8;
            totalList.clear();
            setData();
        }
        if (requestCode == 2 && resultCode == 5) {
            pageNo = 0;
            pageCount = 8;
            totalList.clear();
            setData();
        }
    }

    static class followInfo {
        String appKey;
        String timeSpan;
        int patientID;
        int endFlag;
        String pageNo;
        String pageCount;


        public followInfo(String appKey, String timeSpan, int patientID, int endFlag, String pageNo, String pageCount) {
            this.appKey = appKey;
            this.timeSpan = timeSpan;
            this.patientID = patientID;
            this.endFlag = endFlag;
            this.pageNo = pageNo;
            this.pageCount = pageCount;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }
}