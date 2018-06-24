/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  retrofit2.Call
 */
package com.smule.android.network.managers;

import com.smule.android.network.api.PushTokenAPI;
import com.smule.android.network.core.CoreUtil;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkResponseCallback;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.SnpRequest;
import retrofit2.Call;

public class PushTokenManager {
    static final String a = PushTokenManager.class.getName();
    protected static PushTokenManager b = null;
    private com.smule.android.network.api.PushTokenAPI c = MagicNetwork.a().a(com.smule.android.network.api.PushTokenAPI.class);

    private PushTokenManager() {
    }

    public static PushTokenManager a() {
        if (b == null) {
            b = new PushTokenManager();
        }
        return b;
    }

    public NetworkResponse a(String string2, String string3) {
        return NetworkUtils.a(this.c.register(new SnpRequest(){
            public String app = MagicNetwork.b();
            public String deviceId;
            public String deviceType = "AND";
            public java.lang.Boolean gcm = true;
            public java.lang.Boolean notifEnabled;
            public String pushToken;

            public String getDeviceId() {
                return this.deviceId;
            }

            public boolean getNotifEnabled() {
                return this.notifEnabled;
            }

            public String getPushToken() {
                return this.pushToken;
            }

            public PushTokenAPI setDeviceId(String string2) {
                this.deviceId = string2;
                return this;
            }

            public PushTokenAPI setNotifEnabled(boolean bl) {
                this.notifEnabled = bl;
                return this;
            }

            public PushTokenAPI setPushToken(String string2) {
                this.pushToken = string2;
                return this;
            }
        }.setPushToken(string2).setDeviceId(string3)));
    }

    public NetworkResponse a(String string2, String string3, boolean bl) {
        return NetworkUtils.a(this.c.register(new .setPushToken(string2).setDeviceId(string3).setNotifEnabled(bl)));
    }

}

