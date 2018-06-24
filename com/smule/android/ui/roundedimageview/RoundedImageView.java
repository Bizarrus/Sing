/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.ColorStateList
 *  android.content.res.Resources
 *  android.content.res.TypedArray
 *  android.graphics.Bitmap
 *  android.graphics.ColorFilter
 *  android.graphics.Shader
 *  android.graphics.Shader$TileMode
 *  android.graphics.drawable.Drawable
 *  android.graphics.drawable.LayerDrawable
 *  android.net.Uri
 *  android.support.annotation.ColorInt
 *  android.support.annotation.DimenRes
 *  android.support.annotation.DrawableRes
 *  android.support.v7.widget.AppCompatImageView
 *  android.util.AttributeSet
 *  android.util.Log
 *  android.widget.ImageView
 *  android.widget.ImageView$ScaleType
 */
package com.smule.android.ui.roundedimageview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.ColorFilter;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.support.annotation.ColorInt;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import com.smule.android.R;
import com.smule.android.ui.roundedimageview.RoundedDrawable;
import com.smule.android.ui.roundedimageview.RoundedImageView;

public class RoundedImageView
extends AppCompatImageView {
    private static final ImageView.ScaleType[] a;
    public static final Shader.TileMode c;
    static final /* synthetic */ boolean d;
    private final float[] b = new float[]{0.0f, 0.0f, 0.0f, 0.0f};
    private Drawable e;
    private ColorStateList f = ColorStateList.valueOf((int)-16777216);
    private float g = 0.0f;
    private ColorFilter h = null;
    private boolean i = false;
    private Drawable j;
    private boolean k = false;
    private boolean l = false;
    private boolean m = false;
    private int n;
    private ImageView.ScaleType o = ImageView.ScaleType.FIT_CENTER;
    private Shader.TileMode p = c;
    private Shader.TileMode q = c;

    /*
     * Enabled aggressive block sorting
     */
    static {
        boolean bl = !RoundedImageView.class.desiredAssertionStatus();
        d = bl;
        c = Shader.TileMode.CLAMP;
        a = new ImageView.ScaleType[]{ImageView.ScaleType.MATRIX, ImageView.ScaleType.FIT_XY, ImageView.ScaleType.FIT_START, ImageView.ScaleType.FIT_CENTER, ImageView.ScaleType.FIT_END, ImageView.ScaleType.CENTER, ImageView.ScaleType.CENTER_CROP, ImageView.ScaleType.CENTER_INSIDE};
    }

    public RoundedImageView(Context context) {
        super(context);
    }

    public RoundedImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /*
     * Enabled aggressive block sorting
     */
    public RoundedImageView(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        context = context.obtainStyledAttributes(attributeSet, R.styleable.RoundedImageView, n, 0);
        n = context.getInt(R.styleable.RoundedImageView_android_scaleType, -1);
        if (n >= 0) {
            this.setScaleType(a[n]);
        } else {
            this.setScaleType(ImageView.ScaleType.FIT_CENTER);
        }
        float f = context.getDimensionPixelSize(R.styleable.RoundedImageView_riv_corner_radius, -1);
        this.b[0] = context.getDimensionPixelSize(R.styleable.RoundedImageView_riv_corner_radius_top_left, -1);
        this.b[1] = context.getDimensionPixelSize(R.styleable.RoundedImageView_riv_corner_radius_top_right, -1);
        this.b[2] = context.getDimensionPixelSize(R.styleable.RoundedImageView_riv_corner_radius_bottom_right, -1);
        this.b[3] = context.getDimensionPixelSize(R.styleable.RoundedImageView_riv_corner_radius_bottom_left, -1);
        int n2 = this.b.length;
        int n3 = 0;
        for (n = 0; n < n2; ++n) {
            if (this.b[n] < 0.0f) {
                this.b[n] = 0.0f;
                continue;
            }
            n3 = 1;
        }
        if (f < 0.0f) {
            f = 0.0f;
        }
        n3 = this.b.length;
        for (n = 0; n < n3; ++n) {
            this.b[n] = f;
        }
        this.g = context.getDimensionPixelSize(R.styleable.RoundedImageView_riv_border_width, -1);
        if (this.g < 0.0f) {
            this.g = 0.0f;
        }
        this.f = context.getColorStateList(R.styleable.RoundedImageView_riv_border_color);
        if (this.f == null) {
            this.f = ColorStateList.valueOf((int)-16777216);
        }
        this.m = context.getBoolean(R.styleable.RoundedImageView_riv_mutate_background, false);
        this.l = context.getBoolean(R.styleable.RoundedImageView_riv_oval, false);
        n = context.getInt(R.styleable.RoundedImageView_riv_tile_mode, -2);
        if (n != -2) {
            this.setTileModeX(RoundedImageView.a(n));
            this.setTileModeY(RoundedImageView.a(n));
        }
        if ((n = context.getInt(R.styleable.RoundedImageView_riv_tile_mode_x, -2)) != -2) {
            this.setTileModeX(RoundedImageView.a(n));
        }
        if ((n = context.getInt(R.styleable.RoundedImageView_riv_tile_mode_y, -2)) != -2) {
            this.setTileModeY(RoundedImageView.a(n));
        }
        this.b();
        this.a(true);
        context.recycle();
    }

    private static Shader.TileMode a(int n) {
        switch (n) {
            default: {
                return null;
            }
            case 0: {
                return Shader.TileMode.CLAMP;
            }
            case 1: {
                return Shader.TileMode.REPEAT;
            }
            case 2: 
        }
        return Shader.TileMode.MIRROR;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private Drawable a() {
        Drawable drawable2 = null;
        Resources resources = this.getResources();
        if (resources == null) {
            return null;
        }
        Drawable drawable3 = drawable2;
        if (this.n == 0) return RoundedDrawable.a(drawable3);
        try {
            drawable3 = resources.getDrawable(this.n);
        }
        catch (Exception exception) {
            Log.w((String)"RoundedImageView", (String)("Unable to find resource: " + this.n), (Throwable)exception);
            this.n = 0;
            drawable3 = drawable2;
            return RoundedDrawable.a(drawable3);
        }
        return RoundedDrawable.a(drawable3);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(Drawable drawable2) {
        block7 : {
            block6 : {
                if (drawable2 == null) break block6;
                if (drawable2 instanceof RoundedDrawable) {
                    ((RoundedDrawable)drawable2).a(this.o).a(this.g).a(this.f).a(this.l).a(this.p).b(this.q);
                    if (this.b != null) {
                        ((RoundedDrawable)drawable2).a(this.b[0], this.b[1], this.b[2], this.b[3]);
                    }
                    this.c();
                    return;
                }
                if (drawable2 instanceof LayerDrawable) break block7;
            }
            return;
        }
        drawable2 = (LayerDrawable)drawable2;
        int n = drawable2.getNumberOfLayers();
        int n2 = 0;
        while (n2 < n) {
            this.a(drawable2.getDrawable(n2));
            ++n2;
        }
    }

    private void a(boolean bl) {
        if (this.m) {
            if (bl) {
                this.e = RoundedDrawable.a(this.e);
            }
            this.a(this.e);
        }
    }

    private void b() {
        this.a(this.j);
    }

    private void c() {
        if (this.j != null && this.i) {
            this.j = this.j.mutate();
            if (this.k) {
                this.j.setColorFilter(this.h);
            }
        }
    }

    public void a(float f, float f2, float f3, float f4) {
        if (this.b[0] == f && this.b[1] == f2 && this.b[2] == f4 && this.b[3] == f3) {
            return;
        }
        this.b[0] = f;
        this.b[1] = f2;
        this.b[3] = f3;
        this.b[2] = f4;
        this.b();
        this.a(false);
        this.invalidate();
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        this.invalidate();
    }

    @ColorInt
    public int getBorderColor() {
        return this.f.getDefaultColor();
    }

    public ColorStateList getBorderColors() {
        return this.f;
    }

    public float getBorderWidth() {
        return this.g;
    }

    public float getCornerRadius() {
        return this.getMaxCornerRadius();
    }

    public float getMaxCornerRadius() {
        float f = 0.0f;
        float[] arrf = this.b;
        int n = arrf.length;
        for (int i = 0; i < n; ++i) {
            f = Math.max(arrf[i], f);
        }
        return f;
    }

    public ImageView.ScaleType getScaleType() {
        return this.o;
    }

    public Shader.TileMode getTileModeX() {
        return this.p;
    }

    public Shader.TileMode getTileModeY() {
        return this.q;
    }

    public void setBackground(Drawable drawable2) {
        this.setBackgroundDrawable(drawable2);
    }

    @Deprecated
    public void setBackgroundDrawable(Drawable drawable2) {
        this.e = drawable2;
        this.a(true);
        super.setBackgroundDrawable(this.e);
    }

    public void setBorderColor(@ColorInt int n) {
        this.setBorderColor(ColorStateList.valueOf((int)n));
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setBorderColor(ColorStateList colorStateList) {
        block5 : {
            block4 : {
                if (this.f.equals((Object)colorStateList)) break block4;
                if (colorStateList == null) {
                    colorStateList = ColorStateList.valueOf((int)-16777216);
                }
                this.f = colorStateList;
                this.b();
                this.a(false);
                if (this.g > 0.0f) break block5;
            }
            return;
        }
        this.invalidate();
    }

    public void setBorderWidth(float f) {
        if (this.g == f) {
            return;
        }
        this.g = f;
        this.b();
        this.a(false);
        this.invalidate();
    }

    public void setBorderWidth(@DimenRes int n) {
        this.setBorderWidth(this.getResources().getDimension(n));
    }

    public void setColorFilter(ColorFilter colorFilter) {
        if (this.h != colorFilter) {
            this.h = colorFilter;
            this.k = true;
            this.i = true;
            this.c();
            this.invalidate();
        }
    }

    public void setCornerRadius(float f) {
        this.a(f, f, f, f);
    }

    public void setCornerRadiusDimen(@DimenRes int n) {
        float f = this.getResources().getDimension(n);
        this.a(f, f, f, f);
    }

    public void setImageBitmap(Bitmap bitmap) {
        this.n = 0;
        this.j = RoundedDrawable.a(bitmap);
        this.b();
        super.setImageDrawable(this.j);
    }

    public void setImageDrawable(Drawable drawable2) {
        this.n = 0;
        this.j = RoundedDrawable.a(drawable2);
        this.b();
        super.setImageDrawable(this.j);
    }

    public void setImageResource(@DrawableRes int n) {
        if (this.n != n) {
            this.n = n;
            this.j = this.a();
            this.b();
            super.setImageDrawable(this.j);
        }
    }

    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        this.setImageDrawable(this.getDrawable());
    }

    public void setOval(boolean bl) {
        this.l = bl;
        this.b();
        this.a(false);
        this.invalidate();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setScaleType(ImageView.ScaleType scaleType) {
        if (!d && scaleType == null) {
            throw new AssertionError();
        }
        if (this.o != scaleType) {
            this.o = scaleType;
            switch (.a[scaleType.ordinal()]) {
                default: {
                    super.setScaleType(scaleType);
                    break;
                }
                case 1: 
                case 2: 
                case 3: 
                case 4: 
                case 5: 
                case 6: 
                case 7: {
                    super.setScaleType(ImageView.ScaleType.FIT_XY);
                }
            }
            this.b();
            this.a(false);
            this.invalidate();
        }
    }

    public void setTileModeX(Shader.TileMode tileMode) {
        if (this.p == tileMode) {
            return;
        }
        this.p = tileMode;
        this.b();
        this.a(false);
        this.invalidate();
    }

    public void setTileModeY(Shader.TileMode tileMode) {
        if (this.q == tileMode) {
            return;
        }
        this.q = tileMode;
        this.b();
        this.a(false);
        this.invalidate();
    }
}

