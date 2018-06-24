/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.TypedArray
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.ViewParent
 *  android.widget.RelativeLayout
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import android.widget.RelativeLayout;
import com.smule.singandroid.R;

public class BetweenRelativeLayout
extends RelativeLayout {
    int a;

    public BetweenRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a(context, attributeSet);
    }

    public BetweenRelativeLayout(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.a(context, attributeSet);
    }

    protected void a(Context context, AttributeSet attributeSet) {
        context = context.obtainStyledAttributes(attributeSet, R.BetweenRelativeLayout);
        this.a = context.getResourceId(0, 0);
        context.recycle();
    }

    protected void onLayout(boolean bl, int n, int n2, int n3, int n4) {
        super.onLayout(bl, n, n2, n3, n4);
        if (bl) {
            View view = null;
            if (this.a != 0) {
                view = ((View)this.getParent()).findViewById(this.a);
            }
            n = (n2 - n4) / 2;
            if (view != null) {
                int[] arrn = new int[2];
                this.getLocationInWindow(arrn);
                int[] arrn2 = new int[2];
                view.getLocationInWindow(arrn2);
                n -= arrn[1] - arrn2[1];
            }
            this.offsetTopAndBottom(n);
        }
    }
}

