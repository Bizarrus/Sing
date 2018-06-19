package com.smule.android.google.plus;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.smule.android.logging.Log;

class MagicGooglePlus$1 implements ResultCallback<Status> {
    final /* synthetic */ MagicGooglePlus f15999a;

    MagicGooglePlus$1(MagicGooglePlus magicGooglePlus) {
        this.f15999a = magicGooglePlus;
    }

    public /* synthetic */ void onResult(Result result) {
        m17771a((Status) result);
    }

    public void m17771a(Status status) {
        Log.e(MagicGooglePlus.c(), "User access revoked!");
        if (!status.isSuccess()) {
            MagicGooglePlus.a(this.f15999a).disconnect();
        }
        MagicGooglePlus.a(this.f15999a).connect();
    }
}
