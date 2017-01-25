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
import com.fenchtose.gujarativiewer.controllers.communication.GujaratiTextEvent;
import com.fenchtose.gujarativiewer.utils.Constants;
import com.fenchtose.gujarativiewer.utils.Finder;
import com.fenchtose.gujarativiewer.views.widgets.FloatingWindowView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.greenrobot.event.EventBus;

/**
 * Created by Jay Rambhia on 13/07/15.
 */
public class ClipboardService extends Service implements ClipboardManager.OnPrimaryClipChangedListener {

    private static final String TAG = "ClipboardService";
    private ClipboardManager mClipboardManager;

    private CharSequence mClipText;
    private Finder mFinder;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

        mClipboardManager = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
        mClipboardManager.addPrimaryClipChangedListener(this);
        mFinder = new Finder();

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

            if (mFinder.hasGujaratiText(mClipText)) {
                showFloatingWindow(mClipText);
            }

            String mClipString = mClipText.toString();
            Log.i(TAG, "clip string: " + mClipString);


        } else {
            Log.e(TAG, "primary clip not found");
        }
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

    private void showFloatingWindow(CharSequence text) {
        EventBus.getDefault().post(new GujaratiTextEvent(GujaratiTextEvent.EVENT_SHOW_TEXT,
                GujaratiTextEvent.SOURCE_CLIPBOARD_SERVICE, text));
    }
}
