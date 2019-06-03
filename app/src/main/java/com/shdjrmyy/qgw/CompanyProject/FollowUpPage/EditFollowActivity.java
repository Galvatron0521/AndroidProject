package com.shdjrmyy.qgw.CompanyProject.FollowUpPage;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.BaseActivity;
import com.shdjrmyy.qgw.CompanyProject.FollowUpPage.adapter.EditPlanAdapter;
import com.shdjrmyy.qgw.CompanyProject.FollowUpPage.bean.EditPlan;
import com.shdjrmyy.qgw.CompanyProject.R;
import com.shdjrmyy.qgw.CompanyProject.UtilsFolder.NetWorkStateReceiver;
import com.shdjrmyy.qgw.CompanyProject.ViewFolder.FuncUtil;

import java.util.ArrayList;
import java.util.List;

public class EditFollowActivity extends BaseActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {


    private EditText sf_name;
    private EditText sf_describe;
    private EditText ed_count;
    private EditText ed_Advance;
    private EditText ed_year;
    private EditText ed_month;
    private EditText ed_day;
    private TextView sf_add;
    private TextView sf_remove;
    private CheckBox sf_count;
    private CheckBox sf_accessTime;
    private RecyclerView timeTable;

    private LinearLayout Count;
    private LinearLayout AccessTime;
    private EditPlanAdapter editPlanAdapter;
    private LinearLayoutManager layoutManager;
    private List<EditPlan> timeNoList = new ArrayList<>();

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

    @Override
    public int setView() {
        return R.layout.activity_edit_follow;
    }

    public void initView() {
        FuncUtil.iniSystemBar(this, R.color.white);
        initTB("编辑随访方式", "保存");
        sf_name = findViewById(R.id.sf_name);
        sf_describe = findViewById(R.id.sf_describe);
        sf_name = findViewById(R.id.sf_name);
        sf_describe = findViewById(R.id.sf_describe);
        ed_count = findViewById(R.id.ed_count);
        ed_year = findViewById(R.id.ed_year);
        ed_month = findViewById(R.id.ed_month);
        ed_day = findViewById(R.id.ed_day);
        ed_Advance = findViewById(R.id.ed_Advance);
        sf_add = findViewById(R.id.btn_add);
        sf_remove = findViewById(R.id.btn_remove);
        sf_count = findViewById(R.id.sf_count);
        sf_accessTime = findViewById(R.id.sf_accessTime);
        timeTable = findViewById(R.id.timeTable);
        Count = findViewById(R.id.Count);
        AccessTime = findViewById(R.id.AccessTime);
        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.other).setOnClickListener(this);
        sf_add.setOnClickListener(this);
        sf_remove.setOnClickListener(this);
        sf_count.setOnCheckedChangeListener(this);
        sf_accessTime.setOnCheckedChangeListener(this);
    }

    @Override
    public void setData() {
        Intent intent = getIntent();
        followId = intent.getIntExtra("followId", 10000);
        createTime = intent.getStringExtra("createTime");
        createUser = intent.getStringExtra("createUser");
        updateTime = intent.getStringExtra("updateTime");
        updateUser = intent.getStringExtra("updateUser");
        followDayNum = intent.getIntExtra("followDayNum", 0);
        followName = intent.getStringExtra("followName");
        followDescript = intent.getStringExtra("followDescript");
        followMaxDay = intent.getIntExtra("followMaxDay", 0);
        followMaxMonth = intent.getIntExtra("followMaxMonth", 0);
        followMaxYear = intent.getIntExtra("followMaxYear", 0);
        followMaxNum = intent.getIntExtra("followMaxNum", 0);

        sf_name.setText(followName);
        sf_describe.setText(followDescript);
        ed_count.setText(String.valueOf(followDayNum));

        initData();
    }

    private void initData() {
        EditPlan editPlan = new EditPlan();
        editPlan.setTimeNo(1);
        editPlan.setFollowMaxYear(followMaxYear);
        editPlan.setFollowMaxMonth(followMaxMonth);
        editPlan.setFollowMaxDay(followMaxDay);
        timeNoList.add(editPlan);
        editPlanAdapter = new EditPlanAdapter(R.layout.item_time_interval, timeNoList);
        layoutManager = new LinearLayoutManager(this);
        timeTable.setLayoutManager(layoutManager);
        timeTable.setAdapter(editPlanAdapter);

//        for (int i = 0; i < editPlanAdapter.getData().size(); i++) {
//            View childAt = timeTable.getChildAt(i);
//            EditText editYear = childAt.findViewById(R.id.add_year);
//            editYear.setText(followMaxYear);
//            EditText editMonth = childAt.findViewById(R.id.add_month);
//            editMonth.setText(followMaxMonth);
//            EditText editDay = childAt.findViewById(R.id.add_day);
//            editDay.setText(followMaxDay);
//        }

    }

//    private void initEdit() {
//        OkHttpUtils.post().url(Base.url).addParams("act", "updateFollow")
//                .addParams("data", new editFollow(Base.getMD5Str(), Base.getTimeSpan(), ).toJson())
//                .build().execute(new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e, int id) {
//
//            }
//
//            @Override
//            public void onResponse(String response, int id) {
//
//            }
//        });
//
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.other:
//                initEdit();
                break;
            case R.id.btn_add:
                int addNumber = timeNoList.size() + 1;
                timeNoList.clear();
                for (int i = 0; i < addNumber; i++) {
                    EditPlan editPlan = new EditPlan();
                    editPlan.setTimeNo(i + 1);
                    timeNoList.add(editPlan);
                    editPlanAdapter.notifyDataSetChanged();
                }

                break;
            case R.id.btn_remove:
                int cutNumber = timeNoList.size() - 1;
                if (cutNumber >= 0) {
                    timeNoList.remove(cutNumber);
                    editPlanAdapter.notifyDataSetChanged();
                }
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.sf_count:
                if (isChecked) {
                    AccessTime.setVisibility(View.GONE);
                } else {
                    AccessTime.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.sf_accessTime:
                if (isChecked) {
                    Count.setVisibility(View.GONE);
                } else {
                    Count.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    static class editFollow {

        private String appKey;
        private String timeSpan;
        private int userid;
        private int id;
        private String updateTime;
        private String updateUser;
        private String followDayNum;
        private String followName;
        private String followDescript;
        private String followMaxDay;
        private String followMaxMonth;
        private String followMaxYear;
        private String followMaxNum;

        public editFollow(String appKey, String timeSpan, int userid, int id, String updateTime,
                          String updateUser, String followDayNum, String followName, String followDescript,
                          String followMaxDay, String followMaxMonth, String followMaxYear, String followMaxNum) {
            this.appKey = appKey;
            this.timeSpan = timeSpan;
            this.userid = userid;
            this.id = id;
            this.updateTime = updateTime;
            this.updateUser = updateUser;
            this.followDayNum = followDayNum;
            this.followName = followName;
            this.followDescript = followDescript;
            this.followMaxDay = followMaxDay;
            this.followMaxMonth = followMaxMonth;
            this.followMaxYear = followMaxYear;
            this.followMaxNum = followMaxNum;

        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }

}
