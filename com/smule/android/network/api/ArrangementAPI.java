/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  okhttp3.MultipartBody
 *  okhttp3.MultipartBody$Part
 *  retrofit2.Call
 *  retrofit2.http.Body
 *  retrofit2.http.Multipart
 *  retrofit2.http.POST
 *  retrofit2.http.Part
 *  retrofit2.http.SNP
 */
package com.smule.android.network.api;

import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.SnpRequest;
import java.util.List;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.SNP;

public interface ArrangementAPI {
    @POST(value="/v2/arr/bookmark")
    @SNP
    public Call<NetworkResponse> addSongBookmark(@Body  var1);

    @POST(value="/v2/arr/create")
    @SNP
    public Call<NetworkResponse> arrangementCreate(@Body  var1);

    @Multipart
    @POST(value="/v2/arr/resource/create")
    @SNP
    public Call<NetworkResponse> arrangementResourceCreate(@Part MultipartBody.Part var1, @Part(value="jsonData")  var2);

    @POST(value="/v2/arr/update")
    @SNP
    public Call<NetworkResponse> arrangementUpdate(@Body  var1);

    @POST(value="/v2/arr/ver/create")
    @SNP
    public Call<NetworkResponse> arrangementVersionCreate(@Body  var1);

    @POST(value="/v2/arr/vote")
    @SNP
    public Call<NetworkResponse> arrangementVote(@Body  var1);

    @POST(value="/v2/arr/delete")
    @SNP
    public Call<NetworkResponse> deleteArrangement(@Body  var1);

    @POST(value="/v2/arr")
    @SNP(allowGuest=true)
    public Call<NetworkResponse> getArrangement(@Body  var1);

    @POST(value="/v2/arr/detail")
    @SNP(allowGuest=true)
    public Call<NetworkResponse> getArrangementDetails(@Body  var1);

    @Deprecated
    @POST(value="/v2/arr/list")
    @SNP(allowGuest=true)
    public Call<NetworkResponse> getArrangementList(@Body  var1);

    @POST(value="/v2/arr/owned")
    @SNP(allowGuest=true)
    public Call<NetworkResponse> getArrangementListOwnedBy(@Body  var1);

    @Deprecated
    @POST(value="/v2/arr/unlocked")
    @SNP
    public Call<NetworkResponse> getArrangementListUnlocked(@Body SnpRequest var1);

    @POST(value="/v2/arr/owned/detail")
    @SNP
    public Call<NetworkResponse> getArrangementOwnedDetails(@Body  var1);

    @POST(value="/v2/arr/byKeys")
    @SNP(allowGuest=true)
    public Call<NetworkResponse> getArrangementsByKeys(@Body  var1);

    @POST(value="/v2/arr/bookmark/list")
    @SNP
    public Call<NetworkResponse> getSongBookmarkList(@Body  var1);

    @POST(value="/v2/arr/bookmark/remove")
    @SNP
    public Call<NetworkResponse> removeSongBookmark(@Body  var1);

    @POST(value="/v2/arr/play")
    @SNP(allowGuest=true)
    public Call<NetworkResponse> sendArrangementPlayed(@Body  var1);

}

