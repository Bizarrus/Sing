package com.smule.android.network.managers;

import com.fasterxml.jackson.databind.JsonNode;
import com.smule.android.network.core.NetworkResponse;

public class UserManager$DeferredLaunchParam {
    public String f17307a;
    public Type f17308b;
    public Feature f17309c;

    public enum Feature {
        ONBOARD
    }

    public enum Type {
        f17304a,
        ARR
    }

    public UserManager$DeferredLaunchParam(JsonNode jsonNode) throws IllegalArgumentException {
        if (jsonNode == null) {
            throw new IllegalArgumentException("launchParamNode cannot be null");
        }
        this.f17307a = NetworkResponse.b(jsonNode, "id");
        if (this.f17307a == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        String b = NetworkResponse.b(jsonNode, "type");
        if (b == null) {
            throw new IllegalArgumentException("type cannot be null");
        }
        this.f17308b = Type.valueOf(b);
        b = NetworkResponse.b(jsonNode, "feature");
        if (b == null) {
            throw new IllegalArgumentException("feature cannot be null");
        }
        this.f17309c = Feature.valueOf(b);
    }
}
