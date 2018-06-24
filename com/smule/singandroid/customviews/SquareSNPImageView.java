/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.util.AttributeSet;
import com.smule.android.ui.SNPImageView;

public class SquareSNPImageView
extends SNPImageView {
    public SquareSNPImageView(Context context) {
        super(context);
    }

    public SquareSNPImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SquareSNPImageView(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
    }

    protected void onMeasure(int n, int n2) {
        super.onMeasure(n, n2);
        n = Math.min(this.getMeasuredWidth(), this.getMeasuredHeight());
        this.setMeasuredDimension(n, n);
    }
}

