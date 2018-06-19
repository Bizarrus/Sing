package com.smule.android.network.managers;

import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.managers.UserManager.BlockedUsersResponse;

public interface UserManager$BlockedUsersResponseCallback extends ResponseInterface<BlockedUsersResponse> {
    void handleResponse(BlockedUsersResponse blockedUsersResponse);
}
