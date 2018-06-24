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
import com.smule.android.network.models.ContestData;
import java.io.IOException;

public final class ContestData$Reward$$JsonObjectMapper
extends JsonMapper<ContestData> {
    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public ContestData parse(JsonParser jsonParser) throws IOException {
        Object object = new Object(){
            public static final String TYPE_SONG = "SONG";
            public static final String TYPE_XP_BOOST = "XP";
            @com.fasterxml.jackson.annotation.JsonProperty(value="type")
            @com.bluelinelabs.logansquare.annotation.JsonField
            public String type;
            @com.fasterxml.jackson.annotation.JsonProperty(value="value")
            @com.bluelinelabs.logansquare.annotation.JsonField
            public String value;
        };
        if (jsonParser.getCurrentToken() == null) {
            jsonParser.nextToken();
        }
        if (jsonParser.getCurrentToken() != JsonToken.START_OBJECT) {
            jsonParser.skipChildren();
            return null;
        }
        do {
            Object object2 = object;
            if (jsonParser.nextToken() == JsonToken.END_OBJECT) return object2;
            object2 = jsonParser.getCurrentName();
            jsonParser.nextToken();
            this.parseField(object, (String)object2, jsonParser);
            jsonParser.skipChildren();
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void parseField(ContestData reward, String string2, JsonParser jsonParser) throws IOException {
        if ("type".equals(string2)) {
            reward.type = jsonParser.getValueAsString(null);
            return;
        } else {
            if (!"value".equals(string2)) return;
            {
                reward.value = jsonParser.getValueAsString(null);
                return;
            }
        }
    }

    public void serialize(ContestData reward, JsonGenerator jsonGenerator, boolean bl) throws IOException {
        if (bl) {
            jsonGenerator.writeStartObject();
        }
        if (reward.type != null) {
            jsonGenerator.writeStringField("type", reward.type);
        }
        if (reward.value != null) {
            jsonGenerator.writeStringField("value", reward.value);
        }
        if (bl) {
            jsonGenerator.writeEndObject();
        }
    }
}

