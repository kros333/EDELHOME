package com.EDELHOME;

import androidx.appcompat.app.AppCompatActivity;
import java.time.LocalDate;


import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.ListView;

import java.time.LocalTime;
import java.util.ArrayList;
import android.widget.AdapterView.OnItemClickListener;
import android.os.Bundle;

public class BaseActivityForOffice extends AppCompatActivity
{
    ArrayList<Event> events = setInitialData();

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


        // получаем элемент ListView
        ListView eventsList = findViewById(R.id.events);

        // создаем адаптер
        //ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, name_of_ivents);
        EventArrayAdapter eventArrayAdapter = new EventArrayAdapter(this, R.layout.list_event, events);


        // устанавливаем для списка адаптер
        eventsList.setAdapter(eventArrayAdapter);

        eventsList.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                // TODO Auto-generated method stub
                onEvent(position);
            }
        });
    }

    public void onExit(View view)
    {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    public void onOrderList(View view)
    {
        Intent intent = new Intent(this, EventListForOfficeActivity.class);
        intent.putExtra("EventsExtra", events);
        startActivity(intent);
    }
    public void onCalendar(View view)
    {
        Intent intent = new Intent(this, CalendarForOfficeActivity.class);
        startActivity(intent);
    }
    public void onWorkers(View view)
    {
        Intent intent = new Intent(this, WorkerListForOfficeActivity.class);
        startActivity(intent);
    }
    public void onEvent(int position)
    {
        Intent intent = new Intent(this, EventPageForOfficeActivity.class);
        intent.putExtra(Event.class.getSimpleName(), events.get(position));
        startActivity(intent);
    }
    private ArrayList<Event> setInitialData()
    {
        String[] name_of_events = {"Замеры для установки шкафа", "Установка дверей", "Замена бракованной кухонной плитки"};
        LocalDate[] date_of_events = {LocalDate.of(2017, 11, 30), LocalDate.of(2017, 8, 31), LocalDate.of(2018, 1, 2)};
        ArrayList<Event> events = new ArrayList<Event>();
        for(int i=0; i<10; i++)
        {
            events.add(new Event(name_of_events[0], date_of_events[0], R.drawable.test, null, null, null, "Эдуадр Крхтбекович", "89850284972", null, "Улица Постышева, дом 2 корп. 4, кв. 33"));
            events.add(new Event(name_of_events[1], date_of_events[1], R.drawable.test1, null, null, null, "Волоколамская Лидия Петровна", "89850284972", null, "Проспект Ветеринаров, дом 5, кв. 124"));
            events.add(new Event(name_of_events[2], date_of_events[2], R.drawable.test, null, null, null, "Зинаида Ивановна", "89850284972", null, "Юбилейный проспект, д.2, стр.11, помещение 2"));
        }
        for(int i=0; i<30; i = i + 5)
        {
            events.set(i, new Event(events.get(i).getName_of_event(), events.get(i).getDate_of_event(), null, LocalTime.of(14, 00), LocalTime.of(16,00), null, "Кристиан Бейл", "89850284972", null, "Улица Постышева, дом 2 корп. 4, кв. 33"));

        }
        for(int i=0; i<30; i = i + 3)
        {
            events.set(i, new Event(events.get(i).getName_of_event(), events.get(i).getDate_of_event(), events.get(i).getImages_of_event(), LocalTime.of(14, 00), LocalTime.of(16,00), null, "Балданов Зоригто Немединович", "89850284972", null, "Улица Постышева, дом 2 корп. 4, кв. 33"));

        }
        Event TEST = new Event(name_of_events[2], date_of_events[2], R.drawable.test, null, null, null, "Леонардо да Винчи", "89850284972", null, "Улица Постышева, дом 2 корп. 4, кв. 33");
        TEST.setImages_of_event(R.drawable.test1);
        TEST.setImages_of_event(R.drawable.quad_green);
        TEST.setImages_of_event(R.drawable.test_worker1);


        events.set(2, TEST);
        return events;
    }
}

