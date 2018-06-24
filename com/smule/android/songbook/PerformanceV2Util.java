/*
 * Decompiled with CFR 0_123.
 */
package com.smule.android.songbook;

import com.smule.android.network.models.PerformanceV2;

public class PerformanceV2Util {
    public static String a(PerformanceV2 performanceV2, boolean bl) {
        if (performanceV2 == null) {
            return null;
        }
        if (performanceV2.highlightUrl != null) {
            return performanceV2.highlightUrl;
        }
        if (bl && performanceV2.c()) {
            return performanceV2.videoRenderedUrl;
        }
        if (performanceV2.b()) {
            return performanceV2.shortTermRenderedUrl;
        }
        return performanceV2.videoRenderedUrl;
    }

    @Deprecated
    public static boolean a(String string2) {
        if (string2 == null) {
            return false;
        }
        return string2.startsWith("_open_mic_");
    }

}

