package com.shdjrmyy.qgw.CompanyProject.PatientPage.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.shdjrmyy.qgw.CompanyProject.R;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ShowPhotoActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_photo);
        initView();
    }


    private void initView() {
        findViewById(R.id.btn_data).setOnClickListener(this);
        findViewById(R.id.btn_show).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_data:
                break;
            case R.id.btn_show:

                break;
        }
    }
}
