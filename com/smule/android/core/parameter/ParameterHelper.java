/*
 * Decompiled with CFR 0_123.
 */
package com.smule.android.core.parameter;

import com.smule.android.core.parameter.KeyedParameter;
import java.util.HashMap;

public class ParameterHelper {
    public static /* varargs */ <T> T a(String string2, KeyedParameter ... arrkeyedParameter) {
        for (KeyedParameter keyedParameter : arrkeyedParameter) {
            if (!keyedParameter.a().equals(string2)) continue;
            return (T)keyedParameter.b();
        }
        return null;
    }

    public static /* varargs */ HashMap<String, Object> a(KeyedParameter ... arrkeyedParameter) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        if (arrkeyedParameter != null) {
            for (KeyedParameter keyedParameter : arrkeyedParameter) {
                hashMap.put(keyedParameter.a(), keyedParameter.b());
            }
        }
        return hashMap;
    }
}

