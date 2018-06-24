/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.support.annotation.NonNull
 */
package com.smule.singandroid.audio.core.state_machine;

import android.support.annotation.NonNull;
import com.smule.singandroid.audio.core.exception.ErrorHelper;
import com.smule.singandroid.audio.core.exception.IError;
import com.smule.singandroid.audio.core.state_machine.StateMachineParameterType;

public enum StateMachineErrorCode implements IError
{
    a(1, "(" + (Object)((Object)StateMachineParameterType.g) + ") An invalid class was provided"),
    b(2, "(" + (Object)((Object)StateMachineParameterType.g) + ") An invalid state was provided"),
    c(3, "(" + (Object)((Object)StateMachineParameterType.g) + ") An invalid tag was provided"),
    d(4, "(" + (Object)((Object)StateMachineParameterType.g) + ") An invalid command was provided"),
    e(5, "(" + (Object)((Object)StateMachineParameterType.g) + ") An invalid error code was provided"),
    f(6, "(" + (Object)((Object)StateMachineParameterType.g) + ") Duplicate state transition definition for Key/Output: '" + (Object)((Object)StateMachineParameterType.a) + " / " + (Object)((Object)StateMachineParameterType.b) + "'"),
    g(7, "(" + (Object)((Object)StateMachineParameterType.g) + ") No state transition defined for Key: '" + (Object)((Object)StateMachineParameterType.a) + "'"),
    h(8, "(" + (Object)((Object)StateMachineParameterType.g) + ") Cannot perform command '" + (Object)((Object)StateMachineParameterType.c) + "' while in state '" + (Object)((Object)StateMachineParameterType.d) + "'"),
    i(9, "");
    
    private int j;
    private int k;
    private String l;

    private StateMachineErrorCode(int n2, @NonNull String string3) {
        this.k = n2;
        this.l = string3;
        this.j = ErrorHelper.a("STATE_MACHINE_ERROR_CODE_OFFSET");
    }

    @Override
    public String a() {
        return this.l;
    }
}

