package com.EDELHOME;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Ivents
{
    private String name_of_ivent;
    private LocalDate date_of_ivent;

    public Ivents(String name_of_ivent, LocalDate date_of_ivent)
    {
        this.name_of_ivent = name_of_ivent;
        this.date_of_ivent = date_of_ivent;
    }


    public String getName_of_ivent()
    {
        return this.name_of_ivent;
    }
    public void setName_of_ivent(String name_of_ivent)
    {
        this.name_of_ivent = name_of_ivent;
    }


    public String getDate_of_iventString()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date_of_ivent_String = date_of_ivent.format(formatter);
        return date_of_ivent_String;
    }
    public void setName_of_ivent(LocalDate date_of_ivent)
    {
        this.date_of_ivent = date_of_ivent;
    }
}

