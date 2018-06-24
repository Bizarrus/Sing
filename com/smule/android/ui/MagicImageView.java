/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.view.View$MeasureSpec
 *  android.widget.ImageView
 */
package com.smule.android.ui;

import android.view.View;
import android.widget.ImageView;

public class MagicImageView
extends ImageView {
    protected void onMeasure(int n, int n2) {
        block3 : {
            block2 : {
                int n3 = View.MeasureSpec.getSize((int)n);
                if (n3 > (n2 = View.MeasureSpec.getSize((int)n2))) break block2;
                n = n3;
                if (n3 != 0) break block3;
            }
            n = n2;
        }
        this.setMeasuredDimension(n, n);
    }
}

