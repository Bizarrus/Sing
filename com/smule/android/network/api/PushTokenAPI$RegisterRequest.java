package com.smule.android.network.api;

import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.SnpRequest;

public class PushTokenAPI$RegisterRequest extends SnpRequest {
    public String app = MagicNetwork.b();
    public String deviceId;
    public String deviceType = "AND";
    public Boolean gcm = Boolean.valueOf(true);
    public String pushToken;

    public String getDeviceId() {
        return this.deviceId;
    }

    public PushTokenAPI$RegisterRequest setDeviceId(String str) {
        this.deviceId = str;
        return this;
    }

    public String getPushToken() {
        return this.pushToken;
    }

    public PushTokenAPI$RegisterRequest setPushToken(String str) {
        this.pushToken = str;
        return this;
    }
}
