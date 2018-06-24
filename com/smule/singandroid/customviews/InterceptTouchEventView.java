package com.smule.singandroid.customviews;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class InterceptTouchEventView extends View {
    public InterceptTouchEventView(Context context) {
        super(context);
    }

    public InterceptTouchEventView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public InterceptTouchEventView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @TargetApi(21)
    public InterceptTouchEventView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return true;
    }
}
