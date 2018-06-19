package com.smule.singandroid;

import com.smule.android.network.managers.PerformanceManager$PerformanceResponseCallback;
import com.smule.android.network.managers.PerformanceManager.PerformanceResponse;

class MasterActivity$12 implements PerformanceManager$PerformanceResponseCallback {
    final /* synthetic */ Long f18848a;
    final /* synthetic */ MasterActivity f18849b;

    MasterActivity$12(MasterActivity masterActivity, Long l) {
        this.f18849b = masterActivity;
        this.f18848a = l;
    }

    public void handleResponse(final PerformanceResponse performanceResponse) {
        if (performanceResponse.a()) {
            if (performanceResponse.mPerformance != null) {
                this.f18849b.a(new Runnable(this) {
                    final /* synthetic */ MasterActivity$12 f18847b;

                    public void run() {
                        this.f18847b.f18849b.a(performanceResponse.mPerformance, true, true, null, this.f18847b.f18848a, null, false, 0, null);
                    }
                });
            }
        } else if (performanceResponse.a.e()) {
            this.f18849b.a(performanceResponse.a.f, true, null);
        }
    }
}
