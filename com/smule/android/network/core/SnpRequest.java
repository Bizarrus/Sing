/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  com.fasterxml.jackson.annotation.JsonIgnore
 *  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 *  com.fasterxml.jackson.annotation.JsonInclude
 *  com.fasterxml.jackson.annotation.JsonInclude$Include
 *  okhttp3.MediaType
 *  okhttp3.RequestBody
 *  okio.BufferedSink
 */
package com.smule.android.network.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.smule.android.uploader.UploadJob;
import com.smule.android.utils.JsonUtils;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.SortedSet;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(value=JsonInclude.Include.NON_NULL)
public class SnpRequest
extends RequestBody {
    public String advId;
    public SnpRequest common;
    public  device;
    private String mContent = null;
    @JsonIgnore
    private MediaType mMediaType = MediaType.a((String)"application/json; charset=UTF-8");

    public SnpRequest() {
    }

    public SnpRequest(MediaType mediaType) {
        this.mMediaType = mediaType;
    }

    public long contentLength() throws IOException {
        return this.getContent().getBytes().length;
    }

    public MediaType contentType() {
        return this.mMediaType;
    }

    @JsonIgnore
    public String getContent() {
        if (this.mContent == null) {
            this.mContent = JsonUtils.a(this);
        }
        return this.mContent;
    }

    public void writeTo(BufferedSink bufferedSink) throws IOException {
        bufferedSink.a(this.getContent().getBytes(this.mMediaType.c()));
    }

}

