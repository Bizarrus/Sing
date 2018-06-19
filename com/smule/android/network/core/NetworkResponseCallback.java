package com.smule.android.network.core;

public interface NetworkResponseCallback extends ResponseInterface<NetworkResponse> {
    void handleResponse(NetworkResponse networkResponse);
}
