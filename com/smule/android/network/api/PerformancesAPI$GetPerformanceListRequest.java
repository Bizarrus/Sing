package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class PerformancesAPI$GetPerformanceListRequest extends SnpRequest {
    public String app;
    public String arrKey;
    public String fillStatus = PerformancesAPI$FillStatus.FILLED.toString();
    public String hotType;
    public Integer limit = Integer.valueOf(25);
    public String mixApp;
    public Integer offset = Integer.valueOf(0);
    public String songUid;
    public String sort = PerformancesAPI$SortOrder.RECENT.toString();
    public Boolean video;

    public PerformancesAPI$GetPerformanceListRequest setSort(PerformancesAPI$SortOrder performancesAPI$SortOrder) {
        if (performancesAPI$SortOrder != null) {
            this.sort = performancesAPI$SortOrder.toString();
        }
        return this;
    }

    public PerformancesAPI$GetPerformanceListRequest setOffset(Integer num) {
        if (num != null) {
            this.offset = num;
        }
        return this;
    }

    public PerformancesAPI$GetPerformanceListRequest setLimit(Integer num) {
        if (num != null) {
            this.limit = num;
        }
        return this;
    }

    public PerformancesAPI$GetPerformanceListRequest setFillStatus(PerformancesAPI$FillStatus performancesAPI$FillStatus) {
        if (performancesAPI$FillStatus != null) {
            this.fillStatus = performancesAPI$FillStatus.toString();
        }
        return this;
    }

    public PerformancesAPI$GetPerformanceListRequest setHotType(PerformancesAPI$HotType performancesAPI$HotType) {
        if (performancesAPI$HotType != null) {
            this.hotType = performancesAPI$HotType.toString();
        }
        return this;
    }

    public PerformancesAPI$GetPerformanceListRequest setSongUid(String str) {
        this.songUid = str;
        return this;
    }

    public PerformancesAPI$GetPerformanceListRequest setArrKey(String str) {
        this.arrKey = str;
        return this;
    }

    public PerformancesAPI$GetPerformanceListRequest setApp(String str) {
        this.app = str;
        return this;
    }

    public PerformancesAPI$GetPerformanceListRequest setMixApp(String str) {
        this.mixApp = str;
        return this;
    }

    public PerformancesAPI$GetPerformanceListRequest setVideoOnly(Boolean bool) {
        if (bool != null) {
            this.video = bool;
        }
        return this;
    }
}
