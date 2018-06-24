/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.widget.ScrollView
 */
package com.smule.android.console;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class ConsoleScrollView
extends ScrollView {
    private ScrollViewListener a = null;

    public ConsoleScrollView(Context context) {
        super(context);
    }

    public ConsoleScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ConsoleScrollView(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
    }

    protected void onScrollChanged(int n, int n2, int n3, int n4) {
        super.onScrollChanged(n, n2, n3, n4);
        if (this.a != null) {
            this.a.a(n, n2, n3, n4);
        }
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.a = scrollViewListener;
    }

    public static interface ScrollViewListener {
        public void a(int var1, int var2, int var3, int var4);
    }

}

