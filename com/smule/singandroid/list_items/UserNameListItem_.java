package com.smule.singandroid.list_items;

import android.widget.TextView;
import com.smule.singandroid.C1947R;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class UserNameListItem_ extends UserNameListItem implements HasViews, OnViewChangedListener {
    private boolean f23316c;
    private final OnViewChangedNotifier f23317d;

    public void onFinishInflate() {
        if (!this.f23316c) {
            this.f23316c = true;
            inflate(getContext(), C1947R.layout.user_name_list_item, this);
            this.f23317d.a(this);
        }
        super.onFinishInflate();
    }

    public void m24519a(HasViews hasViews) {
        this.a = (TextView) hasViews.findViewById(C1947R.id.user_name_text_view);
    }
}
