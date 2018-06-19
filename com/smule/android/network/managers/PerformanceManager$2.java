package com.smule.android.network.managers;

import com.smule.android.network.api.PerformancesAPI$FillStatus;
import com.smule.android.network.api.PerformancesAPI$HotType;
import com.smule.android.network.api.PerformancesAPI$SortOrder;
import com.smule.android.network.core.CoreUtil;

class PerformanceManager$2 implements Runnable {
    final /* synthetic */ PerformanceManager$PerformancesResponseCallback f16788a;
    final /* synthetic */ PerformancesAPI$SortOrder f16789b;
    final /* synthetic */ Integer f16790c;
    final /* synthetic */ Integer f16791d;
    final /* synthetic */ PerformancesAPI$FillStatus f16792e;
    final /* synthetic */ PerformancesAPI$HotType f16793f;
    final /* synthetic */ String f16794g;
    final /* synthetic */ String f16795h;
    final /* synthetic */ String f16796i;
    final /* synthetic */ boolean f16797j;
    final /* synthetic */ boolean f16798k;
    final /* synthetic */ PerformanceManager f16799l;

    PerformanceManager$2(PerformanceManager performanceManager, PerformanceManager$PerformancesResponseCallback performanceManager$PerformancesResponseCallback, PerformancesAPI$SortOrder performancesAPI$SortOrder, Integer num, Integer num2, PerformancesAPI$FillStatus performancesAPI$FillStatus, PerformancesAPI$HotType performancesAPI$HotType, String str, String str2, String str3, boolean z, boolean z2) {
        this.f16799l = performanceManager;
        this.f16788a = performanceManager$PerformancesResponseCallback;
        this.f16789b = performancesAPI$SortOrder;
        this.f16790c = num;
        this.f16791d = num2;
        this.f16792e = performancesAPI$FillStatus;
        this.f16793f = performancesAPI$HotType;
        this.f16794g = str;
        this.f16795h = str2;
        this.f16796i = str3;
        this.f16797j = z;
        this.f16798k = z2;
    }

    public void run() {
        CoreUtil.m18079a(this.f16788a, this.f16799l.a(this.f16789b, this.f16790c, this.f16791d, this.f16792e, this.f16793f, this.f16794g, this.f16795h, this.f16796i, this.f16797j, this.f16798k));
    }
}
