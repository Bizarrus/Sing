package com.smule.android.core.logger;

import android.util.Log;

public class TagLogger {
    public static boolean f15803a = false;
    public static LogLevel f15804b = LogLevel.DEBUG;
    private String f15805c;

    public enum LogLevel {
        DEBUG,
        INFO,
        WARN,
        ERROR
    }

    public TagLogger(String str) {
        this.f15805c = str;
    }

    public void m17591a(String str) {
        if (f15803a) {
            m17590a(str, LogLevel.DEBUG);
        } else {
            Log.d(this.f15805c, str);
        }
    }

    public void m17592b(String str) {
        if (f15803a) {
            m17590a(str, LogLevel.INFO);
        } else {
            Log.i(this.f15805c, str);
        }
    }

    public void m17593c(String str) {
        if (f15803a) {
            m17590a(str, LogLevel.ERROR);
        } else {
            Log.e(this.f15805c, str);
        }
    }

    private void m17590a(String str, LogLevel logLevel) {
        if (logLevel.ordinal() >= f15804b.ordinal()) {
            System.out.println(logLevel + ": <" + this.f15805c + "> " + str);
        }
    }
}
