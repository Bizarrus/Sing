package com.smule.singandroid.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.TextureView;
import android.view.View.MeasureSpec;

public class SquareTextureView extends TextureView {
    private int f21999a;

    public SquareTextureView(Context context) {
        super(context);
    }

    public SquareTextureView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SquareTextureView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected synchronized void onMeasure(int i, int i2) {
        int size;
        if (this.f21999a == 0) {
            size = MeasureSpec.getSize(i);
        } else {
            size = this.f21999a;
        }
        setMeasuredDimension(size, size);
    }

    public void setWidth(int i) {
        this.f21999a = i;
    }
}
