package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;
import java.util.List;

public class UserAPI$BlockUnblockRequest extends SnpRequest {
    public List<Long> add;
    public List<Long> remove;

    public UserAPI$BlockUnblockRequest setAdd(List<Long> list) {
        this.add = list;
        return this;
    }

    public UserAPI$BlockUnblockRequest setRemove(List<Long> list) {
        this.remove = list;
        return this;
    }
}
