package com.fenchtose.gujarativiewer.views;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fenchtose.gujarativiewer.R;
import com.fenchtose.gujarativiewer.utils.Constants;

/**
 * Created by Jay Rambhia on 13/07/15.
 */
public class FloatingWindowView extends LinearLayout implements View.OnClickListener {

    private TextView contentView;
    private FloatingWindowCallback mCallback;

    public FloatingWindowView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.floating_window_layout, this, true);
        Typeface gujaratiTypeface = Typeface.createFromAsset(context.getAssets(),
                Constants.GUJARATI_FONT_NAME);
        contentView = (TextView)findViewById(R.id.content_view);
        contentView.setTypeface(gujaratiTypeface);

        TextView headerTextView = (TextView)findViewById(R.id.textview_header);
        headerTextView.setTypeface(gujaratiTypeface);

        ImageView cancelView = (ImageView)findViewById(R.id.imageview_close);
        cancelView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (mCallback != null) {
            mCallback.onCancelClicked(this, v);
        }
    }

    public FloatingWindowCallback getCallback() {
        return mCallback;
    }

    public void setCallback(FloatingWindowCallback mCallback) {
        this.mCallback = mCallback;
    }

    public CharSequence getContent() {
        return contentView.getText();
    }

    public void setContent(CharSequence content) {
        contentView.setText(content);
    }

    public interface FloatingWindowCallback {
        void onCancelClicked(FloatingWindowView window, View v);
    }
}
