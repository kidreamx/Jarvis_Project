package com.cookandroid.jarvis_project.database;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ReadAndWriteSnippets {

    private static final String TAG = "ReadAndWriteSnippets";
    private DatabaseReference mDatabase;

    public ReadAndWriteSnippets(DatabaseReference database) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    private void addPostEventListener(DatabaseReference mPostReference) {
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Post post = dataSnapshot.getValue(Post.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        mPostReference.addValueEventListener(postListener);
    }

    public void writeNewPost(String title, String body) {
        //사용자 추가
        User user = new User();
        mDatabase.child("users").child(String.valueOf(user.userId)).setValue(user);

        // 게시글 업데이트
        String key = mDatabase.child("posts").push().getKey();
        Post post = new Post(user.userId, user.username, title, body);
        Map<String, Object> postValues = post.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/posts/"+key,postValues);
        childUpdates.put("/user-posts/" + user.userId + "/" + key, postValues);

        mDatabase.updateChildren(childUpdates);
    }

}
