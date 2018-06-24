package com.smule.singandroid.customviews;

import android.content.Context;
import android.util.AttributeSet;
import com.smule.android.ui.SNPImageView;

public class SquareSNPImageView extends SNPImageView {
    public SquareSNPImageView(Context context) {
        super(context);
    }

    public SquareSNPImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SquareSNPImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int min = Math.min(getMeasuredWidth(), getMeasuredHeight());
        setMeasuredDimension(min, min);
    }
}
