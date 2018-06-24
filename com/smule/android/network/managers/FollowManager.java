/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.support.v4.util.LruCache
 *  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 *  com.fasterxml.jackson.annotation.JsonProperty
 *  com.fasterxml.jackson.databind.JsonNode
 *  retrofit2.Call
 */
package com.smule.android.network.managers;

import android.os.Parcel;
import android.support.v4.util.LruCache;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.network.api.SocialAPI;
import com.smule.android.network.core.CoreUtil;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.core.SnpRequest;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.AccountApps;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.utils.JsonUtils;
import com.smule.android.utils.ListSetConverter;
import com.smule.android.utils.NotificationCenter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Future;
import retrofit2.Call;

public class FollowManager {
    private static final String a = FollowManager.class.getName();
    private static FollowManager b = null;
    private com.smule.android.network.api.SocialAPI c = MagicNetwork.a().a(com.smule.android.network.api.SocialAPI.class);
    private Set<Long> d = new TreeSet<Long>();
    private Set<Long> e = new TreeSet<Long>();
    private boolean f;

    private FollowManager() {
    }

    public static FollowManager a() {
        if (b == null) {
            b = new FollowManager();
        }
        return b;
    }

    public static List<Long> a(NetworkResponse object, String object2) {
        if (!object.c()) {
            return new ArrayList<Long>();
        }
        JsonNode jsonNode = JsonUtils.a(object.l, JsonNode.class);
        if (jsonNode == null) {
            return new ArrayList<Long>();
        }
        object = new ArrayList();
        object2 = jsonNode.get((String)object2).iterator();
        while (object2.hasNext()) {
            object.add(((JsonNode)object2.next()).asLong());
        }
        return object;
    }

    private Future<?> a(final long l, final FolloweesResponseCallback followeesResponseCallback) {
        if (this.f) {
            return null;
        }
        this.f = true;
        return MagicNetwork.a(new Runnable(){

            @Override
            public void run() {
                 followeesResponse = .a(NetworkUtils.a(FollowManager.this.c.getFollowees(new SnpRequest(){
                    public Long accountId;
                    public Collection<String> apps;

                    public SocialAPI setAccountId(Long l) {
                        this.accountId = l;
                        return this;
                    }

                    public SocialAPI setApps(Collection<String> collection) {
                        if (collection != null && collection.size() > 0) {
                            this.apps = collection;
                        }
                        return this;
                    }
                }.setAccountId(l))));
                if (followeesResponse.a()) {
                    FollowManager.this.a(followeesResponse.mFollowees, followeesResponse.mAccountApps);
                    if (l == UserManager.a().f()) {
                        for (com.smule.android.network.models.AccountIcon accountIcon : followeesResponse.mFollowees) {
                            FollowManager.this.d.add(accountIcon.accountId);
                        }
                        NotificationCenter.a().b("PERSONAL_FOLLOW_INFO_UPDATED_EVENT", new Object[0]);
                    }
                }
                FollowManager.this.f = false;
                CoreUtil.a(followeesResponseCallback, followeesResponse);
            }
        });
    }

    private Future<?> a(final long l, final FollowersResponseCallback followersResponseCallback) {
        return MagicNetwork.a(new Runnable(){

            @Override
            public void run() {
                 followersResponse = .a(NetworkUtils.a(FollowManager.this.c.getFollowers(new SnpRequest(){
                    public Long accountId;
                    public List<String> apps;

                    public SocialAPI setAccountId(Long l) {
                        this.accountId = l;
                        return this;
                    }

                    public SocialAPI setApps(List<String> list) {
                        if (list != null && list.size() > 0) {
                            this.apps = list;
                        }
                        return this;
                    }
                }.setAccountId(l))));
                if (followersResponse.a()) {
                    FollowManager.this.a(followersResponse.mFollowers, followersResponse.mAccountApps);
                }
                CoreUtil.a(followersResponseCallback, followersResponse);
            }
        });
    }

    private Collection<Long> b(Collection<Long> collection) {
        collection = new HashSet<Long>(collection);
        collection.removeAll(this.d);
        collection.removeAll(this.e);
        return collection;
    }

