package com.EDELHOME;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.io.Serializable;
import java.util.ArrayList;

public class Event  implements Serializable
{
    private String name_of_event;
    private LocalDate date_of_event;
    private LocalTime start_of_event;
    private LocalTime end_of_event;
    private ArrayList<Integer> images_of_event = new ArrayList<>();
    private Worker worker;
    //private String name_of_client;
    //private


    public Event(String name_of_event, LocalDate date_of_event, Integer images_of_event, LocalTime start_of_event, LocalTime end_of_event , Worker worker)
    {
        this.name_of_event = name_of_event;
        this.date_of_event = date_of_event;
        if(images_of_event != 0)
            this.images_of_event.add(images_of_event);
        else
            this.images_of_event.add(R.drawable.event_no_photo);
        this.start_of_event = start_of_event;
        this.end_of_event = end_of_event;
        this.worker = worker;
    }

    public String getName_of_event()
    {
        return this.name_of_event;
    }
    public void setName_of_event(String name_of_event)
    {
        this.name_of_event = name_of_event;
    }


    public String getDate_of_eventInString()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date_of_event_String = date_of_event.format(formatter);
        return date_of_event_String;
    }
    public LocalDate getDate_of_event()
    {
        return this.date_of_event;
    }
    public void setDate_of_event(LocalDate date_of_event)
    {
        this.date_of_event = date_of_event;
    }


    public ArrayList<Integer> getImages_of_event()
    {
        return this.images_of_event;
    }
    public void setImages_of_event(int images_of_event)
    {
        if(this.images_of_event.get(0) == R.drawable.event_no_photo)
        {
            this.images_of_event.set(0, Integer.valueOf(images_of_event));
        }
        else
            this.images_of_event.add(Integer.valueOf(images_of_event));
    }
    public int getCountOfImages()
    {
        return this.images_of_event.size();
    }
}

