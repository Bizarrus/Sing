package com.smule.chat;

import com.smule.android.network.managers.FollowManager;
import com.smule.chat.CollectionBatcher.CallbackAggregator;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import twitter4j.HttpResponseCode;

public class FollowingBatcher {
    private static final FollowingBatcher f18167b = new FollowingBatcher();
    private CollectionBatcher<Long, Void> f18168a = new CollectionBatcher<Long, Void>(this, HttpResponseCode.MULTIPLE_CHOICES, 0) {
        final /* synthetic */ FollowingBatcher f18165a;

        /* synthetic */ Object mo6306b(Set set) {
            return m19469a(set);
        }

        Void m19469a(Set<Long> set) {
            FollowManager.m18204a().m18218a((Collection) set);
            return null;
        }
    };

    private static class FollowingAggregator extends CallbackAggregator<Long, Void> {
        private Runnable f18166b;

        public FollowingAggregator(Long l, Runnable runnable) {
            super(new HashSet(Collections.singleton(l)));
            this.f18166b = runnable;
        }

        public void m19473a(Void voidR) {
        }

        public void mo6308a() {
            this.f18166b.run();
        }
    }

    public static FollowingBatcher m19474a() {
        return f18167b;
    }

    public void m19475a(long j, Runnable runnable) {
        this.f18168a.m19095a(new FollowingAggregator(Long.valueOf(j), runnable));
    }

    private FollowingBatcher() {
    }
}
