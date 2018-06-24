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
import com.smule.android.network.api.InviteAPI;
import com.smule.android.network.core.CoreUtil;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkResponseCallback;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.core.SnpRequest;
import com.smule.android.network.models.CursorModel;
import com.smule.android.network.models.Invite;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Future;
import retrofit2.Call;

public class InviteManager {
    private static final String a = InviteManager.class.getName();
    private static InviteManager b;
    private com.smule.android.network.api.InviteAPI c = MagicNetwork.a().a(com.smule.android.network.api.InviteAPI.class);

    private InviteManager() {
    }

    public static InviteManager a() {
        synchronized (InviteManager.class) {
            if (b == null) {
                b = new InviteManager();
            }
            InviteManager inviteManager = b;
            return inviteManager;
        }
    }

    public  a(String string2, CursorModel cursorModel, Integer n) {
        return .a(NetworkUtils.a(this.c.listInvitesFollower(new SnpRequest(){
            public String app;
            public String cursor = null;
            public Integer limit = 25;

            public InviteAPI setApp(String string2) {
                this.app = string2;
                return this;
            }

            public InviteAPI setCursor(String string2) {
                if (string2 != null) {
                    this.cursor = string2;
                }
                return this;
            }

            public InviteAPI setLimit(Integer n) {
                if (n != null) {
                    this.limit = n;
                }
                return this;
            }
        }.setApp(string2).setCursor(cursorModel.next).setLimit(n))));
    }

    public  a(String string2, Integer n, Integer n2) {
        return .a(NetworkUtils.a(this.c.listInvites(new SnpRequest(){
            public String app;
            public Integer limit = 25;
            public Integer offset = 0;

            public InviteAPI setApp(String string2) {
                this.app = string2;
                return this;
            }

            public InviteAPI setLimit(Integer n) {
                if (n != null) {
                    this.limit = n;
                }
                return this;
            }

            public InviteAPI setOffset(Integer n) {
                if (n != null) {
                    this.offset = n;
                }
                return this;
            }
        }.setApp(string2).setOffset(n).setLimit(n2))));
    }

    public Future<?> a(CursorModel cursorModel, Integer n, ListInvitesFollowerResponseCallback listInvitesFollowerResponseCallback) {
        return this.a(null, cursorModel, n, listInvitesFollowerResponseCallback);
    }

    public Future<?> a(final String string2, final NetworkResponseCallback networkResponseCallback) {
        return MagicNetwork.a(new Runnable(){

            @Override
            public void run() {
                CoreUtil.a(networkResponseCallback, NetworkUtils.a(InviteManager.this.c.sendInvitesToAllFollowers(new SnpRequest(){
                    public String performanceKey;

                    public InviteAPI setPerformanceKey(String string2) {
                        this.performanceKey = string2;
                        return this;
                    }
                }.setPerformanceKey(string2))));
            }
        });
    }

    public Future<?> a(final String string2, final CursorModel cursorModel, final Integer n, final ListInvitesFollowerResponseCallback listInvitesFollowerResponseCallback) {
        return MagicNetwork.a(new Runnable(){

            @Override
            public void run() {
                CoreUtil.a(listInvitesFollowerResponseCallback, InviteManager.this.a(string2, cursorModel, n));
            }
        });
    }

    public Future<?> a(final String string2, final Collection<Long> collection, final NetworkResponseCallback networkResponseCallback) {
        return MagicNetwork.a(new Runnable(){

            @Override
            public void run() {
                CoreUtil.a(networkResponseCallback, NetworkUtils.a(InviteManager.this.c.sendInvites(new SnpRequest(){
                    public Collection<Long> accountIds;
                    public String performanceKey;

                    public InviteAPI setAccountIds(Collection<Long> collection) {
                        this.accountIds = collection;
                        return this;
                    }

                    public InviteAPI setPerformanceKey(String string2) {
                        this.performanceKey = string2;
                        return this;
                    }
                }.setPerformanceKey(string2).setAccountIds(collection))));
            }
        });
    }

    public Future<?> b(final String string2, final Collection<Long> collection, final NetworkResponseCallback networkResponseCallback) {
        return MagicNetwork.a(new Runnable(){

            @Override
            public void run() {
                CoreUtil.a(networkResponseCallback, NetworkUtils.a(InviteManager.this.c.sendChatInvites(new .setPerformanceKey(string2).setAccountIds(collection))));
            }
        });
    }

    public static interface ListInvitesFollowerResponseCallback
    extends ResponseInterface<> {
        @Override
        public void handleResponse( var1);
    }

    public static interface ListInvitesResponseCallback
    extends ResponseInterface<> {
        @Override
        public void handleResponse( var1);
    }

    public static interface SendingInvitesResponseCallback {
        public void a();

        public void a(boolean var1, boolean var2, boolean var3, boolean var4, int var5);
    }

}

