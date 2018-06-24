package com.smule.singandroid.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.singandroid.C1947R;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class VideoFXTabIndicator_ extends VideoFXTabIndicator implements HasViews, OnViewChangedListener {
    private boolean f22048n = false;
    private final OnViewChangedNotifier f22049o = new OnViewChangedNotifier();

    public VideoFXTabIndicator_(Context context) {
        super(context);
        m23530f();
    }

    public VideoFXTabIndicator_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m23530f();
    }

    public VideoFXTabIndicator_(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m23530f();
    }

    public void onFinishInflate() {
        if (!this.f22048n) {
            this.f22048n = true;
            inflate(getContext(), C1947R.layout.tab_indicator_layout, this);
            this.f22049o.a(this);
        }
        super.onFinishInflate();
    }

    private void m23530f() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f22049o);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m23531a(HasViews hasViews) {
        this.b = hasViews.findViewById(C1947R.id.top_gradient);
        this.c = hasViews.findViewById(C1947R.id.bottom_gradient);
        this.d = (TextView) hasViews.findViewById(C1947R.id.tab_title);
        this.e = (TextView) hasViews.findViewById(C1947R.id.tab_subtitle);
        this.f = (LinearLayout) hasViews.findViewById(C1947R.id.indicator_layout);
    }
}
