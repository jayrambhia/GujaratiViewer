package com.fenchtose.gujarativiewer.controllers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Jay Rambhia on 14/07/15.
 */
public class BootBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "BootBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onBoot received");

        Intent clipboardServiceIntent = new Intent(context, ClipboardService.class);
        context.startService(clipboardServiceIntent);

        Intent windowRenderingServiceIntent = new Intent(context, WindowRenderingService.class);
        context.startService(windowRenderingServiceIntent);
    }
}
