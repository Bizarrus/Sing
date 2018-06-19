package com.smule.android.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListSetConverter {
    public static <T> List<T> m18994a(Set<T> set) {
        List<T> arrayList = new ArrayList();
        if (set != null) {
            for (T add : set) {
                arrayList.add(add);
            }
        }
        return arrayList;
    }

    public static <T> Set<T> m18995a(List<T> list) {
        Set<T> hashSet = new HashSet();
        if (list != null) {
            for (T add : list) {
                hashSet.add(add);
            }
        }
        return hashSet;
    }
}
