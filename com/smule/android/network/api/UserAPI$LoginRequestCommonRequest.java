package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;
import com.smule.android.utils.TimeUtils;
import com.smule.android.utils.VorgomUtils;

public class UserAPI$LoginRequestCommonRequest extends SnpRequest {
    public boolean automaticLogin = true;
    public Long playerId;
    public int pulp = VorgomUtils.b();
    public int tzOffset = TimeUtils.m19067a();
    public boolean vorgom = VorgomUtils.c();

    public UserAPI$LoginRequestCommonRequest setAutomaticLogin(boolean z) {
        this.automaticLogin = z;
        return this;
    }

    public UserAPI$LoginRequestCommonRequest setPlayerId(Long l) {
        if (!(l == null || l.longValue() == 0)) {
            this.playerId = l;
        }
        return this;
    }
}
