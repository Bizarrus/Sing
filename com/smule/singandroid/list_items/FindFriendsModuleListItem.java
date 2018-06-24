/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  org.androidannotations.annotations.EViewGroup
 */
package com.smule.singandroid.list_items;

import android.content.Context;
import com.smule.android.logging.Analytics;
import com.smule.singandroid.list_items.FindFriendsModuleListItem_;
import com.smule.singandroid.list_items.RecUserFollowListItem;
import org.androidannotations.annotations.EViewGroup;

@EViewGroup
public class FindFriendsModuleListItem
extends RecUserFollowListItem {
    private static final String t = FindFriendsModuleListItem.class.getName();

    public FindFriendsModuleListItem(Context context) {
        super(context);
    }

    public static FindFriendsModuleListItem a(Context context) {
        return FindFriendsModuleListItem_.b(context);
    }

    @Override
    protected String getFollowContext() {
        return "FFMD";
    }

    @Override
    protected Analytics getRecSysFollowContext() {
        return Analytics.l;
    }
}

