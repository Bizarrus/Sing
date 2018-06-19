package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;
import com.smule.android.utils.TimeUtils;

public class UserAPI$GuestLoginRequest extends SnpRequest {
    public boolean automaticLogin = true;
    public boolean forceNewPlayer;
    public Long playerId;
    public int tzOffset = TimeUtils.m19067a();

    public UserAPI$GuestLoginRequest setForceNewPlayer(boolean z) {
        this.forceNewPlayer = z;
        return this;
    }

    public UserAPI$GuestLoginRequest setPlayerId(Long l) {
        if (!(l == null || l.longValue() == 0)) {
            this.playerId = l;
        }
        return this;
    }
}
