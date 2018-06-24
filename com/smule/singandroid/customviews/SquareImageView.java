/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.widget.ImageView
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class SquareImageView
extends ImageView {
    public SquareImageView(Context context) {
        super(context);
    }

    public SquareImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SquareImageView(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
    }

    protected void onMeasure(int n, int n2) {
        super.onMeasure(n, n2);
        this.setMeasuredDimension(this.getMeasuredWidth(), this.getMeasuredWidth());
    }
}

