/*
 * Decompiled with CFR 0_123.
 */
package com.smule.chat;

import com.smule.android.network.managers.FollowManager;
import com.smule.chat.CollectionBatcher;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class FollowingBatcher {
    private static final FollowingBatcher b = new FollowingBatcher();
    private CollectionBatcher<Long, Void> a;

    private FollowingBatcher() {
        this.a = new CollectionBatcher<Long, Void>(300, 0){

            Void a(Set<Long> set) {
                FollowManager.a().a(set);
                return null;
            }

            @Override
            /* synthetic */ Object b(Set set) {
                return this.a(set);
            }
        };
    }

    public static FollowingBatcher a() {
        return b;
    }

    public void a(long l, Runnable runnable) {
        this.a.a(new FollowingAggregator(l, runnable));
    }

    private static class FollowingAggregator
    extends CollectionBatcher.CallbackAggregator<Long, Void> {
        private Runnable b;

        public FollowingAggregator(Long l, Runnable runnable) {
            super(new HashSet<Long>(Collections.singleton(l)));
            this.b = runnable;
        }

        @Override
        public void a() {
            this.b.run();
        }

        @Override
        public void a(Void void_) {
        }
    }

}

