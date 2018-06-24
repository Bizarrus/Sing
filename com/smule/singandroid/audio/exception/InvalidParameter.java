/*
 * Decompiled with CFR 0_123.
 */
package com.smule.singandroid.audio.exception;

import com.smule.singandroid.audio.exception.NativeException;

public class InvalidParameter
extends NativeException {
    public InvalidParameter(String string2) {
        super(string2);
    }

    InvalidParameter(String string2, Throwable throwable) {
        super(string2, throwable);
    }

    InvalidParameter(Throwable throwable) {
        super(throwable);
    }
}

