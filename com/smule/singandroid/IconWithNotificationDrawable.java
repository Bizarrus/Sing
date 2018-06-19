package com.smule.singandroid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;

public class IconWithNotificationDrawable extends LayerDrawable {
    private static final String f18762a = IconWithNotificationDrawable.class.getSimpleName();
    private float f18763b;
    private int f18764c;
    private int f18765d;

    public IconWithNotificationDrawable(Context context, int i, int i2, int i3, Drawable[] drawableArr) {
        super(drawableArr);
        this.f18763b = ((float) i) / 2.0f;
        this.f18764c = i2;
        this.f18765d = i3;
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        float f = this.f18763b;
        float f2 = ((float) (getBounds().right - this.f18764c)) - f;
        float f3 = (((float) getBounds().top) + f) + ((float) this.f18765d);
        float f4 = f2 - f;
        f2 += f;
        float f5 = f3 - f;
        f3 += f;
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(new float[]{f, f, f, f, f, f, f, f, f}, null, null));
        shapeDrawable.getPaint().setFlags(1);
        shapeDrawable.getPaint().setColor(Color.rgb(235, 22, 157));
        shapeDrawable.setBounds(Math.round(f4), Math.round(f5), Math.round(f2), Math.round(f3));
        shapeDrawable.draw(canvas);
    }
}
