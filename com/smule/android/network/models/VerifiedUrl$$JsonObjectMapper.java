/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  com.bluelinelabs.logansquare.JsonMapper
 *  com.fasterxml.jackson.core.JsonGenerator
 *  com.fasterxml.jackson.core.JsonParser
 *  com.fasterxml.jackson.core.JsonToken
 */
package com.smule.android.network.models;

import com.bluelinelabs.logansquare.JsonMapper;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.smule.android.network.models.VerifiedUrl;
import java.io.IOException;

public final class VerifiedUrl$$JsonObjectMapper
extends JsonMapper<VerifiedUrl> {
    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public VerifiedUrl a(JsonParser jsonParser) throws IOException {
        VerifiedUrl verifiedUrl = new VerifiedUrl();
        if (jsonParser.getCurrentToken() == null) {
            jsonParser.nextToken();
        }
        if (jsonParser.getCurrentToken() != JsonToken.START_OBJECT) {
            jsonParser.skipChildren();
            return null;
        }
        do {
            Object object = verifiedUrl;
            if (jsonParser.nextToken() == JsonToken.END_OBJECT) return object;
            object = jsonParser.getCurrentName();
            jsonParser.nextToken();
            this.a(verifiedUrl, (String)object, jsonParser);
            jsonParser.skipChildren();
        } while (true);
    }

    public void a(VerifiedUrl verifiedUrl, JsonGenerator jsonGenerator, boolean bl) throws IOException {
        if (bl) {
            jsonGenerator.writeStartObject();
        }
        if (verifiedUrl.desc != null) {
            jsonGenerator.writeStringField("desc", verifiedUrl.desc);
        }
        if (verifiedUrl.type != null) {
            jsonGenerator.writeStringField("type", verifiedUrl.type);
        }
        if (verifiedUrl.url != null) {
            jsonGenerator.writeStringField("url", verifiedUrl.url);
        }
        if (bl) {
            jsonGenerator.writeEndObject();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(VerifiedUrl verifiedUrl, String string2, JsonParser jsonParser) throws IOException {
        if ("desc".equals(string2)) {
            verifiedUrl.desc = jsonParser.getValueAsString(null);
            return;
        } else {
            if ("type".equals(string2)) {
                verifiedUrl.type = jsonParser.getValueAsString(null);
                return;
            }
            if (!"url".equals(string2)) return;
            {
                verifiedUrl.url = jsonParser.getValueAsString(null);
                return;
            }
        }
    }

    public /* synthetic */ Object parse(JsonParser jsonParser) throws IOException {
        return this.a(jsonParser);
    }

    public /* synthetic */ void parseField(Object object, String string2, JsonParser jsonParser) throws IOException {
        this.a((VerifiedUrl)object, string2, jsonParser);
    }

    public /* synthetic */ void serialize(Object object, JsonGenerator jsonGenerator, boolean bl) throws IOException {
        this.a((VerifiedUrl)object, jsonGenerator, bl);
    }
}

