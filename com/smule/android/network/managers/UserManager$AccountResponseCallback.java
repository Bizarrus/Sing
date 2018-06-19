package com.smule.android.network.managers;

import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.managers.UserManager.AccountResponse;

public interface UserManager$AccountResponseCallback extends ResponseInterface<AccountResponse> {
    void handleResponse(AccountResponse accountResponse);
}
