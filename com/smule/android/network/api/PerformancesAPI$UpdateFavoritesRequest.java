package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;
import java.util.List;

public class PerformancesAPI$UpdateFavoritesRequest extends SnpRequest {
    public List<String> add;
    public List<String> remove;

    public PerformancesAPI$UpdateFavoritesRequest setAdd(List<String> list) {
        this.add = list;
        return this;
    }

    public PerformancesAPI$UpdateFavoritesRequest setRemove(List<String> list) {
        this.remove = list;
        return this;
    }
}
