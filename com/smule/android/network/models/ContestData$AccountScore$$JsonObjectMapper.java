/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  com.bluelinelabs.logansquare.JsonMapper
 *  com.bluelinelabs.logansquare.LoganSquare
 *  com.fasterxml.jackson.core.JsonGenerator
 *  com.fasterxml.jackson.core.JsonParser
 *  com.fasterxml.jackson.core.JsonToken
 */
package com.smule.android.network.models;

import com.bluelinelabs.logansquare.JsonMapper;
import com.bluelinelabs.logansquare.LoganSquare;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.ContestData;
import java.io.IOException;

public final class ContestData$AccountScore$$JsonObjectMapper
extends JsonMapper<ContestData> {
    private static final JsonMapper<AccountIcon> COM_SMULE_ANDROID_NETWORK_MODELS_ACCOUNTICON__JSONOBJECTMAPPER = LoganSquare.b(AccountIcon.class);

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public ContestData parse(JsonParser jsonParser) throws IOException {
        Object object = new Object(){
            @com.fasterxml.jackson.annotation.JsonProperty(value="accountIcon")
            @com.bluelinelabs.logansquare.annotation.JsonField
            public AccountIcon accountIcon;
            @com.fasterxml.jackson.annotation.JsonProperty(value="score")
            @com.bluelinelabs.logansquare.annotation.JsonField
            public Integer score;
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
    public void parseField(ContestData accountScore, String object, JsonParser jsonParser) throws IOException {
        if ("accountIcon".equals(object)) {
            accountScore.accountIcon = (AccountIcon)COM_SMULE_ANDROID_NETWORK_MODELS_ACCOUNTICON__JSONOBJECTMAPPER.parse(jsonParser);
            return;
        } else {
            if (!"score".equals(object)) return;
            {
                object = jsonParser.getCurrentToken() == JsonToken.VALUE_NULL ? null : Integer.valueOf(jsonParser.getValueAsInt());
            }
        }
        accountScore.score = object;
    }

    public void serialize(ContestData accountScore, JsonGenerator jsonGenerator, boolean bl) throws IOException {
        if (bl) {
            jsonGenerator.writeStartObject();
        }
        if (accountScore.accountIcon != null) {
            jsonGenerator.writeFieldName("accountIcon");
            COM_SMULE_ANDROID_NETWORK_MODELS_ACCOUNTICON__JSONOBJECTMAPPER.serialize((Object)accountScore.accountIcon, jsonGenerator, true);
        }
        if (accountScore.score != null) {
            jsonGenerator.writeNumberField("score", accountScore.score.intValue());
        }
        if (bl) {
            jsonGenerator.writeEndObject();
        }
    }
}

