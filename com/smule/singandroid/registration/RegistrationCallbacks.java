/*
 * Decompiled with CFR 0_123.
 */
package com.smule.singandroid.registration;

import com.smule.android.network.core.NetworkResponse;

public interface RegistrationCallbacks {

    public static interface DeviceLookupFailedCallback {
        public void a(NetworkResponse var1);
    }

    public static interface LoggedInCallback {
        public void a();
    }

}

