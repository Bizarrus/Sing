package com.smule.singandroid.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.ImageView;

public class NativeAdsMainImageView extends ImageView {
    public NativeAdsMainImageView(Context context) {
        super(context);
    }

    public NativeAdsMainImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public NativeAdsMainImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onMeasure(int i, int i2) {
        int size = MeasureSpec.getSize(i);
        setMeasuredDimension(size, (int) (((float) size) / 1.91f));
    }
}
