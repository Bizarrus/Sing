/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.graphics.drawable.Drawable
 *  android.view.View
 *  android.view.View$MeasureSpec
 *  android.widget.ImageView
 */
package com.smule.android.ui;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

public class ResizableImageView
extends ImageView {
    protected void onMeasure(int n, int n2) {
        Drawable drawable2 = this.getDrawable();
        if (drawable2 != null) {
            n = View.MeasureSpec.getSize((int)n);
            this.setMeasuredDimension(n, (int)Math.ceil((float)drawable2.getIntrinsicHeight() / (float)drawable2.getIntrinsicWidth() * (float)n));
            return;
        }
        super.onMeasure(n, n2);
    }
}

