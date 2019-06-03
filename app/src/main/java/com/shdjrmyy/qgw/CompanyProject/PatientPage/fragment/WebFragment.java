package com.shdjrmyy.qgw.CompanyProject.PatientPage.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.shdjrmyy.qgw.CompanyProject.R;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.Base;

/**
 * Created by Administrator on 2017/12/1.
 */

public class WebFragment extends Fragment implements View.OnClickListener {

    private WebView webView;
    private ImageView imageView;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        Bundle bundle = getArguments();
        int ID = bundle.getInt("ID");
        String treat = bundle.getString("link");
        String md5Str = Base.getMD5Str();
        String timeSpan = Base.getTimeSpan();
        StringBuilder builder = new StringBuilder();
        builder.append(Base.url).append(treat).append("&data={\"appKey\":\"").append(md5Str).append("\",")
                .append("\"timeSpan\":\"").append(timeSpan).append("\"}&").append("patientID=").append(ID)
                .append("&diseasesid=").append("").append("&userId=").append(Base.getUserID());
        webView = view.findViewById(R.id.webView);
        imageView = view.findViewById(R.id.goBack);
        progressBar = view.findViewById(R.id.progressBar);
        imageView.setOnClickListener(this);
        WebSettings webSettings = webView.getSettings();
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
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO 自动生成的方法存根

                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);//加载完网页进度条消失
                } else {
                    progressBar.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    progressBar.setProgress(newProgress);//设置进度值
                }

            }
        });
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); //支持内容重新布局
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.goBack:
                webView.goBack();
                break;
        }
    }
}

