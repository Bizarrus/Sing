package com.smule.android.network.models;

import com.bluelinelabs.logansquare.JsonMapper;
import com.bluelinelabs.logansquare.LoganSquare;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.smule.android.network.models.ContestData.AccountScore;
import com.smule.android.network.models.ContestData.Contest;
import com.smule.android.network.models.ContestData.ContestInfo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class ContestData$ContestInfo$$JsonObjectMapper extends JsonMapper<ContestInfo> {
    private static final JsonMapper<AccountScore> f17395x65b9f2b9 = LoganSquare.b(AccountScore.class);
    private static final JsonMapper<Contest> f17396x5d7c510 = LoganSquare.b(Contest.class);

    public ContestInfo parse(JsonParser jsonParser) throws IOException {
        ContestInfo contestInfo = new ContestInfo();
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
            parseField(contestInfo, currentName, jsonParser);
            jsonParser.skipChildren();
        }
        return contestInfo;
    }

    public void parseField(ContestInfo contestInfo, String str, JsonParser jsonParser) throws IOException {
        Integer num = null;
        if ("contest".equals(str)) {
            contestInfo.contest = (Contest) f17396x5d7c510.parse(jsonParser);
        } else if ("leaderboard".equals(str)) {
            if (jsonParser.getCurrentToken() == JsonToken.START_ARRAY) {
                ArrayList arrayList = new ArrayList();
                while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                    arrayList.add((AccountScore) f17395x65b9f2b9.parse(jsonParser));
                }
                contestInfo.leaderboard = arrayList;
                return;
            }
            contestInfo.leaderboard = null;
        } else if ("rank".equals(str)) {
            Long valueOf;
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                valueOf = Long.valueOf(jsonParser.getValueAsLong());
            }
            contestInfo.rank = valueOf;
        } else if ("score".equals(str)) {
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                num = Integer.valueOf(jsonParser.getValueAsInt());
            }
            contestInfo.score = num;
        }
    }

    public void serialize(ContestInfo contestInfo, JsonGenerator jsonGenerator, boolean z) throws IOException {
        if (z) {
            jsonGenerator.writeStartObject();
        }
        if (contestInfo.contest != null) {
            jsonGenerator.writeFieldName("contest");
            f17396x5d7c510.serialize(contestInfo.contest, jsonGenerator, true);
        }
        List<AccountScore> list = contestInfo.leaderboard;
        if (list != null) {
            jsonGenerator.writeFieldName("leaderboard");
            jsonGenerator.writeStartArray();
            for (AccountScore accountScore : list) {
                if (accountScore != null) {
                    f17395x65b9f2b9.serialize(accountScore, jsonGenerator, true);
                }
            }
            jsonGenerator.writeEndArray();
        }
        if (contestInfo.rank != null) {
            jsonGenerator.writeNumberField("rank", contestInfo.rank.longValue());
        }
        if (contestInfo.score != null) {
            jsonGenerator.writeNumberField("score", contestInfo.score.intValue());
        }
        if (z) {
            jsonGenerator.writeEndObject();
        }
    }
}
