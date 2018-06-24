package com.smule.singandroid.customviews;

import android.graphics.Color;
import android.os.Parcel;
import android.text.TextPaint;
import android.text.style.ForegroundColorSpan;

public class MutableForegroundColorSpan extends ForegroundColorSpan {
    private int f21617a = 255;
    private int f21618b;

    public MutableForegroundColorSpan(int i, int i2) {
        super(i2);
        this.f21617a = i;
        this.f21618b = i2;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.f21618b);
        parcel.writeFloat((float) this.f21617a);
    }

    public void updateDrawState(TextPaint textPaint) {
        textPaint.setColor(getForegroundColor());
    }

    public void m23247a(int i) {
        this.f21618b = i;
    }

    public int getForegroundColor() {
        return Color.argb(this.f21617a, Color.red(this.f21618b), Color.green(this.f21618b), Color.blue(this.f21618b));
    }
}
