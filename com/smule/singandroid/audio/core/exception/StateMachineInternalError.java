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
import com.smule.singandroid.audio.core.exception.StateMachineError;
import com.smule.singandroid.audio.core.state_machine.ICommand;
import com.smule.singandroid.audio.core.state_machine.IState;
import com.smule.singandroid.audio.core.state_machine.StateMachine;

public class StateMachineInternalError
extends StateMachineError {
    public StateMachineInternalError(@NonNull StateMachine stateMachine, @NonNull IError iError) {
        this(stateMachine, iError, null, null);
    }

    public StateMachineInternalError(@NonNull StateMachine stateMachine, @NonNull IError iError, @Nullable ICommand iCommand, @Nullable Throwable throwable) {
        super(stateMachine.getClass(), iError, stateMachine.B(), iCommand, throwable);
    }
}

