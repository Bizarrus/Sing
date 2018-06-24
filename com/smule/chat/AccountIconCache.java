/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Handler
 *  android.os.Looper
 */
package com.smule.chat;

import android.os.Handler;
import android.os.Looper;
import com.smule.android.logging.Log;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.utils.SimpleLRUCache;
import com.smule.chat.CollectionBatcher;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AccountIconCache {
    private static final AccountIconCache a = new AccountIconCache();
    private static final String b = AccountIconCache.class.getName();
    private final CollectionBatcher<Long, UserManager.AccountIconsResponse> c;
    private final SimpleLRUCache<Long, AccountIcon> d;
    private final Handler e;
    private int f;
    private int g;

    AccountIconCache() {
        this.c = new CollectionBatcher<Long, UserManager.AccountIconsResponse>(25, -101){

            UserManager.AccountIconsResponse a(Set<Long> set) {
                return AccountIconCache.this.c(set);
            }

            @Override
            /* synthetic */ Object b(Set set) {
                return this.a(set);
            }
        };
        this.d = new SimpleLRUCache(2500);
        this.e = new Handler(Looper.getMainLooper());
    }

    public static AccountIconCache a() {
        return a;
    }

    private UserManager.AccountIconsResponse c(Collection<Long> collection) {
        ++this.g;
        this.f += collection.size();
        Log.d(b, "fetching " + collection.size() + " total=" + this.f + " reqs=" + this.g);
        return com.smule.android.network.managers.UserManager.a().a(collection);
    }

    public void a(long l, UserManager accountIconsResponseCallback) {
        this.a(Collections.singleton(l), accountIconsResponseCallback);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a(AccountIcon accountIcon) {
        SimpleLRUCache<Long, AccountIcon> simpleLRUCache = this.d;
        synchronized (simpleLRUCache) {
            this.d.put(accountIcon.accountId, accountIcon);
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a(Collection<AccountIcon> object) {
        SimpleLRUCache<Long, AccountIcon> simpleLRUCache = this.d;
        synchronized (simpleLRUCache) {
            object = object.iterator();
            while (object.hasNext()) {
                AccountIcon accountIcon = (AccountIcon)object.next();
                this.d.put(accountIcon.accountId, accountIcon);
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public void a(Collection<Long> object, final UserManager accountIconsResponseCallback) {
        final ArrayList<AccountIcon> arrayList = new ArrayList<AccountIcon>();
        ArrayList<Long> arrayList2 = new ArrayList<Long>();
        SimpleLRUCache<Long, AccountIcon> simpleLRUCache = this.d;
        // MONITORENTER : simpleLRUCache
        object = object.iterator();
        while (object.hasNext()) {
            Long l = (Long)object.next();
            AccountIcon accountIcon = this.d.get(l);
            if (accountIcon != null) {
                arrayList.add(accountIcon);
                continue;
            }
            arrayList2.add(l);
        }
        // MONITOREXIT : simpleLRUCache
        if (arrayList2.isEmpty()) {
            object = UserManager.AccountIconsResponse.a(arrayList);
            this.e.post(new Runnable((UserManager.AccountIconsResponse)object){
                final /* synthetic */ UserManager.AccountIconsResponse b;
                {
                    this.b = accountIconsResponse;
                }

                @Override
                public void run() {
                    accountIconsResponseCallback.handleResponse(this.b);
                }
            });
            return;
        }
        this.c.a(new AccountIconsAggregator(arrayList2, new UserManager(){

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            @Override
            public void handleResponse(UserManager.AccountIconsResponse accountIconsResponse) {
                if (accountIconsResponse.a()) {
                    SimpleLRUCache simpleLRUCache = AccountIconCache.this.d;
                    synchronized (simpleLRUCache) {
                        for (AccountIcon accountIcon : accountIconsResponse.accountIcons) {
                            AccountIconCache.this.d.put(accountIcon.accountId, accountIcon);
                        }
                    }
                    accountIconsResponse.accountIcons.addAll(arrayList);
                }
                accountIconsResponseCallback.handleResponse(accountIconsResponse);
            }
        }));
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a(Map<Long, AccountIcon> map) {
        SimpleLRUCache<Long, AccountIcon> simpleLRUCache = this.d;
        synchronized (simpleLRUCache) {
            Iterator<Map.Entry<Long, AccountIcon>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Long, AccountIcon> entry = iterator.next();
                Long l = entry.getKey();
                AccountIcon accountIcon = this.d.get(l);
                if (accountIcon != null) {
                    map.put(l, accountIcon);
                    continue;
                }
                this.d.put(l, entry.getValue());
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void b(Collection<Long> object) {
        SimpleLRUCache<Long, AccountIcon> simpleLRUCache = this.d;
        synchronized (simpleLRUCache) {
            object = object.iterator();
            while (object.hasNext()) {
                Long l = (Long)object.next();
                this.d.remove(l);
            }
            return;
        }
    }

    private static class AccountIconsAggregator
    extends CollectionBatcher.CallbackAggregator<Long, UserManager.AccountIconsResponse> {
        private UserManager b;
        private UserManager.AccountIconsResponse c;

        public AccountIconsAggregator(Collection<Long> collection, UserManager accountIconsResponseCallback) {
            super(new HashSet<Long>(collection));
            this.b = accountIconsResponseCallback;
        }

        @Override
        public void a() {
            this.b.handleResponse(this.c);
        }

        @Override
        public void a(UserManager.AccountIconsResponse object) {
            if (this.c == null) {
                this.c = UserManager.AccountIconsResponse.a(new ArrayList<AccountIcon>());
            }
            if (object != null) {
                if (object.a()) {
                    for (AccountIcon accountIcon : object.accountIcons) {
                        if (!this.a.contains(accountIcon.accountId)) continue;
                        this.c.accountIcons.add(accountIcon);
                    }
                } else {
                    this.c.a.a = object.a.a;
                    this.c.a.b = object.a.b;
                }
            }
        }
    }

}

