/*
 * Decompiled with CFR 0_123.
 */
package com.smule.singandroid.boost;

import com.smule.android.core.exception.ErrorHelper;
import com.smule.android.core.exception.IError;
import com.smule.android.core.state_machine.StateMachineParameterType;

public enum BoostError implements IError
{
    a(1, "(" + (Object)((Object)StateMachineParameterType.g) + ") An invalid class was provided"),
    b(2, "(" + (Object)((Object)StateMachineParameterType.g) + ") An invalid state was provided"),
    c(3, "(" + (Object)((Object)StateMachineParameterType.g) + ") An invalid tag was provided"),
    d(4, "(" + (Object)((Object)StateMachineParameterType.g) + ") An invalid tag was provided"),
    e(5, "(" + (Object)((Object)StateMachineParameterType.g) + ") An invalid command was provided");
    
    private final int f;
    private final int g;
    private final String h;

    private BoostError(int n2, String string3) {
        this.g = n2;
        this.h = string3;
        this.f = ErrorHelper.a("BOOST_ERROR_CODE_OFFSET");
    }

    @Override
    public String a() {
        return this.h;
    }
}

