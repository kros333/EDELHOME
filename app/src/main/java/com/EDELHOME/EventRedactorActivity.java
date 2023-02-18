package com.EDELHOME;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class EventRedactorActivity extends AppCompatActivity {
    String[] filter_method = {"Замеры", "Мебель", "Окна", "Входные двери", "Межкомнатные двери", "Кухни"};
    int time_button;
    Button date;
    Button time;
    Button time2;
    Calendar dateAndTime = Calendar.getInstance();
    LocalDate localDate = LocalDateTime.ofInstant(dateAndTime.toInstant(), dateAndTime.getTimeZone().toZoneId()).toLocalDate();
    LocalTime localTime = LocalDateTime.ofInstant(dateAndTime.toInstant(), dateAndTime.getTimeZone().toZoneId()).toLocalTime();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_redactor);

        EditText name_of_event = findViewById(R.id.name_of_event);
        EditText name_of_client = findViewById(R.id.name_of_client);
        EditText phone = findViewById(R.id.phone);
        EditText address = findViewById(R.id.address);
        date = findViewById(R.id.setDate);
        time = findViewById(R.id.setTime);
        time2 = findViewById(R.id.setTime2);

        Spinner filter = findViewById(R.id.filter);
        ArrayAdapter<String> filter_adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, filter_method);
        filter_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filter.setAdapter(filter_adapter);
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
        filter.setOnItemSelectedListener(itemSelectedListener);

        Bundle arguments = getIntent().getExtras();

        if (arguments != null) {
            Event event = (Event) arguments.getSerializable(Event.class.getSimpleName());

            name_of_client.setText(event.getName_of_client());
            name_of_event.setText(event.getName_of_event());
            phone.setText(event.getPhone_number());
            address.setText(event.getAddress());

            if(event.getDate_of_event() == null)
            {
                date.setText("Выбрать дату");
                time.setEnabled(false);
            }
            else
                date.setText(event.getDate_of_eventInString());
            if(event.getStart_of_event() == null)
            {
                time.setText("Начало");
                time2.setEnabled(false);
            }
            else
            {
                time.setText(event.getStart_of_eventInString());
            }
            if(event.getEnd_of_event() == null)
            {
                time2.setText("Конец");

            }
            else
            {
                time2.setText(event.getEnd_of_eventInString());
            }
        }
        else
        {
            date.setText("Выбрать дату");
            time.setEnabled(false);
            time.setText("Начало");
            time2.setEnabled(false);
            time2.setText("Конец");
        }
    }

    public void onSetDate(View v) {
        new DatePickerDialog(EventRedactorActivity.this, d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    // установка обработчика выбора даты
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            localDate = LocalDateTime.ofInstant(dateAndTime.toInstant(), dateAndTime.getTimeZone().toZoneId()).toLocalDate();
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
//            String localeDateString = localDate.format(formatter);
//            currentDate.setText(localeDateString);
            setInitialDate();
        }
    };

    private void setInitialDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        date.setText(localDate.format(formatter));
        time.setEnabled(true);
    }

    public void onSetTime(View v)
    {
        time_button = 1;
        new TimePickerDialog(EventRedactorActivity.this, t,
                dateAndTime.get(Calendar.HOUR_OF_DAY),
                dateAndTime.get(Calendar.MINUTE), true)
                .show();
    }
    public void onSetTime2(View v)
    {
        time_button = 2;
        new TimePickerDialog(EventRedactorActivity.this, t,
                dateAndTime.get(Calendar.HOUR_OF_DAY),
                dateAndTime.get(Calendar.MINUTE), true)
                .show();
    }
    // установка обработчика выбора времени
    TimePickerDialog.OnTimeSetListener t=new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateAndTime.set(Calendar.MINUTE, minute);
            localTime = LocalDateTime.ofInstant(dateAndTime.toInstant(), dateAndTime.getTimeZone().toZoneId()).toLocalTime();
            setInitialTime();
        }
    };
    private void setInitialTime()
    {
//        if()
//        {
//
//        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        if(time_button == 1)
        {
            time.setText(localTime.format(formatter));
            time2.setEnabled(true);
        }
        if(time_button == 2)
        {
            time2.setText(localTime.format(formatter));
        }
    }

}