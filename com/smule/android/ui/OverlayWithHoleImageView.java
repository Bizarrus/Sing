/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.Canvas
 *  android.graphics.Paint
 *  android.graphics.PorterDuff
 *  android.graphics.PorterDuff$Mode
 *  android.graphics.PorterDuffXfermode
 *  android.graphics.RectF
 *  android.graphics.Xfermode
 *  android.util.AttributeSet
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.ImageView
 */
package com.smule.android.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class OverlayWithHoleImageView
extends ImageView {
    private RectF a;
    private int b;
    private int c;
    private int d;
    private final Paint e = new Paint(1);
    private final PorterDuffXfermode f = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
    private View.OnClickListener g;
    private View.OnClickListener h;

    public OverlayWithHoleImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.setLayerType(1, null);
    }

    public void a(int n, int n2, int n3) {
        RectF rectF = new RectF((float)(n - n3), (float)(n2 - n3), (float)(n + n3), (float)(n2 + n3));
        this.b = n;
        this.c = n2;
        this.a = rectF;
        this.d = n3;
        this.postInvalidate();
    }

    public void a(View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        this.h = onClickListener;
        this.g = onClickListener2;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() != 1) return true;
        {
            if (Math.abs(motionEvent.getX() - (float)this.b) < (float)this.d && Math.abs(motionEvent.getY() - (float)this.c) < (float)this.d) {
                if (this.h == null) return true;
                {
                    this.h.onClick(null);
                    return true;
                }
            } else {
                if (this.g == null) return true;
                {
                    this.g.onClick(null);
                    return true;
                }
            }
        }
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.a != null) {
            this.e.setXfermode((Xfermode)this.f);
            canvas.drawRoundRect(this.a, (float)this.d, (float)this.d, this.e);
        }
    }
}

