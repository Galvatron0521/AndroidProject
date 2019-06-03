package com.shdjrmyy.qgw.CompanyProject.FollowUpPage;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.shdjrmyy.qgw.CompanyProject.PatientPage.bean.ResultBean;
import com.shdjrmyy.qgw.CompanyProject.R;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.Base;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.BaseActivity;
import com.shdjrmyy.qgw.CompanyProject.FollowUpPage.adapter.AddPlanAdapter;
import com.shdjrmyy.qgw.CompanyProject.FollowUpPage.bean.FollowPlan;
import com.shdjrmyy.qgw.CompanyProject.UtilsFolder.GsonCallBack;
import com.shdjrmyy.qgw.CompanyProject.UtilsFolder.NetWorkStateReceiver;
import com.shdjrmyy.qgw.CompanyProject.UtilsFolder.User;
import com.shdjrmyy.qgw.CompanyProject.ViewFolder.FuncUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;


import org.json.JSONException;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

public class AddPlanActivity extends BaseActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

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
    private AddPlanAdapter planAdapter;
    private LinearLayoutManager layoutManager;
    private List<FollowPlan> timeNoList;

    private String userName;
    private String name;
    private String describe;
    private String count;
    private String Advance;
    private String year;
    private String month;
    private String day;
    private List<Map<String, String>> followTimeMap = new ArrayList<>();
    private String yearNum;
    private String monthNum;
    private String dayNum;

    @Override
    public int setView() {
        return R.layout.activity_add_follow;
    }

    @Override
    public void initView() {
        FuncUtil.iniSystemBar(this, R.color.white);
        sf_name = findViewById(R.id.sf_name);
        sf_describe = findViewById(R.id.sf_describe);
        initTB("添加随访方案", "保存");
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
        timeNoList = new ArrayList<>();
        FollowPlan followPlan = new FollowPlan();
        followPlan.setTimeNo(1);
        timeNoList.add(followPlan);
        planAdapter = new AddPlanAdapter(R.layout.item_addplan, timeNoList);
        layoutManager = new LinearLayoutManager(this);
        timeTable.setLayoutManager(layoutManager);
        timeTable.setAdapter(planAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.other:
                initRequest();
                break;
            case R.id.btn_add:
                int addNumber = timeNoList.size() + 1;
                timeNoList.clear();
                for (int i = 0; i < addNumber; i++) {
                    FollowPlan followPlan = new FollowPlan();
                    followPlan.setTimeNo(i + 1);
                    timeNoList.add(followPlan);
                    planAdapter.notifyDataSetChanged();
                }

                break;
            case R.id.btn_remove:
                int cutNumber = timeNoList.size() - 1;
                if (cutNumber >= 0) {
                    timeNoList.remove(cutNumber);
                    planAdapter.notifyDataSetChanged();
                }
                break;
        }
    }

    private void initRequest() {

        List<User> users = DataSupport.findAll(User.class);
        userName = users.get(0).getName();

        String time = Base.getTimeSpan();
        name = String.valueOf(sf_name.getText());
        describe = String.valueOf(sf_describe.getText());
        count = String.valueOf(ed_count.getText());
        Advance = String.valueOf(ed_Advance.getText());
        year = String.valueOf(ed_year.getText());
        month = String.valueOf(ed_month.getText());
        day = String.valueOf(ed_day.getText());

        for (int i = 0; i < planAdapter.getData().size(); i++) {
            View childAt = timeTable.getChildAt(i);
            EditText editYear = childAt.findViewById(R.id.add_year);
            yearNum = editYear.getText().toString();
            EditText editMonth = childAt.findViewById(R.id.add_month);
            monthNum = editMonth.getText().toString();
            EditText editDay = childAt.findViewById(R.id.add_day);
            dayNum = editDay.getText().toString();

            Map<String, String> stringMap = new HashMap();
            stringMap.put("yearNum", yearNum);
            stringMap.put("monthNum", monthNum);
            stringMap.put("dayNum", dayNum);
            followTimeMap.add(stringMap);

        }
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请填写方案名称", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(describe)) {
            Toast.makeText(this, "请填写方案描述", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(Advance)) {
            Toast.makeText(this, "请填写提醒天数", Toast.LENGTH_SHORT).show();
        } else {
            OkHttpUtils.post().url(Base.url).addParams("act", "insertFollow")
                    .addParams("data", new insertFollow(Base.getMD5Str(), Base.getTimeSpan(), count, year, month, day, Advance, name, describe, followTimeMap, time, userName).toJson())
                    .build().execute(new GsonCallBack<ResultBean>() {

                @Override
                public void onSuccess(ResultBean response) throws JSONException {
                    String status = response.getStatus();
                    if ("0".equals(status)) {
                        String data = response.getData().getData();
                        Toast.makeText(AddPlanActivity.this, data, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        setResult(2, intent);
                    } else {
                        String data = response.getData().getData();
                        Toast.makeText(AddPlanActivity.this, data, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onError(Exception e) {

                }
            });
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

    static class insertFollow {
        private String appKey;
        private String timeSpan;
        private String followMaxNum;//最多访问次数
        private String followMaxYear;//最长访问时间年
        private String followMaxMonth;//最长访问时间月
        private String followMaxDay;//最长访问时间日
        private String followDayNum;//提前几天提醒
        private String followName;//随访名称
        private String followDescript;//随访描述
        private String createUser;
        private String createTime;
        private List<Map<String, String>> followTimeMap;


        public insertFollow(String appKey, String timeSpan, String followMaxNum, String followMaxYear, String followMaxMonth,
                            String followMaxDay, String followDayNum, String followName, String followDescript,
                            List<Map<String, String>> followTimeMap, String createTime, String createUser) {
            this.appKey = appKey;
            this.timeSpan = timeSpan;
            this.followMaxNum = followMaxNum;
            this.followMaxYear = followMaxYear;
            this.followMaxMonth = followMaxMonth;
            this.followMaxDay = followMaxDay;
            this.followDayNum = followDayNum;
            this.followName = followName;
            this.followDescript = followDescript;
            this.createTime = createTime;
            this.createUser = createUser;
            this.followTimeMap = followTimeMap;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }
}
