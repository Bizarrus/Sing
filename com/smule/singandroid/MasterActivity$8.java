package com.smule.singandroid;

import com.smule.android.network.managers.PerformanceManager$PerformanceResponseCallback;
import com.smule.android.network.managers.PerformanceManager.PerformanceResponse;
import com.smule.android.network.models.PerformanceV2;
import com.smule.singandroid.pre_sing.PreSingActivity;
import com.smule.singandroid.pre_sing.PreSingActivity.StartupMode;

class MasterActivity$8 implements PerformanceManager$PerformanceResponseCallback {
    final /* synthetic */ Long f18871a;
    final /* synthetic */ MasterActivity f18872b;

    class C38681 implements Runnable {
        final /* synthetic */ MasterActivity$8 f18870a;

        C38681(MasterActivity$8 masterActivity$8) {
            this.f18870a = masterActivity$8;
        }

        public void run() {
            this.f18870a.f18872b.r();
        }
    }

    MasterActivity$8(MasterActivity masterActivity, Long l) {
        this.f18872b = masterActivity;
        this.f18871a = l;
    }

    public void handleResponse(PerformanceResponse performanceResponse) {
        if (performanceResponse.a()) {
            PerformanceV2 performanceV2 = performanceResponse.mPerformance;
            if (performanceV2 == null) {
                this.f18872b.r();
            } else {
                PreSingActivity.m24763a(this.f18872b).m24796a(StartupMode.DEEPLINK_SEED).m24792a(this.f18871a.longValue()).m24793a(performanceV2).a();
            }
        } else if (performanceResponse.a.e()) {
            this.f18872b.a(performanceResponse.a.f, true, new C38681(this));
        } else {
            this.f18872b.r();
        }
    }
}
