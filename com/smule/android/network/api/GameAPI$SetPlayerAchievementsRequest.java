package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;
import java.util.List;

public class GameAPI$SetPlayerAchievementsRequest extends SnpRequest {
    public List<GameAPI$Achievement> playerAchievements;

    public GameAPI$SetPlayerAchievementsRequest setPlayerAchievements(List<GameAPI$Achievement> list) {
        this.playerAchievements = list;
        return this;
    }
}
