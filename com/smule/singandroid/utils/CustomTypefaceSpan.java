package com.smule.singandroid.utils;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.TextAppearanceSpan;

public class CustomTypefaceSpan extends TextAppearanceSpan {
    private final TextViewStyle f24680a;
    private final int f24681b;

    public static class TextViewStyle {
        float f24677a;
        int f24678b;
        Typeface f24679c;

        private TextViewStyle(float f, int i, Typeface typeface) {
            this.f24677a = f;
            this.f24678b = i;
            this.f24679c = typeface;
        }
    }

    public CustomTypefaceSpan(Context context, float f, int i, Typeface typeface) {
        super(context, 16842817);
        this.f24681b = context.getResources().getColor(i);
        this.f24680a = new TextViewStyle(f, i, typeface);
    }

    public CustomTypefaceSpan(Context context, TextViewStyle textViewStyle) {
        super(context, 16842817);
        this.f24681b = context.getResources().getColor(textViewStyle.f24678b);
        this.f24680a = textViewStyle;
    }

    public void updateDrawState(TextPaint textPaint) {
        m25817a(textPaint, this.f24680a);
    }

    public void updateMeasureState(TextPaint textPaint) {
        m25817a(textPaint, this.f24680a);
    }

    private void m25817a(Paint paint, TextViewStyle textViewStyle) {
        if (textViewStyle != null) {
            paint.setColor(this.f24681b);
            paint.setTextSize(textViewStyle.f24677a);
            paint.setTypeface(textViewStyle.f24679c);
        }
    }
}
