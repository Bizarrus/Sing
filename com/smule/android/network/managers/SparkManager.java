/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  com.fasterxml.jackson.annotation.JsonProperty
 *  retrofit2.Call
 */
package com.smule.android.network.managers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.api.SparkAPI;
import com.smule.android.network.core.CoreUtil;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.core.SnpRequest;
import com.smule.android.network.models.SNPChat;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;
import retrofit2.Call;

public class SparkManager {
    private static SparkManager a;
    private com.smule.android.network.api.SparkAPI b = MagicNetwork.a().a(com.smule.android.network.api.SparkAPI.class);

    private SparkManager() {
    }

    public static SparkManager a() {
        if (a == null) {
            a = new SparkManager();
        }
        return a;
    }

    public NetworkResponse a(Collection<SNPChat> collection, Collection<SNPChat> collection2) {
        return NetworkUtils.a(this.b.setActiveChats(new SnpRequest(){
            public Collection<SNPChat> add;
            public Collection<SNPChat> remove;

            public SparkAPI setAdd(Collection<SNPChat> collection) {
                this.add = collection;
                return this;
            }

            public SparkAPI setRemove(Collection<SNPChat> collection) {
                this.remove = collection;
                return this;
            }
        }.setAdd(collection).setRemove(collection2)));
    }

    public  a(Collection<SNPChat> collection) {
        return .a(NetworkUtils.a(this.b.getMuteState(new SnpRequest(){
            public Collection<SNPChat> chats;

            public SparkAPI setChats(Collection<SNPChat> collection) {
                this.chats = collection;
                return this;
            }
        }.setChats(collection))));
    }

    public Future<?> a(final OfflineMessageCallback offlineMessageCallback) {
        return MagicNetwork.a(new Runnable(){

            @Override
            public void run() {
                NetworkResponse networkResponse = NetworkUtils.a(SparkManager.this.b.getOfflineMessageCount(new SnpRequest()));
                if (offlineMessageCallback != null) {
                    CoreUtil.a(offlineMessageCallback, .a(networkResponse, .class));
                }
            }
        });
    }

    public Future<?> a(SNPChat sNPChat, MuteStateUpdateCallback muteStateUpdateCallback) {
        return this.a(Collections.singletonList(sNPChat), muteStateUpdateCallback);
    }

    public Future<?> a(final String string2, final int n, final ActiveChatsCallback activeChatsCallback) {
        return MagicNetwork.a(new Runnable(){

            @Override
            public void run() {
                 activeChatsResponse = .a(NetworkUtils.a(SparkManager.this.b.getActiveChats(new SnpRequest(){
                    public Integer limit;
                    public String type;

                    public SparkAPI setLimit(Integer n) {
                        this.limit = n;
                        return this;
                    }

                    public SparkAPI setType(String string2) {
                        this.type = string2;
                        return this;
                    }
                }.setType(string2).setLimit(n))));
                if (activeChatsCallback != null) {
                    CoreUtil.a(activeChatsCallback, activeChatsResponse);
                }
            }
        });
    }

    public Future<?> a(List<SNPChat> list, MuteStateUpdateCallback muteStateUpdateCallback) {
        return this.a(list, Collections.<SNPChat>emptyList(), muteStateUpdateCallback);
    }

    public Future<?> a(final List<SNPChat> list, final List<SNPChat> list2, final MuteStateUpdateCallback muteStateUpdateCallback) {
        return MagicNetwork.a(new Runnable(){

            @Override
            public void run() {
                NetworkResponse networkResponse = NetworkUtils.a(SparkManager.this.b.setMuteState(new SnpRequest(){
                    public List<SNPChat> add;
                    public List<SNPChat> remove;

                    public SparkAPI setAdd(List<SNPChat> list) {
                        this.add = list;
                        return this;
                    }

                    public SparkAPI setRemove(List<SNPChat> list) {
                        this.remove = list;
                        return this;
                    }
                }.setAdd(list).setRemove(list2)));
                if (muteStateUpdateCallback != null) {
                    CoreUtil.a(muteStateUpdateCallback, MuteStateUpdateResponse.a(networkResponse, MuteStateUpdateResponse.class));
                }
            }
        });
    }

    public Future<?> b(SNPChat sNPChat, MuteStateUpdateCallback muteStateUpdateCallback) {
        return this.b(Collections.singletonList(sNPChat), muteStateUpdateCallback);
    }

    public Future<?> b(List<SNPChat> list, MuteStateUpdateCallback muteStateUpdateCallback) {
        return this.a(Collections.<SNPChat>emptyList(), list, muteStateUpdateCallback);
    }

    public static interface ActiveChatsCallback
    extends ResponseInterface<> {
    }

    public static interface ActiveChatsUpdateCallback
    extends ResponseInterface<NetworkResponse> {
    }

    public static interface MuteStateResponseCallback
    extends ResponseInterface<> {
        @Override
        public void handleResponse( var1);
    }

    public static interface MuteStateUpdateCallback
    extends ResponseInterface<MuteStateUpdateResponse> {
        @Override
        public void handleResponse(MuteStateUpdateResponse var1);
    }

    public static class MuteStateUpdateResponse
    extends ParsedResponse {
    }

    public static interface OfflineMessageCallback
    extends ResponseInterface<> {
        @Override
        public void handleResponse( var1);
    }

}

