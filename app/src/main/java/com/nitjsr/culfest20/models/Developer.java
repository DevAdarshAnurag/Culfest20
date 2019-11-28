package com.nitjsr.culfest20.models;

public class Developer {

    public String name, phone, linkedIn, insta;
    public int img;

    public Developer() {
    }

    public Developer(String name, String phone, String linkedIn, String insta, int img) {
        this.name = name;
        this.phone = phone;
        this.linkedIn = linkedIn;
        this.insta = insta;
        this.img = img;
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

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
