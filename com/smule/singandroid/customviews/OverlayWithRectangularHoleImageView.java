/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.Canvas
 *  android.graphics.Paint
 *  android.graphics.Paint$Style
 *  android.graphics.PorterDuff
 *  android.graphics.PorterDuff$Mode
 *  android.graphics.PorterDuffXfermode
 *  android.graphics.RectF
 *  android.graphics.Xfermode
 *  android.support.v7.widget.AppCompatImageView
 *  android.util.AttributeSet
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$OnClickListener
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class OverlayWithRectangularHoleImageView
extends AppCompatImageView {
    private RectF a;
    private final Paint b = new Paint(1);
    private final Paint c = new Paint(1);
    private View.OnClickListener d;

    public OverlayWithRectangularHoleImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.setLayerType(1, null);
    }

    private boolean a(MotionEvent motionEvent) {
        if (motionEvent.getX() > this.a.left && motionEvent.getX() < this.a.right && motionEvent.getY() > this.a.top && motionEvent.getY() < this.a.bottom) {
            return true;
        }
        return false;
    }

    public void a(int n, int n2, int n3, int n4) {
        this.a = new RectF((float)n, (float)n3, (float)n2, (float)n4);
        this.postInvalidate();
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        block6 : {
            block5 : {
                block7 : {
                    block4 : {
                        if (this.a == null) break block4;
                        if (motionEvent.getAction() != 0) break block5;
                        if (!this.a(motionEvent)) break block6;
                        if (this.d != null) break block7;
                    }
                    return false;
                }
                this.d.onClick((View)this);
                return false;
            }
            if (motionEvent.getAction() == 1 && !this.a(motionEvent) && this.d != null) {
                this.d.onClick((View)this);
            }
        }
        return true;
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.a != null) {
            this.b.setColor(this.getResources().getColor(17170444));
            this.b.setStyle(Paint.Style.FILL);
            this.b.setAlpha(150);
            canvas.drawPaint(this.b);
            this.c.setColor(0);
            this.c.setXfermode((Xfermode)new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
            this.c.setAntiAlias(true);
            canvas.drawRect(this.a, this.c);
        }
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.d = onClickListener;
    }
}

