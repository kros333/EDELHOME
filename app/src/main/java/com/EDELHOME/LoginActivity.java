package com.EDELHOME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.SharedPreferences;



public class LoginActivity extends AppCompatActivity {

    private static final String PREFS_FILE = "Account";
    private static final String PREF_NAME = "go";

    String login;
    SharedPreferences Account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Account = getSharedPreferences(PREFS_FILE, MODE_PRIVATE);
        if (Account.getString(PREF_NAME,null) != null)
        {
            Intent intent = new Intent(this, BaseActivityForOffice.class);
            intent.putExtra("login", login);
            startActivity(intent);
        }
    }

    public void onClick(View view) {
        EditText loginText = findViewById(R.id.login);
        login = loginText.getText().toString();
        if(login.equals("Соня") || login.equals("Кирилл") || login.equals("Влад") || login.equals("Егор") || login.equals("Саня") || login.equals("Ваня"))
        {
            SharedPreferences.Editor prefEditor = Account.edit();
            prefEditor.putString(PREF_NAME, login);
            prefEditor.apply();
            Intent intent = new Intent(this, BaseActivityForOffice.class);
            intent.putExtra("login", login);
            startActivity(intent);
        }
        else
        {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Неправильный логин/пароль", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();
        }
    }
}