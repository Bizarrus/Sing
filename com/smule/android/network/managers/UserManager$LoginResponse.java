package com.smule.android.network.managers;

import com.fasterxml.jackson.databind.JsonNode;
import com.smule.android.logging.Log;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.utils.EmailOptIn;
import java.util.ArrayList;
import java.util.List;

public class UserManager$LoginResponse extends ParsedResponse {
    public String f17319b;
    public int f17320c;
    public String f17321d;
    public long f17322e;
    public long f17323f;
    public String f17324g;
    public String f17325h;
    public String f17326i;
    public Long f17327j;
    public boolean f17328k = true;
    public int f17329l;
    public EmailOptIn f17330m;
    public int f17331n = 0;
    public String f17332o;
    public Boolean f17333p = Boolean.valueOf(false);
    public int f17334q = 0;
    public List<String> f17335r;
    public boolean f17336s;
    public UserManager$DeferredLaunchParam f17337t;
    public Boolean f17338u = Boolean.valueOf(false);
    public Boolean f17339v = Boolean.valueOf(false);

    public UserManager$LoginResponse(NetworkResponse networkResponse) {
        this.a = networkResponse;
        if (networkResponse != null && networkResponse.l != null) {
            JsonNode jsonNode;
            JsonNode jsonNode2 = networkResponse.l.has("loginResult") ? networkResponse.l.get("loginResult") : networkResponse.l;
            this.f17319b = NetworkResponse.b(jsonNode2, "sessionToken");
            this.f17320c = NetworkResponse.a(jsonNode2, "sessionTtl", -1);
            this.f17321d = NetworkResponse.b(jsonNode2, "refreshToken");
            this.f17323f = NetworkResponse.a(jsonNode2, "accountId", 0);
            this.f17324g = NetworkResponse.b(jsonNode2, "email");
            this.f17322e = NetworkResponse.a(jsonNode2, "playerId", 0);
            this.f17325h = NetworkResponse.b(jsonNode2, "handle");
            this.f17338u = Boolean.valueOf(NetworkResponse.a(jsonNode2, "handleNew", false));
            this.f17339v = Boolean.valueOf(NetworkResponse.a(jsonNode2, "handlePrefill", false));
            this.f17329l = NetworkResponse.a(jsonNode2, "loginCount", 0);
            this.f17330m = EmailOptIn.m18963a(NetworkResponse.a(jsonNode2, "newsletter", -1));
            this.f17332o = NetworkResponse.b(jsonNode2, "jid");
            this.f17333p = Boolean.valueOf(NetworkResponse.a(jsonNode2, "playerNewlyRegistered", false));
            this.f17336s = NetworkResponse.a(jsonNode2, "showEmailOpt", true);
            if (jsonNode2.get("picUrl") != null) {
                this.f17326i = jsonNode2.get("picUrl").asText();
            }
            if (jsonNode2.has("playerStat")) {
                jsonNode = jsonNode2.get("playerStat");
                if (jsonNode.has("installDate")) {
                    this.f17327j = Long.valueOf(jsonNode.get("installDate").asLong());
                }
            }
            if (jsonNode2.has("elControl")) {
                jsonNode = jsonNode2.get("elControl");
                if (jsonNode.has("npt")) {
                    this.f17328k = jsonNode.get("npt").asBoolean(true);
                }
            }
            if (jsonNode2.has("notificationCount")) {
                jsonNode = jsonNode2.get("notificationCount");
                if (jsonNode.has("activity")) {
                    this.f17331n = jsonNode.get("activity").asInt(0);
                }
                if (jsonNode.has("pubInvite")) {
                    this.f17334q = jsonNode.get("pubInvite").asInt(0);
                }
            }
            if (jsonNode2.has("hosts")) {
                jsonNode = jsonNode2.get("hosts");
                if (jsonNode.has("xmpp")) {
                    this.f17335r = m18482a(jsonNode.get("xmpp"));
                }
            }
            if (jsonNode2.has("launchParam")) {
                try {
                    this.f17337t = new UserManager$DeferredLaunchParam(jsonNode2.get("launchParam"));
                } catch (Throwable e) {
                    Log.d(UserManager.R(), "Received invalid launchParam", e);
                }
            }
        }
    }

    private List<String> m18482a(JsonNode jsonNode) {
        int i = 0;
        if (jsonNode == null) {
            return new ArrayList(0);
        }
        List<String> arrayList = new ArrayList(jsonNode.size());
        while (i < jsonNode.size()) {
            arrayList.add(jsonNode.get(i).asText());
            i++;
        }
        return arrayList;
    }
}
