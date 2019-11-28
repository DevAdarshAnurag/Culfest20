package com.nitjsr.culfest20.models;

public class BlogPost {

    public String post_id,user_id, image_url, desc, image_thumb;
    public String  timestamp;
    public String user_name, user_img;

    public BlogPost() {
    }

    public BlogPost(String post_id, String user_id, String image_url, String desc, String image_thumb, String timestamp, String user_name, String user_img) {
        this.post_id = post_id;
        this.user_id = user_id;
        this.image_url = image_url;
        this.desc = desc;
        this.image_thumb = image_thumb;
        this.timestamp = timestamp;
        this.user_name = user_name;
        this.user_img = user_img;
    }

}
