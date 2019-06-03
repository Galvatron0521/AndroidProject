package com.shdjrmyy.qgw.CompanyProject.PatientPage.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.text.method.NumberKeyListener;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.google.gson.Gson;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.Base;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.BaseActivity;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.IDCard;
import com.shdjrmyy.qgw.CompanyProject.PatientPage.UtilFolder.GetJsonDataUtil;
import com.shdjrmyy.qgw.CompanyProject.PatientPage.bean.JsonBean;
import com.shdjrmyy.qgw.CompanyProject.PatientPage.bean.ResultBean;
import com.shdjrmyy.qgw.CompanyProject.R;
import com.shdjrmyy.qgw.CompanyProject.UtilsFolder.GsonCallBack;
import com.shdjrmyy.qgw.CompanyProject.UtilsFolder.NetWorkStateReceiver;
import com.shdjrmyy.qgw.CompanyProject.ViewFolder.FuncUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;


public class AddPatientActivity extends BaseActivity implements View.OnClickListener {

    private EditText hz_name;
    private EditText hz_age;
    private TextView hz_sex;
    private EditText hz_num;
    private EditText hz_idCard;
    private TextView hz_birthday;
    private EditText hz_mobile;
    private TextView hz_domicile;

    private String name;
    private String age;
    private String num;
    private String idCard;
    private String mobile;
    private String birthday;
    private String gender;
    private String birth;

    private PopupWindow TypePopup;
    private ListView Gender;
    private List<String> genderType = new ArrayList<>();
    private ArrayAdapter<String> genderAdapter;

    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private Thread thread;
    private static final int MSG_LOAD_DATA = 0x0001;
    private boolean isLoaded = false;

    private String provinceID;
    private String cityID;
    private String regionID;

    @Override
    public int setView() {
        return R.layout.activity_add_patient;
    }

