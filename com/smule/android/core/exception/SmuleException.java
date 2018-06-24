/*
 * Decompiled with CFR 0_123.
 */
package com.smule.android.core.exception;

import com.smule.android.core.exception.IError;
import com.smule.android.core.parameter.KeyedParameter;
import com.smule.android.core.parameter.MessageParameterHandler;
import com.smule.android.core.parameter.ParameterHelper;
import java.util.Map;

public class SmuleException
extends Exception {
    protected IError a;
    protected String b;
    protected Map<String, Object> c;

    public SmuleException(IError iError, Map<String, Object> map) {
        this.a = iError;
        this.c = map;
        this.d();
    }

    public /* varargs */ SmuleException(IError iError, KeyedParameter ... arrkeyedParameter) {
        this(iError, ParameterHelper.a(arrkeyedParameter));
    }

    private void d() {
        if (this.a == null) {
            return;
        }
        this.b = MessageParameterHandler.a(this.a.a(), this.c);
    }

    public IError a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public Map<String, Object> c() {
        return this.c;
    }

    @Override
    public String getMessage() {
        return this.b + "\n" + super.getMessage();
    }
}

