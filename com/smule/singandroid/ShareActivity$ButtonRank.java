package com.smule.singandroid;

import android.support.annotation.NonNull;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ShareActivity$ButtonRank implements Comparable<ShareActivity$ButtonRank> {
    @JsonProperty("nice")
    public int nice;
    @JsonProperty("tag")
    public String tag;

    public /* synthetic */ int compareTo(@NonNull Object obj) {
        return m8780a((ShareActivity$ButtonRank) obj);
    }

    public ShareActivity$ButtonRank(String str, int i) {
        this.tag = str;
        this.nice = i;
    }

    public int m8780a(@NonNull ShareActivity$ButtonRank shareActivity$ButtonRank) {
        if (this.nice < shareActivity$ButtonRank.nice) {
            return -1;
        }
        return this.nice == shareActivity$ButtonRank.nice ? 0 : 1;
    }
}
