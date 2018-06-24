/*
 * Decompiled with CFR 0_123.
 */
package com.smule.chat;

import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.managers.SparkManager;
import com.smule.android.network.models.SNPChat;
import com.smule.chat.CollectionBatcher;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ActiveChatBatcher {
    private static final ActiveChatBatcher c = new ActiveChatBatcher();
    private CollectionBatcher<SNPChat, NetworkResponse> a;
    private CollectionBatcher<SNPChat, NetworkResponse> b;

    private ActiveChatBatcher() {
        this.a = new CollectionBatcher<SNPChat, NetworkResponse>(300, 0){

            NetworkResponse a(Set<SNPChat> set) {
                return SparkManager.a().a(set, Collections.<SNPChat>emptyList());
            }

            @Override
            /* synthetic */ Object b(Set set) {
                return this.a(set);
            }
        };
        this.b = new CollectionBatcher<SNPChat, NetworkResponse>(300, 0){

            NetworkResponse a(Set<SNPChat> set) {
                return SparkManager.a().a(Collections.<SNPChat>emptyList(), set);
            }

            @Override
            /* synthetic */ Object b(Set set) {
                return this.a(set);
            }
        };
    }

    public static ActiveChatBatcher a() {
        return c;
    }

    public void a(SNPChat sNPChat, SparkManager.ActiveChatsUpdateCallback activeChatsUpdateCallback) {
        this.a.a(new ActiveChatAggregator(sNPChat, activeChatsUpdateCallback));
    }

    public void b(SNPChat sNPChat, SparkManager.ActiveChatsUpdateCallback activeChatsUpdateCallback) {
        this.b.a(new ActiveChatAggregator(sNPChat, activeChatsUpdateCallback));
    }

    private static class ActiveChatAggregator
    extends CollectionBatcher.CallbackAggregator<SNPChat, NetworkResponse> {
        private SparkManager.ActiveChatsUpdateCallback b;
        private NetworkResponse c;

        public ActiveChatAggregator(SNPChat sNPChat, SparkManager.ActiveChatsUpdateCallback activeChatsUpdateCallback) {
            super(new HashSet<SNPChat>(Collections.singleton(sNPChat)));
            this.b = activeChatsUpdateCallback;
        }

        @Override
        public void a() {
            if (this.b != null) {
                this.b.handleResponse(this.c);
            }
        }

        @Override
        public void a(NetworkResponse networkResponse) {
            if (networkResponse != null) {
                this.c = networkResponse;
                return;
            }
            this.c = NetworkResponse.a();
        }
    }

}

