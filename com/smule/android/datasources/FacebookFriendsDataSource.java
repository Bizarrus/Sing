/*
 * Decompiled with CFR 0_123.
 */
package com.smule.android.datasources;

import com.smule.android.facebook.FacebookFriend;
import com.smule.android.facebook.FriendFinder;
import com.smule.android.facebook.MagicFacebook;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Future;

public class FacebookFriendsDataSource
extends MagicDataSource<FacebookFriend, MagicDataSource.OffsetPaginationTracker> {
    private static final String b = FacebookFriendsDataSource.class.getName();
    FriendFinder a = new FriendFinder(false, 500);

    public FacebookFriendsDataSource() {
        super(FacebookFriendsDataSource.class.getSimpleName(), new MagicDataSource.OffsetPaginationTracker());
    }

    @Override
    public int a() {
        return 0;
    }

    @Override
    public Future<?> a(MagicDataSource.OffsetPaginationTracker offsetPaginationTracker, int n, final MagicDataSource.FetchDataCallback<FacebookFriend, MagicDataSource.OffsetPaginationTracker> fetchDataCallback) {
        if (!com.smule.android.facebook.MagicFacebook.a().c()) {
            fetchDataCallback.a();
            return null;
        }
        this.a.a(new MagicFacebook(){

            @Override
            public void a() {
                fetchDataCallback.a();
            }

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void a(List<FacebookFriend> iterator, List<FacebookFriend> list, boolean bl) {
                iterator.addAll(list);
                list = new ArrayList<FacebookFriend>();
                iterator = iterator.iterator();
                while (iterator.hasNext()) {
                    FacebookFriend facebookFriend = iterator.next();
                    if (facebookFriend.e == 0 || facebookFriend.d) continue;
                    list.add(facebookFriend);
                }
                Collections.sort(list, new FacebookFriend.ComparatorByName());
                int n = bl ? 1 : -1;
                fetchDataCallback.a(list, new MagicDataSource.OffsetPaginationTracker(n));
            }
        });
        return null;
    }

}

