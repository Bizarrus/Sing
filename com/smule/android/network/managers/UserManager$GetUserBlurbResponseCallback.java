package com.smule.android.network.managers;

import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.managers.UserManager.UserBlurbResponse;

public interface UserManager$GetUserBlurbResponseCallback extends ResponseInterface<UserBlurbResponse> {
    void handleResponse(UserBlurbResponse userBlurbResponse);
}
