package com.smule.android.core.parameter;

import com.smule.android.core.exception.ErrorHelper;
import com.smule.android.core.exception.IError;

public enum ParameterError implements IError {
    INVALID_TYPE(1, "Invalid type provided"),
    INVALID_KEY(2, "Invalid key provided"),
    INVALID_PARAMETER(3, "Invalid mapped parameter provided"),
    INVALID_PARAMETERS(4, "Invalid mapped parameters provided"),
    DUPLICATE_PARAMETER(5, "Attempted to store duplicate parameter '" + ParameterType.PARAMETER + "'"),
    COULD_NOT_FIND_PARAMETER(6, "Could not find parameter '" + ParameterType.PARAMETER + "'"),
    COULD_NOT_UPDATE_IMMUTABLE_PROPERTY(7, "Attempted to update an immutable property");
    
    private int f15816h;
    private int f15817i;
    private String f15818j;

    private ParameterError(int i, String str) {
        this.f15817i = i;
        this.f15818j = str;
        this.f15816h = ErrorHelper.m17582a("PARAMETER_ERROR_CODE_OFFSET");
    }

    public String mo6239a() {
        return this.f15818j;
    }
}
