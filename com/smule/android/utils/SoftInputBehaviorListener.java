package com.smule.android.utils;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

public class SoftInputBehaviorListener implements OnGlobalLayoutListener {
    private static final String f17844a = SoftInputBehaviorListener.class.getSimpleName();
    private final View f17845b;
    private final OnSoftInputBehaveListener f17846c;
    private int f17847d;
    private final Rect f17848e = new Rect();

    public interface OnSoftInputBehaveListener {
        void mo6810a();

        void mo6811b();
    }

    public SoftInputBehaviorListener(@NonNull View view, @NonNull OnSoftInputBehaveListener onSoftInputBehaveListener) {
        this.f17845b = view;
        this.f17846c = onSoftInputBehaveListener;
    }

    public void onGlobalLayout() {
        this.f17845b.getWindowVisibleDisplayFrame(this.f17848e);
        int height = this.f17845b.getRootView().getHeight();
        int i = height - this.f17848e.bottom;
        if (i != this.f17847d) {
            if (((double) i) > ((double) height) * 0.15d) {
                this.f17846c.mo6810a();
            } else if (i == 0) {
                this.f17846c.mo6811b();
            }
        }
        this.f17847d = i;
    }
}
