package com.smule.singandroid.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.mopub.volley.DefaultRetryPolicy;

public class FullHeightFractionalRelativeLayout extends RelativeLayout {
    private static final String f24756a = FullHeightFractionalRelativeLayout.class.getName();
    private float f24757b;
    private int f24758c;

    public FullHeightFractionalRelativeLayout(Context context) {
        super(context);
    }

    public FullHeightFractionalRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.f24758c = i2;
    }

    public float getYFraction() {
        return this.f24757b;
    }

    public void setYFraction(float f) {
        this.f24757b = f;
        if (this.f24758c > 0) {
            setY(((float) this.f24758c) * (DefaultRetryPolicy.DEFAULT_BACKOFF_MULT - f));
        }
    }
}
