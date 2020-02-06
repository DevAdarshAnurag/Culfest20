package com.nitjsr.culfest20.models;

public class Developer {

    public String name, phone, linkedIn, insta, imgLoc;

    public Developer() {
    }

    public Developer(String name, String phone, String linkedIn, String insta, String imgLoc) {
        this.name = name;
        this.phone = phone;
        this.linkedIn = linkedIn;
        this.insta = insta;
        this.imgLoc = imgLoc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLinkedIn() {
        return linkedIn;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }

    public String getInsta() {
        return insta;
    }

    public void setInsta(String insta) {
        this.insta = insta;
    }


    public String getImgLoc() {
        return imgLoc;
    }

    public void setImgLoc(String imgLoc) {
        this.imgLoc = imgLoc;
    }
}
