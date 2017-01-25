package com.fenchtose.gujarativiewer.controllers;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.text.TextUtils;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

import com.fenchtose.gujarativiewer.controllers.communication.GujaratiTextEvent;
import com.fenchtose.gujarativiewer.utils.Finder;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by admin on 11/8/15.
 */
public class AutoGrabService extends AccessibilityService {
    private static final String TAG = "AutoGrabService";

    private Finder mFinder;

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (event == null) {
            Log.e(TAG, "null event passed");
            return;
        }

        String eventName = AccessibilityEvent.eventTypeToString(event.getEventType());

        List<CharSequence> content = event.getText();
        showIfGujaratiText(content);


        Log.i(TAG, "event name: " + eventName);
        Log.i(TAG, "Event: " + event.toString());
    }

    @Override
    public void onInterrupt() {
        Log.v(TAG, "onInterrupt");
    }

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        Log.i(TAG, "onServiceConnected");

        AccessibilityServiceInfo mInfo = new AccessibilityServiceInfo();
        mInfo.eventTypes = AccessibilityEvent.TYPE_VIEW_CLICKED | AccessibilityEvent.TYPE_VIEW_SCROLLED;
        mInfo.feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC;
        mInfo.flags = AccessibilityServiceInfo.DEFAULT;

        this.setServiceInfo(mInfo);
    }

    private void showIfGujaratiText(List<CharSequence> content) {
        CharSequence text = "";
        for (CharSequence data : content) {
            text = TextUtils.concat(text, data, "\n");
        }

        if (mFinder == null) {
            mFinder = new Finder();
        }

        if (mFinder.hasGujaratiText(text)) {
            showFloatingWindow(text);
        }
    }

    private void showFloatingWindow(CharSequence text) {
        EventBus.getDefault().post(new GujaratiTextEvent(GujaratiTextEvent.EVENT_SHOW_TEXT,
                GujaratiTextEvent.SOURCE_AUTOGRAB_SERVICE, text));
    }
}
