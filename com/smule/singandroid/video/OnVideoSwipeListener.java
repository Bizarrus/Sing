package com.smule.singandroid.video;

import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class OnVideoSwipeListener implements OnTouchListener {
    private GestureDetector f25372a;

    private class GestureListener extends SimpleOnGestureListener {
        final /* synthetic */ OnVideoSwipeListener f25371a;

        public boolean onDown(MotionEvent motionEvent) {
            return true;
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            try {
                float x = motionEvent2.getX() - motionEvent.getX();
                if (Math.abs(x) <= Math.abs(motionEvent2.getY() - motionEvent.getY())) {
                    return false;
                }
                if (Math.abs(x) > 100.0f && Math.abs(f) > 100.0f) {
                    if (x > 0.0f) {
                        this.f25371a.m26521a();
                    } else {
                        this.f25371a.m26522b();
                    }
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        return this.f25372a.onTouchEvent(motionEvent);
    }

    public void m26521a() {
    }

    public void m26522b() {
    }
}
