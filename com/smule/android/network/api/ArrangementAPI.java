package com.smule.android.network.api;

import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.SnpRequest;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.SNP;

public interface ArrangementAPI {
    @POST("/v2/arr/create")
    @SNP
    Call<NetworkResponse> arrangementCreate(@Body ArrangementCreateRequest arrangementCreateRequest);

    @Multipart
    @POST("/v2/arr/resource/create")
    @SNP
    Call<NetworkResponse> arrangementResourceCreate(@Part MultipartBody.Part part, @Part("jsonData") ArrangementCreateResourceRequest arrangementCreateResourceRequest);

    @POST("/v2/arr/update")
    @SNP
    Call<NetworkResponse> arrangementUpdate(@Body ArrangementUpdateRequest arrangementUpdateRequest);

    @POST("/v2/arr/ver/create")
    @SNP
    Call<NetworkResponse> arrangementVersionCreate(@Body ArrangementVersionCreateRequest arrangementVersionCreateRequest);

    @POST("/v2/arr/vote")
    @SNP
    Call<NetworkResponse> arrangementVote(@Body ArrangementVoteRequest arrangementVoteRequest);

    @POST("/v2/arr/delete")
    @SNP
    Call<NetworkResponse> deleteArrangement(@Body ArrangementRemoveRequest arrangementRemoveRequest);

    @POST("/v2/arr")
    @SNP(allowGuest = true)
    Call<NetworkResponse> getArrangement(@Body GetArrangementRequest getArrangementRequest);

    @POST("/v2/arr/detail")
    @SNP(allowGuest = true)
    Call<NetworkResponse> getArrangementDetails(@Body GetArrangementDetailsRequest getArrangementDetailsRequest);

    @POST("/v2/arr/list")
    @SNP(allowGuest = true)
    Call<NetworkResponse> getArrangementList(@Body GetArrangementListRequest getArrangementListRequest);

    @POST("/v2/arr/owned")
    @SNP(allowGuest = true)
    Call<NetworkResponse> getArrangementListOwnedBy(@Body GetArrangmentListOwnedByRequest getArrangmentListOwnedByRequest);

    @POST("/v2/arr/unlocked")
    @Deprecated
    @SNP
    Call<NetworkResponse> getArrangementListUnlocked(@Body SnpRequest snpRequest);

    @POST("/v2/arr/owned/detail")
    @SNP
    Call<NetworkResponse> getArrangementOwnedDetails(@Body GetArrangementRequest getArrangementRequest);

    @POST("/v2/arr/byKeys")
    @SNP(allowGuest = true)
    Call<NetworkResponse> getArrangementsByKeys(@Body GetArrangementsByKeysRequest getArrangementsByKeysRequest);

    @POST("/v2/arr/play")
    @SNP(allowGuest = true)
    Call<NetworkResponse> sendArrangementPlayed(@Body SendArrangementPlayedRequest sendArrangementPlayedRequest);
}
