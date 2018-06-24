package com.smule.singandroid.customviews;

import android.widget.VideoView;
import com.smule.android.logging.Log;

public class ScaleVideoView extends VideoView {
    private static final String f21951a = ScaleVideoView.class.getName();
    private int f21952b;
    private int f21953c;

    public enum DisplayMode {
        ORIGINAL,
        FULL_SCREEN,
        ZOOM
    }

    protected void onMeasure(int i, int i2) {
        int defaultSize = getDefaultSize(this.f21952b, i);
        int defaultSize2 = getDefaultSize(this.f21953c, i2);
        DisplayMode displayMode = DisplayMode.ZOOM;
        Log.e(f21951a, "mVideoWidth:" + this.f21952b + " mVideoHeight:" + this.f21953c + " width:" + defaultSize + " height:" + defaultSize2);
        if (displayMode == DisplayMode.ORIGINAL) {
            if (this.f21952b > 0 && this.f21953c > 0) {
                if (this.f21952b * defaultSize2 > this.f21953c * defaultSize) {
                    defaultSize2 = (this.f21953c * defaultSize) / this.f21952b;
                } else if (this.f21952b * defaultSize2 < this.f21953c * defaultSize) {
                    defaultSize = (this.f21952b * defaultSize2) / this.f21953c;
                }
            }
        } else if (displayMode != DisplayMode.FULL_SCREEN && displayMode == DisplayMode.ZOOM && this.f21952b > 0 && this.f21953c > 0 && this.f21952b < defaultSize) {
            defaultSize2 = (this.f21953c * defaultSize) / this.f21952b;
        }
        Log.e(f21951a, "new dimensions: width:" + defaultSize + " height:" + defaultSize2);
        setMeasuredDimension(defaultSize, defaultSize2);
    }
}
