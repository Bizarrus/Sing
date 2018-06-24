/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.graphics.Canvas
 *  android.graphics.ColorFilter
 *  android.graphics.Paint
 *  android.graphics.Paint$Align
 *  android.graphics.Paint$Style
 *  android.graphics.Rect
 *  android.graphics.RectF
 *  android.graphics.Typeface
 *  android.graphics.drawable.ShapeDrawable
 *  android.graphics.drawable.shapes.OvalShape
 *  android.graphics.drawable.shapes.RectShape
 *  android.graphics.drawable.shapes.RoundRectShape
 *  android.graphics.drawable.shapes.Shape
 *  android.support.annotation.ColorInt
 *  android.support.annotation.Dimension
 */
package com.smule.android.ui;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.support.annotation.ColorInt;
import android.support.annotation.Dimension;
import com.smule.android.ui.CircleShape;

public class TextDrawable
extends ShapeDrawable {
    private final Paint a;
    private final Paint b;
    private final Paint c;
    private final String d;
    @ColorInt
    private final int e;
    @ColorInt
    private final int f;
    private final RectShape g;
    private final int h;
    private final int i;
    private final int j;
    private final float k;
    private final float l;
    private boolean m;
    private RectShape n;
    private final float o;
    private int p;
    private float q;
    private boolean r = false;

    /*
     * Enabled aggressive block sorting
     */
    private TextDrawable(TextDrawableBuilder textDrawableBuilder) {
        super((Shape)textDrawableBuilder.j);
        this.g = textDrawableBuilder.j;
        this.h = textDrawableBuilder.h;
        this.i = textDrawableBuilder.g;
        this.k = textDrawableBuilder.c;
        this.m = textDrawableBuilder.n;
        this.n = textDrawableBuilder.o;
        this.p = textDrawableBuilder.q;
        this.q = textDrawableBuilder.r;
        this.o = textDrawableBuilder.p;
        this.r = textDrawableBuilder.s;
        String string2 = textDrawableBuilder.m ? textDrawableBuilder.d.toUpperCase() : textDrawableBuilder.d;
        this.d = string2;
        this.e = textDrawableBuilder.e;
        this.f = textDrawableBuilder.a;
        this.j = textDrawableBuilder.k;
        this.a = new Paint();
        this.a.setColor(textDrawableBuilder.b);
        this.a.setAntiAlias(true);
        this.a.setFakeBoldText(textDrawableBuilder.l);
        this.a.setStyle(Paint.Style.FILL);
        this.a.setTypeface(textDrawableBuilder.i);
        this.a.setTextAlign(Paint.Align.CENTER);
        this.a.setStrokeWidth(textDrawableBuilder.f);
        this.l = textDrawableBuilder.f;
        this.b = new Paint();
        this.b.setAntiAlias(true);
        this.b.setColor(this.f);
        string2 = this.b;
        textDrawableBuilder = this.m ? Paint.Style.FILL : Paint.Style.STROKE;
        string2.setStyle((Paint.Style)textDrawableBuilder);
        this.b.setStrokeWidth(this.l);
        this.c = new Paint();
        this.c.setAntiAlias(true);
        this.c.setColor(this.p);
        string2 = this.c;
        textDrawableBuilder = this.r ? Paint.Style.FILL : Paint.Style.STROKE;
        string2.setStyle((Paint.Style)textDrawableBuilder);
        this.c.setStrokeWidth(this.q);
        this.getPaint().setColor(this.e);
    }

    public static ShapeBuilder a() {
        return new TextDrawableBuilder();
    }

    private void a(Canvas canvas, RectShape rectShape, float f, float f2, Paint paint) {
        RectF rectF = new RectF(this.getBounds());
        rectF.inset(f2 / 2.0f, f2 / 2.0f);
        if (rectShape instanceof CircleShape) {
            canvas.drawCircle(rectF.centerX(), rectF.centerY(), f, paint);
            return;
        }
        if (rectShape instanceof OvalShape) {
            canvas.drawOval(rectF, paint);
            return;
        }
        if (rectShape instanceof RoundRectShape) {
            canvas.drawRoundRect(rectF, f, f, paint);
            return;
        }
        canvas.drawRect(rectF, paint);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Rect rect = this.getBounds();
        if (this.l > 0.0f || this.m) {
            this.a(canvas, this.g, this.k, this.l, this.b);
        }
        if (this.q > 0.0f || this.r) {
            this.a(canvas, this.n, this.o, this.q, this.c);
        }
        int n = canvas.save();
        canvas.translate((float)rect.left, (float)rect.top);
        int n2 = this.i < 0 ? rect.width() : this.i;
        int n3 = this.h < 0 ? rect.height() : this.h;
        int n4 = this.j < 0 ? Math.min(n2, n3) / 2 : this.j;
        this.a.setTextSize((float)n4);
        canvas.drawText(this.d, (float)(n2 / 2), (float)(n3 / 2) - (this.a.descent() + this.a.ascent()) / 2.0f, this.a);
        canvas.restoreToCount(n);
    }

    public int getIntrinsicHeight() {
        return this.h;
    }

    public int getIntrinsicWidth() {
        return this.i;
    }

    public int getOpacity() {
        return this.a.getAlpha();
    }

    public void setAlpha(int n) {
        this.a.setAlpha(n);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.a.setColorFilter(colorFilter);
    }

    public static interface Builder {
    }

    public static interface PropertyBuilder {
        public PropertyBuilder a(float var1);

        public PropertyBuilder a(int var1);

        public PropertyBuilder a(Typeface var1);

        public PropertyBuilder a(boolean var1);

        public ShapeBuilder a();

        public PropertyBuilder b(int var1);

        public PropertyBuilder c(@ColorInt int var1);

        public PropertyBuilder d(@Dimension int var1);
    }

    public static interface ShapeBuilder {
        public TextDrawable a(String var1, @ColorInt int var2);

        public PropertyBuilder b();
    }

    public static class TextDrawableBuilder
    implements Builder,
    PropertyBuilder,
    ShapeBuilder {
        public int a = 0;
        public int b = -1;
        public float c;
        private String d = "";
        private int e = -7829368;
        private float f = 0.0f;
        private int g = -1;
        private int h = -1;
        private Typeface i = Typeface.DEFAULT;
        private RectShape j = new RectShape();
        private int k = -1;
        private boolean l = false;
        private boolean m = false;
        private boolean n = false;
        private RectShape o;
        private float p;
        private int q = 0;
        private float r = 0.0f;
        private boolean s = false;

        private TextDrawableBuilder() {
        }

        @Override
        public PropertyBuilder a(float f) {
            this.f = f;
            return this;
        }

        @Override
        public PropertyBuilder a(int n) {
            this.g = n;
            return this;
        }

        @Override
        public PropertyBuilder a(Typeface typeface) {
            this.i = typeface;
            return this;
        }

        @Override
        public PropertyBuilder a(boolean bl) {
            this.n = bl;
            return this;
        }

        @Override
        public ShapeBuilder a() {
            return this;
        }

        @Override
        public TextDrawable a(String string2, @ColorInt int n) {
            this.c();
            return this.b(string2, n);
        }

        @Override
        public PropertyBuilder b() {
            return this;
        }

        @Override
        public PropertyBuilder b(int n) {
            this.h = n;
            return this;
        }

        public TextDrawable b(String string2, @ColorInt int n) {
            this.e = n;
            this.d = string2;
            return new TextDrawable(this);
        }

        public Builder c() {
            this.j = new OvalShape();
            return this;
        }

        @Override
        public PropertyBuilder c(int n) {
            this.b = n;
            return this;
        }

        @Override
        public PropertyBuilder d(int n) {
            this.k = n;
            return this;
        }
    }

}

