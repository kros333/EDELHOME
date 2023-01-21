package com.EDELHOME;

import androidx.appcompat.app.AppCompatActivity;
import java.time.LocalDate;


import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.ListView;
import java.util.ArrayList;

import android.os.Bundle;

public class BaseActivityForOffice extends AppCompatActivity
{

    String[] name_of_events = {"Замеры для установки шкафа", "Установка дверей", "Замена бракованной кухонной плитки"};
    LocalDate[] date_of_events = {LocalDate.of(2017, 11, 30), LocalDate.of(2017, 8, 31), LocalDate.of(2018, 1, 2)};
   // Ivents[] ivents = {new Ivents(name_of_ivents[0], date_of_ivents[0]), new Ivents(name_of_ivents[1], date_of_ivents[1]), new Ivents(name_of_ivents[2], date_of_ivents[2]) } ;
    ArrayList<Event> events = new ArrayList<Event>();
    //ListView iventsList;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_for_office);

        TextView textView = findViewById(R.id.textView);
        Bundle arguments = getIntent().getExtras();

        if(arguments!=null)
        {
            String login = arguments.get("login").toString();
            textView.setText(login);
            textView.setTextSize(26);
        }

        // начальная инициализация списка
        setInitialData();
        // получаем элемент ListView
        ListView eventsList = findViewById(R.id.events);

        // создаем адаптер
        //ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, name_of_ivents);
        EventArrayAdapter eventArrayAdapter = new EventArrayAdapter(this, R.layout.list_event, events);


        // устанавливаем для списка адаптер
        eventsList.setAdapter(eventArrayAdapter);


    }

    public void onExit(View view)
    {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    private void setInitialData()
    {
        events.add(new Event(name_of_events[0], date_of_events[0], R.drawable.test));
        events.add(new Event(name_of_events[1], date_of_events[1], R.drawable.test1));
        events.add(new Event(name_of_events[2], date_of_events[2], R.drawable.test));
    }
    public void onOrderList(View view)
    {
        Intent intent = new Intent(this, EventListForOfficeActivity.class);
        startActivity(intent);
    }
}

