package com.smule.android.network.managers;

import com.fasterxml.jackson.databind.JsonNode;
import com.smule.android.logging.Log;
import com.smule.android.utils.JsonUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class LocalizationManager$LocalizationConfig {
    private Map<String, Map<String, Map<String, String>>> f16695a;

    private LocalizationManager$LocalizationConfig() {
        this.f16695a = new HashMap();
    }

    public LocalizationManager$LocalizationConfig(JsonNode jsonNode) {
        this();
        Iterator fields = jsonNode.fields();
        while (fields.hasNext()) {
            Entry entry = (Entry) fields.next();
            String str = (String) entry.getKey();
            m18249a(str.substring(0, str.indexOf(46)), str.substring(str.indexOf(46) + 1, str.lastIndexOf(46)), str.substring(str.lastIndexOf(46) + 1), ((JsonNode) entry.getValue()).textValue());
        }
    }

    public boolean m18250a() {
        return this.f16695a == null || this.f16695a.isEmpty();
    }

    public Map<String, String> m18248a(String str, String str2) {
        Map map = (Map) this.f16695a.get(str);
        if (map != null) {
            return (Map) map.get(str2);
        }
        return null;
    }

    public Map<String, Map<String, String>> m18247a(String str) {
        return (Map) this.f16695a.get(str);
    }

    void m18249a(String str, String str2, String str3, String str4) {
        if (!this.f16695a.containsKey(str)) {
            this.f16695a.put(str, new HashMap());
        }
        Map map = (Map) this.f16695a.get(str);
        if (!map.containsKey(str2)) {
            map.put(str2, new HashMap());
        }
        ((Map) map.get(str2)).put(str3, str4);
    }

    public String m18251b() {
        Map hashMap = new HashMap();
        for (Entry entry : this.f16695a.entrySet()) {
            for (Entry entry2 : ((Map) entry.getValue()).entrySet()) {
                for (Entry entry3 : ((Map) entry2.getValue()).entrySet()) {
                    hashMap.put(((String) entry.getKey()) + "." + ((String) entry2.getKey()) + "." + ((String) entry3.getKey()), entry3.getValue());
                }
            }
        }
        try {
            return JsonUtils.m18984a().writeValueAsString(hashMap);
        } catch (Throwable e) {
            Log.d(LocalizationManager.b(), "Failed to serialize config", e);
            return null;
        }
    }
}
