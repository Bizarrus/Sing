package com.smule.android.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Pair;
import com.smule.android.network.managers.FollowManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

public class AutocompleteUtils {
    private static List<Pair<Integer, Integer>> m18952b(String str, String str2) {
        int i = 0;
        if (str == null || str.isEmpty()) {
            return null;
        }
        List<Pair<Integer, Integer>> arrayList = new ArrayList();
        String[] split = str.split(" ");
        String[] split2 = str2.split(" ");
        if (split2.length > split.length) {
            return null;
        }
        List<Pair<Integer, Integer>> list;
        int i2 = 0;
        while (i < split2.length) {
            String str3 = split[i];
            String str4 = split2[i];
            if (!str3.startsWith(str4)) {
                return null;
            }
            arrayList.add(new Pair(Integer.valueOf(i2), Integer.valueOf(str4.length() + i2)));
            i2 += str3.length() + 1;
            i++;
        }
        if (arrayList.isEmpty()) {
            list = null;
        } else {
            list = arrayList;
        }
        return list;
    }

    private static List<Pair<Integer, Integer>> m18954c(String str, String str2) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        List<Pair<Integer, Integer>> arrayList = new ArrayList();
        String[] split = str.split(" ");
        String[] split2 = str2.split(" ");
        HashSet hashSet = new HashSet();
        int length = split.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            String str3 = split[i];
            int i3 = 0;
            for (int i4 = 0; i4 < split2.length; i4++) {
                String str4 = split2[i4];
                if (str3.startsWith(str4)) {
                    hashSet.add(Integer.valueOf(i4));
                    if (str4.length() > i3) {
                        i3 = str4.length();
                    }
                }
            }
            if (i3 > 0) {
                arrayList.add(new Pair(Integer.valueOf(i2), Integer.valueOf(i3 + i2)));
            }
            i++;
            i2 += str3.length() + 1;
        }
        if (hashSet.size() == split2.length) {
            return arrayList;
        }
        return null;
    }

    public static List<Pair<Integer, Integer>> m18948a(String str, String str2) {
        String toLowerCase = str.toLowerCase();
        String toLowerCase2 = str2.toLowerCase();
        List<Pair<Integer, Integer>> b = m18952b(toLowerCase, toLowerCase2);
        if (b != null) {
            return b;
        }
        b = m18954c(toLowerCase, toLowerCase2);
        if (b == null || b.isEmpty()) {
            return null;
        }
        return b;
    }

    public static void m18951a(Context context, String str) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(AutocompleteUtils.class.getName(), 0);
        JSONArray jSONArray = null;
        try {
            jSONArray = new JSONArray(sharedPreferences.getString("SHARED_PREF_RECENT_KEY", new JSONArray().toString()));
        } catch (JSONException e) {
        }
        if (jSONArray != null) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    arrayList.add(jSONArray.getString(i));
                } catch (JSONException e2) {
                }
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                if (((String) it.next()).toLowerCase().equals(str.toLowerCase())) {
                    it.remove();
                    break;
                }
            }
            if (arrayList.contains(str)) {
                arrayList.remove(str);
            }
            arrayList.add(0, str);
            if (arrayList.size() > 50) {
                arrayList.remove(arrayList.size() - 1);
            }
            JSONArray jSONArray2 = new JSONArray();
            it = arrayList.iterator();
            while (it.hasNext()) {
                jSONArray2.put((String) it.next());
            }
            sharedPreferences.edit().putString("SHARED_PREF_RECENT_KEY", jSONArray2.toString()).commit();
        }
    }

    public static ArrayList<String> m18946a(Context context, int i) {
        int i2 = 0;
        try {
            JSONArray jSONArray = new JSONArray(context.getSharedPreferences(AutocompleteUtils.class.getName(), 0).getString("SHARED_PREF_RECENT_KEY", new JSONArray().toString()));
            ArrayList<String> arrayList = new ArrayList();
            while (i2 < jSONArray.length()) {
                try {
                    arrayList.add(jSONArray.getString(i2));
                    if (arrayList.size() == i) {
                        return arrayList;
                    }
                    i2++;
                } catch (JSONException e) {
                }
            }
            return arrayList;
        } catch (JSONException e2) {
            return new ArrayList();
        }
    }

    public static boolean m18953b(Context context, String str) {
        boolean z = false;
        try {
            JSONArray jSONArray = new JSONArray(context.getSharedPreferences(AutocompleteUtils.class.getName(), z).getString("SHARED_PREF_RECENT_KEY", new JSONArray().toString()));
            ArrayList arrayList = new ArrayList();
            for (int i = z; i < jSONArray.length(); i++) {
                try {
                    arrayList.add(jSONArray.getString(i).toLowerCase());
                } catch (JSONException e) {
                }
            }
            z = arrayList.contains(str.toLowerCase());
        } catch (JSONException e2) {
        }
        return z;
    }

    public static void m18950a(Context context) {
        context.getSharedPreferences(AutocompleteUtils.class.getName(), 0).edit().putString("SHARED_PREF_RECENT_KEY", null).commit();
    }

    public static void m18955c(Context context, String str) {
        JSONArray jSONArray;
        int i = 0;
        SharedPreferences sharedPreferences = context.getSharedPreferences(AutocompleteUtils.class.getName(), 0);
        try {
            jSONArray = new JSONArray(sharedPreferences.getString("SHARED_PREF_RECENT_KEY", new JSONArray().toString()));
        } catch (JSONException e) {
            jSONArray = null;
        }
        if (jSONArray != null) {
            ArrayList arrayList = new ArrayList();
            while (i < jSONArray.length()) {
                try {
                    arrayList.add(jSONArray.getString(i));
                } catch (JSONException e2) {
                }
                i++;
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                if (((String) it.next()).toLowerCase().equals(str.toLowerCase())) {
                    it.remove();
                    break;
                }
            }
            jSONArray = new JSONArray();
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                jSONArray.put((String) it2.next());
            }
            sharedPreferences.edit().putString("SHARED_PREF_RECENT_KEY", jSONArray.toString()).commit();
        }
    }

    public static List<String> m18947a(Context context, String str, HashSet<String> hashSet) {
        int i = 0;
        try {
            JSONArray jSONArray = new JSONArray(context.getSharedPreferences(AutocompleteUtils.class.getName(), 0).getString("SHARED_PREF_RECENT_KEY", new JSONArray().toString()));
            ArrayList arrayList = new ArrayList();
            while (i < jSONArray.length()) {
                try {
                    arrayList.add(jSONArray.getString(i));
                } catch (JSONException e) {
                }
                i++;
            }
            List<String> arrayList2 = new ArrayList();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                String str2 = (String) it.next();
                if (!(hashSet.contains(str2) || m18948a(str2, str) == null)) {
                    arrayList2.add(str2);
                }
            }
            Collections.sort(arrayList2, String.CASE_INSENSITIVE_ORDER);
            return arrayList2.isEmpty() ? null : arrayList2;
        } catch (JSONException e2) {
            return null;
        }
    }

    public static List<String> m18949a(String str, HashSet<String> hashSet) {
        List<String> c = FollowManager.m18204a().m18226c();
        if (c == null) {
            return null;
        }
        List<String> arrayList = new ArrayList();
        for (String str2 : c) {
            String str3 = "@" + str2;
            if (!(hashSet.contains(str2) || m18948a(str3, str) == null)) {
                arrayList.add(str3);
            }
        }
        Collections.sort(arrayList, String.CASE_INSENSITIVE_ORDER);
        return arrayList.isEmpty() ? null : arrayList;
    }
}
