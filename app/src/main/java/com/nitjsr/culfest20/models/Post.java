package com.nitjsr.culfest20.models;

import java.util.HashMap;

public class Post {
    String title, image, description, id, timestamp;
    HashMap<String, Integer> likes = new HashMap<>();

    public Post() {
    }

    public Post(String title, String image, String description, String id, String timestamp, HashMap<String, Integer> likes) {
        this.title = title;
        this.image = image;
        this.description = description;
        this.id = id;
        this.timestamp = timestamp;
        this.likes = likes;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public HashMap<String, Integer> getLikes() {
        return likes;
    }

    public void setLikes(HashMap<String, Integer> likes) {
        this.likes = likes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
