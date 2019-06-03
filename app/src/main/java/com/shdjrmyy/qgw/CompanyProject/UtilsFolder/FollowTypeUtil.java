package com.shdjrmyy.qgw.CompanyProject.UtilsFolder;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.Base;
import com.shdjrmyy.qgw.CompanyProject.PatientPage.bean.FollowTypeBean;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/15.
 */

public class FollowTypeUtil {

    public static List<String> typeNo = new ArrayList<>();
    public static List<String> typeName = new ArrayList<>();

    public static void initTypeList(Context context) {
        typeNo.clear();
        typeName.clear();
        OkHttpUtils.post().url(Base.url).addParams("act", "FollowTypeList")
                .addParams("data", new FollowTypeList(Base.getMD5Str(), Base.getTimeSpan()).toJson())
                .build().execute(new GsonCallBack<FollowTypeBean>() {

            @Override
            public void onSuccess(FollowTypeBean response) throws JSONException {
                for (int i = 0; i < response.getData().getFollowVisitList().size(); i++) {
                    String typeDetailCode = response.getData().getFollowVisitList().get(i).getTypeDetailCode();
                    String typeDetailName = response.getData().getFollowVisitList().get(i).getTypeDetailName();

                    typeNo.add(typeDetailCode);
                    typeName.add(typeDetailName);
                }
            }

            @Override
            public void onError(Exception e) {

            }
        });

        SharedPreferences.Editor editor = context.getSharedPreferences("FollowType", Context.MODE_PRIVATE).edit();
        editor.putString("typeName0", typeName.get(0));
        editor.putString("typeName1", typeName.get(1));
        editor.putString("typeName2", typeName.get(2));
        editor.putString("typeName3", typeName.get(3));
    }

    public static List<String> initTypeName() {
        typeName.clear();
        OkHttpUtils.post().url(Base.url).addParams("act", "FollowTypeList")
                .addParams("data", new FollowTypeList(Base.getMD5Str(), Base.getTimeSpan()).toJson())
                .build().execute(new GsonCallBack<FollowTypeBean>() {

            @Override
            public void onSuccess(FollowTypeBean response) throws JSONException {
                for (int i = 0; i < response.getData().getFollowVisitList().size(); i++) {
                    String typeDetailName = response.getData().getFollowVisitList().get(i).getTypeDetailName();
                    typeName.add(typeDetailName);
                }
            }

            @Override
            public void onError(Exception e) {

            }
        });
        return typeName;
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
