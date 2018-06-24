/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.support.annotation.NonNull
 */
package com.smule.singandroid.audio.core.exception;

import android.support.annotation.NonNull;
import com.smule.singandroid.audio.core.exception.IError;
import com.smule.singandroid.audio.core.exception.StateMachineError;
import com.smule.singandroid.audio.core.parameter.KeyedParameter;
import com.smule.singandroid.audio.core.parameter.MessageParameterHandler;
import com.smule.singandroid.audio.core.state_machine.ICommand;
import com.smule.singandroid.audio.core.state_machine.IState;
import com.smule.singandroid.audio.core.state_machine.StateMachineErrorCode;
import com.smule.singandroid.audio.core.state_machine.StateMachineParameterType;
import java.util.Arrays;

public class StateMachineConfigurationError
extends StateMachineError {
    private String a;

    public StateMachineConfigurationError(@NonNull StateMachineErrorCode stateMachineErrorCode, @NonNull Class class_, @NonNull String string2, @NonNull IState iState) {
        super(class_, MessageParameterHandler.a(stateMachineErrorCode.a(), Arrays.asList(new KeyedParameter(StateMachineParameterType.g.toString(), class_.getSimpleName()), new KeyedParameter(StateMachineParameterType.a.toString(), string2), new KeyedParameter(StateMachineParameterType.f.toString(), iState))), null, stateMachineErrorCode, iState, null);
        this.a = string2;
    }
}

