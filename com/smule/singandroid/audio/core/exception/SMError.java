/*
 * Decompiled with CFR 0_123.
 */
package com.smule.singandroid.audio.core.exception;

import com.smule.singandroid.audio.core.exception.ErrorHelper;
import com.smule.singandroid.audio.core.exception.IError;

public enum SMError implements IError
{
    a(0, "No error");
    
    private int b;
    private int c;
    private String d;

    private SMError(int n2, String string3) {
        this.c = n2;
        this.d = string3;
        this.b = ErrorHelper.a("ERROR_CODE_OFFSET");
    }

    @Override
    public String a() {
        return this.d;
    }
}

