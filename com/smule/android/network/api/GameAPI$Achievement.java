package com.smule.android.network.api;

public class GameAPI$Achievement {
    public String achievementId;
    public boolean claim;
    public boolean complete;
    public String progress;

    public GameAPI$Achievement(String str, String str2, boolean z, boolean z2) {
        this.achievementId = str;
        this.progress = str2;
        this.complete = z;
        this.claim = z2;
    }
}
