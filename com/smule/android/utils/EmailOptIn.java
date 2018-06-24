/*
 * Decompiled with CFR 0_123.
 */
package com.smule.android.utils;

public enum EmailOptIn {
    a(-1),
    b(0),
    c(1);
    
    private final Integer d;

    private EmailOptIn(Integer n2) {
        this.d = n2;
    }

    public static EmailOptIn a(int n) {
        for (EmailOptIn emailOptIn : EmailOptIn.values()) {
            if (emailOptIn.a() != n) continue;
            return emailOptIn;
        }
        throw new RuntimeException("Invalid value: " + n);
    }

    public Integer a() {
        return this.d;
    }
}

