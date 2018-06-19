package com.smule.android.network.models;

import com.bluelinelabs.logansquare.JsonMapper;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.smule.android.network.models.ContestData.Reward;
import java.io.IOException;

public final class ContestData$Reward$$JsonObjectMapper extends JsonMapper<Reward> {
    public Reward parse(JsonParser jsonParser) throws IOException {
        Reward reward = new Reward();
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
            parseField(reward, currentName, jsonParser);
            jsonParser.skipChildren();
        }
        return reward;
    }

    public void parseField(Reward reward, String str, JsonParser jsonParser) throws IOException {
        if ("type".equals(str)) {
            reward.type = jsonParser.getValueAsString(null);
        } else if ("value".equals(str)) {
            reward.value = jsonParser.getValueAsString(null);
        }
    }

    public void serialize(Reward reward, JsonGenerator jsonGenerator, boolean z) throws IOException {
        if (z) {
            jsonGenerator.writeStartObject();
        }
        if (reward.type != null) {
            jsonGenerator.writeStringField("type", reward.type);
        }
        if (reward.value != null) {
            jsonGenerator.writeStringField("value", reward.value);
        }
        if (z) {
            jsonGenerator.writeEndObject();
        }
    }
}
