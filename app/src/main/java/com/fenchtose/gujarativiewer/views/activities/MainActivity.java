package com.fenchtose.gujarativiewer.views.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_share) {
            shareApp();
        }

        return super.onOptionsItemSelected(item);
    }

    private void shareApp() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.share_app_content));
        sendIntent.setType("text/plain");

        Intent chooserIntent = Intent.createChooser(sendIntent, "Share App Via..");
        chooserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(chooserIntent);
    }
}
