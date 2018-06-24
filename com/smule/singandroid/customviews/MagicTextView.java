/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.support.v7.widget.AppCompatTextView
 */
package com.smule.singandroid.customviews;

import android.support.v7.widget.AppCompatTextView;

public class MagicTextView
extends AppCompatTextView {
    private int a;

    public void setIconFontPadding(int n) {
        this.a = n;
        this.setCompoundDrawablePadding(this.a);
    }

    public static enum Position {
        a,
        b,
        c,
        d;
        

        private Position() {
        }
    }

}

