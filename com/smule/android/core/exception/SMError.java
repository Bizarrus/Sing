package com.smule.android.core.exception;

public enum SMError implements IError {
    NO_ERROR(0, "No error");
    
    private int f15792b;
    private int f15793c;
    private String f15794d;

    private SMError(int i, String str) {
        this.f15793c = i;
        this.f15794d = str;
        this.f15792b = ErrorHelper.m17582a("ERROR_CODE_OFFSET");
    }

    public String mo6239a() {
        return this.f15794d;
    }
}
