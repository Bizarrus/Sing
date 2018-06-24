/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.support.annotation.NonNull
 */
package com.smule.singandroid.models;

import android.support.annotation.NonNull;

public class Lyric {
    public final String a;
    public final double b;
    public final double c;
    public final boolean d;
    public final boolean e;
    public final double f;
    public final LyricMeta g = new LyricMeta();
    public float h;
    public int i;

    /*
     * Enabled aggressive block sorting
     */
    public Lyric(String string2, int n, double d, double d2, boolean bl, boolean bl2) {
        string2 = string2 != null ? string2.replace("^", "*") : "";
        String string3 = string2;
        if (string2 != null) {
            string3 = string2;
            if (string2.endsWith("\\n")) {
                string3 = string2.substring(0, string2.length() - 2);
            }
        }
        this.a = string3;
        this.i = n;
        this.b = d;
        if (d2 - d <= 0.1) {
            d2 = d + 2.0;
        }
        this.c = d2;
        this.d = bl;
        this.e = bl2;
        this.f = this.c - this.b;
    }

    /*
     * Enabled aggressive block sorting
     */
    public String toString() {
        String string2;
        switch (this.i) {
            default: {
                string2 = "[?" + this.i + "?]";
                break;
            }
            case 0: {
                string2 = "[solo]";
                break;
            }
            case 1: {
                string2 = "[part 1]";
                break;
            }
            case 2: {
                string2 = "[part 2]";
                break;
            }
            case 3: {
                string2 = "[together]";
            }
        }
        StringBuilder stringBuilder = new StringBuilder().append(string2).append(" \"").append(this.a).append("\" @ ").append(this.b).append(" -> ").append(this.c);
        string2 = this.d ? " *" : "";
        stringBuilder = stringBuilder.append(string2).append(" Duration: ").append(this.f);
        if (this.e) {
            string2 = " \\n";
            return stringBuilder.append(string2).toString();
        }
        string2 = "";
        return stringBuilder.append(string2).toString();
    }

    public static class LyricMeta {
        public int a;
        public int b;
    }

    public static enum Version {
        a,
        b;
        

        private Version() {
        }

        public static Version a(@NonNull String string2) {
            if (string2.contains("version:1")) {
                return b;
            }
            return a;
        }
    }

}

