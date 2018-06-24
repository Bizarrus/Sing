/*
 * Decompiled with CFR 0_123.
 */
package com.smule.android.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ListSetConverter {
    public static <T> List<T> a(Set<T> object) {
        ArrayList arrayList = new ArrayList();
        if (object != null) {
            object = object.iterator();
            while (object.hasNext()) {
                arrayList.add(object.next());
            }
        }
        return arrayList;
    }

    public static <T> Set<T> a(List<T> object) {
        HashSet hashSet = new HashSet();
        if (object != null) {
            object = object.iterator();
            while (object.hasNext()) {
                hashSet.add(object.next());
            }
        }
        return hashSet;
    }
}

