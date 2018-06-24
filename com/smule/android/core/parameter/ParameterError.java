/*
 * Decompiled with CFR 0_123.
 */
package com.smule.android.core.parameter;

import com.smule.android.core.exception.ErrorHelper;
import com.smule.android.core.exception.IError;
import com.smule.android.core.parameter.ParameterType;

public enum ParameterError implements IError
{
    a(1, "Invalid type provided"),
    b(2, "Invalid key provided"),
    c(3, "Invalid mapped parameter provided"),
    d(4, "Invalid mapped parameters provided"),
    e(5, "Attempted to store duplicate parameter '" + (Object)((Object)ParameterType.a) + "'"),
    f(6, "Could not find parameter '" + (Object)((Object)ParameterType.a) + "'"),
    g(7, "Attempted to update an immutable property");
    
    private int h;
    private int i;
    private String j;

    private ParameterError(int n2, String string3) {
        this.i = n2;
        this.j = string3;
        this.h = ErrorHelper.a("PARAMETER_ERROR_CODE_OFFSET");
    }

    @Override
    public String a() {
        return this.j;
    }
}

