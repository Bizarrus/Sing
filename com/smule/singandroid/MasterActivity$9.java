package com.smule.singandroid;

import com.smule.android.logging.Log;
import com.smule.android.network.managers.PerformanceManager$PerformanceResponseCallback;
import com.smule.android.network.managers.PerformanceManager.PerformanceResponse;
import com.smule.android.network.models.PerformanceV2;
import com.smule.singandroid.pre_sing.PreSingActivity;
import com.smule.singandroid.pre_sing.PreSingActivity.StartupMode;

class MasterActivity$9 implements PerformanceManager$PerformanceResponseCallback {
    final /* synthetic */ MasterActivity f18876a;

    class C38691 implements Runnable {
        final /* synthetic */ MasterActivity$9 f18873a;

        C38691(MasterActivity$9 masterActivity$9) {
            this.f18873a = masterActivity$9;
        }

        public void run() {
            this.f18873a.f18876a.r();
        }
    }

    MasterActivity$9(MasterActivity masterActivity) {
        this.f18876a = masterActivity;
    }

    public void handleResponse(PerformanceResponse performanceResponse) {
        if (performanceResponse.a()) {
            final PerformanceV2 performanceV2 = performanceResponse.mPerformance;
            if (performanceV2 == null) {
                Log.d(MasterActivity.g, "OpenCall not found - Server response was null");
                this.f18876a.r();
                return;
            }
            this.f18876a.a(new Runnable(this) {
                final /* synthetic */ MasterActivity$9 f18875b;

                public void run() {
                    PreSingActivity.m24763a(this.f18875b.f18876a).m24796a(StartupMode.DEEPLINK_SEED_OPENCALL).m24793a(performanceV2).a();
                }
            });
            return;
        }
        Log.d(MasterActivity.g, "OpenCall not found - Server responded !ok");
        if (performanceResponse.a.e()) {
            this.f18876a.a(performanceResponse.a.f, true, new C38691(this));
        } else {
            this.f18876a.r();
        }
    }
}
