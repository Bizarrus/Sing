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

import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.SnpRequest;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.SNP;

public interface PlaylistAPI {
    @Deprecated
    @POST(value="/v2/playlist")
    @SNP
    public Call<NetworkResponse> getPlaylistPerformances(@Body  var1);

    @POST(value="/v2/playlist/list")
    @SNP
    public Call<NetworkResponse> getPlaylists(@Body  var1);

    @POST(value="/v2/playlist/get")
    @SNP
    public Call<NetworkResponse> playlistPerformancesGet(@Body  var1);

}

