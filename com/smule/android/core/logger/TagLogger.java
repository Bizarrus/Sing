/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.util.Log
 */
package com.smule.android.core.logger;

import android.util.Log;
import java.io.PrintStream;

public class TagLogger {
    public static boolean a = false;
    public static LogLevel b = LogLevel.a;
    private String c;

    public TagLogger(String string2) {
        this.c = string2;
    }

    private void a(String string2, LogLevel logLevel) {
        if (logLevel.ordinal() >= b.ordinal()) {
            System.out.println((Object)((Object)logLevel) + ": <" + this.c + "> " + string2);
        }
    }

    public void a(String string2) {
        if (a) {
            this.a(string2, LogLevel.a);
            return;
        }
        Log.d((String)this.c, (String)string2);
    }

    public void b(String string2) {
        if (a) {
            this.a(string2, LogLevel.b);
            return;
        }
        Log.i((String)this.c, (String)string2);
    }

    public void c(String string2) {
        if (a) {
            this.a(string2, LogLevel.d);
            return;
        }
        Log.e((String)this.c, (String)string2);
    }

    public static enum LogLevel {
        a,
        b,
        c,
        d;
        

        private LogLevel() {
        }
    }

}

