/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.support.annotation.NonNull
 *  android.support.annotation.Nullable
 *  android.text.TextUtils
 */
package com.smule.android.utils;

import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

public class ParcelUtils {
    private static final String a = ParcelUtils.class.getName();

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static <T extends Enum<?>> T a(@NonNull Parcel object, @NonNull Class<T> arrenum, @Nullable T t) {
        object = object.readString();
        arrenum = (Enum[])arrenum.getEnumConstants();
        if (TextUtils.isEmpty((CharSequence)object)) {
            return t;
        }
        int n = arrenum.length;
        int n2 = 0;
        while (n2 < n) {
            Enum enum_ = arrenum[n2];
            if (enum_.name().equals(object)) {
                return (T)enum_;
            }
            ++n2;
        }
        return t;
    }

    public static void a(@NonNull Parcel parcel, @Nullable Enum<?> enum_, @Nullable String string2) {
        if (enum_ == null) {
            parcel.writeString(string2);
            return;
        }
        parcel.writeString(enum_.name());
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void a(@NonNull Parcel parcel, boolean bl) {
        byte by = bl ? 1 : 0;
        parcel.writeByte(by);
    }

    public static boolean a(@NonNull Parcel parcel) {
        if (parcel.readByte() != 0) {
            return true;
        }
        return false;
    }
}

