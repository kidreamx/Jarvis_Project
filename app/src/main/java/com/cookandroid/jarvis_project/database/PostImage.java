package com.cookandroid.jarvis_project.database;

public class PostImage {
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    String image;
    String title;
    String body;
    public PostImage(){

    }
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public PostImage(String url, String title, String image) {
        this.image = image;
        this.title = title;
        this.title = body;
    }

    public String getUrl() {
        return image;
    }

    public void set(String url) {
        this.image = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
