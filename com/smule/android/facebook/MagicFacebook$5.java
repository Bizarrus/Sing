package com.smule.android.facebook;

import com.facebook.GraphRequest.Callback;
import com.facebook.GraphResponse;
import com.smule.android.logging.Log;

class MagicFacebook$5 implements Callback {
    final /* synthetic */ MagicFacebook f15976a;

    MagicFacebook$5(MagicFacebook magicFacebook) {
        this.f15976a = magicFacebook;
    }

    public void onCompleted(GraphResponse graphResponse) {
        if (graphResponse.getError() != null) {
            Log.d(MagicFacebook.i(), "Error posting to Facebook feed: " + graphResponse.getError().getErrorMessage());
            if (MagicFacebook.b(this.f15976a) != null) {
                MagicFacebook.b(this.f15976a).mo6609b();
                MagicFacebook.a(this.f15976a, null);
            }
        } else if (MagicFacebook.b(this.f15976a) != null) {
            MagicFacebook.b(this.f15976a).mo6608a();
            MagicFacebook.a(this.f15976a, null);
        }
    }
}
