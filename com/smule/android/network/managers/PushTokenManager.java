package com.smule.android.network.managers;

import com.smule.android.network.api.PushTokenAPI;
import com.smule.android.network.api.PushTokenAPI$RegisterRequest;
import com.smule.android.network.core.CoreUtil;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkResponseCallback;
import com.smule.android.network.core.NetworkUtils;

public class PushTokenManager {
    static final String f16929a = PushTokenManager.class.getName();
    protected static PushTokenManager f16930b = null;
    private PushTokenAPI f16931c = ((PushTokenAPI) MagicNetwork.a().a(PushTokenAPI.class));

    class C35831 implements Runnable {
        final /* synthetic */ NetworkResponseCallback f16925a;
        final /* synthetic */ String f16926b;
        final /* synthetic */ String f16927c;
        final /* synthetic */ PushTokenManager f16928d;

        public void run() {
            CoreUtil.m18079a(this.f16925a, this.f16928d.m18278a(this.f16926b, this.f16927c));
        }
    }

    private PushTokenManager() {
    }

    public static PushTokenManager m18277a() {
        if (f16930b == null) {
            f16930b = new PushTokenManager();
        }
        return f16930b;
    }

    public NetworkResponse m18278a(String str, String str2) {
        return NetworkUtils.m18104a(this.f16931c.register(new PushTokenAPI$RegisterRequest().setPushToken(str).setDeviceId(str2)));
    }
}
