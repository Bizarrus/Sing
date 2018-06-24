/*
 * Decompiled with CFR 0_123.
 */
package com.smule.singandroid.audio.core.logger;

import com.smule.android.logging.Log;

public class TagLogger {
    public static boolean a = false;
    public static LogLevel b = LogLevel.b;
    private String c;

    public TagLogger(String string2) {
        this.c = string2;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void a(String string2, LogLevel logLevel) {
        if (logLevel.ordinal() < b.ordinal()) return;
        switch (.a[logLevel.ordinal()]) {
            default: {
                return;
            }
            case 1: {
                Log.a(this.c, string2);
                return;
            }
            case 2: {
                Log.b(this.c, string2);
                return;
            }
            case 3: {
                Log.c(this.c, string2);
                return;
            }
            case 4: {
                Log.d(this.c, string2);
                return;
            }
            case 5: 
        }
        Log.e(this.c, string2);
    }

    public static enum LogLevel {
        a(2),
        b(3),
        c(4),
        d(5),
        e(6);
        
        private int f;

        private LogLevel(int n2) {
            this.f = n2;
        }
    }

}

