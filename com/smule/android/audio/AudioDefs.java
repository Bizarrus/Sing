/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.media.AudioManager
 */
package com.smule.android.audio;

import android.app.Activity;
import android.media.AudioManager;
import com.smule.android.logging.Analytics;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class AudioDefs {
    public static float a(Activity activity) {
        activity = (AudioManager)activity.getSystemService("audio");
        return (float)activity.getStreamVolume(3) / (float)activity.getStreamMaxVolume(3);
    }

    public static List<HeadphonesType> a(HeadphonesType headphonesType) {
        LinkedList<HeadphonesType> linkedList = new LinkedList<HeadphonesType>(Arrays.asList(HeadphonesType.a, HeadphonesType.b, HeadphonesType.c));
        linkedList.remove(headphonesType);
        linkedList.add(0, headphonesType);
        return linkedList;
    }

    public static enum HeadphonesType implements Analytics
    {
        a("headset", "user_headset_latency", "headset_latency", "headset_latency_v3", "headset_latency_v4", "user_sp_headset_latency"),
        b("headphones", "user_headphones_latency", "headphones_latency", "headphones_latency_v3", "headphones_latency_v4", "user_sp_headphones_latency"),
        c("none", "user_over_air_latency", "over_air_latency", "over_air_latency_v3", "over_air_latency_v4", "user_sp_over_air_latency"),
        d("bt", "user_bt_latency", "bt_latency", "bt_latency_v3", "bt_latency_v4", "user_sp_bt_latency");
        
        private String e;
        private String f;
        private String g;
        private String h;
        private String i;
        private String j;

        private HeadphonesType(String string3, String string4, String string5, String string6, String string7, String string8) {
            this.e = string3;
            this.f = string4;
            this.g = string5;
            this.h = string6;
            this.i = string7;
            this.j = string8;
        }

        public static HeadphonesType a(boolean bl, boolean bl2) {
            if (bl) {
                if (bl2) {
                    return a;
                }
                return b;
            }
            return c;
        }

        @Override
        public String a() {
            return this.e;
        }

        public String b() {
            return this.f;
        }

        public String c() {
            return this.g;
        }

        public String d() {
            return this.h;
        }

        public String e() {
            return this.i;
        }

        public String f() {
            return this.j;
        }
    }

    public static enum MonitoringMode implements Analytics
    {
        a("no_monitoring", 0),
        b("open_sl_monitoring", 1),
        c("samsung_monitoring", 2);
        
        private String d;
        private int e;

        private MonitoringMode(String string3, int n2) {
            this.d = string3;
            this.e = n2;
        }

        @Override
        public String a() {
            return this.d;
        }
    }

}

