package com.smule.android.network.models;

import android.annotation.SuppressLint;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.ParsedResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserProfile extends ParsedResponse {
    private static final String f6898d = UserProfile.class.getName();
    @JsonProperty("accountIcon")
    public AccountIcon accountIcon;
    @JsonProperty("apps")
    public List<String> apps = new ArrayList();
    public int f6899b;
    public int f6900c;
    private HashMap<String, Integer> f6901e = new HashMap();
    private HashMap<String, String> f6902f = new HashMap();
    @JsonProperty("webUrl")
    public String webUrl;

    @JsonProperty("social")
    @SuppressLint({"ValueOfNotAllowedForNumberSubClasses"})
    public void setSocial(Map<String, Object> map) {
        this.f6899b = Integer.parseInt(map.get("numFollowers").toString());
        this.f6900c = Integer.parseInt(map.get("numFollowees").toString());
    }

    @JsonProperty("performances")
    @SuppressLint({"ValueOfNotAllowedForNumberSubClasses"})
    public void setPerformances(List<Map<String, String>> list) {
        for (Map map : list) {
            this.f6901e.put(map.get("app"), Integer.valueOf((String) map.get("numPerformances")));
        }
    }

    @JsonProperty("players")
    public void setPlayers(List<Map<String, String>> list) {
        for (Map map : list) {
            this.f6902f.put(map.get("app"), map.get("playerId"));
        }
    }

    public static UserProfile m8293a(NetworkResponse networkResponse) {
        return (UserProfile) ParsedResponse.m7676a(networkResponse, UserProfile.class);
    }

    public long getAccountId() {
        return this.accountIcon.accountId;
    }

    public String getHandle() {
        return this.accountIcon.handle;
    }

    public String getPictureUrl() {
        return this.accountIcon.picUrl;
    }

    public List<String> getApps() {
        return this.apps;
    }

    public int getNumberFollowers() {
        return this.f6899b;
    }

    public int getNumberFollowees() {
        return this.f6900c;
    }

    public HashMap<String, Integer> getPerformanceCountByApp() {
        return this.f6901e;
    }

    public HashMap<String, String> getPlayerIds() {
        return this.f6902f;
    }

    public String getWebUrl() {
        return this.webUrl;
    }

    public void m8294a(int i) {
        this.f6899b += i;
    }

    public boolean m8295b() {
        return this.accountIcon.m8240d();
    }
}
