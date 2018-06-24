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

public class QuickSelectControlItemViewModel {
    private boolean a;
    private final int b;
    @StringRes
    private final int c;
    @ColorInt
    private final int d;

    public QuickSelectControlItemViewModel(int n, @StringRes int n2, @ColorInt int n3) {
        this.b = n;
        this.c = n2;
        this.d = n3;
    }

    public int a() {
        return this.c;
    }

    public void a(boolean bl) {
        this.a = bl;
    }

    public int b() {
        return this.d;
    }

    public int c() {
        return this.b;
    }

    public boolean d() {
        return this.a;
    }
}

