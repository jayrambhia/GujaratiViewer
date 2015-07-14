package com.fenchtose.gujarativiewer.presenters;

import android.os.Bundle;

/**
 * Created by Jay Rambhia on 14/07/15.
 */
public interface DisplayPresenter {

    void attachView(Bundle sharedExtras);
    void detachView();
}
