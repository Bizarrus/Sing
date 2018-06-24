/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.FrameLayout
 *  android.widget.LinearLayout
 */
package com.smule.singandroid.boost;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class LearnHowToBoostTabIndicator
extends LinearLayout {
    protected int a = 0;

    public LearnHowToBoostTabIndicator(Context context) {
        super(context);
    }

    public LearnHowToBoostTabIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public LearnHowToBoostTabIndicator(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
    }

    private void a(int n) {
        View view = ((FrameLayout)this.getChildAt(n)).getChildAt(0);
        if (n == this.a) {
            view.setBackgroundResource(2130838088);
            return;
        }
        view.setBackgroundResource(2130838089);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void a(Context context, int n, int n2) {
        context = LayoutInflater.from((Context)context);
        int n3 = 0;
        while (n3 < n) {
            View view = context.inflate(2130903445, null);
            View view2 = view.findViewById(2131756752);
            int n4 = n3 == n2 ? 2130838089 : 2130838088;
            view2.setBackgroundResource(n4);
            this.addView(view);
            ++n3;
        }
    }

    public void setSelection(int n) {
        this.a(this.a);
        this.a(n);
        this.a = n;
    }
}

