/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.widget.VideoView
 */
package com.smule.singandroid.customviews;

import android.widget.VideoView;
import com.smule.android.logging.Log;

public class ScaleVideoView
extends VideoView {
    private static final String a = ScaleVideoView.class.getName();
    private int b;
    private int c;

    /*
     * Enabled aggressive block sorting
     */
    protected void onMeasure(int n, int n2) {
        int n3 = ScaleVideoView.getDefaultSize((int)this.b, (int)n);
        int n4 = ScaleVideoView.getDefaultSize((int)this.c, (int)n2);
        DisplayMode displayMode = DisplayMode.c;
        Log.e(a, "mVideoWidth:" + this.b + " mVideoHeight:" + this.c + " width:" + n3 + " height:" + n4);
        if (displayMode == DisplayMode.a) {
            n = n4;
            n2 = n3;
            if (this.b > 0) {
                n = n4;
                n2 = n3;
                if (this.c > 0) {
                    if (this.b * n4 > this.c * n3) {
                        n = this.c * n3 / this.b;
                        n2 = n3;
                    } else {
                        n = n4;
                        n2 = n3;
                        if (this.b * n4 < this.c * n3) {
                            n2 = this.b * n4 / this.c;
                            n = n4;
                        }
                    }
                }
            }
        } else {
            n = n4;
            n2 = n3;
            if (displayMode != DisplayMode.b) {
                n = n4;
                n2 = n3;
                if (displayMode == DisplayMode.c) {
                    n = n4;
                    n2 = n3;
                    if (this.b > 0) {
                        n = n4;
                        n2 = n3;
                        if (this.c > 0) {
                            n = n4;
                            n2 = n3;
                            if (this.b < n3) {
                                n = this.c * n3 / this.b;
                                n2 = n3;
                            }
                        }
                    }
                }
            }
        }
        Log.e(a, "new dimensions: width:" + n2 + " height:" + n);
        this.setMeasuredDimension(n2, n);
    }

    public static enum DisplayMode {
        a,
        b,
        c;
        

        private DisplayMode() {
        }
    }

}

