package com.smule.android.ui;

import android.graphics.drawable.Drawable;
import android.view.View.MeasureSpec;
import android.widget.ImageView;

public class ResizableImageView extends ImageView {
    protected void onMeasure(int i, int i2) {
        Drawable drawable = getDrawable();
        if (drawable != null) {
            int size = MeasureSpec.getSize(i);
            setMeasuredDimension(size, (int) Math.ceil((double) ((((float) drawable.getIntrinsicHeight()) / ((float) drawable.getIntrinsicWidth())) * ((float) size))));
            return;
        }
        super.onMeasure(i, i2);
    }
}
