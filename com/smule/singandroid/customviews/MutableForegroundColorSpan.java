/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.graphics.Color
 *  android.os.Parcel
 *  android.text.TextPaint
 *  android.text.style.ForegroundColorSpan
 */
package com.smule.singandroid.customviews;

import android.graphics.Color;
import android.os.Parcel;
import android.text.TextPaint;
import android.text.style.ForegroundColorSpan;

public class MutableForegroundColorSpan
extends ForegroundColorSpan {
    private int a = 255;
    private int b;

    public MutableForegroundColorSpan(int n, int n2) {
        super(n2);
        this.a = n;
        this.b = n2;
    }

    public void a(int n) {
        this.b = n;
    }

    public int getForegroundColor() {
        return Color.argb((int)this.a, (int)Color.red((int)this.b), (int)Color.green((int)this.b), (int)Color.blue((int)this.b));
    }

    public void updateDrawState(TextPaint textPaint) {
        textPaint.setColor(this.getForegroundColor());
    }

    public void writeToParcel(Parcel parcel, int n) {
        super.writeToParcel(parcel, n);
        parcel.writeInt(this.b);
        parcel.writeFloat((float)this.a);
    }
}

