package com.shdjrmyy.qgw.CompanyProject.LoginPage;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.Base;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.BaseActivity;
import com.shdjrmyy.qgw.CompanyProject.PatientPage.bean.FollowTypeBean;
import com.shdjrmyy.qgw.CompanyProject.R;
import com.shdjrmyy.qgw.CompanyProject.UtilsFolder.GsonCallBack;
import com.shdjrmyy.qgw.CompanyProject.UtilsFolder.NetWorkStateReceiver;
import com.shdjrmyy.qgw.CompanyProject.UtilsFolder.User;
import com.shdjrmyy.qgw.CompanyProject.ViewFolder.FuncUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;
import org.litepal.crud.DataSupport;

import java.util.List;


/**
 * Created by 16001 on 2017/10/17 0017.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_login;
    private TextView tv_register;
    private ImageView img_login;
    private ImageView img_register;
    private Bundle bundle;
    private FragmentManager fragmentManager;

    private NetWorkStateReceiver netWorkStateReceiver;

    @Override
    public int setView() {
        return R.layout.activity_login;
    }


    @Override
    public void initView() {
        FuncUtil.iniSystemBar(this, R.color.white);
        tv_login = findViewById(R.id.tv_login);
        tv_register = findViewById(R.id.tv_register);
        img_login = findViewById(R.id.img_login);
        img_register = findViewById(R.id.img_register);
        fragmentManager = getSupportFragmentManager();
        tv_login.setOnClickListener(this);
        tv_register.setOnClickListener(this);
        initFollowType();
    }

    private void initFollowType() {
        DataSupport.deleteAll(FollowUpTypeBean.class);
        OkHttpUtils.post().url(Base.url)
                .addParams("act", "FollowTypeList")
                .addParams("data", new FollowTypeList(Base.getMD5Str(), Base.getTimeSpan()).toJson())
                .build().execute(new GsonCallBack<FollowTypeBean>() {

            @Override
            public void onSuccess(FollowTypeBean response) throws JSONException {
                for (int i = 0; i < response.getData().getFollowVisitList().size(); i++) {
                    FollowUpTypeBean typeBean = new FollowUpTypeBean();
                    String typeDetailCode = response.getData().getFollowVisitList().get(i).getTypeDetailCode();
                    String typeDetailName = response.getData().getFollowVisitList().get(i).getTypeDetailName();
                    typeBean.setTypeDetailCode(typeDetailCode);
                    typeBean.setTypeDetailName(typeDetailName);
                    typeBean.save();
                }
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }


    @Override
    protected void onResume() {
        if (netWorkStateReceiver == null) {
            netWorkStateReceiver = new NetWorkStateReceiver();
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(netWorkStateReceiver, filter);
        initFollowType();
        super.onResume();
    }

    @Override
    protected void onPause() {
        unregisterReceiver(netWorkStateReceiver);
        super.onPause();
    }

    @Override
    public void setData() {
        Intent intent = getIntent();
        int quit = intent.getIntExtra("quit", 0);
        if (1 == quit) {
            bundle = new Bundle();
            bundle.putString("quit", "quit");
        }
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        LoginFragment loginFragment = new LoginFragment();
        transaction.add(R.id.login_interface, loginFragment);
        transaction.commit();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                if (img_login.getVisibility() != View.VISIBLE) {
                    img_login.setVisibility(View.VISIBLE);
                    img_register.setVisibility(View.INVISIBLE);
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    LoginFragment loginFragment = new LoginFragment();
                    transaction.replace(R.id.login_interface, loginFragment);
                    transaction.commit();
                }
                break;
            case R.id.tv_register:
                List<User> users = DataSupport.findAll(User.class);
                if (users.size() != 0) {
                    if (img_register.getVisibility() != View.VISIBLE) {
                        img_register.setVisibility(View.VISIBLE);
                        img_login.setVisibility(View.INVISIBLE);
                        Intent intent = new Intent(this, ChangeWordActivity.class);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(this, "请先登录后注册", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    static class FollowTypeList {
        String appKey;
        String timeSpan;


        public FollowTypeList(String appKey, String timeSpan) {
            this.appKey = appKey;
            this.timeSpan = timeSpan;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }

}


