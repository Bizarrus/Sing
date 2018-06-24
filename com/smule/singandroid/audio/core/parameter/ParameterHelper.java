/*
 * Decompiled with CFR 0_123.
 */
package com.smule.singandroid.audio.core.parameter;

import com.smule.singandroid.audio.core.parameter.KeyedParameter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class ParameterHelper {
    public static HashMap<String, Object> a(Collection<KeyedParameter> object) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        if (object != null) {
            object = object.iterator();
            while (object.hasNext()) {
                KeyedParameter keyedParameter = (KeyedParameter)object.next();
                hashMap.put(keyedParameter.a(), keyedParameter.b());
            }
        }
        return hashMap;
    }
}

