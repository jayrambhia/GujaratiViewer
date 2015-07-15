package com.fenchtose.gujarativiewer.controllers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.fenchtose.gujarativiewer.R;
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

    private Handler mHandler;
    private final int DISMISS_TIMEOUT = 600;

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

        mHandler = new Handler();
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

    private void showFloatingWindow(final CharSequence content) {
        if (mFloatingWindow == null) {
            mFloatingWindow = new FloatingWindowView(this);
            mFloatingWindow.setCallback(new FloatingWindowView.FloatingWindowCallback() {
                @Override
                public void onCancelClicked(FloatingWindowView window, View v) {
                    dismissView(window);
                }

                @Override
                public void onBackPressed(FloatingWindowView window) {
                    dismissView(window);
                }

                @Override
                public void onShareClicked(FloatingWindowView window, View v) {
                    dismissView(window);
                    shareContent(content);
                }

                @Override
                public void onFavClicked(FloatingWindowView window, View v) {
                    dismissView(window);
                    shareApp();
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

    private void dismissView(final View view) {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (view.getParent() != null) {
                    mWindowManager.removeViewImmediate(view);
                }
            }
        }, DISMISS_TIMEOUT);
    }

    private void shareContent(CharSequence content) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, content);
        sendIntent.setType("text/plain");

        Intent chooserIntent = Intent.createChooser(sendIntent, "Share Text Via..");
        chooserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(chooserIntent);
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
