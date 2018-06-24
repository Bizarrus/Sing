/*
 * Decompiled with CFR 0_123.
 */
package com.smule.singandroid;

import com.smule.android.logging.EventLogger2;
import com.smule.android.logging.Log;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.preference.MagicPreferences;

public class SingNPTMembershipPersisterDelegate
implements EventLogger2 {
    @Override
    public void a(boolean bl) {
        Log.b("npt", "NPT sampling saving as " + bl);
        MagicPreferences.a(SingApplication.g(), "NPT_ENABLED", bl);
    }

    @Override
    public boolean a() {
        return MagicPreferences.b(SingApplication.g(), "NPT_ENABLED", true);
    }
}

