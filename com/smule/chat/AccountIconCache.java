package com.smule.chat;

import android.os.Handler;
import android.os.Looper;
import com.mopub.volley.DefaultRetryPolicy;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.managers.UserManager$AccountIconsResponseCallback;
import com.smule.android.network.managers.UserManager.AccountIconsResponse;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.utils.SimpleLRUCache;
import com.smule.chat.CollectionBatcher.CallbackAggregator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class AccountIconCache {
    private static final AccountIconCache f17901a = new AccountIconCache();
    private static final String f17902b = AccountIconCache.class.getName();
    private final CollectionBatcher<Long, AccountIconsResponse> f17903c = new CollectionBatcher<Long, AccountIconsResponse>(this, 25, -101) {
        final /* synthetic */ AccountIconCache f17891a;

        /* synthetic */ Object mo6306b(Set set) {
            return m19097a(set);
        }

        AccountIconsResponse m19097a(Set<Long> set) {
            return this.f17891a.m19107c(set);
        }
    };
    private final SimpleLRUCache<Long, AccountIcon> f17904d = new SimpleLRUCache(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS);
    private final Handler f17905e = new Handler(Looper.getMainLooper());
    private int f17906f;
    private int f17907g;

    private static class AccountIconsAggregator extends CallbackAggregator<Long, AccountIconsResponse> {
        private UserManager$AccountIconsResponseCallback f17899b;
        private AccountIconsResponse f17900c;

        public AccountIconsAggregator(Collection<Long> collection, UserManager$AccountIconsResponseCallback userManager$AccountIconsResponseCallback) {
            super(new HashSet(collection));
            this.f17899b = userManager$AccountIconsResponseCallback;
        }

        public void m19102a(AccountIconsResponse accountIconsResponse) {
            if (this.f17900c == null) {
                this.f17900c = AccountIconsResponse.a(new ArrayList());
            }
            if (accountIconsResponse == null) {
                return;
            }
            if (accountIconsResponse.a()) {
                for (AccountIcon accountIcon : accountIconsResponse.accountIcons) {
                    if (this.a.contains(Long.valueOf(accountIcon.accountId))) {
                        this.f17900c.accountIcons.add(accountIcon);
                    }
                }
                return;
            }
            this.f17900c.a.a = accountIconsResponse.a.a;
            this.f17900c.a.b = accountIconsResponse.a.b;
        }

        public void mo6308a() {
            this.f17899b.handleResponse(this.f17900c);
        }
    }

    public static AccountIconCache m19106a() {
        return f17901a;
    }

    AccountIconCache() {
    }

    public void m19108a(long j, UserManager$AccountIconsResponseCallback userManager$AccountIconsResponseCallback) {
        m19111a(Collections.singleton(Long.valueOf(j)), userManager$AccountIconsResponseCallback);
    }

    public void m19111a(Collection<Long> collection, final UserManager$AccountIconsResponseCallback userManager$AccountIconsResponseCallback) {
        final List arrayList = new ArrayList();
        Collection arrayList2 = new ArrayList();
        synchronized (this.f17904d) {
            for (Long l : collection) {
                AccountIcon accountIcon = (AccountIcon) this.f17904d.get(l);
                if (accountIcon != null) {
                    arrayList.add(accountIcon);
                } else {
                    arrayList2.add(l);
                }
            }
        }
        if (arrayList2.isEmpty()) {
            final AccountIconsResponse a = AccountIconsResponse.a(arrayList);
            this.f17905e.post(new Runnable(this) {
                final /* synthetic */ AccountIconCache f17894c;

                public void run() {
                    userManager$AccountIconsResponseCallback.handleResponse(a);
                }
            });
            return;
        }
        this.f17903c.m19095a(new AccountIconsAggregator(arrayList2, new UserManager$AccountIconsResponseCallback(this) {
            final /* synthetic */ AccountIconCache f17897c;

            public void handleResponse(AccountIconsResponse accountIconsResponse) {
                if (accountIconsResponse.a()) {
                    synchronized (this.f17897c.f17904d) {
                        for (AccountIcon accountIcon : accountIconsResponse.accountIcons) {
                            this.f17897c.f17904d.put(Long.valueOf(accountIcon.accountId), accountIcon);
                        }
                    }
                    accountIconsResponse.accountIcons.addAll(arrayList);
                }
                userManager$AccountIconsResponseCallback.handleResponse(accountIconsResponse);
            }
        }));
    }

    public void m19109a(AccountIcon accountIcon) {
        synchronized (this.f17904d) {
            this.f17904d.put(Long.valueOf(accountIcon.accountId), accountIcon);
        }
    }

    public void m19110a(Collection<AccountIcon> collection) {
        synchronized (this.f17904d) {
            for (AccountIcon accountIcon : collection) {
                this.f17904d.put(Long.valueOf(accountIcon.accountId), accountIcon);
            }
        }
    }

    public void m19113b(Collection<Long> collection) {
        synchronized (this.f17904d) {
            for (Long remove : collection) {
                this.f17904d.remove(remove);
            }
        }
    }

    public void m19112a(Map<Long, AccountIcon> map) {
        synchronized (this.f17904d) {
            for (Entry entry : map.entrySet()) {
                Long l = (Long) entry.getKey();
                AccountIcon accountIcon = (AccountIcon) this.f17904d.get(l);
                if (accountIcon != null) {
                    map.put(l, accountIcon);
                } else {
                    this.f17904d.put(l, entry.getValue());
                }
            }
        }
    }

    private AccountIconsResponse m19107c(Collection<Long> collection) {
        this.f17907g++;
        this.f17906f += collection.size();
        Log.d(f17902b, "fetching " + collection.size() + " total=" + this.f17906f + " reqs=" + this.f17907g);
        return UserManager.a().a(collection);
    }
}
