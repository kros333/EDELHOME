package com.EDELHOME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;


import android.widget.AdapterView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class EventPageForOfficeActivity extends AppCompatActivity {

    Event event;
    int pos = 0;
    ViewPager viewPager;
    TextView count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_page_for_office);
        Bundle arguments = getIntent().getExtras();
        count = findViewById(R.id.count);

        if(arguments!=null)
        {
            event = (Event) arguments.getSerializable(Event.class.getSimpleName());
            viewPager = findViewById(R.id.viewpager);
            EventImageAdapter adapterForPosition = new EventImageAdapter(EventPageForOfficeActivity.this, event.getImages_of_event());
            PagerAdapter adapter = adapterForPosition;
            viewPager.setAdapter(adapter);
            viewPager.setCurrentItem(0);
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    pos = position;
                    count.setText(" " + (pos + 1) + " / " + event.getCountOfImages() + " ");
                    if (pos == 0)
                    {
                        View b = findViewById(R.id.left);
                        //b.setVisibility(View.INVISIBLE);
                        b.setAlpha(0);
                    }
                    if (pos == event.getCountOfImages() - 2)
                    {
                        View b = findViewById(R.id.right);
                        //b.setVisibility(View.VISIBLE);
                        b.setAlpha((float) 0.7);
                    }
                    if (pos == event.getCountOfImages() - 1)
                    {
                        View b = findViewById(R.id.right);
                        //b.setVisibility(View.INVISIBLE);
                        b.setAlpha(0);
                    }
                    if (pos == 1)
                    {
                        View b = findViewById(R.id.left);
                        //b.setVisibility(View.VISIBLE);
                        b.setAlpha((float) 0.7);
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
//            viewPager.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    // посылаем идентификатор картинки в FullScreenActivity
//                    Intent i = new Intent(getApplicationContext(),
//                            FullImageEventActivity.class);
//                    // передаем индекс массива
//                    i.putExtra("img", event.getImages_of_event().get(position));
//                    startActivity(i);
//                }
//            });
        }
        View l = findViewById(R.id.left);
        //l.setVisibility(View.INVISIBLE);
        l.setAlpha(0);
        if(event.getCountOfImages() == 1)
        {
            View r = findViewById(R.id.right);
            //r.setVisibility(View.INVISIBLE);
            r.setAlpha(0);
        }
        count.setText(" " + (pos + 1) + " / " + event.getCountOfImages() + " ");
    }
    public void onImg(View view)
    {
        Intent i = new Intent(getApplicationContext(), EventFullImageActivity.class);

        i.putExtra("img", event.getImages_of_event().get(pos));
                    startActivity(i);
    }
    public void onLeft(View view)
    {
        if (pos != 0)
        {
            pos--;
            viewPager.setCurrentItem(pos);
            count.setText(" " + (pos + 1) + " / " + event.getCountOfImages() + " ");

        }
        if (pos == 0)
        {
            View b = findViewById(R.id.left);
            //b.setVisibility(View.INVISIBLE);
            b.setAlpha(0);
        }
        if (pos == event.getCountOfImages() - 2)
        {
            View b = findViewById(R.id.right);
            //b.setVisibility(View.VISIBLE);
            b.setAlpha((float) 0.7);
        }

    }
    public void onRight(View view)
    {
        if (pos != event.getCountOfImages() - 1)
        {
            pos++;
            viewPager.setCurrentItem(pos);
            count.setText(" " + (pos + 1) + " / " + event.getCountOfImages() + " ");

        }
        if (pos == event.getCountOfImages() - 1)
        {
            View b = findViewById(R.id.right);
            //b.setVisibility(View.INVISIBLE);
            b.setAlpha(0);
        }
        if (pos == 1)
        {
            View b = findViewById(R.id.left);
            //b.setVisibility(View.VISIBLE);
            b.setAlpha((float) 0.7);
        }
    }

}