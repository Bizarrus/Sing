/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.support.v4.view.ViewPager
 *  android.util.AttributeSet
 *  android.view.MotionEvent
 */
package com.smule.singandroid;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.smule.android.utils.ReferenceMonitor;

public class CustomViewPager
extends ViewPager {
    private boolean a = true;

    public CustomViewPager(Context context) {
        super(context);
        ReferenceMonitor.a().a((Object)this);
    }

    public CustomViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        ReferenceMonitor.a().a((Object)this);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.a && super.onInterceptTouchEvent(motionEvent)) {
            return true;
        }
        return false;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.a || super.onTouchEvent(motionEvent)) {
            return true;
        }
        return false;
    }

    public void setPagingEnabled(boolean bl) {
        this.a = bl;
    }
}

