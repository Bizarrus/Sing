/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.support.annotation.NonNull
 *  android.support.annotation.Nullable
 */
package com.smule.singandroid.audio.core.exception;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.smule.singandroid.audio.core.exception.IError;
import com.smule.singandroid.audio.core.state_machine.ICommand;
import com.smule.singandroid.audio.core.state_machine.IState;

public class StateMachineError
extends Error {
    @NonNull
    private Class a;
    @NonNull
    private IError b;
    @Nullable
    private IState c;
    @Nullable
    private ICommand d;

    /*
     * Enabled aggressive block sorting
     */
    StateMachineError(@NonNull Class class_, @NonNull IError iError, @Nullable IState iState, @Nullable ICommand iCommand, @Nullable Throwable throwable) {
        StringBuilder stringBuilder = new StringBuilder().append("Error from state machine ").append(class_.getSimpleName());
        String string2 = iState == null ? "" : " in state " + iState;
        stringBuilder = stringBuilder.append(string2);
        string2 = iCommand == null ? "" : " while executing command " + iCommand;
        super(stringBuilder.append(string2).append(": ").append(iError.a()).toString(), throwable);
        this.a = class_;
        this.d = iCommand;
        this.c = iState;
        this.b = iError;
    }

    public StateMachineError(@NonNull Class class_, String string2, Throwable throwable, @NonNull IError iError, @Nullable IState iState, @Nullable ICommand iCommand) {
        super(string2, throwable);
        this.a = class_;
        this.b = iError;
        this.c = iState;
        this.d = iCommand;
    }
}

