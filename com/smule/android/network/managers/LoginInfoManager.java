/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 *  com.fasterxml.jackson.annotation.JsonProperty
 *  retrofit2.Call
 */
package com.smule.android.network.managers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.api.LoginInfoAPI;
import com.smule.android.network.core.CoreUtil;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.core.SnpRequest;
import com.smule.android.network.managers.NotificationBadgeManager;
import com.smule.android.network.managers.TopicsManager;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import retrofit2.Call;

public class LoginInfoManager {
    private static final String a = TopicsManager.class.getName();
    private static LoginInfoManager b;
    private com.smule.android.network.api.LoginInfoAPI c = MagicNetwork.a().a(com.smule.android.network.api.LoginInfoAPI.class);

    private LoginInfoManager() {
    }

    public static LoginInfoManager a() {
        synchronized (LoginInfoManager.class) {
            if (b == null) {
                b = new LoginInfoManager();
            }
            LoginInfoManager loginInfoManager = b;
            return loginInfoManager;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public  a(boolean bl) {
        List<String> list = bl ? Arrays.asList("notificationCount", "feedActivity") : Arrays.asList("notificationCount");
        list = new SnpRequest(){
            public List<String> reqInfo;

            public LoginInfoAPI setReqInfo(List<String> list) {
                if (list != null) {
                    this.reqInfo = list;
                }
                return this;
            }
        }.setReqInfo(list);
        if ((list = .a(NetworkUtils.a(this.c.getLoginInfo((Object)list)))).a() && list.notificationCount != null) {
            NotificationBadgeManager.a().a(list.notificationCount.get("activity"), list.notificationCount.get("pubInvite"));
        }
        return list;
    }

    public Future<?> a(final boolean bl, final LoginInfoResponseListener loginInfoResponseListener) {
        return MagicNetwork.a(new Runnable(){

            @Override
            public void run() {
                CoreUtil.a(loginInfoResponseListener, LoginInfoManager.this.a(bl));
            }
        });
    }

    public static interface LoginInfoResponseListener
    extends ResponseInterface<> {
        @Override
        public void handleResponse( var1);
    }

}

