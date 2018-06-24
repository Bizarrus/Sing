/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.text.TextUtils
 */
package com.smule.android.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.smule.android.logging.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class StringCacheManager {
    private static final String a = StringCacheManager.class.getName();
    private static StringCacheManager b = null;
    private Context c;
    private HashMap<String, LinkedHashSet<String>> d = new HashMap();
    private HashMap<String, Integer> e = new HashMap();
    private final boolean f = false;

    private StringCacheManager() {
        this.e.put("loved_perf_keys", 500);
        this.e.put("rated_arrangement_keys", 1000);
        this.e.put("all_followers_invited_to_perf_keys", 500);
    }

    public static StringCacheManager a() {
        if (b == null) {
            b = new StringCacheManager();
        }
        return b;
    }

    private boolean a(String string2, String string3) {
        return this.d.get(string2).contains(string3);
    }

    private void b(String string2, String string3) {
        if (this.f) {
            Log.a(a, "addToCache - " + string2 + " - inserting entry, " + string3 + " into the cache.");
        }
        this.d.get(string2).add(string3);
        this.g(string2);
        this.h(string2);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void g(String string2) {
        HashSet hashSet = this.d.get(string2);
        int n = this.e.get(string2);
        if (hashSet.size() > n) {
            ArrayList arrayList = new ArrayList(hashSet);
            while (arrayList.size() > n - 20) {
                if (this.f) {
                    Log.a(a, "pruneCache - " + string2 + " - pruning entry for string: " + (String)arrayList.get(0));
                }
                arrayList.remove(0);
            }
            this.d.put(string2, new LinkedHashSet(arrayList));
            if (!this.f) return;
            {
                Log.a(a, "pruneCache - " + string2 + " - size of set is now: " + arrayList.size());
                return;
            }
        } else {
            if (!this.f) return;
            {
                Log.a(a, "pruneCache - " + string2 + " - size of set is smaller than max allowed entries; not pruning any.");
                return;
            }
        }
    }

    private void h(String string2) {
        if (this.c == null) {
            Log.e(a, "saveCache - " + string2 + " - mContext is null; aborting save operation!");
            return;
        }
        if (this.f) {
            Log.a(a, "saveCache - " + string2 + " begin");
        }
        String string3 = TextUtils.join((CharSequence)",", (Iterable)this.d.get(string2));
        this.c.getSharedPreferences("CACHE_FILE", 0).edit().putString(string2, string3).apply();
        Log.c(a, "saveCache - end");
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void i(String string2) {
        synchronized (this) {
            if (this.c == null) {
                Log.e(a, "loadCacheFromPrefs - " + string2 + " - mContext is null; aborting load operation!");
            } else {
                String string3 = this.c.getSharedPreferences("CACHE_FILE", 0).getString(string2, "");
                if (this.d.containsKey(string2)) {
                    this.d.get(string2).clear();
                } else {
                    this.d.put(string2, new LinkedHashSet());
                }
                this.d.get(string2).addAll(Arrays.asList(TextUtils.split((String)string3, (String)",")));
                if (this.f) {
                    Log.a(a, "loadEntriesForKey - " + string2 + " - done loading keys. Current size of set is: " + this.d.get(string2).size());
                }
                this.g(string2);
            }
            return;
        }
    }

    public void a(Context context) {
        this.c = context;
        this.i("loved_perf_keys");
        this.i("rated_arrangement_keys");
        this.i("all_followers_invited_to_perf_keys");
    }

    public void a(String string2) {
        if (this.f) {
            Log.a(a, "lovedPerformance - inserting performanceKey, " + string2 + " into the cache.");
        }
        this.b("loved_perf_keys", string2);
        this.g("loved_perf_keys");
        this.h("loved_perf_keys");
    }

    public boolean b(String string2) {
        boolean bl = this.a("loved_perf_keys", string2);
        if (this.f) {
            Log.a(a, "hasLovedPerformance - for performanceKey, " + string2 + " user has loved: " + bl);
        }
        return bl;
    }

    public void c(String string2) {
        if (this.f) {
            Log.a(a, "ratedArrangement - inserting arrangementKey, " + string2 + " into the cache.");
        }
        this.b("rated_arrangement_keys", string2);
        this.g("rated_arrangement_keys");
        this.h("rated_arrangement_keys");
    }

    public boolean d(String string2) {
        boolean bl = this.a("rated_arrangement_keys", string2);
        if (this.f) {
            Log.a(a, "hasRatedArrangement - for arrangementKey, " + string2 + " user has rated: " + bl);
        }
        return bl;
    }

    public void e(String string2) {
        this.b("all_followers_invited_to_perf_keys", string2);
        this.g("all_followers_invited_to_perf_keys");
        this.h("all_followers_invited_to_perf_keys");
    }

    public boolean f(String string2) {
        return this.a("all_followers_invited_to_perf_keys", string2);
    }
}

