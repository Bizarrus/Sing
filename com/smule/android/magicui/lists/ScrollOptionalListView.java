/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.view.MotionEvent
 *  android.widget.ListView
 */
package com.smule.android.magicui.lists;

import android.view.MotionEvent;
import android.widget.ListView;

public class ScrollOptionalListView
extends ListView {
    private boolean a;
    private int b;

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean bl = true;
        if (this.a) return super.dispatchTouchEvent(motionEvent);
        int n = motionEvent.getActionMasked() & 255;
        if (n == 0) {
            this.b = this.pointToPosition((int)motionEvent.getX(), (int)motionEvent.getY());
            return super.dispatchTouchEvent(motionEvent);
        }
        if (n == 2) return bl;
        if (n != 1) return super.dispatchTouchEvent(motionEvent);
        if (this.pointToPosition((int)motionEvent.getX(), (int)motionEvent.getY()) == this.b) {
            super.dispatchTouchEvent(motionEvent);
            return super.dispatchTouchEvent(motionEvent);
        }
        this.setPressed(false);
        this.invalidate();
        return true;
    }

    public void setScrollable(boolean bl) {
        this.a = bl;
    }
}

