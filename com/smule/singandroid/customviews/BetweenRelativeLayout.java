package com.smule.singandroid.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import com.smule.singandroid.R$styleable;

public class BetweenRelativeLayout extends RelativeLayout {
    int f21451a;

    public BetweenRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m23117a(context, attributeSet);
    }

    public BetweenRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m23117a(context, attributeSet);
    }

    protected void m23117a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.BetweenRelativeLayout);
        this.f21451a = obtainStyledAttributes.getResourceId(0, 0);
        obtainStyledAttributes.recycle();
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            int i5;
            View view = null;
            if (this.f21451a != 0) {
                view = ((View) getParent()).findViewById(this.f21451a);
            }
            int i6 = (i2 - i4) / 2;
            if (view != null) {
                int[] iArr = new int[2];
                getLocationInWindow(iArr);
                int[] iArr2 = new int[2];
                view.getLocationInWindow(iArr2);
                i5 = i6 - (iArr[1] - iArr2[1]);
            } else {
                i5 = i6;
            }
            offsetTopAndBottom(i5);
        }
    }
}
