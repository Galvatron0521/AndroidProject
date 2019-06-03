package com.shdjrmyy.qgw.CompanyProject.HomePage.activity;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.shdjrmyy.qgw.CompanyProject.R;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.Base;
import com.shdjrmyy.qgw.CompanyProject.UtilsFolder.NetWorkStateReceiver;
import com.shdjrmyy.qgw.CompanyProject.ViewFolder.FuncUtil;

public class CRFManageActivity extends AppCompatActivity implements View.OnClickListener {

    private WebView webView;
    private ImageView back;
    private TextView title;
    private TextView other;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crf_manage);
        initView();

    }

    private void initView() {
        FuncUtil.iniSystemBar(this, R.color.white);
        webView = findViewById(R.id.crf_web);
        back = findViewById(R.id.back);
        title = findViewById(R.id.title);
        other = findViewById(R.id.other);
        title.setText("CRF管理");
        back.setOnClickListener(this);
        other.setVisibility(View.GONE);
        initCRF();
    }

    private void initCRF() {
        WebSettings webSettings = webView.getSettings();
        String md5Str = Base.getMD5Str();
        String timeSpan = Base.getTimeSpan();
        StringBuilder builder = new StringBuilder();
        builder.append(Base.url).append("?act=").append("selectCRFList").append("&pageNo=").append("0")
                .append("&data={\"appKey\":\"").append(md5Str).append("\",").append("\"timeSpan\":\"")
                .append(timeSpan).append("\"}&").append("moduleCode=").append("SP0403");
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
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
        }

    }
}
