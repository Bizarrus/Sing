/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  com.bluelinelabs.logansquare.JsonMapper
 *  com.bluelinelabs.logansquare.LoganSquare
 *  com.bluelinelabs.logansquare.typeconverters.TypeConverter
 *  com.fasterxml.jackson.core.JsonGenerator
 *  com.fasterxml.jackson.core.JsonParser
 *  com.fasterxml.jackson.core.JsonToken
 */
package com.smule.android.network.models;

import com.bluelinelabs.logansquare.JsonMapper;
import com.bluelinelabs.logansquare.LoganSquare;
import com.bluelinelabs.logansquare.typeconverters.TypeConverter;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.smule.android.network.models.ContestData;
import java.io.IOException;
import java.util.Date;

public final class ContestData$ContestUserState$$JsonObjectMapper
extends JsonMapper<ContestData> {
    protected static final ContestData.SubmitState.TypeConverter COM_SMULE_ANDROID_NETWORK_MODELS_CONTESTDATA_SUBMITSTATE_TYPECONVERTER = new ContestData.SubmitState.TypeConverter();
    private static TypeConverter<Date> java_util_Date_type_converter;

    private static final TypeConverter<Date> getjava_util_Date_type_converter() {
        if (java_util_Date_type_converter == null) {
            java_util_Date_type_converter = LoganSquare.c(Date.class);
        }
        return java_util_Date_type_converter;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public ContestData parse(JsonParser jsonParser) throws IOException {
        Object object = new Object(){
            @com.fasterxml.jackson.annotation.JsonProperty(value="rank")
            @com.bluelinelabs.logansquare.annotation.JsonField
            public Long rank;
            @com.fasterxml.jackson.annotation.JsonProperty(value="reported")
            @com.bluelinelabs.logansquare.annotation.JsonField
            public Boolean reported;
            @com.fasterxml.jackson.annotation.JsonProperty(value="rewardEndDate")
            @com.bluelinelabs.logansquare.annotation.JsonField
            public Date rewardEndDate;
            @com.fasterxml.jackson.annotation.JsonProperty(value="score")
            @com.bluelinelabs.logansquare.annotation.JsonField
            public Integer score;
            @com.fasterxml.jackson.annotation.JsonProperty(value="started")
            @com.bluelinelabs.logansquare.annotation.JsonField
            public Boolean started;
            @com.fasterxml.jackson.annotation.JsonProperty(value="submitState")
            @com.bluelinelabs.logansquare.annotation.JsonField
            public ContestData.SubmitState submitState;
            {
                this.submitState = ContestData.SubmitState.NOT_SUBMITTED;
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
    public void parseField(ContestData contestUserState, String object, JsonParser jsonParser) throws IOException {
        Object var5_4 = null;
        Object var6_5 = null;
        Object var7_6 = null;
        Object var4_7 = null;
        if ("rank".equals(object)) {
            object = jsonParser.getCurrentToken() == JsonToken.VALUE_NULL ? var4_7 : Long.valueOf(jsonParser.getValueAsLong());
            contestUserState.rank = object;
            return;
        } else {
            if ("reported".equals(object)) {
                object = jsonParser.getCurrentToken() == JsonToken.VALUE_NULL ? var5_4 : Boolean.valueOf(jsonParser.getValueAsBoolean());
                contestUserState.reported = object;
                return;
            }
            if ("rewardEndDate".equals(object)) {
                contestUserState.rewardEndDate = (Date)LoganSquare.c(Date.class).parse(jsonParser);
                return;
            }
            if ("score".equals(object)) {
                object = jsonParser.getCurrentToken() == JsonToken.VALUE_NULL ? var6_5 : Integer.valueOf(jsonParser.getValueAsInt());
                contestUserState.score = object;
                return;
            }
            if ("started".equals(object)) {
                object = jsonParser.getCurrentToken() == JsonToken.VALUE_NULL ? var7_6 : Boolean.valueOf(jsonParser.getValueAsBoolean());
                contestUserState.started = object;
                return;
            }
            if (!"submitState".equals(object)) return;
            {
                contestUserState.submitState = (ContestData.SubmitState)((Object)COM_SMULE_ANDROID_NETWORK_MODELS_CONTESTDATA_SUBMITSTATE_TYPECONVERTER.parse(jsonParser));
                return;
            }
        }
    }

    public void serialize(ContestData contestUserState, JsonGenerator jsonGenerator, boolean bl) throws IOException {
        if (bl) {
            jsonGenerator.writeStartObject();
        }
        if (contestUserState.rank != null) {
            jsonGenerator.writeNumberField("rank", contestUserState.rank.longValue());
        }
        if (contestUserState.reported != null) {
            jsonGenerator.writeBooleanField("reported", contestUserState.reported.booleanValue());
        }
        if (contestUserState.rewardEndDate != null) {
            LoganSquare.c(Date.class).serialize((Object)contestUserState.rewardEndDate, "rewardEndDate", true, jsonGenerator);
        }
        if (contestUserState.score != null) {
            jsonGenerator.writeNumberField("score", contestUserState.score.intValue());
        }
        if (contestUserState.started != null) {
            jsonGenerator.writeBooleanField("started", contestUserState.started.booleanValue());
        }
        COM_SMULE_ANDROID_NETWORK_MODELS_CONTESTDATA_SUBMITSTATE_TYPECONVERTER.serialize((Object)contestUserState.submitState, "submitState", true, jsonGenerator);
        if (bl) {
            jsonGenerator.writeEndObject();
        }
    }
}

