package com.nitjsr.culfest20.models;

public class User {
    public String name, instituteName, instituteID, contact;
    public boolean accommodation, payment, tshirt, kit;
    public String tshirtSize;
    public String uid, email;
    public String culfestID;

    public User() {

    }

    public User(String name, String instituteName, String instituteID, String contact, boolean accommodation, boolean payment, boolean tshirt, boolean kit, String tshirtSize, String uid, String email, String culfestID) {
        this.name = name;
        this.instituteName = instituteName;
        this.instituteID = instituteID;
        this.contact = contact;
        this.accommodation = accommodation;
        this.payment = payment;
        this.tshirt = tshirt;
        this.kit = kit;
        this.tshirtSize = tshirtSize;
        this.uid = uid;
        this.email = email;
        this.culfestID = culfestID;
    }

    public boolean isAccommodation() {
        return accommodation;
    }

    public void setAccommodation(boolean accommodation) {
        this.accommodation = accommodation;
    }

    public boolean isPayment() {
        return payment;
    }

    public void setPayment(boolean payment) {
        this.payment = payment;
    }

    public boolean isTshirt() {
        return tshirt;
    }

    public void setTshirt(boolean tshirt) {
        this.tshirt = tshirt;
    }

    public boolean isKit() {
        return kit;
    }

    public void setKit(boolean kit) {
        this.kit = kit;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }

    public String getInstituteID() {
        return instituteID;
    }

    public void setInstituteID(String instituteID) {
        this.instituteID = instituteID;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getTshirtSize() {
        return tshirtSize;
    }

    public void setTshirtSize(String tshirtSize) {
        this.tshirtSize = tshirtSize;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCulfestID() {
        return culfestID;
    }

    public void setCulfestID(String culfestID) {
        this.culfestID = culfestID;
    }
}