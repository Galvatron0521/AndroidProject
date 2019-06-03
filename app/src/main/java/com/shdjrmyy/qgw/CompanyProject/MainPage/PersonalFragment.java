package com.shdjrmyy.qgw.CompanyProject.MainPage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shdjrmyy.qgw.CompanyProject.BaseFolder.BaseFragment;
import com.shdjrmyy.qgw.CompanyProject.HomePage.activity.CleanMessageUtil;
import com.shdjrmyy.qgw.CompanyProject.LoginPage.LoginActivity;
import com.shdjrmyy.qgw.CompanyProject.LoginPage.ChangeWordActivity;
import com.shdjrmyy.qgw.CompanyProject.R;
import com.shdjrmyy.qgw.CompanyProject.UtilsFolder.User;

import org.litepal.crud.DataSupport;

import java.util.Date;
import java.util.List;

/**
 * Created by 16001 on 2017/10/17 0017.
 */

public class PersonalFragment extends BaseFragment implements View.OnClickListener {

    private TextView LoginName;
    private TextView Name;
    private TextView Mobile;
    private TextView IDCard;
    private TextView birthday;
    private TextView Sex;
    private RelativeLayout changeWord;
    private RelativeLayout clean;
    private RelativeLayout quit;
    private SharedPreferences sp;

    private String loginName;
    private String name;
    private String mobile;
    private String idCard;
    private String brithday;
    private int sex;

    @Override
    protected int setLayout() {
        return R.layout.fragment_personal;
    }

    @Override
    protected void initView(View view) {
        changeWord = view.findViewById(R.id.changeWord);
        clean = view.findViewById(R.id.changeWord);
        quit = view.findViewById(R.id.quit);
        LoginName = view.findViewById(R.id.LoginName);
        Name = view.findViewById(R.id.Name);
        Mobile = view.findViewById(R.id.Mobile);
        IDCard = view.findViewById(R.id.idCard);
        birthday = view.findViewById(R.id.birthday);
        Sex = view.findViewById(R.id.Sex);
        changeWord.setOnClickListener(this);
        clean.setOnClickListener(this);
        quit.setOnClickListener(this);
        sp = getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
    }

    @Override
    protected void initData() {
        List<User> users = DataSupport.findAll(User.class);
        for (int i = 0; i < users.size(); i++) {
            loginName = users.get(i).getLoginName();
            name = users.get(i).getName();
            mobile = users.get(i).getMobile();
            idCard = users.get(i).getIDCard();
            brithday = users.get(i).getBrithday();
            sex = users.get(i).getSex();
        }
        LoginName.setText(loginName);
        Name.setText(name);
        Mobile.setText(mobile);
        IDCard.setText(idCard);
        birthday.setText(brithday);
        if (1 == sex) {
            Sex.setText("男");
        } else {
            Sex.setText("女");
        }

    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.changeWord:
                Intent intentRegister = new Intent(getContext(), ChangeWordActivity.class);
                startActivityForResult(intentRegister,10);
                break;
            case R.id.clean:
                CleanMessageUtil.clearAllCache(getContext());
                Toast.makeText(context, "缓存已清除", Toast.LENGTH_SHORT).show();
                break;
            case R.id.quit:
                DataSupport.deleteAll(User.class);
                sp.edit().putBoolean("AUTO_ISCHECK", false).commit();
                Intent intentLogin = new Intent(getContext(), LoginActivity.class);
                startActivity(intentLogin);
                getActivity().finish();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==10&&resultCode==11){
            getActivity().finish();
        }
    }
}

