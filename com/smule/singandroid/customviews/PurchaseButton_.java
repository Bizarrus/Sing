package com.smule.singandroid.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.singandroid.C1947R;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class PurchaseButton_ extends PurchaseButton implements HasViews, OnViewChangedListener {
    private boolean f21906g = false;
    private final OnViewChangedNotifier f21907h = new OnViewChangedNotifier();

    public PurchaseButton_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m23463b();
    }

    public PurchaseButton_(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m23463b();
    }

    public void onFinishInflate() {
        if (!this.f21906g) {
            this.f21906g = true;
            inflate(getContext(), C1947R.layout.purchase_button, this);
            this.f21907h.a(this);
        }
        super.onFinishInflate();
    }

    private void m23463b() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f21907h);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m23464a(HasViews hasViews) {
        this.c = (LinearLayout) hasViews.findViewById(C1947R.id.purchase_button_inner_button);
        this.d = (TextView) hasViews.findViewById(C1947R.id.purchase_button_inner_button_title);
        this.e = (TextView) hasViews.findViewById(C1947R.id.purchase_button_inner_button_subtitle);
        this.f = (TextView) hasViews.findViewById(C1947R.id.purchase_button_tag_text_view);
    }
}
