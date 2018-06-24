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
import com.smule.android.network.api.OAuth2API;
import com.smule.android.network.core.CoreUtil;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.core.SnpRequest;
import java.util.Set;
import java.util.concurrent.Future;
import retrofit2.Call;

public class OAuth2Manager {
    private static final String a = OAuth2Manager.class.getName();
    private static OAuth2Manager b;
    private com.smule.android.network.api.OAuth2API c = MagicNetwork.a().a(com.smule.android.network.api.OAuth2API.class);

    private OAuth2Manager() {
    }

    public static OAuth2Manager a() {
        synchronized (OAuth2Manager.class) {
            if (b == null) {
                b = new OAuth2Manager();
            }
            OAuth2Manager oAuth2Manager = b;
            return oAuth2Manager;
        }
    }

    public  a(String string2, String string3, String string4, String string5, String string6) {
        return .a(NetworkUtils.a(this.c.getInfo(new SnpRequest(){
            public OAuth2API request;

            public OAuth2API setRequest(OAuth2API authRequest) {
                this.request = authRequest;
                return this;
            }
        }.setRequest(new Object(){
            public String clientId;
            public String redirectUri;
            public String responseType;
            public String scope;
            public String state;

            public OAuth2API setClientId(String string2) {
                this.clientId = string2;
                return this;
            }

            public OAuth2API setRedirectUri(String string2) {
                this.redirectUri = string2;
                return this;
            }

            public OAuth2API setResponseType(String string2) {
                this.responseType = string2;
                return this;
            }

            public OAuth2API setScope(String string2) {
                this.scope = string2;
                return this;
            }

            public OAuth2API setState(String string2) {
                this.state = string2;
                return this;
            }
        }.setResponseType(string2).setClientId(string3).setScope(string4).setState(string5).setRedirectUri(string6)))));
    }

    public Future<?> a(final String string2, final String string3, final String string4, final String string5, final String string6, final AuthorizeResponseCallback authorizeResponseCallback) {
        return MagicNetwork.a(new Runnable(){

            @Override
            public void run() {
                CoreUtil.a(authorizeResponseCallback, OAuth2Manager.this.b(string2, string3, string4, string5, string6));
            }
        });
    }

    public Future<?> a(final String string2, final String string3, final String string4, final String string5, final String string6, final InfoResponseCallback infoResponseCallback) {
        return MagicNetwork.a(new Runnable(){

            @Override
            public void run() {
                CoreUtil.a(infoResponseCallback, OAuth2Manager.this.a(string2, string3, string4, string5, string6));
            }
        });
    }

    public  b(String string2, String string3, String string4, String string5, String string6) {
        return .a(NetworkUtils.a(this.c.authorize(new SnpRequest(){
            public OAuth2API request;

            public OAuth2API setRequest(OAuth2API authRequest) {
                this.request = authRequest;
                return this;
            }
        }.setRequest(new .setResponseType(string2).setClientId(string3).setScope(string4).setState(string5).setRedirectUri(string6)))));
    }

    public static interface AuthorizeResponseCallback
    extends ResponseInterface<> {
        @Override
        public void handleResponse( var1);
    }

    public static interface InfoResponseCallback
    extends ResponseInterface<> {
        @Override
        public void handleResponse( var1);
    }

}