    public void a(long l, boolean bl) {
        this.d.remove(l);
        this.e.add(l);
        if (bl) {
            new FollowListDataSource(1, UserManager.a().f(), null, null).u();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(FolloweesResponseCallback followeesResponseCallback) {
        FollowListDataSource followListDataSource = new FollowListDataSource(1, UserManager.a().f(), null, followeesResponseCallback);
        if (followListDataSource.i() == MagicDataSource.DataState.a) {
            followListDataSource.o();
            return;
        } else {
            if (followeesResponseCallback == null) return;
            {
                 followeesResponse = .a(NetworkResponse.a());
                followeesResponse.mFollowees = followListDataSource.x();
                followeesResponseCallback.handleResponse(followeesResponse);
                return;
            }
        }
    }

    public void a(Long l, ToggleFollowStateListener toggleFollowStateListener) {
        this.a(l, "APP", null, toggleFollowStateListener);
    }

    public void a(Long l, Runnable runnable) {
        this.a(Collections.singletonList(l), runnable);
    }

    public void a(Long l, String string2, String string3, ToggleFollowStateListener toggleFollowStateListener) {
        MagicNetwork.a(new Runnable(FollowManager.a().a(l), l, string2, string3, toggleFollowStateListener){
            final /* synthetic */ boolean a;
            final /* synthetic */ Long b;
            final /* synthetic */ String c;
            final /* synthetic */ String d;
            final /* synthetic */ ToggleFollowStateListener e;
            {
                this.a = bl;
                this.b = l;
                this.c = string2;
                this.d = string3;
                this.e = toggleFollowStateListener;
            }

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void run() {
                Object object = this.a ? NetworkUtils.a(FollowManager.this.c.updateFollowees(new SnpRequest(){
                    public List<Long> add;
                    public String context;
                    public String name;
                    public List<Long> remove;

                    public SocialAPI setAdd(List<Long> list) {
                        this.add = list;
                        return this;
                    }

                    public SocialAPI setContextAndName(String string2, String string3) {
                        if (string3 != null) {
                            this.name = string3;
                        }
                        if (string2 != null) {
                            this.context = string2;
                        }
                        return this;
                    }

                    public SocialAPI setRemove(List<Long> list) {
                        this.remove = list;
                        return this;
                    }
                }.setRemove(Collections.singletonList(this.b)).setAdd(new ArrayList<Long>()).setContextAndName(this.c, this.d))) : NetworkUtils.a(FollowManager.this.c.updateFollowees(new .setAdd(Collections.singletonList(this.b)).setRemove(new ArrayList<Long>()).setContextAndName(this.c, this.d)));
                boolean bl = object != null && object.b == 0;
                boolean bl2 = object != null && object.b == 2004;
                if (bl) {
                    if (this.a) {
                        FollowManager.a().b(this.b);
                    } else {
                        FollowManager.a().c(this.b);
                    }
                }
                if (this.e != null) {
                    object = this.e;
                    boolean bl3 = !this.a;
                    object.a(bl, bl3, bl2);
                }
                NotificationCenter.a().b("FOLLOW_STATE_CHANGED", "FOLLOW_STATE_ACCOUNT", this.b);
            }
        });
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(Collection<Long> iterator) {
        if ((iterator = this.b((Collection<Long>)((Object)iterator))).size() != 0 && (iterator = NetworkUtils.a(this.c.isFollowingAccountIds(new SnpRequest(){
            public Collection<Long> accountIds;
            public List<String> apps = Collections.singletonList(MagicNetwork.b());

            public SocialAPI setAccountIds(Collection<Long> collection) {
                this.accountIds = collection;
                return this;
            }

            public SocialAPI setApps(List<String> list) {
                this.apps = list;
                return this;
            }
        }.setAccountIds((Collection<Long>)((Object)iterator))))).c()) {
            Iterator<Long> iterator2 = FollowManager.a((NetworkResponse)((Object)iterator), "following").iterator();
            while (iterator2.hasNext()) {
                this.b(iterator2.next(), false);
            }
            iterator = FollowManager.a((NetworkResponse)((Object)iterator), "notFollowing").iterator();
            while (iterator.hasNext()) {
                this.a((long)iterator.next(), false);
            }
        }
    }

    public void a(final Collection<Long> collection, final Runnable runnable) {
        if ((collection = this.b(collection)).size() == 0) {
            if (runnable != null) {
                runnable.run();
            }
            return;
        }
        MagicNetwork.a(new Runnable(){

            @Override
            public void run() {
                NetworkResponse networkResponse = NetworkUtils.a(FollowManager.this.c.isFollowingAccountIds(new .setAccountIds(collection)));
                if (networkResponse.c()) {
                    for (Long l : FollowManager.a(networkResponse, "following")) {
                        FollowManager.this.b(l, false);
                    }
                    for (Long l : FollowManager.a(networkResponse, "notFollowing")) {
                        FollowManager.this.a((long)l, false);
                    }
                }
                if (runnable != null) {
                    runnable.run();
                }
            }
        });
    }

    public void a(HashSet<Long> hashSet, String string2, String string3, FollowListener followListener) {
        Iterator<Long> iterator = hashSet.iterator();
        while (iterator.hasNext()) {
            long l = iterator.next();
            if (this.a(l)) {
                iterator.remove();
                continue;
            }
            this.c(l);
        }
        MagicNetwork.a(new Runnable(ListSetConverter.a(hashSet), string2, string3, followListener){
            final /* synthetic */ List a;
            final /* synthetic */ String b;
            final /* synthetic */ String c;
            final /* synthetic */ FollowListener d;
            {
                this.a = list;
                this.b = string2;
                this.c = string3;
                this.d = followListener;
            }

            @Override
            public void run() {
                NetworkResponse networkResponse = NetworkUtils.a(FollowManager.this.c.updateFollowees(new .setAdd(this.a).setRemove(new ArrayList<Long>()).setContextAndName(this.b, this.c)));
                if (this.d != null) {
                    this.d.a(networkResponse);
                }
            }
        });
    }

    protected void a(List<com.smule.android.network.models.AccountIcon> object, List<AccountApps> object2) {
        AccountApps accountApps;
        HashMap<Long, AccountApps> hashMap = new HashMap<Long, AccountApps>();
        object2 = object2.iterator();
        while (object2.hasNext()) {
            accountApps = (AccountApps)object2.next();
            hashMap.put(accountApps.accountId, accountApps);
        }
        object = object.iterator();
        while (object.hasNext()) {
            object2 = (com.smule.android.network.models.AccountIcon)object.next();
            accountApps = (AccountApps)hashMap.get(object2.accountId);
            if (accountApps == null) continue;
            object2.a((Collection<String>)accountApps.apps);
        }
    }

    public boolean a(long l) {
        if (!this.e.contains(l) && this.d.contains(l)) {
            return true;
        }
        return false;
    }

    public int b() {
        return this.d.size();
    }

    public void b(long l) {
        this.a(l, true);
    }

    public void b(long l, boolean bl) {
        this.d.add(l);
        this.e.remove(l);
        if (bl) {
            new FollowListDataSource(1, UserManager.a().f(), null, null).u();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public List<String> c() {
        ArrayList<String> arrayList = null;
        Object object = new FollowListDataSource(1, UserManager.a().f(), null, null);
        if (object.i() == MagicDataSource.DataState.a && !this.f) {
            object.o();
            return arrayList;
        } else {
            ArrayList<String> arrayList2 = new ArrayList<String>();
            object = object.x().iterator();
            do {
                arrayList = arrayList2;
                if (!object.hasNext()) return arrayList;
                arrayList2.add(((com.smule.android.network.models.AccountIcon)object.next()).handle);
            } while (true);
        }
    }

    public void c(long l) {
        this.b(l, true);
    }

    public static class FollowListDataSource
    extends MagicDataSource<com.smule.android.network.models.AccountIcon, MagicDataSource.OffsetPaginationTracker> {
        private int a;
        private long b;
        private int l;
        private int m;
        private FollowersResponseCallback n;
        private FolloweesResponseCallback o;

        public FollowListDataSource(int n, long l, FollowersResponseCallback followersResponseCallback, FolloweesResponseCallback followeesResponseCallback) {
            super(FollowManager.class.getName() + "**" + n + "**" + l, new MagicDataSource.OffsetPaginationTracker());
            this.a = n;
            this.b = l;
            this.n = followersResponseCallback;
            this.o = followeesResponseCallback;
        }

        /*
         * Enabled aggressive block sorting
         */
        private void a(final com.smule.android.network.managers.FollowManager$FollowersResponse followersResponse, final com.smule.android.network.managers.FollowManager$FolloweesResponse followeesResponse, final MagicDataSource.FetchDataCallback<com.smule.android.network.models.AccountIcon, MagicDataSource.OffsetPaginationTracker> fetchDataCallback) {
            final List<com.smule.android.network.models.AccountIcon> list = this.a == 0 ? followersResponse.mFollowers : followeesResponse.mFollowees;
            ArrayList<Long> arrayList = new ArrayList<Long>();
            Iterator<com.smule.android.network.models.AccountIcon> iterator = list.iterator();
            do {
                if (!iterator.hasNext()) {
                    FollowManager.a().a(arrayList, new Runnable(){

                        /*
                         * Enabled aggressive block sorting
                         */
                        @Override
                        public void run() {
                            ArrayList<com.smule.android.network.models.AccountIcon> arrayList = new ArrayList<com.smule.android.network.models.AccountIcon>();
                            ArrayList<com.smule.android.network.models.AccountIcon> arrayList2 = new ArrayList<com.smule.android.network.models.AccountIcon>();
                            for (com.smule.android.network.models.AccountIcon accountIcon : list) {
                                if (accountIcon.b()) {
                                    arrayList.add(accountIcon);
                                    continue;
                                }
                                arrayList2.add(accountIcon);
                            }
                            Collections.sort(arrayList, new Comparator<com.smule.android.network.models.AccountIcon>(){

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
                            Collections.sort(arrayList2, new );
                            FollowListDataSource.this.l = arrayList.size();
                            FollowListDataSource.this.m = arrayList2.size();
                            arrayList.addAll(arrayList2);
                            if (FollowListDataSource.this.a == 0) {
                                if (FollowListDataSource.this.n != null) {
                                    FollowListDataSource.this.n.handleResponse(followersResponse);
                                }
                            } else if (FollowListDataSource.this.o != null) {
                                FollowListDataSource.this.o.handleResponse(followeesResponse);
                            }
                            fetchDataCallback.a(arrayList, new MagicDataSource.OffsetPaginationTracker(-1));
                        }
                    });
                    return;
                }
                arrayList.add(iterator.next().accountId);
            } while (true);
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        @Override
        public Future<?> a(MagicDataSource.OffsetPaginationTracker offsetPaginationTracker, int n, final MagicDataSource.FetchDataCallback<com.smule.android.network.models.AccountIcon, MagicDataSource.OffsetPaginationTracker> fetchDataCallback) {
            if (this.a == 0) {
                FollowManager.a().a(this.b, new FollowersResponseCallback(){

                    @Override
                    public void handleResponse(com.smule.android.network.managers.FollowManager$FollowersResponse followersResponse) {
                        if (!followersResponse.a()) {
                            if (FollowListDataSource.this.n != null) {
                                FollowListDataSource.this.n.handleResponse(followersResponse);
                            }
                            fetchDataCallback.a();
                            return;
                        }
                        FollowListDataSource.this.a(followersResponse, null, fetchDataCallback);
                    }
                });
                do {
                    return null;
                    break;
                } while (true);
            }
            FollowManager.a().a(this.b, new FolloweesResponseCallback(){

                @Override
                public void handleResponse(com.smule.android.network.managers.FollowManager$FolloweesResponse followeesResponse) {
                    if (!followeesResponse.a()) {
                        if (FollowListDataSource.this.o != null) {
                            FollowListDataSource.this.o.handleResponse(followeesResponse);
                        }
                        fetchDataCallback.a();
                        return;
                    }
                    FollowListDataSource.this.a(null, followeesResponse, fetchDataCallback);
                }
            });
            return null;
        }

        public int e() {
            return this.l;
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        @Override
        protected boolean f() {
            boolean bl;
            if (this.f == null) {
                return false;
            }
            FollowListCacheItem followListCacheItem = (FollowListCacheItem)d.get((Object)this.f);
            if (followListCacheItem == null) {
                return false;
            }
            String string2 = this.f;
            synchronized (string2) {
                if (this.a(followListCacheItem)) {
                    d.remove((Object)this.f);
                    return false;
                }
                followListCacheItem.d.setDataPosition(0);
                this.b(followListCacheItem.d);
                this.l = followListCacheItem.e;
                this.m = followListCacheItem.f;
                this.h = this.a(this.g);
                bl = true;
            }
            boolean bl2 = bl;
            if (!bl) return bl2;
            this.i = new MagicDataSource.FetchDataCallbackObject();
            this.i.a(this.g, true);
            return bl;
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        @Override
        protected void g() {
            synchronized (this) {
                long l;
                if (this.f != null && (l = this.d()) != 0) {
                    MagicDataSource.CacheItem cacheItem;
                    MagicDataSource.CacheItem cacheItem2 = cacheItem = (MagicDataSource.CacheItem)d.get((Object)this.f);
                    if (cacheItem == null) {
                        cacheItem2 = new FollowListCacheItem(this.f, System.currentTimeMillis(), this.d() * 1000, this.l, this.m);
                        d.put((Object)this.f, (Object)cacheItem2);
                    }
                    cacheItem2.d.setDataPosition(0);
                    this.a(cacheItem2.d);
                    this.h();
                }
                return;
            }
        }

        public int w() {
            return this.m;
        }

        public List<com.smule.android.network.models.AccountIcon> x() {
            return Collections.unmodifiableList(this.g);
        }

        protected static class FollowListCacheItem
        extends MagicDataSource.CacheItem {
            protected int e;
            protected int f;

            protected FollowListCacheItem(String string2, long l, long l2, int n, int n2) {
                super(string2, l, l2);
                this.e = n;
                this.f = n2;
            }
        }

    }

    public static interface FollowListener {
        public void a(NetworkResponse var1);
    }

    public static interface FolloweesResponseCallback
    extends ResponseInterface<> {
        @Override
        public void handleResponse( var1);
    }

    public static interface FollowersResponseCallback
    extends ResponseInterface<> {
        @Override
        public void handleResponse( var1);
    }

    public static interface ToggleFollowStateListener {
        public void a(boolean var1, boolean var2, boolean var3);
    }

}

