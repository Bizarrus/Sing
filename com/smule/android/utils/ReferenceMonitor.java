package com.smule.android.utils;

import com.smule.android.logging.Log;
import java.util.HashMap;
import java.util.HashSet;
import java.util.WeakHashMap;

public class ReferenceMonitor {
    public static final String f6958a = ReferenceMonitor.class.getName();
    private static ReferenceMonitor f6959d;
    private WeakHashMap<Object, ReferenceMonitorInfo> f6960b = new WeakHashMap();
    private HashMap<String, Integer> f6961c = new HashMap();
    private final Runnable f6962e = new 1(this);

    public static ReferenceMonitor m8403a() {
        if (f6959d == null) {
            f6959d = new ReferenceMonitor();
        }
        return f6959d;
    }

    private ReferenceMonitor() {
    }

    public void m8408a(Object obj) {
        m8409a(obj, 5);
    }

    public void m8409a(Object obj, int i) {
        if (this.f6960b.get(obj) == null) {
            this.f6960b.put(obj, new ReferenceMonitorInfo(this, obj, i));
        }
    }

    public String m8410b() {
        return m8407a(false);
    }

    public String m8407a(boolean z) {
        HashMap hashMap = new HashMap();
        StringBuilder stringBuilder = z ? new StringBuilder("Instances:") : null;
        HashSet hashSet = null;
        for (ReferenceMonitorInfo referenceMonitorInfo : this.f6960b.values()) {
            if (z) {
                stringBuilder.append("\n  ");
                stringBuilder.append(referenceMonitorInfo);
            }
            String a = ReferenceMonitorInfo.a(referenceMonitorInfo);
            int intValue = hashMap.containsKey(a) ? ((Integer) hashMap.get(a)).intValue() + 1 : 1;
            hashMap.put(a, Integer.valueOf(intValue));
            if (z && intValue >= ReferenceMonitorInfo.b(referenceMonitorInfo)) {
                HashSet hashSet2;
                if (hashSet == null) {
                    hashSet2 = new HashSet();
                } else {
                    hashSet2 = hashSet;
                }
                hashSet2.add(a);
                hashSet = hashSet2;
            }
        }
        StringBuilder stringBuilder2 = new StringBuilder("Counts:");
        for (String str : hashMap.keySet()) {
            if (z) {
                stringBuilder2.append("\n  ").append(str).append(": ").append(hashMap.get(str));
                if (hashSet != null && hashSet.contains(str)) {
                    stringBuilder2.append(" WARNING INSTANCE COUNT IS HIGH. POSSIBLE MEMORY LEAK");
                }
            } else {
                stringBuilder2.append(str).append("(").append(hashMap.get(str)).append(") ");
            }
        }
        return hashSet != null ? stringBuilder2.toString() + "\n" + stringBuilder.toString() : stringBuilder2.toString();
    }

    private void m8406c() {
        Log.m7767a(f6958a, m8407a(true));
    }
}
