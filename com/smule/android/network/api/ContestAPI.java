package com.smule.android.network.api;

import com.smule.android.network.core.NetworkResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.SNP;

public interface ContestAPI {
    public static final GetContestInfoRequest CURRENT_CONTEST_INFO_REQUEST = new GetContestInfoRequest().setPrevious(Boolean.valueOf(false));
    public static final GetContestInfoRequest PAST_CONTEST_INFO_REQUEST = new GetContestInfoRequest().setPrevious(Boolean.valueOf(true));

    @POST("/v2/contest/get")
    @SNP(allowGuest = true)
    Call<NetworkResponse> getContestInfo(@Body GetContestInfoRequest getContestInfoRequest);

    @POST("/v2/contest/score")
    @SNP
    Call<NetworkResponse> submitScore(@Body SubmitScoreRequest submitScoreRequest);
}
