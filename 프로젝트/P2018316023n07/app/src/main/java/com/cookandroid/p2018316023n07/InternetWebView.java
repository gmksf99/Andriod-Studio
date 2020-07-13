package com.cookandroid.p2018316023n07;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class InternetWebView extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.internet_webview);

        WebView webView = (WebView) findViewById(R.id.webView);
        //인텐트로 정보를 넘겨받음
        Intent intent = getIntent();
        String blog_url = intent.getExtras().getString("blog_url");
        webView.getSettings().setJavaScriptEnabled(true);   //http:때문에 해줘야댐
        webView.loadUrl(blog_url);
        webView.setWebChromeClient(new WebChromeClient()); //http:때문에 해줘야댐
        webView.setWebViewClient(new WebViewClient());

        Button btnFin = findViewById(R.id.button3);
        btnFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

