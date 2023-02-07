package com.EDELHOME;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Worker
{
    private String name;
    private int image;

    private ArrayList<LocalDate> startOfWork = new ArrayList<LocalDate>();
    private ArrayList<LocalDate> endOfWork = new ArrayList<LocalDate>();
    public Worker(String name)
    {
        this.name = name;
    }
    public Worker(String name, int image)
    {
        this.name = name;
        this.image = image;
    }
    public Worker(String name, int image, LocalDate startOfWork, LocalDate endOfWork)
    {
        this.name = name;
        this.image = image;
        this.startOfWork.add(startOfWork);
        this.endOfWork.add(endOfWork);
    }

    public String getName()
    {
        return this.name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public int getImage()
    {
        return this.image;
    }
    public void setImage(int image)
    {
        this.image = image;
    }
    public ArrayList<LocalDate> getStartOfWork() {
        return startOfWork;
    }
    public void setStartOfWork(ArrayList<LocalDate> startOfWork) {
        this.startOfWork = startOfWork;
    }
    public ArrayList<LocalDate> getEndOfWork() {
        return endOfWork;
    }
    public void setEndOfWork(ArrayList<LocalDate> endOfWork) {
        this.endOfWork = endOfWork;
    }


}
