package com.smule.android.utils;

import android.support.annotation.NonNull;
import android.support.v4.util.LongSparseArray;
import java.util.ArrayList;
import java.util.List;

public class ListUtils {
    @NonNull
    public static String m18996a(List<String> list, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            stringBuilder.append((String) list.get(i));
            if (i != list.size() - 1) {
                stringBuilder.append(str);
            }
        }
        return stringBuilder.toString();
    }

    @NonNull
    public static <T> ArrayList<T> m18997a(LongSparseArray<T> longSparseArray) {
        int size;
        int i = 0;
        if (longSparseArray != null) {
            size = longSparseArray.size();
        } else {
            size = 0;
        }
        ArrayList<T> arrayList = new ArrayList(size);
        if (longSparseArray != null) {
            while (i < longSparseArray.size()) {
                arrayList.add(longSparseArray.valueAt(i));
                i++;
            }
        }
        return arrayList;
    }
}
