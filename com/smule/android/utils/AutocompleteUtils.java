/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.util.Pair
 *  org.json.JSONArray
 *  org.json.JSONException
 */
package com.smule.android.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Pair;
import com.smule.android.network.managers.FollowManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

public class AutocompleteUtils {
    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static ArrayList<String> a(Context context, int n) {
        int n2;
        ArrayList<String> arrayList;
        context = context.getSharedPreferences(AutocompleteUtils.class.getName(), 0);
        try {
            context = new JSONArray(context.getString("SHARED_PREF_RECENT_KEY", new JSONArray().toString()));
            arrayList = new ArrayList<String>();
        }
        catch (JSONException jSONException) {
            return new ArrayList<String>();
        }
        for (int i = 0; i < (n2 = context.length()); ++i) {
            try {
                arrayList.add(context.getString(i));
                n2 = arrayList.size();
                if (n2 != n) continue;
                return arrayList;
            }
            catch (JSONException jSONException) {
                // empty catch block
            }
        }
        return arrayList;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    public static List<String> a(Context var0, String var1_2, HashSet<String> var2_3) {
        var0 = var0.getSharedPreferences(AutocompleteUtils.class.getName(), 0);
        var6_5 = new JSONArray(var0.getString("SHARED_PREF_RECENT_KEY", new JSONArray().toString()));
        var0 = new ArrayList<E>();
        ** for (var3_4 = 0;
        ; var3_4 < (var4_6 = var6_5.length()); ++var3_4)
lbl-1000: // 1 sources:
        {
            var0.add(var6_5.getString(var3_4));
lbl8: // 2 sources:
            continue block5;
        }
lbl10: // 1 sources:
        try {
            var6_5 = new ArrayList<E>();
            var0 = var0.iterator();
            while (var0.hasNext()) {
                var7_8 = (String)var0.next();
                if (var2_3.contains(var7_8) || AutocompleteUtils.a(var7_8, var1_2) == null) continue;
                var6_5.add(var7_8);
            }
            Collections.sort(var6_5, String.CASE_INSENSITIVE_ORDER);
            var5_9 = var6_5.isEmpty();
            if (var5_9) {
                return null;
            }
            return var6_5;
        }
        catch (JSONException var0_1) {
            return null;
        }
        catch (JSONException var7_7) {
            ** continue;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public static List<Pair<Integer, Integer>> a(String list, String list2) {
        block3 : {
            block2 : {
                String string2 = list.toLowerCase();
                list = AutocompleteUtils.b(string2, (String)((Object)(list2 = list2.toLowerCase())));
                if (list != null) break block2;
                if ((list2 = AutocompleteUtils.c(string2, (String)((Object)list2))) == null) break block3;
                list = list2;
                if (list2.isEmpty()) break block3;
            }
            return list;
        }
        return null;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static List<String> a(String arrayList, HashSet<String> hashSet) {
        List<String> list = FollowManager.a().c();
        if (list == null) {
            return null;
        }
        ArrayList<String> arrayList2 = new ArrayList<String>();
        for (String string2 : list) {
            String string3 = "@" + string2;
            if (hashSet.contains(string2) || AutocompleteUtils.a(string3, arrayList) == null) continue;
            arrayList2.add(string3);
        }
        Collections.sort(arrayList2, String.CASE_INSENSITIVE_ORDER);
        if (!arrayList2.isEmpty()) return arrayList2;
        return null;
    }

    public static void a(Context context) {
        context.getSharedPreferences(AutocompleteUtils.class.getName(), 0).edit().putString("SHARED_PREF_RECENT_KEY", null).commit();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void a(Context jSONArray, String iterator) {
        JSONArray jSONArray2;
        SharedPreferences sharedPreferences;
        sharedPreferences = jSONArray.getSharedPreferences(AutocompleteUtils.class.getName(), 0);
        jSONArray = null;
        try {
            jSONArray2 = new JSONArray(sharedPreferences.getString("SHARED_PREF_RECENT_KEY", new JSONArray().toString()));
            jSONArray = jSONArray2;
        }
        catch (JSONException jSONException) {}
        if (jSONArray == null) {
            return;
        }
        jSONArray2 = new JSONArray();
        for (int n = 0; n < jSONArray.length(); ++n) {
            try {
                jSONArray2.add(jSONArray.getString(n));
                continue;
            }
            catch (JSONException jSONException) {}
        }
        jSONArray = jSONArray2.iterator();
        while (jSONArray.hasNext()) {
            if (!((String)jSONArray.next()).toLowerCase().equals(iterator.toLowerCase())) continue;
            jSONArray.remove();
            break;
        }
        if (jSONArray2.contains(iterator)) {
            jSONArray2.remove(iterator);
        }
        jSONArray2.add(0, iterator);
        if (jSONArray2.size() > 50) {
            jSONArray2.remove(jSONArray2.size() - 1);
        }
        jSONArray = new JSONArray();
        iterator = jSONArray2.iterator();
        do {
            if (!iterator.hasNext()) {
                sharedPreferences.edit().putString("SHARED_PREF_RECENT_KEY", jSONArray.toString()).commit();
                return;
            }
            jSONArray.put((Object)((String)iterator.next()));
        } while (true);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static List<Pair<Integer, Integer>> b(String object, String arrstring) {
        if (object == null) return null;
        if (object.isEmpty()) {
            return null;
        }
        ArrayList<Pair> arrayList = new ArrayList<Pair>();
        object = object.split(" ");
        if ((arrstring = arrstring.split(" ")).length > object.length) return null;
        int n = 0;
        for (int i = 0; i < arrstring.length; ++i) {
            String string2 = object[i];
            String string3 = arrstring[i];
            if (!string2.startsWith(string3)) return null;
            arrayList.add(new Pair((Object)n, (Object)(string3.length() + n)));
            n += string2.length() + 1;
        }
        if (!arrayList.isEmpty()) return arrayList;
        return null;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    public static boolean b(Context var0, String var1_2) {
        var0 = var0.getSharedPreferences(AutocompleteUtils.class.getName(), 0);
        var0 = new JSONArray(var0.getString("SHARED_PREF_RECENT_KEY", new JSONArray().toString()));
        var5_3 = new ArrayList<String>();
        ** for (var2_4 = 0;
        ; var2_4 < (var3_5 = var0.length()); ++var2_4)
lbl-1000: // 1 sources:
        {
            var5_3.add(var0.getString(var2_4).toLowerCase());
lbl8: // 2 sources:
            continue block5;
        }
lbl10: // 1 sources:
        try {
            var4_7 = var5_3.contains(var1_2.toLowerCase());
            return var4_7;
        }
        catch (JSONException var0_1) {
            return false;
        }
        catch (JSONException var6_6) {
            ** continue;
        }
    }

    private static List<Pair<Integer, Integer>> c(String arrstring, String arrstring2) {
        if (arrstring == null || arrstring.isEmpty()) {
            return null;
        }
        ArrayList<Pair<Integer, Integer>> arrayList = new ArrayList<Pair<Integer, Integer>>();
        arrstring = arrstring.split(" ");
        arrstring2 = arrstring2.split(" ");
        HashSet<Integer> hashSet = new HashSet<Integer>();
        int n = arrstring.length;
        int n2 = 0;
        int n3 = 0;
        while (n2 < n) {
            int n4;
            String string2 = arrstring[n2];
            int n5 = 0;
            for (n4 = 0; n4 < arrstring2.length; ++n4) {
                String string3 = arrstring2[n4];
                int n6 = n5;
                if (string2.startsWith(string3)) {
                    hashSet.add(n4);
                    n6 = n5;
                    if (string3.length() > n5) {
                        n6 = string3.length();
                    }
                }
                n5 = n6;
            }
            if (n5 > 0) {
                arrayList.add((Pair)new Pair((Object)n3, (Object)(n5 + n3)));
            }
            n4 = string2.length();
            ++n2;
            n3 += n4 + 1;
        }
        if (hashSet.size() == arrstring2.length) {
            return arrayList;
        }
        return null;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void c(Context jSONArray, String iterator) {
        SharedPreferences sharedPreferences = jSONArray.getSharedPreferences(AutocompleteUtils.class.getName(), 0);
        try {
            jSONArray = new JSONArray(sharedPreferences.getString("SHARED_PREF_RECENT_KEY", new JSONArray().toString()));
            if (jSONArray == null) {
                return;
            }
        }
        catch (JSONException jSONException) {
            return;
        }
        ArrayList<String> arrayList = new ArrayList<String>();
        for (int n = 0; n < jSONArray.length(); ++n) {
            try {
                arrayList.add(jSONArray.getString(n));
                continue;
            }
            catch (JSONException jSONException) {}
        }
        jSONArray = arrayList.iterator();
        while (jSONArray.hasNext()) {
            if (!((String)jSONArray.next()).toLowerCase().equals(iterator.toLowerCase())) continue;
            jSONArray.remove();
            break;
        }
        jSONArray = new JSONArray();
        iterator = arrayList.iterator();
        do {
            if (!iterator.hasNext()) {
                sharedPreferences.edit().putString("SHARED_PREF_RECENT_KEY", jSONArray.toString()).commit();
                return;
            }
            jSONArray.put((Object)((String)iterator.next()));
        } while (true);
    }
}

