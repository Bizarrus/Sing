/*
 * Decompiled with CFR 0_123.
 */
package com.smule.singandroid;

import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.Callable;

public class CallableQueueHelper {
    public static final String a = CallableQueueHelper.class.getName();
    private HashMap<String, Callable<CallableReturnCode>> b = new HashMap();
    private HashSet<String> c = new HashSet();
    private boolean d = false;

    public static enum CallableReturnCode {
        a,
        b,
        c,
        d;
        

        private CallableReturnCode() {
        }
    }

}

