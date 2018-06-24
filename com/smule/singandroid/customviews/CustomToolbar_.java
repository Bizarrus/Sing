package com.smule.singandroid.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.textviews.AutoResizeTextView;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class CustomToolbar_ extends CustomToolbar implements HasViews, OnViewChangedListener {
    private boolean f21541q = false;
    private final OnViewChangedNotifier f21542r = new OnViewChangedNotifier();

    public CustomToolbar_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m23173d();
    }

    public CustomToolbar_(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m23173d();
    }

    public void onFinishInflate() {
        if (!this.f21541q) {
            this.f21541q = true;
            inflate(getContext(), C1947R.layout.custom_toolbar, this);
            this.f21542r.a(this);
        }
        super.onFinishInflate();
    }

    private void m23173d() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f21542r);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m23174a(HasViews hasViews) {
        this.g = hasViews.findViewById(C1947R.id.root_view);
        this.h = (ImageView) hasViews.findViewById(C1947R.id.left_button);
        this.i = (TextView) hasViews.findViewById(C1947R.id.title_text_view);
        this.j = (TextView) hasViews.findViewById(C1947R.id.sub_title_text_view);
        this.k = (TextView) hasViews.findViewById(C1947R.id.title_centered_text_view);
        this.l = (ImageView) hasViews.findViewById(C1947R.id.pre_search_left_button);
        this.m = (TextView) hasViews.findViewById(C1947R.id.pre_search_title_text_view);
        this.b = (ImageView) hasViews.findViewById(C1947R.id.right_button);
        this.c = (AutoResizeTextView) hasViews.findViewById(C1947R.id.right_text_button);
        this.d = (ImageView) hasViews.findViewById(C1947R.id.mVipOnlyImageView);
        m23169a();
    }
}
