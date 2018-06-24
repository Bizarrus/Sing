/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.support.design.widget.TabLayout
 *  android.util.AttributeSet
 *  android.view.MotionEvent
 */
package com.smule.singandroid;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class CustomTabLayout
extends TabLayout {
    private boolean a = true;

    public CustomTabLayout(Context context) {
        super(context);
    }

    public CustomTabLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.a && super.onInterceptTouchEvent(motionEvent)) {
            return true;
        }
        return false;
    }

    public void setTouchEnabled(boolean bl) {
        this.a = bl;
    }
}

