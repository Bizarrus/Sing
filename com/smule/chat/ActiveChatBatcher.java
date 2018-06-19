package com.smule.chat;

import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.managers.SparkManager;
import com.smule.android.network.managers.SparkManager.ActiveChatsUpdateCallback;
import com.smule.android.network.models.SNPChat;
import com.smule.chat.CollectionBatcher.CallbackAggregator;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import twitter4j.HttpResponseCode;

public class ActiveChatBatcher {
    private static final ActiveChatBatcher f17912c = new ActiveChatBatcher();
    private CollectionBatcher<SNPChat, NetworkResponse> f17913a = new CollectionBatcher<SNPChat, NetworkResponse>(this, HttpResponseCode.MULTIPLE_CHOICES, 0) {
        final /* synthetic */ ActiveChatBatcher f17908a;

        /* synthetic */ Object mo6306b(Set set) {
            return m19114a(set);
        }

        NetworkResponse m19114a(Set<SNPChat> set) {
            return SparkManager.m18359a().m18360a((Collection) set, Collections.emptyList());
        }
    };
    private CollectionBatcher<SNPChat, NetworkResponse> f17914b = new CollectionBatcher<SNPChat, NetworkResponse>(this, HttpResponseCode.MULTIPLE_CHOICES, 0) {
        final /* synthetic */ ActiveChatBatcher f17909a;

        /* synthetic */ Object mo6306b(Set set) {
            return m19116a(set);
        }

        NetworkResponse m19116a(Set<SNPChat> set) {
            return SparkManager.m18359a().m18360a(Collections.emptyList(), (Collection) set);
        }
    };

    private static class ActiveChatAggregator extends CallbackAggregator<SNPChat, NetworkResponse> {
        private ActiveChatsUpdateCallback f17910b;
        private NetworkResponse f17911c;

        public ActiveChatAggregator(SNPChat sNPChat, ActiveChatsUpdateCallback activeChatsUpdateCallback) {
            super(new HashSet(Collections.singleton(sNPChat)));
            this.f17910b = activeChatsUpdateCallback;
        }

        public void m19119a(NetworkResponse networkResponse) {
            if (networkResponse != null) {
                this.f17911c = networkResponse;
            } else {
                this.f17911c = NetworkResponse.a();
            }
        }

        public void mo6308a() {
            if (this.f17910b != null) {
                this.f17910b.handleResponse(this.f17911c);
            }
        }
    }

    public static ActiveChatBatcher m19121a() {
        return f17912c;
    }

    public void m19122a(SNPChat sNPChat, ActiveChatsUpdateCallback activeChatsUpdateCallback) {
        this.f17913a.m19095a(new ActiveChatAggregator(sNPChat, activeChatsUpdateCallback));
    }

    public void m19123b(SNPChat sNPChat, ActiveChatsUpdateCallback activeChatsUpdateCallback) {
        this.f17914b.m19095a(new ActiveChatAggregator(sNPChat, activeChatsUpdateCallback));
    }

    private ActiveChatBatcher() {
    }
}
