package com.nitjsr.culfest20.models;


public class Person {
    private int thumbnail;
    private String name;
    private String post;
    private String whatsappNumber;
    private String facebook;
    private String instagram;

    public Person() {
    }

    public Person(int thumbnail, String name, String post, String whatsappNumber, String facebook, String instagram) {
        this.thumbnail = thumbnail;
        this.name = name;
        this.post = post;
        this.whatsappNumber = whatsappNumber;
        this.facebook = facebook;
        this.instagram = instagram;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getWhatsappNumber() {
        return whatsappNumber;
    }

    public void setWhatsappNumber(String whatsappNumber) {
        this.whatsappNumber = whatsappNumber;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }
}
