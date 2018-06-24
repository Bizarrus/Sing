/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.Canvas
 *  android.graphics.Color
 *  android.graphics.Paint
 *  android.graphics.Rect
 *  android.graphics.RectF
 *  android.graphics.drawable.Drawable
 *  android.graphics.drawable.LayerDrawable
 *  android.graphics.drawable.ShapeDrawable
 *  android.graphics.drawable.shapes.RoundRectShape
 *  android.graphics.drawable.shapes.Shape
 */
package com.smule.singandroid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;

public class IconWithNotificationDrawable
extends LayerDrawable {
    private static final String a = IconWithNotificationDrawable.class.getSimpleName();
    private float b;
    private int c;
    private int d;

    public IconWithNotificationDrawable(Context context, int n, int n2, int n3, Drawable[] arrdrawable) {
        super(arrdrawable);
        this.b = (float)n / 2.0f;
        this.c = n2;
        this.d = n3;
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        float f = this.b;
        float f2 = (float)(this.getBounds().right - this.c) - f;
        float f3 = (float)this.getBounds().top + f + (float)this.d;
        ShapeDrawable shapeDrawable = new ShapeDrawable((Shape)new RoundRectShape(new float[]{f, f, f, f, f, f, f, f, f}, null, null));
        shapeDrawable.getPaint().setFlags(1);
        shapeDrawable.getPaint().setColor(Color.rgb((int)235, (int)22, (int)157));
        shapeDrawable.setBounds(Math.round(f2 - f), Math.round(f3 - f), Math.round(f2 + f), Math.round(f3 + f));
        shapeDrawable.draw(canvas);
    }
}

