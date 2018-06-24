/*
 * Decompiled with CFR 0_123.
 */
package com.smule.android.utils;

import java.util.LinkedHashMap;
import java.util.Map;

public class SimpleLRUCache<K, V>
extends LinkedHashMap<K, V> {
    private int a;

    public SimpleLRUCache(int n) {
        super(1, 0.75f, true);
        this.a = n;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> entry) {
        if (this.size() > this.a) {
            return true;
        }
        return false;
    }
}

