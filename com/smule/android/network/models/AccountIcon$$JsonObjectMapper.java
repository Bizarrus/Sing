package com.smule.android.network.models;

import com.bluelinelabs.logansquare.JsonMapper;
import com.bluelinelabs.logansquare.LoganSquare;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class AccountIcon$$JsonObjectMapper extends JsonMapper<AccountIcon> {
    private static final JsonMapper<VerifiedUrl> f17390a = LoganSquare.b(VerifiedUrl.class);

    public /* synthetic */ Object parse(JsonParser jsonParser) throws IOException {
        return m18527a(jsonParser);
    }

    public /* synthetic */ void parseField(Object obj, String str, JsonParser jsonParser) throws IOException {
        m18529a((AccountIcon) obj, str, jsonParser);
    }

    public /* synthetic */ void serialize(Object obj, JsonGenerator jsonGenerator, boolean z) throws IOException {
        m18528a((AccountIcon) obj, jsonGenerator, z);
    }

    public AccountIcon m18527a(JsonParser jsonParser) throws IOException {
        AccountIcon accountIcon = new AccountIcon();
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
            m18529a(accountIcon, currentName, jsonParser);
            jsonParser.skipChildren();
        }
        return accountIcon;
    }

    public void m18529a(AccountIcon accountIcon, String str, JsonParser jsonParser) throws IOException {
        if ("accountId".equals(str)) {
            accountIcon.accountId = jsonParser.getValueAsLong();
        } else if ("subApps".equals(str)) {
            if (jsonParser.getCurrentToken() == JsonToken.START_ARRAY) {
                r0 = new HashSet();
                while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                    r0.add(jsonParser.getValueAsString(null));
                }
                accountIcon.appsWithSubscription = r0;
                return;
            }
            accountIcon.appsWithSubscription = null;
        } else if ("firstName".equals(str)) {
            accountIcon.firstName = jsonParser.getValueAsString(null);
        } else if ("handle".equals(str)) {
            accountIcon.handle = jsonParser.getValueAsString(null);
        } else if ("jid".equals(str)) {
            accountIcon.jid = jsonParser.getValueAsString(null);
        } else if ("lastName".equals(str)) {
            accountIcon.lastName = jsonParser.getValueAsString(null);
        } else if ("latitude".equals(str)) {
            accountIcon.latitude = (float) jsonParser.getValueAsDouble();
        } else if ("longitude".equals(str)) {
            accountIcon.longitude = (float) jsonParser.getValueAsDouble();
        } else if ("picUrl".equals(str)) {
            accountIcon.picUrl = jsonParser.getValueAsString(null);
        } else if ("vip".equals(str)) {
            if (jsonParser.getCurrentToken() == JsonToken.START_ARRAY) {
                r0 = new HashSet();
                while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                    r0.add(jsonParser.getValueAsString(null));
                }
                accountIcon.platformsWithSmulePass = r0;
                return;
            }
            accountIcon.platformsWithSmulePass = null;
        } else if (!"verifiedUrls".equals(str)) {
        } else {
            if (jsonParser.getCurrentToken() == JsonToken.START_ARRAY) {
                List arrayList = new ArrayList();
                while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                    arrayList.add((VerifiedUrl) f17390a.parse(jsonParser));
                }
                accountIcon.verifiedUrls = arrayList;
                return;
            }
            accountIcon.verifiedUrls = null;
        }
    }

    public void m18528a(AccountIcon accountIcon, JsonGenerator jsonGenerator, boolean z) throws IOException {
        if (z) {
            jsonGenerator.writeStartObject();
        }
        jsonGenerator.writeNumberField("accountId", accountIcon.accountId);
        Set<String> set = accountIcon.appsWithSubscription;
        if (set != null) {
            jsonGenerator.writeFieldName("subApps");
            jsonGenerator.writeStartArray();
            for (String str : set) {
                if (str != null) {
                    jsonGenerator.writeString(str);
                }
            }
            jsonGenerator.writeEndArray();
        }
        if (accountIcon.firstName != null) {
            jsonGenerator.writeStringField("firstName", accountIcon.firstName);
        }
        if (accountIcon.handle != null) {
            jsonGenerator.writeStringField("handle", accountIcon.handle);
        }
        if (accountIcon.jid != null) {
            jsonGenerator.writeStringField("jid", accountIcon.jid);
        }
        if (accountIcon.lastName != null) {
            jsonGenerator.writeStringField("lastName", accountIcon.lastName);
        }
        jsonGenerator.writeNumberField("latitude", accountIcon.latitude);
        jsonGenerator.writeNumberField("longitude", accountIcon.longitude);
        if (accountIcon.picUrl != null) {
            jsonGenerator.writeStringField("picUrl", accountIcon.picUrl);
        }
        set = accountIcon.platformsWithSmulePass;
        if (set != null) {
            jsonGenerator.writeFieldName("vip");
            jsonGenerator.writeStartArray();
            for (String str2 : set) {
                if (str2 != null) {
                    jsonGenerator.writeString(str2);
                }
            }
            jsonGenerator.writeEndArray();
        }
        List<VerifiedUrl> list = accountIcon.verifiedUrls;
        if (list != null) {
            jsonGenerator.writeFieldName("verifiedUrls");
            jsonGenerator.writeStartArray();
            for (VerifiedUrl verifiedUrl : list) {
                if (verifiedUrl != null) {
                    f17390a.serialize(verifiedUrl, jsonGenerator, true);
                }
            }
            jsonGenerator.writeEndArray();
        }
        if (z) {
            jsonGenerator.writeEndObject();
        }
    }
}
