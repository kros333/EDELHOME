package com.EDELHOME;

import static android.service.controls.ControlsProviderService.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import org.apache.http.impl.client.CloseableHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class CreateUserActivity extends AppCompatActivity {

    String debug = "g";
    boolean ok = false;
    private Toolbar mToolbar;
    EditText user_login;
    EditText user_pass;
    CheckBox user_isadmin;
    AppUser appuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        user_login = findViewById(R.id.user_login);
        user_pass = findViewById(R.id.user_pass);
        user_isadmin = findViewById(R.id.user_isadmin);
        Button save_user = findViewById(R.id.save_user);
        Button cancel_create_user = findViewById(R.id.cancel_create_user);
        save_user.setOnClickListener(onSave);
        cancel_create_user.setOnClickListener(onCancel);
    }

    View.OnClickListener onCancel = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(CreateUserActivity.this, BaseActivityForOffice.class);
            startActivity(intent);
        }
    };
    View.OnClickListener onSave = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (user_login.getText().toString() != "" && user_pass.getText().toString() != "" && user_login.getText().toString() != null && user_pass.getText().toString() != null && !user_login.getText().toString().isEmpty() && !user_pass.getText().toString().isEmpty() && user_pass.getText().length() > 5 && !user_pass.getText().toString().contains(" ")) {
                appuser = new AppUser();
                appuser.setLogin(user_login.getText().toString());
                appuser.setPass(user_pass.getText().toString());
                appuser.setIsadmin(user_isadmin.isChecked());
                ExecutorService executor = Executors.newSingleThreadExecutor();
                Handler handler = new Handler(Looper.getMainLooper());

                executor.execute(() -> {
                    try {
                        ok = POSTAppUser();
                        if (ok) {
                            ClipboardManager clipboard = (ClipboardManager) CreateUserActivity.this.getSystemService(Context.CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText("", ("Логин: " + user_login.getText() + "\nПароль: " + user_pass.getText()));
                            clipboard.setPrimaryClip(clip);
                            Intent intent = new Intent(CreateUserActivity.this, BaseActivityForOffice.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(CreateUserActivity.this, debug, Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                    handler.post(() -> {

                        //Toast.makeText(CreateUserActivity.this, params.toString(), Toast.LENGTH_LONG).show();

                        //UI Thread work here
                    });
                });
            } else {
                if (user_pass.getText().length() < 6 || user_pass.getText().toString().contains(" "))
                    if (user_pass.getText().length() < 6)
                        Toast.makeText(getApplicationContext(), "Пароль не менее 6 символов.", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(getApplicationContext(), "Пароль не должен содержать пробелы.", Toast.LENGTH_LONG).show();
                else {
                    Toast.makeText(getApplicationContext(), "Логин не может быть пустым.", Toast.LENGTH_LONG).show();
                }
            }
        }
    };


    public boolean POSTAppUser() throws JSONException, IOException {
        Gson gson = new Gson();
        boolean x = true;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpPost request = new HttpPost("https://24e8-213-208-174-203.eu.ngrok.io/appusers");
            StringEntity params = new StringEntity(gson.toJson(appuser), "UTF-8");
            request.addHeader("content-type", "application/json; charset=UTF-8");
            request.setEntity(params);
            httpClient.execute(request);
        } catch (Exception ex) {
            debug = ex.toString();
            x = false;
        }
        httpClient.close();
        return x;
    }
}