package com.cookandroid.jarvis_project;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.core.app.NotificationCompat;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class busgo extends Activity {
    private String TAG = this.getClass().getSimpleName();


    //현재 시간,분 변수선언
    int currHour, currMinute;


    private AlarmManager alarmManager;

    private TimePickerDialog.OnTimeSetListener timeCallbackMethod; //알람시간 변경선택 타임피커 콜백처리 변수 선언
    RadioButton  button57, button61, button419;
    Button button_alarm_57,button_alarm_61,button_alarm_419,button_main,button_school,button_city;
    AutoCompleteTextView dropdown_hour,dropdown_minute,dropdown_ago,dropdown_hour_61,dropdown_minute_61,dropdown_ago_61,
            dropdown_hour_419,dropdown_minute_419,dropdown_ago_419;
    TextView text_hour, text_minute, text_ago,text_hour_61, text_minute_61, text_ago_61,
            text_hour_419, text_minute_419, text_ago_419;



    GregorianCalendar mCalender;

    NotificationManager notificationManager;
    NotificationCompat.Builder builder;
    PendingIntent pendingIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.go_bus);

//        button_choice = findViewById(R.id.button_choice);
        button57 = findViewById(R.id.button57);
        button61 = findViewById(R.id.button61);
        button419 = findViewById(R.id.button419);
        button_alarm_57 = findViewById(R.id.button_alarm_57);
        button_alarm_61 = findViewById(R.id.button_alarm_61);
        button_alarm_419 = findViewById(R.id.button_alarm_419);


        button_main = findViewById(R.id.button_main);

        button_school = findViewById(R.id.button_school);
        button_city = findViewById(R.id.button_city);

        dropdown_hour = findViewById(R.id.dropdown_hour);
        dropdown_minute = findViewById(R.id.dropdown_minute);
        dropdown_ago = findViewById(R.id.dropdown_ago);


        dropdown_hour_61 = findViewById(R.id.dropdown_hour_61);
        dropdown_minute_61 = findViewById(R.id.dropdown_minute_61);
        dropdown_ago_61 = findViewById(R.id.dropdown_ago_61);

        dropdown_hour_419 = findViewById(R.id.dropdown_hour_419);
        dropdown_minute_419 = findViewById(R.id.dropdown_minute_419);
        dropdown_ago_419 = findViewById(R.id.dropdown_ago_419);

        text_hour = findViewById(R.id.text_hour);
        text_minute = findViewById(R.id.text_minute);
        text_ago = findViewById(R.id.text_ago);

        text_hour_61 = findViewById(R.id.text_hour_61);
        text_minute_61 = findViewById(R.id.text_minute_61);
        text_ago_61 = findViewById(R.id.text_ago_61);

        text_hour_419 = findViewById(R.id.text_hour_419);
        text_minute_419 = findViewById(R.id.text_minute_419);
        text_ago_419 = findViewById(R.id.text_ago_419);


        String[] hour_57 = {"06", "07", "08", "09", "10", "11", "12",
                "13", "14", "15", "16", "17", "18", "19", "20", "21", "22"};

        String[] minute_57 = {"00", "05", "08", "10", "11", "15", "16", "20", "23", "25", "30", "34", "35",
                "40", "42", "45", "47", "50", "55",};

        String[] ago_57 = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10"};

        String[] hour_61 = {"06", "07", "08", "09", "10", "11", "12",
                "13", "14", "15", "16", "17", "18", "19", "20", "21", "22"};

        String[] minute_61 = {"00", "02", "04", "05", "07", "09", "13", "20", "28", "30", "31", "33", "35", "36", "38", "41", "45", "55"
        };

        String[] ago_61 = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10"};

        String[] hour_419 = {"06", "07", "08", "09", "10", "11", "12",
                "13", "14", "15", "16", "17", "18", "19", "20", "21", "22"};

        String[] minute_419 = {"00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55",};

        String[] ago_419 = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10"};


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, hour_57);
        dropdown_hour.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, minute_57);
        dropdown_minute.setAdapter(adapter2);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, ago_57);
        dropdown_ago.setAdapter(adapter3);


        ArrayAdapter<String> adapter4 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, hour_61);
        dropdown_hour_61.setAdapter(adapter4);

        ArrayAdapter<String> adapter5 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, minute_61);
        dropdown_minute_61.setAdapter(adapter5);

        ArrayAdapter<String> adapter6 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, ago_61);
        dropdown_ago_61.setAdapter(adapter6);

        ArrayAdapter<String> adapter7 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, hour_419);
        dropdown_hour_419.setAdapter(adapter7);

        ArrayAdapter<String> adapter8 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, minute_419);
        dropdown_minute_419.setAdapter(adapter8);

        ArrayAdapter<String> adapter9 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, ago_419);
        dropdown_ago_419.setAdapter(adapter9);



        dropdown_hour.setVisibility(View.GONE); // 처음에 시간이 안보이도록
        dropdown_minute.setVisibility(View.GONE); // 처음에 분이 안보이도록
        dropdown_ago.setVisibility(View.GONE); // 처음에 몇분전이 안보이도록

        text_hour.setVisibility(View.GONE); // 처음에 시간이 안보이도록
        text_minute.setVisibility(View.GONE); // 처음에 분이 안보이도록
        text_ago.setVisibility(View.GONE); // 처음에 몇분전이 안보이도록

        text_hour_61.setVisibility(View.GONE); // 처음에 시간이 안보이도록
        text_minute_61.setVisibility(View.GONE); // 처음에 분이 안보이도록
        text_ago_61.setVisibility(View.GONE); // 처음에 몇분전이 안보이도록

        dropdown_hour_61.setVisibility(View.GONE); // 처음에 시간이 안보이도록
        dropdown_minute_61.setVisibility(View.GONE); // 처음에 분이 안보이도록
        dropdown_ago_61.setVisibility(View.GONE); // 처음에 몇분전이 안보이도록

        text_hour_419.setVisibility(View.GONE); // 처음에 시간이 안보이도록
        text_minute_419.setVisibility(View.GONE); // 처음에 분이 안보이도록
        text_ago_419.setVisibility(View.GONE); // 처음에 몇분전이 안보이도록

        dropdown_hour_419.setVisibility(View.GONE); // 처음에 시간이 안보이도록
        dropdown_minute_419.setVisibility(View.GONE); // 처음에 분이 안보이도록
        dropdown_ago_419.setVisibility(View.GONE); // 처음에 몇분전이 안보이도록

        button_alarm_57.setVisibility(View.GONE); // 처음에 57이 안보이도록
        button_alarm_61.setVisibility(View.GONE); // 처음에 61이 안보이도록
        button_alarm_419.setVisibility(View.GONE); // 처음에 419이 안보이도록





        button57.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // 버스선택을 클릭했을 때 작동할 코드

                dropdown_hour.setVisibility(View.VISIBLE); //시간선택이 보이도록
                dropdown_minute.setVisibility(View.VISIBLE); // 분선택이 보이도록
                dropdown_ago.setVisibility(View.VISIBLE); //몇분전선택이 보이도록

                text_hour.setVisibility(View.VISIBLE); //시 글자가 보이도록
                text_minute.setVisibility(View.VISIBLE); // 분글자가 보이도록
                text_ago.setVisibility(View.VISIBLE); //몇분전 글자가 보이도록


                text_hour_419.setVisibility(View.GONE); // 처음에 시간이 안보이도록
                text_minute_419.setVisibility(View.GONE); // 처음에 분이 안보이도록
                text_ago_419.setVisibility(View.GONE); // 처음에 몇분전이 안보이도록

                dropdown_hour_419.setVisibility(View.GONE); // 처음에 시간이 안보이도록
                dropdown_minute_419.setVisibility(View.GONE); // 처음에 분이 안보이도록
                dropdown_ago_419.setVisibility(View.GONE); // 처음에 몇분전이 안보이도록

                text_hour_61.setVisibility(View.GONE); // 처음에 시간이 안보이도록
                text_minute_61.setVisibility(View.GONE); // 처음에 분이 안보이도록
                text_ago_61.setVisibility(View.GONE); // 처음에 몇분전이 안보이도록

                dropdown_hour_61.setVisibility(View.GONE); // 처음에 시간이 안보이도록
                dropdown_minute_61.setVisibility(View.GONE); // 처음에 분이 안보이도록
                dropdown_ago_61.setVisibility(View.GONE); // 처음에 몇분전이 안보이도록


                button_alarm_57.setVisibility(View.VISIBLE); //57번 예약버튼이 보이도록
                button_alarm_61.setVisibility(View.GONE); // 61번 예약버튼이 안보이도록
                button_alarm_419.setVisibility(View.GONE); // 419번 예약버튼이 안보이도록




            }
        });


        button61.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // 버스선택을 클릭했을 때 작동할 코드

                dropdown_hour_61.setVisibility(View.VISIBLE); //시간선택이 보이도록
                dropdown_minute_61.setVisibility(View.VISIBLE); // 분선택이 보이도록
                dropdown_ago_61.setVisibility(View.VISIBLE); //몇분전선택이 보이도록

                text_hour_61.setVisibility(View.VISIBLE); //시 글자가 보이도록
                text_minute_61.setVisibility(View.VISIBLE); // 분글자가 보이도록
                text_ago_61.setVisibility(View.VISIBLE); //몇분전 글자가 보이도록

                text_hour_419.setVisibility(View.GONE); // 처음에 시간이 안보이도록
                text_minute_419.setVisibility(View.GONE); // 처음에 분이 안보이도록
                text_ago_419.setVisibility(View.GONE); // 처음에 몇분전이 안보이도록

                dropdown_hour_419.setVisibility(View.GONE); // 처음에 시간이 안보이도록
                dropdown_minute_419.setVisibility(View.GONE); // 처음에 분이 안보이도록
                dropdown_ago_419.setVisibility(View.GONE); // 처음에 몇분전이 안보이도록

                dropdown_hour.setVisibility(View.GONE); // 처음에 시간이 안보이도록
                dropdown_minute.setVisibility(View.GONE); // 처음에 분이 안보이도록
                dropdown_ago.setVisibility(View.GONE); // 처음에 몇분전이 안보이도록

                text_hour.setVisibility(View.GONE); // 처음에 시간이 안보이도록
                text_minute.setVisibility(View.GONE); // 처음에 분이 안보이도록
                text_ago.setVisibility(View.GONE); // 처음에 몇분전이 안보이도록

                button_alarm_61.setVisibility(View.VISIBLE); //61번 예약버튼이 보이도록
                button_alarm_57.setVisibility(View.GONE); // 57 예약버튼이 안보이도록
                button_alarm_419.setVisibility(View.GONE); // 419번 예약버튼이 안보이도록

            }
        });

        button419.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // 버스선택을 클릭했을 때 작동할 코드

                dropdown_hour_419.setVisibility(View.VISIBLE); //시간선택이 보이도록
                dropdown_minute_419.setVisibility(View.VISIBLE); // 분선택이 보이도록
                dropdown_ago_419.setVisibility(View.VISIBLE); //몇분전선택이 보이도록

                text_hour_419.setVisibility(View.VISIBLE); //시 글자가 보이도록
                text_minute_419.setVisibility(View.VISIBLE); // 분글자가 보이도록
                text_ago_419.setVisibility(View.VISIBLE); //몇분전 글자가 보이도록

                dropdown_hour.setVisibility(View.GONE); // 처음에 시간이 안보이도록
                dropdown_minute.setVisibility(View.GONE); // 처음에 분이 안보이도록
                dropdown_ago.setVisibility(View.GONE); // 처음에 몇분전이 안보이도록

                text_hour.setVisibility(View.GONE); // 처음에 시간이 안보이도록
                text_minute.setVisibility(View.GONE); // 처음에 분이 안보이도록
                text_ago.setVisibility(View.GONE); // 처음에 몇분전이 안보이도록

                text_hour_61.setVisibility(View.GONE); // 처음에 시간이 안보이도록
                text_minute_61.setVisibility(View.GONE); // 처음에 분이 안보이도록
                text_ago_61.setVisibility(View.GONE); // 처음에 몇분전이 안보이도록

                dropdown_hour_61.setVisibility(View.GONE); // 처음에 시간이 안보이도록
                dropdown_minute_61.setVisibility(View.GONE); // 처음에 분이 안보이도록
                dropdown_ago_61.setVisibility(View.GONE); // 처음에 몇분전이 안보이도록

                button_alarm_419.setVisibility(View.VISIBLE); //419번 예약버튼이 보이도록
                button_alarm_57.setVisibility(View.GONE); // 57 예약버튼이 안보이도록
                button_alarm_61.setVisibility(View.GONE); // 61번 예약버튼이 안보이도록



            }
        });


        dropdown_hour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dropdown_hour.showDropDown();
                dropdown_hour.setDropDownHeight(500);
            }
        });

        dropdown_minute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dropdown_minute.showDropDown();
                dropdown_minute.setDropDownHeight(500);
            }
        });

        dropdown_ago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dropdown_ago.showDropDown();
                dropdown_ago.setDropDownHeight(500);
            }
        });

        dropdown_hour.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedText = (String) parent.getItemAtPosition(position);

            }
        });

        dropdown_minute.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedText = (String) parent.getItemAtPosition(position);

            }
        });

        dropdown_ago.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedText = (String) parent.getItemAtPosition(position);

            }
        });

        dropdown_hour_61.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dropdown_hour_61.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(dropdown_hour_61, InputMethodManager.SHOW_IMPLICIT);
                dropdown_hour_61.showDropDown();
                dropdown_hour_61.setDropDownHeight(500);
            }
        });

        dropdown_minute_61.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dropdown_minute_61.showDropDown();
                dropdown_minute_61.setDropDownHeight(500);
            }
        });

        dropdown_ago_61.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dropdown_ago_61.showDropDown();
                dropdown_ago_61.setDropDownHeight(500);
            }
        });

        dropdown_hour_61.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedText = (String) parent.getItemAtPosition(position);

            }
        });

        dropdown_minute_61.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedText = (String) parent.getItemAtPosition(position);

            }
        });

        dropdown_ago_61.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedText = (String) parent.getItemAtPosition(position);

            }
        });


        dropdown_hour_419.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dropdown_hour_419.showDropDown();
                dropdown_hour_419.setDropDownHeight(500);
            }
        });

        dropdown_minute_419.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dropdown_minute_419.showDropDown();
                dropdown_minute_419.setDropDownHeight(500);
            }
        });

        dropdown_ago_419.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dropdown_ago_419.showDropDown();
                dropdown_ago_419.setDropDownHeight(500);
            }
        });

        dropdown_hour_419.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedText = (String) parent.getItemAtPosition(position);

            }
        });

        dropdown_minute_419.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedText = (String) parent.getItemAtPosition(position);

            }
        });

        dropdown_ago_419.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedText = (String) parent.getItemAtPosition(position);

            }
        });

        button_school.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 버튼 클릭 시 다음 화면으로 이동하는 Intent 생성
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent); // 다음 화면으로 전환
            }
        });

        button_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 버튼 클릭 시 다음 화면으로 이동하는 Intent 생성
                Intent intent = new Intent(getApplicationContext(), citybus.class);
                startActivity(intent); // 다음 화면으로 전환
            }
        });

        button_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 버튼 클릭 시 다음 화면으로 이동하는 Intent 생성
                Intent intent = new Intent(getApplicationContext(), citybus.class);
                startActivity(intent); // 다음 화면으로 전환
            }
        });

        //인스턴스 초기화
        notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        mCalender = new GregorianCalendar();

        Log.v("HelloAlarmActivity", mCalender.getTime().toString());


        button_alarm_57.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 사용자가 선택한 시간과 분, 그리고 몇 분 전에 알람을 울릴지에 대한 값을 가져옴
                String selectedHour = dropdown_hour.getText().toString();
                String selectedMinute = dropdown_minute.getText().toString();
                String selectedAgo = dropdown_ago.getText().toString();


                try {

                    // 시간, 분, 알람 전 시간을 정수로 변환
                    int hour = Integer.parseInt(selectedHour);
                    int minute = Integer.parseInt(selectedMinute);
                    int agoMinutes = Integer.parseInt(selectedAgo);

                    if(minute-agoMinutes<0){
                        hour--;
                        minute = 60 +minute - agoMinutes;
                    }
                    else{
                        minute = minute-agoMinutes;
                    }
                    // 현재 시간을 기준으로 캘린더 객체를 설정
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    LocalDate localDate = LocalDate.now();
                    Date datetime = null;

                    try {
                        datetime = dateFormat.parse(localDate + " " +hour+":"+minute+":"+"00");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(datetime);
                    // 알람을 설정한 시간에서 'agoMinutes'만큼 이전으로 설정
                    Log.e("알림1", datetime+"");
                    if (calendar.getTimeInMillis() <= System.currentTimeMillis()) { // 설정된 시간이 현재 시간보다 이전인지 확인
                        Toast.makeText(busgo.this, "설정된 시간이 현재 시간보다 이전입니다. 올바른 시간을 설정해 주세요.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    SimpleDateFormat dataForm5 = new SimpleDateFormat("HH:mm:ss");
                    // 알람을 수신할 리시버 인텐트 생성
                    Intent receiverIntent = new Intent(getApplicationContext(), AlarmReceiver.class);
                    receiverIntent.putExtra("content",Integer.toString(agoMinutes));
                    int requestCode = (int) System.currentTimeMillis();
                    pendingIntent = PendingIntent.getBroadcast(busgo.this, requestCode, receiverIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
                    // 알람 매니저를 통해 알람 설정
                    alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                    if (alarmManager != null) {
                        alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
                        Log.e("알림1", dataForm5.format(calendar.getTimeInMillis()));
                        Toast.makeText(busgo.this, "57번 알람이 설정되었습니다", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(busgo.this, "알람 설정 중 오류가 발생했습니다", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button_alarm_61.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 사용자가 선택한 시간과 분, 그리고 몇 분 전에 알람을 울릴지에 대한 값을 가져옴
                String selectedHour61 = dropdown_hour_61.getText().toString();
                String selectedMinute61 = dropdown_minute_61.getText().toString();
                String selectedAgo61 = dropdown_ago_61.getText().toString();


                try {

                    // 시간, 분, 알람 전 시간을 정수로 변환
                    int hour = Integer.parseInt(selectedHour61);
                    int minute = Integer.parseInt(selectedMinute61);
                    int agoMinutes = Integer.parseInt(selectedAgo61);

                    if(minute-agoMinutes<0){
                        hour--;
                        minute = 60 +minute - agoMinutes;
                    }
                    else{
                        minute = minute-agoMinutes;
                    }
                    // 현재 시간을 기준으로 캘린더 객체를 설정
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    LocalDate localDate = LocalDate.now();
                    Date datetime = null;

                    try {
                        datetime = dateFormat.parse(localDate + " " +hour+":"+minute+":"+"00");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(datetime);
                    // 알람을 설정한 시간에서 'agoMinutes'만큼 이전으로 설정
                    Log.e("알림2", datetime+"");
                    if (calendar.getTimeInMillis() <= System.currentTimeMillis()) { // 설정된 시간이 현재 시간보다 이전인지 확인
                        Toast.makeText(busgo.this, "설정된 시간이 현재 시간보다 이전입니다. 올바른 시간을 설정해 주세요.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    SimpleDateFormat dataForm5 = new SimpleDateFormat("HH:mm:ss");
                    // 알람을 수신할 리시버 인텐트 생성
                    Intent receiverIntent = new Intent(getApplicationContext(), AlarmReceiver.class);
                    receiverIntent.putExtra("content",Integer.toString(agoMinutes));
                    int requestCode = (int) System.currentTimeMillis();
                    pendingIntent = PendingIntent.getBroadcast(busgo.this, requestCode, receiverIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
                    // 알람 매니저를 통해 알람 설정
                    alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                    if (alarmManager != null) {
                        alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
                        Log.e("알림2", dataForm5.format(calendar.getTimeInMillis()));
                        Toast.makeText(busgo.this, "61번 알람이 설정되었습니다", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(busgo.this, "알람 설정 중 오류가 발생했습니다", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button_alarm_419.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 사용자가 선택한 시간과 분, 그리고 몇 분 전에 알람을 울릴지에 대한 값을 가져옴
                String selectedHour419 = dropdown_hour_419.getText().toString();
                String selectedMinute419 = dropdown_minute_419.getText().toString();
                String selectedAgo419 = dropdown_ago_419.getText().toString();


                try {

                    // 시간, 분, 알람 전 시간을 정수로 변환
                    int hour = Integer.parseInt(selectedHour419);
                    int minute = Integer.parseInt(selectedMinute419);
                    int agoMinutes = Integer.parseInt(selectedAgo419);

                    if(minute-agoMinutes<0){
                        hour--;
                        minute = 60 +minute - agoMinutes;
                    }
                    else{
                        minute = minute-agoMinutes;
                    }
                    // 현재 시간을 기준으로 캘린더 객체를 설정
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    LocalDate localDate = LocalDate.now();
                    Date datetime = null;

                    try {
                        datetime = dateFormat.parse(localDate + " " +hour+":"+minute+":"+"00");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(datetime);
                    // 알람을 설정한 시간에서 'agoMinutes'만큼 이전으로 설정
                    Log.e("알림3", datetime+"");
                    if (calendar.getTimeInMillis() <= System.currentTimeMillis()) { // 설정된 시간이 현재 시간보다 이전인지 확인
                        Toast.makeText(busgo.this, "설정된 시간이 현재 시간보다 이전입니다. 올바른 시간을 설정해 주세요.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    SimpleDateFormat dataForm5 = new SimpleDateFormat("HH:mm:ss");
                    // 알람을 수신할 리시버 인텐트 생성
                    Intent receiverIntent = new Intent(getApplicationContext(), AlarmReceiver.class);
                    receiverIntent.putExtra("content",Integer.toString(agoMinutes));
                    int requestCode = (int) System.currentTimeMillis();
                    pendingIntent = PendingIntent.getBroadcast(busgo.this, requestCode, receiverIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
                    // 알람 매니저를 통해 알람 설정
                    alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                    if (alarmManager != null) {
                        alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
                        Log.e("알림3", dataForm5.format(calendar.getTimeInMillis()));
                        Toast.makeText(busgo.this, "419번 알람이 설정되었습니다", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(busgo.this, "알람 설정 중 오류가 발생했습니다", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button_school.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 버튼 클릭 시 다음 화면으로 이동하는 Intent 생성
                Intent intent = new Intent(getApplicationContext(), schoolAlarm.class);
                startActivity(intent); // 다음 화면으로 전환
            }
        });

        button_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 버튼 클릭 시 다음 화면으로 이동하는 Intent 생성
                Intent intent = new Intent(getApplicationContext(), busgo.class);
                startActivity(intent); // 다음 화면으로 전환
            }
        });

        button_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 버튼 클릭 시 다음 화면으로 이동하는 Intent 생성
                Intent intent = new Intent(getApplicationContext(), schoolAlarm.class);
                startActivity(intent); // 다음 화면으로 전환
            }
        });







    }
}