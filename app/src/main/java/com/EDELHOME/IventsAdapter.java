package com.EDELHOME;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class IventsAdapter extends ArrayAdapter<Ivents>
{
    private LayoutInflater inflater;
    private int layout;
    private List<Ivents> ivents;

    public IventsAdapter(Context context, int resource, List<Ivents> ivents) {
        super(context, resource, ivents);
        this.ivents = ivents;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view=inflater.inflate(this.layout, parent, false);

        TextView name_of_iventView = view.findViewById(R.id.name_of_ivent);
        TextView date_of_iventView = view.findViewById(R.id.date_of_ivent);

        Ivents ivent = ivents.get(position);

        name_of_iventView.setText(ivent.getName_of_ivent());
        date_of_iventView.setText(ivent.getDate_of_iventString());

        return view;
    }
}
