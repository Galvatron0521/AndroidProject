package com.shdjrmyy.qgw.CompanyProject.LoginPage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.Base;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.BaseActivity;
import com.shdjrmyy.qgw.CompanyProject.PatientPage.bean.ResultBean;
import com.shdjrmyy.qgw.CompanyProject.R;
import com.shdjrmyy.qgw.CompanyProject.UtilsFolder.GsonCallBack;
import com.shdjrmyy.qgw.CompanyProject.ViewFolder.FuncUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;

public class ChangeWordActivity extends BaseActivity implements View.OnClickListener {

    private EditText oldCipher;
    private EditText NewPassWord;
    private EditText ConfirmWord;

    private String oldCipherTxt;
    private String ConfirmWordTxt;
    private String NewPassWordTxt;

    private SharedPreferences sp;

    @Override
    public int setView() {
        return R.layout.activity_change_word;
    }

    @Override
    public void initView() {
        FuncUtil.iniSystemBar(this, R.color.white);
        initTB("更改密码", "提交");
        oldCipher = findViewById(R.id.oldCipher);
        NewPassWord = findViewById(R.id.NewPassWord);
        ConfirmWord = findViewById(R.id.ConfirmWord);
        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.other).setOnClickListener(this);
        sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
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
                oldCipherTxt = oldCipher.getText().toString();
                NewPassWordTxt = NewPassWord.getText().toString();
                ConfirmWordTxt = ConfirmWord.getText().toString();
                if (!NewPassWordTxt.equals(ConfirmWordTxt)) {
                    Toast.makeText(this, "两次输入密码不一致", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(oldCipherTxt.trim())) {
                    Toast.makeText(this, "旧密码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    initHttp();
                }
                break;
        }
    }

    private void initHttp() {
        OkHttpUtils.post().url(Base.url)
                .addParams("act", "updateUserPassword")
                .addParams("data", new updateUserPassword(Base.getMD5Str(), Base.getTimeSpan(), Base.getUserID(),
                        oldCipherTxt, ConfirmWordTxt).toJson())
                .build().execute(new GsonCallBack<ResultBean>() {

            @Override
            public void onSuccess(ResultBean response) throws JSONException {
                String status = response.getStatus();
                if ("0".equals(status)) {
                    Toast.makeText(ChangeWordActivity.this, response.getData().getData(), Toast.LENGTH_SHORT).show();
                    Intent intentBack = new Intent();
                    setResult(11, intentBack);
                    initReload();
                    finish();
                } else {
                    Toast.makeText(ChangeWordActivity.this, response.getData().getData(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    private void initReload() {
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    static class updateUserPassword {

        private String appKey;
        private String timeSpan;
        private int id;
        private String password;
        private String passwordnew;


        public updateUserPassword(String appKey, String timeSpan, int id, String password, String passwordnew) {
            this.appKey = appKey;
            this.password = password;
            this.timeSpan = timeSpan;
            this.id = id;
            this.passwordnew = passwordnew;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }

    }
}
