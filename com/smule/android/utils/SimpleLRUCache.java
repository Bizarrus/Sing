package com.smule.android.utils;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class SimpleLRUCache<K, V> extends LinkedHashMap<K, V> {
    private int f17843a;

    public SimpleLRUCache(int i) {
        super(1, 0.75f, true);
        this.f17843a = i;
    }

    protected boolean removeEldestEntry(Entry<K, V> entry) {
        return size() > this.f17843a;
    }
}
