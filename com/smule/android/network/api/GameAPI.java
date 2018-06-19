package com.smule.android.network.api;

import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.SnpRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.SNP;

public interface GameAPI {
    public static final int SUCCESS = 0;

    @POST("/v2/game/get")
    @SNP(allowGuest = true)
    Call<NetworkResponse> getGameConfiguration(@Body GetGameConfigurationRequest getGameConfigurationRequest);

    @POST("/v2/game/player/achievement/get")
    @SNP(needsSession = false)
    Call<NetworkResponse> getPlayerAchievements(@Body GetPlayerAchievementsRequest getPlayerAchievementsRequest);

    @POST("/v2/game/player/level/get")
    @SNP(needsSession = false)
    Call<NetworkResponse> getPlayerLevel(@Body GetPlayerLevelRequest getPlayerLevelRequest);

    @POST("/v2/game/player/restore")
    @SNP
    Call<NetworkResponse> restorePlayerGameStats(@Body SnpRequest snpRequest);

    @POST("/v2/game/player/achievement/set")
    @SNP(allowGuest = true)
    Call<NetworkResponse> setPlayerAchievements(@Body SetPlayerAchievementsRequest setPlayerAchievementsRequest);

    @POST("/v2/game/player/score/set")
    @SNP(allowGuest = true)
    Call<NetworkResponse> setPlayerScores(@Body SetPlayerScoresRequest setPlayerScoresRequest);
}
