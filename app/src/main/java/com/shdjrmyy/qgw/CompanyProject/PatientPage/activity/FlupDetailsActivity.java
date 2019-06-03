package com.shdjrmyy.qgw.CompanyProject.PatientPage.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shdjrmyy.qgw.CompanyProject.BaseFolder.BaseActivity;
import com.shdjrmyy.qgw.CompanyProject.R;
import com.shdjrmyy.qgw.CompanyProject.ViewFolder.FuncUtil;

import java.text.SimpleDateFormat;

public class FlupDetailsActivity extends BaseActivity implements View.OnClickListener {

    private TextView flup_name;
    private TextView flup_followName;
    private TextView flup_followType;
    private TextView flup_followMan;
    private TextView flup_startTime;
    private TextView flup_createUser;
    private TextView flup_createTime;
    private TextView flup_updateUser;
    private TextView flup_updateTime;
    private TextView flup_descript;
    private TextView flup_endTime;
    private TextView flup_endReason;
    private LinearLayout FollowMan;
    private LinearLayout CreateUser;
    private LinearLayout UpdateUser;
    private LinearLayout UpdateTime;
    private LinearLayout EndTime;
    private LinearLayout EndReason;

    private int Id;
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

    @Override
    public int setView() {
        return R.layout.activity_flup_details;
    }

    @Override
    public void initView() {
        FuncUtil.iniSystemBar(this, R.color.white);
        initTB("患者随访详情", "终止");
        flup_name = findViewById(R.id.flup_name);
        flup_followName = findViewById(R.id.flup_followName);
        flup_followType = findViewById(R.id.flup_followType);
        flup_followMan = findViewById(R.id.flup_followMan);
        flup_startTime = findViewById(R.id.flup_startTime);
        flup_createUser = findViewById(R.id.flup_createUser);
        flup_createTime = findViewById(R.id.flup_createTime);
        flup_updateUser = findViewById(R.id.flup_updateUser);
        flup_updateTime = findViewById(R.id.flup_updateTime);
        flup_descript = findViewById(R.id.flup_descript);
        flup_endTime = findViewById(R.id.flup_endTime);
        flup_endReason = findViewById(R.id.flup_endReason);
        FollowMan = findViewById(R.id.FollowMan);
        CreateUser = findViewById(R.id.CreateUser);
        UpdateUser = findViewById(R.id.UpdateUser);
        UpdateTime = findViewById(R.id.UpdateTime);
        EndTime = findViewById(R.id.EndTime);
        EndReason = findViewById(R.id.EndReason);
        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.other).setOnClickListener(this);
    }

    @Override
    public void setData() {
        Intent intent = getIntent();
        Id = intent.getIntExtra("Id", 0);
        name = intent.getStringExtra("name");
        patientID = intent.getIntExtra("patientID", 0);
        followName = intent.getStringExtra("followName");
        descript = intent.getStringExtra("descript");
        startTime = intent.getStringExtra("startTime");
        endTime = intent.getStringExtra("endTime");
        endReason = intent.getStringExtra("endReason");
        createTime = intent.getStringExtra("createTime");
        createUser = intent.getStringExtra("createUser");
        followID = intent.getIntExtra("followID", 0);
        followMan = intent.getStringExtra("followMan");
        followType = intent.getStringExtra("followType");
        updateTime = intent.getStringExtra("updateTime");
        updateUser = intent.getStringExtra("updateUser");
        Log.i("stop", "setData: " + endReason + "\t" + endTime);
        flup_name.setText(name);
        flup_followName.setText(followName);
        flup_followType.setText(followType);
        flup_descript.setText(descript);
        if ((!(TextUtils.isEmpty(followMan))) && (!followMan.equals("null"))) {
            flup_followMan.setText(followMan);
            FollowMan.setVisibility(View.VISIBLE);
        }
        if (!(TextUtils.isEmpty(startTime))) {
            String substring = startTime.substring(startTime.length() - 1);
            if ("日".equals(substring)) {
                flup_startTime.setText(startTime);
            } else {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                long start = Long.parseLong(startTime);
                String format = simpleDateFormat.format(start);
                flup_startTime.setText(format);
            }
        }
        if (!(TextUtils.isEmpty(createTime))) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            long creat = Long.parseLong(createTime);
            String format = simpleDateFormat.format(creat);
            flup_createTime.setText(format);
        }
        if (!TextUtils.isEmpty(updateTime)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            long update = Long.parseLong(updateTime);
            String format = simpleDateFormat.format(update);
            flup_updateTime.setText(format);
            UpdateTime.setVisibility(View.VISIBLE);
        }
        if (!TextUtils.isEmpty(updateUser) && !updateUser.equals("null")) {
            flup_updateUser.setText(updateUser);
            UpdateTime.setVisibility(View.VISIBLE);
        }
        if (!TextUtils.isEmpty(createUser) && !createUser.equals("null")) {
            flup_createUser.setText(createUser);
            CreateUser.setVisibility(View.VISIBLE);
        }
        if (!TextUtils.isEmpty(endReason) && !endReason.equals("null")) {
            flup_endReason.setText(endReason);
            EndReason.setVisibility(View.VISIBLE);
        }
        if (!TextUtils.isEmpty(endTime) && !endTime.equals("null")) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            long end = Long.parseLong(endTime);
            String format = simpleDateFormat.format(end);
            flup_endTime.setText(format);
            EndTime.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.other:
                Intent intent = new Intent(this, StopPatientFollowActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("followName", followName);
                intent.putExtra("Id", Id);
                intent.putExtra("followType", followType);
                startActivityForResult(intent, 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 2) {
            Intent intent1 = new Intent();
            setResult(5, intent1);
            Intent intent2 = new Intent();
            setResult(9, intent2);
            finish();
        }
    }
}
