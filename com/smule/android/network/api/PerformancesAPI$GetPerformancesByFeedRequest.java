package com.smule.android.network.api;

import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.SnpRequest;
import java.util.Arrays;
import java.util.List;

public class PerformancesAPI$GetPerformancesByFeedRequest extends SnpRequest {
    public List<String> forApps = Arrays.asList(new String[]{MagicNetwork.d().getAppUID()});
}
