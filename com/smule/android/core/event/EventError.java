package com.smule.android.core.event;

import com.smule.android.core.exception.ErrorHelper;
import com.smule.android.core.exception.IError;

public enum EventError implements IError {
    INVALID_LISTENER(1, "Specified listener is invalid."),
    INVALID_EVENT_TYPE(2, "Specified event type is invalid."),
    INVALID_FILTER(3, "Specified event filter is invalid."),
    EVENT_TYPE_NOT_DEFINED(4, "Specified event type '" + EventParameterType.EVENT_TYPE + "' is not defined as available");
    
    private int f15781e;
    private int f15782f;
    private String f15783g;

    private EventError(int i, String str) {
        this.f15782f = i;
        this.f15783g = str;
        this.f15781e = ErrorHelper.m17582a("EVENT_ERROR_CODE_OFFSET");
    }

    public String mo6239a() {
        return this.f15783g;
    }
}
