package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;
import java.util.List;

public class UserAPI$GetPreferencesRequest extends SnpRequest {
    public List<String> names;

    public UserAPI$GetPreferencesRequest setNames(List<String> list) {
        this.names = list;
        return this;
    }
}
