package com.shdjrmyy.qgw.CompanyProject.PatientPage.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.google.gson.Gson;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.BaseActivity;
import com.shdjrmyy.qgw.CompanyProject.DiseasePage.bean.DiseaseListBean;
import com.shdjrmyy.qgw.CompanyProject.R;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.Base;
import com.shdjrmyy.qgw.CompanyProject.DiseasePage.bean.DiseaseBean;
import com.shdjrmyy.qgw.CompanyProject.UtilsFolder.GsonCallBack;
import com.shdjrmyy.qgw.CompanyProject.PatientPage.bean.ResultBean;
import com.shdjrmyy.qgw.CompanyProject.UtilsFolder.NetWorkStateReceiver;
import com.shdjrmyy.qgw.CompanyProject.ViewFolder.FuncUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;


public class AddHRecordActivity extends BaseActivity implements View.OnClickListener {

    private EditText hzAdd_hospitalNum;
    private TextView hzAdd_diseases;
    private TextView hzAdd_inTime;
    private TextView hzAdd_outTime;
    private EditText hzAdd_patientSay;
    private EditText hzAdd_diagnosis;
    private EditText hzAdd_procedure;
    private EditText hzAdd_treat;
    private PopupWindow diseasesPopup;
    private ListView diseasesView;
    private ArrayAdapter<String> diseaseAdapter;
    private int size;
    private int pageNo = 0;
    private int Refresh = 0;
    private int pageCount = 8;

    private int id_http;
    private String hospitalNum;
    private String diseases;
    private String inTime;
    private String outTime;
    private String diagnosis;
    private String patientSay;
    private String procedure;
    private String treat;

    private String ids;
    private String id;
    private String name;
    private String diseaseNo;
    private List<DiseaseBean> diseaseList = new ArrayList<>();
    private List<String> diseaseName = new ArrayList<>();

    @Override
    public int setView() {
        return R.layout.activity_add_hrecord;
    }

