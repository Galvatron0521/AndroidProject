package com.shdjrmyy.qgw.CompanyProject.PatientPage.activity;


import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.google.gson.Gson;
import com.shdjrmyy.qgw.CompanyProject.LoginPage.FollowUpTypeBean;
import com.shdjrmyy.qgw.CompanyProject.PatientPage.bean.ResultBean;
import com.shdjrmyy.qgw.CompanyProject.R;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.Base;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.BaseActivity;
import com.shdjrmyy.qgw.CompanyProject.UtilsFolder.GsonCallBack;
import com.shdjrmyy.qgw.CompanyProject.PatientPage.adapter.FollowWayAdapter;
import com.shdjrmyy.qgw.CompanyProject.FollowUpPage.bean.FollowBean;
import com.shdjrmyy.qgw.CompanyProject.UtilsFolder.NetWorkStateReceiver;
import com.shdjrmyy.qgw.CompanyProject.ViewFolder.FuncUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import okhttp3.Call;


public class AddPatientFollowActivity extends BaseActivity implements View.OnClickListener {

    private TextView add_followType;
    private TextView add_startTime;
    private EditText add_descript;
    private RecyclerView followUPRecycler;
    private int followId;
    private String followName;
    private String followDescript;

    private int size;
    private int totalCount;
    private int temp = 0;//记录每次点击的按钮的Id
    private int pageNo = 0;
    private int pageCount = 8;
    private FollowWayAdapter followWayAdapter;
    private LinearLayoutManager layoutManager;
    private List<FollowBean.DataBean.ListBean> listBeans;

    private String StartTime;
    private int FollowID = 0;
    private String addDescript;
    private String addStartTime;
    private String name;
    private int patientID;
    private PopupWindow TypePopup;
    private ListView TypeView;
    private ArrayAdapter<String> typeAdapter;
    private String typeNo;
    private List<String> typeName = new ArrayList<>();
    private List<FollowUpTypeBean> followUpTypeBeans = new ArrayList<>();

    @Override
    public int setView() {
        return R.layout.activity_add_patient_flup;
    }

