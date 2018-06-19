package com.smule.chat;

import com.smule.android.network.managers.SparkManager;
import com.smule.android.network.managers.SparkManager.MuteStateResponse;
import com.smule.android.network.managers.SparkManager.MuteStateResponseCallback;
import com.smule.android.network.models.SNPChat;
import com.smule.chat.CollectionBatcher.CallbackAggregator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import twitter4j.HttpResponseCode;

public class MuteBatcher {
    private static final MuteBatcher f18280b = new MuteBatcher();
    private CollectionBatcher<SNPChat, MuteStateResponse> f18281a = new CollectionBatcher<SNPChat, MuteStateResponse>(this, HttpResponseCode.MULTIPLE_CHOICES, 0) {
        final /* synthetic */ MuteBatcher f18277a;

        /* synthetic */ Object mo6306b(Set set) {
            return m19632a(set);
        }

        MuteStateResponse m19632a(Set<SNPChat> set) {
            return SparkManager.m18359a().m18361a((Collection) set);
        }
    };

    private static class MuteAggregator extends CallbackAggregator<SNPChat, MuteStateResponse> {
        private MuteStateResponseCallback f18278b;
        private MuteStateResponse f18279c;

        public MuteAggregator(SNPChat sNPChat, MuteStateResponseCallback muteStateResponseCallback) {
            super(new HashSet(Collections.singleton(sNPChat)));
            this.f18278b = muteStateResponseCallback;
        }

        public void m19635a(MuteStateResponse muteStateResponse) {
            if (this.f18279c == null) {
                this.f18279c = new MuteStateResponse();
                this.f18279c.muted = new ArrayList();
                this.f18279c.notMuted = new ArrayList();
            }
            if (muteStateResponse != null && muteStateResponse.a()) {
                this.f18279c.a = muteStateResponse.a;
                for (SNPChat sNPChat : muteStateResponse.muted) {
                    if (this.a.contains(sNPChat)) {
                        this.f18279c.muted.add(sNPChat);
                    }
                }
                for (SNPChat sNPChat2 : muteStateResponse.notMuted) {
                    if (this.a.contains(sNPChat2)) {
                        this.f18279c.notMuted.add(sNPChat2);
                    }
                }
            }
        }

        public void mo6308a() {
            this.f18278b.handleResponse(this.f18279c);
        }
    }

    public static MuteBatcher m19637a() {
        return f18280b;
    }

    public void m19638a(SNPChat sNPChat, MuteStateResponseCallback muteStateResponseCallback) {
        this.f18281a.m19095a(new MuteAggregator(sNPChat, muteStateResponseCallback));
    }

    private MuteBatcher() {
    }
}
