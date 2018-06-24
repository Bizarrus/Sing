/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.support.v4.util.LongSparseArray
 */
package com.smule.android.datasources;

import android.support.v4.util.LongSparseArray;
import com.smule.android.logging.Log;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.managers.SearchManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.utils.ListUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Future;

public class UserNameSearchDataSource
extends MagicDataSource<com.smule.android.network.models.AccountIcon, MagicDataSource.OffsetPaginationTracker> {
    private static final String a = UserNameSearchDataSource.class.getName();
    private final String b;

    @Override
    public int a() {
        return 25;
    }

    @Override
    public Future<?> a(final MagicDataSource.OffsetPaginationTracker offsetPaginationTracker, final int n, final MagicDataSource.FetchDataCallback<com.smule.android.network.models.AccountIcon, MagicDataSource.OffsetPaginationTracker> fetchDataCallback) {
        Log.b(a, "Time to do a deeper search: " + this.b);
        return MagicNetwork.a(new Runnable(){

            @Override
            public void run() {
                Log.b(a, "Running deep search with term: " + UserNameSearchDataSource.this.b);
                SearchManager sASearchResponse = com.smule.android.network.managers.SearchManager.a().a(UserNameSearchDataSource.this.b, SearchManager.SearchResultType.b, offsetPaginationTracker.a(), n, SearchManager.SearchSortOption.a);
                Object object = new LongSparseArray();
                if (sASearchResponse != null && sASearchResponse.a()) {
                    for (com.smule.android.network.models.AccountIcon accountIcon : sASearchResponse.mAccts) {
                        if (!com.smule.android.network.models.AccountIcon.a(accountIcon)) {
                            Log.d(a, "Invalid account icon parsed in doDeepSearch");
                            continue;
                        }
                        object.put(accountIcon.accountId, (Object)accountIcon);
                    }
                    object = ListUtils.a(object);
                    Collections.sort(object, new Comparator<com.smule.android.network.models.AccountIcon>(){

                        public int a(com.smule.android.network.models.AccountIcon accountIcon, com.smule.android.network.models.AccountIcon accountIcon2) {
                            if (accountIcon.handle == null && accountIcon2.handle == null) {
                                return 0;
                            }
                            if (accountIcon.handle == null || accountIcon2.handle == null) {
                                if (accountIcon.handle == null) {
                                    return -1;
                                }
                                return 1;
                            }
                            return accountIcon.handle.toLowerCase().compareTo(accountIcon2.handle.toLowerCase());
                        }

                        public /* synthetic */ int compare(Object object, Object object2) {
                            return this.a((com.smule.android.network.models.AccountIcon)object, (com.smule.android.network.models.AccountIcon)object2);
                        }
                    });
                    fetchDataCallback.a(object, new MagicDataSource.OffsetPaginationTracker(sASearchResponse.mNext));
                    return;
                }
                fetchDataCallback.a();
            }
        });
    }

    @Override
    protected long d() {
        return 30;
    }

}

