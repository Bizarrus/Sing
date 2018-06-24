/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 *  com.fasterxml.jackson.annotation.JsonProperty
 *  com.fasterxml.jackson.core.type.TypeReference
 *  com.fasterxml.jackson.databind.JsonNode
 *  com.fasterxml.jackson.databind.node.ArrayNode
 */
package com.smule.android.network.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.smule.android.logging.Log;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.FeedIcon;
import com.smule.android.network.models.FeedItem;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.utils.JsonUtils;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class FeedItem {
    private static final String e = FeedItem.class.getName();
    public PerformanceV2 a;
    @JsonProperty(value="app")
    public String app;
    public AccountIcon b;
    public Long c;
    @JsonProperty(value="content")
    public String content;
    public boolean d = false;
    @JsonProperty(value="objectType")
    public String objectType;
    @JsonProperty(value="objects")
    public List<FeedIcon> objects;
    @JsonProperty(value="subjects")
    public List<AccountIcon> subjects;
    @JsonProperty(value="time")
    public Date time;
    @JsonProperty(value="type")
    public String type;
    @JsonProperty(value="verb")
    public String verb;

    public void a(long l, long l2) {
        this.d = false;
        if (this.a != null && (long)this.a.playerId == l2) {
            this.d = true;
        }
        if (this.b != null && this.b.accountId == l) {
            this.d = true;
        }
    }

    public boolean a(List<String> list) {
        if (this.verb == null || this.verb.equals("")) {
            return false;
        }
        if (list.contains(this.verb.toUpperCase())) {
            return true;
        }
        Log.b(e, "Unsupported verb type, " + this.verb + ", processed in isValid() method. Returning as invalid object");
        return false;
    }

    @JsonProperty(value="objects")
    public void setObjects(ArrayNode object) {
        object = object.iterator();
        while (object.hasNext()) {
            JsonNode jsonNode = (JsonNode)object.next();
            if (jsonNode.get("performanceIcon") != null) {
                this.a = JsonUtils.a(jsonNode.get("performanceIcon"), PerformanceV2.class);
            }
            if (jsonNode.get("accountIcon") == null) continue;
            this.b = JsonUtils.a(jsonNode.get("accountIcon"), AccountIcon.class);
        }
    }

    @JsonProperty(value="subjects")
    public void setSubjects(JsonNode jsonNode) {
        this.subjects = JsonUtils.a(jsonNode.get("accountIcons"), new TypeReference<List<AccountIcon>>(this){
            final /* synthetic */ FeedItem a;
            {
                this.a = feedItem;
            }
        });
        if (jsonNode.get("total") != null) {
            this.c = JsonUtils.a(jsonNode.get("total"), Long.TYPE);
            return;
        }
        this.c = 1;
    }

    @JsonProperty(value="time")
    public void setTime(long l) {
        this.time = new Date(1000 * l);
    }

    public String toString() {
        return "FeedItem [type=" + this.type + ", verb=" + this.verb + ", app=" + this.app + ", objectType=" + this.objectType + ", content=" + this.content + ", time=" + this.time + ", mine=" + this.d + ", subjects=" + this.subjects + "]";
    }

}

