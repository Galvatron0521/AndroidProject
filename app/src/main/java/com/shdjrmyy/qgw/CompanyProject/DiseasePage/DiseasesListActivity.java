package com.shdjrmyy.qgw.CompanyProject.DiseasePage;


import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.Base;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.BaseActivity;
import com.shdjrmyy.qgw.CompanyProject.DiseasePage.bean.DiseaseListBean;
import com.shdjrmyy.qgw.CompanyProject.PatientPage.bean.ResultBean;
import com.shdjrmyy.qgw.CompanyProject.R;
import com.shdjrmyy.qgw.CompanyProject.UtilsFolder.GsonCallBack;
import com.shdjrmyy.qgw.CompanyProject.UtilsFolder.NetWorkStateReceiver;
import com.shdjrmyy.qgw.CompanyProject.ViewFolder.FuncUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


public class DiseasesListActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView recycler;
    private ImageView back;
    private TextView other;

    private int pageNo = 0;
    private int pageCount = 100;
    private int size;


    private String id;
    private String name;
    private String descript;
    private String createTime;
    private String updateTime;
    private String createUser;
    private String updateUser;
    private DiseasesListAdapter diseasesListAdapter;
    private LinearLayoutManager manager;
    private List<DiseaseListBean.DataBean.ListBean> addList = new ArrayList<>();

    @Override
    public int setView() {
        return R.layout.activity_diseases_list;
    }


    @Override
    public void initView() {
        FuncUtil.iniSystemBar(this, R.color.white);
        recycler = findViewById(R.id.recycler);
        back = findViewById(R.id.back);
        other = findViewById(R.id.other);
        other.setText("添加");
        back.setOnClickListener(this);
        other.setOnClickListener(this);
        diseasesListAdapter = new DiseasesListAdapter();
        manager = new LinearLayoutManager(DiseasesListActivity.this);
        recycler.setLayoutManager(manager);
        recycler.setAdapter(diseasesListAdapter);
        recycler.addItemDecoration(new DividerItemDecoration(DiseasesListActivity.this, LinearLayoutManager.VERTICAL));
    }

    @Override
    public void setData() {
        final List<DiseaseListBean.DataBean.ListBean> listBeans = new ArrayList<>();
        OkHttpUtils.post().url(Base.url)
                .addParams("act", "DiseasesList")
                .addParams("data", new Disease(Base.getMD5Str(), Base.getTimeSpan(), String.valueOf(pageNo), String.valueOf(pageCount), "SP0401").toJson())
                .build().execute(new GsonCallBack<DiseaseListBean>() {
            @Override
            public void onSuccess(DiseaseListBean response) throws JSONException {
                size = response.getData().getList().size();
                for (int i = 0; i < response.getData().getList().size(); i++) {
                    DiseaseListBean.DataBean.ListBean bzBean = new DiseaseListBean.DataBean.ListBean();
                    id = response.getData().getList().get(i).getId();
                    name = response.getData().getList().get(i).getName();
                    descript = response.getData().getList().get(i).getDescript();
                    createTime = response.getData().getList().get(i).getCreateTime();
                    createUser = response.getData().getList().get(i).getCreateUser();
                    updateTime = response.getData().getList().get(i).getUpdateTime();
                    updateUser = response.getData().getList().get(i).getUpdateUser();
                    bzBean.setId(id);
                    bzBean.setName(name);
                    bzBean.setDescript(descript);
                    bzBean.setCreateTime(createTime);
                    bzBean.setCreateUser(createUser);
                    bzBean.setUpdateTime(updateTime);
                    bzBean.setUpdateUser(updateUser);
                    listBeans.add(bzBean);
                    addList.add(bzBean);
                }
                diseasesListAdapter.setNewData(listBeans);
            }

            @Override
            public void onError(Exception e) {

            }
        });
        initLoadMore();
        initClick();
    }

    private void initLoadMore() {
        diseasesListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                recycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        final List<DiseaseListBean.DataBean.ListBean> listBeans = new ArrayList<>();
                        if (!(size < pageCount)) {
                            pageNo = pageNo + pageCount;
                            OkHttpUtils.post().url(Base.url).addParams("act", "DiseasesList")
                                    .addParams("data", new Disease(Base.getMD5Str(), Base.getTimeSpan(), String.valueOf(pageNo), String.valueOf(pageCount), "SP0401").toJson())
                                    .build().execute(new GsonCallBack<DiseaseListBean>() {
                                @Override
                                public void onSuccess(DiseaseListBean response) throws JSONException {
                                    size = response.getData().getList().size();
                                    for (int i = 0; i < response.getData().getList().size(); i++) {
                                        DiseaseListBean.DataBean.ListBean bzBean = new DiseaseListBean.DataBean.ListBean();
                                        id = response.getData().getList().get(i).getId();
                                        name = response.getData().getList().get(i).getName();
                                        descript = response.getData().getList().get(i).getDescript();
                                        createTime = response.getData().getList().get(i).getCreateTime();
                                        createUser = response.getData().getList().get(i).getCreateUser();
                                        updateTime = response.getData().getList().get(i).getUpdateTime();
                                        updateUser = response.getData().getList().get(i).getUpdateUser();

                                        bzBean.setId(id);
                                        bzBean.setName(name);
                                        bzBean.setDescript(descript);
                                        bzBean.setCreateTime(createTime);
                                        bzBean.setCreateUser(createUser);
                                        bzBean.setUpdateTime(updateTime);
                                        bzBean.setUpdateUser(updateUser);

                                        listBeans.add(bzBean);
                                        addList.add(bzBean);
                                    }
                                    diseasesListAdapter.addData(listBeans);
                                    diseasesListAdapter.loadMoreComplete();
                                }

                                @Override
                                public void onError(Exception e) {

                                }
                            });
                        } else {
                            diseasesListAdapter.loadMoreEnd();
                        }
                    }
                }, 2000);
            }
        }, recycler);
    }

    private void initClick() {
        diseasesListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.content:
                        Intent intent = new Intent(DiseasesListActivity.this, DiseasesDetailActivity.class);
                        intent.putExtra("id", addList.get(position).getId());
                        intent.putExtra("name", addList.get(position).getName());
                        intent.putExtra("descript", addList.get(position).getDescript());
                        intent.putExtra("createTime", addList.get(position).getCreateTime());
                        intent.putExtra("updateTime", addList.get(position).getUpdateTime());
                        intent.putExtra("createUser", addList.get(position).getCreateUser());
                        intent.putExtra("updateUser", addList.get(position).getUpdateUser());
                        startActivityForResult(intent, 2);
                        break;
                    case R.id.right:
                        initDelete(position);
                        diseasesListAdapter.remove(position);
                        diseasesListAdapter.notifyDataSetChanged();
                        break;
                }

            }
        });
    }

    private void initDelete(int position) {
        String dId = addList.get(position).getId();
        OkHttpUtils.post().url(Base.url).addParams("act", "deleteDiseases")
                .addParams("data", new deleteDisease(Base.getMD5Str(), Base.getTimeSpan(), Base.getUserID(), "delete", dId, "1").toJson())
                .build().execute(new GsonCallBack<ResultBean>() {

            @Override
            public void onSuccess(ResultBean response) throws JSONException {
                String data = response.getData().getData();
                Toast.makeText(DiseasesListActivity.this, data, Toast.LENGTH_SHORT).show();
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
                finish();
                break;
            case R.id.other:
                Intent intent = new Intent(DiseasesListActivity.this, AddDiseasesActivity.class);
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
            addList.clear();
            setData();
        }
        if (requestCode == 2 && resultCode == 1) {
            pageNo = 0;
            pageCount = 8;
            addList.clear();
            setData();
        }
    }

    static class Disease {
        private String appKey;
        private String timeSpan;
        private String pageNo;
        private String pageCount;
        private String moduleCode;


        public Disease(String appKey, String timeSpan, String pageNo, String pageCount, String moduleCode) {
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

    static class deleteDisease {
        String optionTag;
        String id;
        int userid;
        String delFlag;
        String appKey;
        String timeSpan;

        public deleteDisease(String appKey, String timeSpan, int userid, String optionTag, String id, String delFlag) {
            this.appKey = appKey;
            this.optionTag = optionTag;
            this.delFlag = delFlag;
            this.id = id;
            this.userid = userid;
            this.timeSpan = timeSpan;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }
}