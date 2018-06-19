package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;
import java.util.List;

public class SocialAPI$UpdateFolloweesRequest extends SnpRequest {
    public List<Long> add;
    public String context;
    public String name;
    public List<Long> remove;

    public SocialAPI$UpdateFolloweesRequest setAdd(List<Long> list) {
        this.add = list;
        return this;
    }

    public SocialAPI$UpdateFolloweesRequest setRemove(List<Long> list) {
        this.remove = list;
        return this;
    }

    public SocialAPI$UpdateFolloweesRequest setContextAndName(String str, String str2) {
        if (str2 != null) {
            this.context = str;
            this.name = str2;
        }
        return this;
    }
}
