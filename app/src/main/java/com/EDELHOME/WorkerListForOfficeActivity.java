package com.EDELHOME;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;


public class WorkerListForOfficeActivity extends AppCompatActivity {


    String[] name_of_events = {"Замеры для установки шкафа", "Установка дверей", "Замена бракованной кухонной плитки"};
    LocalDate[] date_of_events = {LocalDate.of(2017, 11, 30), LocalDate.of(2017, 8, 31), LocalDate.of(2018, 1, 2)};
    // Ivents[] ivents = {new Ivents(name_of_ivents[0], date_of_ivents[0]), new Ivents(name_of_ivents[1], date_of_ivents[1]), new Ivents(name_of_ivents[2], date_of_ivents[2]) } ;
    ArrayList<Event> events = new ArrayList<Event>();
    //ListView iventsList;


    TextView currentDate;
    Calendar dateAndTime = Calendar.getInstance();
    LocalDate localDate = LocalDateTime.ofInstant(dateAndTime.toInstant(), dateAndTime.getTimeZone().toZoneId()).toLocalDate();
    ArrayList<Worker> workerArrayList = new ArrayList<Worker>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_list_for_office);
        //setWorkerAndEventData();
        createWorkerArrayList();

        currentDate = findViewById(R.id.textView3);
        setInitialDate();

        String[] specialization = {"Замеры", "Окна", "Двери", "Мебель", "Кухни"};
        Spinner spinner = findViewById(R.id.spinner3);
        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> spinner_adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, specialization);
        // Определяем разметку для использования при выборе элемента
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinner.setAdapter(spinner_adapter);

        GridView workersList = findViewById(R.id.gridView);
        // создаем адаптер
        workersList.setAdapter(new WorkerGridAdapter(this, workerArrayList));
    }
    // отображаем диалоговое окно для выбора даты
    public void onSetDate(View v) {
        new DatePickerDialog(WorkerListForOfficeActivity.this, d,
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
    // установка начальных даты и времени
    private void setInitialDate() {

        currentDate.setText(DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR
                        ));
    }
    private void createWorkerArrayList()
    {
        for(int i=0; i<4; i++)
        {
            workerArrayList.add(new Worker("Станиславович Леонидович Вячеславович", R.drawable.test_worker));
            workerArrayList.add(new Worker("Жена :з", R.drawable.test_worker2));
            workerArrayList.add(new Worker("Жена Егора", R.drawable.test_worker1));
            workerArrayList.add(new Worker("Георгианский календарь ШТО", R.drawable.test_worker3));
            workerArrayList.add(new Worker("хз кто но пусть тоже будет шобы обидно не было", R.drawable.test1));


        }

    }
//    private void setWorkerAndEventData()
//    {
//        for(int i=0; i<10; i++)
//        {
//            events.add(new Event(name_of_events[0], date_of_events[0], R.drawable.test, null, null, null));
//            events.add(new Event(name_of_events[1], date_of_events[1], R.drawable.test1, null, null, null));
//            events.add(new Event(name_of_events[2], date_of_events[2], R.drawable.test, null, null, null));
//        }
//        for(int i=0; i<30; i += 5)
//        {
//            int j = i;
//            if (j>20)
//                j -= 20;
//            events.set(i, new Event(events.get(j).getName_of_event(), events.get(j).getDate_of_event(), 0, LocalTime.of(14, 00), LocalTime.of(16,00), workerArrayList.get(j)));
//
//        }
//        for(int i=0; i<30; i = i + 3)
//        {
//            int j = i;
//            if (j>20)
//                j -= 20;
//            events.set(i, new Event(events.get(j).getName_of_event(), events.get(j).getDate_of_event(), events.get(j).getImage_of_event(), LocalTime.of(11, 00), LocalTime.of(17,00), workerArrayList.get(j)));
//
//        }
//
//    }
}