package com.smule.android.network.managers;

import com.fasterxml.jackson.databind.JsonNode;
import com.smule.android.logging.Log;
import com.smule.android.network.core.NetworkResponse;

public class UserManager$GuestLoginResponse {
    public long f17311a;
    public NetworkResponse f17312b;
    public Long f17313c;
    public boolean f17314d = true;
    public int f17315e;
    public int f17316f;
    public int f17317g;
    public UserManager$DeferredLaunchParam f17318h;

    public UserManager$GuestLoginResponse(NetworkResponse networkResponse) {
        this.f17312b = networkResponse;
        if (networkResponse != null && networkResponse.l != null) {
            JsonNode jsonNode;
            if (networkResponse.l.get("playerId") != null) {
                this.f17311a = networkResponse.l.get("playerId").asLong();
            }
            if (networkResponse.l.has("playerStat")) {
                jsonNode = networkResponse.l.get("playerStat");
                if (jsonNode.has("installDate")) {
                    this.f17313c = Long.valueOf(jsonNode.get("installDate").asLong());
                }
            }
            if (networkResponse.l.has("elControl")) {
                jsonNode = networkResponse.l.get("elControl");
                if (jsonNode.has("npt")) {
                    this.f17314d = jsonNode.get("npt").asBoolean(true);
                }
            }
            this.f17315e = NetworkResponse.a(networkResponse.l, "loginCount", 0);
            if (networkResponse.l.has("notificationCount")) {
                jsonNode = networkResponse.l.get("notificationCount");
                if (jsonNode.has("activity")) {
                    this.f17316f = jsonNode.get("activity").asInt(0);
                }
                if (jsonNode.has("pubInvite")) {
                    this.f17317g = jsonNode.get("pubInvite").asInt(0);
                }
            }
            if (networkResponse.l.has("launchParam")) {
                try {
                    this.f17318h = new UserManager$DeferredLaunchParam(networkResponse.l.get("launchParam"));
                } catch (Throwable e) {
                    Log.d(UserManager.R(), "Received invalid launchParam", e);
                }
            }
        }
    }
}
