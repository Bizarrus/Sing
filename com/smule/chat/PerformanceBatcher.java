package com.smule.chat;

import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.managers.PerformanceManager$PerformanceResponseCallback;
import com.smule.android.network.managers.PerformanceManager.PerformanceResponse;
import com.smule.android.network.managers.PerformanceManager.PerformancesResponse;
import com.smule.android.network.models.PerformanceV2;
import com.smule.chat.CollectionBatcher.CallbackAggregator;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class PerformanceBatcher {
    private static final PerformanceBatcher f18304b = new PerformanceBatcher();
    private CollectionBatcher<String, PerformancesResponse> f18305a = new CollectionBatcher<String, PerformancesResponse>(this, 25, 0) {
        final /* synthetic */ PerformanceBatcher f18301a;

        /* synthetic */ Object mo6306b(Set set) {
            return m19643a(set);
        }

        PerformancesResponse m19643a(Set<String> set) {
            return PerformanceManager.a().a(set);
        }
    };

    private static class PerformanceAggregator extends CallbackAggregator<String, PerformancesResponse> {
        private PerformanceManager$PerformanceResponseCallback f18302b;
        private PerformanceResponse f18303c;

        public PerformanceAggregator(String str, PerformanceManager$PerformanceResponseCallback performanceManager$PerformanceResponseCallback) {
            super(new HashSet(Collections.singleton(str)));
            this.f18302b = performanceManager$PerformanceResponseCallback;
        }

        public void m19646a(PerformancesResponse performancesResponse) {
            if (this.f18303c == null) {
                this.f18303c = new PerformanceResponse();
            }
            if (performancesResponse != null && performancesResponse.a()) {
                this.f18303c.a = performancesResponse.a;
                Iterator it = performancesResponse.mPerformances.iterator();
                while (it.hasNext()) {
                    PerformanceV2 performanceV2 = (PerformanceV2) it.next();
                    if (this.a.contains(performanceV2.performanceKey)) {
                        this.f18303c.mPerformance = performanceV2;
                        return;
                    }
                }
            }
        }

        public void mo6308a() {
            this.f18302b.handleResponse(this.f18303c);
        }
    }

    public static PerformanceBatcher m19648a() {
        return f18304b;
    }

    public void m19649a(String str, PerformanceManager$PerformanceResponseCallback performanceManager$PerformanceResponseCallback) {
        this.f18305a.m19095a(new PerformanceAggregator(str, performanceManager$PerformanceResponseCallback));
    }

    private PerformanceBatcher() {
    }
}
