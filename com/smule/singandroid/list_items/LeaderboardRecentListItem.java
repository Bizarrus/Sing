/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  org.androidannotations.annotations.EViewGroup
 */
package com.smule.singandroid.list_items;

import android.content.Context;
import com.smule.singandroid.list_items.LeaderboardRecentListItem_;
import com.smule.singandroid.profile.PerformanceListItem;
import org.androidannotations.annotations.EViewGroup;

@EViewGroup
public class LeaderboardRecentListItem
extends PerformanceListItem {
    private static final String a = LeaderboardRecentListItem.class.getName();

    public LeaderboardRecentListItem(Context context) {
        super(context);
    }

    public static LeaderboardRecentListItem a(Context context) {
        return LeaderboardRecentListItem_.b(context);
    }
}

