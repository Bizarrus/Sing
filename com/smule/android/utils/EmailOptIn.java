package com.smule.android.utils;

public enum EmailOptIn {
    NEVER_ASKED(-1),
    NO(0),
    YES(1);
    
    private final int f17765d;

    private EmailOptIn(int i) {
        this.f17765d = i;
    }

    public int m18964a() {
        return this.f17765d;
    }

    public static EmailOptIn m18963a(int i) {
        for (EmailOptIn emailOptIn : values()) {
            if (emailOptIn.m18964a() == i) {
                return emailOptIn;
            }
        }
        throw new RuntimeException("Invalid value: " + i);
    }
}
