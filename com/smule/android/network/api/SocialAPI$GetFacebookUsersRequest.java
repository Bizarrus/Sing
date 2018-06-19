package com.smule.android.network.api;

import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.SnpRequest;
import java.util.List;

public class SocialAPI$GetFacebookUsersRequest extends SnpRequest {
    public List<String> afbIds;
    public String app;
    public Boolean autoFollow;
    public String fbAppId = MagicNetwork.d().getFacebookAppId();

    public SocialAPI$GetFacebookUsersRequest setAfbIds(List<String> list) {
        this.afbIds = list;
        return this;
    }

    public SocialAPI$GetFacebookUsersRequest setThisAppOnly(boolean z) {
        if (z) {
            this.app = MagicNetwork.d().getAppUID();
        }
        return this;
    }

    public SocialAPI$GetFacebookUsersRequest setAutoFollow(Boolean bool) {
        this.autoFollow = bool;
        return this;
    }
}