    public void initView() {
        FuncUtil.iniSystemBar(this, R.color.white);
        initTB("添加入院记录", "保存");
        hzAdd_hospitalNum = findViewById(R.id.hzAdd_hospitalNum);
        hzAdd_diseases = findViewById(R.id.hzAdd_diseases);
        hzAdd_inTime = findViewById(R.id.hzAdd_inTime);
        hzAdd_outTime = findViewById(R.id.hzAdd_outTime);
        hzAdd_patientSay = findViewById(R.id.hzAdd_patientSay);
        hzAdd_diagnosis = findViewById(R.id.hzAdd_diagnosis);
        hzAdd_procedure = findViewById(R.id.hzAdd_procedure);
        hzAdd_treat = findViewById(R.id.hzAdd_treat);
        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.other).setOnClickListener(this);
        hzAdd_diseases.setOnClickListener(this);
        hzAdd_inTime.setOnClickListener(this);
        hzAdd_outTime.setOnClickListener(this);
    }

    @Override
    public void setData() {
        do {
            diseaseList.clear();
            OkHttpUtils.post().url(Base.url).addParams("act", "DiseasesList")
                    .addParams("data", new Disease(Base.getMD5Str(), Base.getTimeSpan(), "SP0401",
                            String.valueOf(pageNo), String.valueOf(pageCount)).toJson())
                    .build().execute(new GsonCallBack<DiseaseListBean>() {
                @Override
                public void onSuccess(final DiseaseListBean response) throws JSONException {
                    size = response.getData().getTotalCount();
                    for (int i = 0; i < response.getData().getList().size(); i++) {
                        DiseaseBean diseaseBean = new DiseaseBean();
                        id = response.getData().getList().get(i).getId();
                        name = response.getData().getList().get(i).getName();
                        diseaseBean.setDiseaseNo(id);
                        diseaseBean.setDiseaseName(name);
                        diseaseList.add(diseaseBean);
                        diseaseName.add(name);
                    }
                    pageCount = size;
                }

                @Override
                public void onError(Exception e) {

                }

            });
        } while (size > pageCount);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.other:
                initData();
                initSave();
                break;
            case R.id.hzAdd_diseases:
                initSelectPopup();
                if (diseasesPopup != null && !diseasesPopup.isShowing()) {
                    diseasesPopup.showAsDropDown(hzAdd_diseases, 0, 10);
                }
                break;
            case R.id.hzAdd_inTime:
                DatePickDialog InTime = new DatePickDialog(this);
                TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
                //设置上下年分限制
                InTime.setYearLimt(80);
                //设置标题
                InTime.setTitle("选择时间");
                //设置类型
                InTime.setType(DateType.TYPE_ALL);
                //设置点击确定按钮回调
                InTime.setOnSureLisener(new OnSureLisener() {
                    @Override
                    public void onSure(Date date) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        inTime = simpleDateFormat.format(date);
                        hzAdd_inTime.setText(inTime);
                    }
                });
                InTime.show();
                break;
            case R.id.hzAdd_outTime:
                DatePickDialog OutTime = new DatePickDialog(this);
                TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
                //设置上下年分限制
                OutTime.setYearLimt(80);
                //设置标题
                OutTime.setTitle("选择时间");
                //设置类型
                OutTime.setType(DateType.TYPE_ALL);
                //设置点击确定按钮回调
                OutTime.setOnSureLisener(new OnSureLisener() {
                    @Override
                    public void onSure(Date date) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        outTime = simpleDateFormat.format(date);
                        hzAdd_outTime.setText(outTime);
                    }
                });
                OutTime.show();
                break;
        }
    }

    private void initSelectPopup() {
        diseasesView = new ListView(this);
        diseaseAdapter = new ArrayAdapter<String>(this, R.layout.item_disease, diseaseName);
        diseasesView.setAdapter(diseaseAdapter);

        // 设置ListView点击事件监听
        diseasesView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 在这里获取item数据
                String value = diseaseName.get(position);
                diseaseNo = diseaseList.get(position).getDiseaseNo();
                // 把选择的数据展示对应的TextView上
                hzAdd_diseases.setText(value);
                // 选择完后关闭popup窗口
                diseasesPopup.dismiss();
            }
        });
        diseasesPopup = new PopupWindow(diseasesView, hzAdd_diseases.getWidth(), ActionBar.LayoutParams.WRAP_CONTENT, true);
        // 取得popup窗口的背景图片
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.bg_corner);
        diseasesPopup.setBackgroundDrawable(drawable);
        diseasesPopup.setFocusable(true);
        diseasesPopup.setOutsideTouchable(true);
        diseasesPopup.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                // 关闭popup窗口
                diseasesPopup.dismiss();
            }
        });
    }

    private void initData() {
        Intent intent = getIntent();
        id_http = intent.getIntExtra("id", 10000);
        ids = String.valueOf(id_http);
        hospitalNum = String.valueOf(hzAdd_hospitalNum.getText());
        diseases = hzAdd_diseases.getText().toString();
        inTime = String.valueOf(hzAdd_inTime.getText());
        outTime = String.valueOf(hzAdd_outTime.getText());
        diagnosis = String.valueOf(hzAdd_patientSay.getText());
        patientSay = String.valueOf(hzAdd_diagnosis.getText());
        procedure = String.valueOf(hzAdd_procedure.getText());
        treat = String.valueOf(hzAdd_treat.getText());
    }

    private void initSave() {
        if (hzAdd_diseases.getText().equals("--请选择--")) {
            Toast.makeText(this, "请选择病种", Toast.LENGTH_SHORT).show();
        } else {
            diseaseNo = String.valueOf(hzAdd_diseases.getText());
            for (int i = 0; i < diseaseList.size(); i++) {
                String diseaseName = diseaseList.get(i).getDiseaseName();
                if (diseaseNo.equals(diseaseName)) {
                    diseaseNo = diseaseList.get(i).getDiseaseNo();
                }
            }
            OkHttpUtils.post().url(Base.url)
                    .addParams("act", "insertHospitalRecord")
                    .addParams("data", (new AddHRecord(Base.getMD5Str(), Base.getTimeSpan(), Base.getUserID(), Base.getUserID(), ids, hospitalNum, diseaseNo,
                            inTime, outTime, diagnosis, patientSay, procedure, treat).toJson()))
                    .build().execute(new GsonCallBack<ResultBean>() {
                @Override
                public void onSuccess(ResultBean response) throws JSONException {
                    String data = response.getData().getData();
                    String status = response.getStatus();
                    if ("0".equals(status)) {
                        Toast.makeText(AddHRecordActivity.this, data, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        setResult(Refresh, intent);
                        finish();
                    } else {
                        Toast.makeText(AddHRecordActivity.this, data, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onError(Exception e) {

                }
            });
        }
    }

    static class AddHRecord {
        int UserID;
        int createUser;
        String id;
        String hospitalNum;
        String diseasesID;
        String inTime;
        String outTime;
        String diagnosis;
        String patientSay;
        String procedure;
        String treat;
        String appKey;
        String timeSpan;


        public AddHRecord(String appKey, String timeSpan, int UserID, int createUser, String id, String hospitalNum, String diseasesID, String inTime,
                          String outTime, String diagnosis, String patientSay, String procedure, String treat) {
            this.appKey = appKey;
            this.timeSpan = timeSpan;
            this.UserID = UserID;
            this.createUser = createUser;
            this.id = id;
            this.hospitalNum = hospitalNum;
            this.diseasesID = diseasesID;
            this.inTime = inTime;
            this.outTime = outTime;
            this.diagnosis = diagnosis;
            this.patientSay = patientSay;
            this.procedure = procedure;
            this.treat = treat;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }

    static class Disease {
        String moduleCode;
        String pageCount;
        String pageNo;
        String appKey;
        String timeSpan;


        public Disease(String appKey, String timeSpan, String moduleCode, String pageNo, String pageCount) {
            this.appKey = appKey;
            this.pageCount = pageCount;
            this.pageNo = pageNo;
            this.timeSpan = timeSpan;
            this.moduleCode = moduleCode;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }

}
