/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.support.annotation.ColorInt
 *  android.support.annotation.StringRes
 */
package com.smule.singandroid.customviews;

import android.support.annotation.ColorInt;
import android.support.annotation.StringRes;

public class CyclableStateItemViewModel {
    @ColorInt
    private final int a;
    @StringRes
    private final int b;
    private final String c;

    public CyclableStateItemViewModel(@ColorInt int n, @StringRes int n2, String string2) {
        this.a = n;
        this.b = n2;
        this.c = string2;
    }

    @ColorInt
    public int a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }
}

