/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.TypedArray
 *  android.util.AttributeSet
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.smule.android.ui.roundedimageview.RoundedImageView;
import com.smule.singandroid.R;

public class ForcedAspectRatioImageView
extends RoundedImageView {
    private float a;
    private float b;

    public ForcedAspectRatioImageView(Context context) {
        super(context);
        this.a(context, null, 0);
    }

    public ForcedAspectRatioImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a(context, attributeSet, 0);
    }

    public ForcedAspectRatioImageView(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.a(context, attributeSet, n);
    }

    private void a(Context context, AttributeSet attributeSet, int n) {
        context = context.obtainStyledAttributes(attributeSet, R.ForcedAspectRatioImageView, n, 0);
        this.a = context.getInteger(0, 1);
        this.b = context.getInteger(1, 1);
    }

    protected void onMeasure(int n, int n2) {
        super.onMeasure(n, n2);
        if (this.a != 0.0f && this.b != 0.0f) {
            float f = (float)this.getMeasuredWidth() * this.b / this.a;
            this.setMeasuredDimension(this.getMeasuredWidth(), (int)f);
            return;
        }
        this.setMeasuredDimension(this.getMeasuredWidth(), this.getMeasuredHeight());
    }
}

