package com.smule.android.core.exception;

import com.smule.android.core.logger.TagLogger;
import com.smule.android.core.parameter.KeyedParameter;
import java.util.HashMap;

public class ErrorHelper {
    private static TagLogger f15787a = new TagLogger(ErrorHelper.class.getSimpleName());

    public static int m17582a(String str) {
        try {
            m17585b(str);
            if (str.equals("STATE_MACHINE_ERROR_CODE_OFFSET")) {
                return 100;
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public static void m17583a(IError iError, KeyedParameter... keyedParameterArr) throws SmuleException {
        m17584a(new SmuleException(iError, keyedParameterArr));
    }

    public static void m17584a(SmuleException smuleException) throws SmuleException {
        if (smuleException.f15797c == null) {
            smuleException.f15797c = new HashMap();
        }
        smuleException.f15797c.put(ExceptionParameterType.STACKTRACE.toString(), smuleException.getStackTrace());
        f15787a.m17593c(smuleException.m17589b());
        throw smuleException;
    }

    private static void m17585b(String str) throws Exception {
        if (str == null || str.equals("")) {
            throw new Exception("Invalid key for error code offset");
        }
    }
}
