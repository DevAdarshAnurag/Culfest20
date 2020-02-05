package com.nitjsr.culfest20.models;


public class Person {
    private String imgLoc;
    private String name;
    private String post;
    private String whatsappNumber;
    private String facebook;
    private String instagram;

    public Person() {
    }

    public Person(String imgLoc, String name, String post, String whatsappNumber, String facebook, String instagram) {
        this.imgLoc = imgLoc;
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

    public String getImgLoc() {
        return imgLoc;
    }

    public void setImgLoc(String imgLoc) {
        this.imgLoc = imgLoc;
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
