package com.EDELHOME;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.List;

public class EventArrayAdapter extends ArrayAdapter<Event>
{
    private LayoutInflater inflater;
    private int layout;
    private List<Event> events;

    public EventArrayAdapter(Context context, int resource, List<Event> events)
    {
        super(context, resource, events);
        this.events = events;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {

        View view=inflater.inflate(this.layout, parent, false);

        TextView name_of_eventView = view.findViewById(R.id.name_of_event);
        TextView date_of_eventView = view.findViewById(R.id.date_of_event);

        Event event = events.get(position);

        name_of_eventView.setText(event.getName_of_event());
        if(event.getTimeInString() == "")
        {
            date_of_eventView.setText("——:——");
        }
        else
            date_of_eventView.setText(event.getTimeInString());

        return view;
    }
}
