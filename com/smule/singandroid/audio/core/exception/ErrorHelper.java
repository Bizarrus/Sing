/*
 * Decompiled with CFR 0_123.
 */
package com.smule.singandroid.audio.core.exception;

public class ErrorHelper {
    public static int a(String string2) {
        try {
            ErrorHelper.b(string2);
            boolean bl = string2.equals("STATE_MACHINE_ERROR_CODE_OFFSET");
            if (bl) {
                return 100;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return 0;
    }

    private static void b(String string2) throws Exception {
        if (string2 == null || string2.equals("")) {
            throw new Exception("Invalid key for error code offset");
        }
    }
}

