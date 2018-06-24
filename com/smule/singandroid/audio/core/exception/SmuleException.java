/*
 * Decompiled with CFR 0_123.
 */
package com.smule.singandroid.audio.core.exception;

import com.smule.singandroid.audio.core.exception.IError;
import com.smule.singandroid.audio.core.parameter.MessageParameterHandler;
import com.smule.singandroid.audio.core.parameter.ParameterHelper;
import java.util.Map;

public class SmuleException
extends Exception {
    protected IError a;
    protected String b;
    protected Map<String, Object> c;

    public SmuleException(IError iError) {
        this(iError, ParameterHelper.a(null));
    }

    public SmuleException(IError iError, Map<String, Object> map) {
        this.a = iError;
        this.c = map;
        this.b();
    }

    private void b() {
        if (this.a == null) {
            return;
        }
        this.b = MessageParameterHandler.a(this.a.a(), this.c);
    }

    public IError a() {
        return this.a;
    }

    @Override
    public String getMessage() {
        return this.b + "\n" + super.getMessage();
    }
}

