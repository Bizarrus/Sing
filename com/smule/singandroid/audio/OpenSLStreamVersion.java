package com.smule.singandroid.audio;

import java.security.InvalidParameterException;

public enum OpenSLStreamVersion {
    V1(1),
    V2(2),
    V3(3),
    UNSPECIFIED(0);
    
    int f20663e;

    private OpenSLStreamVersion(int i) {
        this.f20663e = i;
    }

    public int m22284a() {
        return this.f20663e;
    }

    public static OpenSLStreamVersion m22283a(int i) {
        for (OpenSLStreamVersion openSLStreamVersion : values()) {
            if (openSLStreamVersion.m22284a() == i) {
                return openSLStreamVersion;
            }
        }
        throw new InvalidParameterException("No OpenSLStreamVersion for the given value");
    }
}
