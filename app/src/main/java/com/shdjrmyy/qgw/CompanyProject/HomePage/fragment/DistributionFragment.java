package com.shdjrmyy.qgw.CompanyProject.HomePage.fragment;

import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.shdjrmyy.qgw.CompanyProject.BaseFolder.Base;
import com.shdjrmyy.qgw.CompanyProject.R;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.BaseFragment;


/**
 * Created by 16001 on 2017/10/18 0018.
 */

public class DistributionFragment extends BaseFragment {


    @Override
    protected int setLayout() {
        return R.layout.fragemnt_distribution;
    }

    WebView webView;

    @Override
    protected void initView(View view) {
        webView = view.findViewById(R.id.web);
    }

    @Override
    protected void initData() {
        WebSettings webSettings = webView.getSettings();
        String md5Str = Base.getMD5Str();
        String timeSpan = Base.getTimeSpan();
        StringBuilder builder = new StringBuilder();
        builder.append(Base.url).append("?act=").append("patientsMap").append("&data={\"appKey\":\"")
                .append(md5Str).append("\",").append("\"timeSpan\":\"").append(timeSpan).append("\"}");
        webView.loadUrl(builder.toString());
        webView.getSettings().setSupportZoom(true);
        // 设置出现缩放工具
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
    }

    @Override
    public void onPause() {
        super.onPause();
        webView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        webView.destroy();
    }

    public String getTitle() {
        return "患者分布";
    }
}
