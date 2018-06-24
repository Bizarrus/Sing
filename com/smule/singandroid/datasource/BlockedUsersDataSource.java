package com.smule.singandroid.datasource;

import android.content.Context;
import com.smule.android.logging.Log;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.magicui.lists.adapters.MagicDataSource.FetchDataCallback;
import com.smule.android.network.managers.UserManager$AccountIconsResponseCallback;
import com.smule.android.network.managers.UserManager.AccountIconsResponse;
import com.smule.android.network.models.AccountIcon;
import com.smule.chat.AccountIconCache;
import com.smule.singandroid.BlockedUserListItemContainer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Future;

public class BlockedUsersDataSource extends MagicDataSource<BlockedUserListItemContainer> {
    public static final String f22102a = BlockedUsersDataSource.class.getName();
    private List<Long> f22103b;

    public BlockedUsersDataSource(Context context, List<Long> list) {
        this.f22103b = list;
    }

    public int mo6242a() {
        return 25;
    }

    public Future<?> mo6243a(int i, int i2, final FetchDataCallback<BlockedUserListItemContainer> fetchDataCallback) {
        final int size = i + 25 < this.f22103b.size() ? i + 25 : this.f22103b.size();
        if (i < 0 || size <= i || size > this.f22103b.size()) {
            fetchDataCallback.mo6257a(new ArrayList(), -1);
        } else {
            Collection subList = this.f22103b.subList(i, size);
            Log.a(f22102a, "Fetching " + subList.size() + " accounts");
            AccountIconCache.m19106a().m19111a(subList, new UserManager$AccountIconsResponseCallback(this) {
                final /* synthetic */ BlockedUsersDataSource f22101c;

                public void handleResponse(AccountIconsResponse accountIconsResponse) {
                    if (accountIconsResponse.a()) {
                        List arrayList = new ArrayList();
                        for (AccountIcon blockedUserListItemContainer : accountIconsResponse.accountIcons) {
                            arrayList.add(new BlockedUserListItemContainer(blockedUserListItemContainer, true));
                        }
                        fetchDataCallback.mo6257a(arrayList, size + 1);
                        return;
                    }
                    fetchDataCallback.mo6256a();
                }
            });
        }
        return null;
    }
}
