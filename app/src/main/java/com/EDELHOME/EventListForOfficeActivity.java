package com.EDELHOME;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;

import java.time.LocalDate;
import java.util.ArrayList;

public class EventListForOfficeActivity extends AppCompatActivity
{

    String[] name_of_events = {"Замеры для установки шкафа", "Установка дверей", "Замена бракованной кухонной плитки"};
    LocalDate[] date_of_events = {LocalDate.of(2017, 11, 30), LocalDate.of(2017, 8, 31), LocalDate.of(2018, 1, 2)};
    // Ivents[] ivents = {new Ivents(name_of_ivents[0], date_of_ivents[0]), new Ivents(name_of_ivents[1], date_of_ivents[1]), new Ivents(name_of_ivents[2], date_of_ivents[2]) } ;
    ArrayList<Event> events = new ArrayList<Event>();
    //ListView iventsList;

    String[] sort_method = {"По дате (ближайшие)", "По дате (с конца)", "По дате добавления (сначала новые)", "По дате добавления (сначала старые)"};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list_for_office);

        Spinner spinner = findViewById(R.id.spinner);
        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> spinner_adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, sort_method);
        // Определяем разметку для использования при выборе элемента
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinner.setAdapter(spinner_adapter);


        setInitialData();
        // получаем элемент GridView
        GridView eventsList = findViewById(R.id.gridView);
        // создаем адаптер
        eventsList.setAdapter(new EventGridAdapter(this, events));



        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {

                // Получаем выбранный объект
                String item = (String)parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
            }
        };
        spinner.setOnItemSelectedListener(itemSelectedListener);
    }

    private void setInitialData()
    {
        for(int i=0; i<10; i++)
        {
            events.add(new Event(name_of_events[0], date_of_events[0], R.drawable.test));
            events.add(new Event(name_of_events[1], date_of_events[1], R.drawable.test1));
            events.add(new Event(name_of_events[2], date_of_events[2], R.drawable.test));
        }

    }
}