package com.nitjsr.culfest20.models;

public class Notification {
    public String title, body, id;
    public int event;

    public Notification() {
    }

    public Notification(String title, String body, String id, int event) {
        this.title = title;
        this.body = body;
        this.id = id;
        this.event = event;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getEvent() {
        return event;
    }

    public void setEvent(int event) {
        this.event = event;
    }
}
