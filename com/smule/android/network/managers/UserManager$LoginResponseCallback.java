package com.smule.android.network.managers;

import com.smule.android.network.core.ResponseInterface;

public interface UserManager$LoginResponseCallback extends ResponseInterface<UserManager$LoginResponse> {
    void handleResponse(UserManager$LoginResponse userManager$LoginResponse);
}
