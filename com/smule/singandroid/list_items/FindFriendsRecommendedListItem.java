/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  org.androidannotations.annotations.EViewGroup
 */
package com.smule.singandroid.list_items;

import android.content.Context;
import com.smule.singandroid.list_items.FindFriendsRecommendedListItem_;
import com.smule.singandroid.list_items.RecUserFollowListItem;
import org.androidannotations.annotations.EViewGroup;

@EViewGroup
public class FindFriendsRecommendedListItem
extends RecUserFollowListItem {
    private static final String t = FindFriendsRecommendedListItem.class.getName();

    public FindFriendsRecommendedListItem(Context context) {
        super(context);
    }

    public static FindFriendsRecommendedListItem a(Context context) {
        return FindFriendsRecommendedListItem_.b(context);
    }
}

