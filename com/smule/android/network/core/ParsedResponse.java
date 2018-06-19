package com.smule.android.network.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.smule.android.logging.Log;
import com.smule.android.utils.JsonUtils;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class ParsedResponse {
    private static final String f6683b = ParsedResponse.class.getName();
    public NetworkResponse f6684a;

    public static <T extends ParsedResponse> T m7676a(NetworkResponse networkResponse, Class<T> cls) {
        T t;
        if (networkResponse == null) {
            Log.m7774d(f6683b, "createFromNetworkResponse - got null networkResponse for class: " + cls.getName());
        }
        if (networkResponse == null || !networkResponse.m7850c() || networkResponse.f6766l == null) {
            try {
                ParsedResponse parsedResponse = (ParsedResponse) cls.newInstance();
            } catch (Throwable e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } catch (Throwable e2) {
                e2.printStackTrace();
                throw new RuntimeException(e2);
            }
        }
        t = (ParsedResponse) JsonUtils.a(networkResponse.f6766l, cls);
        t.f6684a = networkResponse;
        return t;
    }

    public boolean m7677a() {
        return this.f6684a != null && this.f6684a.m7850c();
    }
}
