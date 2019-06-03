package com.shdjrmyy.qgw.CompanyProject.FollowUpPage;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.Base;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.BaseFragment;
import com.shdjrmyy.qgw.CompanyProject.FollowUpPage.adapter.MyFollowAdapter;
import com.shdjrmyy.qgw.CompanyProject.FollowUpPage.bean.AllFollowBean;
import com.shdjrmyy.qgw.CompanyProject.LoginPage.FollowUpTypeBean;
import com.shdjrmyy.qgw.CompanyProject.PatientPage.activity.FlupDetailsActivity;
import com.shdjrmyy.qgw.CompanyProject.R;
import com.shdjrmyy.qgw.CompanyProject.UtilsFolder.GsonCallBack;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;
import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 16001 on 2017/10/18 0018.
 */

public class MyFollowFragment extends BaseFragment {


    private RecyclerView recycler;
    private LinearLayoutManager layoutManager;
    private MyFollowAdapter myFollowAdapter;

    private int loop = 0;
    private int pageNo = 0;
    private int pageCount = 8;
    private int size;
    private String typeCode;
    private String typeName;
    private List<AllFollowBean.DataBean.ListBean> addList = new ArrayList<>();
    private List<FollowUpTypeBean> followUpTypeBeans = new ArrayList<>();

    private int id;
    private int followID;
    private int patientID;
    private String name;
    private String startTime;
    private String timeFormat;
    private String createTime;
    private String createUser;
    private String descript;
    private String endReason;
    private String endTime;
    private String followMan;
    private String followName;
    private String followType;
    private String updateTime;
    private String updateUser;

    @Override
    protected int setLayout() {
        initType();
        return R.layout.fragemnt_my_follow;
    }

    @Override
    protected void initView(View view) {
        recycler = view.findViewById(R.id.recycler);
        myFollowAdapter = new MyFollowAdapter();
        layoutManager = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(myFollowAdapter);
    }

    private void initType() {
        followUpTypeBeans.clear();
        followUpTypeBeans = DataSupport.findAll(FollowUpTypeBean.class);
    }

    @Override
    protected void initData() {
        loop = 0;
        final List<AllFollowBean.DataBean.ListBean> listBeans = new ArrayList<>();
        OkHttpUtils.post().url(Base.url)
                .addParams("act", "FollowInformationIndex")
                .addParams("data", new insertFollow(Base.getMD5Str(), Base.getTimeSpan(), "0",
                        String.valueOf(pageNo), String.valueOf(pageCount), "0").toJson())
                .build().execute(new GsonCallBack<AllFollowBean>() {

            @Override
            public void onSuccess(AllFollowBean response) throws JSONException {
                size = response.getData().getList().size();
                do {
                    typeCode = followUpTypeBeans.get(loop).getTypeDetailCode();
                    typeName = followUpTypeBeans.get(loop).getTypeDetailName();
                    for (int i = 0; i < response.getData().getList().size(); i++) {
                        AllFollowBean.DataBean.ListBean listBean = new AllFollowBean.DataBean.ListBean();
                        id = response.getData().getList().get(i).getId();
                        followID = response.getData().getList().get(i).getFollowID();
                        patientID = response.getData().getList().get(i).getPatientID();
                        name = response.getData().getList().get(i).getName();
                        startTime = response.getData().getList().get(i).getStartTime();
                        createTime = response.getData().getList().get(i).getCreateTime();
                        createUser = response.getData().getList().get(i).getCreateUser();
                        descript = response.getData().getList().get(i).getDescript();
                        endReason = response.getData().getList().get(i).getEndReason();
                        endTime = response.getData().getList().get(i).getEndTime();
                        followMan = response.getData().getList().get(i).getFollowMan();
                        followName = response.getData().getList().get(i).getFollowName();
                        followType = response.getData().getList().get(i).getFollowType();
                        updateTime = response.getData().getList().get(i).getUpdateTime();
                        updateUser = response.getData().getList().get(i).getUpdateUser();

                        if (typeCode.equals(followType)) {
                            long start = Long.parseLong(startTime);
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
                            timeFormat = dateFormat.format(start);
                            listBean.setName(name);
                            listBean.setFollowType(typeName);
                            listBean.setStartTime(timeFormat);
                            listBean.setId(id);
                            listBean.setPatientID(patientID);
                            listBean.setFollowName(followName);
                            listBean.setEndTime(endTime);
                            listBean.setEndReason(endReason);
                            listBean.setCreateTime(createTime);
                            listBean.setCreateUser(createUser);
                            listBean.setFollowID(followID);
                            listBean.setFollowMan(followMan);
                            listBean.setUpdateTime(updateTime);
                            listBean.setUpdateUser(updateUser);
                            listBean.setDescript(descript);
                            listBeans.add(listBean);
                            addList.add(listBean);
                        }
                    }
                    loop++;
                } while (loop <= 3);
                myFollowAdapter.setNewData(listBeans);
            }

            @Override
            public void onError(Exception e) {

            }
        });
        initLoadMore();
        initClick();
    }


