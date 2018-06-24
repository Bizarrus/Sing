package com.smule.singandroid.utils;

import android.view.View.MeasureSpec;
import android.widget.ImageView;

public class AspectRatioImageView extends ImageView {
    protected void onMeasure(int i, int i2) {
        int size = MeasureSpec.getSize(i);
        setMeasuredDimension(size, (getDrawable().getIntrinsicHeight() * size) / getDrawable().getIntrinsicWidth());
    }
}
