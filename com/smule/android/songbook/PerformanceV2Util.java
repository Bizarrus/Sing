package com.smule.android.songbook;

import com.smule.android.network.managers.StoreManager;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.network.models.SongV2;

public class PerformanceV2Util {

    public enum RemovalReason {
        GENERIC(10),
        CONTENT_OWNER(20),
        PERF_OWNER(21),
        ARR_OWNER(22),
        CONTENT_PARTICIPANT(30),
        PERF_PARTICIPANT(31),
        CONTENT_COPYRIGHT(40),
        PERF_COPYRIGHT(41),
        ARR_COPYRIGHT(42),
        GENERIC_PERF_REMOVAL(51),
        GENERIC_ARR_REMOVAL(52);
        
        private final int f17639l;

        private RemovalReason(int i) {
            this.f17639l = i;
        }
    }

    public static String m18804a(PerformanceV2 performanceV2) {
        if (performanceV2 == null) {
            return null;
        }
        if (performanceV2.s()) {
            return performanceV2.songUid;
        }
        if (performanceV2.arrangementVersion == null || performanceV2.arrangementVersion.arrangement == null || performanceV2.arrangementVersion.arrangement.songId == null) {
            return null;
        }
        return performanceV2.arrangementVersion.arrangement.songId;
    }

    public static SongV2 m18807b(PerformanceV2 performanceV2) {
        String a = m18804a(performanceV2);
        return a != null ? StoreManager.m18378a().m18416a(a) : null;
    }

    public static boolean m18806a(String str) {
        if (str == null) {
            return false;
        }
        return str.startsWith("_open_mic_");
    }

    public static String m18805a(PerformanceV2 performanceV2, boolean z) {
        if (performanceV2 == null) {
            return null;
        }
        if (performanceV2.highlightUrl != null) {
            return performanceV2.highlightUrl;
        }
        if (z && performanceV2.c()) {
            return performanceV2.videoRenderedUrl;
        }
        if (performanceV2.b()) {
            return performanceV2.shortTermRenderedUrl;
        }
        return performanceV2.videoRenderedUrl;
    }
}
