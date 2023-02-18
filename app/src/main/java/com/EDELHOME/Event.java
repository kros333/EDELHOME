package com.EDELHOME;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class Event  implements Serializable
{
    private String name_of_event;
    private LocalDate date_of_event;
    private LocalTime start_of_event;
    private LocalTime end_of_event;
    private ArrayList<Integer> images_of_event = new ArrayList<>();
    private Worker worker;
    private String name_of_client;
    private String phone_number;
    private String description;
    private LocalDateTime date_added;
    private String address;
    private int category; //1 - замеры, 2 - мебель, 3 - окна, 4 - входные двери, 5 - межкомнатные двери, 6 - кухни
    private UUID id;
    private boolean in_archive;

    public Event(String name_of_event, LocalDate date_of_event, ArrayList<Integer> images_of_event, LocalTime start_of_event, LocalTime end_of_event , Worker worker, String name_of_client, String phone_number, String description, String address, int category, boolean in_archive)
    {
        this.id = UUID.randomUUID();
        this.name_of_event = name_of_event;
        this.date_of_event = date_of_event;
        if (images_of_event != null)
        {
            for (int i = 0; i < images_of_event.size(); i++)
            {
                this.images_of_event.add(images_of_event.get(i));
            }
        }
        else
        {
            this.images_of_event = null;
        }
        this.start_of_event = start_of_event;
        this.end_of_event = end_of_event;
        this.worker = worker;
        this.name_of_client = name_of_client;
        this.phone_number = phone_number;
        this.description = description;
        this.date_added = LocalDateTime.now();
        this.address = address;
        this.category = category;
        this.in_archive = in_archive;
    }
    public Event(String name_of_event, LocalDate date_of_event, int images_of_event, LocalTime start_of_event, LocalTime end_of_event , Worker worker, String name_of_client, String phone_number, String description, String address, int category, boolean in_archive)
    {
        this.id = UUID.randomUUID();
        this.name_of_event = name_of_event;
        this.date_of_event = date_of_event;
        if (images_of_event != 0)
            this.images_of_event.add(images_of_event);
        this.start_of_event = start_of_event;
        this.end_of_event = end_of_event;
        this.worker = worker;
        this.name_of_client = name_of_client;
        this.phone_number = phone_number;
        this.description = description;
        this.date_added = LocalDateTime.now();
        this.address = address;
        this.category = category;
        this.in_archive = in_archive;

    }

    public String getName_of_event()
    {
        if(name_of_event == null)
        {
            return "";
        }
        else
            return this.name_of_event;
    }
    public void setName_of_event(String name_of_event)
    {
        this.name_of_event = name_of_event;
    }


    public String getDate_of_eventInString()
    {
        if(date_of_event == null) {
            return "";
        }
        else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            String date_of_event_String = date_of_event.format(formatter);
            return date_of_event_String;
        }
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
            this.images_of_event.add(Integer.valueOf(images_of_event));
    }
    public int getCount_of_images()
    {
        if(images_of_event != null)
        {
            return this.images_of_event.size();
        }
        else
        {
            return 1;
        }
    }
    public String getPhone_number()
    {
        if(phone_number == null) {
            return "";
        }
        else
            return this.phone_number;
    }

    public String getAddress()
    {
        if(address == null) {
            return "";
        }
        else
            return this.address;
    }
    public String getName_of_client()
    {
        if(name_of_client == null) {
            return "";
        }
        else
            return this.name_of_client;
    }
    public String getTimeInString()
    {
        String s_time = "";
        if(start_of_event != null)
        {
            s_time = s_time + start_of_event.format(DateTimeFormatter.ofPattern("HH:mm"));
            if(end_of_event != null)
            {
                s_time = s_time + " - " + end_of_event.format(DateTimeFormatter.ofPattern("HH:mm"));
            }
        }
        return s_time;
    }
    public String getStart_of_eventInString()
    {
        String s_time = "";
        if(start_of_event != null)
        {
            s_time = start_of_event.format(DateTimeFormatter.ofPattern("HH:mm"));
        }
        return s_time;
    }
    public String getEnd_of_eventInString()
    {
        String e_time = "";
        if(end_of_event != null)
        {
            e_time = end_of_event.format(DateTimeFormatter.ofPattern("HH:mm"));
        }
        return e_time;
    }
    public LocalTime getStart_of_event()
    {
        return this.start_of_event;
    }
    public LocalTime getEnd_of_event()
    {
        return this.start_of_event;
    }
    public UUID getId()
    {
        return this.id;
    }
    public int getCategory()
    {
        return this.category;
    }
    public boolean getIn_archive()
    {
        return this.in_archive;
    }
    public void setIn_archive(boolean in_archive)
    {
        this.in_archive = in_archive;
    }
}

