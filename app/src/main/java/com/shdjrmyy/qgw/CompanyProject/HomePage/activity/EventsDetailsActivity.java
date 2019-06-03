package com.shdjrmyy.qgw.CompanyProject.HomePage.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.shdjrmyy.qgw.CompanyProject.BaseFolder.BaseActivity;
import com.shdjrmyy.qgw.CompanyProject.R;
import com.shdjrmyy.qgw.CompanyProject.ViewFolder.FuncUtil;

import java.text.SimpleDateFormat;

public class EventsDetailsActivity extends BaseActivity implements View.OnClickListener {

    private TextView Journal_title;
    private TextView Journal_content;
    private TextView Journal_Start;
    private TextView Journal_End;
    private SimpleDateFormat dateFormat;

    private String title;
    private String content;
    private String start;
    private String end;

    @Override
    public int setView() {
        return R.layout.activity_events_details;
    }

    @Override
    public void initView() {
        FuncUtil.iniSystemBar(this, R.color.white);
        initTB("备忘录详情", "");
        Journal_title = findViewById(R.id.Journal_title);
        Journal_content = findViewById(R.id.Journal_content);
        Journal_Start = findViewById(R.id.Journal_Start);
        Journal_End = findViewById(R.id.Journal_End);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.other).setOnClickListener(this);
    }

    @Override
    public void setData() {
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        content = intent.getStringExtra("Content");
        start = intent.getStringExtra("Start");
        end = intent.getStringExtra("End");

        Journal_title.setText(title);
        Journal_content.setText(content);
        long StartLong = Long.parseLong(start);
        Journal_Start.setText(dateFormat.format(StartLong));
        long EndLong = Long.parseLong(end);
        Journal_End.setText(dateFormat.format(EndLong));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }
}
