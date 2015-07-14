package com.fenchtose.gujarativiewer.views.activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.fenchtose.gujarativiewer.R;
import com.fenchtose.gujarativiewer.presenters.DisplayPresenter;
import com.fenchtose.gujarativiewer.presenters.DisplayPresenterImpl;
import com.fenchtose.gujarativiewer.views.DisplayView;

/**
 * Created by Jay Rambhia on 14/07/15.
 */
public class DisplayActivity extends AppCompatActivity implements DisplayView {

    private TextView contentView;
    private TextView titleView;
    private TextView subjectView;

    private DisplayPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_layout);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "Lohit-Gujarati.ttf");

        TextView headerTextView = (TextView)findViewById(R.id.textview_header);
        headerTextView.setTypeface(typeface);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        contentView = (TextView)findViewById(R.id.content_view);
        titleView = (TextView)findViewById(R.id.title_view);
        subjectView = (TextView)findViewById(R.id.subject_view);

        contentView.setTypeface(typeface);
        titleView.setTypeface(typeface);
        subjectView.setTypeface(typeface);

        mPresenter = new DisplayPresenterImpl(this);

        if (getIntent() != null) {
            mPresenter.attachView(getIntent().getExtras());
        }
    }

    @Override
    public void setContent(CharSequence content) {
        contentView.setText(content);
    }

    @Override
    public CharSequence getContent() {
        return contentView.getText();
    }

    @Override
    public void setTitleText(CharSequence title) {
        if (title == null || title.length() == 0) {
            titleView.setVisibility(View.GONE);
            return;
        }

        titleView.setVisibility(View.VISIBLE);
        titleView.setText(title);
    }

    @Override
    public CharSequence getTitleText() {
        return titleView.getText();
    }

    @Override
    public void setSubject(CharSequence subject) {
        if (subject == null || subject.length() == 0) {
            subjectView.setVisibility(View.GONE);
            return;
        }

        subjectView.setVisibility(View.VISIBLE);
        subjectView.setText(subject);
    }

    @Override
    public CharSequence getSubject() {
        return subjectView.getText();
    }
}
