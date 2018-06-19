package com.smule.android.utils;

import java.util.Random;

public class RandomString {
    private static final char[] f17824a = new char[36];
    private final Random f17825b = new Random();
    private final char[] f17826c;

    static {
        int i = 10;
        for (int i2 = 0; i2 < 10; i2++) {
            f17824a[i2] = (char) (i2 + 48);
        }
        while (i < 36) {
            f17824a[i] = (char) ((i + 97) - 10);
            i++;
        }
    }

    public RandomString(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("length < 1: " + i);
        }
        this.f17826c = new char[i];
    }

    public String m19023a() {
        for (int i = 0; i < this.f17826c.length; i++) {
            this.f17826c[i] = f17824a[this.f17825b.nextInt(f17824a.length)];
        }
        return new String(this.f17826c);
    }
}
