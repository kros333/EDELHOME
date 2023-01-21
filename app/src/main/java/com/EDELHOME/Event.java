package com.EDELHOME;
import android.media.Image;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Event
{
    private String name_of_event;
    private LocalDate date_of_event;
    private int image_of_event;

    public Event(String name_of_event, LocalDate date_of_event, int image_of_event)
    {
        this.name_of_event = name_of_event;
        this.date_of_event = date_of_event;
        this.image_of_event = image_of_event;
    }


    public String getName_of_event()
    {
        return this.name_of_event;
    }
    public void setName_of_event(String name_of_event)
    {
        this.name_of_event = name_of_event;
    }


    public String getDate_of_eventString()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date_of_event_String = date_of_event.format(formatter);
        return date_of_event_String;
    }
    public void setDate_of_event(LocalDate date_of_event)
    {
        this.date_of_event = date_of_event;
    }


    public int getImage_of_event()
    {
        return this.image_of_event;
    }
    public void setImage_of_event(int image_of_event)
    {
        this.image_of_event = image_of_event;
    }
}

