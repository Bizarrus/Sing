package com.smule.android.facebook;

import android.app.Activity;
import com.facebook.FacebookRequestError;
import com.facebook.GraphRequest.Callback;
import com.facebook.GraphResponse;
import com.smule.android.logging.Log;
import com.smule.android.utils.Toaster;
import org.json.JSONException;

class MagicFacebook$6 implements Callback {
    final /* synthetic */ String f15977a;
    final /* synthetic */ Activity f15978b;

    public void onCompleted(GraphResponse graphResponse) {
        if (graphResponse == null) {
            Log.e(MagicFacebook.i(), "Facebook response is null, possible crash incoming");
        }
        FacebookRequestError error = graphResponse.getError();
        if (error != null) {
            Log.e(MagicFacebook.i(), "Failed to publish action " + error.getErrorMessage());
            return;
        }
        try {
            Log.b(MagicFacebook.i(), "publishAction - post id: " + graphResponse.getJSONObject().getString("id"));
        } catch (JSONException e) {
            Log.c(MagicFacebook.i(), "JSON error " + e.getMessage());
        }
        if (this.f15977a != null) {
            Toaster.a(this.f15978b, this.f15977a);
        }
    }
}
