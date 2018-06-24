/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.View$MeasureSpec
 *  android.widget.ImageView
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

public class NativeAdsMainImageView
extends ImageView {
    public NativeAdsMainImageView(Context context) {
        super(context);
    }

    public NativeAdsMainImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public NativeAdsMainImageView(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
    }

    protected void onMeasure(int n, int n2) {
        n = View.MeasureSpec.getSize((int)n);
        this.setMeasuredDimension(n, (int)((float)n / 1.91f));
    }
}

