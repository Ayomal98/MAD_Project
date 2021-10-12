package com.example.reminder;

import java.sql.Time;
import java.util.Date;

public class AlarmReminderModel {
    private int id;
    private String title;
    private String description;
    private String location;
    private String type;
    private String date;
    private String time;
    private int noNotifications;
    private String uri;

    public String getUri() {
        return uri;
    }

    public int getNoNotifications() {
        return noNotifications;
    }

    public void setNoNotifications(int noNotifications) {
        this.noNotifications = noNotifications;
    }

    @Override
    public String toString() {
        return "AlarmReminderModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    //uri needed to be added
    public AlarmReminderModel(int id, String title, String description, String location, String type,int noNotifications,String date,String time) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.noNotifications=noNotifications;
        this.date=date;
        this.time=time;
    }


    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public AlarmReminderModel(int id, String title, String description, String location, String type, String date, String time,String uri) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.date = date;
        this.time = time;
        this.uri=uri;
    }
}
