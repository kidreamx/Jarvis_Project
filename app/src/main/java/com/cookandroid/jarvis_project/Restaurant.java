package com.cookandroid.jarvis_project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Restaurant extends AppCompatActivity {
    Button Ebutton;
    Button Gbutton;
    Button Bbutton;
    Button button;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant);

        button = (Button)findViewById(R.id.restaurant_back);
         Ebutton = (Button)findViewById(R.id.engineering);
        Gbutton = (Button)findViewById(R.id.global);
        Bbutton = (Button)findViewById(R.id.white);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        // 웹뷰 설정

        // 버튼 클릭 이벤트 설정
        Ebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),engineercafe.class);
                startActivity(intent);
            }
        });

        Gbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),globalcafe.class);
                startActivity(intent);

            }
        });

        Bbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), whitecafe.class);
                startActivity(intent);
            }
        });
    }
}
