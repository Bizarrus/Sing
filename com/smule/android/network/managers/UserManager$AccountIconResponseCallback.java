package com.smule.android.network.managers;

import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.managers.UserManager.AccountIconResponse;

public interface UserManager$AccountIconResponseCallback extends ResponseInterface<AccountIconResponse> {
    void handleResponse(AccountIconResponse accountIconResponse);
}
