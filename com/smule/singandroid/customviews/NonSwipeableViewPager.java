package com.smule.singandroid.customviews;

import android.support.v4.view.ViewPager;
import android.view.MotionEvent;

public class NonSwipeableViewPager extends ViewPager {
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return false;
    }
}
