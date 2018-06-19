package com.smule.android.network.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.smule.android.logging.Log;
import com.smule.android.utils.JsonUtils;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FeedItem {
    private static final String f6881e = FeedItem.class.getName();
    public PerformanceV2 f6882a;
    @JsonProperty("app")
    public String app;
    public AccountIcon f6883b;
    public Long f6884c;
    @JsonProperty("content")
    public String content;
    public boolean f6885d = false;
    @JsonProperty("objectType")
    public String objectType;
    @JsonProperty("objects")
    public List<FeedIcon> objects;
    @JsonProperty("subjects")
    public List<AccountIcon> subjects;
    @JsonProperty("time")
    public Date time;
    @JsonProperty("type")
    public String type;
    @JsonProperty("verb")
    public String verb;

    @JsonProperty("objects")
    public void setObjects(ArrayNode arrayNode) {
        Iterator it = arrayNode.iterator();
        while (it.hasNext()) {
            JsonNode jsonNode = (JsonNode) it.next();
            if (jsonNode.get("performanceIcon") != null) {
                this.f6882a = (PerformanceV2) JsonUtils.a(jsonNode.get("performanceIcon"), PerformanceV2.class);
            }
            if (jsonNode.get("accountIcon") != null) {
                this.f6883b = (AccountIcon) JsonUtils.a(jsonNode.get("accountIcon"), AccountIcon.class);
            }
        }
    }

    @JsonProperty("time")
    public void setTime(long j) {
        this.time = new Date(1000 * j);
    }

    @JsonProperty("subjects")
    public void setSubjects(JsonNode jsonNode) {
        this.subjects = JsonUtils.a(jsonNode.get("accountIcons"), new 1(this));
        if (jsonNode.get("total") != null) {
            this.f6884c = (Long) JsonUtils.a(jsonNode.get("total"), Long.TYPE);
        } else {
            this.f6884c = Long.valueOf(1);
        }
    }

    public boolean m8252a(List<String> list) {
        if (this.verb == null || this.verb.equals("")) {
            return false;
        }
        if (list.contains(this.verb.toUpperCase())) {
            return true;
        }
        Log.m7770b(f6881e, "Unsupported verb type, " + this.verb + ", processed in isValid() method. Returning as invalid object");
        return false;
    }

    public void m8251a(long j, long j2) {
        this.f6885d = false;
        if (this.f6882a != null && ((long) this.f6882a.playerId) == j2) {
            this.f6885d = true;
        }
        if (this.f6883b != null && this.f6883b.accountId == j) {
            this.f6885d = true;
        }
    }

    public String toString() {
        return "FeedItem [type=" + this.type + ", verb=" + this.verb + ", app=" + this.app + ", objectType=" + this.objectType + ", content=" + this.content + ", time=" + this.time + ", mine=" + this.f6885d + ", subjects=" + this.subjects + "]";
    }
}
