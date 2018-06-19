package com.smule.singandroid;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.smule.android.utils.ReferenceMonitor;

public class CustomViewPager extends ViewPager {
    private boolean f18510a = true;

    public CustomViewPager(Context context) {
        super(context);
        ReferenceMonitor.a().a(this);
    }

    public CustomViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        ReferenceMonitor.a().a(this);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.f18510a) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.f18510a) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return false;
    }

    public void setPagingEnabled(boolean z) {
        this.f18510a = z;
    }
}
