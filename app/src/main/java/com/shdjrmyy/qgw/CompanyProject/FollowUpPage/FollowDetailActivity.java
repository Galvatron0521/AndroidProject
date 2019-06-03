package com.shdjrmyy.qgw.CompanyProject.FollowUpPage;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shdjrmyy.qgw.CompanyProject.BaseFolder.BaseActivity;
import com.shdjrmyy.qgw.CompanyProject.R;
import com.shdjrmyy.qgw.CompanyProject.UtilsFolder.NetWorkStateReceiver;
import com.shdjrmyy.qgw.CompanyProject.ViewFolder.FuncUtil;

import java.text.SimpleDateFormat;

public class FollowDetailActivity extends BaseActivity implements View.OnClickListener {

    private TextView follow_Name;
    private TextView follow_Description;
    private TextView follow_MaxYear;
    private TextView follow_MaxMonth;
    private TextView follow_MaxDay;
    private TextView follow_MaxNum;
    private TextView follow_DayNum;

    private long time;
    private String format;
    private SimpleDateFormat simpleDateFormat;

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
        return R.layout.activity_follow_detail;
    }

    public void initView() {
        FuncUtil.iniSystemBar(this, R.color.white);
        initTB("随访方案详情", "编辑");
        follow_Name = findViewById(R.id.follow_Name);
        follow_Description = findViewById(R.id.follow_Description);
        follow_MaxYear = findViewById(R.id.followMaxYear);
        follow_MaxMonth = findViewById(R.id.followMaxMonth);
        follow_MaxDay = findViewById(R.id.followMaxDay);
        follow_MaxNum = findViewById(R.id.followMaxNum);
        follow_DayNum = findViewById(R.id.followDayNum);
        simpleDateFormat = new SimpleDateFormat(" yy-MM-dd HH:mm");
        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.other).setOnClickListener(this);
    }

    @Override
    public void setData() {
        Intent intent = getIntent();
        followId = intent.getIntExtra("followId", 0);
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

        follow_Name.setText(followName);
        follow_Description.setText(followDescript);
        follow_MaxYear.setText(String.valueOf(followMaxYear));
        follow_MaxMonth.setText(String.valueOf(followMaxMonth));
        follow_MaxDay.setText(String.valueOf(followMaxDay));
        follow_MaxNum.setText(String.valueOf(followMaxNum));
        follow_DayNum.setText(String.valueOf(followDayNum));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.other:
                Intent intent = new Intent(this, EditFollowActivity.class);
                intent.putExtra("followId", followId);
                intent.putExtra("createTime", createTime);
                intent.putExtra("createUser", String.valueOf(createUser));
                intent.putExtra("updateTime", updateTime);
                intent.putExtra("updateUser", String.valueOf(updateUser));
                intent.putExtra("followDayNum", followDayNum);
                intent.putExtra("followName", followName);
                intent.putExtra("followDescript", followDescript);
                intent.putExtra("followMaxDay", followMaxDay);
                intent.putExtra("followMaxMonth", followMaxMonth);
                intent.putExtra("followMaxYear", followMaxYear);
                intent.putExtra("followMaxNum", followMaxNum);
                startActivity(intent);
                break;
        }
    }
}
