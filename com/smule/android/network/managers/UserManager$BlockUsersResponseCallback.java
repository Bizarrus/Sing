package com.smule.android.network.managers;

import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.ResponseInterface;

public interface UserManager$BlockUsersResponseCallback extends ResponseInterface<NetworkResponse> {
    void handleResponse(NetworkResponse networkResponse);
}