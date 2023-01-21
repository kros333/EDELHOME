package com.EDELHOME;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class EventGridAdapter extends BaseAdapter
{
    private Context mContext;
    private List<Event> events;

    public EventGridAdapter(Context context, List<Event> events)
    {
        this.mContext = context;
        this.events = events;
    }

    @Override
    public int getCount() {
        return events.size();
    }

    @Override
    public Object getItem(int position) {
        return events.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View grid;

        if (convertView == null) {
            grid = new View(mContext);
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            grid = inflater.inflate(R.layout.grid_event, parent, false);
        } else {
            grid = (View) convertView;
        }
        ImageView image_of_eventView = (ImageView) grid.findViewById(R.id.image_of_event);
        TextView name_of_eventView = grid.findViewById(R.id.name_of_event);
        TextView date_of_eventView = grid.findViewById(R.id.date_of_event);
        Event event = events.get(position);

        image_of_eventView.setImageResource(event.getImage_of_event());
        name_of_eventView.setText(event.getName_of_event());
        date_of_eventView.setText(event.getDate_of_eventString());

        return grid;
    }
}
