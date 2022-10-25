package com.EDELHOME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onClick(View view) {
        EditText loginText = findViewById(R.id.login);
        String login = loginText.getText().toString();
        if(login.equals("Соня") || login.equals("Кирилл") || login.equals("Влад") || login.equals("Егор") || login.equals("Саня") || login.equals("Ваня"))
        {
            Intent intent = new Intent(this, BaseActivity.class);
            startActivity(intent);
        }
    }
}