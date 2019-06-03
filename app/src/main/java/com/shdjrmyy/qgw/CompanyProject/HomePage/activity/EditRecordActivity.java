package com.shdjrmyy.qgw.CompanyProject.HomePage.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.google.gson.Gson;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.Base;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.BaseActivity;
import com.shdjrmyy.qgw.CompanyProject.PatientPage.bean.ResultBean;
import com.shdjrmyy.qgw.CompanyProject.R;
import com.shdjrmyy.qgw.CompanyProject.UtilsFolder.GsonCallBack;
import com.shdjrmyy.qgw.CompanyProject.ViewFolder.FuncUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import okhttp3.Call;

public class EditRecordActivity extends BaseActivity implements View.OnClickListener {

    private EditText Journal_title;
    private EditText Journal_content;
    private TextView Journal_Start;
    private TextView Journal_End;
    private SimpleDateFormat dateFormat;

    private String titleTxt;
    private String contentTxt;
    private String Start;
    private String End;

    private long calendarid;
    private String title;
    private String content;
    private String startIntent;
    private String endIntent;

    @Override
    public int setView() {
        return R.layout.activity_edit_record;
    }

    @Override
    public void initView() {
        FuncUtil.iniSystemBar(this, R.color.white);
        initTB("编辑备忘录", "提交");
        Journal_title = findViewById(R.id.Journal_title);
        Journal_content = findViewById(R.id.Journal_content);
        Journal_Start = findViewById(R.id.Journal_Start);
        Journal_End = findViewById(R.id.Journal_End);
        Journal_Start.setOnClickListener(this);
        Journal_End.setOnClickListener(this);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.other).setOnClickListener(this);
    }

    @Override
    public void setData() {
        Intent intent = getIntent();
        calendarid = intent.getLongExtra("Calendarid", 0);
        title = intent.getStringExtra("title");
        content = intent.getStringExtra("Content");
        startIntent = intent.getStringExtra("Start");
        endIntent = intent.getStringExtra("End");
        Journal_title.setText(title);
        Journal_content.setText(content);
        long StartLong = Long.parseLong(startIntent);
        Journal_Start.setText(dateFormat.format(StartLong));
        long EndLong = Long.parseLong(endIntent);
        Journal_End.setText(dateFormat.format(EndLong));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.other:
                titleTxt = Journal_title.getText().toString();
                contentTxt = Journal_content.getText().toString();
                if (!TextUtils.isEmpty(titleTxt.trim()) && !TextUtils.isEmpty(contentTxt.trim())) {
                    initHttp();
                }
                break;
            case R.id.Journal_Start:
                DatePickDialog datePickDialog = new DatePickDialog(this);
                TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
                //设置上下年分限制
                datePickDialog.setYearLimt(80);
                //设置标题
                datePickDialog.setTitle("选择时间");
                //设置类型
                datePickDialog.setType(DateType.TYPE_YMD);
                //设置点击确定按钮回调
                datePickDialog.setOnSureLisener(new OnSureLisener() {
                    @Override
                    public void onSure(Date date) {
                        startIntent = String.valueOf(date.getTime());
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Start = simpleDateFormat.format(date);
                        Journal_Start.setText(Start);
                    }
                });
                datePickDialog.show();
                break;
            case R.id.Journal_End:
                DatePickDialog datePickDialog1 = new DatePickDialog(this);
                TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
                //设置上下年分限制
                datePickDialog1.setYearLimt(80);
                //设置标题
                datePickDialog1.setTitle("选择时间");
                //设置类型
                datePickDialog1.setType(DateType.TYPE_YMD);
                //设置点击确定按钮回调
                datePickDialog1.setOnSureLisener(new OnSureLisener() {
                    @Override
                    public void onSure(Date date) {
                        endIntent = String.valueOf(date.getTime());
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        End = simpleDateFormat.format(date);
                        Journal_End.setText(End);
                    }
                });
                datePickDialog1.show();
                break;
        }
    }

    private void initHttp() {
        String ID = String.valueOf(calendarid);
        OkHttpUtils.post().url(Base.url)
                .addParams("act", "insertOrUpdateCalendar")
                .addParams("data", new insertOrUpdateCalendar("update", Base.getMD5Str(), Base.getTimeSpan(), Base.getUserID(),
                        Integer.valueOf(ID), titleTxt, contentTxt, startIntent, endIntent).toJson())
                .build().execute(new GsonCallBack<ResultBean>() {
            @Override
            public void onSuccess(ResultBean response) throws JSONException {
                String status = response.getStatus();
                if ("0".equals(status)) {
                    Intent intent = new Intent();
                    setResult(1, intent);
                    Toast.makeText(EditRecordActivity.this, response.getData().getData(), Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(EditRecordActivity.this, response.getData().getData(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    static class insertOrUpdateCalendar {
        private String optionTag;
        private String appKey;
        private String timeSpan;
        private int id;
        private int calendarid;
        private String title;
        private String calendardescribe;
        private String start;
        private String end;

        public insertOrUpdateCalendar(String optionTag, String appKey, String timeSpan, int id, int calendarid,
                                      String title, String calendardescribe, String start, String end) {
            this.optionTag = optionTag;
            this.appKey = appKey;
            this.timeSpan = timeSpan;
            this.calendarid = calendarid;
            this.id = id;
            this.title = title;
            this.calendardescribe = calendardescribe;
            this.start = start;
            this.end = end;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }
}
