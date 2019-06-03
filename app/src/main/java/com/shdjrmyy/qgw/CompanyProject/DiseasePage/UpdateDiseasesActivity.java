package com.shdjrmyy.qgw.CompanyProject.DiseasePage;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.Base;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.BaseActivity;
import com.shdjrmyy.qgw.CompanyProject.PatientPage.bean.ResultBean;
import com.shdjrmyy.qgw.CompanyProject.R;
import com.shdjrmyy.qgw.CompanyProject.UtilsFolder.GsonCallBack;
import com.shdjrmyy.qgw.CompanyProject.UtilsFolder.NetWorkStateReceiver;
import com.shdjrmyy.qgw.CompanyProject.ViewFolder.FuncUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;

public class UpdateDiseasesActivity extends BaseActivity implements View.OnClickListener {

    private TextView bz_num;
    private EditText bz_name;
    private EditText bz_detail;

    private String id;
    private String name;
    private String descript;

    @Override
    public int setView() {
        return R.layout.activity_updata_bz;
    }

    public void initView() {
        FuncUtil.iniSystemBar(this, R.color.white);
        initTB("编辑病种", "保存");
        bz_num = findViewById(R.id.bz_num);
        bz_name = findViewById(R.id.bz_name);
        bz_detail = findViewById(R.id.bz_detail);
        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.other).setOnClickListener(this);
    }

    @Override
    public void setData() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        name = intent.getStringExtra("name");
        descript = intent.getStringExtra("descript");
        bz_num.setText(id);
        bz_name.setText(name);
        bz_detail.setText(descript);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.other:
                initEdit();
                break;
        }

    }

    private void initEdit() {
        String name = bz_name.getText().toString();
        String detail = bz_detail.getText().toString();
        if ((!(TextUtils.isEmpty(name))) && (!(TextUtils.isEmpty(detail)))) {
            OkHttpUtils.post().url(Base.url).addParams("act", "updateDiseases")
                    .addParams("data", new UpDataDisease(Base.getMD5Str(), Base.getTimeSpan(), "update",
                            "SP0201", Base.getUserID(), id, name, detail).toJson())
                    .build().execute(new GsonCallBack<ResultBean>() {
                @Override
                public void onSuccess(ResultBean response) throws JSONException {
                    if ("0".equals(response.getStatus())) {
                        String data = response.getData().getData();
                        Toast.makeText(UpdateDiseasesActivity.this, data, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        setResult(0, intent);
                        finish();
                    } else {
                        String data = response.getData().getData();
                        Toast.makeText(UpdateDiseasesActivity.this, data, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onError(Exception e) {

                }
            });
        } else {
            Toast.makeText(this, "请将病种资料填写完善", Toast.LENGTH_SHORT).show();
        }

    }


    static class UpDataDisease {
        private String optionTag;
        private String moduleCode;
        private String appKey;
        private String timeSpan;
        private int userID;
        private String id;
        private String name;
        private String descript;


        public UpDataDisease(String appKey, String timeSpan, String optionTag, String moduleCode, int userID,
                             String id, String name, String descript) {
            this.appKey = appKey;
            this.moduleCode = moduleCode;
            this.timeSpan = timeSpan;
            this.optionTag = optionTag;
            this.userID = userID;
            this.id = id;
            this.name = name;
            this.descript = descript;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }

    }
}
