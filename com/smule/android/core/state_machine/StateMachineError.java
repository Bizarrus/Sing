package com.smule.android.core.state_machine;

import com.smule.android.core.exception.ErrorHelper;
import com.smule.android.core.exception.IError;

public enum StateMachineError implements IError {
    INVALID_CLASS(1, "(" + StateMachineParameterType.NAME + ") An invalid class was provided"),
    INVALID_STATE(2, "(" + StateMachineParameterType.NAME + ") An invalid state was provided"),
    INVALID_TAG(3, "(" + StateMachineParameterType.NAME + ") An invalid tag was provided"),
    INVALID_COMMAND(4, "(" + StateMachineParameterType.NAME + ") An invalid command was provided"),
    INVALID_ERROR(5, "(" + StateMachineParameterType.NAME + ") An invalid error code was provided"),
    DUPLICATE_STATE_TRANSITION_DEFINITION(6, "(" + StateMachineParameterType.NAME + ") Duplicate state transition definition for Key/Output: '" + StateMachineParameterType.KEY + " / " + StateMachineParameterType.OUTPUT + "'"),
    NO_STATE_TRANSITION_FOR_KEY(7, "(" + StateMachineParameterType.NAME + ") No state transition defined for Key: '" + StateMachineParameterType.KEY + "'"),
    COMMAND_NOT_ALLOWED_IN_CURRENT_STATE(8, "(" + StateMachineParameterType.NAME + ") Cannot perform command '" + StateMachineParameterType.COMMAND + "' while in state '" + StateMachineParameterType.STATE + "'"),
    ANY(9, "");
    
    private int f15842j;
    private int f15843k;
    private String f15844l;

    private StateMachineError(int i, String str) {
        this.f15843k = i;
        this.f15844l = str;
        this.f15842j = ErrorHelper.m17582a("STATE_MACHINE_ERROR_CODE_OFFSET");
    }

    public String mo6239a() {
        return this.f15844l;
    }
}
