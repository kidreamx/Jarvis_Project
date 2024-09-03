package com.cookandroid.jarvis_project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.cookandroid.jarvis_project.database.bus_tip_list;

public class bus_main extends AppCompatActivity {
    Button topbtn, btn1, btn2, btn3, btn4;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.bus_main);

        topbtn = (Button) findViewById(R.id.button);                //버스 정보로 돌아가기 (배너)
        btn1 = (Button) findViewById(R.id.button1);                // 정류장위치 경로안내
        btn2 = (Button) findViewById(R.id.button2);                // 셔틀버스 TIP
        btn3 = (Button) findViewById(R.id.button3);                //버스 시간표
        btn4 = (Button) findViewById(R.id.button4);                //버스출발시간알림서비스

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        topbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                //버스 정보로 돌아가기 (배너)
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 정류장위치 경로안내
                Intent intent = new Intent(getApplicationContext(),mainmap.class);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), bus_tip_list.class);
                startActivity(intent);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //버스 시간표
                Intent intent = new Intent(getApplicationContext(),time.class);
                startActivity(intent);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 버스 알람

                Intent intent = new Intent(getApplicationContext(),schoolAlarm.class);
                startActivity(intent);

            }
        });
    }
}