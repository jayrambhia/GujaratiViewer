package com.fenchtose.gujarativiewer.presenters;

import android.content.Intent;
import android.os.Bundle;

import com.fenchtose.gujarativiewer.views.DisplayView;

/**
 * Created by Jay Rambhia on 14/07/15.
 */
public class DisplayPresenterImpl implements DisplayPresenter {

    private DisplayView mDisplayView;

    private String extraText;
    private String extraTitle;
    private String extraSubject;

    public DisplayPresenterImpl(DisplayView view) {
        mDisplayView = view;
    }

    @Override
    public void attachView(Bundle sharedExtras) {
        if (sharedExtras == null) {
            return;
        }

        extraText = sharedExtras.getString(Intent.EXTRA_TEXT, null);
        extraTitle = sharedExtras.getString(Intent.EXTRA_TITLE, null);
        extraSubject = sharedExtras.getString(Intent.EXTRA_SUBJECT, null);

        mDisplayView.setContent(extraText);
        mDisplayView.setSubject(extraSubject);
        mDisplayView.setTitleText(extraTitle);
    }

    @Override
    public void detachView() {

    }
}
