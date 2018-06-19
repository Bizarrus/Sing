package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;
import com.smule.android.utils.TimeUtils;
import com.smule.android.utils.VorgomUtils;

public class UserAPI$GoogleLoginRequest extends SnpRequest {
    public String advId;
    public boolean automaticLogin;
    public Long playerId;
    public int pulp = VorgomUtils.b();
    public String requestedPassword;
    public String token;
    public int tzOffset = TimeUtils.m19067a();
    public boolean vorgom = VorgomUtils.c();

    public UserAPI$GoogleLoginRequest setToken(String str) {
        this.token = str;
        return this;
    }

    public UserAPI$GoogleLoginRequest setRequestedPassword(String str) {
        this.requestedPassword = str;
        return this;
    }

    public UserAPI$GoogleLoginRequest setAutomaticLogin(boolean z) {
        this.automaticLogin = z;
        return this;
    }

    public UserAPI$GoogleLoginRequest setAdvId(String str) {
        this.advId = str;
        return this;
    }

    public UserAPI$GoogleLoginRequest setPlayerId(Long l) {
        this.playerId = l;
        return this;
    }
}
