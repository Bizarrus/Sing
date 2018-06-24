/*
 * Decompiled with CFR 0_123.
 */
package com.smule.singandroid.audio.exception;

import com.smule.singandroid.audio.exception.NativeException;

public class UninitializedException
extends NativeException {
    public UninitializedException(String string2) {
        super(string2);
    }

    public UninitializedException(String string2, Throwable throwable) {
        super(string2, throwable);
    }

    public UninitializedException(Throwable throwable) {
        super(throwable);
    }
}

