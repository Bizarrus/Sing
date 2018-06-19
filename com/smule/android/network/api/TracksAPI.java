package com.smule.android.network.api;

import com.smule.android.network.api.PerformancesAPI.UploadType;
import com.smule.android.network.core.NetworkConstants.Timeout;
import com.smule.android.network.core.NetworkResponse;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.FileRequestBody;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.SNP;

public interface TracksAPI {
    @Multipart
    @POST("/v2/track/uploadAudio")
    @SNP(timeout = Timeout.INFINITE)
    Call<NetworkResponse> uploadAudio(@Part MultipartBody.Part part);

    @POST("/v2/track/uploadAudioResource")
    @Headers({"Accept: */*", "Content-Type: audio/x-m4a", "Accept-Encoding: gzip, deflate"})
    @SNP(digestParameters = {"performanceKey", "uploadAudioKey"}, rawMode = true, timeout = Timeout.VERY_LONG)
    Call<NetworkResponse> uploadAudioResource(@Header("Session-ID") String str, @Header("Content-Range") String str2, @Header("Content-Disposition") String str3, @Query("performanceKey") String str4, @Query("uploadAudioKey") String str5, @Body FileRequestBody fileRequestBody);

    @Multipart
    @POST("/v2/perf/upload")
    @SNP(digestParameters = {"pop"}, timeout = Timeout.VERY_LONG)
    Call<NetworkResponse> uploadResources(@Header("Host") String str, @Query("pop") String str2, @Part("jsonData") UploadResourceRequest uploadResourceRequest, @Part MultipartBody.Part part, @Part MultipartBody.Part part2, @Part MultipartBody.Part part3);

    @Multipart
    @POST("/v2/track/uploadTrackAudio")
    @SNP(timeout = Timeout.VERY_LONG)
    Call<NetworkResponse> uploadTrackAudio(@Part("jsonData") UploadTrackAudioRequest uploadTrackAudioRequest, @Part MultipartBody.Part part);

    @POST("/v2/perf/uploadVideo")
    @Headers({"Content-Type: video/mp4"})
    @SNP(digestParameters = {"pop", "performanceKey", "resourceId", "resourceType", "trackKey", "uploadType"}, rawMode = true, timeout = Timeout.VERY_LONG)
    Call<NetworkResponse> uploadVideo(@Header("Host") String str, @Header("Content-Disposition") String str2, @Header("Session-ID") String str3, @Query("pop") String str4, @Query("performanceKey") String str5, @Query("resourceId") Long l, @Query("resourceType") String str6, @Query("trackKey") String str7, @Query("uploadType") UploadType uploadType, @Header("Content-Range") String str8, @Body FileRequestBody fileRequestBody);

    @POST("/v2/track/uploadVideo")
    @Headers({"Accept: */*", "Content-Type: video/mp4", "Accept-Encoding: gzip, deflate"})
    @SNP(digestParameters = {"performanceKey", "uploadVideoKey"}, rawMode = true, timeout = Timeout.VERY_LONG)
    Call<NetworkResponse> uploadVideo(@Header("Host") String str, @Header("Session-ID") String str2, @Header("Content-Range") String str3, @Header("Content-Disposition") String str4, @Query("performanceKey") String str5, @Query("uploadVideoKey") String str6, @Body FileRequestBody fileRequestBody);
}
