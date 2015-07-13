package com.fenchtose.gujarativiewer.controllers;

import android.app.Service;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.fenchtose.gujarativiewer.utils.Constants;
import com.fenchtose.gujarativiewer.views.FloatingWindowView;

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
            });
        }

        if (mFloatingWindow.getParent() != null) {
            mFloatingWindow.setContent(content);
            return;
        }

        WindowManager.LayoutParams mParams = new WindowManager.LayoutParams(400, 300, 60, 200,
                WindowManager.LayoutParams.TYPE_PHONE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        mParams.gravity = Gravity.TOP | Gravity.LEFT;

        mFloatingWindow.setContent(content);
        mWindowManager.addView(mFloatingWindow, mParams);
    }
}
