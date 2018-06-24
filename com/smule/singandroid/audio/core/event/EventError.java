/*
 * Decompiled with CFR 0_123.
 */
package com.smule.singandroid.audio.core.event;

import com.smule.singandroid.audio.core.event.EventParameterType;
import com.smule.singandroid.audio.core.exception.ErrorHelper;
import com.smule.singandroid.audio.core.exception.IError;

public enum EventError implements IError
{
    a(1, "Specified listener is invalid."),
    b(2, "Specified event type is invalid."),
    c(3, "Specified event filter is invalid."),
    d(4, "Specified event type '" + (Object)((Object)EventParameterType.a) + "' is not defined as available");
    
    private int e;
    private int f;
    private String g;

    private EventError(int n2, String string3) {
        this.f = n2;
        this.g = string3;
        this.e = ErrorHelper.a("EVENT_ERROR_CODE_OFFSET");
    }

    @Override
    public String a() {
        return this.g;
    }
}

