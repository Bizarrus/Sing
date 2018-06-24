/*
 * Decompiled with CFR 0_123.
 */
package com.smule.android.utils;

import java.util.Random;

public class RandomString {
    private static final char[] a;
    private final Random b = new Random();
    private final char[] c;

    static {
        int n = 10;
        a = new char[36];
        int n2 = 0;
        do {
            if (n2 >= 10) break;
            RandomString.a[n2] = (char)(n2 + 48);
            ++n2;
        } while (true);
        for (int i = n; i < 36; ++i) {
            RandomString.a[i] = (char)(i + 97 - 10);
        }
    }

    public RandomString(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("length < 1: " + n);
        }
        this.c = new char[n];
    }

    public String a() {
        for (int i = 0; i < this.c.length; ++i) {
            this.c[i] = a[this.b.nextInt(a.length)];
        }
        return new String(this.c);
    }
}

