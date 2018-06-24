/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.graphics.Canvas
 *  android.view.MotionEvent
 *  android.widget.SeekBar
 */
package com.smule.android.ui;

import android.graphics.Canvas;
import android.view.MotionEvent;
import android.widget.SeekBar;
import com.smule.android.utils.NotificationCenter;

public class VerticalSeekBar
extends SeekBar {
    private static final String a = VerticalSeekBar.class.getName();

    protected void onDraw(Canvas canvas) {
        canvas.rotate(-90.0f);
        canvas.translate((float)(- this.getHeight()), 0.0f);
        super.onDraw(canvas);
    }

    protected void onMeasure(int n, int n2) {
        synchronized (this) {
            super.onMeasure(n2, n);
            this.setMeasuredDimension(this.getMeasuredHeight(), this.getMeasuredWidth());
            return;
        }
    }

    protected void onSizeChanged(int n, int n2, int n3, int n4) {
        super.onSizeChanged(n2, n, n4, n3);
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.isEnabled()) {
            return false;
        }
        int n = Math.max(0, Math.min(this.getMax() - (int)((float)this.getMax() * motionEvent.getY() / (float)this.getHeight()), this.getMax()));
        switch (motionEvent.getAction()) {
            case 0: 
            case 2: {
                this.setProgress(n);
                this.onSizeChanged(this.getWidth(), this.getHeight(), 0, 0);
                NotificationCenter.a().a("VERTICAL_SEEK_BAR_MOVE_EVENT", (Object)new Integer(n));
            }
            default: {
                return true;
            }
            case 1: 
        }
        this.setProgress(n);
        this.onSizeChanged(this.getWidth(), this.getHeight(), 0, 0);
        NotificationCenter.a().a("VERTICAL_SEEK_BAR_TOUCH_UP_EVENT", (Object)new Integer(n));
        return true;
    }
}

