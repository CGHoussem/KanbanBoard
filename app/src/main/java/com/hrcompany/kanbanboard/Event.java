package com.hrcompany.kanbanboard;

import java.sql.Date;

/**
 * Created by Houssem on 23/03/2017.
 */

public class Event {

    private String title;
    private String description;
    private Date eventDate;
    private int state;

    public Event(String title, String description, Date eventDate, int state) {

        this.title = title;
        this.description = description;
        this.eventDate = eventDate;
        this.state = state;
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

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }


}
