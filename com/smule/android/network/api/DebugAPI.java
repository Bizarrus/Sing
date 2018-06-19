package com.smule.android.network.api;

import com.smule.android.network.core.NetworkConstants.Timeout;
import com.smule.android.network.core.NetworkResponse;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.SNP;

public interface DebugAPI {
    @POST("/v2/debug/preupload")
    @SNP
    Call<NetworkResponse> preupload(@Body PreuploadRequest preuploadRequest);

    @Multipart
    @POST("/v2/debug/upload")
    @SNP(timeout = Timeout.VERY_LONG)
    Call<NetworkResponse> upload(@Part MultipartBody.Part part, @Part("jsonData") UploadRequest uploadRequest);
}
