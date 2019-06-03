package com.shdjrmyy.qgw.CompanyProject.DiseasePage;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.BaseActivity;
import com.shdjrmyy.qgw.CompanyProject.PatientPage.activity.AddPatientActivity;
import com.shdjrmyy.qgw.CompanyProject.R;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.Base;
import com.shdjrmyy.qgw.CompanyProject.UtilsFolder.GsonCallBack;
import com.shdjrmyy.qgw.CompanyProject.PatientPage.bean.ResultBean;
import com.shdjrmyy.qgw.CompanyProject.UtilsFolder.NetWorkStateReceiver;
import com.shdjrmyy.qgw.CompanyProject.ViewFolder.FuncUtil;
import com.zhy.http.okhttp.OkHttpUtils;


import org.json.JSONException;

public class AddDiseasesActivity extends BaseActivity implements View.OnClickListener {


    private EditText ed_num;
    private EditText ed_name;
    private EditText ed_describe;

    private String name;
    private String descrie;
    private String num;

    @Override
    public int setView() {
        return R.layout.activity_add_bz;
    }


    public void initView() {
        FuncUtil.iniSystemBar(this, R.color.white);
        initTB("病种添加", "保存");
        ed_num = findViewById(R.id.bz_num);
        ed_name = findViewById(R.id.bz_name);
        ed_describe = findViewById(R.id.bz_detail);
        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.other).setOnClickListener(this);
    }

    @Override
    public void setData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.other:
                name = ed_name.getText().toString();
                descrie = ed_describe.getText().toString();
                num = ed_num.getText().toString();
                if (TextUtils.isEmpty(name) && TextUtils.isEmpty(descrie) && TextUtils.isEmpty(num)) {
                    Toast.makeText(this, "请填写病种名称或病种描述", Toast.LENGTH_SHORT).show();
                } else {
                    insertDiseases();
                }
                break;
        }
    }

    private void insertDiseases() {
        OkHttpUtils.post().url(Base.url)
                .addParams("act", "insertDiseases")
                .addParams("data", (new insertDisease(Base.getMD5Str(), Base.getTimeSpan(), "insert", "SP0201",
                        num, name, descrie, Base.getUserID(), Base.getTimeSpan(), Base.getUserID(), 0).toJson()))
                .build().execute(new GsonCallBack<ResultBean>() {

            @Override
            public void onSuccess(ResultBean response) throws JSONException {
                if ("0".equals(response.getStatus())) {
                    String data = response.getData().getData();
                    Toast.makeText(AddDiseasesActivity.this, data, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    setResult(0, intent);
                    finish();
                } else {
                    String data = response.getData().getData();
                    Toast.makeText(AddDiseasesActivity.this, data, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Exception e) {
            }
        });
    }

    static class insertDisease {
        private String optionTag;
        private String moduleCode;
        private String appKey;
        private String timeSpan;
        private String id;
        private String name;
        private String descript;
        private int createUser;
        private String createTime;
        private int userID;
        private int delFlag;


        public insertDisease(String appKey, String timeSpan, String optionTag, String moduleCode,
                             String id, String name, String descript, int createUser, String createTime, int userID, int delFlag) {
            this.appKey = appKey;
            this.moduleCode = moduleCode;
            this.timeSpan = timeSpan;
            this.optionTag = optionTag;
            this.id = id;
            this.name = name;
            this.descript = descript;
            this.createUser = createUser;
            this.createTime = createTime;
            this.userID = userID;
            this.delFlag = delFlag;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }

    }
}
