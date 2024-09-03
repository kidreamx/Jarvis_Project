package com.cookandroid.jarvis_project;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;


public class AlarmReceiver extends BroadcastReceiver {

    public AlarmReceiver(){ }

    NotificationManager manager;
    NotificationCompat.Builder builder;

    //오레오 이상은 반드시 채널을 설정해줘야 Notification이 작동함
    private static String CHANNEL_ID = "channel1";
    private static String CHANNEL_NAME = "Channel1";


    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("알림 들어옴", "알림이 시작됐어요!" );
        String contextValue = intent.getStringExtra("content");
        builder = null;
        manager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.createNotificationChannel(
                new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
        );
        builder = new NotificationCompat.Builder(context, CHANNEL_ID);

        //알림창 클릭 시 busgo 화면 부름
        Intent intent2 = new Intent(context, busgo.class);
        int requestNum = (int)System.currentTimeMillis();
        PendingIntent pendingIntent = PendingIntent.getActivity(context,requestNum,intent2, PendingIntent.FLAG_UPDATE_CURRENT| PendingIntent.FLAG_IMMUTABLE);

        //알림창 제목
        builder.setContentTitle("버스 출발 "+contextValue+"분전 입니다!");
        //알림창 아이콘
        builder.setSmallIcon(R.drawable.baseline_directions_bus_24);
        //알림창 터치시 자동 삭제
        builder.setAutoCancel(true);

        builder.setContentIntent(pendingIntent);

        Notification notification = builder.build();
        manager.notify(1,notification);

    }
}