/*
 * Decompiled with CFR 0_123.
 */
package com.smule.android.core.wait;

public class WaitUtil {
    public static void a(long l) {
        try {
            Thread.sleep(l);
            return;
        }
        catch (InterruptedException interruptedException) {
            return;
        }
    }
}

