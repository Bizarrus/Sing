/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.res.ColorStateList
 *  android.graphics.Bitmap
 *  android.graphics.Bitmap$Config
 *  android.graphics.BitmapShader
 *  android.graphics.Canvas
 *  android.graphics.ColorFilter
 *  android.graphics.Matrix
 *  android.graphics.Matrix$ScaleToFit
 *  android.graphics.Paint
 *  android.graphics.Paint$Style
 *  android.graphics.Rect
 *  android.graphics.RectF
 *  android.graphics.Shader
 *  android.graphics.Shader$TileMode
 *  android.graphics.drawable.BitmapDrawable
 *  android.graphics.drawable.Drawable
 *  android.graphics.drawable.LayerDrawable
 *  android.support.annotation.NonNull
 *  android.util.Log
 *  android.widget.ImageView
 *  android.widget.ImageView$ScaleType
 */
package com.smule.android.ui.roundedimageview;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;
import java.util.HashSet;
import java.util.Iterator;

public class RoundedDrawable
extends Drawable {
    private final RectF a = new RectF();
    private final RectF b = new RectF();
    private final RectF c = new RectF();
    private final Bitmap d;
    private final Paint e;
    private final int f;
    private final int g;
    private final RectF h = new RectF();
    private final Paint i;
    private final Matrix j = new Matrix();
    private final RectF k = new RectF();
    private Shader.TileMode l = Shader.TileMode.CLAMP;
    private Shader.TileMode m = Shader.TileMode.CLAMP;
    private boolean n = true;
    private float o = 0.0f;
    private final boolean[] p = new boolean[]{true, true, true, true};
    private boolean q = false;
    private float r = 0.0f;
    private ColorStateList s = ColorStateList.valueOf((int)-16777216);
    private ImageView.ScaleType t = ImageView.ScaleType.FIT_CENTER;

    public RoundedDrawable(Bitmap bitmap) {
        this.d = bitmap;
        this.f = bitmap.getWidth();
        this.g = bitmap.getHeight();
        this.c.set(0.0f, 0.0f, (float)this.f, (float)this.g);
        this.e = new Paint();
        this.e.setStyle(Paint.Style.FILL);
        this.e.setAntiAlias(true);
        this.i = new Paint();
        this.i.setStyle(Paint.Style.STROKE);
        this.i.setAntiAlias(true);
        this.i.setColor(this.s.getColorForState(this.getState(), -16777216));
        this.i.setStrokeWidth(this.r);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static Drawable a(Drawable drawable2) {
        Drawable drawable3 = drawable2;
        if (drawable2 == null) return drawable3;
        if (drawable2 instanceof RoundedDrawable) {
            return drawable2;
        }
        if (drawable2 instanceof LayerDrawable) {
            drawable2 = (LayerDrawable)drawable2;
            int n = drawable2.getNumberOfLayers();
            int n2 = 0;
            do {
                drawable3 = drawable2;
                if (n2 >= n) return drawable3;
                drawable3 = drawable2.getDrawable(n2);
                drawable2.setDrawableByLayerId(drawable2.getId(n2), RoundedDrawable.a(drawable3));
                ++n2;
            } while (true);
        }
        Bitmap bitmap = RoundedDrawable.b(drawable2);
        drawable3 = drawable2;
        if (bitmap == null) return drawable3;
        return new RoundedDrawable(bitmap);
    }

    public static RoundedDrawable a(Bitmap bitmap) {
        if (bitmap != null) {
            return new RoundedDrawable(bitmap);
        }
        return null;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(Canvas canvas) {
        float f;
        float f2;
        float f3;
        block7 : {
            block6 : {
                if (RoundedDrawable.b(this.p) || this.o == 0.0f) break block6;
                f2 = this.b.left;
                float f4 = this.b.top;
                float f5 = this.b.width() + f2;
                f = this.b.height() + f4;
                f3 = this.o;
                if (!this.p[0]) {
                    this.k.set(f2, f4, f2 + f3, f4 + f3);
                    canvas.drawRect(this.k, this.e);
                }
                if (!this.p[1]) {
                    this.k.set(f5 - f3, f4, f5, f3);
                    canvas.drawRect(this.k, this.e);
                }
                if (!this.p[2]) {
                    this.k.set(f5 - f3, f - f3, f5, f);
                    canvas.drawRect(this.k, this.e);
                }
                if (!this.p[3]) break block7;
            }
            return;
        }
        this.k.set(f2, f - f3, f3 + f2, f);
        canvas.drawRect(this.k, this.e);
    }

    private static boolean a(boolean[] arrbl) {
        boolean bl = false;
        int n = arrbl.length;
        int n2 = 0;
        do {
            block4 : {
                boolean bl2;
                block3 : {
                    bl2 = bl;
                    if (n2 >= n) break block3;
                    if (!arrbl[n2]) break block4;
                    bl2 = true;
                }
                return bl2;
            }
            ++n2;
        } while (true);
    }

    public static Bitmap b(Drawable drawable2) {
        if (drawable2 instanceof BitmapDrawable) {
            return ((BitmapDrawable)drawable2).getBitmap();
        }
        int n = Math.max(drawable2.getIntrinsicWidth(), 2);
        int n2 = Math.max(drawable2.getIntrinsicHeight(), 2);
        try {
            Bitmap bitmap = Bitmap.createBitmap((int)n, (int)n2, (Bitmap.Config)Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable2.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable2.draw(canvas);
            return bitmap;
        }
        catch (Exception exception) {
            exception.printStackTrace();
            Log.w((String)"RoundedDrawable", (String)"Failed to create bitmap from drawable!");
            return null;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void b() {
        float f = 0.0f;
        switch (.a[this.t.ordinal()]) {
            default: {
                this.h.set(this.c);
                this.j.setRectToRect(this.c, this.a, Matrix.ScaleToFit.CENTER);
                this.j.mapRect(this.h);
                this.h.inset(this.r / 2.0f, this.r / 2.0f);
                this.j.setRectToRect(this.c, this.h, Matrix.ScaleToFit.FILL);
                break;
            }
            case 1: {
                this.h.set(this.a);
                this.h.inset(this.r / 2.0f, this.r / 2.0f);
                this.j.reset();
                this.j.setTranslate((float)((int)((this.h.width() - (float)this.f) * 0.5f + 0.5f)), (float)((int)((this.h.height() - (float)this.g) * 0.5f + 0.5f)));
                break;
            }
            case 2: {
                float f2;
                float f3;
                this.h.set(this.a);
                this.h.inset(this.r / 2.0f, this.r / 2.0f);
                this.j.reset();
                if ((float)this.f * this.h.height() > this.h.width() * (float)this.g) {
                    f2 = this.h.height() / (float)this.g;
                    f3 = (this.h.width() - (float)this.f * f2) * 0.5f;
                } else {
                    f2 = this.h.width() / (float)this.f;
                    f = this.h.height();
                    float f4 = this.g;
                    f3 = 0.0f;
                    f = (f - f4 * f2) * 0.5f;
                }
                this.j.setScale(f2, f2);
                this.j.postTranslate((float)((int)(f3 + 0.5f)) + this.r / 2.0f, (float)((int)(f + 0.5f)) + this.r / 2.0f);
                break;
            }
            case 3: {
                this.j.reset();
                f = (float)this.f <= this.a.width() && (float)this.g <= this.a.height() ? 1.0f : Math.min(this.a.width() / (float)this.f, this.a.height() / (float)this.g);
                float f5 = (int)((this.a.width() - (float)this.f * f) * 0.5f + 0.5f);
                float f6 = (int)((this.a.height() - (float)this.g * f) * 0.5f + 0.5f);
                this.j.setScale(f, f);
                this.j.postTranslate(f5, f6);
                this.h.set(this.c);
                this.j.mapRect(this.h);
                this.h.inset(this.r / 2.0f, this.r / 2.0f);
                this.j.setRectToRect(this.c, this.h, Matrix.ScaleToFit.FILL);
                break;
            }
            case 5: {
                this.h.set(this.c);
                this.j.setRectToRect(this.c, this.a, Matrix.ScaleToFit.END);
                this.j.mapRect(this.h);
                this.h.inset(this.r / 2.0f, this.r / 2.0f);
                this.j.setRectToRect(this.c, this.h, Matrix.ScaleToFit.FILL);
                break;
            }
            case 6: {
                this.h.set(this.c);
                this.j.setRectToRect(this.c, this.a, Matrix.ScaleToFit.START);
                this.j.mapRect(this.h);
                this.h.inset(this.r / 2.0f, this.r / 2.0f);
                this.j.setRectToRect(this.c, this.h, Matrix.ScaleToFit.FILL);
                break;
            }
            case 7: {
                this.h.set(this.a);
                this.h.inset(this.r / 2.0f, this.r / 2.0f);
                this.j.reset();
                this.j.setRectToRect(this.c, this.h, Matrix.ScaleToFit.FILL);
            }
        }
        this.b.set(this.h);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void b(Canvas canvas) {
        float f;
        float f2;
        float f3;
        float f4;
        block7 : {
            block6 : {
                if (RoundedDrawable.b(this.p) || this.o == 0.0f) break block6;
                f2 = this.b.left;
                float f5 = this.b.top;
                float f6 = f2 + this.b.width();
                f = f5 + this.b.height();
                f4 = this.o;
                f3 = this.r / 2.0f;
                if (!this.p[0]) {
                    canvas.drawLine(f2 - f3, f5, f2 + f4, f5, this.i);
                    canvas.drawLine(f2, f5 - f3, f2, f5 + f4, this.i);
                }
                if (!this.p[1]) {
                    canvas.drawLine(f6 - f4 - f3, f5, f6, f5, this.i);
                    canvas.drawLine(f6, f5 - f3, f6, f5 + f4, this.i);
                }
                if (!this.p[2]) {
                    canvas.drawLine(f6 - f4 - f3, f, f6 + f3, f, this.i);
                    canvas.drawLine(f6, f - f4, f6, f, this.i);
                }
                if (!this.p[3]) break block7;
            }
            return;
        }
        canvas.drawLine(f2 - f3, f, f2 + f4, f, this.i);
        canvas.drawLine(f2, f - f4, f2, f, this.i);
    }

    private static boolean b(boolean[] arrbl) {
        int n = arrbl.length;
        for (int i = 0; i < n; ++i) {
            if (!arrbl[i]) continue;
            return false;
        }
        return true;
    }

    public Bitmap a() {
        return RoundedDrawable.b(this);
    }

    public RoundedDrawable a(float f) {
        this.r = f;
        this.i.setStrokeWidth(this.r);
        return this;
    }

    /*
     * Enabled aggressive block sorting
     */
    public RoundedDrawable a(float f, float f2, float f3, float f4) {
        boolean bl = true;
        boolean[] arrbl = new boolean[](4);
        arrbl.add((Float)Float.valueOf(f));
        arrbl.add((Float)Float.valueOf(f2));
        arrbl.add((Float)Float.valueOf(f3));
        arrbl.add((Float)Float.valueOf(f4));
        arrbl.remove(Float.valueOf(0.0f));
        if (arrbl.size() > 1) {
            throw new IllegalArgumentException("Multiple nonzero corner radii not yet supported.");
        }
        if (!arrbl.isEmpty()) {
            float f5 = ((Float)arrbl.iterator().next()).floatValue();
            if (Float.isInfinite(f5) || Float.isNaN(f5) || f5 < 0.0f) {
                throw new IllegalArgumentException("Invalid radius value: " + f5);
            }
            this.o = f5;
        } else {
            this.o = 0.0f;
        }
        arrbl = this.p;
        boolean bl2 = f > 0.0f;
        arrbl[0] = bl2;
        arrbl = this.p;
        bl2 = f2 > 0.0f;
        arrbl[1] = bl2;
        arrbl = this.p;
        bl2 = f3 > 0.0f;
        arrbl[2] = bl2;
        arrbl = this.p;
        bl2 = f4 > 0.0f ? bl : false;
        arrbl[3] = bl2;
        return this;
    }

    /*
     * Enabled aggressive block sorting
     */
    public RoundedDrawable a(ColorStateList colorStateList) {
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf((int)0);
        }
        this.s = colorStateList;
        this.i.setColor(this.s.getColorForState(this.getState(), -16777216));
        return this;
    }

    public RoundedDrawable a(Shader.TileMode tileMode) {
        if (this.l != tileMode) {
            this.l = tileMode;
            this.n = true;
            this.invalidateSelf();
        }
        return this;
    }

    public RoundedDrawable a(ImageView.ScaleType scaleType) {
        ImageView.ScaleType scaleType2 = scaleType;
        if (scaleType == null) {
            scaleType2 = ImageView.ScaleType.FIT_CENTER;
        }
        if (this.t != scaleType2) {
            this.t = scaleType2;
            this.b();
        }
        return this;
    }

    public RoundedDrawable a(boolean bl) {
        this.q = bl;
        return this;
    }

    public RoundedDrawable b(Shader.TileMode tileMode) {
        if (this.m != tileMode) {
            this.m = tileMode;
            this.n = true;
            this.invalidateSelf();
        }
        return this;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void draw(@NonNull Canvas canvas) {
        if (this.n) {
            BitmapShader bitmapShader = new BitmapShader(this.d, this.l, this.m);
            if (this.l == Shader.TileMode.CLAMP && this.m == Shader.TileMode.CLAMP) {
                bitmapShader.setLocalMatrix(this.j);
            }
            this.e.setShader((Shader)bitmapShader);
            this.n = false;
        }
        if (this.q) {
            if (this.r <= 0.0f) {
                canvas.drawOval(this.b, this.e);
                return;
            }
            canvas.drawOval(this.b, this.e);
            canvas.drawOval(this.h, this.i);
            return;
        } else {
            if (RoundedDrawable.a(this.p)) {
                float f = this.o;
                if (this.r > 0.0f) {
                    canvas.drawRoundRect(this.b, f, f, this.e);
                    canvas.drawRoundRect(this.h, f, f, this.i);
                    this.a(canvas);
                    this.b(canvas);
                    return;
                }
                canvas.drawRoundRect(this.b, f, f, this.e);
                this.a(canvas);
                return;
            }
            canvas.drawRect(this.b, this.e);
            if (this.r <= 0.0f) return;
            {
                canvas.drawRect(this.h, this.i);
                return;
            }
        }
    }

    public int getAlpha() {
        return this.e.getAlpha();
    }

    public ColorFilter getColorFilter() {
        return this.e.getColorFilter();
    }

    public int getIntrinsicHeight() {
        return this.g;
    }

    public int getIntrinsicWidth() {
        return this.f;
    }

    public int getOpacity() {
        return -3;
    }

    public boolean isStateful() {
        return this.s.isStateful();
    }

    protected void onBoundsChange(@NonNull Rect rect) {
        super.onBoundsChange(rect);
        this.a.set(rect);
        this.b();
    }

    protected boolean onStateChange(int[] arrn) {
        int n = this.s.getColorForState(arrn, 0);
        if (this.i.getColor() != n) {
            this.i.setColor(n);
            return true;
        }
        return super.onStateChange(arrn);
    }

    public void setAlpha(int n) {
        this.e.setAlpha(n);
        this.invalidateSelf();
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.e.setColorFilter(colorFilter);
        this.invalidateSelf();
    }

    public void setDither(boolean bl) {
        this.e.setDither(bl);
        this.invalidateSelf();
    }

    public void setFilterBitmap(boolean bl) {
        this.e.setFilterBitmap(bl);
        this.invalidateSelf();
    }

}

