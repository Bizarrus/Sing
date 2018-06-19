package com.smule.android.network.api;

import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.SnpRequest;

public class GameAPI$GetPlayerAchievementsRequest extends SnpRequest {
    public String app = MagicNetwork.b();
    public Long playerId;

    public GameAPI$GetPlayerAchievementsRequest setPlayerId(Long l) {
        this.playerId = l;
        return this;
    }
}
