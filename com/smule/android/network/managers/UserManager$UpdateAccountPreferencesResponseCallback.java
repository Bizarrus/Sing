package com.smule.android.network.managers;

import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.managers.UserManager.UpdateAccountPreferencesResponse;

public interface UserManager$UpdateAccountPreferencesResponseCallback extends ResponseInterface<UpdateAccountPreferencesResponse> {
    void handleResponse(UpdateAccountPreferencesResponse updateAccountPreferencesResponse);
}
