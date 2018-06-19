package com.smule.android.network.managers;

import com.fasterxml.jackson.databind.JsonNode;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.magicui.lists.adapters.MagicDataSource.DataState;
import com.smule.android.magicui.lists.adapters.MagicDataSource.FetchDataCallback;
import com.smule.android.magicui.lists.adapters.MagicDataSource.FetchDataCallbackObject;
import com.smule.android.network.api.SocialAPI;
import com.smule.android.network.api.SocialAPI$GetFolloweesRequest;
import com.smule.android.network.api.SocialAPI$GetFollowersRequest;
import com.smule.android.network.api.SocialAPI$IsFollowingAccountIdsRequest;
import com.smule.android.network.api.SocialAPI$UpdateFolloweesRequest;
import com.smule.android.network.core.CoreUtil;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.models.AccountApps;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.AccountIcon$AccountIconComparatorByHandle;
import com.smule.android.utils.JsonUtils;
import com.smule.android.utils.ListSetConverter;
import com.smule.android.utils.NotificationCenter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Future;

public class FollowManager {
    private static final String f16653a = FollowManager.class.getName();
    private static FollowManager f16654b = null;
    private SocialAPI f16655c = ((SocialAPI) MagicNetwork.a().a(SocialAPI.class));
    private Set<Long> f16656d = new TreeSet();
    private Set<Long> f16657e = new TreeSet();
    private boolean f16658f;

    class C35581 implements Runnable {
        final /* synthetic */ List f16614a;
        final /* synthetic */ FollowManager f16615b;

        public void run() {
            NetworkUtils.m18104a(this.f16615b.f16655c.updateFollowees(new SocialAPI$UpdateFolloweesRequest().setAdd(this.f16614a).setRemove(new ArrayList()).setContextAndName(SocialAPI.FOLLOWEES_UPDATE_CONTEXT_APP, null)));
        }
    }

    public interface FollowersResponseCallback extends ResponseInterface<FollowersResponse> {
        void handleResponse(FollowersResponse followersResponse);
    }

    public interface FolloweesResponseCallback extends ResponseInterface<FolloweesResponse> {
        void handleResponse(FolloweesResponse followeesResponse);
    }

    public static class FollowListDataSource extends MagicDataSource<AccountIcon> {
        private int f16647a;
        private long f16648b;
        private int f16649c;
        private int f16650k;
        private FollowersResponseCallback f16651l;
        private FolloweesResponseCallback f16652m;

        protected static class FollowListCacheItem extends CacheItem {
            protected int f16645e;
            protected int f16646f;

            protected FollowListCacheItem(String str, long j, long j2, int i, int i2) {
                super(str, j, j2);
                this.f16645e = i;
                this.f16646f = i2;
            }
        }

        public FollowListDataSource(int i, long j, FollowersResponseCallback followersResponseCallback, FolloweesResponseCallback followeesResponseCallback) {
            super(FollowManager.class.getName() + "**" + i + "**" + j);
            this.f16647a = i;
            this.f16648b = j;
            this.f16651l = followersResponseCallback;
            this.f16652m = followeesResponseCallback;
        }

        public int mo6264d() {
            return this.f16649c;
        }

        public int m18199v() {
            return this.f16650k;
        }

        private void m18191a(FollowersResponse followersResponse, FolloweesResponse followeesResponse, FetchDataCallback<AccountIcon> fetchDataCallback) {
            List list;
            if (this.f16647a == 0) {
                list = followersResponse.mFollowers;
            } else {
                list = followeesResponse.mFollowees;
            }
            Collection arrayList = new ArrayList();
            for (AccountIcon accountIcon : r2) {
                arrayList.add(Long.valueOf(accountIcon.accountId));
            }
            final FollowersResponse followersResponse2 = followersResponse;
            final FolloweesResponse followeesResponse2 = followeesResponse;
            final FetchDataCallback<AccountIcon> fetchDataCallback2 = fetchDataCallback;
            FollowManager.m18204a().m18219a(arrayList, new Runnable(this) {
                final /* synthetic */ FollowListDataSource f16640e;

                public void run() {
                    List arrayList = new ArrayList();
                    Collection arrayList2 = new ArrayList();
                    for (AccountIcon accountIcon : r2) {
                        if (accountIcon.b()) {
                            arrayList.add(accountIcon);
                        } else {
                            arrayList2.add(accountIcon);
                        }
                    }
                    Collections.sort(arrayList, new AccountIcon$AccountIconComparatorByHandle());
                    Collections.sort(arrayList2, new AccountIcon$AccountIconComparatorByHandle());
                    this.f16640e.f16649c = arrayList.size();
                    this.f16640e.f16650k = arrayList2.size();
                    arrayList.addAll(arrayList2);
                    if (this.f16640e.f16647a == 0) {
                        if (this.f16640e.f16651l != null) {
                            this.f16640e.f16651l.handleResponse(followersResponse2);
                        }
                    } else if (this.f16640e.f16652m != null) {
                        this.f16640e.f16652m.handleResponse(followeesResponse2);
                    }
                    fetchDataCallback2.mo6257a(arrayList, -1);
                }
            });
        }

