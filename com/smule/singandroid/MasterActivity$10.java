package com.smule.singandroid;

import com.smule.android.logging.Log;
import com.smule.android.network.managers.ArrangementManager$ArrangementVersionCallback;
import com.smule.android.network.managers.ArrangementManager.ArrangementVersionResponse;
import com.smule.android.songbook.SongbookEntry;
import com.smule.singandroid.SingBundle.Builder;
import com.smule.singandroid.SingBundle.PerformanceType;
import com.smule.singandroid.pre_sing.PreSingActivity;
import com.smule.singandroid.pre_sing.PreSingActivity.StartupMode;

class MasterActivity$10 implements ArrangementManager$ArrangementVersionCallback {
    final /* synthetic */ String f18835a;
    final /* synthetic */ boolean f18836b;
    final /* synthetic */ PerformanceType f18837c;
    final /* synthetic */ int f18838d;
    final /* synthetic */ boolean f18839e;
    final /* synthetic */ Long f18840f;
    final /* synthetic */ MasterActivity f18841g;

    class C38631 implements Runnable {
        final /* synthetic */ MasterActivity$10 f18832a;

        C38631(MasterActivity$10 masterActivity$10) {
            this.f18832a = masterActivity$10;
        }

        public void run() {
            this.f18832a.f18841g.s();
        }
    }

    MasterActivity$10(MasterActivity masterActivity, String str, boolean z, PerformanceType performanceType, int i, boolean z2, Long l) {
        this.f18841g = masterActivity;
        this.f18835a = str;
        this.f18836b = z;
        this.f18837c = performanceType;
        this.f18838d = i;
        this.f18839e = z2;
        this.f18840f = l;
    }

    public void handleResponse(final ArrangementVersionResponse arrangementVersionResponse) {
        if (arrangementVersionResponse == null || !arrangementVersionResponse.a() || arrangementVersionResponse.mArrangementVersion == null) {
            int i;
            Log.d(MasterActivity.g, "launchArrangement for key " + this.f18835a + " failed; " + (arrangementVersionResponse == null ? "NULL reply" : "error in call"));
            if (arrangementVersionResponse == null || arrangementVersionResponse.a == null) {
                i = 0;
            } else {
                i = arrangementVersionResponse.a.f;
            }
            this.f18841g.a(i, false, new C38631(this));
            return;
        }
        this.f18841g.a(new Runnable(this) {
            final /* synthetic */ MasterActivity$10 f18834b;

            public void run() {
                Log.c(MasterActivity.g, "Starting song flow for key " + this.f18834b.f18835a);
                SongbookEntry a = SongbookEntry.m18743a(arrangementVersionResponse.mArrangementVersion);
                PreSingActivity.m24763a(this.f18834b.f18841g).m24796a(StartupMode.DEEPLINK_ARR).m24794a(a).m24797a("lk_url").m24792a(this.f18834b.f18840f.longValue()).m24795a(new Builder().m21613a(a).m21615a(this.f18834b.f18836b ? this.f18834b.f18837c : PerformanceType.UNDEFINED).m21622b(this.f18834b.f18838d).m21627f(this.f18834b.f18839e).m21618a(this.f18834b.f18840f).m21621a()).m24798a(this.f18834b.f18836b).a();
            }
        });
    }
}
