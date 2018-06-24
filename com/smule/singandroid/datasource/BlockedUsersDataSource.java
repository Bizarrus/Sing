/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 */
package com.smule.singandroid.datasource;

import android.content.Context;
import com.smule.android.logging.Log;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.chat.AccountIconCache;
import com.smule.singandroid.BlockedUserListItemContainer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Future;

public class BlockedUsersDataSource
extends MagicDataSource<BlockedUserListItemContainer, MagicDataSource.OffsetPaginationTracker> {
    public static final String a = BlockedUsersDataSource.class.getName();
    private List<Long> b;

    public BlockedUsersDataSource(Context context, List<Long> list) {
        super(new MagicDataSource.OffsetPaginationTracker());
        this.b = list;
    }

    @Override
    public int a() {
        return 25;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public Future<?> a(MagicDataSource.OffsetPaginationTracker list, final int n, final MagicDataSource.FetchDataCallback<BlockedUserListItemContainer, MagicDataSource.OffsetPaginationTracker> fetchDataCallback) {
        n = list.a() + 25 < this.b.size() ? list.a() + 25 : this.b.size();
        if (list.a() >= 0 && n > list.a() && n <= this.b.size()) {
            list = this.b.subList(list.a(), n);
            Log.a(a, "Fetching " + list.size() + " accounts");
            AccountIconCache.a().a(list, new UserManager(){

                @Override
                public void handleResponse(UserManager.AccountIconsResponse object) {
                    if (object.a()) {
                        ArrayList<BlockedUserListItemContainer> arrayList = new ArrayList<BlockedUserListItemContainer>();
                        object = object.accountIcons.iterator();
                        while (object.hasNext()) {
                            arrayList.add(new BlockedUserListItemContainer((AccountIcon)object.next(), true));
                        }
                        fetchDataCallback.a(arrayList, new MagicDataSource.OffsetPaginationTracker(n + 1));
                        return;
                    }
                    fetchDataCallback.a();
                }
            });
            return null;
        }
        fetchDataCallback.a(new ArrayList(), new MagicDataSource.OffsetPaginationTracker(-1));
        return null;
    }

}

