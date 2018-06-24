package com.smule.singandroid.customviews;

import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.singandroid.C1947R;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class CustomTabIndicator_ extends CustomTabIndicator implements HasViews, OnViewChangedListener {
    private boolean f21527m;
    private final OnViewChangedNotifier f21528n;

    public void onFinishInflate() {
        if (!this.f21527m) {
            this.f21527m = true;
            inflate(getContext(), C1947R.layout.tab_indicator_layout, this);
            this.f21528n.a(this);
        }
        super.onFinishInflate();
    }

    public void m23168a(HasViews hasViews) {
        this.b = hasViews.findViewById(C1947R.id.top_gradient);
        this.c = hasViews.findViewById(C1947R.id.bottom_gradient);
        this.d = (TextView) hasViews.findViewById(C1947R.id.tab_title);
        this.e = (TextView) hasViews.findViewById(C1947R.id.tab_subtitle);
        this.f = (LinearLayout) hasViews.findViewById(C1947R.id.indicator_layout);
    }
}
