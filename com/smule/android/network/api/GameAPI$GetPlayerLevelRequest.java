package com.smule.android.network.api;

import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.SnpRequest;

public class GameAPI$GetPlayerLevelRequest extends SnpRequest {
    public String app = MagicNetwork.b();
    public Long playerId;

    public GameAPI$GetPlayerLevelRequest setPlayerId(Long l) {
        this.playerId = l;
        return this;
    }
}
