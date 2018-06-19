package com.smule.android.ui;

import android.graphics.Canvas;
import android.view.MotionEvent;
import android.widget.SeekBar;
import com.mopub.mobileads.resource.DrawableConstants.RadialCountdown;
import com.smule.android.utils.NotificationCenter;

public class VerticalSeekBar extends SeekBar {
    private static final String f17705a = VerticalSeekBar.class.getName();

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i2, i, i4, i3);
    }

    protected synchronized void onMeasure(int i, int i2) {
        super.onMeasure(i2, i);
        setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
    }

    protected void onDraw(Canvas canvas) {
        canvas.rotate(RadialCountdown.START_ANGLE);
        canvas.translate((float) (-getHeight()), 0.0f);
        super.onDraw(canvas);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isEnabled()) {
            return false;
        }
        int max = Math.max(0, Math.min(getMax() - ((int) ((((float) getMax()) * motionEvent.getY()) / ((float) getHeight()))), getMax()));
        switch (motionEvent.getAction()) {
            case 0:
            case 2:
                setProgress(max);
                onSizeChanged(getWidth(), getHeight(), 0, 0);
                NotificationCenter.m19011a().m19012a("VERTICAL_SEEK_BAR_MOVE_EVENT", new Integer(max));
                break;
            case 1:
                setProgress(max);
                onSizeChanged(getWidth(), getHeight(), 0, 0);
                NotificationCenter.m19011a().m19012a("VERTICAL_SEEK_BAR_TOUCH_UP_EVENT", new Integer(max));
                break;
        }
        return true;
    }
}
