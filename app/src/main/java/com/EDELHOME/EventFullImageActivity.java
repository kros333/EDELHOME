package com.EDELHOME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.github.chrisbanes.photoview.PhotoViewAttacher;

public class EventFullImageActivity extends AppCompatActivity
{
    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_image_event);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        // get intent data
        Intent intent = getIntent();

        // Selected image id

        int image = intent.getExtras().getInt("img");
        ImageView imageView = (ImageView) findViewById(R.id.full_image_view);
        imageView.setImageResource(image);
        PhotoViewAttacher pAttacher;
        pAttacher = new PhotoViewAttacher(imageView);
        pAttacher.update();
    }

}



