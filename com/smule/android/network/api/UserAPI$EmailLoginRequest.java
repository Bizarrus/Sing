package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;
import com.smule.android.utils.TimeUtils;
import com.smule.android.utils.VorgomUtils;

public class UserAPI$EmailLoginRequest extends SnpRequest {
    public boolean automaticLogin;
    public String email;
    public String password;
    public Long playerId;
    public int pulp = VorgomUtils.b();
    public int tzOffset = TimeUtils.m19067a();
    public boolean vorgom = VorgomUtils.c();

    public UserAPI$EmailLoginRequest setEmail(String str) {
        this.email = str;
        return this;
    }

    public UserAPI$EmailLoginRequest setPassword(String str) {
        this.password = str;
        return this;
    }

    public UserAPI$EmailLoginRequest setPlayerId(Long l) {
        this.playerId = l;
        return this;
    }

    public UserAPI$EmailLoginRequest setAutomaticLogin(boolean z) {
        this.automaticLogin = z;
        return this;
    }
}
