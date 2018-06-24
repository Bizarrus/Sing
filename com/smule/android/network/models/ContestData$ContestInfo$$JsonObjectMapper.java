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

public final class ContestData$ContestInfo$$JsonObjectMapper
extends JsonMapper<ContestData> {
    private static final JsonMapper<ContestData> COM_SMULE_ANDROID_NETWORK_MODELS_CONTESTDATA_ACCOUNTSCORE__JSONOBJECTMAPPER = LoganSquare.b(ContestData.class);
    private static final JsonMapper<ContestData> COM_SMULE_ANDROID_NETWORK_MODELS_CONTESTDATA_CONTEST__JSONOBJECTMAPPER = LoganSquare.b(ContestData.class);

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public ContestData parse(JsonParser jsonParser) throws IOException {
        Object object = new Object(){
            @com.fasterxml.jackson.annotation.JsonProperty(value="contest")
            @com.bluelinelabs.logansquare.annotation.JsonField
            public ContestData contest;
            @com.fasterxml.jackson.annotation.JsonProperty(value="leaderboard")
            @com.bluelinelabs.logansquare.annotation.JsonField
            public ArrayList<ContestData> leaderboard;
            @com.fasterxml.jackson.annotation.JsonProperty(value="rank")
            @com.bluelinelabs.logansquare.annotation.JsonField
            public Long rank;
            @com.fasterxml.jackson.annotation.JsonProperty(value="score")
            @com.bluelinelabs.logansquare.annotation.JsonField
            public Integer score;

            public boolean isEnded() {
                if (this.contest.end * 1000 < java.lang.System.currentTimeMillis()) {
                    return true;
                }
                return false;
            }
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
    public void parseField(ContestData contestInfo, String serializable, JsonParser jsonParser) throws IOException {
        Object var5_4 = null;
        Object var4_5 = null;
        if ("contest".equals(serializable)) {
            contestInfo.contest = COM_SMULE_ANDROID_NETWORK_MODELS_CONTESTDATA_CONTEST__JSONOBJECTMAPPER.parse(jsonParser);
            return;
        } else {
            if ("leaderboard".equals(serializable)) {
                if (jsonParser.getCurrentToken() != JsonToken.START_ARRAY) {
                    contestInfo.leaderboard = null;
                    return;
                }
                serializable = new ArrayList();
                do {
                    if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                        contestInfo.leaderboard = serializable;
                        return;
                    }
                    serializable.add(COM_SMULE_ANDROID_NETWORK_MODELS_CONTESTDATA_ACCOUNTSCORE__JSONOBJECTMAPPER.parse(jsonParser));
                } while (true);
            }
            if ("rank".equals(serializable)) {
                serializable = jsonParser.getCurrentToken() == JsonToken.VALUE_NULL ? var4_5 : Long.valueOf(jsonParser.getValueAsLong());
                contestInfo.rank = serializable;
                return;
            }
            if (!"score".equals(serializable)) return;
            {
                serializable = jsonParser.getCurrentToken() == JsonToken.VALUE_NULL ? var5_4 : Integer.valueOf(jsonParser.getValueAsInt());
            }
        }
        contestInfo.score = serializable;
    }

    public void serialize(ContestData contestInfo, JsonGenerator jsonGenerator, boolean bl) throws IOException {
        Object object;
        if (bl) {
            jsonGenerator.writeStartObject();
        }
        if (contestInfo.contest != null) {
            jsonGenerator.writeFieldName("contest");
            COM_SMULE_ANDROID_NETWORK_MODELS_CONTESTDATA_CONTEST__JSONOBJECTMAPPER.serialize((Object)contestInfo.contest, jsonGenerator, true);
        }
        if ((object = contestInfo.leaderboard) != null) {
            jsonGenerator.writeFieldName("leaderboard");
            jsonGenerator.writeStartArray();
            object = object.iterator();
            while (object.hasNext()) {
                ContestData accountScore = object.next();
                if (accountScore == null) continue;
                COM_SMULE_ANDROID_NETWORK_MODELS_CONTESTDATA_ACCOUNTSCORE__JSONOBJECTMAPPER.serialize((Object)accountScore, jsonGenerator, true);
            }
            jsonGenerator.writeEndArray();
        }
        if (contestInfo.rank != null) {
            jsonGenerator.writeNumberField("rank", contestInfo.rank.longValue());
        }
        if (contestInfo.score != null) {
            jsonGenerator.writeNumberField("score", contestInfo.score.intValue());
        }
        if (bl) {
            jsonGenerator.writeEndObject();
        }
    }
}

