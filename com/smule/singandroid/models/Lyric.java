package com.smule.singandroid.models;

import android.support.annotation.NonNull;

public class Lyric {
    public final String f23482a;
    public final double f23483b;
    public final double f23484c;
    public final boolean f23485d;
    public final boolean f23486e;
    public final double f23487f;
    public final LyricMeta f23488g = new LyricMeta();
    public float f23489h;
    public int f23490i;

    public static class LyricMeta {
        public int f23477a;
        public int f23478b;
    }

    public enum Version {
        RAVEN,
        COMMUNITY_V1;

        public static Version m24739a(@NonNull String str) {
            if (str.contains("version:1")) {
                return COMMUNITY_V1;
            }
            return RAVEN;
        }
    }

    public Lyric(String str, int i, double d, double d2, boolean z, boolean z2) {
        String replace = str != null ? str.replace("^", "*") : "";
        if (replace != null && replace.endsWith("\\n")) {
            replace = replace.substring(0, replace.length() - 2);
        }
        this.f23482a = replace;
        this.f23490i = i;
        this.f23483b = d;
        if (d2 - d <= 0.1d) {
            d2 = d + 2.0d;
        }
        this.f23484c = d2;
        this.f23485d = z;
        this.f23486e = z2;
        this.f23487f = this.f23484c - this.f23483b;
    }

    public String toString() {
        String str;
        switch (this.f23490i) {
            case 0:
                str = "[solo]";
                break;
            case 1:
                str = "[part 1]";
                break;
            case 2:
                str = "[part 2]";
                break;
            case 3:
                str = "[together]";
                break;
            default:
                str = "[?" + this.f23490i + "?]";
                break;
        }
        StringBuilder append = new StringBuilder().append(str).append(" \"").append(this.f23482a).append("\" @ ").append(this.f23483b).append(" -> ").append(this.f23484c);
        if (this.f23485d) {
            str = " *";
        } else {
            str = "";
        }
        append = append.append(str).append(" Duration: ").append(this.f23487f);
        if (this.f23486e) {
            str = " \\n";
        } else {
            str = "";
        }
        return append.append(str).toString();
    }
}
