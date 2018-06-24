/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.graphics.Canvas
 *  android.graphics.Paint
 *  android.graphics.Paint$FontMetricsInt
 *  android.graphics.Rect
 *  android.graphics.drawable.Drawable
 *  android.support.annotation.NonNull
 *  android.text.style.ImageSpan
 */
package com.smule.android.ui;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.text.style.ImageSpan;

public class CustomImageSpan
extends ImageSpan {
    public void draw(@NonNull Canvas canvas, CharSequence charSequence, int n, int n2, float f, int n3, int n4, int n5, @NonNull Paint paint) {
        charSequence = this.getDrawable();
        canvas.save();
        canvas.translate(f, (float)(n5 - charSequence.getBounds().bottom - paint.getFontMetricsInt().descent / 2));
        charSequence.draw(canvas);
        canvas.restore();
    }
}

