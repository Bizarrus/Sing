/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.graphics.Point
 *  android.view.View
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnPreDrawListener
 */
package com.smule.singandroid.customviews;

import android.graphics.Point;
import android.view.View;
import android.view.ViewTreeObserver;

public class WindowLocationBroadcastingView
extends View {
    private static final String b = WindowLocationBroadcastingView.class.getName();
    protected LocationChangeListener a;

    private void setLocationChangeListener(LocationChangeListener locationChangeListener) {
        synchronized (this) {
            this.a = locationChangeListener;
            return;
        }
    }

    public static interface LocationChangeListener {
        public void a(View var1, Point var2);
    }

}

