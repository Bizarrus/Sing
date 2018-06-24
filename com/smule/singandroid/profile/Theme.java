/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.Color
 *  android.support.annotation.ColorInt
 *  android.support.annotation.ColorRes
 *  android.support.annotation.NonNull
 *  android.support.v4.content.ContextCompat
 */
package com.smule.singandroid.profile;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import com.smule.android.network.models.ColorTheme;

public enum Theme {
    a(2131689869, 2131689873, 2131689872, 2131689870, 2131689873),
    b(2131689875, 2131689877, 2131689876, 2131689874, 2131689875),
    c(2131689863, 2131689868, 2131689867, 2131689874, 2131689863),
    d(2131689864, 2131689866, 2131689865, 2131689874, 2131689864),
    e(2131689881, 2131689883, 2131689882, 2131689874, 2131689881),
    f(2131689878, 2131689880, 2131689879, 2131689874, 2131689878);
    
    @ColorRes
    private final int g;
    @ColorRes
    private final int h;
    @ColorRes
    private final int i;
    @ColorRes
    private final int j;
    @ColorRes
    private final int k;

    private Theme(int n2, int n3, int n4, int n5, int n6) {
        this.g = n2;
        this.h = n3;
        this.i = n4;
        this.j = n5;
        this.k = n6;
    }

    @ColorInt
    public static int a(int n) {
        return Color.rgb((int)(n >> 16 & 255), (int)(n >> 8 & 255), (int)(n & 255));
    }

    /*
     * Enabled aggressive block sorting
     */
    public static Theme a(@NonNull Context context, @NonNull ColorTheme arrtheme) {
        int n = Theme.a(arrtheme.getSnpBackgroundColor());
        int n2 = Theme.a(arrtheme.getSnpForegroundColor());
        int n3 = arrtheme.getLightText() ? 2131689874 : 2131689870;
        int n4 = ContextCompat.getColor((Context)context, (int)n3);
        arrtheme = Theme.values();
        int n5 = arrtheme.length;
        n3 = 0;
        while (n3 < n5) {
            Theme theme = arrtheme[n3];
            if (ContextCompat.getColor((Context)context, (int)theme.g) == n && ContextCompat.getColor((Context)context, (int)theme.h) == n2 && ContextCompat.getColor((Context)context, (int)theme.j) == n4) {
                return theme;
            }
            ++n3;
        }
        return a;
    }

    public static boolean a(Context context, int n) {
        if (ContextCompat.getColor((Context)context, (int)2131689874) == n) {
            return true;
        }
        return false;
    }

    @ColorInt
    public static int b(int n) {
        return 16777215 & n;
    }

    @ColorRes
    public int a() {
        return this.g;
    }

    @ColorRes
    public int b() {
        return this.h;
    }

    @ColorRes
    public int c() {
        return this.i;
    }

    @ColorRes
    public int d() {
        return this.j;
    }

    @ColorRes
    public int e() {
        return this.k;
    }
}

