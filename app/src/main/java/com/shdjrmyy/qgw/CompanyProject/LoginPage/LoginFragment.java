package com.shdjrmyy.qgw.CompanyProject.LoginPage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.Base;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.BaseFragment;
import com.shdjrmyy.qgw.CompanyProject.MainPage.MainActivity;
import com.shdjrmyy.qgw.CompanyProject.R;
import com.shdjrmyy.qgw.CompanyProject.UtilsFolder.GsonCallBack;
import com.shdjrmyy.qgw.CompanyProject.UtilsFolder.User;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.litepal.crud.DataSupport;

import java.util.List;

import okhttp3.Call;

/**
 * Created by Administrator on 2018/1/17.
 */

public class LoginFragment extends BaseFragment implements View.OnClickListener {

    private TextView login_forget;
    private TextView login_keep;
    private TextView login_auto;
    private EditText login_username;
    private EditText login_password;
    private Button btn_login;
    private String userName;
    private String password;
    private String loginName;
    private String name;
    private int sex;
    private int userID;
    private String idCard;
    private String mobile;
    private String brithday;

    private SharedPreferences sp;

    @Override
    protected int setLayout() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initView(View view) {
        login_forget = view.findViewById(R.id.login_forget);
        login_keep = view.findViewById(R.id.login_keep);
        login_auto = view.findViewById(R.id.login_auto);
        login_username = view.findViewById(R.id.login_username);
        login_password = view.findViewById(R.id.login_password);
        btn_login = view.findViewById(R.id.btn_login);
        login_forget.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        login_keep.setOnClickListener(this);
        login_auto.setOnClickListener(this);
        sp = getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
    }

    @Override
    protected void initData() {
        //判断记住密码多选框的状态
        if (sp.getBoolean("ISCHECK", false)) {
            //设置默认是记录密码状态
            login_keep.setTextColor(getResources().getColor(R.color.keepBlue));
            userName = sp.getString("USER_NAME", "");
            password = sp.getString("PASSWORD", "");
            login_username.setText(userName);
            login_password.setText(password);
            //判断自动登陆多选框状态
            if (sp.getBoolean("AUTO_ISCHECK", false)) {
                //设置默认是自动登录状态
                login_auto.setTextColor(getResources().getColor(R.color.keepBlue));
                //跳转界面
                initLogin();
            }
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_keep:
                if (login_keep.getCurrentTextColor() == -6710887) {
                    login_keep.setTextColor(getResources().getColor(R.color.keepBlue));
                    sp.edit().putBoolean("ISCHECK", true).commit();
                } else {
                    login_keep.setTextColor(getResources().getColor(R.color.keepGray));
                    sp.edit().putBoolean("ISCHECK", false).commit();
                }
                break;
            case R.id.login_auto:
                if (login_auto.getCurrentTextColor() == -6710887) {
                    login_auto.setTextColor(getResources().getColor(R.color.keepBlue));
                    sp.edit().putBoolean("AUTO_ISCHECK", true).commit();
                } else {
                    login_auto.setTextColor(getResources().getColor(R.color.keepGray));
                    sp.edit().putBoolean("AUTO_ISCHECK", false).commit();
                }
                break;
            case R.id.btn_login:
                userName = login_username.getText().toString();
                password = login_password.getText().toString();
                if ((!(userName.trim().isEmpty())) && (!(password.trim().isEmpty()))) {
                    initLogin();
                } else {
                    Toast.makeText(context, "请输入账号或密码", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void initLogin() {
        DataSupport.deleteAll(User.class);
        OkHttpUtils.post().url(Base.url)
                .addParams("act", "login")
                .addParams("data", new login(Base.getMD5Str(), Base.getTimeSpan(), userName, password).toJson())
                .build().execute(new GsonCallBack<LoginResult>() {
            @Override
            public void onSuccess(LoginResult response) throws JSONException {
                User user = new User();
                String status = response.getStatus();
                if ("0".equals(status)) {
                    Toast.makeText(context, "登陆成功", Toast.LENGTH_SHORT).show();
                    name = response.getData().getUser().getName();
                    userID = response.getData().getUser().getUserID();
                    loginName = response.getData().getUser().getLoginName();
                    idCard = response.getData().getUser().getIDCard();
                    mobile = response.getData().getUser().getMobile();
                    sex = response.getData().getUser().getSex();
                    brithday = response.getData().getUser().getBrithday();
                    user.setUserID(userID);
                    user.setName(name);
                    user.setLoginName(loginName);
                    user.setBrithday(brithday);
                    user.setSex(sex);
                    user.setMobile(mobile);
                    user.setIDCard(idCard);
                    user.save();
                    List<FollowUpTypeBean> all = DataSupport.findAll(FollowUpTypeBean.class);
                    if (all.size() != 0) {
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        startActivity(intent);
                    }

                    if (login_keep.getCurrentTextColor() != -6710887) {
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("USER_NAME", userName);
                        editor.putString("PASSWORD", password);
                        editor.commit();
                    }
                    getActivity().finish();
                } else {
                    Toast.makeText(context, "账号或密码输入错误", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    @Override
    public String getTitle() {
        return null;
    }


    static class login {
        private String appKey;
        private String timeSpan;
        private String username;
        private String password;

        public login(String appKey, String timeSpan, String username, String password) {
            this.appKey = appKey;
            this.timeSpan = timeSpan;
            this.username = username;
            this.password = password;

        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }

}
