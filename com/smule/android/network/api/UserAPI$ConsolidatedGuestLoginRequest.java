package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;
import java.util.List;

public class UserAPI$ConsolidatedGuestLoginRequest extends SnpRequest {
    public boolean forceNewPlayer;
    public boolean lookupAccount;
    public List<String> settingsIds;

    public UserAPI$ConsolidatedGuestLoginRequest setForceNewPlayer(boolean z) {
        this.forceNewPlayer = z;
        return this;
    }

    public UserAPI$ConsolidatedGuestLoginRequest setSettingsIds(List<String> list) {
        this.settingsIds = list;
        return this;
    }

    public UserAPI$ConsolidatedGuestLoginRequest setLookupAccount(boolean z) {
        this.lookupAccount = z;
        return this;
    }

    public UserAPI$ConsolidatedGuestLoginRequest setLoginRequestCommonRequest(UserAPI$LoginRequestCommonRequest userAPI$LoginRequestCommonRequest) {
        this.common = userAPI$LoginRequestCommonRequest;
        return this;
    }
}
