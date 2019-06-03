package com.shdjrmyy.qgw.CompanyProject.DiseasePage;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.view.View;
import android.widget.TextView;

import com.shdjrmyy.qgw.CompanyProject.BaseFolder.BaseActivity;
import com.shdjrmyy.qgw.CompanyProject.R;
import com.shdjrmyy.qgw.CompanyProject.UtilsFolder.NetWorkStateReceiver;
import com.shdjrmyy.qgw.CompanyProject.ViewFolder.FuncUtil;

public class DiseasesDetailActivity extends BaseActivity implements View.OnClickListener {

    private TextView bz_name;
    private TextView bz_detail;

    private String id;
    private String name;
    private String descript;

    @Override
    public int setView() {
        return R.layout.activity_bzdetail;
    }

    public void initView() {
        FuncUtil.iniSystemBar(this, R.color.white);
        initTB("病种详情", "编辑");
        bz_name = findViewById(R.id.bz_name);
        bz_detail = findViewById(R.id.bz_detail);
        findViewById(R.id.other).setOnClickListener(this);
        findViewById(R.id.back).setOnClickListener(this);
    }

    @Override
    public void setData() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        name = intent.getStringExtra("name");
        descript = intent.getStringExtra("descript");
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
                Intent intent = new Intent(this, UpdateDiseasesActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("name", name);
                intent.putExtra("descript", descript);
                startActivityForResult(intent, 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 0) {
            Intent intent = new Intent();
            setResult(1, intent);
            finish();
        }
    }
}
