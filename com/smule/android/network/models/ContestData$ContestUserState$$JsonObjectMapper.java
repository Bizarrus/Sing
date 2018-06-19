package com.smule.android.network.models;

import com.bluelinelabs.logansquare.JsonMapper;
import com.bluelinelabs.logansquare.LoganSquare;
import com.facebook.accountkit.internal.InternalLogger;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.smule.android.network.models.ContestData.ContestUserState;
import com.smule.android.network.models.ContestData.SubmitState;
import com.smule.android.network.models.ContestData.SubmitState.TypeConverter;
import java.io.IOException;
import java.util.Date;

public final class ContestData$ContestUserState$$JsonObjectMapper extends JsonMapper<ContestUserState> {
    protected static final TypeConverter f17397x3df2a334 = new TypeConverter();
    private static com.bluelinelabs.logansquare.typeconverters.TypeConverter<Date> java_util_Date_type_converter;

    public ContestUserState parse(JsonParser jsonParser) throws IOException {
        ContestUserState contestUserState = new ContestUserState();
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
            parseField(contestUserState, currentName, jsonParser);
            jsonParser.skipChildren();
        }
        return contestUserState;
    }

    public void parseField(ContestUserState contestUserState, String str, JsonParser jsonParser) throws IOException {
        Boolean bool = null;
        if ("rank".equals(str)) {
            Long valueOf;
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                valueOf = Long.valueOf(jsonParser.getValueAsLong());
            }
            contestUserState.rank = valueOf;
        } else if ("reported".equals(str)) {
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                bool = Boolean.valueOf(jsonParser.getValueAsBoolean());
            }
            contestUserState.reported = bool;
        } else if ("rewardEndDate".equals(str)) {
            contestUserState.rewardEndDate = (Date) LoganSquare.c(Date.class).parse(jsonParser);
        } else if ("score".equals(str)) {
            Integer valueOf2;
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                valueOf2 = Integer.valueOf(jsonParser.getValueAsInt());
            }
            contestUserState.score = valueOf2;
        } else if (InternalLogger.EVENT_PARAM_EXTRAS_STARTED.equals(str)) {
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                bool = Boolean.valueOf(jsonParser.getValueAsBoolean());
            }
            contestUserState.started = bool;
        } else if ("submitState".equals(str)) {
            contestUserState.submitState = (SubmitState) f17397x3df2a334.parse(jsonParser);
        }
    }

    public void serialize(ContestUserState contestUserState, JsonGenerator jsonGenerator, boolean z) throws IOException {
        if (z) {
            jsonGenerator.writeStartObject();
        }
        if (contestUserState.rank != null) {
            jsonGenerator.writeNumberField("rank", contestUserState.rank.longValue());
        }
        if (contestUserState.reported != null) {
            jsonGenerator.writeBooleanField("reported", contestUserState.reported.booleanValue());
        }
        if (contestUserState.rewardEndDate != null) {
            LoganSquare.c(Date.class).serialize(contestUserState.rewardEndDate, "rewardEndDate", true, jsonGenerator);
        }
        if (contestUserState.score != null) {
            jsonGenerator.writeNumberField("score", contestUserState.score.intValue());
        }
        if (contestUserState.started != null) {
            jsonGenerator.writeBooleanField(InternalLogger.EVENT_PARAM_EXTRAS_STARTED, contestUserState.started.booleanValue());
        }
        f17397x3df2a334.serialize(contestUserState.submitState, "submitState", true, jsonGenerator);
        if (z) {
            jsonGenerator.writeEndObject();
        }
    }

    private static final com.bluelinelabs.logansquare.typeconverters.TypeConverter<Date> getjava_util_Date_type_converter() {
        if (java_util_Date_type_converter == null) {
            java_util_Date_type_converter = LoganSquare.c(Date.class);
        }
        return java_util_Date_type_converter;
    }
}
