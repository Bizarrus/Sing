/*
 * Decompiled with CFR 0_123.
 */
package com.smule.android.core.parameter;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class MessageParameterHandler {
    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String a(String string2, Map<String, Object> map) {
        if (string2 == null) return "";
        if (string2.equals("")) {
            return "";
        }
        String string3 = string2;
        if (map == null) return string3;
        string3 = string2;
        if (map.size() == 0) return string3;
        Iterator<String> iterator = map.keySet().iterator();
        do {
            string3 = string2;
            if (!iterator.hasNext()) return string3;
            String string4 = iterator.next();
            string3 = "(no parameter value)";
            Object object = map.get(string4);
            if (object != null) {
                string3 = object.toString();
            }
            if (!string2.contains(string4)) continue;
            string2 = string2.replaceAll(Pattern.quote(string4), string3);
        } while (true);
    }
}

