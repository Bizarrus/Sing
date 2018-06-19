package com.smule.android.network.core;

import com.smule.android.logging.Analytics.AnalyticsType;

public enum MagicNetwork$LaunchModeType implements AnalyticsType {
    NO_FAST("no_fast"),
    FAST_NO_EXPIRED_SESSION("fast_no_expired_session"),
    FAST_EXPIRED_SESSION("fast_expired_session");
    
    private String f16462d;

    private MagicNetwork$LaunchModeType(String str) {
        this.f16462d = str;
    }

    public String mo6235a() {
        return this.f16462d;
    }
}
