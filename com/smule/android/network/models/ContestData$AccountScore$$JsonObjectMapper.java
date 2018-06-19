package com.smule.android.network.models;

import com.bluelinelabs.logansquare.JsonMapper;
import com.bluelinelabs.logansquare.LoganSquare;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.smule.android.network.models.ContestData.AccountScore;
import java.io.IOException;

public final class ContestData$AccountScore$$JsonObjectMapper extends JsonMapper<AccountScore> {
    private static final JsonMapper<AccountIcon> COM_SMULE_ANDROID_NETWORK_MODELS_ACCOUNTICON__JSONOBJECTMAPPER = LoganSquare.b(AccountIcon.class);

    public AccountScore parse(JsonParser jsonParser) throws IOException {
        AccountScore accountScore = new AccountScore();
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
            parseField(accountScore, currentName, jsonParser);
            jsonParser.skipChildren();
        }
        return accountScore;
    }

    public void parseField(AccountScore accountScore, String str, JsonParser jsonParser) throws IOException {
        if ("accountIcon".equals(str)) {
            accountScore.accountIcon = (AccountIcon) COM_SMULE_ANDROID_NETWORK_MODELS_ACCOUNTICON__JSONOBJECTMAPPER.parse(jsonParser);
        } else if ("score".equals(str)) {
            accountScore.score = jsonParser.getCurrentToken() == JsonToken.VALUE_NULL ? null : Integer.valueOf(jsonParser.getValueAsInt());
        }
    }

    public void serialize(AccountScore accountScore, JsonGenerator jsonGenerator, boolean z) throws IOException {
        if (z) {
            jsonGenerator.writeStartObject();
        }
        if (accountScore.accountIcon != null) {
            jsonGenerator.writeFieldName("accountIcon");
            COM_SMULE_ANDROID_NETWORK_MODELS_ACCOUNTICON__JSONOBJECTMAPPER.serialize(accountScore.accountIcon, jsonGenerator, true);
        }
        if (accountScore.score != null) {
            jsonGenerator.writeNumberField("score", accountScore.score.intValue());
        }
        if (z) {
            jsonGenerator.writeEndObject();
        }
    }
}
