package com.smule.android.network.api;

import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.SnpRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SocialAPI$GetActivityFeedRequest extends SnpRequest {
    public List<String> apps = Collections.singletonList(MagicNetwork.d().getAppUID());
    public boolean useIcons = true;

    public SocialAPI$GetActivityFeedRequest setApps(List<String> list) {
        this.apps = list;
        return this;
    }

    public SocialAPI$GetActivityFeedRequest setApp(String str) {
        if (str != null) {
            this.apps = Arrays.asList(new String[]{str});
        }
        return this;
    }
}
