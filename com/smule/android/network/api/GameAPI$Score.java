package com.smule.android.network.api;

public class GameAPI$Score {
    public String arrKey;
    public String compType;
    public String progress;
    public Integer score;
    public String songId;

    public GameAPI$Score(String str, String str2, String str3, int i, String str4) {
        this.compType = str;
        this.songId = str2;
        this.arrKey = str3;
        this.score = Integer.valueOf(i);
        this.progress = str4;
    }
}