    @Override
    public void initView() {
        FuncUtil.iniSystemBar(this, R.color.white);
        initTB("添加患者", "保存");
        hz_name = findViewById(R.id.hz_name);
        hz_age = findViewById(R.id.hz_age);
        hz_sex = findViewById(R.id.hz_sex);
        hz_num = findViewById(R.id.hz_num);
        hz_idCard = findViewById(R.id.hz_idCard);
        hz_birthday = findViewById(R.id.hz_birthday);
        hz_mobile = findViewById(R.id.hz_mobile);
        hz_domicile = findViewById(R.id.hz_domicile);
        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.other).setOnClickListener(this);
        hz_birthday.setOnClickListener(this);
        hz_sex.setOnClickListener(this);
        hz_domicile.setOnClickListener(this);
    }

    @Override
    public void setData() {
        genderType.clear();
        genderType.add("男");
        genderType.add("女");
        mHandler.sendEmptyMessage(MSG_LOAD_DATA);
    }

    private void initSelectPopup() {
        Gender = new ListView(this);
        genderAdapter = new ArrayAdapter<String>(this, R.layout.item_gender, genderType);
        Gender.setAdapter(genderAdapter);

        // 设置ListView点击事件监听
        Gender.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 在这里获取item数据
                String value = genderType.get(position);
                // 把选择的数据展示对应的TextView上
                hz_sex.setText(value);
                // 选择完后关闭popup窗口
                TypePopup.dismiss();
            }
        });
        TypePopup = new PopupWindow(Gender, hz_sex.getWidth(), ActionBar.LayoutParams.WRAP_CONTENT, true);
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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.other:
                initData();
                break;
            case R.id.hz_birthday:
                DatePickDialog datePickDialog = new DatePickDialog(this);
                TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
                //设置上下年分限制
                datePickDialog.setYearLimt(80);
                //设置标题
                datePickDialog.setTitle("选择时间");
                //设置类型
                datePickDialog.setType(DateType.TYPE_YMD);
                //设置点击确定按钮回调
                datePickDialog.setOnSureLisener(new OnSureLisener() {
                    @Override
                    public void onSure(Date date) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        birthday = simpleDateFormat.format(date);
                        hz_birthday.setText(birthday);
                    }
                });
                datePickDialog.show();
                break;
            case R.id.hz_sex:
                initSelectPopup();
                if (TypePopup != null && !TypePopup.isShowing()) {
                    TypePopup.showAsDropDown(hz_sex, 0, 10);
                }
                break;
            case R.id.hz_domicile:
                if (isLoaded) {
                    ShowPickerView();
                }
                break;
        }

    }

    private void initData() {
        name = hz_name.getText().toString();
        age = hz_age.getText().toString();
        num = hz_num.getText().toString();
        idCard = hz_idCard.getText().toString();
        mobile = hz_mobile.getText().toString();
        birth = hz_birthday.getText().toString();
        gender = hz_sex.getText().toString();
        hz_idCard.setKeyListener(new NumberKeyListener() {
            @NonNull
            @Override
            public int getInputType() {
                return android.text.InputType.TYPE_CLASS_PHONE;
            }

            @Override
            protected char[] getAcceptedChars() {
                char[] numberChars = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'X'};
                return numberChars;
            }
        });
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请填写姓名", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(age)) {
            Toast.makeText(this, "请填写年龄", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(num)) {
            Toast.makeText(this, "请填写编号", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请填写病人信息", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(birth)) {
            Toast.makeText(this, "请填写出生日期", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(gender)) {
            Toast.makeText(this, "请填写性别", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(provinceID)) {
            Toast.makeText(this, "请选择居住地址", Toast.LENGTH_SHORT).show();
        } else {
            try {
                boolean idCardValidate = IDCard.IDCardValidate(idCard);
                if (!idCardValidate) {
                    Toast.makeText(this, "请输入正确的身份证号码", Toast.LENGTH_SHORT).show();
                } else {
                    initHttp();
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOAD_DATA:
                    if (thread == null) {//如果已创建就不再重新创建子线程了
                        thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                // 写子线程中的操作,解析省市区数据
                                initJsonData();
                            }
                        });
                        thread.start();
                    }
                    break;
            }
        }
    };

    private void ShowPickerView() {// 弹出选择器
        final OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getName() +
                        options2Items.get(options1).get(options2) +
                        options3Items.get(options1).get(options2).get(options3);
                hz_domicile.setText(tx);
                Toast.makeText(AddPatientActivity.this, tx, Toast.LENGTH_SHORT).show();

                provinceID = options1Items.get(options1).getId();
                cityID = options1Items.get(options1).getCitylist().get(options2).getId();
                if (!(options1Items.get(options1).getCitylist().get(options2).getRegionlist().size() == 0)) {
                    regionID = options1Items.get(options1).getCitylist().get(options2).getRegionlist().get(options3).getId();
                }
            }
        })
                .setSubmitText("确定")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                .setTitleText("城市选择")//标题
                .setSubCalSize(18)//确定和取消文字大小
                .setTitleSize(20)//标题文字大小
                .setTitleColor(Color.BLACK)//标题文字颜色
                .setSubmitColor(Color.BLUE)//确定按钮文字颜色
                .setCancelColor(Color.BLUE)//取消按钮文字颜色
                .setTitleBgColor(Color.WHITE)
                .setContentTextSize(18)//滚轮文字大小
                .setOutSideCancelable(false)//点击外部dismiss default true
                .build();

        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    private void initJsonData() {//解析数据
        String JsonData = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json文件数据
        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体
        /**
         * 添加省份数据
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;
        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）
            for (int c = 0; c < jsonBean.get(i).getCitylist().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCitylist().get(c).getName();
                CityList.add(CityName);//添加城市
                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表
                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCitylist().get(c).getRegionlist() == null
                        || jsonBean.get(i).getCitylist().get(c).getRegionlist().size() == 0) {
                    City_AreaList.add("");
                } else {
                    for (int d = 0; d < jsonBean.get(i).getCitylist().get(c).getRegionlist().size(); d++) {//该城市对应地区所有数据
                        String AreaName = jsonBean.get(i).getCitylist().get(c).getRegionlist().get(d).getName();
                        City_AreaList.add(AreaName);//添加该城市所有地区数据
                    }
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }
            options2Items.add(CityList);//添加城市数据
            options3Items.add(Province_AreaList);//添加地区数据
        }
        isLoaded = true;
    }

    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }

    private void initHttp() {
        OkHttpUtils.post().url(Base.url).addParams("act", "insertPatient")
                .addParams("data", new insertPatient(Base.getMD5Str(), Base.getTimeSpan(), "insert", "SP0201",
                        Base.getUserID(), num, name, age, gender, mobile, birth, idCard, provinceID, cityID, regionID, 0).toJson())
                .build().execute(new GsonCallBack<ResultBean>() {
            @Override
            public void onSuccess(ResultBean response) throws JSONException {
                if ("0".equals(response.getStatus())) {
                    String data = response.getData().getData();
                    Toast.makeText(AddPatientActivity.this, data, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    setResult(0, intent);
                    finish();
                } else {
                    String data = response.getData().getData();
                    Toast.makeText(AddPatientActivity.this, data, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    static class insertPatient {
        private String optionTag;
        private String moduleCode;
        private String appKey;
        private String timeSpan;
        private int userid;
        private String num;
        private String name;
        private String age;
        private String sex;
        private String mobile;
        private String brithday;
        private String idCard;
        private String provinceID; // 省份id
        private String cityID; // 城市id
        private String regionID; // 区
        private int delFlag;


        public insertPatient(String appKey, String timeSpan, String optionTag, String moduleCode, int userid, String num,
                             String name, String age, String sex, String mobile, String brithday, String idCard, String provinceID,
                             String cityID, String regionID, int delFlag) {
            this.appKey = appKey;
            this.moduleCode = moduleCode;
            this.timeSpan = timeSpan;
            this.optionTag = optionTag;
            this.userid = userid;
            this.num = num;
            this.name = name;
            this.age = age;
            this.sex = sex;
            this.mobile = mobile;
            this.brithday = brithday;
            this.idCard = idCard;
            this.provinceID = provinceID;
            this.cityID = cityID;
            this.regionID = regionID;
            this.delFlag = delFlag;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }

    }
}
