package com.EDELHOME;

import androidx.appcompat.app.AppCompatActivity;
import java.time.LocalDate;


import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

import android.os.Bundle;

public class BaseActivityForOffice extends AppCompatActivity
{

    String[] name_of_ivents = {"Замеры для установки шкафа", "Установка дверей", "Замена бракованной кухонной плитки"};
    LocalDate[] date_of_ivents = {LocalDate.of(2017, 11, 30), LocalDate.of(2017, 8, 31), LocalDate.of(2018, 1, 2)};
   // Ivents[] ivents = {new Ivents(name_of_ivents[0], date_of_ivents[0]), new Ivents(name_of_ivents[1], date_of_ivents[1]), new Ivents(name_of_ivents[2], date_of_ivents[2]) } ;
    ArrayList<Ivents> ivents = new ArrayList<Ivents>();
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
        ListView iventsList = findViewById(R.id.ivents);

        // создаем адаптер
        //ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, name_of_ivents);
        IventsAdapter iventsAdapter = new IventsAdapter(this, R.layout.list_ivent, ivents);


        // устанавливаем для списка адаптер
        iventsList.setAdapter(iventsAdapter);


    }

    public void onClick(View view)
    {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    private void setInitialData()
    {
        ivents.add(new Ivents(name_of_ivents[0], date_of_ivents[0]));
        ivents.add(new Ivents(name_of_ivents[1], date_of_ivents[1]));
        ivents.add(new Ivents(name_of_ivents[2], date_of_ivents[2]));
    }
}