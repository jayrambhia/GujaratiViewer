package com.fenchtose.gujarativiewer.views;

/**
 * Created by Jay Rambhia on 14/07/15.
 */
public interface DisplayView {
    void setContent(CharSequence content);
    CharSequence getContent();

    void setTitleText(CharSequence title);
    CharSequence getTitleText();

    void setSubject(CharSequence subject);
    CharSequence getSubject();
}
