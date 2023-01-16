package com.EDELHOME;
import java.io.*;
import java.util.Arrays;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public MainActivity() throws FileNotFoundException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        FileReader reader = new FileReader("login.txt");
//        char[] buf = new char[256];
//        int c;
//        while((c = reader.read(buf))>0){
//
//            if(c < 256){
//                buf = Arrays.copyOf(buf, c);
//            }
//            System.out.print(buf);
//        }
    }






    public void onClick(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}