    private void initLoadMore() {
        myFollowAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loop = 0;
                final List<AllFollowBean.DataBean.ListBean> listBeans = new ArrayList<>();
                recycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (!(size < pageCount)) {
                            pageNo = pageNo + size;
                            OkHttpUtils.post().url(Base.url)
                                    .addParams("act", "FollowInformationIndex")
                                    .addParams("data", new insertFollow(Base.getMD5Str(), Base.getTimeSpan(), "0",
                                            String.valueOf(pageNo), String.valueOf(pageCount), "0").toJson())
                                    .build().execute(new GsonCallBack<AllFollowBean>() {
                                @Override
                                public void onSuccess(AllFollowBean response) throws JSONException {
                                    do {
                                        typeCode = followUpTypeBeans.get(loop).getTypeDetailCode();
                                        typeName = followUpTypeBeans.get(loop).getTypeDetailName();
                                        size = response.getData().getList().size();
                                        for (int i = 0; i < response.getData().getList().size(); i++) {
                                            AllFollowBean.DataBean.ListBean listBean = new AllFollowBean.DataBean.ListBean();
                                            id = response.getData().getList().get(i).getId();
                                            followID = response.getData().getList().get(i).getFollowID();
                                            patientID = response.getData().getList().get(i).getPatientID();
                                            name = response.getData().getList().get(i).getName();
                                            startTime = response.getData().getList().get(i).getStartTime();
                                            createTime = response.getData().getList().get(i).getCreateTime();
                                            createUser = response.getData().getList().get(i).getCreateUser();
                                            descript = response.getData().getList().get(i).getDescript();
                                            endReason = response.getData().getList().get(i).getEndReason();
                                            endTime = response.getData().getList().get(i).getEndTime();
                                            followMan = response.getData().getList().get(i).getFollowMan();
                                            followName = response.getData().getList().get(i).getFollowName();
                                            followType = response.getData().getList().get(i).getFollowType();
                                            updateTime = response.getData().getList().get(i).getUpdateTime();
                                            updateUser = response.getData().getList().get(i).getUpdateUser();

                                            if (typeCode.equals(followType)) {
                                                long start = Long.parseLong(startTime);
                                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
                                                startTime = dateFormat.format(start);
                                                listBean.setName(name);
                                                listBean.setFollowType(typeName);
                                                listBean.setStartTime(startTime);
                                                listBean.setId(id);
                                                listBean.setPatientID(patientID);
                                                listBean.setFollowName(followName);
                                                listBean.setEndTime(endTime);
                                                listBean.setEndReason(endReason);
                                                listBean.setCreateTime(createTime);
                                                listBean.setCreateUser(createUser);
                                                listBean.setFollowID(followID);
                                                listBean.setFollowMan(followMan);
                                                listBean.setUpdateTime(updateTime);
                                                listBean.setUpdateUser(updateUser);
                                                listBean.setDescript(descript);
                                                listBeans.add(listBean);
                                                addList.add(listBean);
                                            }
                                        }
                                        loop++;
                                    } while (loop <= 3);
                                    myFollowAdapter.addData(listBeans);
                                    myFollowAdapter.loadMoreComplete();
                                }

                                @Override
                                public void onError(Exception e) {

                                }
                            });
                        } else {
                            myFollowAdapter.loadMoreEnd();
                        }
                    }
                }, 2000);
            }
        }, recycler);

    }

    private void initClick() {
        myFollowAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getContext(), FlupDetailsActivity.class);
                intent.putExtra("Id", addList.get(position).getId());
                intent.putExtra("name", addList.get(position).getName());
                intent.putExtra("patientID", addList.get(position).getPatientID());
                intent.putExtra("followName", addList.get(position).getFollowName());
                intent.putExtra("descript", addList.get(position).getDescript());
                intent.putExtra("startTime", addList.get(position).getStartTime());
                intent.putExtra("endTime", addList.get(position).getEndTime());
                intent.putExtra("endReason", addList.get(position).getEndReason());
                intent.putExtra("createTime", addList.get(position).getCreateTime());
                intent.putExtra("createUser", addList.get(position).getCreateUser());
                intent.putExtra("followID", addList.get(position).getFollowID());
                intent.putExtra("followMan", addList.get(position).getFollowMan());
                intent.putExtra("followType", addList.get(position).getFollowType());
                intent.putExtra("updateTime", addList.get(position).getUpdateTime());
                intent.putExtra("updateUser", addList.get(position).getUpdateUser());
                startActivityForResult(intent, 8);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 8 && resultCode == 9) {
            pageNo = 0;
            pageCount = 8;
            addList.clear();
            initData();
        }
    }

    @Override
    public String getTitle() {
        return "我的随访";
    }

    static class insertFollow {
        private String appKey;
        private String timeSpan;
        private String patientID;
        private String pageNo;
        private String pageCount;
        private String endFlag;


        public insertFollow(String appKey, String timeSpan, String patientID, String pageNo, String pageCount, String endFlag) {

            this.appKey = appKey;
            this.timeSpan = timeSpan;
            this.patientID = patientID;
            this.pageNo = pageNo;
            this.pageCount = pageCount;
            this.endFlag = endFlag;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }
}
