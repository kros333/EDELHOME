package com.EDELHOME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.FieldClassification;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemClickListener;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventListForOfficeActivity extends AppCompatActivity implements FilterDialogInterface
{
    String[] sort_method = {"По дате (ближайшие)", "По дате (с конца)", "По дате добавления (сначала новые)", "По дате добавления (сначала старые)"};
    String[] filter_method = {"Все", "Замеры", "Мебель", "Кухни", "Окна", "Входные двери", "Межкомнатные двери"};
    ArrayList<Event> events = new ArrayList<>();
    ArrayList<Event> s_f_events = new ArrayList<>();
    int filter = 0;
    int sort = 1;
    EventGridAdapter eventGridAdapter;
    GridView eventsList;
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
        eventsList = findViewById(R.id.gridView);
        // создаем адаптер
        if (filter != 0)
        {
            eventGridAdapter = new EventGridAdapter(this, s_f_events);
        }
        else
        {
            eventGridAdapter = new EventGridAdapter(this, events);
        }
        eventsList.setAdapter(eventGridAdapter);
        eventsList.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                // TODO Auto-generated method stub
                onEvent(position);
            }
        });
        EditText search = findViewById(R.id.search_text);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                //Pattern pattern = Pattern.compile(s.toString()+"(\\w*)");
                //ArrayList<Event> search_events = new ArrayList<>();
                if (filter == 0)
                {
                    s_f_events.clear();
                    if(s.toString() != "")
                    {
                        for(int i = 0; i < events.size(); i++)
                        {
                            //Matcher matcher = pattern.matcher(events.get(i).getName_of_event());
                            //if (matcher.find())
                            if (events.get(i).getName_of_event().toLowerCase().indexOf(s.toString().toLowerCase()) != -1
                                    || events.get(i).getPhone_number().toLowerCase().indexOf(s.toString().toLowerCase()) != -1
                                    || events.get(i).getName_of_client().toLowerCase().indexOf(s.toString().toLowerCase()) != -1
                                    || events.get(i).getAddress().toLowerCase().indexOf(s.toString().toLowerCase()) != -1)
                            {
                                s_f_events.add(events.get(i));
                            }
                        }
                        eventGridAdapter = new EventGridAdapter(EventListForOfficeActivity.this, s_f_events);
                        eventsList.setAdapter(eventGridAdapter);
                    }
                    else
                    {
                        eventGridAdapter = new EventGridAdapter(EventListForOfficeActivity.this, events);
                        eventsList.setAdapter(eventGridAdapter);
                    }

                }
                else
                {
//                    for(int i = 0; i < search_events.size(); i++)
//                    {
//                        if (events.get(i).getName_of_event().matches(s.toString()) == true)
//                        {
//                            search_events.add(events.get(i));
//                        }
//                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void onEvent(int position)
    {
        Intent intent = new Intent(this, EventPageForOfficeActivity.class);
        if (filter == 0)
        {
            if(s_f_events.size() != 0)
            {
                intent.putExtra(Event.class.getSimpleName(), s_f_events.get(position));
            }
            else
            {
                intent.putExtra(Event.class.getSimpleName(), events.get(position));
            }
        }
        else
        {
            for (int i = 0; i < events.size(); i++)
            {
                if (events.get(i).getId() == s_f_events.get(position).getId())
                {
                    intent.putExtra(Event.class.getSimpleName(), events.get(i));
                }
            }
        }
        startActivity(intent);
    }
    public void onSortButton(View v)
    {
        EventListSortDialogFragment dialog = new EventListSortDialogFragment(this);
        dialog.show(getSupportFragmentManager(), "custom");
    }
    @Override
    public void onSendData(int filter, int sort) {
        this.filter = filter;
        this.sort = sort;
        if (filter != 0)
        {
            s_f_events.clear();
            for(int i = 0; i < events.size(); i++)
            {
                if (this.filter == events.get(i).getCategory())
                {
                    s_f_events.add(events.get(i));
                }
            }
            eventGridAdapter = new EventGridAdapter(this, s_f_events);
        }
        else
        {
            eventGridAdapter = new EventGridAdapter(this, events);
        }
        eventsList.setAdapter(eventGridAdapter);
    }
}