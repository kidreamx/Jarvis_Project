package com.cookandroid.jarvis_project.database;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.cookandroid.jarvis_project.MainActivity;
import com.cookandroid.jarvis_project.MyListViewAdapter2;
import com.cookandroid.jarvis_project.R;
import com.cookandroid.jarvis_project.bus_main;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class festival_list extends AppCompatActivity {
    Button main_back;
    ListView festival_listview;
    ArrayList<PostImage> arrayList;
    MyListViewAdapter2 adapter;
    DatabaseReference db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.festival_list);
        festival_listview = (ListView) findViewById(R.id.festival_listview_list);
        arrayList = new ArrayList<>();
        main_back = (Button) findViewById(R.id.main_back);
        adapter = new MyListViewAdapter2(arrayList, getApplicationContext());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        db = FirebaseDatabase.getInstance().getReference().child("festivalPosts");
        getValue();
        festival_listview.setAdapter(adapter);
        festival_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               PostImage postImage  = arrayList.get(position);
               Log.d("정보가 잘 넘어갔는가?", postImage.getBody() + postImage.getTitle()+  postImage.getImage() );
               Intent intent = new Intent(festival_list.this,show_festival.class);
               intent.putExtra("body", postImage.getBody() );
               intent.putExtra("title", postImage.getTitle() );
               intent.putExtra("url", postImage.getImage() );
               startActivity(intent);
            }
        });
        main_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
        void getValue(){
            db.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        PostImage sValue = dataSnapshot.getValue(PostImage.class);
                        arrayList.add(sValue);

                        if (sValue != null) {
                            Log.d("Post", sValue.title +" "+sValue.body);
                        } else {
                            Log.d("Post", "sValue is null");
                        }
                    }
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e("TAG", "Failed to read value.", error.toException());
                }
            });

    }
}
