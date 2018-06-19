package com.smule.android.core.parameter;

import java.util.HashMap;

public class ParameterHelper {
    public static HashMap<String, Object> m17599a(KeyedParameter... keyedParameterArr) {
        HashMap<String, Object> hashMap = new HashMap();
        if (keyedParameterArr != null) {
            for (KeyedParameter keyedParameter : keyedParameterArr) {
                hashMap.put(keyedParameter.m17594a(), keyedParameter.m17595b());
            }
        }
        return hashMap;
    }

    public static <T> T m17598a(String str, KeyedParameter... keyedParameterArr) {
        for (KeyedParameter keyedParameter : keyedParameterArr) {
            if (keyedParameter.m17594a().equals(str)) {
                return keyedParameter.m17595b();
            }
        }
        return null;
    }
}
