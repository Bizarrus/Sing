/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.View$MeasureSpec
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.widget.ScrollView
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import com.smule.android.logging.Log;

public class ScrollViewWithMaxHeight
extends ScrollView {
    public static final String a = ScrollViewWithMaxHeight.class.getName();
    public static int b = -1;
    private int c = b;

    public ScrollViewWithMaxHeight(Context context) {
        super(context);
    }

    public ScrollViewWithMaxHeight(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ScrollViewWithMaxHeight(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
    }

    protected void onMeasure(int n, int n2) {
        int n3;
        int n4;
        int n5;
        block9 : {
            int n6;
            n3 = n2;
            n5 = n2;
            n4 = n6 = View.MeasureSpec.getSize((int)n2);
            n3 = n2;
            n5 = n2;
            if (this.c == b) break block9;
            n4 = n6;
            n3 = n2;
            n5 = n2;
            if (n6 <= this.c) break block9;
            n3 = n2;
            n5 = n2;
            n4 = this.c;
        }
        n3 = n2;
        n5 = n2;
        n3 = n2 = View.MeasureSpec.makeMeasureSpec((int)n4, (int)Integer.MIN_VALUE);
        n5 = n2;
        try {
            this.getLayoutParams().height = n4;
        }
        catch (Exception exception) {
            n5 = n3;
            try {
                Log.d(a, "onMesure: Error forcing height:" + exception);
            }
            catch (Throwable throwable) {
                super.onMeasure(n, n5);
                throw throwable;
            }
            super.onMeasure(n, n3);
            return;
        }
        super.onMeasure(n, n2);
        return;
    }

    public void setMaxHeight(int n) {
        this.c = n;
    }
}

