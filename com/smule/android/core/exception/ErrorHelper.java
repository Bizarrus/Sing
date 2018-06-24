/*
 * Decompiled with CFR 0_123.
 */
package com.smule.android.core.exception;

import com.smule.android.core.exception.ExceptionParameterType;
import com.smule.android.core.exception.IError;
import com.smule.android.core.exception.SmuleException;
import com.smule.android.core.logger.TagLogger;
import com.smule.android.core.parameter.KeyedParameter;
import java.util.HashMap;
import java.util.Map;

public class ErrorHelper {
    private static TagLogger a = new TagLogger(ErrorHelper.class.getSimpleName());

    public static int a(String string2) {
        try {
            ErrorHelper.b(string2);
            boolean bl = string2.equals("STATE_MACHINE_ERROR_CODE_OFFSET");
            if (bl) {
                return 100;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return 0;
    }

    public static /* varargs */ void a(IError iError, KeyedParameter ... arrkeyedParameter) throws SmuleException {
        ErrorHelper.a(new SmuleException(iError, arrkeyedParameter));
    }

    public static void a(SmuleException smuleException) throws SmuleException {
        if (smuleException.c == null) {
            smuleException.c = new HashMap<String, Object>();
        }
        smuleException.c.put(ExceptionParameterType.a.toString(), smuleException.getStackTrace());
        a.c(smuleException.b());
        throw smuleException;
    }

    private static void b(String string2) throws Exception {
        if (string2 == null || string2.equals("")) {
            throw new Exception("Invalid key for error code offset");
        }
    }
}

