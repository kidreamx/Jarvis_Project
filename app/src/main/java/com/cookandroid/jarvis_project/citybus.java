package com.cookandroid.jarvis_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

public class citybus extends Activity {
    Button button1, button_main, button12, button13, button14, button15,
            button16, button419, button61, button57,button_check;
    TableLayout tableLayout57,tableLayout61, tableLayout419; // 테이블 레이아웃을 나타내는 객체
    TextView textView2,textView_419, textView_61,textView_57;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bus_city);

        button_main= findViewById(R.id.button_main); //버스시간표
        textView2= findViewById(R.id.textView2); //버스시간표
        //button_main = findViewById(R.id.button_main); //버스선택
        button12 = findViewById(R.id.button12); //57
        button13 = findViewById(R.id.button13); //61
        button14 = findViewById(R.id.button14); //419
        button15 = findViewById(R.id.button15); //셔틀버스
        button16 = findViewById(R.id.button16); //시내버스
        button419 = findViewById(R.id.button419); //시간표속 버튼 419
        button61 = findViewById(R.id.button61); //시간표속 버튼 61
        button57 = findViewById(R.id.button57); //시간표속 버튼 57
        // button_check = findViewById(R.id.button_check); //시간표속 버튼 57


        textView_419 = findViewById(R.id.textView_419);
        textView_61 = findViewById(R.id.textView_61);
        textView_57 = findViewById(R.id.textView_57);

        tableLayout57 = findViewById(R.id.tableLayout57);
        tableLayout61 = findViewById(R.id.tableLayout61);
        tableLayout419 = findViewById(R.id.tableLayout419);

        textView_419.setVisibility(View.GONE); // 419시간표 버튼이 안보이도록
        textView_57.setVisibility(View.GONE); // 61시간표 버튼이 안보이도록
        textView_61.setVisibility(View.GONE); // 61시간표 버튼이 안보이도록


        button419.setVisibility(View.GONE); // 419시간표 버튼이 안보이도록
        button61.setVisibility(View.GONE); // 61시간표 버튼이 안보이도록
        button57.setVisibility(View.GONE); // 61시간표 버튼이 안보이도록


        tableLayout57.setVisibility(View.GONE); //처음에 테이블 레이아웃이 안보이도록
        tableLayout61.setVisibility(View.GONE);
        tableLayout419.setVisibility(View.GONE);



//        button11.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) { // 버스선택을 클릭했을 때 작동할 코드
//
//                button12.setVisibility(View.VISIBLE); //57번이 보이도록
//                button13.setVisibility(View.VISIBLE); // 61번이 보이도록
//                button14.setVisibility(View.VISIBLE); //419번이 보이도록
//
//            }
//        });

        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // 57번을 클릭했을 때 작동할 코드

                button_main.setVisibility(View.GONE);
                textView_57.setVisibility(View.VISIBLE);
                textView2.setVisibility(View.GONE);
                textView_61.setVisibility(View.GONE);
                textView_419.setVisibility(View.GONE);
                //button1.setVisibility(View.GONE); //버스시간표가 안보이도록
                //button11.setVisibility(View.GONE); // 버스가 안보이도록
                button12.setVisibility(View.GONE); // 57이 안보이도록
                button13.setVisibility(View.GONE); // 61이 안보이도록
                button14.setVisibility(View.GONE); // 419이 안보이도록
                button57.setVisibility(View.VISIBLE); // 61이 보이도록
                tableLayout57.setVisibility(View.VISIBLE);// 57번 테이블이 보이도록

            }
        });

        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // 61번을 클릭했을 때 작동할 코드

                button_main.setVisibility(View.GONE);
                textView_61.setVisibility(View.VISIBLE);
                textView2.setVisibility(View.GONE);
                textView_57.setVisibility(View.GONE);
                textView_419.setVisibility(View.GONE);
                //button1.setVisibility(View.GONE); //버스시간표가 안보이도록
                //button11.setVisibility(View.GONE); // 버스가 안보이도록
                button12.setVisibility(View.GONE); // 57이 안보이도록
                button13.setVisibility(View.GONE); // 61이 안보이도록
                button14.setVisibility(View.GONE); // 419이 안보이도록
                button61.setVisibility(View.VISIBLE); // 61이 보이도록
                tableLayout61.setVisibility(View.VISIBLE);// 61번 테이블이 보이도록

            }
        });

        button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // 419번을 클릭했을 때 작동할 코드

                button_main.setVisibility(View.GONE);
                textView_419.setVisibility(View.VISIBLE);

                textView2.setVisibility(View.GONE);
                textView_61.setVisibility(View.GONE);
                textView_57.setVisibility(View.GONE);
                //button1.setVisibility(View.GONE); //버스시간표가 안보이도록
                // button11.setVisibility(View.GONE); // 버스가 안보이도록
                button12.setVisibility(View.GONE); // 57이 안보이도록
                button13.setVisibility(View.GONE); // 61이 안보이도록
                button14.setVisibility(View.GONE); // 419이 안보이도록
                button419.setVisibility(View.VISIBLE); // 419이 보이도록
                tableLayout419.setVisibility(View.VISIBLE);// 419번 테이블이 보이도록

            }
        });

        button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // 셔틀버스를 클릭했을 때 작동할 코드

                Intent intent = new Intent(getApplicationContext(), time.class);
                startActivity(intent);
            }


        });

        button57.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // 시간표속 57번을 클릭했을 때 작동할 코드

                Intent intent =new Intent(getApplicationContext(),
                        citybus.class);

                startActivity(intent);
            }

        });

        button419.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // 419번을 클릭했을 때 작동할 코드

                Intent intent =new Intent(getApplicationContext(),
                        citybus.class);

                startActivity(intent);

            }

        });

        button61.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // 61번을 클릭했을 때 작동할 코드

                Intent intent =new Intent(getApplicationContext(),
                        citybus.class);

                startActivity(intent);

            }

        });
        button_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =new Intent(getApplicationContext(),
                        time.class);

                startActivity(intent);

            }

        });
//        button_check.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // 버튼 클릭 시 다음 화면으로 이동하는 Intent 생성
//                Intent intent = new Intent(getApplicationContext(), busgo.class);
//                startActivity(intent); // 다음 화면으로 전환
//            }
//        });





    }
}
