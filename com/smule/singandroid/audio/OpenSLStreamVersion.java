/*
 * Decompiled with CFR 0_123.
 */
package com.smule.singandroid.audio;

import java.security.InvalidParameterException;

public enum OpenSLStreamVersion {
    a(1),
    b(2),
    c(3),
    d(4),
    e(0);
    
    private int f;

    private OpenSLStreamVersion(int n2) {
        this.f = n2;
    }

    public static OpenSLStreamVersion a(int n) {
        for (OpenSLStreamVersion openSLStreamVersion : OpenSLStreamVersion.values()) {
            if (openSLStreamVersion.a() != n) continue;
            return openSLStreamVersion;
        }
        throw new InvalidParameterException("No OpenSLStreamVersion for the given value");
    }

    public int a() {
        return this.f;
    }
}

