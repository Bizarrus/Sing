package com.smule.android.network.managers;

import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.ResponseInterface;

public interface ArrangementManager$ArrangementUpdateCallback extends ResponseInterface<NetworkResponse> {
    void handleResponse(NetworkResponse networkResponse);
}
