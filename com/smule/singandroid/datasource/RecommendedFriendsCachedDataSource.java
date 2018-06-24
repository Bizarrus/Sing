/*
 * Decompiled with CFR 0_123.
 */
package com.smule.singandroid.datasource;

import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.singandroid.datasource.RecommendedFriendsDataSource;
import java.util.concurrent.TimeUnit;

public class RecommendedFriendsCachedDataSource
extends RecommendedFriendsDataSource {
    public static final String a = RecommendedFriendsCachedDataSource.class.getName();
    private static final long m = TimeUnit.MINUTES.toSeconds(10);

    public RecommendedFriendsCachedDataSource(int n) {
        super(RecommendedFriendsCachedDataSource.class.getSimpleName(), new MagicDataSource.CursorPaginationTracker());
        this.l = n;
    }

    @Override
    public void b(String string2) {
        this.f = RecommendedFriendsCachedDataSource.class.getSimpleName();
    }

    @Override
    protected long d() {
        return m;
    }
}

