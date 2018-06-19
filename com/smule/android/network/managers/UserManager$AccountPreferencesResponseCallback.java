package com.smule.android.network.managers;

import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.managers.UserManager.AccountPreferencesResponse;

public interface UserManager$AccountPreferencesResponseCallback extends ResponseInterface<AccountPreferencesResponse> {
    void handleResponse(AccountPreferencesResponse accountPreferencesResponse);
}
