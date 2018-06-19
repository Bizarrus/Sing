package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class SocialAPI$ListActivityNotificationsRequest extends SnpRequest {
    public Integer limit = Integer.valueOf(10);
    public Integer offset = Integer.valueOf(0);

    public SocialAPI$ListActivityNotificationsRequest setOffset(Integer num) {
        if (num != null) {
            this.offset = num;
        }
        return this;
    }

    public SocialAPI$ListActivityNotificationsRequest setLimit(Integer num) {
        if (num != null) {
            this.limit = num;
        }
        return this;
    }
}
