package com.shdjrmyy.qgw.CompanyProject.BaseFolder;

import com.google.gson.Gson;
import com.shdjrmyy.qgw.CompanyProject.PatientPage.bean.FollowTypeBean;
import com.shdjrmyy.qgw.CompanyProject.UtilsFolder.GsonCallBack;
import com.shdjrmyy.qgw.CompanyProject.UtilsFolder.User;
import com.zhy.http.okhttp.OkHttpUtils;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.json.JSONException;
import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 16001 on 2017/10/17 0017.
 */

public class Base {


    public static final boolean O_LOG = true;
    //192.168.0.210
//    public static final String url = "http://192.168.0.210:8080/skyapp_kfzj/api/app_patient/";
    public static final String url = "http://111.17.215.37:8082/skyapp_kfzj/api/app_patient/";
//    public static final String url = "http://192.168.0.158:8080/skyapp_kfzj/api/app_patient/";

    public static String getMD5Str() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyyMMdd");

        String md5 = new Md5Hash("shenkangyun_canlian_patient", timeFormat.format(new Date()), 1).toString();
        return md5;
    }

    public static String getTimeSpan() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyyMMdd");
        return timeFormat.format(new Date());
    }

    public static String getSystemtime() {
        long timeMillis = System.currentTimeMillis();
        String systemTime = String.valueOf(timeMillis);
        return systemTime;
    }

    public static int getUserID() {
        int userID = 0;
        List<User> users = DataSupport.findAll(User.class);
        for (int i = 0; i < users.size(); i++) {
            userID = users.get(i).getUserID();
        }
        return userID;
    }

    public static List<Map<String, String>> getFollowType() {
        final List<Map<String, String>> maps = new ArrayList<>();

        OkHttpUtils.post().url(Base.url)
                .addParams("act", "FollowTypeList")
                .addParams("data", new FollowTypeList(Base.getMD5Str(), Base.getTimeSpan()).toJson())
                .build().execute(new GsonCallBack<FollowTypeBean>() {

            @Override
            public void onSuccess(FollowTypeBean response) throws JSONException {
                for (int i = 0; i < response.getData().getFollowVisitList().size(); i++) {
                    Map<String, String> stringMap = new HashMap<>();
                    String typeDetailCode = response.getData().getFollowVisitList().get(i).getTypeDetailCode();
                    String typeDetailName = response.getData().getFollowVisitList().get(i).getTypeDetailName();

                    stringMap.put("typeDetailCode", typeDetailCode);
                    stringMap.put("typeDetailName", typeDetailName);
                    maps.add(stringMap);
                }
            }

            @Override
            public void onError(Exception e) {

            }
        });
        return maps;
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
