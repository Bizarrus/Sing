package com.smule.singandroid.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.ScrollView;
import com.smule.android.logging.Log;

public class ScrollViewWithMaxHeight extends ScrollView {
    public static final String f21954a = ScrollViewWithMaxHeight.class.getName();
    public static int f21955b = -1;
    private int f21956c = f21955b;

    public ScrollViewWithMaxHeight(Context context) {
        super(context);
    }

    public ScrollViewWithMaxHeight(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ScrollViewWithMaxHeight(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onMeasure(int i, int i2) {
        try {
            int size = MeasureSpec.getSize(i2);
            if (this.f21956c != f21955b && size > this.f21956c) {
                size = this.f21956c;
            }
            i2 = MeasureSpec.makeMeasureSpec(size, Integer.MIN_VALUE);
            getLayoutParams().height = size;
        } catch (Exception e) {
            Log.d(f21954a, "onMesure: Error forcing height:" + e);
        } finally {
            super.onMeasure(i, i2);
        }
    }

    public void setMaxHeight(int i) {
        this.f21956c = i;
    }
}
