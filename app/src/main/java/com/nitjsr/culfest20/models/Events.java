package com.nitjsr.culfest20.models;

public class Events {
    private int eventImage, eventColor;
    private String eventName, eventPrize;

    public Events() {
    }

    public Events(int eventImage, int eventColor, String eventName, String eventPrize) {
        this.eventImage = eventImage;
        this.eventColor = eventColor;
        this.eventName = eventName;
        this.eventPrize = eventPrize;
    }

    public int getEventColor() {
        return eventColor;
    }

    public void setEventColor(int eventColor) {
        this.eventColor = eventColor;
    }

    public int getEventImage() {
        return eventImage;
    }

    public void setEventImage(int eventImage) {
        this.eventImage = eventImage;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventPrize() {
        return eventPrize;
    }

    public void setEventPrize(String eventPrize) {
        this.eventPrize = eventPrize;
    }
}
