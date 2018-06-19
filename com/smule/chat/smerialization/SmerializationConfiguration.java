package com.smule.chat.smerialization;

import java.util.HashMap;
import java.util.Map;

public class SmerializationConfiguration {
    public Map<String, Class> f18373a = new HashMap();
    public Map<Class, String> f18374b = new HashMap();

    public void m19764a(String str, Class cls) {
        this.f18373a.put(str, cls);
        this.f18374b.put(cls, str);
    }
}
