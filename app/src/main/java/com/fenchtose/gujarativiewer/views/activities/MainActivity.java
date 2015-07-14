package com.fenchtose.gujarativiewer.views.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.fenchtose.gujarativiewer.R;
import com.fenchtose.gujarativiewer.controllers.ClipboardService;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "Lohit-Gujarati.ttf");

        TextView textView = (TextView)findViewById(R.id.textview_info);
        textView.setTypeface(typeface);

        TextView headerTextView = (TextView)findViewById(R.id.textview_header);
        headerTextView.setTypeface(typeface);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);


        TextView textViewInfo2 = (TextView)findViewById(R.id.textview_info_2);
        textViewInfo2.setTypeface(typeface);

        Intent serviceIntent = new Intent(this, ClipboardService.class);
        startService(serviceIntent);
    }

}
