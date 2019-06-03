package com.shdjrmyy.qgw.CompanyProject.FollowUpPage;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.shdjrmyy.qgw.CompanyProject.R;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.Base;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.BaseFragment;
import com.shdjrmyy.qgw.CompanyProject.UtilsFolder.GsonCallBack;
import com.shdjrmyy.qgw.CompanyProject.FollowUpPage.adapter.FollowPlanAdapter;
import com.shdjrmyy.qgw.CompanyProject.FollowUpPage.bean.FollowBean;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 16001 on 2017/10/26 0026.
 */

public class FollowFragment extends BaseFragment implements View.OnClickListener {

    private RecyclerView followList;
    private FloatingActionButton actionButton;
    private int pageNo = 0;
    private int pageCount = 8;
    private int size;

    private int followId;
    private String createTime;
    private String createUser;
    private String updateTime;
    private String updateUser;
    private int followDayNum;
    private String followName;
    private String followDescript;
    private int followMaxDay;
    private int followMaxMonth;
    private int followMaxYear;
    private int followMaxNum;

    private FollowPlanAdapter followPlanAdapter;
    private LinearLayoutManager layoutManager;
    private List<FollowBean.DataBean.ListBean> addList = new ArrayList<>();

    @Override
    protected int setLayout() {
        return R.layout.fragment_follow_plan;
    }

    @Override
    protected void initView(View view) {
        followList = view.findViewById(R.id.followList);
        actionButton = view.findViewById(R.id.ActionButton);
        layoutManager = new LinearLayoutManager(getActivity());
        followPlanAdapter = new FollowPlanAdapter();
        followList.setLayoutManager(layoutManager);
        followList.setAdapter(followPlanAdapter);
        actionButton.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        final List<FollowBean.DataBean.ListBean> listBeans = new ArrayList<>();
        OkHttpUtils.post()
                .url(Base.url)
                .addParams("act", "followList")
                .addParams("data", new FollowList(Base.getMD5Str(), Base.getTimeSpan(), String.valueOf(pageNo), String.valueOf(pageCount), "").toJson())
                .build().execute(new GsonCallBack<FollowBean>() {
            @Override
            public void onSuccess(FollowBean response) throws JSONException {
                size = response.getData().getList().size();
                for (int i = 0; i < response.getData().getList().size(); i++) {
                    FollowBean.DataBean.ListBean bean = new FollowBean.DataBean.ListBean();
                    followId = response.getData().getList().get(i).getId();
                    createTime = response.getData().getList().get(i).getCreateTime();
                    createUser = response.getData().getList().get(i).getCreateUser();
                    updateTime = response.getData().getList().get(i).getUpdateTime();
                    updateUser = response.getData().getList().get(i).getUpdateUser();
                    followDayNum = response.getData().getList().get(i).getFollowDayNum();
                    followName = response.getData().getList().get(i).getFollowName();
                    followDescript = response.getData().getList().get(i).getFollowDescript();
                    followMaxDay = response.getData().getList().get(i).getFollowMaxDay();
                    followMaxMonth = response.getData().getList().get(i).getFollowMaxMonth();
                    followMaxYear = response.getData().getList().get(i).getFollowMaxYear();
                    followMaxNum = response.getData().getList().get(i).getFollowMaxNum();

                    bean.setFollowName(followName);
                    bean.setFollowDescript(followDescript);
                    listBeans.add(bean);
                    addList.add(bean);
                }
                followPlanAdapter.setNewData(listBeans);
            }

            @Override
            public void onError(Exception e) {

            }
        });
        initLoadMore();
        initClick();
    }

    private void initLoadMore() {
        followPlanAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                final List<FollowBean.DataBean.ListBean> listBeans = new ArrayList<>();
                followList.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (!(size < pageCount)) {
                            pageNo = pageNo + size;
                            OkHttpUtils.post()
                                    .url(Base.url)
                                    .addParams("act", "followList")
                                    .addParams("data", new FollowList(Base.getMD5Str(), Base.getTimeSpan(), String.valueOf(pageNo), String.valueOf(pageCount), "").toJson())
                                    .build().execute(new GsonCallBack<FollowBean>() {
                                @Override
                                public void onSuccess(FollowBean response) throws JSONException {
                                    size = response.getData().getList().size();
                                    for (int i = 0; i < response.getData().getList().size(); i++) {
                                        FollowBean.DataBean.ListBean bean = new FollowBean.DataBean.ListBean();
                                        followId = response.getData().getList().get(i).getId();
                                        createTime = response.getData().getList().get(i).getCreateTime();
                                        createUser = response.getData().getList().get(i).getCreateUser();
                                        updateTime = response.getData().getList().get(i).getUpdateTime();
                                        updateUser = response.getData().getList().get(i).getUpdateUser();
                                        followDayNum = response.getData().getList().get(i).getFollowDayNum();
                                        followName = response.getData().getList().get(i).getFollowName();
                                        followDescript = response.getData().getList().get(i).getFollowDescript();
                                        followMaxDay = response.getData().getList().get(i).getFollowMaxDay();
                                        followMaxMonth = response.getData().getList().get(i).getFollowMaxMonth();
                                        followMaxYear = response.getData().getList().get(i).getFollowMaxYear();
                                        followMaxNum = response.getData().getList().get(i).getFollowMaxNum();

                                        bean.setFollowName(followName);
                                        bean.setFollowDescript(followDescript);
                                        listBeans.add(bean);
                                        addList.add(bean);
                                    }
                                    followPlanAdapter.addData(listBeans);
                                    followPlanAdapter.loadMoreComplete();
                                }

                                @Override
                                public void onError(Exception e) {

                                }
                            });

                        } else {
                            followPlanAdapter.loadMoreEnd();
                        }
                    }
                }, 2000);
            }
        }, followList);
    }

    private void initClick() {
        followPlanAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.content:
                        Intent intent = new Intent(getActivity(), FollowDetailActivity.class);
                        intent.putExtra("followId", addList.get(position).getId());
                        intent.putExtra("createTime", addList.get(position).getCreateTime());
                        intent.putExtra("createUser", addList.get(position).getCreateUser());
                        intent.putExtra("updateTime", addList.get(position).getUpdateTime());
                        intent.putExtra("updateUser", addList.get(position).getUpdateUser());
                        intent.putExtra("followDayNum", addList.get(position).getFollowDayNum());
                        intent.putExtra("followName", addList.get(position).getFollowName());
                        intent.putExtra("followDescript", addList.get(position).getFollowDescript());
                        intent.putExtra("followMaxDay", addList.get(position).getFollowMaxDay());
                        intent.putExtra("followMaxMonth", addList.get(position).getFollowMaxMonth());
                        intent.putExtra("followMaxYear", addList.get(position).getFollowMaxYear());
                        intent.putExtra("followMaxNum", addList.get(position).getFollowMaxNum());
                        startActivity(intent);
                        break;
                    case R.id.right:
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ActionButton:
                Intent intent = new Intent(getContext(), AddPlanActivity.class);
                startActivityForResult(intent, 1);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 2) {
            pageNo = 8;
            pageCount = 8;
            addList.clear();
            initData();
        }
    }

    @Override
    public String getTitle() {
        return "随访方案";
    }


    static class FollowList {
        String pageNo;
        String pageCount;
        String appKey;
        String timeSpan;
        String name;


        public FollowList(String appKey, String timeSpan, String pageNo, String pageCount, String name) {
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
}
