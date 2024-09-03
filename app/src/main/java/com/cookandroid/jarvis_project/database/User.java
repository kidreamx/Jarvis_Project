package com.cookandroid.jarvis_project.database;


import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {
    private int Id = 0;
    public String username;
    public String userId;

    public User() {
        userId = String.valueOf(Id);
        username = "익명" + userId;
        Id++;
    }


}