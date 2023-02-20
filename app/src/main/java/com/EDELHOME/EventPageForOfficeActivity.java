package com.EDELHOME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.net.Uri;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventPageForOfficeActivity extends AppCompatActivity {
    Event event;
    int pos = 0;
    ViewPager viewPager;
    TextView count;
    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_page_for_office);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        Bundle arguments = getIntent().getExtras();
        count = findViewById(R.id.count);

        if(arguments!=null)
        {
            event = (Event) arguments.getSerializable(Event.class.getSimpleName());

            TextView name_of_event = findViewById(R.id.name_of_event);
            TextView name_of_client = findViewById(R.id.name_of_client);
            TextView phone = findViewById(R.id.phone);
            TextView address = findViewById(R.id.address);
            TextView date = findViewById(R.id.date);
            TextView time = findViewById(R.id.time);
            name_of_client.setText(event.getName_of_client());
            name_of_event.setText(event.getName_of_event());
            phone.setText(event.getPhone_number());
            address.setText(event.getAddress());
            date.setText(event.getDate_of_eventInString());
            time.setText(event.getTimeInString());

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
                    count.setText(" " + (pos + 1) + " / " + event.getCount_of_images() + " ");
                    if (pos == 0)
                    {
                        View b = findViewById(R.id.left);
                        //b.setVisibility(View.INVISIBLE);
                        b.setAlpha(0);
                    }
                    if (pos == event.getCount_of_images() - 2)
                    {
                        View b = findViewById(R.id.right);
                        //b.setVisibility(View.VISIBLE);
                        b.setAlpha((float) 0.7);
                    }
                    if (pos == event.getCount_of_images() - 1)
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
        if(event.getCount_of_images() == 1)
        {
            View r = findViewById(R.id.right);
            //r.setVisibility(View.INVISIBLE);
            r.setAlpha(0);
        }
        count.setText(" " + (pos + 1) + " / " + event.getCount_of_images() + " ");
    }
    public void onImg(View view)
    {
        if(event.getImages_of_event() != null)
        {
            Intent i = new Intent(getApplicationContext(), EventFullImageActivity.class);

            i.putExtra("img", event.getImages_of_event().get(pos));
            startActivity(i);
        }
    }
    public void onLeft(View view)
    {
        if (pos != 0)
        {
            pos--;
            viewPager.setCurrentItem(pos);
            count.setText(" " + (pos + 1) + " / " + event.getCount_of_images() + " ");

        }
        if (pos == 0)
        {
            View b = findViewById(R.id.left);
            //b.setVisibility(View.INVISIBLE);
            b.setAlpha(0);
        }
        if (pos == event.getCount_of_images() - 2)
        {
            View b = findViewById(R.id.right);
            //b.setVisibility(View.VISIBLE);
            b.setAlpha((float) 0.7);
        }

    }
    public void onRight(View view)
    {
        if (pos != event.getCount_of_images() - 1)
        {
            pos++;
            viewPager.setCurrentItem(pos);
            count.setText(" " + (pos + 1) + " / " + event.getCount_of_images() + " ");

        }
        if (pos == event.getCount_of_images() - 1)
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
    public void onNumber(View view)
    {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + event.getPhone_number()));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    public void onCopyPhone(View view)
    {
        ClipboardManager clipboard = (ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("", event.getPhone_number());
        clipboard.setPrimaryClip(clip);
    }
    public void onCopyAddress(View view)
    {
        ClipboardManager clipboard = (ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("", event.getAddress());
        clipboard.setPrimaryClip(clip);
    }
    public void onAddPhoto(View view)
    {
        FragmentManager manager = getSupportFragmentManager();
        AddImageDialogFragment myDialogFragment = new AddImageDialogFragment();
        myDialogFragment.show(manager, "myDialog");
    }
    public void onRedactor(View view)
    {
        Intent intent = new Intent(this, EventRedactorActivity.class);
        intent.putExtra(Event.class.getSimpleName(), event);
        startActivity(intent);
    }
}