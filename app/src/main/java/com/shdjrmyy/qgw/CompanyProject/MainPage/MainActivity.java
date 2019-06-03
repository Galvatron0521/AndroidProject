package com.shdjrmyy.qgw.CompanyProject.MainPage;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.shdjrmyy.qgw.CompanyProject.HomePage.fragment.HomePageFragment;
import com.shdjrmyy.qgw.CompanyProject.R;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.BaseFragment;
import com.shdjrmyy.qgw.CompanyProject.ViewFolder.FuncUtil;
import com.shdjrmyy.qgw.CompanyProject.ViewFolder.NoScrollViewPager;
import com.shdjrmyy.qgw.CompanyProject.ViewFolder.ViewPagerAdapter;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private RadioButton bt_homePage, bt_patientInfo, bt_personal;
    private NoScrollViewPager vp;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FuncUtil.iniSystemBar(this,R.color.white);
        setContentView(R.layout.activity_main);
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initView() {
        vp = findViewById(R.id.vp_content);
        bt_personal = findViewById(R.id.personal);
        bt_patientInfo = findViewById(R.id.patientInfo);
        bt_homePage = findViewById(R.id.homePage);
        bt_personal.setOnCheckedChangeListener(this);
        bt_patientInfo.setOnCheckedChangeListener(this);
        bt_homePage.setOnCheckedChangeListener(this);
        ArrayList<BaseFragment> fragments = new ArrayList<BaseFragment>();
        fragments.add(new HomePageFragment());
        fragments.add(new PatientInfoFragment());
        fragments.add(new PersonalFragment());
        vp.setOffscreenPageLimit(3);
        vp.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), fragments));
    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b) {
            switch (compoundButton.getId()) {
                case R.id.homePage:
                    vp.setCurrentItem(0, false);
                    bt_homePage.setChecked(true);
                    bt_patientInfo.setChecked(false);
                    bt_personal.setChecked(false);
                    break;
                case R.id.patientInfo:
                    vp.setCurrentItem(1, false);
                    bt_homePage.setChecked(false);
                    bt_patientInfo.setChecked(true);
                    bt_personal.setChecked(false);
                    break;
                case R.id.personal:
                    vp.setCurrentItem(3, false);
                    bt_homePage.setChecked(false);
                    bt_patientInfo.setChecked(false);
                    bt_personal.setChecked(true);
                    break;

            }
        }
    }
}
