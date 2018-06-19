package com.smule.android.utils;

import android.os.SystemClock;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class TimeExpiringLruCache<K, V> {
    private final LinkedHashMap<K, V> f17858a;
    private final HashMap<K, Long> f17859b;
    private int f17860c;
    private final int f17861d;
    private int f17862e;
    private int f17863f;
    private int f17864g;
    private int f17865h;
    private int f17866i;
    private final int f17867j;

    public TimeExpiringLruCache(int i, int i2) {
        if (i <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.f17861d = i;
        this.f17867j = i2;
        this.f17858a = new LinkedHashMap(0, 0.75f, true);
        this.f17859b = new HashMap(0, 0.75f);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final V m19061a(K r9) {
        /*
        r8 = this;
        r3 = 0;
        if (r9 != 0) goto L_0x000b;
    L_0x0003:
        r0 = new java.lang.NullPointerException;
        r1 = "key == null";
        r0.<init>(r1);
        throw r0;
    L_0x000b:
        monitor-enter(r8);
        r0 = r8.f17858a;	 Catch:{ all -> 0x0048 }
        r1 = r0.get(r9);	 Catch:{ all -> 0x0048 }
        if (r1 == 0) goto L_0x0039;
    L_0x0014:
        r0 = r8.f17859b;	 Catch:{ all -> 0x0048 }
        r0 = r0.get(r9);	 Catch:{ all -> 0x0048 }
        r0 = (java.lang.Long) r0;	 Catch:{ all -> 0x0048 }
        r4 = r0.longValue();	 Catch:{ all -> 0x0048 }
        r6 = android.os.SystemClock.uptimeMillis();	 Catch:{ all -> 0x0048 }
        r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r0 <= 0) goto L_0x0034;
    L_0x0028:
        r0 = 1;
    L_0x0029:
        if (r0 == 0) goto L_0x0036;
    L_0x002b:
        r0 = r8.f17865h;	 Catch:{ all -> 0x0048 }
        r0 = r0 + 1;
        r8.f17865h = r0;	 Catch:{ all -> 0x0048 }
        monitor-exit(r8);	 Catch:{ all -> 0x0048 }
        r0 = r1;
    L_0x0033:
        return r0;
    L_0x0034:
        r0 = r3;
        goto L_0x0029;
    L_0x0036:
        r8.m19065b(r9);	 Catch:{ all -> 0x0048 }
    L_0x0039:
        r0 = r8.f17866i;	 Catch:{ all -> 0x0048 }
        r0 = r0 + 1;
        r8.f17866i = r0;	 Catch:{ all -> 0x0048 }
        monitor-exit(r8);	 Catch:{ all -> 0x0048 }
        r2 = r8.m19066c(r9);
        if (r2 != 0) goto L_0x004b;
    L_0x0046:
        r0 = 0;
        goto L_0x0033;
    L_0x0048:
        r0 = move-exception;
        monitor-exit(r8);	 Catch:{ all -> 0x0048 }
        throw r0;
    L_0x004b:
        monitor-enter(r8);
        r0 = r8.f17863f;	 Catch:{ all -> 0x008a }
        r0 = r0 + 1;
        r8.f17863f = r0;	 Catch:{ all -> 0x008a }
        r0 = r8.f17858a;	 Catch:{ all -> 0x008a }
        r1 = r0.put(r9, r2);	 Catch:{ all -> 0x008a }
        r0 = r8.f17859b;	 Catch:{ all -> 0x008a }
        r4 = android.os.SystemClock.uptimeMillis();	 Catch:{ all -> 0x008a }
        r6 = r8.f17867j;	 Catch:{ all -> 0x008a }
        r6 = (long) r6;	 Catch:{ all -> 0x008a }
        r4 = r4 + r6;
        r4 = java.lang.Long.valueOf(r4);	 Catch:{ all -> 0x008a }
        r0 = r0.put(r9, r4);	 Catch:{ all -> 0x008a }
        r0 = (java.lang.Long) r0;	 Catch:{ all -> 0x008a }
        if (r1 == 0) goto L_0x0080;
    L_0x006e:
        r4 = r8.f17858a;	 Catch:{ all -> 0x008a }
        r4.put(r9, r1);	 Catch:{ all -> 0x008a }
        r4 = r8.f17859b;	 Catch:{ all -> 0x008a }
        r4.put(r9, r0);	 Catch:{ all -> 0x008a }
    L_0x0078:
        monitor-exit(r8);	 Catch:{ all -> 0x008a }
        if (r1 == 0) goto L_0x008d;
    L_0x007b:
        r8.m19063a(r3, r9, r2, r1);
        r0 = r1;
        goto L_0x0033;
    L_0x0080:
        r0 = r8.f17860c;	 Catch:{ all -> 0x008a }
        r4 = r8.m19060c(r9, r2);	 Catch:{ all -> 0x008a }
        r0 = r0 + r4;
        r8.f17860c = r0;	 Catch:{ all -> 0x008a }
        goto L_0x0078;
    L_0x008a:
        r0 = move-exception;
        monitor-exit(r8);	 Catch:{ all -> 0x008a }
        throw r0;
    L_0x008d:
        r0 = r8.f17861d;
        r8.m19059a(r0);
        r0 = r2;
        goto L_0x0033;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.smule.android.utils.TimeExpiringLruCache.a(java.lang.Object):V");
    }

    public final V m19062a(K k, V v) {
        if (k == null || v == null) {
            throw new NullPointerException("key == null || value == null");
        }
        V put;
        synchronized (this) {
            this.f17862e++;
            this.f17860c += m19060c(k, v);
            put = this.f17858a.put(k, v);
            if (put != null) {
                this.f17860c -= m19060c(k, put);
            }
            this.f17859b.put(k, Long.valueOf(SystemClock.uptimeMillis() + ((long) this.f17867j)));
        }
        if (put != null) {
            m19063a(false, k, put, v);
        }
        m19059a(this.f17861d);
        return put;
    }

    private void m19059a(int i) {
        while (true) {
            Object key;
            Object value;
            synchronized (this) {
                if (this.f17860c >= 0 && (!this.f17858a.isEmpty() || this.f17860c == 0)) {
                    if (this.f17860c <= i) {
                        return;
                    }
                    Entry entry = (Entry) this.f17858a.entrySet().iterator().next();
                    if (entry == null) {
                        return;
                    }
                    key = entry.getKey();
                    value = entry.getValue();
                    this.f17858a.remove(key);
                    this.f17860c -= m19060c(key, value);
                    this.f17864g++;
                }
            }
            m19063a(true, key, value, null);
        }
        throw new IllegalStateException(getClass().getName() + ".sizeOf() is reporting inconsistent results!");
    }

    public final V m19065b(K k) {
        if (k == null) {
            throw new NullPointerException("key == null");
        }
        V remove;
        synchronized (this) {
            remove = this.f17858a.remove(k);
            if (remove != null) {
                this.f17860c -= m19060c(k, remove);
                this.f17859b.remove(k);
            }
        }
        if (remove != null) {
            m19063a(false, k, remove, null);
        }
        return remove;
    }

    protected void m19063a(boolean z, K k, V v, V v2) {
    }

    protected V m19066c(K k) {
        return null;
    }

    private int m19060c(K k, V v) {
        int b = m19064b(k, v);
        if (b >= 0) {
            return b;
        }
        throw new IllegalStateException("Negative size: " + k + "=" + v);
    }

    protected int m19064b(K k, V v) {
        return 1;
    }

    public final synchronized String toString() {
        String format;
        int i = 0;
        synchronized (this) {
            int i2 = this.f17865h + this.f17866i;
            if (i2 != 0) {
                i = (this.f17865h * 100) / i2;
            }
            format = String.format("LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", new Object[]{Integer.valueOf(this.f17861d), Integer.valueOf(this.f17865h), Integer.valueOf(this.f17866i), Integer.valueOf(i)});
        }
        return format;
    }
}
