package com.fenchtose.gujarativiewer.utils;

import android.content.ClipData;
import android.support.annotation.NonNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by admin on 11/8/15.
 */
public class Finder {

    private Pattern mGujaratiPattern;

    public Finder() {
        mGujaratiPattern = Pattern.compile(Constants.GUJARATI_REGEX, Pattern.DOTALL | Pattern.MULTILINE);
    }

    public boolean hasGujaratiText(@NonNull CharSequence text) {
        Matcher matcher = mGujaratiPattern.matcher(text);
        return matcher.find();
    }
}
