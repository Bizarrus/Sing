package com.smule.android.network.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.smule.android.utils.JsonUtils;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SnpRequest extends RequestBody {
    public String advId;
    public SnpRequest common;
    public Device device;
    private String mContent = null;
    @JsonIgnore
    private MediaType mMediaType = MediaType.a("application/json; charset=UTF-8");

    public SnpRequest(MediaType mediaType) {
        this.mMediaType = mediaType;
    }

    @JsonIgnore
    public String getContent() {
        if (this.mContent == null) {
            this.mContent = JsonUtils.a(this);
        }
        return this.mContent;
    }

    public MediaType contentType() {
        return this.mMediaType;
    }

    public void writeTo(BufferedSink bufferedSink) throws IOException {
        bufferedSink.a(getContent().getBytes(this.mMediaType.c()));
    }

    public long contentLength() throws IOException {
        return (long) getContent().getBytes().length;
    }
}
