/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.support.annotation.NonNull
 *  android.support.v4.util.LongSparseArray
 */
package com.smule.android.utils;

import android.support.annotation.NonNull;
import android.support.v4.util.LongSparseArray;
import java.util.ArrayList;
import java.util.List;

public class ListUtils {
    @NonNull
    public static String a(List<String> list, String string2) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); ++i) {
            stringBuilder.append(list.get(i));
            if (i == list.size() - 1) continue;
            stringBuilder.append(string2);
        }
        return stringBuilder.toString();
    }

    /*
     * Enabled aggressive block sorting
     */
    @NonNull
    public static <T> ArrayList<T> a(LongSparseArray<T> longSparseArray) {
        int n = 0;
        int n2 = longSparseArray != null ? longSparseArray.size() : 0;
        ArrayList<Object> arrayList = new ArrayList<Object>(n2);
        if (longSparseArray != null) {
            for (n2 = n; n2 < longSparseArray.size(); ++n2) {
                arrayList.add(longSparseArray.valueAt(n2));
            }
        }
        return arrayList;
    }
}

