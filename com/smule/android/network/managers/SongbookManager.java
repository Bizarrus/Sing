/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.SystemClock
 *  android.support.annotation.NonNull
 *  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 *  com.fasterxml.jackson.annotation.JsonProperty
 *  retrofit2.Call
 */
package com.smule.android.network.managers;

import android.os.SystemClock;
import android.support.annotation.NonNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.logging.Log;
import com.smule.android.network.api.SongbookAPI;
import com.smule.android.network.core.CoreUtil;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.core.SnpRequest;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.ArrangementVersion;
import com.smule.android.network.models.ArrangementVersionLite;
import com.smule.android.utils.NotificationCenter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import retrofit2.Call;

public class SongbookManager {
    public static final String a = SongbookManager.class.getName();
    protected static SongbookManager b = null;
    public static final long c = TimeUnit.MILLISECONDS.convert(5, TimeUnit.MINUTES);
    private final LinkedList<Runnable> d = new LinkedList();
    private com.smule.android.network.api.SongbookAPI e = MagicNetwork.a().a(com.smule.android.network.api.SongbookAPI.class);
    private Map<Long, String> f = new LinkedHashMap<Long, String>();
    private Long g = null;
    private Long h;
    private List<> i = new ArrayList<>();
    private  j;
    private long k = 0;
    private long l = 0;
    private AtomicBoolean m = new AtomicBoolean(false);
    private AtomicBoolean n = new AtomicBoolean(false);

    private SongbookManager() {
    }

    private void a(@NonNull  getSongbookResponse) {
        synchronized (this) {
            this.b(getSongbookResponse);
            this.k = SystemClock.elapsedRealtime();
            this.l = UserManager.a().f();
            return;
        }
    }

