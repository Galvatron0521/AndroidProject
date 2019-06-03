package com.shdjrmyy.qgw.CompanyProject.PatientPage.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.Base;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.BaseActivity;
import com.shdjrmyy.qgw.CompanyProject.PatientPage.bean.StopBean;
import com.shdjrmyy.qgw.CompanyProject.R;
import com.shdjrmyy.qgw.CompanyProject.UtilsFolder.GsonCallBack;
import com.shdjrmyy.qgw.CompanyProject.ViewFolder.FuncUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;

import okhttp3.Call;

public class StopPatientFollowActivity extends BaseActivity implements View.OnClickListener {

    private EditText flup_endReason;
    private TextView flup_name;
    private TextView flup_followName;
    private TextView flup_followType;

    private int Id;
    private String name;
    private String followName;
    private String followType;
    private String endReason;

    @Override
    public int setView() {
        return R.layout.activity_stop_patient_follow;
    }

    @Override
    public void initView() {
        FuncUtil.iniSystemBar(this, R.color.white);
        initTB("终止患者随访", "提交");
        flup_endReason = findViewById(R.id.flup_endReason);
        flup_name = findViewById(R.id.flup_name);
        flup_followName = findViewById(R.id.flup_followName);
        flup_followType = findViewById(R.id.flup_followType);
        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.other).setOnClickListener(this);
    }

    @Override
    public void setData() {
        Intent intent = getIntent();
        Id = intent.getIntExtra("Id", 0);
        name = intent.getStringExtra("name");
        followName = intent.getStringExtra("followName");
        followType = intent.getStringExtra("followType");

        flup_name.setText(name);
        flup_followName.setText(followName);
        flup_followType.setText(followType);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.other:
                endReason = flup_endReason.getText().toString();
                Log.i("stop", "onClick: " + endReason);
                if (!TextUtils.isEmpty(endReason)) {
                    Log.i("stop", "onClick1111: " + endReason);
                    initStopFollow();
                }
                break;
        }
    }

    private void initStopFollow() {
        OkHttpUtils.post().url(Base.url)
                .addParams("act", "stopFollowRecord")
                .addParams("data", new stopFollowRecord(Base.getMD5Str(), Base.getTimeSpan(), Base.getUserID(), String.valueOf(Id),
                        1, Base.getTimeSpan(), endReason, String.valueOf(Base.getUserID()), Base.getTimeSpan()).toJson())
                .build().execute(new GsonCallBack<StopBean>() {
            @Override
            public void onSuccess(StopBean response) throws JSONException {
                String status = response.getStatus();
                if ("0".equals(status)) {
                    Intent intent = new Intent();
                    setResult(2, intent);
                    finish();
                } else {
                    Toast.makeText(StopPatientFollowActivity.this, "提交失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    static class stopFollowRecord {
        private String appKey;
        private String timeSpan;
        private int UserID;
        private String id;
        private int endFlag;
        private String endTime;
        private String endReason;
        private String followMan;
        private String UpdateTime;

        public stopFollowRecord(String appKey, String timeSpan, int UserID, String id, int endFlag, String endTime,
                                String endReason, String followMan, String UpdateTime) {
            this.appKey = appKey;
            this.timeSpan = timeSpan;
            this.UserID = UserID;
            this.id = id;
            this.endFlag = endFlag;
            this.endTime = endTime;
            this.endReason = endReason;
            this.followMan = followMan;
            this.UpdateTime = UpdateTime;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }
}
