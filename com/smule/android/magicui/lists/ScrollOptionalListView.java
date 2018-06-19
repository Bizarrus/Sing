package com.smule.android.magicui.lists;

import android.view.MotionEvent;
import android.widget.ListView;

public class ScrollOptionalListView extends ListView {
    private boolean f16410a;
    private int f16411b;

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (!this.f16410a) {
            int actionMasked = motionEvent.getActionMasked() & 255;
            if (actionMasked == 0) {
                this.f16411b = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
                return super.dispatchTouchEvent(motionEvent);
            } else if (actionMasked == 2) {
                return true;
            } else {
                if (actionMasked == 1) {
                    if (pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY()) == this.f16411b) {
                        super.dispatchTouchEvent(motionEvent);
                    } else {
                        setPressed(false);
                        invalidate();
                        return true;
                    }
                }
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void setScrollable(boolean z) {
        this.f16410a = z;
    }
}
