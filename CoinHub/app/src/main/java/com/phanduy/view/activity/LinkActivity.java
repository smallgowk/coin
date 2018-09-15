package com.phanduy.view.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.phanduy.store.AppSharePreference;
import com.phanduy.view.base.BABaseActivity;

import com.phanduy.coinhub.R;

/**
 * Created by duyuno on 11/22/16.
 */
public class LinkActivity extends BABaseActivity {

    AppSharePreference appSharePreference;

    WebView webView;
    TextView txtTitleHeader;
    String content;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        appSharePreference = com.phanduy.store.AppSharePreference.getInstance(this);
        setContentView(R.layout.layout_link_activity);
        initView();
        initData();
    }

    public void initView() {
        webView = (WebView) findViewById(R.id.webView);
        txtTitleHeader = (TextView) findViewById(R.id.txtTitleHeader);
    }
    public void initData() {
        txtTitleHeader.setText(getString(R.string.labelServiceInfo));
        String url = "http://thethao.vnexpress.net/tin-tuc/giai-ngoai-hang-anh/vuot-torres-morata-thanh-ky-luc-chuyen-nhuong-cua-chelsea-3615836.html";
        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            public void onPageFinished(WebView view, String url) {
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

            }
        });


        webView.loadUrl(url);
    }

    public void onClickBack(View v) {
        onKeyBack();
    }

    @Override
    public void onKeyBack() {
        finish();
    }

}
