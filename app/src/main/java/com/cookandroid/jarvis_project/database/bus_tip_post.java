package com.cookandroid.jarvis_project.database;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.cookandroid.jarvis_project.R;
import com.google.firebase.database.DatabaseReference;


public class bus_tip_post extends AppCompatActivity {

    Button btnCancel, btnComplete;
    String head, content;
    EditText edit_head, edit_content;


    private DatabaseReference mDatabase;

    private static final String TAG = "busTip";


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bus_tip);
        ReadAndWriteSnippets db = new ReadAndWriteSnippets(mDatabase);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnCancel = (Button) findViewById(R.id.cancel);             //취소 버튼
        btnComplete = (Button) findViewById(R.id.complete);         //완료 버튼
        edit_head = (EditText) findViewById(R.id.editItem);         //제목
        edit_content = (EditText) findViewById(R.id.editItem1);     //내용

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //글쓰기 취소
                Intent intent = new Intent(getApplicationContext(), bus_tip_list.class);
                startActivity(intent);
            }
        });
        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                head = String.valueOf(edit_head.getText());
                content = String.valueOf(edit_content.getText());

                db.writeNewPost(head, content);
                //글쓰기 완료
                Intent intent = new Intent(getApplicationContext(), bus_tip_list.class);
                startActivity(intent);
            }
        });
    }

}
//https://aries574.tistory.com/255