/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.support.annotation.NonNull
 *  android.util.Pair
 */
package com.smule.singandroid.audio.core.exception;

import android.support.annotation.NonNull;
import android.util.Pair;
import com.smule.singandroid.audio.core.exception.StateMachineException;
import com.smule.singandroid.audio.core.state_machine.ICommand;
import com.smule.singandroid.audio.core.state_machine.IState;
import com.smule.singandroid.audio.core.state_machine.StateMachineErrorCode;
import com.smule.singandroid.audio.core.state_machine.StateMachineParameterType;

public class StateMachineTransitionException
extends StateMachineException {
    private ICommand d;
    private IState e;
    private String f;

    public StateMachineTransitionException(@NonNull StateMachineErrorCode stateMachineErrorCode, @NonNull ICommand iCommand, @NonNull IState iState) {
        super(stateMachineErrorCode);
        this.d = iCommand;
        this.e = iState;
    }

    public StateMachineTransitionException(@NonNull StateMachineErrorCode stateMachineErrorCode, @NonNull String string2) {
        super(stateMachineErrorCode);
        this.f = string2;
    }

    @Override
    public String getMessage() {
        if (this.f != null) {
            return this.a(StateMachineTransitionException.a(StateMachineParameterType.a, this.f));
        }
        return this.a(StateMachineTransitionException.a(StateMachineParameterType.c, this.d), StateMachineTransitionException.a(StateMachineParameterType.d, this.e));
    }
}

