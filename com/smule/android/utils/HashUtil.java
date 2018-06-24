/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.annotation.SuppressLint
 */
package com.smule.android.utils;

import android.annotation.SuppressLint;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {
    @SuppressLint(value={"ValueOfNotAllowedForNumberSubClasses"})
    public static int a(long l, long l2, long l3) throws NoSuchAlgorithmException {
        String string2 = BigInteger.valueOf(l).multiply(BigInteger.valueOf(l3)).toString();
        return Math.abs(new BigInteger(MessageDigest.getInstance("MD5").digest(string2.getBytes())).mod(BigInteger.valueOf(l2)).intValue());
    }
}

