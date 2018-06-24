/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.widget.LinearLayout
 */
package com.smule.singandroid;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.smule.singandroid.BaseFragment;

public abstract class FindFriendsPageView
extends LinearLayout {
    protected Context l;
    protected BaseFragment m;

    public FindFriendsPageView(Context context) {
        super(context);
    }

    public FindFriendsPageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public FindFriendsPageView(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
    }

    public void a() {
    }

    public void c() {
    }

    public boolean h() {
        if (this.m == null) {
            return false;
        }
        return this.m.isAdded();
    }
}

