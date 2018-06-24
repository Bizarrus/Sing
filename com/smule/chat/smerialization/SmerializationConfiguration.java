/*
 * Decompiled with CFR 0_123.
 */
package com.smule.chat.smerialization;

import java.util.HashMap;
import java.util.Map;

public class SmerializationConfiguration {
    public Map<String, Class> a = new HashMap<String, Class>();
    public Map<Class, String> b = new HashMap<Class, String>();

    public void a(String string2, Class class_) {
        this.a.put(string2, class_);
        this.b.put(class_, string2);
    }
}

