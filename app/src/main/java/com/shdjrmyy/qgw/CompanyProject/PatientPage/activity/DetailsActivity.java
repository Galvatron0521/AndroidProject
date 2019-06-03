package com.shdjrmyy.qgw.CompanyProject.PatientPage.activity;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.Base;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.BaseActivity;
import com.shdjrmyy.qgw.CompanyProject.PatientPage.adapter.LinksAdapter;
import com.shdjrmyy.qgw.CompanyProject.PatientPage.bean.LinkBean;
import com.shdjrmyy.qgw.CompanyProject.PatientPage.bean.PatientBean;
import com.shdjrmyy.qgw.CompanyProject.PatientPage.bean.URLBean;
import com.shdjrmyy.qgw.CompanyProject.R;
import com.shdjrmyy.qgw.CompanyProject.UtilsFolder.GsonCallBack;
import com.shdjrmyy.qgw.CompanyProject.ViewFolder.FuncUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;


public class DetailsActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView detail_recycler;
    private List<PatientBean> patientBeans;
    private List<String> linkLists = new ArrayList<>();
    private List<LinkBean> titleLists = new ArrayList<>();
    private List<String> fixedList = new ArrayList<>();
    private LinksAdapter linksAdapter;
    private LinearLayoutManager layoutManager;

    private int id;

    @Override
    public int setView() {
        return R.layout.activity_details;
    }

    @Override
    public void initView() {
        FuncUtil.iniSystemBar(this, R.color.white);
        initTB("其他资料", " ");
        detail_recycler = findViewById(R.id.detail_recycler);
        findViewById(R.id.other).setVisibility(View.GONE);
        findViewById(R.id.back).setOnClickListener(this);
    }

    @Override
    public void setData() {
        patientBeans = new ArrayList<>();
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        fixedList.add("入院记录");
        fixedList.add("随访");
        fixedList.add("影像资料");
        patientBeans.clear();
        patientBeans = DataSupport.findAll(PatientBean.class);
        OkHttpUtils.post().url(Base.url)
                .addParams("act", "CRFListURL")
                .addParams("userid", String.valueOf(Base.getUserID()))
                .addParams("moduleCode", "SP0201")
                .addParams("data", (new CRFListURL(Base.getMD5Str(), Base.getTimeSpan()).toJson()))
                .build().execute(new GsonCallBack<URLBean>() {
            @Override
            public void onSuccess(URLBean response) throws JSONException {
                for (int i = 0; i < response.getData().getCRFlist().size(); i++) {
                    String CRF = response.getData().getCRFlist().get(i);
                    linkLists.add(CRF);
                }
                for (int i = 0; i < response.getData().getLBlist().size(); i++) {
                    String LB = response.getData().getLBlist().get(i);
                    linkLists.add(LB);
                }
                for (int i = 0; i < linkLists.size(); i++) {
                }
                for (int i = 0; i < linkLists.size() + 3; i++) {
                    if (i >= 3 && !(patientBeans.size() == 0)) {
                        String link = linkLists.get(i - 3);
                        int beginIndex = link.lastIndexOf("=");
                        String title = link.substring(beginIndex + 1, link.length());
                        LinkBean linkBean = new LinkBean();
                        linkBean.setId(i);
                        linkBean.setTitle(title);
                        titleLists.add(linkBean);
                    } else if (i >= 3 && patientBeans.size() == 0) {
                        continue;
                    } else {
                        LinkBean linkBean = new LinkBean();
                        linkBean.setId(i);
                        linkBean.setTitle(fixedList.get(i));
                        titleLists.add(linkBean);
                    }
                }
                initAdapter();
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    private void initAdapter() {
        linksAdapter = new LinksAdapter(R.layout.item_links, titleLists);
        layoutManager = new LinearLayoutManager(this);
        detail_recycler.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        detail_recycler.setLayoutManager(layoutManager);
        detail_recycler.setAdapter(linksAdapter);
        linksAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (titleLists.get(position).getTitle().equals("入院记录")) {
                    Intent HRecord = new Intent(DetailsActivity.this, HospitalRecordsActivity.class);
                    HRecord.putExtra("id", id);
                    HRecord.putExtra("title", titleLists.get(position).getTitle());
                    startActivityForResult(HRecord, 2);
                } else if (titleLists.get(position).getTitle().equals("随访")) {
                    Intent PatientFollow = new Intent(DetailsActivity.this, PatientFollowActivity.class);
                    PatientFollow.putExtra("id", id);
                    PatientFollow.putExtra("title", titleLists.get(position).getTitle());
                    startActivity(PatientFollow);
                } else if (titleLists.get(position).getTitle().equals("影像资料")) {

                } else {
                    Intent intent = new Intent(DetailsActivity.this, OtherInfoActivity.class);
                    intent.putExtra("id", id);
                    intent.putExtra("title", titleLists.get(position).getTitle());
                    intent.putExtra("link", linkLists.get(position - 3));
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && resultCode == 3) {
            Intent intent = new Intent();
            setResult(4,intent);
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }

    static class CRFListURL {

        private String appKey;
        private String timeSpan;

        public CRFListURL(String appKey, String timeSpan) {
            this.appKey = appKey;
            this.timeSpan = timeSpan;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }
}
