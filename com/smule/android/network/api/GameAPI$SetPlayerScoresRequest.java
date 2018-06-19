package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;
import java.util.List;

public class GameAPI$SetPlayerScoresRequest extends SnpRequest {
    public Integer level;
    public List<GameAPI$Score> playerScores;
    public Long xp;

    public GameAPI$SetPlayerScoresRequest setLevel(Integer num) {
        this.level = num;
        return this;
    }

    public GameAPI$SetPlayerScoresRequest setXp(Long l) {
        this.xp = l;
        return this;
    }

    public GameAPI$SetPlayerScoresRequest setPlayerScores(List<GameAPI$Score> list) {
        this.playerScores = list;
        return this;
    }
}
