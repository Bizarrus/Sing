package com.smule.android.core.wait;

import com.smule.android.core.exception.ErrorHelper;
import com.smule.android.core.exception.IError;

public enum WaiterError implements IError {
    TIMEOUT(1, "Waiter timed out after '" + WaiterParameterType.TIMEOUT_MSEC + "' msec"),
    INVALID_TIMEOUT(2, "Invalid timeout specified: " + WaiterParameterType.TIMEOUT_MSEC + " msec"),
    COULD_NOT_PERFORM_WAIT(3, "Could not perform wait, reason: " + WaiterParameterType.MESSAGE);
    
    private int f15858d;
    private int f15859e;
    private String f15860f;

    private WaiterError(int i, String str) {
        this.f15859e = i;
        this.f15860f = str;
        this.f15858d = ErrorHelper.m17582a("WAITER_ERROR_CODE_OFFSET");
    }

    public String mo6239a() {
        return this.f15860f;
    }
}
