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
import com.smule.android.network.models.ContestData;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public final class ContestData$Contest$$JsonObjectMapper
extends JsonMapper<ContestData> {
    private static final JsonMapper<ContestData> COM_SMULE_ANDROID_NETWORK_MODELS_CONTESTDATA_REWARD__JSONOBJECTMAPPER = LoganSquare.b(ContestData.class);

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public ContestData parse(JsonParser jsonParser) throws IOException {
        Object object = new Object(){
            @com.fasterxml.jackson.annotation.JsonProperty(value="end")
            @com.bluelinelabs.logansquare.annotation.JsonField
            public Long end;
            @com.fasterxml.jackson.annotation.JsonProperty(value="id")
            @com.bluelinelabs.logansquare.annotation.JsonField
            public Long id;
            @com.fasterxml.jackson.annotation.JsonProperty(value="numWinners")
            @com.bluelinelabs.logansquare.annotation.JsonField
            public Integer numWinners;
            @com.fasterxml.jackson.annotation.JsonProperty(value="rewards")
            @com.bluelinelabs.logansquare.annotation.JsonField
            public ArrayList<ContestData> rewards;
            @com.fasterxml.jackson.annotation.JsonProperty(value="songId")
            @com.bluelinelabs.logansquare.annotation.JsonField
            public String songId;
            @com.fasterxml.jackson.annotation.JsonProperty(value="start")
            @com.bluelinelabs.logansquare.annotation.JsonField
            public Long start;
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
    public void parseField(ContestData contest, String serializable, JsonParser jsonParser) throws IOException {
        void var3_16;
        void var2_14;
        Object var5_17 = null;
        Object var6_18 = null;
        Object var7_19 = null;
        Object var4_20 = null;
        if ("end".equals(serializable)) {
            void var2_4;
            if (var3_16.getCurrentToken() == JsonToken.VALUE_NULL) {
                Object var2_3 = var4_20;
            } else {
                Long l = var3_16.getValueAsLong();
            }
            contest.end = var2_4;
            return;
        }
        if ("id".equals(serializable)) {
            void var2_7;
            if (var3_16.getCurrentToken() == JsonToken.VALUE_NULL) {
                Object var2_6 = var5_17;
            } else {
                Long l = var3_16.getValueAsLong();
            }
            contest.id = var2_7;
            return;
        }
        if ("numWinners".equals(serializable)) {
            void var2_10;
            if (var3_16.getCurrentToken() == JsonToken.VALUE_NULL) {
                Object var2_9 = var6_18;
            } else {
                Integer n = var3_16.getValueAsInt();
            }
            contest.numWinners = var2_10;
            return;
        }
        if ("rewards".equals(serializable)) {
            if (var3_16.getCurrentToken() != JsonToken.START_ARRAY) {
                contest.rewards = null;
                return;
            }
            ArrayList<ContestData> arrayList = new ArrayList<ContestData>();
            do {
                if (var3_16.nextToken() == JsonToken.END_ARRAY) {
                    contest.rewards = arrayList;
                    return;
                }
                arrayList.add(COM_SMULE_ANDROID_NETWORK_MODELS_CONTESTDATA_REWARD__JSONOBJECTMAPPER.parse((JsonParser)var3_16));
            } while (true);
        }
        if ("songId".equals(serializable)) {
            contest.songId = var3_16.getValueAsString(null);
            return;
        }
        if (!"start".equals(serializable)) return;
        if (var3_16.getCurrentToken() == JsonToken.VALUE_NULL) {
            Object var2_13 = var7_19;
        } else {
            Long l = var3_16.getValueAsLong();
        }
        contest.start = var2_14;
    }

    public void serialize(ContestData contest, JsonGenerator jsonGenerator, boolean bl) throws IOException {
        Object object;
        if (bl) {
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
        if ((object = contest.rewards) != null) {
            jsonGenerator.writeFieldName("rewards");
            jsonGenerator.writeStartArray();
            object = object.iterator();
            while (object.hasNext()) {
                ContestData reward = object.next();
                if (reward == null) continue;
                COM_SMULE_ANDROID_NETWORK_MODELS_CONTESTDATA_REWARD__JSONOBJECTMAPPER.serialize((Object)reward, jsonGenerator, true);
            }
            jsonGenerator.writeEndArray();
        }
        if (contest.songId != null) {
            jsonGenerator.writeStringField("songId", contest.songId);
        }
        if (contest.start != null) {
            jsonGenerator.writeNumberField("start", contest.start.longValue());
        }
        if (bl) {
            jsonGenerator.writeEndObject();
        }
    }
}

