package com.fenchtose.gujarativiewer.controllers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.fenchtose.gujarativiewer.utils.Constants;
import com.fenchtose.gujarativiewer.views.widgets.FloatingWindowView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Jay Rambhia on 13/07/15.
 */
public class ClipboardService extends Service implements ClipboardManager.OnPrimaryClipChangedListener {

    private static final String TAG = "ClipboardService";
    private ClipboardManager mClipboardManager;

    private WindowManager mWindowManager;

    private CharSequence mClipText;
    private Pattern mGujaratiPattern;

    private FloatingWindowView mFloatingWindow;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        mGujaratiPattern = Pattern.compile(Constants.GUJARATI_REGEX, Pattern.DOTALL | Pattern.MULTILINE);

        mWindowManager = (WindowManager)getSystemService(WINDOW_SERVICE);

        mClipboardManager = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
        mClipboardManager.addPrimaryClipChangedListener(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if (mClipboardManager != null) {
            mClipboardManager.removePrimaryClipChangedListener(this);
        }
    }

    @Override
    public void onPrimaryClipChanged() {
        ClipData primaryClip = mClipboardManager.getPrimaryClip();
        if (primaryClip != null) {
            int count = primaryClip.getItemCount();

            mClipText = "";
            for (int i=0; i<count; i++) {
                ClipData.Item mItem = primaryClip.getItemAt(i);
                mClipText = TextUtils.concat(mClipText, mItem.getText());
            }

            String mClipString = mClipText.toString();
            Log.i(TAG, "clip string: " + mClipString);

            Matcher matcher = mGujaratiPattern.matcher(mClipText);
            if (matcher.find()) {
                Log.i(TAG, "Gujarati text found");
                showFloatingWindow(mClipText);
            } else {
                Log.e(TAG, "Gujarati text not found");
            }
        } else {
            Log.e(TAG, "primary clip not found");
        }
    }

    private void showFloatingWindow(CharSequence content) {
        if (mFloatingWindow == null) {
            mFloatingWindow = new FloatingWindowView(this);
            mFloatingWindow.setCallback(new FloatingWindowView.FloatingWindowCallback() {
                @Override
                public void onCancelClicked(FloatingWindowView window, View v) {
                    mWindowManager.removeViewImmediate(window);
                }

                @Override
                public void onBackPressed(FloatingWindowView window) {
                    mWindowManager.removeViewImmediate(window);
                }

            });
        }

        if (mFloatingWindow.getParent() != null) {
            mFloatingWindow.setContent(content);
            return;
        }

        DisplayMetrics dm = new DisplayMetrics();
        mWindowManager.getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        int xpos = 60;
        int ypos = 120;

        WindowManager.LayoutParams mParams = new WindowManager.LayoutParams(width - 2 * xpos,
                height - 2 * ypos, xpos, ypos,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,
                PixelFormat.TRANSLUCENT);

        mParams.gravity = Gravity.TOP | Gravity.LEFT;

        mFloatingWindow.setContent(content);
        mWindowManager.addView(mFloatingWindow, mParams);
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Intent restartService = new Intent(getApplicationContext(),
                this.getClass());
        restartService.setPackage(getPackageName());
        PendingIntent restartServicePI = PendingIntent.getService(
                getApplicationContext(), 1, restartService,
                PendingIntent.FLAG_ONE_SHOT);
        AlarmManager alarmService = (AlarmManager)getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        alarmService.set(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + 1000, restartServicePI);
    }
}
