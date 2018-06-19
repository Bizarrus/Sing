package com.smule.android.core.parameter;

import java.util.Map;
import java.util.regex.Pattern;

public class MessageParameterHandler {
    public static String m17596a(String str, Map<String, Object> map) {
        if (str == null || str.equals("")) {
            return "";
        }
        if (map == null || map.size() == 0) {
            return str;
        }
        for (String str2 : map.keySet()) {
            String str3 = "(no parameter value)";
            Object obj = map.get(str2);
            if (obj != null) {
                str3 = obj.toString();
            }
            if (str.contains(str2)) {
                str = str.replaceAll(Pattern.quote(str2), str3);
            }
        }
        return str;
    }
}
