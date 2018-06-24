/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.SystemClock
 */
package com.smule.android.utils;

import android.os.SystemClock;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class TimeExpiringLruCache<K, V> {
    private final LinkedHashMap<K, V> a;
    private final HashMap<K, Long> b;
    private int c;
    private final int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private final int j;

    public TimeExpiringLruCache(int n, int n2) {
        if (n <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.d = n;
        this.j = n2;
        this.a = new LinkedHashMap(0, 0.75f, true);
        this.b = new HashMap(0, 0.75f);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void a(int n) {
        do {
            K k;
            Map.Entry entry2;
            synchronized (this) {
                if (this.c < 0 || this.a.isEmpty() && this.c != 0) {
                    throw new IllegalStateException(this.getClass().getName() + ".sizeOf() is reporting inconsistent results!");
                }
                if (this.c <= n) {
                    return;
                }
                Map.Entry entry2 = this.a.entrySet().iterator().next();
                if (entry2 == null) {
                    return;
                }
                k = entry2.getKey();
                entry2 = entry2.getValue();
                this.a.remove(k);
                this.c -= this.c(k, entry2);
                ++this.g;
            }
            this.a(true, k, entry2, null);
        } while (true);
    }

    private int c(K k, V v) {
        int n = this.b(k, v);
        if (n < 0) {
            throw new IllegalStateException("Negative size: " + k + "=" + v);
        }
        return n;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public final V a(K k) {
        if (k == null) {
            throw new NullPointerException("key == null");
        }
        // MONITORENTER : this
        V v = this.a.get(k);
        if (v != null) {
            boolean bl = this.b.get(k) > SystemClock.uptimeMillis();
            if (bl) {
                ++this.h;
                // MONITOREXIT : this
                return v;
            }
            this.b(k);
        }
        ++this.i;
        // MONITOREXIT : this
        v = this.c(k);
        if (v == null) {
            return null;
        }
        // MONITORENTER : this
        ++this.f;
        V v2 = this.a.put(k, v);
        Long l = this.b.put(k, SystemClock.uptimeMillis() + (long)this.j);
        if (v2 != null) {
            this.a.put(k, v2);
            this.b.put(k, l);
        } else {
            this.c += this.c(k, v);
        }
        // MONITOREXIT : this
        if (v2 != null) {
            this.a(false, k, v, v2);
            return v2;
        }
        this.a(this.d);
        return v;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public final V a(K k, V v) {
        if (k == null) throw new NullPointerException("key == null || value == null");
        if (v == null) {
            throw new NullPointerException("key == null || value == null");
        }
        // MONITORENTER : this
        ++this.e;
        this.c += this.c(k, v);
        V v2 = this.a.put(k, v);
        if (v2 != null) {
            this.c -= this.c(k, v2);
        }
        this.b.put(k, SystemClock.uptimeMillis() + (long)this.j);
        // MONITOREXIT : this
        if (v2 != null) {
            this.a(false, k, v2, v);
        }
        this.a(this.d);
        return v2;
    }

    protected void a(boolean bl, K k, V v, V v2) {
    }

    protected int b(K k, V v) {
        return 1;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public final V b(K k) {
        if (k == null) {
            throw new NullPointerException("key == null");
        }
        // MONITORENTER : this
        V v = this.a.remove(k);
        if (v != null) {
            this.c -= this.c(k, v);
            this.b.remove(k);
        }
        // MONITOREXIT : this
        if (v == null) return v;
        this.a(false, k, v, null);
        return v;
    }

    protected V c(K k) {
        return null;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public final String toString() {
        int n = 0;
        synchronized (this) {
            int n2 = this.h + this.i;
            if (n2 == 0) return String.format("LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", this.d, this.h, this.i, n);
            n = this.h * 100 / n2;
            return String.format("LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", this.d, this.h, this.i, n);
        }
    }
}

