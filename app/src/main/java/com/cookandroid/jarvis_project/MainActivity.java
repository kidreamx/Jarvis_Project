package com.cookandroid.jarvis_project;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.cookandroid.jarvis_project.database.festival_list;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.kakao.vectormap.KakaoMapSdk;

import org.w3c.dom.Text;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {
    ImageView mainpage_image;
    ImageView school_image;
    ImageButton route;
    ImageButton bus;
    ImageButton cafeteria;
    ImageButton event;
    TextView bus_text;
    TextView cafeteria_text;
    TextView event_text;
    TextView route_text;
    Button homepage_button;
    Button diary_school_button;
    TextView school_guidetext;
    private void getHashKey(){
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo == null)
            Log.e("KeyHash", "KeyHash:null");

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            } catch (NoSuchAlgorithmException e) {
                Log.e("KeyHash", "Unable to get MessageDigest. signature=" + signature, e);
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        getHashKey();
        mainpage_image = (ImageView)findViewById(R.id.mainpage_image);
        school_guidetext = (TextView)findViewById(R.id.school_guide);
        school_image = (ImageView)findViewById(R.id.school_image);

        bus = (ImageButton)findViewById(R.id.bus);
        cafeteria = (ImageButton)findViewById(R.id.cafeteria);
        event = (ImageButton)findViewById(R.id.event);
        route = (ImageButton)findViewById(R.id.route);

        bus_text = (TextView)findViewById(R.id.bus_text);
        cafeteria_text = (TextView)findViewById(R.id.cafeteria_text);
        event_text = (TextView)findViewById(R.id.event_text);
        route_text = (TextView)findViewById(R.id.route_text);

        homepage_button = (Button)findViewById(R.id.homepage_button);
        diary_school_button = (Button)findViewById(R.id.diary_school_button);

        bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),bus_main.class);
                startActivity(intent);
            }
        });
        route.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),route.class);
                startActivity(intent);
            }
        });
        cafeteria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Restaurant.class);
                startActivity(intent);
            }
        });
        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), festival_list.class);
                startActivity(intent);
            }
        });
        homepage_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www3.chosun.ac.kr/chosun/index.do");
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });
        diary_school_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www3.chosun.ac.kr/chosun/224/subview.do");
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast
                        String msg = getString(R.string.msg_token_fmt, token);
                        Log.d("토큰 생성", token);

                    }
                });

    }
}