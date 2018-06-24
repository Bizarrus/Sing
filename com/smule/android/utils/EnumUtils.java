/*
 * Decompiled with CFR 0_123.
 */
package com.smule.android.utils;

public class EnumUtils {
    public static <T extends Enum<?>> T a(Class<T> arrenum, String string2) {
        for (Enum enum_ : (Enum[])arrenum.getEnumConstants()) {
            if (enum_.name().compareToIgnoreCase(string2) != 0) continue;
            return (T)enum_;
        }
        return null;
    }
}

