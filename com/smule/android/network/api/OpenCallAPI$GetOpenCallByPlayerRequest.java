package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class OpenCallAPI$GetOpenCallByPlayerRequest extends SnpRequest {
    public Long playerId;

    public OpenCallAPI$GetOpenCallByPlayerRequest setPlayerId(Long l) {
        this.playerId = l;
        return this;
    }
}
