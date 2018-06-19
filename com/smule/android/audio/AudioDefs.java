package com.smule.android.audio;

import android.app.Activity;
import android.media.AudioManager;
import com.smule.android.logging.Analytics.AnalyticsType;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class AudioDefs {

    public enum HeadphonesType implements AnalyticsType {
        HEADSET("headset", "user_headset_latency", "headset_latency", "headset_latency_v3"),
        HEADPHONES("headphones", "user_headphones_latency", "headphones_latency", "headphones_latency_v3"),
        OVER_AIR("none", "user_over_air_latency", "over_air_latency", "over_air_latency_v3");
        
        private String f15681d;
        private String f15682e;
        private String f15683f;
        private String f15684g;

        private HeadphonesType(String str, String str2, String str3, String str4) {
            this.f15681d = str;
            this.f15682e = str2;
            this.f15683f = str3;
            this.f15684g = str4;
        }

        public String mo6235a() {
            return this.f15681d;
        }

        public String m17504b() {
            return this.f15682e;
        }

        public String m17505c() {
            return this.f15683f;
        }

        public String m17506d() {
            return this.f15684g;
        }

        public static HeadphonesType m17502a(boolean z, boolean z2) {
            if (!z) {
                return OVER_AIR;
            }
            if (z2) {
                return HEADSET;
            }
            return HEADPHONES;
        }
    }

    public enum MonitoringMode implements AnalyticsType {
        NONE("no_monitoring", 0),
        OPENSLES("open_sl_monitoring", 1),
        SAPA("samsung_monitoring", 2);
        
        private String f15689d;
        private int f15690e;

        private MonitoringMode(String str, int i) {
            this.f15689d = str;
            this.f15690e = i;
        }

        public String mo6235a() {
            return this.f15689d;
        }
    }

    public static List<HeadphonesType> m17509a(HeadphonesType headphonesType) {
        List<HeadphonesType> linkedList = new LinkedList(Arrays.asList(new HeadphonesType[]{HeadphonesType.HEADSET, HeadphonesType.HEADPHONES, HeadphonesType.OVER_AIR}));
        linkedList.remove(headphonesType);
        linkedList.add(0, headphonesType);
        return linkedList;
    }

    public static float m17508a(Activity activity) {
        AudioManager audioManager = (AudioManager) activity.getSystemService("audio");
        return ((float) audioManager.getStreamVolume(3)) / ((float) audioManager.getStreamMaxVolume(3));
    }
}