        public Future<?> mo6243a(int i, int i2, final FetchDataCallback<AccountIcon> fetchDataCallback) {
            if (this.f16647a == 0) {
                FollowManager.m18204a().m18207a(this.f16648b, new FollowersResponseCallback(this) {
                    final /* synthetic */ FollowListDataSource f16642b;

                    public void handleResponse(FollowersResponse followersResponse) {
                        if (followersResponse.a()) {
                            this.f16642b.m18191a(followersResponse, null, fetchDataCallback);
                            return;
                        }
                        if (this.f16642b.f16651l != null) {
                            this.f16642b.f16651l.handleResponse(followersResponse);
                        }
                        fetchDataCallback.mo6256a();
                    }
                });
            } else {
                FollowManager.m18204a().m18206a(this.f16648b, new FolloweesResponseCallback(this) {
                    final /* synthetic */ FollowListDataSource f16644b;

                    public void handleResponse(FolloweesResponse followeesResponse) {
                        if (followeesResponse.a()) {
                            this.f16644b.m18191a(null, followeesResponse, fetchDataCallback);
                            return;
                        }
                        if (this.f16644b.f16652m != null) {
                            this.f16644b.f16652m.handleResponse(followeesResponse);
                        }
                        fetchDataCallback.mo6256a();
                    }
                });
            }
            return null;
        }

        protected synchronized void mo6266f() {
            if (!(this.e == null || mo6245c() == 0)) {
                CacheItem cacheItem = (CacheItem) d.get(this.e);
                if (cacheItem == null) {
                    cacheItem = new FollowListCacheItem(this.e, System.currentTimeMillis(), mo6245c() * 1000, this.f16649c, this.f16650k);
                    d.put(this.e, cacheItem);
                }
                cacheItem.f16429d.setDataPosition(0);
                mo6684a(cacheItem.f16429d);
                m17658h();
            }
        }

        protected boolean mo6265e() {
            if (this.e == null) {
                return false;
            }
            FollowListCacheItem followListCacheItem = (FollowListCacheItem) d.get(this.e);
            if (followListCacheItem == null) {
                return false;
            }
            boolean z;
            synchronized (this.e) {
                if (m17648a((CacheItem) followListCacheItem)) {
                    d.remove(this.e);
                    z = false;
                } else {
                    followListCacheItem.d.setDataPosition(0);
                    mo6685b(followListCacheItem.d);
                    this.f16649c = followListCacheItem.f16645e;
                    this.f16650k = followListCacheItem.f16646f;
                    this.g = mo6791a(this.f);
                    z = true;
                }
            }
            if (!z) {
                return z;
            }
            this.h = new FetchDataCallbackObject(this);
            this.h.m18067a(this.f, true);
            return z;
        }

        public List<AccountIcon> m18200w() {
            return Collections.unmodifiableList(this.f);
        }
    }

    public interface FollowListener {
        void mo6416a(NetworkResponse networkResponse);
    }

    public interface ToggleFollowStateListener {
        void mo6399a(boolean z, boolean z2, boolean z3);
    }

    private FollowManager() {
    }

    public static FollowManager m18204a() {
        if (f16654b == null) {
            f16654b = new FollowManager();
        }
        return f16654b;
    }

