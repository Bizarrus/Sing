package com.smule.android.core.exception;

import com.smule.android.core.parameter.KeyedParameter;
import com.smule.android.core.parameter.MessageParameterHandler;
import com.smule.android.core.parameter.ParameterHelper;
import java.util.Map;

public class SmuleException extends Exception {
    protected IError f15795a;
    protected String f15796b;
    protected Map<String, Object> f15797c;

    public SmuleException(IError iError, KeyedParameter... keyedParameterArr) {
        this(iError, ParameterHelper.m17599a(keyedParameterArr));
    }

    public SmuleException(IError iError, Map<String, Object> map) {
        this.f15795a = iError;
        this.f15797c = map;
        m17587c();
    }

    public IError m17588a() {
        return this.f15795a;
    }

    public String m17589b() {
        return this.f15796b;
    }

    private void m17587c() {
        if (this.f15795a != null) {
            this.f15796b = MessageParameterHandler.m17596a(this.f15795a.mo6239a(), this.f15797c);
        }
    }

    public String getMessage() {
        return this.f15796b + "\n" + super.getMessage();
    }
}
