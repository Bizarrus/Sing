/*
 * Decompiled with CFR 0_123.
 */
package com.smule.android.network.core;

import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.ResponseInterface;

public interface NetworkResponseCallback
extends ResponseInterface<NetworkResponse> {
    @Override
    public void handleResponse(NetworkResponse var1);
}

