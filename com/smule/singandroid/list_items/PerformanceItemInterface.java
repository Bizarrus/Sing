package com.smule.singandroid.list_items;

import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.PerformanceV2;

public interface PerformanceItemInterface {

    public interface PerformanceItemListener {
        void mo6471a(MediaPlayingViewInterface mediaPlayingViewInterface, AccountIcon accountIcon);

        void mo6472a(MediaPlayingViewInterface mediaPlayingViewInterface, PerformanceV2 performanceV2);

        void mo6473b(MediaPlayingViewInterface mediaPlayingViewInterface, PerformanceV2 performanceV2);
    }

    void setListener(PerformanceItemListener performanceItemListener);

    void setPerformance(PerformanceV2 performanceV2);
}
