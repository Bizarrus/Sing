/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.support.annotation.NonNull
 */
package com.smule.android.audio;

import android.support.annotation.NonNull;

public enum GlitchContext {
    a("Sing"),
    b("Review");
    
    private final String c;

    private GlitchContext(@NonNull String string3) {
        this.c = string3;
    }

    public String a() {
        return this.c;
    }
}

