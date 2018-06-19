package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;
import com.smule.android.utils.TimeUtils;
import com.smule.android.utils.VorgomUtils;

public class UserAPI$DeviceLoginRequest extends SnpRequest {
    public boolean automaticLogin;
    public Long playerId;
    public int pulp = VorgomUtils.b();
    public int tzOffset = TimeUtils.m19067a();
    public boolean vorgom = VorgomUtils.c();

    public UserAPI$DeviceLoginRequest setAutomaticLogin(boolean z) {
        this.automaticLogin = z;
        return this;
    }

    public UserAPI$DeviceLoginRequest setPlayerId(Long l) {
        this.playerId = l;
        return this;
    }
}
