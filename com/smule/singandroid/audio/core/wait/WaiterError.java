/*
 * Decompiled with CFR 0_123.
 */
package com.smule.singandroid.audio.core.wait;

import com.smule.singandroid.audio.core.exception.ErrorHelper;
import com.smule.singandroid.audio.core.exception.IError;
import com.smule.singandroid.audio.core.wait.WaiterParameterType;

public enum WaiterError implements IError
{
    a(1, "Waiter timed out after '" + (Object)((Object)WaiterParameterType.a) + "' msec"),
    b(2, "Invalid timeout specified: " + (Object)((Object)WaiterParameterType.a) + " msec"),
    c(3, "Could not perform wait, reason: " + (Object)((Object)WaiterParameterType.b));
    
    private int d;
    private int e;
    private String f;

    private WaiterError(int n2, String string3) {
        this.e = n2;
        this.f = string3;
        this.d = ErrorHelper.a("WAITER_ERROR_CODE_OFFSET");
    }

    @Override
    public String a() {
        return this.f;
    }
}