    @Override
    public void initView() {
        FuncUtil.iniSystemBar(this, R.color.white);
        initTB("添加随访信息", "保存");
        add_followType = findViewById(R.id.add_followType);
        add_startTime = findViewById(R.id.add_startTime);
        add_descript = findViewById(R.id.add_descript);
        followUPRecycler = findViewById(R.id.followUPRecycler);
        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.other).setOnClickListener(this);
        add_followType.setOnClickListener(this);
        add_startTime.setOnClickListener(this);
        layoutManager = new LinearLayoutManager(this);
        listBeans = new ArrayList<>();
        followWayAdapter = new FollowWayAdapter();
        followUPRecycler.setLayoutManager(layoutManager);
        followUPRecycler.setAdapter(followWayAdapter);
        initFollowType();
    }

    private void initFollowType() {
        typeName.clear();
        followUpTypeBeans = DataSupport.findAll(FollowUpTypeBean.class);
        for (int i = 0; i < followUpTypeBeans.size(); i++) {
            String detailName = followUpTypeBeans.get(i).getTypeDetailName();
            typeName.add(detailName);
        }
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        patientID = intent.getIntExtra("patientID", 0);
    }

    @Override
    public void setData() {
        do {
            listBeans.clear();
            OkHttpUtils.post().url(Base.url).addParams("act", "followList")
                    .addParams("data", new FollowList(String.valueOf(pageNo), String.valueOf(pageCount), Base.getMD5Str(), Base.getTimeSpan()).toJson())
                    .build().execute(new GsonCallBack<FollowBean>() {
                @Override
                public void onSuccess(FollowBean response) throws JSONException {
                    size = response.getData().getList().size();
                    totalCount = response.getData().getTotalCount();
                    Log.i("follow", "onSuccess: " + totalCount);
                    for (int i = 0; i < response.getData().getList().size(); i++) {
                        FollowBean.DataBean.ListBean bean = new FollowBean.DataBean.ListBean();
                        followId = response.getData().getList().get(i).getId();
                        followName = response.getData().getList().get(i).getFollowName();
                        followDescript = response.getData().getList().get(i).getFollowDescript();

                        bean.setCheck(false);
                        bean.setId(followId);
                        bean.setFollowName(followName);
                        bean.setFollowDescript(followDescript);
                        listBeans.add(bean);
                    }
                    if (size == totalCount) {
                        followWayAdapter.setNewData(listBeans);
                    }
                }

                @Override
                public void onError(Exception e) {

                }
            });
        } while (!(size == totalCount));

        initClick();

    }

    private void initClick() {
        followWayAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.content:
                        listBeans.get(temp).setCheck(false);
                        listBeans.get(position).setCheck(true);
                        View childAt = followUPRecycler.getChildAt(position);
                        CheckBox checkbox = childAt.findViewById(R.id.check);
                        boolean check = listBeans.get(position).isCheck();
                        checkbox.setChecked(check);
                        followWayAdapter.notifyDataSetChanged();
                        temp = position;
                        FollowID = listBeans.get(position).getId();
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.other:
                initGetData();
                if (!(addDescript.isEmpty() && addStartTime.isEmpty() && typeNo.isEmpty())) {
                    initSave();
                } else {
                    Toast.makeText(this, "请完整填写随访信息", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.add_startTime:
                DatePickDialog startTime = new DatePickDialog(this);
                TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
                //设置上下年分限制
                startTime.setYearLimt(80);
                //设置标题
                startTime.setTitle("选择时间");
                //设置类型
                startTime.setType(DateType.TYPE_ALL);
                //设置点击确定按钮回调
                startTime.setOnSureLisener(new OnSureLisener() {
                    @Override
                    public void onSure(Date date) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        StartTime = simpleDateFormat.format(date);
                        add_startTime.setText(StartTime);
                    }
                });
                startTime.show();
                break;
            case R.id.add_followType:
                initSelectPopup();
                if (TypePopup != null && !TypePopup.isShowing()) {
                    TypePopup.showAsDropDown(add_followType, 0, 10);
                }
                break;
        }
    }

    private void initSelectPopup() {
        TypeView = new ListView(this);
        typeAdapter = new ArrayAdapter<String>(this, R.layout.item_disease, typeName);
        TypeView.setAdapter(typeAdapter);

        // 设置ListView点击事件监听
        TypeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 在这里获取item数据
                String value = typeName.get(position);
                typeNo = followUpTypeBeans.get(position).getTypeDetailCode();
                // 把选择的数据展示对应的TextView上
                add_followType.setText(value);
                // 选择完后关闭popup窗口
                TypePopup.dismiss();
            }
        });
        TypePopup = new PopupWindow(TypeView, add_followType.getWidth(), ActionBar.LayoutParams.WRAP_CONTENT, true);
        // 取得popup窗口的背景图片
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.bg_corner);
        TypePopup.setBackgroundDrawable(drawable);
        TypePopup.setFocusable(true);
        TypePopup.setOutsideTouchable(true);
        TypePopup.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                // 关闭popup窗口
                TypePopup.dismiss();
            }
        });
    }

    private void initSave() {
        OkHttpUtils.post().url(Base.url).addParams("act", "saveFollowInformation")
                .addParams("data", new saveFollowInformation(Base.getMD5Str(), Base.getTimeSpan(), Base.getUserID(),
                        name, patientID, FollowID, typeNo, addStartTime, addDescript, Base.getUserID(), Base.getTimeSpan()).toJson())
                .build().execute(new GsonCallBack<ResultBean>() {
            @Override
            public void onSuccess(ResultBean response) throws JSONException {
                if ("0".equals(response.getStatus())) {
                    String data = response.getData().getData();
                    Toast.makeText(AddPatientFollowActivity.this, data, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    setResult(0, intent);
                    finish();
                } else {
                    String data = response.getData().getData();
                    Toast.makeText(AddPatientFollowActivity.this, data, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    private void initGetData() {
        addDescript = add_descript.getText().toString();
        addStartTime = add_startTime.getText().toString();
    }

    static class saveFollowInformation {
        private String appKey;
        private String timeSpan;
        private int userid;
        private String name;
        private int patientID;
        private int followID;
        private String followType;
        private String startTime;
        private String descript;
        private int createUser;
        private String createTime;

        public saveFollowInformation(String appKey, String timeSpan, int userid, String name, int patientID, int followID, String followType, String startTime,
                                     String descript, int createUser, String createTime) {
            this.appKey = appKey;
            this.timeSpan = timeSpan;
            this.userid = userid;
            this.name = name;
            this.patientID = patientID;
            this.followID = followID;
            this.followType = followType;
            this.startTime = startTime;
            this.descript = descript;
            this.createUser = createUser;
            this.createTime = createTime;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }

    static class FollowList {
        String pageNo;
        String pageCount;
        String appKey;
        String timeSpan;


        public FollowList(String pageNo, String pageCount, String appKey, String timeSpan) {
            this.appKey = appKey;
            this.timeSpan = timeSpan;
            this.pageNo = pageNo;
            this.pageCount = pageCount;

        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }
}
