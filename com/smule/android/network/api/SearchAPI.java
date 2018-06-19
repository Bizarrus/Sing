package com.smule.android.network.api;

import com.smule.android.network.core.NetworkResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.SNP;

public interface SearchAPI {
    @POST("/v2/user/search/autocomplete")
    @SNP(allowGuest = true)
    Call<NetworkResponse> getAccounts(@Body GetAccountsRequest getAccountsRequest);

    @POST("/v2/user/search/byHandle")
    @SNP(allowGuest = true)
    Call<NetworkResponse> getUsersForTerm(@Body GetUsersForTermRequest getUsersForTermRequest);

    @POST("/v2/search")
    @SNP(allowGuest = true)
    Call<NetworkResponse> search(@Body SASearchRequest sASearchRequest);

    @POST("/v2/search/autocomplete")
    @SNP(allowGuest = true)
    Call<NetworkResponse> searchAutoComplete(@Body SATermSearchRequest sATermSearchRequest);

    @POST("/v2/search/global")
    @SNP(allowGuest = true)
    Call<NetworkResponse> searchGlobal(@Body SATermSearchRequest sATermSearchRequest);

    @POST("/v2/search/instant")
    @SNP(allowGuest = true)
    Call<NetworkResponse> searchInstant(@Body SATermSearchRequest sATermSearchRequest);

    @POST("/v2/search/songbook")
    @SNP(allowGuest = true)
    Call<NetworkResponse> searchSongbook(@Body SearchSongbookRequest searchSongbookRequest);

    @POST("/v2/search/songbook/autocomplete")
    @SNP(allowGuest = true)
    Call<NetworkResponse> searchSongbookAutoComplete(@Body SearchSongbookAutoCompleteRequest searchSongbookAutoCompleteRequest);
}
