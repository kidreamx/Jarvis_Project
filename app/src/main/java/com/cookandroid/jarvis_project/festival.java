package com.cookandroid.jarvis_project;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

import java.net.URI;
import java.net.URL;

public class festival extends AppCompatActivity {
    Button festival_back;
    ImageView festival_imageview;
    DatabaseReference db;
    FirebaseDatabase database;
    FirebaseStorage storage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.show_festival);
        festival_back = (Button)findViewById(R.id.festival_back);
        festival_imageview = (ImageView) findViewById(R.id.festival_imageview);
        storage = FirebaseStorage.getInstance("gs://jarvisproject-6c07c.appspot.com");
        database = FirebaseDatabase.getInstance();
        db = database.getReference().child("images").child("festival");

        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String imageUrl = dataSnapshot.getValue(String.class);
                    imageUrl = imageUrl.replace("\"", "");
                    Log.d("이미지 업로드", imageUrl);
                    Picasso.get().load(imageUrl).error(R.drawable.baseline_directions_bus_24).into(festival_imageview);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


    }
}
