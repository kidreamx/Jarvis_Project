package com.cookandroid.jarvis_project;

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

public class engineercafe extends AppCompatActivity {
    WebView webView;
    Button engineering_back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.engineercafe);
        webView = (WebView)findViewById(R.id.Engineeringcafe);
        engineering_back = (Button)findViewById(R.id.engineering_back);
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSet = webView.getSettings();
        webSet.setBuiltInZoomControls(true);
        webView.loadUrl("https://www3.chosun.ac.kr/chosun/607/subview.do");
        engineering_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Restaurant.class);
                startActivity(intent);
            }
        });

    }
}
