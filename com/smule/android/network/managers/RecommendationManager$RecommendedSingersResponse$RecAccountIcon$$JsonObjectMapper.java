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
package com.smule.android.network.managers;

import com.bluelinelabs.logansquare.JsonMapper;
import com.bluelinelabs.logansquare.LoganSquare;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.smule.android.network.managers.RecommendationManager;
import com.smule.android.network.models.AccountIcon;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class RecommendationManager$RecommendedSingersResponse$RecAccountIcon$$JsonObjectMapper
extends JsonMapper<RecommendationManager.RecommendedSingersResponse.RecAccountIcon> {
    private static final JsonMapper<AccountIcon> a = LoganSquare.b(AccountIcon.class);

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public RecommendationManager.RecommendedSingersResponse.RecAccountIcon a(JsonParser jsonParser) throws IOException {
        RecommendationManager.RecommendedSingersResponse.RecAccountIcon recAccountIcon = new RecommendationManager.RecommendedSingersResponse.RecAccountIcon();
        if (jsonParser.getCurrentToken() == null) {
            jsonParser.nextToken();
        }
        if (jsonParser.getCurrentToken() != JsonToken.START_OBJECT) {
            jsonParser.skipChildren();
            return null;
        }
        do {
            Object object = recAccountIcon;
            if (jsonParser.nextToken() == JsonToken.END_OBJECT) return object;
            object = jsonParser.getCurrentName();
            jsonParser.nextToken();
            this.a(recAccountIcon, (String)object, jsonParser);
            jsonParser.skipChildren();
        } while (true);
    }

    public void a(RecommendationManager.RecommendedSingersResponse.RecAccountIcon recAccountIcon, JsonGenerator jsonGenerator, boolean bl) throws IOException {
        Object object;
        if (bl) {
            jsonGenerator.writeStartObject();
        }
        if (recAccountIcon.mAccountIcon != null) {
            jsonGenerator.writeFieldName("mAccountIcon");
            a.serialize((Object)recAccountIcon.mAccountIcon, jsonGenerator, true);
        }
        if (recAccountIcon.mReasonType != null) {
            jsonGenerator.writeStringField("mReasonType", recAccountIcon.mReasonType);
        }
        if ((object = recAccountIcon.mReasonVars) != null) {
            jsonGenerator.writeFieldName("mReasonVars");
            jsonGenerator.writeStartArray();
            object = object.iterator();
            while (object.hasNext()) {
                String string2 = (String)object.next();
                if (string2 == null) continue;
                jsonGenerator.writeString(string2);
            }
            jsonGenerator.writeEndArray();
        }
        if (recAccountIcon.mRecId != null) {
            jsonGenerator.writeStringField("mRecId", recAccountIcon.mRecId);
        }
        if (recAccountIcon.b != null) {
            jsonGenerator.writeStringField("mRecommendationType", recAccountIcon.b);
        }
        if (bl) {
            jsonGenerator.writeEndObject();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(RecommendationManager.RecommendedSingersResponse.RecAccountIcon recAccountIcon, String arrayList, JsonParser jsonParser) throws IOException {
        if ("mAccountIcon".equals(arrayList)) {
            recAccountIcon.mAccountIcon = (AccountIcon)a.parse(jsonParser);
            return;
        } else {
            if ("mReasonType".equals(arrayList)) {
                recAccountIcon.mReasonType = jsonParser.getValueAsString(null);
                return;
            }
            if ("mReasonVars".equals(arrayList)) {
                if (jsonParser.getCurrentToken() != JsonToken.START_ARRAY) {
                    recAccountIcon.mReasonVars = null;
                    return;
                }
                arrayList = new ArrayList();
                do {
                    if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                        recAccountIcon.mReasonVars = arrayList;
                        return;
                    }
                    arrayList.add(jsonParser.getValueAsString(null));
                } while (true);
            }
            if ("mRecId".equals(arrayList)) {
                recAccountIcon.mRecId = jsonParser.getValueAsString(null);
                return;
            }
            if (!"mRecommendationType".equals(arrayList)) return;
            {
                recAccountIcon.b = jsonParser.getValueAsString(null);
                return;
            }
        }
    }

    public /* synthetic */ Object parse(JsonParser jsonParser) throws IOException {
        return this.a(jsonParser);
    }

    public /* synthetic */ void parseField(Object object, String string2, JsonParser jsonParser) throws IOException {
        this.a((RecommendationManager.RecommendedSingersResponse.RecAccountIcon)object, string2, jsonParser);
    }

    public /* synthetic */ void serialize(Object object, JsonGenerator jsonGenerator, boolean bl) throws IOException {
        this.a((RecommendationManager.RecommendedSingersResponse.RecAccountIcon)object, jsonGenerator, bl);
    }
}

