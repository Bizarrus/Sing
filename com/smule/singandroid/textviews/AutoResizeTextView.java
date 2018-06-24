package com.smule.singandroid.textviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;
import com.mopub.volley.DefaultRetryPolicy;
import com.smule.android.logging.Log;
import com.smule.singandroid.R$styleable;

public class AutoResizeTextView extends TextView {
    public static final String f24494a = AutoResizeTextView.class.getSimpleName();
    private OnTextResizeListener f24495b;
    private boolean f24496c;
    private float f24497d;
    private float f24498e;
    private float f24499f;
    private float f24500g;
    private float f24501h;
    private boolean f24502i;
    private boolean f24503j;

    public interface OnTextResizeListener {
        void m25697a(TextView textView, float f, float f2);
    }

    public AutoResizeTextView(Context context) {
        this(context, null);
    }

    public AutoResizeTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AutoResizeTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f24496c = false;
        this.f24498e = 0.0f;
        this.f24499f = 5.0f;
        this.f24500g = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        this.f24501h = 0.0f;
        this.f24497d = getTextSize();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.AutoResizeTextView, i, 0);
        this.f24502i = obtainStyledAttributes.getBoolean(0, false);
        this.f24503j = obtainStyledAttributes.getBoolean(1, false);
    }

    protected void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.f24496c = true;
        m25700a();
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        if (i != i3 || i2 != i4) {
            this.f24496c = true;
        }
    }

    public void setOnResizeListener(OnTextResizeListener onTextResizeListener) {
        this.f24495b = onTextResizeListener;
    }

    public void setTextSize(float f) {
        super.setTextSize(f);
        this.f24497d = getTextSize();
    }

    public void setTextSize(int i, float f) {
        super.setTextSize(i, f);
        this.f24497d = getTextSize();
    }

    public void setLineSpacing(float f, float f2) {
        super.setLineSpacing(f, f2);
        this.f24500g = f2;
        this.f24501h = f;
    }

    public void m25700a() {
        if (this.f24497d > 0.0f) {
            super.setTextSize(0, this.f24497d);
            this.f24498e = this.f24497d;
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (z || this.f24496c) {
            m25701a(((i3 - i) - getCompoundPaddingLeft()) - getCompoundPaddingRight(), ((i4 - i2) - getCompoundPaddingBottom()) - getCompoundPaddingTop());
        }
        super.onLayout(z, i, i2, i3, i4);
    }

    public void m25701a(int i, int i2) {
        CharSequence text = getText();
        if (text != null && text.length() != 0 && i2 > 0 && i > 0 && this.f24497d != 0.0f) {
            if (getTransformationMethod() != null) {
                text = getTransformationMethod().getTransformation(text, this);
            }
            TextPaint paint = getPaint();
            float textSize = paint.getTextSize();
            float min = this.f24498e > 0.0f ? Math.min(this.f24497d, this.f24498e) : this.f24497d;
            if (this.f24502i) {
                int a = m25699a(text, paint, i, min);
                if (!this.f24503j) {
                    while (a > i2 && min > this.f24499f) {
                        float max = Math.max(min - 2.0f, this.f24499f);
                        min = max;
                        a = m25699a(text, paint, i, max);
                    }
                } else if (a > i2) {
                    Log.b(f24494a, "text too long:" + getText());
                    setText("");
                }
            } else {
                int a2 = m25698a(min);
                while (a2 > i && min > this.f24499f) {
                    min = Math.max(min - 2.0f, this.f24499f);
                    a2 = m25698a(min);
                }
            }
            setTextSize(0, min);
            setLineSpacing(this.f24501h, this.f24500g);
            if (this.f24495b != null) {
                this.f24495b.m25697a(this, textSize, min);
            }
            this.f24496c = false;
        }
    }

    private int m25699a(CharSequence charSequence, TextPaint textPaint, int i, float f) {
        TextPaint textPaint2 = new TextPaint(textPaint);
        textPaint2.setTextSize(f);
        return new StaticLayout(charSequence, textPaint2, i, Alignment.ALIGN_NORMAL, this.f24500g, this.f24501h, true).getHeight();
    }

    private int m25698a(float f) {
        Paint paint = new Paint();
        paint.setTextSize(f);
        return (int) paint.measureText(getText(), 0, getText().length());
    }
}
