package com.smule.android.network.models;

import com.bluelinelabs.logansquare.JsonMapper;
import com.bluelinelabs.logansquare.LoganSquare;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.smule.android.network.models.ContestData.Contest;
import com.smule.android.network.models.ContestData.Reward;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class ContestData$Contest$$JsonObjectMapper extends JsonMapper<Contest> {
    private static final JsonMapper<Reward> f17394x95a1efe3 = LoganSquare.b(Reward.class);

    public Contest parse(JsonParser jsonParser) throws IOException {
        Contest contest = new Contest();
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
            parseField(contest, currentName, jsonParser);
            jsonParser.skipChildren();
        }
        return contest;
    }

    public void parseField(Contest contest, String str, JsonParser jsonParser) throws IOException {
        Long l = null;
        if ("end".equals(str)) {
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                l = Long.valueOf(jsonParser.getValueAsLong());
            }
            contest.end = l;
        } else if ("id".equals(str)) {
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                l = Long.valueOf(jsonParser.getValueAsLong());
            }
            contest.id = l;
        } else if ("numWinners".equals(str)) {
            Integer valueOf;
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                valueOf = Integer.valueOf(jsonParser.getValueAsInt());
            }
            contest.numWinners = valueOf;
        } else if ("rewards".equals(str)) {
            if (jsonParser.getCurrentToken() == JsonToken.START_ARRAY) {
                ArrayList arrayList = new ArrayList();
                while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                    arrayList.add((Reward) f17394x95a1efe3.parse(jsonParser));
                }
                contest.rewards = arrayList;
                return;
            }
            contest.rewards = null;
        } else if ("songId".equals(str)) {
            contest.songId = jsonParser.getValueAsString(null);
        } else if ("start".equals(str)) {
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                l = Long.valueOf(jsonParser.getValueAsLong());
            }
            contest.start = l;
        }
    }

    public void serialize(Contest contest, JsonGenerator jsonGenerator, boolean z) throws IOException {
        if (z) {
            jsonGenerator.writeStartObject();
        }
        if (contest.end != null) {
            jsonGenerator.writeNumberField("end", contest.end.longValue());
        }
        if (contest.id != null) {
            jsonGenerator.writeNumberField("id", contest.id.longValue());
        }
        if (contest.numWinners != null) {
            jsonGenerator.writeNumberField("numWinners", contest.numWinners.intValue());
        }
        List<Reward> list = contest.rewards;
        if (list != null) {
            jsonGenerator.writeFieldName("rewards");
            jsonGenerator.writeStartArray();
            for (Reward reward : list) {
                if (reward != null) {
                    f17394x95a1efe3.serialize(reward, jsonGenerator, true);
                }
            }
            jsonGenerator.writeEndArray();
        }
        if (contest.songId != null) {
            jsonGenerator.writeStringField("songId", contest.songId);
        }
        if (contest.start != null) {
            jsonGenerator.writeNumberField("start", contest.start.longValue());
        }
        if (z) {
            jsonGenerator.writeEndObject();
        }
    }
}
