package com.cookandroid.jarvis_project.database;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cookandroid.jarvis_project.R;
import com.cookandroid.jarvis_project.database.Post;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.net.URI;
import java.net.URL;

public class show_festival extends AppCompatActivity {
    Button festival_back;
    ImageView festival_imageview;
    TextView festival_textview;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        String title = getIntent().getStringExtra("title");
        String body = getIntent().getStringExtra("body");
        String url = getIntent().getStringExtra("url");
        setContentView(R.layout.show_festival);
        festival_textview=(TextView)findViewById(R.id.festival_textview);
        festival_back = (Button)findViewById(R.id.festival_back);
        festival_imageview = (ImageView) findViewById(R.id.festival_imageview);
        Log.d("이미지 업로드", url);
        Picasso.get().load(url).error(R.drawable.baseline_directions_bus_24).into(festival_imageview);
        festival_textview.setText(title);



//        StorageReference storageReference = storage.getReference("festival/festival_1.png");
//        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//            @Override
//            public void onSuccess(Uri uri) {
//                String imageUrl = uri.toString();
//                Log.d("이미지 url 가져오기 성공", imageUrl);
//                // Picasso를 사용하여 이미지 로드 및 표시
//                Picasso.get().load(imageUrl).error(R.drawable.baseline_directions_bus_24).into(festival_imageview);
//
//                // 또는 Glide를 사용할 수도 있습니다.
//                // Glide.with(MainActivity.this).load(imageUrl).into(imageView);
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception exception) {
//                // Handle any errors
//                Log.e(TAG, "Failed to get download URL", exception);
//            }
//        });
        festival_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), festival_list.class);
                startActivity(intent);
            }
        });


    }
}
