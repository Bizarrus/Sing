package com.smule.android.network.models;

import com.bluelinelabs.logansquare.JsonMapper;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;

public final class VerifiedUrl$$JsonObjectMapper extends JsonMapper<VerifiedUrl> {
    public /* synthetic */ Object parse(JsonParser jsonParser) throws IOException {
        return m18603a(jsonParser);
    }

    public /* synthetic */ void parseField(Object obj, String str, JsonParser jsonParser) throws IOException {
        m18605a((VerifiedUrl) obj, str, jsonParser);
    }

    public /* synthetic */ void serialize(Object obj, JsonGenerator jsonGenerator, boolean z) throws IOException {
        m18604a((VerifiedUrl) obj, jsonGenerator, z);
    }

    public VerifiedUrl m18603a(JsonParser jsonParser) throws IOException {
        VerifiedUrl verifiedUrl = new VerifiedUrl();
        if (jsonParser.getCurrentToken() == null) {
            jsonParser.nextToken();
        }
        if (jsonParser.getCurrentToken() != JsonToken.START_OBJECT) {
            jsonParser.skipChildren();
            return null;
        }
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            m18605a(verifiedUrl, currentName, jsonParser);
            jsonParser.skipChildren();
        }
        return verifiedUrl;
    }

    public void m18605a(VerifiedUrl verifiedUrl, String str, JsonParser jsonParser) throws IOException {
        if ("desc".equals(str)) {
            verifiedUrl.desc = jsonParser.getValueAsString(null);
        } else if ("type".equals(str)) {
            verifiedUrl.type = jsonParser.getValueAsString(null);
        } else if ("url".equals(str)) {
            verifiedUrl.url = jsonParser.getValueAsString(null);
        }
    }

    public void m18604a(VerifiedUrl verifiedUrl, JsonGenerator jsonGenerator, boolean z) throws IOException {
        if (z) {
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
        if (z) {
            jsonGenerator.writeEndObject();
        }
    }
}