    public void m18220a(HashSet<Long> hashSet, String str, String str2, FollowListener followListener) {
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            long longValue = ((Long) it.next()).longValue();
            if (m18222a(longValue)) {
                it.remove();
            } else {
                m18227c(longValue);
            }
        }
        final List a = ListSetConverter.m18994a((Set) hashSet);
        final String str3 = str;
        final String str4 = str2;
        final FollowListener followListener2 = followListener;
        MagicNetwork.a(new Runnable(this) {
            final /* synthetic */ FollowManager f16620e;

            public void run() {
                NetworkResponse a = NetworkUtils.m18104a(this.f16620e.f16655c.updateFollowees(new SocialAPI$UpdateFolloweesRequest().setAdd(a).setRemove(new ArrayList()).setContextAndName(str3, str4)));
                if (followListener2 != null) {
                    followListener2.mo6416a(a);
                }
            }
        });
    }

    public void m18215a(Long l, ToggleFollowStateListener toggleFollowStateListener) {
        m18217a(l, SocialAPI.FOLLOWEES_UPDATE_CONTEXT_APP, null, toggleFollowStateListener);
    }

    public void m18217a(Long l, String str, String str2, ToggleFollowStateListener toggleFollowStateListener) {
        final boolean a = m18204a().m18222a(l.longValue());
        final Long l2 = l;
        final String str3 = str;
        final String str4 = str2;
        final ToggleFollowStateListener toggleFollowStateListener2 = toggleFollowStateListener;
        MagicNetwork.a(new Runnable(this) {
            final /* synthetic */ FollowManager f16626f;

            public void run() {
                NetworkResponse a;
                boolean z;
                boolean z2;
                if (a) {
                    a = NetworkUtils.m18104a(this.f16626f.f16655c.updateFollowees(new SocialAPI$UpdateFolloweesRequest().setRemove(Collections.singletonList(l2)).setAdd(new ArrayList())));
                } else {
                    a = NetworkUtils.m18104a(this.f16626f.f16655c.updateFollowees(new SocialAPI$UpdateFolloweesRequest().setAdd(Collections.singletonList(l2)).setRemove(new ArrayList()).setContextAndName(str3, str4)));
                }
                if (a == null || a.b != 0) {
                    z = false;
                } else {
                    z = true;
                }
                if (a == null || a.b != 2004) {
                    z2 = false;
                } else {
                    z2 = true;
                }
                if (z) {
                    if (a) {
                        FollowManager.m18204a().m18224b(l2.longValue());
                    } else {
                        FollowManager.m18204a().m18227c(l2.longValue());
                    }
                }
                if (toggleFollowStateListener2 != null) {
                    boolean z3;
                    ToggleFollowStateListener toggleFollowStateListener = toggleFollowStateListener2;
                    if (a) {
                        z3 = false;
                    } else {
                        z3 = true;
                    }
                    toggleFollowStateListener.mo6399a(z, z3, z2);
                }
                NotificationCenter.m19011a().m19017b("FOLLOW_STATE_CHANGED", "FOLLOW_STATE_ACCOUNT", l2);
            }
        });
    }

    public static List<Long> m18205a(NetworkResponse networkResponse, String str) {
        if (!networkResponse.c()) {
            return new ArrayList();
        }
        JsonNode jsonNode = (JsonNode) JsonUtils.m18985a(networkResponse.l, JsonNode.class);
        if (jsonNode == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = jsonNode.get(str).iterator();
        while (it.hasNext()) {
            arrayList.add(Long.valueOf(((JsonNode) it.next()).asLong()));
        }
        return arrayList;
    }

    public void m18214a(FolloweesResponseCallback followeesResponseCallback) {
        FollowListDataSource followListDataSource = new FollowListDataSource(1, UserManager.a().f(), null, followeesResponseCallback);
        if (followListDataSource.m17659i() == DataState.NONE) {
            followListDataSource.m17665o();
        } else if (followeesResponseCallback != null) {
            FolloweesResponse a = FolloweesResponse.a(NetworkResponse.a());
            a.mFollowees = followListDataSource.m18200w();
            followeesResponseCallback.handleResponse(a);
        }
    }

    private Future<?> m18206a(final long j, final FolloweesResponseCallback followeesResponseCallback) {
        if (this.f16658f) {
            return null;
        }
        this.f16658f = true;
        return MagicNetwork.a(new Runnable(this) {
            final /* synthetic */ FollowManager f16629c;

            public void run() {
                FolloweesResponse a = FolloweesResponse.a(NetworkUtils.m18104a(this.f16629c.f16655c.getFollowees(new SocialAPI$GetFolloweesRequest().setAccountId(Long.valueOf(j)))));
                if (a.a()) {
                    this.f16629c.m18221a(a.mFollowees, a.mAccountApps);
                    if (j == UserManager.a().f()) {
                        for (AccountIcon accountIcon : a.mFollowees) {
                            this.f16629c.f16656d.add(Long.valueOf(accountIcon.accountId));
                        }
                        NotificationCenter.m19011a().m19017b("PERSONAL_FOLLOW_INFO_UPDATED_EVENT", new Object[0]);
                    }
                }
                this.f16629c.f16658f = false;
                CoreUtil.m18079a(followeesResponseCallback, a);
            }
        });
    }

    private Future<?> m18207a(final long j, final FollowersResponseCallback followersResponseCallback) {
        return MagicNetwork.a(new Runnable(this) {
            final /* synthetic */ FollowManager f16632c;

            public void run() {
                FollowersResponse a = FollowersResponse.a(NetworkUtils.m18104a(this.f16632c.f16655c.getFollowers(new SocialAPI$GetFollowersRequest().setAccountId(Long.valueOf(j)))));
                if (a.a()) {
                    this.f16632c.m18221a(a.mFollowers, a.mAccountApps);
                }
                CoreUtil.m18079a(followersResponseCallback, a);
            }
        });
    }

    public int m18223b() {
        return this.f16656d.size();
    }

    public boolean m18222a(long j) {
        return !this.f16657e.contains(Long.valueOf(j)) && this.f16656d.contains(Long.valueOf(j));
    }

    protected void m18221a(List<AccountIcon> list, List<AccountApps> list2) {
        HashMap hashMap = new HashMap();
        for (AccountApps accountApps : list2) {
            hashMap.put(Long.valueOf(accountApps.accountId), accountApps);
        }
        for (AccountIcon accountIcon : list) {
            AccountApps accountApps2 = (AccountApps) hashMap.get(Long.valueOf(accountIcon.accountId));
            if (accountApps2 != null) {
                accountIcon.a(accountApps2.apps);
            }
        }
    }

    public void m18224b(long j) {
        m18213a(j, true);
    }

    public void m18213a(long j, boolean z) {
        this.f16656d.remove(Long.valueOf(j));
        this.f16657e.add(Long.valueOf(j));
        if (z) {
            new FollowListDataSource(1, UserManager.a().f(), null, null).m17670t();
        }
    }

    public void m18227c(long j) {
        m18225b(j, true);
    }

    public void m18225b(long j, boolean z) {
        this.f16656d.add(Long.valueOf(j));
        this.f16657e.remove(Long.valueOf(j));
        if (z) {
            new FollowListDataSource(1, UserManager.a().f(), null, null).m17670t();
        }
    }

    private Collection<Long> m18211b(Collection<Long> collection) {
        Collection hashSet = new HashSet(collection);
        hashSet.removeAll(this.f16656d);
        hashSet.removeAll(this.f16657e);
        return hashSet;
    }

    public void m18216a(Long l, Runnable runnable) {
        m18219a(Collections.singletonList(l), runnable);
    }

    public void m18219a(Collection<Long> collection, final Runnable runnable) {
        final Collection b = m18211b((Collection) collection);
        if (b.size() != 0) {
            MagicNetwork.a(new Runnable(this) {
                final /* synthetic */ FollowManager f16635c;

                public void run() {
                    NetworkResponse a = NetworkUtils.m18104a(this.f16635c.f16655c.isFollowingAccountIds(new SocialAPI$IsFollowingAccountIdsRequest().setAccountIds(b)));
                    if (a.c()) {
                        for (Long longValue : FollowManager.m18205a(a, "following")) {
                            this.f16635c.m18225b(longValue.longValue(), false);
                        }
                        for (Long longValue2 : FollowManager.m18205a(a, "notFollowing")) {
                            this.f16635c.m18213a(longValue2.longValue(), false);
                        }
                    }
                    if (runnable != null) {
                        runnable.run();
                    }
                }
            });
        } else if (runnable != null) {
            runnable.run();
        }
    }

    public void m18218a(Collection<Long> collection) {
        Collection b = m18211b((Collection) collection);
        if (b.size() != 0) {
            NetworkResponse a = NetworkUtils.m18104a(this.f16655c.isFollowingAccountIds(new SocialAPI$IsFollowingAccountIdsRequest().setAccountIds(b)));
            if (a.c()) {
                for (Long longValue : m18205a(a, "following")) {
                    m18225b(longValue.longValue(), false);
                }
                for (Long longValue2 : m18205a(a, "notFollowing")) {
                    m18213a(longValue2.longValue(), false);
                }
            }
        }
    }

    public List<String> m18226c() {
        List<String> list = null;
        FollowListDataSource followListDataSource = new FollowListDataSource(1, UserManager.a().f(), null, null);
        if (followListDataSource.m17659i() != DataState.NONE || this.f16658f) {
            list = new ArrayList();
            for (AccountIcon accountIcon : followListDataSource.m18200w()) {
                list.add(accountIcon.handle);
            }
        } else {
            followListDataSource.m17665o();
        }
        return list;
    }
}
