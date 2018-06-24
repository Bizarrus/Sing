/*
 * Decompiled with CFR 0_123.
 */
package com.smule.android.utils;

import com.smule.android.logging.Log;
import com.smule.android.utils.ReferenceMonitor;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.WeakHashMap;

public class ReferenceMonitor {
    public static final String a = ReferenceMonitor.class.getName();
    private static ReferenceMonitor d;
    private WeakHashMap<Object, > b = new WeakHashMap();
    private HashMap<String, Integer> c = new HashMap();
    private final Runnable e;

    private ReferenceMonitor() {
        this.e = new Runnable(this){
            final /* synthetic */ ReferenceMonitor a;
            {
                this.a = referenceMonitor;
            }

            public void run() {
                ReferenceMonitor.b(this.a);
            }
        };
    }

    public static ReferenceMonitor a() {
        if (d == null) {
            d = new ReferenceMonitor();
        }
        return d;
    }

    static /* synthetic */ void b(ReferenceMonitor referenceMonitor) {
        referenceMonitor.c();
    }

    private void c() {
        Log.a(a, this.a(true));
    }

    /*
     * Enabled aggressive block sorting
     */
    public String a(boolean bl) {
        HashMap<Object, Integer> hashMap = new HashMap<Object, Integer>();
        StringBuilder stringBuilder = bl ? new StringBuilder("Instances:") : null;
        Object object = this.b.values().iterator();
        HashSet<Object> hashSet = null;
        while (object.hasNext()) {
            Object object2;
            Object object3 = object.next();
            if (bl) {
                stringBuilder.append("\n  ");
                stringBuilder.append(object3);
            }
            int n = hashMap.containsKey(object2 = (object3).b) ? (Integer)hashMap.get(object2) + 1 : 1;
            hashMap.put(object2, n);
            if (!bl || n < (object3).e) continue;
            if (hashSet == null) {
                hashSet = new HashSet<Object>();
            }
            hashSet.add(object2);
        }
        object = new StringBuilder("Counts:");
        for (Object object3 : hashMap.keySet()) {
            if (bl) {
                object.append("\n  ").append((String)object3).append(": ").append(hashMap.get(object3));
                if (hashSet == null || !hashSet.contains(object3)) continue;
                object.append(" WARNING INSTANCE COUNT IS HIGH. POSSIBLE MEMORY LEAK");
                continue;
            }
            object.append((String)object3).append("(").append(hashMap.get(object3)).append(") ");
        }
        if (hashSet != null) {
            return object.toString() + "\n" + stringBuilder.toString();
        }
        return object.toString();
    }

    public void a(Object object) {
        this.a(object, 5);
    }

    public void a(Object object, int n) {
        if (this.b.get(object) != null) {
            return;
        }
        Object object2 = new Object(object, n){
            private String b;
            private Date c;
            private int d;
            private int e;
            private final SimpleDateFormat f;
            {
                this.f = new SimpleDateFormat("HH:mm:ss.SSS", Locale.US);
                this.b = object.getClass().getSimpleName();
                int n2 = ReferenceMonitor.this.c.containsKey(this.b) ? (Integer)ReferenceMonitor.this.c.get(this.b) : 0;
                this.d = n2 + 1;
                ReferenceMonitor.this.c.put(this.b, this.d);
                this.e = n;
                this.c = new Date();
            }

            public String toString() {
                return this.b + " instance: " + this.d + " created: " + this.f.format(this.c);
            }
        };
        this.b.put(object, object2);
    }

    public String b() {
        return this.a(false);
    }

}

