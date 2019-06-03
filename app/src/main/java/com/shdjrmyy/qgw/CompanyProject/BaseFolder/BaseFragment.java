package com.shdjrmyy.qgw.CompanyProject.BaseFolder;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.captain_miao.grantap.CheckPermission;
import com.example.captain_miao.grantap.listeners.PermissionListener;
import com.example.captain_miao.grantap.utils.PermissionUtils;
import com.google.gson.Gson;
import com.qgw.greendao.gen.DaoSession;


import static com.example.captain_miao.grantap.ShadowPermissionActivity.REQ_CODE_REQUEST_SETTING;


/**
 * Created by admin on 2016/8/15.
 */
public abstract class BaseFragment extends Fragment {
    protected Context context;
    public View view;
    public DaoSession db;
    public DaoManager dm;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }





    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(setLayout(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        initView(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dm = DaoManager.getInstance();
        dm.init(getContext());
        db = dm.getDaoSession();
        initData();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public AlertDialog showViewDialog(View view, String title){
        AlertDialog.Builder bui = new AlertDialog.Builder(getContext());
        AlertDialog dia = bui.setView(view).setTitle(title).create();
        dia.show();
        return dia;
    }

    public View inflateView(int id){
        return LayoutInflater.from(getContext()).inflate(id,null,false);
    }


    protected abstract int setLayout();

    protected abstract void initView(View view);

    protected abstract void initData();

    public abstract String getTitle();

    protected <T extends View> T getView(View pa, int id){
        return (T)pa.findViewById(id);
    }


    //    这个方法使组件实例化不需要转型
    //    使用方式:
    //    TextView textView = bindView(R.id.tv);
    //    这样使用这个方法的时候是不需要强转的
    protected <T extends View> T bindView(int id) {
        return (T) getView().findViewById(id);
    }

    public void showDialog(String title, String message, DialogInterface.OnClickListener click){
        AlertDialog.Builder bui = new AlertDialog.Builder(getContext());
        bui.setTitle(title).setMessage(message).setNegativeButton("确定",click).setPositiveButton("取消",null).show();
    }

    //确定按钮添加点击事件的Dialog
    public AlertDialog showDialogView(String title, String message, DialogInterface.OnClickListener click) {
        AlertDialog.Builder bui = new AlertDialog.Builder(getContext());
        AlertDialog dia = bui.setTitle(title).setMessage(message).setNegativeButton("确定", click).setPositiveButton("取消", null).create();
        dia.show();
        return dia;
    }

    public void log(String l){
        if(Base.O_LOG) Log.e(this.getClass().getName()+"类",l);
    }
    public void log(Object json){
        if(Base.O_LOG) Log.e(this.getClass().getName()+"类",new Gson().toJson(json));
    }

    public void call(String tel){
        Intent intent=new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+tel));
        startActivity(intent);
    }

    public interface PmCallBack{
        void ok();
        void no();
    }

    public void reqPermisson(String permission,final PmCallBack callBack) {
        boolean isGranted = PermissionUtils.hasSelfPermissions(getContext(),
                Manifest.permission.CALL_PHONE);
        if (isGranted){
            callBack.ok();return;
        }
        CheckPermission.from(getContext()).setPermissions
                (Manifest.permission.CALL_PHONE).setPermissionListener
                (new PermissionListener() {
                    @Override
                    public void permissionGranted() {
                        callBack.ok();
                    }

                    @Override
                    public void permissionDenied() {
                        callBack.no();
                    }
                }).check();
    }


    public void showHint(String message){
        Snackbar.make(view,message,Snackbar.LENGTH_SHORT).show();
    }

    public void setPeHint(String message){
        Snackbar.make(view,message,Snackbar.LENGTH_LONG).setAction("设置", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                            .setData(Uri.parse("package:" + "com.shdjrmyy.qgw.myapplication.base"));
                    startActivityForResult(intent, REQ_CODE_REQUEST_SETTING);
                } catch (ActivityNotFoundException e) {
                    e.printStackTrace();
                    Intent intent = new Intent(Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS);
                    startActivityForResult(intent, REQ_CODE_REQUEST_SETTING);
                }
            }
        }).show();
    }





}
