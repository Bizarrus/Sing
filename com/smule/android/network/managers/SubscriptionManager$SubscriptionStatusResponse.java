package com.smule.android.network.managers;

import com.facebook.share.internal.ShareConstants;
import com.fasterxml.jackson.databind.JsonNode;
import com.smule.android.logging.Log;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.utils.JsonUtils;

public class SubscriptionManager$SubscriptionStatusResponse {
    final NetworkResponse f17168a;
    Boolean f17169b;
    long f17170c;
    String f17171d;
    boolean f17172e;
    final /* synthetic */ SubscriptionManager f17173f;

    SubscriptionManager$SubscriptionStatusResponse(SubscriptionManager subscriptionManager, NetworkResponse networkResponse) {
        this.f17173f = subscriptionManager;
        this.f17168a = networkResponse;
        if (this.f17168a != null && this.f17168a.j != null) {
            try {
                JsonNode jsonNode = (JsonNode) JsonUtils.m18984a().readValue(this.f17168a.j, JsonNode.class);
                if (jsonNode.has(ShareConstants.WEB_DIALOG_PARAM_DATA)) {
                    jsonNode = jsonNode.get(ShareConstants.WEB_DIALOG_PARAM_DATA);
                    if (jsonNode.has("isActive")) {
                        this.f17169b = Boolean.valueOf(jsonNode.get("isActive").booleanValue());
                    }
                    if (jsonNode.has("expireAt")) {
                        this.f17170c = jsonNode.get("expireAt").longValue();
                    }
                    if (jsonNode.has("status")) {
                        this.f17171d = jsonNode.get("status").textValue();
                    }
                    boolean z = jsonNode.has("skipTrial") && jsonNode.get("skipTrial").booleanValue();
                    this.f17172e = z;
                }
            } catch (Exception e) {
                Log.e(SubscriptionManager.i(), "Error parsing SubscriptionStatusResponse!");
            }
        }
    }
}
