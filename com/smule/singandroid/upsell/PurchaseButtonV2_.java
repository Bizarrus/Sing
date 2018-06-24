package com.smule.singandroid.upsell;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.singandroid.C1947R;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class PurchaseButtonV2_ extends PurchaseButtonV2 implements HasViews, OnViewChangedListener {
    private boolean f24559h = false;
    private final OnViewChangedNotifier f24560i = new OnViewChangedNotifier();

    public PurchaseButtonV2_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m25746b();
    }

    public PurchaseButtonV2_(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m25746b();
    }

    public void onFinishInflate() {
        if (!this.f24559h) {
            this.f24559h = true;
            inflate(getContext(), C1947R.layout.purchase_button_v2, this);
            this.f24560i.a(this);
        }
        super.onFinishInflate();
    }

    private void m25746b() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f24560i);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m25747a(HasViews hasViews) {
        this.d = (LinearLayout) hasViews.findViewById(C1947R.id.purchase_button_outer_layout);
        this.e = (TextView) hasViews.findViewById(C1947R.id.purchase_button_title);
        this.f = (TextView) hasViews.findViewById(C1947R.id.purchase_button_subtitle);
        this.g = (TextView) hasViews.findViewById(C1947R.id.purchase_button_tag_text_view);
    }
}
