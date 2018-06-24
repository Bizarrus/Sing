/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  okhttp3.MultipartBody
 *  okhttp3.MultipartBody$Part
 *  retrofit2.Call
 *  retrofit2.FileRequestBody
 *  retrofit2.http.Body
 *  retrofit2.http.Header
 *  retrofit2.http.Headers
 *  retrofit2.http.Multipart
 *  retrofit2.http.POST
 *  retrofit2.http.Part
 *  retrofit2.http.Query
 *  retrofit2.http.SNP
 */
package com.smule.android.network.api;

import com.smule.android.network.api.PerformancesAPI;
import com.smule.android.network.core.NetworkConstants;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.SnpRequest;
import com.smule.android.network.managers.PerformanceManager;
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
    @POST(value="/v2/track/uploadAudio")
    @SNP(timeout=NetworkConstants.Timeout.INFINITE)
    public Call<NetworkResponse> uploadAudio(@Part MultipartBody.Part var1);

    @Headers(value={"Accept: */*", "Content-Type: audio/x-m4a", "Accept-Encoding: gzip, deflate"})
    @POST(value="/v2/track/uploadAudioResource")
    @SNP(digestParameters={"performanceKey", "uploadAudioKey"}, rawMode=true, timeout=NetworkConstants.Timeout.VERY_LONG)
    public Call<NetworkResponse> uploadAudioResource(@Header(value="Session-ID") String var1, @Header(value="Content-Range") String var2, @Header(value="Content-Disposition") String var3, @Query(value="performanceKey") String var4, @Query(value="uploadAudioKey") String var5, @Body FileRequestBody var6);

    @Multipart
    @POST(value="/v2/perf/upload")
    @SNP(digestParameters={"pop"}, timeout=NetworkConstants.Timeout.VERY_LONG)
    public Call<NetworkResponse> uploadResources(@Header(value="Host") String var1, @Query(value="pop") String var2, @Part(value="jsonData")  var3, @Part MultipartBody.Part var4, @Part MultipartBody.Part var5, @Part MultipartBody.Part var6);

    @Multipart
    @POST(value="/v2/track/uploadTrackAudio")
    @SNP(timeout=NetworkConstants.Timeout.VERY_LONG)
    public Call<NetworkResponse> uploadTrackAudio(@Part(value="jsonData")  var1, @Part MultipartBody.Part var2);

    @Headers(value={"Content-Type: video/mp4"})
    @POST(value="/v2/perf/uploadVideo")
    @SNP(digestParameters={"pop", "performanceKey", "resourceId", "resourceType", "trackKey", "uploadType"}, rawMode=true, timeout=NetworkConstants.Timeout.VERY_LONG)
    public Call<NetworkResponse> uploadVideo(@Header(value="Host") String var1, @Header(value="Content-Disposition") String var2, @Header(value="Session-ID") String var3, @Query(value="pop") String var4, @Query(value="performanceKey") String var5, @Query(value="resourceId") Long var6, @Query(value="resourceType") String var7, @Query(value="trackKey") String var8, @Query(value="uploadType") PerformancesAPI var9, @Header(value="Content-Range") String var10, @Body FileRequestBody var11);

    @Headers(value={"Accept: */*", "Content-Type: video/mp4", "Accept-Encoding: gzip, deflate"})
    @POST(value="/v2/track/uploadVideo")
    @SNP(digestParameters={"performanceKey", "uploadVideoKey"}, rawMode=true, timeout=NetworkConstants.Timeout.VERY_LONG)
    public Call<NetworkResponse> uploadVideo(@Header(value="Host") String var1, @Header(value="Session-ID") String var2, @Header(value="Content-Range") String var3, @Header(value="Content-Disposition") String var4, @Query(value="performanceKey") String var5, @Query(value="uploadVideoKey") String var6, @Body FileRequestBody var7);

}

