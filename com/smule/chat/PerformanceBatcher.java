/*
 * Decompiled with CFR 0_123.
 */
package com.smule.chat;

import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.models.PerformanceV2;
import com.smule.chat.CollectionBatcher;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class PerformanceBatcher {
    private static final PerformanceBatcher b = new PerformanceBatcher();
    private CollectionBatcher<String, PerformanceManager.PerformancesResponse> a;

    private PerformanceBatcher() {
        this.a = new CollectionBatcher<String, PerformanceManager.PerformancesResponse>(25, 0){

            PerformanceManager.PerformancesResponse a(Set<String> set) {
                return com.smule.android.network.managers.PerformanceManager.a().a(set);
            }

            @Override
            /* synthetic */ Object b(Set set) {
                return this.a(set);
            }
        };
    }

    public static PerformanceBatcher a() {
        return b;
    }

    public void a(String string2, PerformanceManager performanceResponseCallback) {
        this.a.a(new PerformanceAggregator(string2, performanceResponseCallback));
    }

    private static class PerformanceAggregator
    extends CollectionBatcher.CallbackAggregator<String, PerformanceManager.PerformancesResponse> {
        private PerformanceManager b;
        private PerformanceManager.PerformanceResponse c;

        public PerformanceAggregator(String string2, PerformanceManager performanceResponseCallback) {
            super(new HashSet<String>(Collections.singleton(string2)));
            this.b = performanceResponseCallback;
        }

        @Override
        public void a() {
            this.b.handleResponse(this.c);
        }

        @Override
        public void a(PerformanceManager.PerformancesResponse object) {
            if (this.c == null) {
                this.c = new PerformanceManager.PerformanceResponse();
            }
            if (object != null && object.a()) {
                this.c.a = object.a;
                for (PerformanceV2 performanceV2 : object.mPerformances) {
                    if (!this.a.contains(performanceV2.performanceKey)) continue;
                    this.c.mPerformance = performanceV2;
                    break;
                }
            }
        }
    }

}

