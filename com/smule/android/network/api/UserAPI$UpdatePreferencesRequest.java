package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;
import com.smule.android.network.models.AccountPreference;
import java.util.List;

public class UserAPI$UpdatePreferencesRequest extends SnpRequest {
    public List<AccountPreference> prefs;

    public UserAPI$UpdatePreferencesRequest setPreferences(List<AccountPreference> list) {
        this.prefs = list;
        return this;
    }
}
