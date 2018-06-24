/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.support.annotation.ColorRes
 */
package com.smule.singandroid.customviews;

import android.support.annotation.ColorRes;

public class ColorSelectorItemViewModel {
    @ColorRes
    private final int a;
    @ColorRes
    private final int b;
    private final String c;
    private final boolean d;
    private final boolean e;

    public ColorSelectorItemViewModel(@ColorRes int n, @ColorRes int n2, String string2, boolean bl, boolean bl2) {
        this.a = n;
        this.b = n2;
        this.c = string2;
        this.d = bl;
        this.e = bl2;
    }

    public int a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public boolean d() {
        return this.d;
    }

    public boolean e() {
        return this.e;
    }
}

