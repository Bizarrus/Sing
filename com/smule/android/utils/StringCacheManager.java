package com.smule.android.utils;

import android.content.Context;
import android.text.TextUtils;
import com.smule.android.logging.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import twitter4j.HttpResponseCode;

public class StringCacheManager {
    private static final String f6968a = StringCacheManager.class.getName();
    private static StringCacheManager f6969b = null;
    private Context f6970c;
    private HashMap<String, LinkedHashSet<String>> f6971d = new HashMap();
    private HashMap<String, Integer> f6972e = new HashMap();
    private final boolean f6973f = false;

    private StringCacheManager() {
        this.f6972e.put("loved_perf_keys", Integer.valueOf(HttpResponseCode.INTERNAL_SERVER_ERROR));
        this.f6972e.put("rated_arrangement_keys", Integer.valueOf(1000));
        this.f6972e.put("all_followers_invited_to_perf_keys", Integer.valueOf(HttpResponseCode.INTERNAL_SERVER_ERROR));
    }

    public static StringCacheManager m8413a() {
        if (f6969b == null) {
            f6969b = new StringCacheManager();
        }
        return f6969b;
    }

    public void m8419a(Context context) {
        this.f6970c = context;
        m8418i("loved_perf_keys");
        m8418i("rated_arrangement_keys");
        m8418i("all_followers_invited_to_perf_keys");
    }

    public void m8420a(String str) {
        if (this.f6973f) {
            Log.m7767a(f6968a, "lovedPerformance - inserting performanceKey, " + str + " into the cache.");
        }
        m8415b("loved_perf_keys", str);
        m8416g("loved_perf_keys");
        m8417h("loved_perf_keys");
    }

    public boolean m8421b(String str) {
        boolean a = m8414a("loved_perf_keys", str);
        if (this.f6973f) {
            Log.m7767a(f6968a, "hasLovedPerformance - for performanceKey, " + str + " user has loved: " + a);
        }
        return a;
    }

    public void m8422c(String str) {
        if (this.f6973f) {
            Log.m7767a(f6968a, "ratedArrangement - inserting arrangementKey, " + str + " into the cache.");
        }
        m8415b("rated_arrangement_keys", str);
        m8416g("rated_arrangement_keys");
        m8417h("rated_arrangement_keys");
    }

    public boolean m8423d(String str) {
        boolean a = m8414a("rated_arrangement_keys", str);
        if (this.f6973f) {
            Log.m7767a(f6968a, "hasRatedArrangement - for arrangementKey, " + str + " user has rated: " + a);
        }
        return a;
    }

    public void m8424e(String str) {
        m8415b("all_followers_invited_to_perf_keys", str);
        m8416g("all_followers_invited_to_perf_keys");
        m8417h("all_followers_invited_to_perf_keys");
    }

    public boolean m8425f(String str) {
        return m8414a("all_followers_invited_to_perf_keys", str);
    }

    private boolean m8414a(String str, String str2) {
        return ((LinkedHashSet) this.f6971d.get(str)).contains(str2);
    }

    private void m8415b(String str, String str2) {
        if (this.f6973f) {
            Log.m7767a(f6968a, "addToCache - " + str + " - inserting entry, " + str2 + " into the cache.");
        }
        ((LinkedHashSet) this.f6971d.get(str)).add(str2);
        m8416g(str);
        m8417h(str);
    }

    private void m8416g(String str) {
        HashSet hashSet = (HashSet) this.f6971d.get(str);
        int intValue = ((Integer) this.f6972e.get(str)).intValue();
        if (hashSet.size() > intValue) {
            Object arrayList = new ArrayList(hashSet);
            while (arrayList.size() > intValue - 20) {
                if (this.f6973f) {
                    Log.m7767a(f6968a, "pruneCache - " + str + " - pruning entry for string: " + ((String) arrayList.get(0)));
                }
                arrayList.remove(0);
            }
            this.f6971d.put(str, new LinkedHashSet(arrayList));
            if (this.f6973f) {
                Log.m7767a(f6968a, "pruneCache - " + str + " - size of set is now: " + arrayList.size());
            }
        } else if (this.f6973f) {
            Log.m7767a(f6968a, "pruneCache - " + str + " - size of set is smaller than max allowed entries; not pruning any.");
        }
    }

    private void m8417h(String str) {
        if (this.f6970c == null) {
            Log.m7776e(f6968a, "saveCache - " + str + " - mContext is null; aborting save operation!");
            return;
        }
        if (this.f6973f) {
            Log.m7767a(f6968a, "saveCache - " + str + " begin");
        }
        this.f6970c.getSharedPreferences("CACHE_FILE", 0).edit().putString(str, TextUtils.join(",", (Iterable) this.f6971d.get(str))).apply();
        Log.m7772c(f6968a, "saveCache - end");
    }

    private synchronized void m8418i(String str) {
        if (this.f6970c == null) {
            Log.m7776e(f6968a, "loadCacheFromPrefs - " + str + " - mContext is null; aborting load operation!");
        } else {
            String string = this.f6970c.getSharedPreferences("CACHE_FILE", 0).getString(str, "");
            if (this.f6971d.containsKey(str)) {
                ((LinkedHashSet) this.f6971d.get(str)).clear();
            } else {
                this.f6971d.put(str, new LinkedHashSet());
            }
            ((LinkedHashSet) this.f6971d.get(str)).addAll(Arrays.asList(TextUtils.split(string, ",")));
            if (this.f6973f) {
                Log.m7767a(f6968a, "loadEntriesForKey - " + str + " - done loading keys. Current size of set is: " + ((LinkedHashSet) this.f6971d.get(str)).size());
            }
            m8416g(str);
        }
    }
}
