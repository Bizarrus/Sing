/*
 * Decompiled with CFR 0_123.
 */
package com.smule.singandroid.audio.exception;

import com.smule.singandroid.audio.exception.NativeException;

public class PreconditionsViolated
extends NativeException {
    PreconditionsViolated(String string2) {
        super(string2);
    }

    PreconditionsViolated(String string2, Throwable throwable) {
        super(string2, throwable);
    }

    PreconditionsViolated(Throwable throwable) {
        super(throwable);
    }
}

