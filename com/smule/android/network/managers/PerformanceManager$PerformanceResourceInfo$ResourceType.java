package com.smule.android.network.managers;

public enum PerformanceManager$PerformanceResourceInfo$ResourceType {
    AUDIO(".m4a", ".m4a"),
    f16863b(".mp4", ".mp4"),
    META(".resource-json", ".json"),
    f16865d(".resource-jpg", ".jpg");
    
    private final String f16867e;
    private final String f16868f;

    private PerformanceManager$PerformanceResourceInfo$ResourceType(String str, String str2) {
        this.f16867e = str;
        this.f16868f = str2;
    }

    public String m18254a() {
        return this.f16867e;
    }

    public String m18255b() {
        return this.f16868f;
    }
}
