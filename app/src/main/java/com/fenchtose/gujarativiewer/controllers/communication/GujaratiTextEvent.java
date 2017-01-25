package com.fenchtose.gujarativiewer.controllers.communication;

/**
 * Created by admin on 11/8/15.
 */
public class GujaratiTextEvent {

    public static final int EVENT_SHOW_TEXT  = 1;

    public static final int SOURCE_CLIPBOARD_SERVICE = 11;
    public static final int SOURCE_AUTOGRAB_SERVICE = 12;

    private int event;
    private int source;
    private CharSequence text;

    public GujaratiTextEvent(int event, int source, CharSequence text) {
        this.event = event;
        this.source = source;
        this.text = text;
    }

    public int getEvent() {
        return event;
    }

    public int getSource() {
        return source;
    }

    public CharSequence getText() {
        return text;
    }
}
