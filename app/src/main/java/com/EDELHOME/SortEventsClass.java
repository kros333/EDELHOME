package com.EDELHOME;

import java.util.Comparator;

public class SortEventsClass implements Comparator<Event>
{
    int sort_type;

    SortEventsClass(int sort_type)
    {
        this.sort_type = sort_type;
    }

    @Override
    public int compare(Event o1, Event o2)
    {
        switch (this.sort_type)
        {
            case (1):
                return o1.getDate_of_event().compareTo(o2.getDate_of_event());
            case (2):
                return o2.getDate_of_event().compareTo(o1.getDate_of_event());
            default:
                return 0;
        }

    }
}
