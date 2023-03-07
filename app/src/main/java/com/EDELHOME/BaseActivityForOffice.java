package com.EDELHOME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.time.LocalDate;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.ListView;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.UUID;

import android.widget.AdapterView.OnItemClickListener;
import android.os.Bundle;



public class BaseActivityForOffice extends AppCompatActivity
{
    ArrayList<Event> events = createEventArrayList();
    ArrayList<Worker> workers = createWorkerArrayList();
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_for_office);

        //TextView textView = findViewById(R.id.textView);
        Bundle arguments = getIntent().getExtras();

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);


//        if(arguments!=null)
//        {
//            String login = arguments.get("login").toString();
//            textView.setText(login);
//            textView.setTextSize(26);
//        }

        ArrayList<Event> today_events = new ArrayList<>();
        for (int i = 0; i < events.size(); i++)
        {
            if(events.get(i).getDate_of_event().isEqual(LocalDate.now()))
                today_events.add(events.get(i));
        }
        ArrayList<Event> yesterday_events = new ArrayList<>();
        for (int i = 0; i < events.size(); i++)
        {
            if(events.get(i).getDate_of_event().isEqual(LocalDate.now().plusDays(1)))
                yesterday_events.add(events.get(i));
        }

        // получаем элемент ListView
        ListView tEventsList = findViewById(R.id.tEvents);
        ListView yEventsList = findViewById(R.id.yEvents);

        // создаем адаптер
        //ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, name_of_ivents);
        EventArrayAdapter tEventArrayAdapter = new EventArrayAdapter(this, R.layout.list_event, today_events);
        EventArrayAdapter yEventArrayAdapter = new EventArrayAdapter(this, R.layout.list_event, yesterday_events);


        // устанавливаем для списка адаптер
        tEventsList.setAdapter(tEventArrayAdapter);
        yEventsList.setAdapter(yEventArrayAdapter);

        tEventsList.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                // TODO Auto-generated method stub
                onEvent(today_events.get(position).getId());
            }
        });
        yEventsList.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                // TODO Auto-generated method stub
                onEvent(yesterday_events.get(position).getId());
            }
        });
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
        intent.putExtra("WorkersExtra", workers);
        startActivity(intent);
    }
    public void onEvent(UUID id)
    {
        Intent intent = new Intent(this, EventPageForOfficeActivity.class);
        for (int i = 0; i < events.size(); i++)
        {
            if (events.get(i).getId() == id)
            {
                intent.putExtra(Event.class.getSimpleName(), events.get(i));
                startActivity(intent);
            }
        }

    }
    public void onCreateOrder(View view)
    {
        Intent intent = new Intent(this, EventRedactorActivity.class);
        startActivity(intent);
    }

    private ArrayList<Event> createEventArrayList()
    {
        String[] name_of_events = {"Замеры для установки шкафа", "Установка дверей", "Замена бракованной кухонной плитки"};
        LocalDate[] date_of_events = {LocalDate.now(), LocalDate.now(), LocalDate.of(2018, 1, 2)};
        ArrayList<Event> events = new ArrayList<Event>();
        for(int i=0; i<10; i++)
        {
            events.add(new Event(name_of_events[0], date_of_events[0], R.drawable.test, null, null, null, "Эдуадр Крхтбекович", "89850284972", null, "Улица Постышева, дом 2 корп. 4, кв. 33", 1, false));
            events.add(new Event(name_of_events[1], date_of_events[1], R.drawable.test1, null, null, null, "Волоколамская Лидия Петровна", "89850284972", null, "Проспект Ветеринаров, дом 5, кв. 124", 5, false));
            events.add(new Event(name_of_events[2], date_of_events[2], R.drawable.test, null, null, null, "Зинаида Ивановна", "89850284972", null, "Юбилейный проспект, д.2, стр.11, помещение 2", 6, false));
        }
        for(int i=0; i<30; i = i + 5)
        {
            events.set(i, new Event(events.get(i).getName_of_event(), LocalDate.now().plusDays(1), null, LocalTime.of(14, 00), LocalTime.of(16,00), null, "Кристиан Бейл", "89850284972", null, "Улица Постышева, дом 2 корп. 4, кв. 33", 4, false));

        }
        for(int i=0; i<30; i = i + 3)
        {
            events.set(i, new Event(events.get(i).getName_of_event(), events.get(i).getDate_of_event(), events.get(i).getImages_of_event(), LocalTime.of(14, 00), LocalTime.of(16,00), null, "Балданов Зоригто Немединович", "89850284972", null, "Улица Постышева, дом 2 корп. 4, кв. 33", 3, false));

        }
        Event TEST = new Event("ЛУЧШЕЕ ПРЕДЛОЖЕНИЕ", LocalDate.now(), R.drawable.test, null, null, null, "Леонардо да Винчи", "89850284972", null, "Улица Постышева, дом 2 корп. 4, кв. 33", 1, false);
        TEST.setImages_of_event(R.drawable.test1);
        TEST.setImages_of_event(R.drawable.quad_green);
        TEST.setImages_of_event(R.drawable.test_worker1);
        events.get(20).setIn_archive(true);
        events.get(22).setIn_archive(true);

        events.set(2, TEST);
        return events;
    }
    private ArrayList<Worker> createWorkerArrayList()
    {
        ArrayList<Worker> workerArrayList = new ArrayList<>();
        for(int i=0; i<4; i++)
        {
            workerArrayList.add(new Worker("Станиславович Леонидович Вячеславович", R.drawable.test_worker));
            workerArrayList.add(new Worker("Жена :з", R.drawable.test_worker2));
            workerArrayList.add(new Worker("Жена Егора", R.drawable.test_worker1));
            workerArrayList.add(new Worker("Георгианский календарь ШТО", R.drawable.test_worker3));
            workerArrayList.add(new Worker("хз кто но пусть тоже будет шобы обидно не было", R.drawable.test1));


        }
        return workerArrayList;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.create_user:
                Intent intent = new Intent(this, CreateUserActivity.class);
                startActivity(intent);
                return true;
            case R.id.settings:
                return true;
            case R.id.logout:
                // Запускаем вторую активность
                Intent intent1 = new Intent(this, LoginActivity.class);
                startActivity(intent1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

