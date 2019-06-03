package com.shdjrmyy.qgw.CompanyProject.BaseFolder;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shdjrmyy.qgw.CompanyProject.R;
import com.shdjrmyy.qgw.CompanyProject.UtilsFolder.NetWorkStateReceiver;
import com.shdjrmyy.qgw.CompanyProject.ViewFolder.PullRecyclerView;
import com.shdjrmyy.qgw.CompanyProject.ViewFolder.RecyclerViewDivider;


/**
 * Created by 16001 on 2017/10/17 0017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusNavigationBar();
        setContentView(setView());
        initView();
        setData();

    }


    public void hideStatusNavigationBar() {

    }

    public abstract int setView();

    public abstract void setData();

    public abstract void initView();


    public void showJP(EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        InputMethodManager inputManager = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(editText, 0);
    }


    public void log(String l) {
        if (Base.O_LOG) Log.e(this.getClass().getName() + "类", l);
    }

    public void log(Object json) {
        if (Base.O_LOG) Log.e(this.getClass().getName() + "类", new Gson().toJson(json));
    }

    public RecyclerView recy;
    public SwipeRefreshLayout swip;

    public void initRecy(RecyclerView recy, SwipeRefreshLayout swip) {
        if (recy != null) this.recy = recy;
        else recy = findViewById(R.id.recy);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        layoutManager.setReverseLayout(true);
        recy.setLayoutManager(layoutManager);
        recy.addItemDecoration(new RecyclerViewDivider(this));
        if (swip != null) {
            this.swip = swip;
            swip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    BaseActivity.this.onRefresh();
                }
            });
        }
    }


    public PullRecyclerView pull;

    //带下拉上拉的RV
    public void initListView(PullRecyclerView view, PullRecyclerView.OnPullRefreshListener listener) {
        pull = view;
        view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        view.addItemDecoration(new RecyclerViewDivider(this));
        view.setOnPullRefreshListener(listener);
    }

    public void initTB(String title, String other) {
        ((TextView) findViewById(R.id.title)).setText(title);
        ((TextView) findViewById(R.id.other)).setText(other);
        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.back:
                        onBack();
                        finish();
                        break;
                    case R.id.title:
                        onTitle();
                        break;
                    case R.id.other:
                        onOther();
                        break;
                }
            }
        };
        findViewById(R.id.back).setOnClickListener(click);
        findViewById(R.id.title).setOnClickListener(click);
        findViewById(R.id.other).setOnClickListener(click);
    }

    public void onBack() {
    }

    public void onTitle() {
    }

    public void onOther() {
    }

    public void onRefresh() {
    }


    public int getWindW() {
        WindowManager wm1 = this.getWindowManager();
        int width1 = wm1.getDefaultDisplay().getWidth();
        return width1;
    }


    public void showHint(String text) {
        Snackbar.make(recy, text, Snackbar.LENGTH_SHORT).show();
    }


}
