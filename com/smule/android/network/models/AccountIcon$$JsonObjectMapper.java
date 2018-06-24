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
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.VerifiedUrl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public final class AccountIcon$$JsonObjectMapper
extends JsonMapper<AccountIcon> {
    private static final JsonMapper<VerifiedUrl> a = LoganSquare.b(VerifiedUrl.class);

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public AccountIcon a(JsonParser jsonParser) throws IOException {
        AccountIcon accountIcon = new AccountIcon();
        if (jsonParser.getCurrentToken() == null) {
            jsonParser.nextToken();
        }
        if (jsonParser.getCurrentToken() != JsonToken.START_OBJECT) {
            jsonParser.skipChildren();
            return null;
        }
        do {
            Object object = accountIcon;
            if (jsonParser.nextToken() == JsonToken.END_OBJECT) return object;
            object = jsonParser.getCurrentName();
            jsonParser.nextToken();
            this.a(accountIcon, (String)object, jsonParser);
            jsonParser.skipChildren();
        } while (true);
    }

    public void a(AccountIcon iterator, JsonGenerator jsonGenerator, boolean bl) throws IOException {
        String string2;
        if (bl) {
            jsonGenerator.writeStartObject();
        }
        jsonGenerator.writeNumberField("accountId", iterator.accountId);
        Object object = iterator.appsWithSubscription;
        if (object != null) {
            jsonGenerator.writeFieldName("subApps");
            jsonGenerator.writeStartArray();
            object = object.iterator();
            while (object.hasNext()) {
                string2 = (String)object.next();
                if (string2 == null) continue;
                jsonGenerator.writeString(string2);
            }
            jsonGenerator.writeEndArray();
        }
        if (iterator.blurb != null) {
            jsonGenerator.writeStringField("blurb", iterator.blurb);
        }
        if (iterator.firstName != null) {
            jsonGenerator.writeStringField("firstName", iterator.firstName);
        }
        if (iterator.handle != null) {
            jsonGenerator.writeStringField("handle", iterator.handle);
        }
        if (iterator.jid != null) {
            jsonGenerator.writeStringField("jid", iterator.jid);
        }
        if (iterator.lastName != null) {
            jsonGenerator.writeStringField("lastName", iterator.lastName);
        }
        jsonGenerator.writeNumberField("latitude", iterator.latitude);
        jsonGenerator.writeNumberField("longitude", iterator.longitude);
        if (iterator.picUrl != null) {
            jsonGenerator.writeStringField("picUrl", iterator.picUrl);
        }
        if ((object = iterator.platformsWithSmulePass) != null) {
            jsonGenerator.writeFieldName("vip");
            jsonGenerator.writeStartArray();
            object = object.iterator();
            while (object.hasNext()) {
                string2 = (String)object.next();
                if (string2 == null) continue;
                jsonGenerator.writeString(string2);
            }
            jsonGenerator.writeEndArray();
        }
        if ((iterator = iterator.verifiedUrls) != null) {
            jsonGenerator.writeFieldName("verifiedUrls");
            jsonGenerator.writeStartArray();
            iterator = iterator.iterator();
            while (iterator.hasNext()) {
                object = (VerifiedUrl)iterator.next();
                if (object == null) continue;
                a.serialize(object, jsonGenerator, true);
            }
            jsonGenerator.writeEndArray();
        }
        if (bl) {
            jsonGenerator.writeEndObject();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(AccountIcon accountIcon, String hashSet, JsonParser jsonParser) throws IOException {
        if ("accountId".equals(hashSet)) {
            accountIcon.accountId = jsonParser.getValueAsLong();
            return;
        } else {
            if ("subApps".equals(hashSet)) {
                if (jsonParser.getCurrentToken() != JsonToken.START_ARRAY) {
                    accountIcon.appsWithSubscription = null;
                    return;
                }
                hashSet = new HashSet();
                do {
                    if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                        accountIcon.appsWithSubscription = hashSet;
                        return;
                    }
                    hashSet.add(jsonParser.getValueAsString(null));
                } while (true);
            }
            if ("blurb".equals(hashSet)) {
                accountIcon.blurb = jsonParser.getValueAsString(null);
                return;
            }
            if ("firstName".equals(hashSet)) {
                accountIcon.firstName = jsonParser.getValueAsString(null);
                return;
            }
            if ("handle".equals(hashSet)) {
                accountIcon.handle = jsonParser.getValueAsString(null);
                return;
            }
            if ("jid".equals(hashSet)) {
                accountIcon.jid = jsonParser.getValueAsString(null);
                return;
            }
            if ("lastName".equals(hashSet)) {
                accountIcon.lastName = jsonParser.getValueAsString(null);
                return;
            }
            if ("latitude".equals(hashSet)) {
                accountIcon.latitude = (float)jsonParser.getValueAsDouble();
                return;
            }
            if ("longitude".equals(hashSet)) {
                accountIcon.longitude = (float)jsonParser.getValueAsDouble();
                return;
            }
            if ("picUrl".equals(hashSet)) {
                accountIcon.picUrl = jsonParser.getValueAsString(null);
                return;
            }
            if ("vip".equals(hashSet)) {
                if (jsonParser.getCurrentToken() != JsonToken.START_ARRAY) {
                    accountIcon.platformsWithSmulePass = null;
                    return;
                }
                hashSet = new HashSet();
                do {
                    if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                        accountIcon.platformsWithSmulePass = hashSet;
                        return;
                    }
                    hashSet.add(jsonParser.getValueAsString(null));
                } while (true);
            }
            if (!"verifiedUrls".equals(hashSet)) return;
            {
                if (jsonParser.getCurrentToken() != JsonToken.START_ARRAY) {
                    accountIcon.verifiedUrls = null;
                    return;
                }
                hashSet = new ArrayList();
                do {
                    if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                        accountIcon.verifiedUrls = hashSet;
                        return;
                    }
                    hashSet.add((VerifiedUrl)a.parse(jsonParser));
                } while (true);
            }
        }
    }

    public /* synthetic */ Object parse(JsonParser jsonParser) throws IOException {
        return this.a(jsonParser);
    }

    public /* synthetic */ void parseField(Object object, String string2, JsonParser jsonParser) throws IOException {
        this.a((AccountIcon)object, string2, jsonParser);
    }

    public /* synthetic */ void serialize(Object object, JsonGenerator jsonGenerator, boolean bl) throws IOException {
        this.a((AccountIcon)object, jsonGenerator, bl);
    }
}

