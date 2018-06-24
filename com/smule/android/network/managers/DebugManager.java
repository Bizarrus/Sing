/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.support.annotation.NonNull
 *  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 *  com.fasterxml.jackson.annotation.JsonProperty
 *  okhttp3.MultipartBody
 *  okhttp3.MultipartBody$Part
 *  okhttp3.RequestBody
 *  retrofit2.Call
 *  retrofit2.FileRequestBody
 */
package com.smule.android.network.managers;

import android.support.annotation.NonNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.api.DebugAPI;
import com.smule.android.network.core.CoreUtil;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.core.SnpRequest;
import java.io.File;
import java.util.concurrent.Future;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.FileRequestBody;

public class DebugManager {
    private static final String a = DebugManager.class.getName();
    private static DebugManager b = null;
    private com.smule.android.network.api.DebugAPI c = MagicNetwork.a().a(com.smule.android.network.api.DebugAPI.class);

    private DebugManager() {
    }

    public static DebugManager a() {
        if (b == null) {
            b = new DebugManager();
        }
        return b;
    }

    public NetworkResponse a(File file, String string2, long l) {
        return NetworkUtils.a(this.c.upload(MultipartBody.Part.createFormData((String)"debug", (String)file.getName(), (RequestBody)new FileRequestBody(file)), new SnpRequest(){
            public long resourceId;
            public String resourceType;

            public DebugAPI setResourceId(long l) {
                this.resourceId = l;
                return this;
            }

            public DebugAPI setResourceType(String string2) {
                this.resourceType = string2;
                return this;
            }
        }.setResourceType(string2).setResourceId(l)));
    }

    public  a(@NonNull String string2) {
        return .a(NetworkUtils.a(this.c.preupload(new SnpRequest(){
            public String resourceType;

            public DebugAPI setResourceType(String string2) {
                this.resourceType = string2;
                return this;
            }
        }.setResourceType(string2))));
    }

    public Future<?> a(final File file, final String string2, final long l, final UploadResponseCallback uploadResponseCallback) {
        return MagicNetwork.a(new Runnable(){

            @Override
            public void run() {
                CoreUtil.a(uploadResponseCallback, DebugManager.this.a(file, string2, l));
            }
        });
    }

    public Future<?> a(final @NonNull String string2, final PreuploadResponseCallback preuploadResponseCallback) {
        return MagicNetwork.a(new Runnable(){

            @Override
            public void run() {
                CoreUtil.a(preuploadResponseCallback, DebugManager.this.a(string2));
            }
        });
    }

    public static interface PreuploadResponseCallback
    extends ResponseInterface<> {
        @Override
        public void handleResponse( var1);
    }

    public static interface UploadResponseCallback
    extends ResponseInterface<NetworkResponse> {
        @Override
        public void handleResponse(NetworkResponse var1);
    }

}

