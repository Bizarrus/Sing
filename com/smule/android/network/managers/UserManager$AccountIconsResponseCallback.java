package com.smule.android.network.managers;

import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.managers.UserManager.AccountIconsResponse;

public interface UserManager$AccountIconsResponseCallback extends ResponseInterface<AccountIconsResponse> {
    void handleResponse(AccountIconsResponse accountIconsResponse);
}
