package com.cookandroid.jarvis_project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class time extends AppCompatActivity {
    Button button1, button2, button3, button4, button5, button6,button7,//button_main,
            button_social, button_global,button_m, button_middle;


    TableLayout tableLayout_main, tableLayout_social,tableLayout_global;

    TextView  textView2, textView_middle,textView_social,textView_global;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.time);


        button3 = findViewById(R.id.button3); //본관행
        button4 = findViewById(R.id.button4); //사회과학대행
        button5 = findViewById(R.id.button5); // 글로벌하우스행
        button6 = findViewById(R.id.button6); // 셔틀버스
        button7 = findViewById(R.id.button7); // 시내버스
        button_social = findViewById(R.id.button_social); // 사회과학대행 시간표
        button_global = findViewById(R.id.button_global); // 글로벌행 시간표
        button_m = findViewById(R.id.button_m);
        button_middle =findViewById(R.id.button_middle);

        tableLayout_main = findViewById(R.id.tableLayout_main);
        tableLayout_social = findViewById(R.id.tableLayout_social);
        tableLayout_global = findViewById(R.id.tableLayout_global);



        textView2 = findViewById(R.id.textView2);
        textView_middle = findViewById(R.id.textView_middle);
        textView_social = findViewById(R.id.textView_social);
        textView_global = findViewById(R.id.textView_global);

        textView_global.setVisibility(View.GONE);
        button_global.setVisibility(View.GONE);

        textView_social.setVisibility(View.GONE);
        button_social.setVisibility(View.GONE);

        textView_middle.setVisibility(View.GONE);
        button_middle.setVisibility(View.GONE);



        tableLayout_main.setVisibility(View.GONE); //처음에 본관행 테이블이 안보이도록

        tableLayout_social.setVisibility(View.GONE); //처음에 사회과학대행 테이블이 안보이도록
        button_social.setVisibility(View.GONE);

        tableLayout_global.setVisibility(View.GONE); //처음에 글로벌행 테이블이 안보이도록
        button_global.setVisibility(View.GONE);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // 본관행을 클릭했을 때


                button3.setVisibility(View.GONE); // 본관행
                button4.setVisibility(View.GONE); // 사회과학대행
                button5.setVisibility(View.GONE); // 글로벌행
                tableLayout_main.setVisibility(View.VISIBLE);// 사회과학대행 시간표가 보이도록
                button6.setVisibility(View.GONE); // 셔틀버스
                button7.setVisibility(View.GONE); //시내버스

                textView_middle.setVisibility(View.VISIBLE);// 본관행 글자
                button_middle.setVisibility(View.VISIBLE);// 본관행 글자

                button_social.setVisibility(View.GONE);

                button_global.setVisibility(View.GONE);
                button_m.setVisibility(View.GONE);



            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // 사회과학대행을 클릭했을 때


                button3.setVisibility(View.GONE); // 본관행
                button4.setVisibility(View.GONE); // 사회과학대행
                button5.setVisibility(View.GONE); // 글로벌행
                button_social.setVisibility(View.VISIBLE); // 사회과학대행 버튼이 보이도록
                tableLayout_social.setVisibility(View.VISIBLE);// 사회과학대행 시간표가 보이도록
                button6.setVisibility(View.GONE); // 셔틀버스
                button7.setVisibility(View.GONE); // 시내버스
                textView_social.setVisibility(View.VISIBLE);// 사회과학대행 글자

                button_middle.setVisibility(View.GONE);

                button_global.setVisibility(View.GONE);
                button_m.setVisibility(View.GONE);

            }
        });


        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // 글로벌행을 클릭했을 때


                button3.setVisibility(View.GONE); // 본관행
                button4.setVisibility(View.GONE); // 사회과학대행
                button5.setVisibility(View.GONE); // 글로벌행
                button_global.setVisibility(View.VISIBLE); // 글로벌행 버튼이 보이도록
                tableLayout_global.setVisibility(View.VISIBLE);// 글로벌행행 시간표가 보이도록
                button6.setVisibility(View.GONE); // 셔틀버스
                button7.setVisibility(View.GONE); //시내버스
                textView_global.setVisibility(View.VISIBLE);// 사회과학대행 글자

                button_social.setVisibility(View.GONE);

                button_middle.setVisibility(View.GONE);
                button_m.setVisibility(View.GONE);

            }
        });

        button_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // 본관행 버튼을 클릭했을 때

                Intent intent = new Intent(getApplicationContext(), bus_main.class);
                startActivity(intent);



            }
        });

        button_middle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // 본관행 버튼을 클릭했을 때

                Intent intent = new Intent(getApplicationContext(), time.class);
                startActivity(intent);



            }
        });

        button_social.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // 사회과학대행 버튼을 클릭했을 때

                Intent intent = new Intent(getApplicationContext(), time.class);
                startActivity(intent);



            }
        });

        button_global.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // 글로벌행 버튼을 클릭했을 때

                Intent intent = new Intent(getApplicationContext(), time.class);
                startActivity(intent);



            }
        });






        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // 시내버스을 클릭했을 때 작동할 코드

                Intent intent =new Intent(getApplicationContext(),
                        citybus.class);

                startActivity(intent);


            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


}