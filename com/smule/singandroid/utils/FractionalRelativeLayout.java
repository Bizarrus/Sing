package com.smule.singandroid.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.smule.android.logging.Log;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.R$styleable;

public class FractionalRelativeLayout extends RelativeLayout {
    public static int f24746a;
    public static int f24747b;
    private static final String f24748c = FractionalRelativeLayout.class.getName();
    private float f24749d;
    private float f24750e;
    private boolean f24751f = true;
    private boolean f24752g = true;
    private int f24753h = 0;
    private int f24754i = 0;
    private boolean f24755j = true;

    public FractionalRelativeLayout(Context context) {
        super(context);
        int dimension = (int) getResources().getDimension(C1947R.dimen.row_single_height);
        this.f24754i = dimension;
        this.f24753h = dimension;
    }

    public FractionalRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.FractionalRelativeLayout, 0, 0);
        this.f24751f = obtainStyledAttributes.getBoolean(0, true);
        obtainStyledAttributes.recycle();
        int dimension = (int) getResources().getDimension(C1947R.dimen.row_single_height);
        this.f24754i = dimension;
        this.f24753h = dimension;
    }

    public void setStartFromBottom(boolean z) {
        this.f24751f = z;
    }

    public boolean getStartFromBottom() {
        return this.f24751f;
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        Log.c(f24748c, "onSizeChanged - called. mRespectOnSizeChanged = " + this.f24752g);
        if (this.f24752g) {
            f24746a = i;
            f24747b = i2;
            if (this.f24751f) {
                setYFraction(this.f24750e);
            }
        }
    }

    public float getXFraction() {
        return this.f24749d;
    }

    public void setXFraction(float f) {
        this.f24749d = f;
        if (f24746a > 0) {
            setX(((float) f24746a) * f);
        }
    }

    public float getYFraction() {
        return this.f24750e;
    }

    public void setYFraction(float f) {
        this.f24750e = f;
        if (f24747b > 0) {
            int i = 0;
            if (this.f24755j) {
                i = this.f24753h;
            }
            setY(((float) (f24747b - i)) - (((float) this.f24754i) * f));
        }
    }

    public void m25829a(String str, boolean z) {
        Log.c(f24748c, "respectOnSizeChanged - called from " + str + ", and setting respect to: " + z);
        this.f24752g = z;
    }

    public void setMiniBarHeight(int i) {
        this.f24754i = i;
    }

    public void setBottomMenuShowing(boolean z) {
        this.f24755j = z;
    }
}
