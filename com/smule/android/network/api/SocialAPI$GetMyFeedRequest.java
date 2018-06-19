package com.smule.android.network.api;

import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.SnpRequest;
import java.util.Arrays;
import java.util.List;

public class SocialAPI$GetMyFeedRequest extends SnpRequest {
    public List<String> apps;
    public boolean useIcons = true;

    public SocialAPI$GetMyFeedRequest setApps(List<String> list) {
        this.apps = list;
        return this;
    }

    public SocialAPI$GetMyFeedRequest setApp(String str) {
        if (str == null) {
            str = MagicNetwork.d().getAppUID();
        }
        this.apps = Arrays.asList(new String[]{str});
        return this;
    }
}
