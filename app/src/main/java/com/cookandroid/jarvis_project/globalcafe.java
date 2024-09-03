package com.cookandroid.jarvis_project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class globalcafe extends AppCompatActivity {
    WebView webView;
    Button global_back;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.globalcafe);
        global_back = (Button)findViewById(R.id.global_back);
        webView = (WebView)findViewById(R.id.globalcafe);
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSet = webView.getSettings();
        webSet.setBuiltInZoomControls(true);
        webView.loadUrl("https://www3.chosun.ac.kr/chosun/608/subview.do");
        global_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Restaurant.class);
                startActivity(intent);
            }
        });

    }
}
