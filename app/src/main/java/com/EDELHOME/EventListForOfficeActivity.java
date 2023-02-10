package com.EDELHOME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemClickListener;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class EventListForOfficeActivity extends AppCompatActivity
{
    String[] sort_method = {"По дате (ближайшие)", "По дате (с конца)", "По дате добавления (сначала новые)", "По дате добавления (сначала старые)"};
    String[] filter_method = {"Все", "Замеры", "Мебель", "Кухни", "Окна", "Входные двери", "Межкомнатные двери"};
    ArrayList<Event> events = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list_for_office);
        Bundle arguments = getIntent().getExtras();
        if(arguments!=null)
        {
            events = (ArrayList<Event>) arguments.getSerializable("EventsExtra");
        }


        // получаем элемент GridView
        final GridView eventsList = findViewById(R.id.gridView);
        // создаем адаптер
        EventGridAdapter eventGridAdapter = new EventGridAdapter(this, events);
        eventsList.setAdapter(eventGridAdapter);
        eventsList.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                // TODO Auto-generated method stub
                onEvent(position);
            }
        });

    }

    public void onEvent(int position)
    {
        Intent intent = new Intent(this, EventPageForOfficeActivity.class);
        intent.putExtra(Event.class.getSimpleName(), events.get(position));
        startActivity(intent);
    }
    public void onSortButton(View v)
    {
        EventListSortDialogFragment dialog = new EventListSortDialogFragment();
        dialog.show(getSupportFragmentManager(), "custom");
    }
}