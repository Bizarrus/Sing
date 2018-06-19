package com.smule.android.network.managers;

import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.models.UserProfile;

public interface UserManager$UserProfileResponseCallback extends ResponseInterface<UserProfile> {
    void handleResponse(UserProfile userProfile);
}
