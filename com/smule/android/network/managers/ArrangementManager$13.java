package com.smule.android.network.managers;

import com.smule.android.network.api.ArrangementAPI$SendArrangementPlayedRequest;
import com.smule.android.network.core.NetworkUtils;

class ArrangementManager$13 implements Runnable {
    final /* synthetic */ String f16498a;
    final /* synthetic */ ArrangementManager f16499b;

    ArrangementManager$13(ArrangementManager arrangementManager, String str) {
        this.f16499b = arrangementManager;
        this.f16498a = str;
    }

    public void run() {
        NetworkUtils.m18104a(ArrangementManager.a(this.f16499b).sendArrangementPlayed(new ArrangementAPI$SendArrangementPlayedRequest().setArrKey(this.f16498a)));
    }
}
