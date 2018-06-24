/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.Canvas
 *  android.graphics.Paint
 *  android.graphics.Rect
 *  android.util.AttributeSet
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import com.smule.android.ui.SNPImageView;

public class SNPProgressImageView
extends SNPImageView {
    private int e;
    private int f;
    private final Paint g = new Paint();
    private final Rect h = new Rect();

    public SNPProgressImageView(Context context) {
        super(context);
    }

    public SNPProgressImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SNPProgressImageView(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
    }

    public int getMax() {
        return this.e;
    }

    public int getProgress() {
        return this.f;
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float f = Math.min((float)this.f / (float)this.e, 1.0f);
        canvas.getClipBounds(this.h);
        this.g.setColor(-1);
        this.g.setAlpha(224);
        float f2 = this.h.height();
        canvas.drawRect((float)this.h.left, (float)this.h.top, (float)this.h.right, (float)this.h.top + (1.0f - f) * f2, this.g);
    }

    public void setMax(int n) {
        this.e = n;
        this.invalidate();
    }

    public void setProgress(int n) {
        this.f = n;
        this.invalidate();
    }
}