    public static SongbookManager b() {
        if (b == null) {
            b = new SongbookManager();
        }
        return b;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void b(@NonNull  getSongbookResponse) {
        synchronized (this) {
            if (getSongbookResponse.mCategories != null && !getSongbookResponse.mCategories.isEmpty()) {
                this.h = getSongbookResponse.mCategories.get((int)0).mId;
                this.f.clear();
                for ( category : getSongbookResponse.mCategories) {
                    this.f.put(category.mId, category.mDisplayName);
                }
            } else {
                String string2 = a;
                StringBuilder stringBuilder = new StringBuilder().append("Missing categories in songbook response ");
                String string3 = getSongbookResponse.a != null ? getSongbookResponse.a.j : "(null)";
                Log.e(string2, stringBuilder.append(string3).toString());
            }
            if (getSongbookResponse.mCat1Songs != null) {
                this.i.clear();
                this.i.addAll(getSongbookResponse.mCat1Songs);
                this.j = getSongbookResponse.mCat1Cursor;
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private boolean k() {
        long l = UserManager.a().f();
        boolean bl = this.l == 0 && l == 0 || this.l != 0 && this.l == l;
        if (SystemClock.elapsedRealtime() - this.k >= 600000) return false;
        if (bl) {
            return true;
        }
        return false;
    }

    private void l() {
        block4 : {
             getSongbookResponse = this.j();
            if (getSongbookResponse == null) break block4;
            if (!getSongbookResponse.a()) break block4;
            this.a(getSongbookResponse);
            this.m();
        }
        return;
        finally {
            this.m.set(false);
        }
    }

    private void m() {
        NotificationCenter.a().b("SONGBOOK_FOR_USER_UPDATED_EVENT", "ACTION", "CATEGORY_UPDATED_ACTION");
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void n() {
        LinkedList<Runnable> linkedList;
        LinkedList<Runnable> linkedList2 = this.d;
        synchronized (linkedList2) {
            linkedList = new LinkedList<Runnable>(this.d);
            this.d.clear();
        }
        while (linkedList.size() > 0) {
            linkedList.remove(0).run();
        }
        return;
    }

    public  a(SongbookAPI.SortType sortType) {
        return .a(NetworkUtils.a(this.e.getCategoryList(new SnpRequest(){
            public String sort;

            public SongbookAPI setSort(SongbookAPI.SortType sortType) {
                if (sortType != null) {
                    this.sort = sortType.getValue();
                }
                return this;
            }

            public static enum SongbookAPI.SortType {
                POPULAR("POPULAR"),
                ALPHA("ALPHA");
                
                private final String value;

                private SongbookAPI.SortType(String string3) {
                    this.value = string3;
                }

                public String getValue() {
                    return this.value;
                }
            }

        }.setSort(sortType))));
    }

    public  a(long l, String string2, int n) {
        return .a(NetworkUtils.a(this.e.getCategorySongs(new SnpRequest(){
            public String cursor;
            public Long id;
            public Integer limit = 10;

            public SongbookAPI setCursor(String string2) {
                if (string2 != null) {
                    this.cursor = string2;
                }
                return this;
            }

            public SongbookAPI setId(Long l) {
                if (l != null) {
                    this.id = l;
                }
                return this;
            }

            public SongbookAPI setLimit(Integer n) {
                if (n != null) {
                    this.limit = n;
                }
                return this;
            }
        }.setId(l).setLimit(n).setCursor(string2))));
    }

    public  a(String string2) {
        return .a(NetworkUtils.a(this.e.getArrangementFromRavenSongRequest(new SnpRequest(){
            public String rsongId;

            public SongbookAPI setRavenSongId(String string2) {
                if (string2 != null) {
                    this.rsongId = string2;
                }
                return this;
            }
        }.setRavenSongId(string2))));
    }

    public  a(List<Long> list) {
        return .a(NetworkUtils.a(this.e.submitSongbookUpdate(new SnpRequest(){
            public List<Long> categoryIds;

            public SongbookAPI setCategoryIds(List<Long> list) {
                this.categoryIds = list;
                return this;
            }
        }.setCategoryIds(list))));
    }

    public Future<?> a(final long l, final String string2, final int n, final GetCategorySongsCallback getCategorySongsCallback) {
        return MagicNetwork.a(new Runnable(){

            @Override
            public void run() {
                CoreUtil.a(getCategorySongsCallback, SongbookManager.this.a(l, string2, n));
            }
        });
    }

    public Future<?> a(final SongbookAPI.SortType sortType, final GetCategoryListCallback getCategoryListCallback) {
        this.n.set(true);
        return MagicNetwork.a(new Runnable(){

            @Override
            public void run() {
                CoreUtil.a(getCategoryListCallback, SongbookManager.this.a(sortType));
                SongbookManager.this.n.set(false);
            }
        });
    }

    public Future<?> a(final String string2, final GetArrangementFromRavenSongCallback getArrangementFromRavenSongCallback) {
        return MagicNetwork.a(new Runnable(){

            @Override
            public void run() {
                CoreUtil.a(getArrangementFromRavenSongCallback, SongbookManager.this.a(string2));
            }
        });
    }

    public Future<?> a(final List<Long> list, final SubmitSongbookUpdateCallback submitSongbookUpdateCallback) {
        return MagicNetwork.a(new Runnable(){

            @Override
            public void run() {
                CoreUtil.a(submitSongbookUpdateCallback, SongbookManager.this.a(list));
            }
        });
    }

    public AtomicBoolean a() {
        return this.n;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a(long l, boolean bl) {
        synchronized (this) {
            if (this.h.equals(l) && this.j != null) {
                this.j = null;
                this.i.clear();
            }
            this.g = bl ? Long.valueOf(l) : null;
            return;
        }
    }

    public void a(@NonNull HashMap<Long, String> hashMap) {
        synchronized (this) {
            this.f.clear();
            this.f = hashMap;
            return;
        }
    }

    public boolean a(Long l) {
        boolean bl = false;
        if (this.h != null) {
            bl = l.equals(this.h);
        }
        return bl;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public boolean a(Runnable runnable) {
        if (runnable != null) {
            LinkedList<Runnable> linkedList = this.d;
            synchronized (linkedList) {
                this.d.addLast(runnable);
            }
        }
        if (this.k()) {
            Log.b(a, "refreshSongbookIfNeeded() - not enough time has lapsed for the songbook refresh to occur");
            this.n();
            return false;
        }
        if (this.m.getAndSet(true)) {
            Log.b(a, "refreshSongbookIfNeeded() - sync already in progress");
            return false;
        }
        MagicNetwork.a(new Runnable(){

            @Override
            public void run() {
                SongbookManager.this.l();
                SongbookManager.this.n();
            }
        });
        return true;
    }

    public boolean b(Long l) {
        if (this.g == null) {
            return false;
        }
        return this.g.equals(l);
    }

    public String c() {
        String string2 = null;
        if (this.h != null) {
            string2 = this.h.toString();
        }
        return string2;
    }

    public String d() {
        String string2;
        String string3 = string2 = this.c();
        if (string2 == null) {
            string3 = "-";
        }
        return string3;
    }

    public final Map<Long, String> e() {
        synchronized (this) {
            Map<Long, String> map = this.f;
            return map;
        }
    }

    public final List<> f() {
        return this.i;
    }

    public final  g() {
        return this.j;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean h() {
        synchronized (this) {
            boolean bl = this.f.isEmpty();
            if (bl) return false;
            return true;
        }
    }

    public Long i() {
        return this.g;
    }

    public  j() {
        return .a(NetworkUtils.a(this.e.getSongbook(new SnpRequest(){
            public String cat1SongsCursor = "start";
            public Integer cat1SongsLimit = 10;

            public SongbookAPI setCat1SongsCursor(String string2) {
                this.cat1SongsCursor = string2;
                return this;
            }

            public SongbookAPI setCat1SongsLimit(Integer n) {
                if (n != null) {
                    this.cat1SongsLimit = n;
                }
                return this;
            }
        })));
    }

    public static interface GetArrangementFromRavenSongCallback
    extends ResponseInterface<> {
        @Override
        public void handleResponse( var1);
    }

    public static interface GetCategoryListCallback
    extends ResponseInterface<> {
        @Override
        public void handleResponse( var1);
    }

    public static interface GetCategorySongsCallback
    extends ResponseInterface<> {
        @Override
        public void handleResponse( var1);
    }

    public static interface GetSongbookCallback
    extends ResponseInterface<> {
        @Override
        public void handleResponse( var1);
    }

    public static interface SubmitSongbookUpdateCallback
    extends ResponseInterface<> {
        @Override
        public void handleResponse( var1);
    }

}

