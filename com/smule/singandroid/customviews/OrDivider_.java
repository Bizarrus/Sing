package com.smule.singandroid.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import com.smule.singandroid.C1947R;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class OrDivider_ extends OrDivider implements HasViews, OnViewChangedListener {
    private boolean f21682e = false;
    private final OnViewChangedNotifier f21683f = new OnViewChangedNotifier();

    public OrDivider_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m23309b();
    }

    public OrDivider_(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m23309b();
    }

    public void onFinishInflate() {
        if (!this.f21682e) {
            this.f21682e = true;
            inflate(getContext(), C1947R.layout.or_divider, this);
            this.f21683f.a(this);
        }
        super.onFinishInflate();
    }

    private void m23309b() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f21683f);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m23310a(HasViews hasViews) {
        this.b = hasViews.findViewById(C1947R.id.left_line);
        this.c = (TextView) hasViews.findViewById(C1947R.id.or_divider_text);
        this.d = hasViews.findViewById(C1947R.id.right_line);
    }
}
