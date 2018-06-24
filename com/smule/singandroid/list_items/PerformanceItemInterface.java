/*
 * Decompiled with CFR 0_123.
 */
package com.smule.singandroid.list_items;

import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.PerformanceV2;
import com.smule.singandroid.list_items.MediaPlayingViewInterface;

public interface PerformanceItemInterface {
    public void setListener(PerformanceItemListener var1);

    public void setPerformance(PerformanceV2 var1);

    public static interface PerformanceItemListener {
        public void a(MediaPlayingViewInterface var1, AccountIcon var2);

        public void a(MediaPlayingViewInterface var1, PerformanceV2 var2);

        public void b(MediaPlayingViewInterface var1, PerformanceV2 var2);
    }

}

