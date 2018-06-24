/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.view.TextureView
 *  android.view.View
 *  android.view.View$MeasureSpec
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.TextureView;
import android.view.View;

public class SquareTextureView
extends TextureView {
    private int a;

    public SquareTextureView(Context context) {
        super(context);
    }

    public SquareTextureView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SquareTextureView(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected void onMeasure(int n, int n2) {
        synchronized (this) {
            n = this.a == 0 ? View.MeasureSpec.getSize((int)n) : this.a;
            this.setMeasuredDimension(n, n);
            return;
        }
    }

    public void setWidth(int n) {
        this.a = n;
    }
}

