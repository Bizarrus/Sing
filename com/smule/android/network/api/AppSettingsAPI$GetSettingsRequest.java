package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;
import java.util.List;

public class AppSettingsAPI$GetSettingsRequest extends SnpRequest {
    public List<String> settingsIds;

    public AppSettingsAPI$GetSettingsRequest setSettingsIds(List<String> list) {
        this.settingsIds = list;
        return this;
    }
}
