package com.smule.singandroid.upsell;

import com.smule.android.network.models.PerformanceV2;
import com.smule.android.songbook.SongbookEntry;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.SingServerValues;

public class UpsellManager {
    public static final String f24630a = UpsellManager.class.getName();

    public static BaseFragment m25791a(boolean z, SongbookEntry songbookEntry, String str, String str2, UpsellType upsellType) {
        if (SingServerValues.m21676N()) {
            return UpsellFragment.m25776a(z, songbookEntry, str, str2, upsellType);
        }
        return SubscriptionPurchaseFragment.m25758a(z, songbookEntry, str, str2, upsellType);
    }

    public static BaseFragment m25790a(boolean z, SongbookEntry songbookEntry, PerformanceV2 performanceV2, Long l, UpsellType upsellType) {
        if (SingServerValues.m21676N()) {
            return UpsellFragment.m25775a(z, songbookEntry, performanceV2, l, upsellType);
        }
        return SubscriptionPurchaseFragment.m25757a(z, songbookEntry, performanceV2, l, upsellType);
    }
}
