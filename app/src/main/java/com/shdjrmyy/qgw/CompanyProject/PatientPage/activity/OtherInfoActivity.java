package com.shdjrmyy.qgw.CompanyProject.PatientPage.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

import com.shdjrmyy.qgw.CompanyProject.R;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.BaseActivity;
import com.shdjrmyy.qgw.CompanyProject.PatientPage.fragment.WebFragment;
import com.shdjrmyy.qgw.CompanyProject.UtilsFolder.NetWorkStateReceiver;
import com.shdjrmyy.qgw.CompanyProject.ViewFolder.FuncUtil;


public class OtherInfoActivity extends BaseActivity implements View.OnClickListener {

    private int ID;
    private String titleText;
    private String link;

    private FragmentManager fragmentManager;


    @Override
    public int setView() {
        return R.layout.activity_other_info;
    }

    @Override
    public void initView() {
        FuncUtil.iniSystemBar(this, R.color.white);
        Intent intent = getIntent();
        ID = intent.getIntExtra("id", 10000);
        titleText = intent.getStringExtra("title");
        link = intent.getStringExtra("link");
        initTB(titleText, "其他");
        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.other).setVisibility(View.GONE);
    }

    @Override
    public void setData() {
        fragmentManager = getSupportFragmentManager();
        WebFragment webFragment = new WebFragment();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.hzData_frame, webFragment);
        Bundle bundle = new Bundle();
        bundle.putInt("ID", ID);
        Log.i("bundle", "setData: " + ID);
        webFragment.setArguments(bundle);
        bundle.putString("link", link);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }

    private NetWorkStateReceiver netWorkStateReceiver;

    @Override
    protected void onResume() {
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        super.onResume();
    }
}
