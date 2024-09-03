package com.cookandroid.jarvis_project;

import android.annotation.SuppressLint;
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
import android.widget.RadioGroup;
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

public class schoolAlarm extends Activity {
    private String TAG = this.getClass().getSimpleName();


    //현재 시간,분 변수선언
    int currHour, currMinute;


    private AlarmManager alarmManager;

    private TimePickerDialog.OnTimeSetListener timeCallbackMethod; //알람시간 변경선택 타임피커 콜백처리 변수 선언
    RadioButton button_middle, button_social, button_global;
    Button button_alarm_middle,button_alarm_social,button_alarm_global,button_main,button_school,button_city;
    AutoCompleteTextView dropdown_hour,dropdown_minute,dropdown_ago,dropdown_hour_social,dropdown_minute_social,dropdown_ago_social,
            dropdown_hour_global,dropdown_minute_global,dropdown_ago_global;
    TextView text_hour, text_minute, text_ago,text_hour_social, text_minute_social, text_ago_social,
            text_hour_global, text_minute_global, text_ago_global;



    GregorianCalendar mCalender;

    NotificationManager notificationManager;
    NotificationCompat.Builder builder;
    PendingIntent pendingIntent;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_school);

//        button_choice = findViewById(R.id.button_choice);
        button_middle = findViewById(R.id.button_middle);
        button_social = findViewById(R.id.button_social);
        button_global = findViewById(R.id.button_global);
        button_alarm_middle = findViewById(R.id.button_alarm_middle);
        button_alarm_social = findViewById(R.id.button_alarm_social);
        button_alarm_global = findViewById(R.id.button_alarm_global);


        button_main = findViewById(R.id.button_main);

        button_school = findViewById(R.id.button_school);
        button_city = findViewById(R.id.button_city);

        dropdown_hour = findViewById(R.id.dropdown_hour);
        dropdown_minute = findViewById(R.id.dropdown_minute);
        dropdown_ago = findViewById(R.id.dropdown_ago);


        dropdown_hour_social = findViewById(R.id.dropdown_hour_social);
        dropdown_minute_social = findViewById(R.id.dropdown_minute_social);
        dropdown_ago_social = findViewById(R.id.dropdown_ago_social);

        dropdown_hour_global = findViewById(R.id.dropdown_hour_global);
        dropdown_minute_global = findViewById(R.id.dropdown_minute_global);
        dropdown_ago_global = findViewById(R.id.dropdown_ago_global);

        text_hour = findViewById(R.id.text_hour);
        text_minute = findViewById(R.id.text_minute);
        text_ago = findViewById(R.id.text_ago);

        text_hour_social = findViewById(R.id.text_hour_social);
        text_minute_social = findViewById(R.id.text_minute_social);
        text_ago_social = findViewById(R.id.text_ago_social);

        text_hour_global = findViewById(R.id.text_hour_global);
        text_minute_global = findViewById(R.id.text_minute_global);
        text_ago_global = findViewById(R.id.text_ago_global);


        String[] hour_middle = {"07", "08", "09", "10", "11", "12",
                "13", "14", "15", "16", "17", "18", "19", "20", "21", "22"};

        String[] minute_middle = {"00", "05" , "10","15", "20", "25", "30", "35", "40", "45", "50"};

        String[] ago_middle = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10"};

        String[] hour_social = {"08", "09", "10", "11", "12","13", "14", "15", "16"};

        String[] minute_social = {"00", "10", "20", "30", "40", "50"};

        String[] ago_social = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10"};

        String[] hour_global = { "08", "09", "10", "11", "12",
                "13", "14", "15", "16", "17", "18"};

        String[] minute_global = {"00", "10", "20", "30", "40", "50"};

        String[] ago_global = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10"};


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, hour_middle);
        dropdown_hour.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, minute_middle);
        dropdown_minute.setAdapter(adapter2);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, ago_middle);
        dropdown_ago.setAdapter(adapter3);


        ArrayAdapter<String> adapter4 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, hour_social);
        dropdown_hour_social.setAdapter(adapter4);

        ArrayAdapter<String> adapter5 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, minute_social);
        dropdown_minute_social.setAdapter(adapter5);

        ArrayAdapter<String> adapter6 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, ago_social);
        dropdown_ago_social.setAdapter(adapter6);

        ArrayAdapter<String> adapter7 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, hour_global);
        dropdown_hour_global.setAdapter(adapter7);

        ArrayAdapter<String> adapter8 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, minute_global);
        dropdown_minute_global.setAdapter(adapter8);

        ArrayAdapter<String> adapter9 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, ago_global);
        dropdown_ago_global.setAdapter(adapter9);




        dropdown_hour.setVisibility(View.GONE); // 처음에 시간이 안보이도록
        dropdown_minute.setVisibility(View.GONE); // 처음에 분이 안보이도록
        dropdown_ago.setVisibility(View.GONE); // 처음에 몇분전이 안보이도록

        text_hour.setVisibility(View.GONE); // 처음에 시간이 안보이도록
        text_minute.setVisibility(View.GONE); // 처음에 분이 안보이도록
        text_ago.setVisibility(View.GONE); // 처음에 몇분전이 안보이도록

        text_hour_social.setVisibility(View.GONE); // 처음에 시간이 안보이도록
        text_minute_social.setVisibility(View.GONE); // 처음에 분이 안보이도록
        text_ago_social.setVisibility(View.GONE); // 처음에 몇분전이 안보이도록

        dropdown_hour_social.setVisibility(View.GONE); // 처음에 시간이 안보이도록
        dropdown_minute_social.setVisibility(View.GONE); // 처음에 분이 안보이도록
        dropdown_ago_social.setVisibility(View.GONE); // 처음에 몇분전이 안보이도록

        text_hour_global.setVisibility(View.GONE); // 처음에 시간이 안보이도록
        text_minute_global.setVisibility(View.GONE); // 처음에 분이 안보이도록
        text_ago_global.setVisibility(View.GONE); // 처음에 몇분전이 안보이도록

        dropdown_hour_global.setVisibility(View.GONE); // 처음에 시간이 안보이도록
        dropdown_minute_global.setVisibility(View.GONE); // 처음에 분이 안보이도록
        dropdown_ago_global.setVisibility(View.GONE); // 처음에 몇분전이 안보이도록

        button_alarm_middle.setVisibility(View.GONE); // 처음에 57이 안보이도록
        button_alarm_social.setVisibility(View.GONE); // 처음에 61이 안보이도록
        button_alarm_global.setVisibility(View.GONE); // 처음에 419이 안보이도록




        button_middle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // 버스선택을 클릭했을 때 작동할 코드

                dropdown_hour.setVisibility(View.VISIBLE); //시간선택이 보이도록
                dropdown_minute.setVisibility(View.VISIBLE); // 분선택이 보이도록
                dropdown_ago.setVisibility(View.VISIBLE); //몇분전선택이 보이도록

                text_hour.setVisibility(View.VISIBLE); //시 글자가 보이도록
                text_minute.setVisibility(View.VISIBLE); // 분글자가 보이도록
                text_ago.setVisibility(View.VISIBLE); //몇분전 글자가 보이도록


                text_hour_global.setVisibility(View.GONE); // 처음에 시간이 안보이도록
                text_minute_global.setVisibility(View.GONE); // 처음에 분이 안보이도록
                text_ago_global.setVisibility(View.GONE); // 처음에 몇분전이 안보이도록

                dropdown_hour_global.setVisibility(View.GONE); // 처음에 시간이 안보이도록
                dropdown_minute_global.setVisibility(View.GONE); // 처음에 분이 안보이도록
                dropdown_ago_global.setVisibility(View.GONE); // 처음에 몇분전이 안보이도록

                text_hour_social.setVisibility(View.GONE); // 처음에 시간이 안보이도록
                text_minute_social.setVisibility(View.GONE); // 처음에 분이 안보이도록
                text_ago_social.setVisibility(View.GONE); // 처음에 몇분전이 안보이도록

                dropdown_hour_social.setVisibility(View.GONE); // 처음에 시간이 안보이도록
                dropdown_minute_social.setVisibility(View.GONE); // 처음에 분이 안보이도록
                dropdown_ago_social.setVisibility(View.GONE); // 처음에 몇분전이 안보이도록


                button_alarm_middle.setVisibility(View.VISIBLE); //57번 예약버튼이 보이도록
                button_alarm_social.setVisibility(View.GONE); // 61번 예약버튼이 안보이도록
                button_alarm_global.setVisibility(View.GONE); // 419번 예약버튼이 안보이도록




            }
        });


        button_social.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // 버스선택을 클릭했을 때 작동할 코드

                dropdown_hour_social.setVisibility(View.VISIBLE); //시간선택이 보이도록
                dropdown_minute_social.setVisibility(View.VISIBLE); // 분선택이 보이도록
                dropdown_ago_social.setVisibility(View.VISIBLE); //몇분전선택이 보이도록

                text_hour_social.setVisibility(View.VISIBLE); //시 글자가 보이도록
                text_minute_social.setVisibility(View.VISIBLE); // 분글자가 보이도록
                text_ago_social.setVisibility(View.VISIBLE); //몇분전 글자가 보이도록

                text_hour_global.setVisibility(View.GONE); // 처음에 시간이 안보이도록
                text_minute_global.setVisibility(View.GONE); // 처음에 분이 안보이도록
                text_ago_global.setVisibility(View.GONE); // 처음에 몇분전이 안보이도록

                dropdown_hour_global.setVisibility(View.GONE); // 처음에 시간이 안보이도록
                dropdown_minute_global.setVisibility(View.GONE); // 처음에 분이 안보이도록
                dropdown_ago_global.setVisibility(View.GONE); // 처음에 몇분전이 안보이도록

                dropdown_hour.setVisibility(View.GONE); // 처음에 시간이 안보이도록
                dropdown_minute.setVisibility(View.GONE); // 처음에 분이 안보이도록
                dropdown_ago.setVisibility(View.GONE); // 처음에 몇분전이 안보이도록

                text_hour.setVisibility(View.GONE); // 처음에 시간이 안보이도록
                text_minute.setVisibility(View.GONE); // 처음에 분이 안보이도록
                text_ago.setVisibility(View.GONE); // 처음에 몇분전이 안보이도록

                button_alarm_social.setVisibility(View.VISIBLE); //61번 예약버튼이 보이도록
                button_alarm_middle.setVisibility(View.GONE); // 57 예약버튼이 안보이도록
                button_alarm_global.setVisibility(View.GONE); // 419번 예약버튼이 안보이도록

            }
        });

        button_global.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // 버스선택을 클릭했을 때 작동할 코드

                dropdown_hour_global.setVisibility(View.VISIBLE); //시간선택이 보이도록
                dropdown_minute_global.setVisibility(View.VISIBLE); // 분선택이 보이도록
                dropdown_ago_global.setVisibility(View.VISIBLE); //몇분전선택이 보이도록

                text_hour_global.setVisibility(View.VISIBLE); //시 글자가 보이도록
                text_minute_global.setVisibility(View.VISIBLE); // 분글자가 보이도록
                text_ago_global.setVisibility(View.VISIBLE); //몇분전 글자가 보이도록

                dropdown_hour.setVisibility(View.GONE); // 처음에 시간이 안보이도록
                dropdown_minute.setVisibility(View.GONE); // 처음에 분이 안보이도록
                dropdown_ago.setVisibility(View.GONE); // 처음에 몇분전이 안보이도록

                text_hour.setVisibility(View.GONE); // 처음에 시간이 안보이도록
                text_minute.setVisibility(View.GONE); // 처음에 분이 안보이도록
                text_ago.setVisibility(View.GONE); // 처음에 몇분전이 안보이도록

                text_hour_social.setVisibility(View.GONE); // 처음에 시간이 안보이도록
                text_minute_social.setVisibility(View.GONE); // 처음에 분이 안보이도록
                text_ago_social.setVisibility(View.GONE); // 처음에 몇분전이 안보이도록

                dropdown_hour_social.setVisibility(View.GONE); // 처음에 시간이 안보이도록
                dropdown_minute_social.setVisibility(View.GONE); // 처음에 분이 안보이도록
                dropdown_ago_social.setVisibility(View.GONE); // 처음에 몇분전이 안보이도록

                button_alarm_global.setVisibility(View.VISIBLE); //419번 예약버튼이 보이도록
                button_alarm_middle.setVisibility(View.GONE); // 57 예약버튼이 안보이도록
                button_alarm_social.setVisibility(View.GONE); // 61번 예약버튼이 안보이도록



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

        dropdown_hour_social.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dropdown_hour_social.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(dropdown_hour_social, InputMethodManager.SHOW_IMPLICIT);
                dropdown_hour_social.showDropDown();
                dropdown_hour_social.setDropDownHeight(500);
            }
        });

        dropdown_minute_social.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dropdown_minute_social.showDropDown();
                dropdown_minute_social.setDropDownHeight(500);
            }
        });

        dropdown_ago_social.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dropdown_ago_social.showDropDown();
                dropdown_ago_social.setDropDownHeight(500);
            }
        });

        dropdown_hour_social.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedText = (String) parent.getItemAtPosition(position);

            }
        });

        dropdown_minute_social.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedText = (String) parent.getItemAtPosition(position);

            }
        });

        dropdown_ago_social.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedText = (String) parent.getItemAtPosition(position);

            }
        });


        dropdown_hour_global.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dropdown_hour_global.showDropDown();
                dropdown_hour_global.setDropDownHeight(500);
            }
        });

        dropdown_minute_global.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dropdown_minute_global.showDropDown();
                dropdown_minute_global.setDropDownHeight(500);
            }
        });

        dropdown_ago_global.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dropdown_ago_global.showDropDown();
                dropdown_ago_global.setDropDownHeight(500);
            }
        });

        dropdown_hour_global.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedText = (String) parent.getItemAtPosition(position);

            }
        });

        dropdown_minute_global.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedText = (String) parent.getItemAtPosition(position);

            }
        });

        dropdown_ago_global.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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


        button_alarm_middle.setOnClickListener(new View.OnClickListener() {
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
                        Toast.makeText(schoolAlarm.this, "설정된 시간이 현재 시간보다 이전입니다. 올바른 시간을 설정해 주세요.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    SimpleDateFormat dataForm5 = new SimpleDateFormat("HH:mm:ss");
                    // 알람을 수신할 리시버 인텐트 생성
                    Intent receiverIntent = new Intent(getApplicationContext(), AlarmReceiver.class);
                    receiverIntent.putExtra("content",Integer.toString(agoMinutes));
                    int requestCode = (int) System.currentTimeMillis();
                    pendingIntent = PendingIntent.getBroadcast(schoolAlarm.this, requestCode, receiverIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
                    // 알람 매니저를 통해 알람 설정
                    alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                    if (alarmManager != null) {
                        alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
                        Log.e("알림1", dataForm5.format(calendar.getTimeInMillis()));
                        Toast.makeText(schoolAlarm.this, "본관행 알람이 설정되었습니다", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(schoolAlarm.this, "알람 설정 중 오류가 발생했습니다", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button_alarm_social.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 사용자가 선택한 시간과 분, 그리고 몇 분 전에 알람을 울릴지에 대한 값을 가져옴
                String selectedHour61 = dropdown_hour_social.getText().toString();
                String selectedMinute61 = dropdown_minute_social.getText().toString();
                String selectedAgo61 = dropdown_ago_social.getText().toString();


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
                        Toast.makeText(schoolAlarm.this, "설정된 시간이 현재 시간보다 이전입니다. 올바른 시간을 설정해 주세요.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    SimpleDateFormat dataForm5 = new SimpleDateFormat("HH:mm:ss");
                    // 알람을 수신할 리시버 인텐트 생성
                    Intent receiverIntent = new Intent(getApplicationContext(), AlarmReceiver.class);
                    receiverIntent.putExtra("content",Integer.toString(agoMinutes));
                    int requestCode = (int) System.currentTimeMillis();
                    pendingIntent = PendingIntent.getBroadcast(schoolAlarm.this, requestCode, receiverIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
                    // 알람 매니저를 통해 알람 설정
                    alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                    if (alarmManager != null) {
                        alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
                        Log.e("알림2", dataForm5.format(calendar.getTimeInMillis()));
                        Toast.makeText(schoolAlarm.this, "사회과학대행 알람이 설정되었습니다", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(schoolAlarm.this, "알람 설정 중 오류가 발생했습니다", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button_alarm_global.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 사용자가 선택한 시간과 분, 그리고 몇 분 전에 알람을 울릴지에 대한 값을 가져옴
                String selectedHour419 = dropdown_hour_global.getText().toString();
                String selectedMinute419 = dropdown_minute_global.getText().toString();
                String selectedAgo419 = dropdown_ago_global.getText().toString();


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
                        Toast.makeText(schoolAlarm.this, "설정된 시간이 현재 시간보다 이전입니다. 올바른 시간을 설정해 주세요.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    SimpleDateFormat dataForm5 = new SimpleDateFormat("HH:mm:ss");
                    // 알람을 수신할 리시버 인텐트 생성
                    Intent receiverIntent = new Intent(getApplicationContext(), AlarmReceiver.class);
                    receiverIntent.putExtra("content",Integer.toString(agoMinutes));
                    int requestCode = (int) System.currentTimeMillis();
                    pendingIntent = PendingIntent.getBroadcast(schoolAlarm.this, requestCode, receiverIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
                    // 알람 매니저를 통해 알람 설정
                    alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                    if (alarmManager != null) {
                        alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
                        Log.e("알림3", dataForm5.format(calendar.getTimeInMillis()));
                        Toast.makeText(schoolAlarm.this, "글로벌하우스행 알람이 설정되었습니다", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(schoolAlarm.this, "알람 설정 중 오류가 발생했습니다", Toast.LENGTH_SHORT).show();
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
                Intent intent = new Intent(getApplicationContext(), bus_main.class);
                startActivity(intent); // 다음 화면으로 전환
            }
        });


    }
}