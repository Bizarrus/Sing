package com.smule.android.network.managers;

import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.managers.UserManager.RegistrationResponse;

public interface UserManager$RegistrationResponseCallback extends ResponseInterface<RegistrationResponse> {
    void handleResponse(RegistrationResponse registrationResponse);
}
