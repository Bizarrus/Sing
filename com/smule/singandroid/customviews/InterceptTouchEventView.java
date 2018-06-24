/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.annotation.TargetApi
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.view.MotionEvent
 *  android.view.View
 */
package com.smule.singandroid.customviews;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class InterceptTouchEventView
extends View {
    public InterceptTouchEventView(Context context) {
        super(context);
    }

    public InterceptTouchEventView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public InterceptTouchEventView(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
    }

    @TargetApi(value=21)
    public InterceptTouchEventView(Context context, AttributeSet attributeSet, int n, int n2) {
        super(context, attributeSet, n, n2);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return true;
    }
}

