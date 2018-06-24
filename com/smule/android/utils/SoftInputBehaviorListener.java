/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.graphics.Rect
 *  android.support.annotation.NonNull
 *  android.view.View
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnGlobalLayoutListener
 */
package com.smule.android.utils;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewTreeObserver;

public class SoftInputBehaviorListener
implements ViewTreeObserver.OnGlobalLayoutListener {
    private static final String a = SoftInputBehaviorListener.class.getSimpleName();
    private final View b;
    private final OnSoftInputBehaveListener c;
    private int d;
    private final Rect e = new Rect();

    public SoftInputBehaviorListener(@NonNull View view, @NonNull OnSoftInputBehaveListener onSoftInputBehaveListener) {
        this.b = view;
        this.c = onSoftInputBehaveListener;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onGlobalLayout() {
        this.b.getWindowVisibleDisplayFrame(this.e);
        int n = this.b.getRootView().getHeight();
        int n2 = n - this.e.bottom;
        if (n2 != this.d) {
            if ((double)n2 > (double)n * 0.15) {
                this.c.a();
            } else if (n2 == 0) {
                this.c.b();
            }
        }
        this.d = n2;
    }

    public static interface OnSoftInputBehaveListener {
        public void a();

        public void b();
    }

}

