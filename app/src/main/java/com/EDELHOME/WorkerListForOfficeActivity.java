package com.EDELHOME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import android.view.Window;
import android.view.WindowManager;
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


    TextView currentDate;
    Calendar dateAndTime = Calendar.getInstance();
    LocalDate localDate = LocalDateTime.ofInstant(dateAndTime.toInstant(), dateAndTime.getTimeZone().toZoneId()).toLocalDate();
    ArrayList<Worker> workerArrayList = new ArrayList<Worker>();
    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_list_for_office);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        Bundle arguments = getIntent().getExtras();
        if(arguments!=null)
        {
            workerArrayList = (ArrayList<Worker>) arguments.getSerializable("WorkersExtra");
        }

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
}