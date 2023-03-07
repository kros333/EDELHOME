package com.EDELHOME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.os.Bundle;
import android.service.autofill.FieldClassification;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemClickListener;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventListForOfficeActivity extends AppCompatActivity implements FilterDialogInterface
{
    ArrayList<Event> events = new ArrayList<>();
    ArrayList<Event> s_f_events = new ArrayList<>();
    ArrayList<Event> s_s_f_events = new ArrayList<>();
    int filter = 0;
    int sort = 1;
    boolean archive = false;
    EventGridAdapter eventGridAdapter;
    GridView eventsList;
    ImageButton clear_text;
    ImageButton sortButton;
    EditText search;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list_for_office);
        mToolbar = findViewById(R.id.toolbar);
        sortButton = findViewById(R.id.sortButton);
        setSupportActionBar(mToolbar);

        Bundle arguments = getIntent().getExtras();
        if(arguments!=null)
        {
            events = (ArrayList<Event>) arguments.getSerializable("EventsExtra");
        }


        // получаем элемент GridView
        eventsList = findViewById(R.id.gridView);
        clear_text = findViewById(R.id.clear_text);
        search = findViewById(R.id.search_text);
        clear_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.getText().clear();
            }
        });
        onSendData(filter, sort, archive);
        eventsList.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                // TODO Auto-generated method stub
                onEvent(position);
            }
        });
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                //Pattern pattern = Pattern.compile(s.toString()+"(\\w*)");
                //ArrayList<Event> search_events = new ArrayList<>();
                s_s_f_events.clear();
                if(count != 0)
                {
                    for(int i = 0; i < s_f_events.size(); i++)
                    {
                            //Matcher matcher = pattern.matcher(events.get(i).getName_of_event());
                            //if (matcher.find())
                        if (s_f_events.get(i).getName_of_event().toLowerCase().indexOf(s.toString().toLowerCase()) != -1
                                || s_f_events.get(i).getPhone_number().toLowerCase().indexOf(s.toString().toLowerCase()) != -1
                                || s_f_events.get(i).getName_of_client().toLowerCase().indexOf(s.toString().toLowerCase()) != -1
                                || s_f_events.get(i).getAddress().toLowerCase().indexOf(s.toString().toLowerCase()) != -1)
                        {
                            s_s_f_events.add(s_f_events.get(i));
                        }
                    }
                    eventGridAdapter = new EventGridAdapter(EventListForOfficeActivity.this, s_s_f_events);
                    eventsList.setAdapter(eventGridAdapter);
                }
                else
                {
                    eventGridAdapter = new EventGridAdapter(EventListForOfficeActivity.this, s_f_events);
                    eventsList.setAdapter(eventGridAdapter);
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
//        if (filter == 0)
//        {
            if(s_f_events.size() != 0 || s_s_f_events.size() != 0)
            {
                if(s_s_f_events.size() != 0)
                {
                    intent.putExtra(Event.class.getSimpleName(), s_s_f_events.get(position));
                }
                else
                {
                    intent.putExtra(Event.class.getSimpleName(), s_f_events.get(position));
                }
            }
            else
            {
                intent.putExtra(Event.class.getSimpleName(), events.get(position));
            }
//        }
//        else
//        {
//            for (int i = 0; i < events.size(); i++)
//            {
//                if (events.get(i).getId() == s_f_events.get(position).getId())
//                {
//                    intent.putExtra(Event.class.getSimpleName(), events.get(i));
//                }
//            }
//        }
        startActivity(intent);
    }
    public void onSortButton(View v)
    {
        EventListSortDialogFragment dialog = new EventListSortDialogFragment(this, filter, sort, archive);
        dialog.show(getSupportFragmentManager(), "custom");
    }

    @Override
    public void onSendData(int filter, int sort, boolean archive) {
        this.filter = filter;
        this.sort = sort;
        this.archive = archive;
        s_f_events.clear();
        if (filter != 0 || archive || sort != 1)
        {
            sortButton.getDrawable().setColorFilter(getResources().getColor(R.color.sort_on), PorterDuff.Mode.SRC_ATOP);
        }
        else
        {
            sortButton.getDrawable().clearColorFilter();
        }
        for(int i = 0; i < events.size(); i++)
        {
            if ((this.filter == events.get(i).getCategory() || this.filter == 0) && this.archive == events.get(i).getIn_archive())
            {
                s_f_events.add(events.get(i));
            }
        }
        Collections.sort(s_f_events, new SortEventsClass(sort));

        String search_text = this.search.getText().toString();
        if(search_text != "")
        {
            s_s_f_events.clear();
            for(int i = 0; i < s_f_events.size(); i++)
            {
                //Matcher matcher = pattern.matcher(events.get(i).getName_of_event());
                //if (matcher.find())
                if (s_f_events.get(i).getName_of_event().toLowerCase().indexOf(search_text.toString().toLowerCase()) != -1
                        || s_f_events.get(i).getPhone_number().toLowerCase().indexOf(search_text.toString().toLowerCase()) != -1
                        || s_f_events.get(i).getName_of_client().toLowerCase().indexOf(search_text.toString().toLowerCase()) != -1
                        || s_f_events.get(i).getAddress().toLowerCase().indexOf(search_text.toString().toLowerCase()) != -1)
                {
                    s_s_f_events.add(s_f_events.get(i));
                }
            }
            eventGridAdapter = new EventGridAdapter(EventListForOfficeActivity.this, s_s_f_events);
            eventsList.setAdapter(eventGridAdapter);
        }
        else
        {
            eventGridAdapter = new EventGridAdapter(EventListForOfficeActivity.this, s_f_events);
            eventsList.setAdapter(eventGridAdapter);
        }

    }
    public int getButtonBackgroundColor(ImageButton clear_text)
    {
        int buttonColor = 0;

        if (clear_text.getBackground() instanceof ColorDrawable) {
            ColorDrawable cd = (ColorDrawable) clear_text.getBackground();
            buttonColor = cd.getColor();
        }

        if (clear_text.getBackground() instanceof RippleDrawable) {
            RippleDrawable rippleDrawable = (RippleDrawable) clear_text.getBackground();
            Drawable.ConstantState state = rippleDrawable.getConstantState();
            try {
                Field colorField = state.getClass().getDeclaredField("mColor");
                colorField.setAccessible(true);
                ColorStateList colorStateList = (ColorStateList) colorField.get(state);
                buttonColor = colorStateList.getDefaultColor();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return buttonColor;
    }
}