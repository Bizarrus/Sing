package com.smule.android.ui;

import android.view.View.MeasureSpec;
import android.widget.ImageView;

public class MagicImageView extends ImageView {
    protected void onMeasure(int i, int i2) {
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        if (size > size2 || size == 0) {
            size = size2;
        }
        setMeasuredDimension(size, size);
    }
}
