package com.smule.android.network.api;

import android.text.TextUtils;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.SnpRequest;

public class PerformancesAPI$GetPerformancesByPerformerRequest extends SnpRequest {
    public Long accountId;
    public String app = MagicNetwork.b();
    public Boolean collapsed = Boolean.valueOf(false);
    public String fillStatus;
    public Integer limit;
    public Integer offset;

    public PerformancesAPI$GetPerformancesByPerformerRequest setAccountId(Long l) {
        if (!(l == null || l.longValue() == 0)) {
            this.accountId = l;
        }
        return this;
    }

    public PerformancesAPI$GetPerformancesByPerformerRequest setApp(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.app = str;
        }
        return this;
    }

    public PerformancesAPI$GetPerformancesByPerformerRequest setFillStatus(PerformancesAPI$FillStatus performancesAPI$FillStatus, Boolean bool) {
        if (performancesAPI$FillStatus != null) {
            this.fillStatus = performancesAPI$FillStatus.toString();
            if (performancesAPI$FillStatus != PerformancesAPI$FillStatus.FILLED || bool == null) {
                this.collapsed = Boolean.valueOf(false);
            } else {
                this.collapsed = bool;
            }
        }
        return this;
    }

    public PerformancesAPI$GetPerformancesByPerformerRequest setOffset(Integer num) {
        this.offset = num;
        return this;
    }

    public PerformancesAPI$GetPerformancesByPerformerRequest setLimit(Integer num) {
        this.limit = num;
        return this;
    }
}
