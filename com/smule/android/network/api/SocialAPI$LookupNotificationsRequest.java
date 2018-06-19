package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;
import java.util.List;

public class SocialAPI$LookupNotificationsRequest extends SnpRequest {
    public List<String> notificationKeys;

    public SocialAPI$LookupNotificationsRequest setNotificationKeys(List<String> list) {
        this.notificationKeys = list;
        return this;
    }
}
