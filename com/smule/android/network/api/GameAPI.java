/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  retrofit2.Call
 *  retrofit2.http.Body
 *  retrofit2.http.POST
 *  retrofit2.http.SNP
 */
package com.smule.android.network.api;

import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.SnpRequest;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.SNP;

public interface GameAPI {
    public static final int SUCCESS = 0;

    @POST(value="/v2/game/get")
    @SNP(allowGuest=true)
    public Call<NetworkResponse> getGameConfiguration(@Body  var1);

    @POST(value="/v2/game/player/achievement/get")
    @SNP(needsSession=false)
    public Call<NetworkResponse> getPlayerAchievements(@Body  var1);

    @POST(value="/v2/game/player/level/get")
    @SNP(needsSession=false)
    public Call<NetworkResponse> getPlayerLevel(@Body  var1);

    @POST(value="/v2/game/player/restore")
    @SNP
    public Call<NetworkResponse> restorePlayerGameStats(@Body SnpRequest var1);

    @POST(value="/v2/game/player/achievement/set")
    @SNP(allowGuest=true)
    public Call<NetworkResponse> setPlayerAchievements(@Body  var1);

    @POST(value="/v2/game/player/score/set")
    @SNP(allowGuest=true)
    public Call<NetworkResponse> setPlayerScores(@Body  var1);

}

