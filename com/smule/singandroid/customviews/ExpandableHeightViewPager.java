/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.support.v4.view.ViewPager
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.View$MeasureSpec
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

public class ExpandableHeightViewPager
extends ViewPager {
    public ExpandableHeightViewPager(Context context) {
        super(context);
    }

    public ExpandableHeightViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onMeasure(int n, int n2) {
        int n3 = 0;
        for (n2 = 0; n2 < this.getChildCount(); ++n2) {
            View view = this.getChildAt(n2);
            view.measure(n, View.MeasureSpec.makeMeasureSpec((int)0, (int)0));
            int n4 = view.getMeasuredHeight();
            int n5 = n3;
            if (n4 > n3) {
                n5 = n4;
            }
            n3 = n5;
        }
        super.onMeasure(n, View.MeasureSpec.makeMeasureSpec((int)n3, (int)1073741824));
    }
}

