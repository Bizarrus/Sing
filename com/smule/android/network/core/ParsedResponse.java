/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 *  com.fasterxml.jackson.databind.JsonNode
 */
package com.smule.android.network.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.smule.android.logging.Log;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.utils.JsonUtils;

@JsonIgnoreProperties(ignoreUnknown=true)
public abstract class ParsedResponse {
    private static final String b = ParsedResponse.class.getName();
    public NetworkResponse a;

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static <T extends ParsedResponse> T a(NetworkResponse networkResponse, Class<T> object) {
        if (networkResponse == null) {
            Log.d(b, "createFromNetworkResponse - got null networkResponse for class: " + object.getName());
        }
        if (networkResponse != null && networkResponse.c() && networkResponse.l != null) {
            object = (ParsedResponse)JsonUtils.a(networkResponse.l, object);
        } else {
            object = (ParsedResponse)object.newInstance();
        }
        object.a = networkResponse;
        return (T)object;
        catch (InstantiationException instantiationException) {
            instantiationException.printStackTrace();
            throw new RuntimeException(instantiationException);
        }
        catch (IllegalAccessException illegalAccessException) {
            illegalAccessException.printStackTrace();
            throw new RuntimeException(illegalAccessException);
        }
    }

    public boolean a() {
        if (this.a != null && this.a.c()) {
            return true;
        }
        return false;
    }
}

