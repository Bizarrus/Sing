package com.smule.android.datasources;

import android.support.v4.util.LongSparseArray;
import com.smule.android.logging.Log;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.magicui.lists.adapters.MagicDataSource.FetchDataCallback;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.managers.SearchManager;
import com.smule.android.network.managers.SearchManager.SASearchResponse;
import com.smule.android.network.managers.SearchManager.SearchResultType;
import com.smule.android.network.managers.SearchManager.SearchSortOption;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.AccountIcon$AccountIconComparatorByHandle;
import com.smule.android.utils.ListUtils;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Future;

public class UserNameSearchDataSource extends MagicDataSource<AccountIcon> {
    private static final String f15893a = UserNameSearchDataSource.class.getName();
    private String f15894b;

    protected long mo6245c() {
        return 30;
    }

    public int mo6242a() {
        return 25;
    }

    public Future<?> mo6243a(final int i, final int i2, final FetchDataCallback<AccountIcon> fetchDataCallback) {
        Log.b(f15893a, "Time to do a deeper search: " + this.f15894b);
        return MagicNetwork.a(new Runnable(this) {
            final /* synthetic */ UserNameSearchDataSource f15892d;

            public void run() {
                Log.b(UserNameSearchDataSource.f15893a, "Running deep search with term: " + this.f15892d.f15894b);
                SASearchResponse a = SearchManager.m18331a().m18334a(this.f15892d.f15894b, SearchResultType.ACCOUNT, i, i2, SearchSortOption.POPULAR);
                LongSparseArray longSparseArray = new LongSparseArray();
                if (a == null || !a.a()) {
                    fetchDataCallback.mo6256a();
                    return;
                }
                Iterator it = a.mAccts.iterator();
                while (it.hasNext()) {
                    AccountIcon accountIcon = (AccountIcon) it.next();
                    if (AccountIcon.a(accountIcon)) {
                        longSparseArray.put(accountIcon.accountId, accountIcon);
                    } else {
                        Log.d(UserNameSearchDataSource.f15893a, "Invalid account icon parsed in doDeepSearch");
                    }
                }
                List a2 = ListUtils.m18997a(longSparseArray);
                Collections.sort(a2, new AccountIcon$AccountIconComparatorByHandle());
                fetchDataCallback.mo6257a(a2, a.mNext.intValue());
            }
        });
    }
}
