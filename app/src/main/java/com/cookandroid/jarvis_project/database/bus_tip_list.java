package com.cookandroid.jarvis_project.database;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.cookandroid.jarvis_project.MainActivity;
import com.cookandroid.jarvis_project.R;
import com.cookandroid.jarvis_project.bus_main;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class bus_tip_list extends AppCompatActivity {
    Button btnBack, btnAddPost;
    ArrayList<Post> arrayList;
    MyListViewAdapter adapter;
    ListView listView;
    DatabaseReference db;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        arrayList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listview_list);
        adapter = new MyListViewAdapter(arrayList, getApplicationContext());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        db = FirebaseDatabase.getInstance().getReference().child("posts");
        getValue();
        listView.setAdapter(adapter);


        btnBack = (Button) findViewById(R.id.back);
        btnAddPost = (Button) findViewById(R.id.plus);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), bus_main.class);
                startActivity(intent);
            }
        });

        btnAddPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), bus_tip_post.class);
                startActivity(intent);
            }
        });

    }

    void getValue() {
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Post sValue = dataSnapshot.getValue(Post.class);
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
