package com.example.android.quakereport.Models;

public class QuakeObject {
    private double magnitude;
    private String location;
    private long timeInMilliseconds;
    private String URL;

    public QuakeObject(double magnitude, String location, long timeInMilliseconds, String URL) {
        this.magnitude = magnitude;
        this.location = location;
        this.timeInMilliseconds = timeInMilliseconds;
        this.URL = URL;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public long getTimeInMilliseconds() {
        return timeInMilliseconds;
    }

    public void setTimeInMilliseconds(long date) {
        this.timeInMilliseconds = date;
    }
}
