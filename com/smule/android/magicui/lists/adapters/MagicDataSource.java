/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Handler
 *  android.os.Looper
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  android.support.v4.util.LruCache
 */
package com.smule.android.magicui.lists.adapters;

import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.LruCache;
import com.smule.android.logging.Log;
import com.smule.android.network.models.CursorModel;
import com.smule.android.utils.ParcelUtils;
import com.smule.android.utils.ThreadUtils;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class MagicDataSource<T, PT extends PaginationTracker> {
    private static final long a = TimeUnit.MINUTES.toSeconds(5);
    protected static final LruCache<String, CacheItem> d = new LruCache(50);
    static final String e = MagicDataSource.class.getName();
    private final AtomicBoolean b = new AtomicBoolean(false);
    protected boolean c;
    protected String f;
    protected ArrayList<T> g = new ArrayList();
    protected ArrayList<T> h = new ArrayList();
    protected MagicDataSource<T, PT> i;
    protected PT j;
    WeakReference<DataSourceObserver> k;
    private volatile DataState l = DataState.a;
    private volatile boolean m = false;
    private volatile boolean n;

    public MagicDataSource(PT PT) {
        this(null, PT);
    }

    public MagicDataSource(String string2, PT PT) {
        this.b(string2);
        this.j = PT;
        this.c = this.f();
    }

    public static void a(String string2) {
        if (d != null) {
            d.remove((Object)string2);
        }
    }

    private void a(boolean bl) {
        synchronized (this) {
            ThreadUtils.a();
            if ((bl || this.l()) && this.b.compareAndSet(false, true)) {
                if (!this.n && this.g.size() == 0 || this.l == DataState.d) {
                    this.l = DataState.c;
                    new Handler(Looper.getMainLooper()).post(new Runnable(){

                        @Override
                        public void run() {
                            MagicDataSource.this.b(false);
                        }
                    });
                }
                this.i = new FetchDataCallbackObject();
                this.e();
                if (bl) {
                    this.j.c();
                }
                this.a(this.j, this.a(), (FetchDataCallback<T, PT>)((Object)this.i));
            }
            return;
        }
    }

    private void b(List<T> list) {
        Log.b(e, "notifyFetchDataFinished " + list);
        if (this.k != null && this.k.get() != null) {
            this.k.get().a(this, list);
        }
    }

    private void e() {
        Log.b(e, "notifyFetchDataStarted");
        if (this.k != null && this.k.get() != null) {
            this.k.get().d(this);
        }
    }

    public int a() {
        return 10;
    }

    public final T a(int n) {
        return this.h.get(n);
    }

    public ArrayList<T> a(List<T> list) {
        return new ArrayList<T>(list);
    }

    public abstract Future<?> a(PT var1, int var2, FetchDataCallback<T, PT> var3);

    public void a(int n, T t) {
        this.g.add(n, t);
        this.h = this.a(this.g);
        if (!this.h.isEmpty()) {
            this.l = DataState.b;
        }
    }

    protected void a(Parcel parcel) {
        parcel.writeInt(this.g.size());
        if (this.g.size() > 0) {
            parcel.writeSerializable(this.g.get(0).getClass());
            parcel.writeList(this.g);
        }
        parcel.writeParcelable(this.j, 0);
    }

    public void a(DataSourceObserver dataSourceObserver) {
        if (dataSourceObserver == null) {
            this.k = null;
            return;
        }
        this.k = new WeakReference<DataSourceObserver>(dataSourceObserver);
        this.m();
    }

    protected boolean a(CacheItem cacheItem) {
        if (System.currentTimeMillis() - cacheItem.b > this.d() * 1000) {
            return true;
        }
        return false;
    }

    public boolean a(Object object) {
        boolean bl = this.h.remove(object);
        this.g.remove(object);
        if (this.h.isEmpty()) {
            this.l = DataState.e;
        }
        return bl;
    }

    public int b() {
        return 2;
    }

    final void b(int n) {
        if (this.k() - n - 1 - this.b() <= 0 && !this.m) {
            this.o();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void b(Parcel parcel) {
        int n = parcel.readInt();
        if (n == 0) {
            this.g.clear();
        } else {
            Class class_ = (Class)parcel.readSerializable();
            this.g = new ArrayList<E>(n);
            parcel.readList(this.g, class_.getClassLoader());
        }
        this.j = (PaginationTracker)parcel.readParcelable(PaginationTracker.class.getClassLoader());
    }

    public void b(T t) {
        int n = this.g.indexOf(t);
        if (n != -1) {
            this.g.set(n, t);
        }
        if ((n = this.h.indexOf(t)) != -1) {
            this.h.set(n, t);
        }
    }

    public void b(String string2) {
        this.f = string2;
    }

    public void b(boolean bl) {
        Log.b(e, "notifyDataSetChanged");
        if (bl) {
            this.g();
        }
        if (this.k != null && this.k.get() != null) {
            this.k.get().c(this);
        }
    }

    protected void c() {
        if (this.f != null) {
            d.remove((Object)this.f);
        }
    }

    protected long d() {
        return a;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected boolean f() {
        boolean bl;
        if (this.f == null) {
            return false;
        }
        CacheItem cacheItem = (CacheItem)d.get((Object)this.f);
        if (cacheItem == null) {
            return false;
        }
        String string2 = this.f;
        synchronized (string2) {
            if (this.a(cacheItem)) {
                d.remove((Object)this.f);
                return false;
            }
            cacheItem.d.setDataPosition(0);
            this.b(cacheItem.d);
            this.h = this.a(this.g);
            bl = true;
        }
        boolean bl2 = bl;
        if (!bl) return bl2;
        this.i = new FetchDataCallbackObject();
        this.i.a(this.g, true);
        return bl;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected void g() {
        synchronized (this) {
            long l;
            if (this.f != null && (l = this.d()) != 0) {
                CacheItem cacheItem;
                CacheItem cacheItem2 = cacheItem = (CacheItem)d.get((Object)this.f);
                if (cacheItem == null) {
                    cacheItem2 = new CacheItem(this.f, System.currentTimeMillis(), this.d() * 1000);
                    d.put((Object)this.f, (Object)cacheItem2);
                }
                cacheItem2.d.setDataPosition(0);
                this.a(cacheItem2.d);
                this.h();
            }
            return;
        }
    }

    protected void h() {
        for (CacheItem cacheItem : d.snapshot().values()) {
            if (System.currentTimeMillis() - cacheItem.b <= cacheItem.c) continue;
            d.remove((Object)cacheItem.a);
        }
    }

    public DataState i() {
        return this.l;
    }

    public void j() {
        synchronized (this) {
            ThreadUtils.a();
            if (this.b.get()) {
                this.i.b();
                this.i = null;
                this.b.set(false);
            }
            return;
        }
    }

    public int k() {
        if (this.h != null) {
            return this.h.size();
        }
        return 0;
    }

    public final boolean l() {
        if (this.j.b() && !this.m) {
            return true;
        }
        return false;
    }

    public final void m() {
        this.m = false;
    }

    protected final void n() {
        this.j();
        this.n = true;
        this.r();
        this.a(true);
    }

    public final void o() {
        this.a(false);
    }

    public void p() {
        this.b(true);
    }

    public void q() {
        this.b(false);
    }

    public void r() {
        Log.b(e, "notifyRefreshStarted");
        if (this.k != null && this.k.get() != null) {
            this.k.get().a(this);
        }
    }

    public void s() {
        Log.b(e, "notifyRefreshFinished");
        if (this.k != null && this.k.get() != null) {
            this.k.get().b(this);
        }
    }

    public boolean t() {
        return this.n;
    }

    public void u() {
        this.c();
        this.g.clear();
        this.h.clear();
    }

    public boolean v() {
        return this.b.get();
    }

    protected static class CacheItem {
        protected final String a;
        protected final long b;
        protected final long c;
        public final Parcel d;

        protected CacheItem(String string2, long l, long l2) {
            this.a = string2;
            this.b = l;
            this.c = l2;
            this.d = Parcel.obtain();
        }
    }

    public static class CursorPaginationTracker
    extends PaginationTracker<CursorModel>
    implements Parcelable {
        public static final Parcelable.Creator<CursorPaginationTracker> CREATOR = new Parcelable.Creator<CursorPaginationTracker>(){

            public CursorPaginationTracker a(Parcel parcel) {
                return new CursorPaginationTracker(parcel);
            }

            public CursorPaginationTracker[] a(int n) {
                return new CursorPaginationTracker[n];
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return this.a(parcel);
            }

            public /* synthetic */ Object[] newArray(int n) {
                return this.a(n);
            }
        };

        public CursorPaginationTracker() {
            super(CursorModel.a());
        }

        protected CursorPaginationTracker(Parcel parcel) {
            super(CursorModel.a());
            ((CursorModel)this.a).next = parcel.readString();
            ((CursorModel)this.a).hasNext = ParcelUtils.a(parcel);
        }

        public CursorPaginationTracker(CursorModel cursorModel) {
            super(cursorModel);
        }

        public CursorModel a() {
            return (CursorModel)this.a;
        }

        @Override
        public boolean b() {
            return ((CursorModel)this.a).hasNext;
        }

        @Override
        public void c() {
            ((CursorModel)this.a).b();
        }

        @Override
        public /* synthetic */ Object d() {
            return this.a();
        }

        public int describeContents() {
            return 0;
        }

        public String toString() {
            return "CursorPaginationTracker {cursor=" + this.a().next + ", hasNext=" + this.b() + "}";
        }

        public void writeToParcel(Parcel parcel, int n) {
            parcel.writeString(this.a().next);
            ParcelUtils.a(parcel, this.a().hasNext);
        }

    }

    public static interface DataSourceObserver {
        public void a(MagicDataSource var1);

        public void a(MagicDataSource var1, List<Object> var2);

        public void b(MagicDataSource var1);

        public void c(MagicDataSource var1);

        public void d(MagicDataSource var1);
    }

    public static class DataSourceObserverObject
    implements DataSourceObserver {
        @Override
        public void a(MagicDataSource magicDataSource) {
        }

        @Override
        public void a(MagicDataSource magicDataSource, List<Object> list) {
        }

        @Override
        public void b(MagicDataSource magicDataSource) {
        }

        @Override
        public void c(MagicDataSource magicDataSource) {
        }

        @Override
        public void d(MagicDataSource magicDataSource) {
        }
    }

    public static enum DataState {
        a,
        b,
        c,
        d,
        e;
        

        private DataState() {
        }
    }

    public static interface FetchDataCallback<T1, PT1> {
        public void a();

        public void a(List<T1> var1, PT1 var2);
    }

    public class FetchDataCallbackObject
    implements FetchDataCallback<T, PT> {
        private boolean b;

        public FetchDataCallbackObject() {
            this.b = false;
        }

        private void a(List<T> list) {
            this.a(list, false);
        }

        @Override
        public void a() {
            if (this.b) {
                return;
            }
            new Handler(Looper.getMainLooper()).post(new Runnable(){

                @Override
                public void run() {
                    if (FetchDataCallbackObject.this.b) {
                        return;
                    }
                    MagicDataSource.this.m = true;
                    FetchDataCallbackObject.this.a(null);
                }
            });
        }

        @Override
        public void a(final List<T> list, PT PT) {
            if (this.b) {
                return;
            }
            final ArrayList<T> arrayList = MagicDataSource.this.a(list);
            new Handler(Looper.getMainLooper()).post(new Runnable((PaginationTracker)PT){
                final /* synthetic */ PaginationTracker c;
                {
                    this.c = paginationTracker;
                }

                @Override
                public void run() {
                    if (FetchDataCallbackObject.this.b) {
                        return;
                    }
                    if (!MagicDataSource.this.b.get()) {
                        Log.b(MagicDataSource.e, "onDataFetch bailed, load was possibly cancelled");
                        return;
                    }
                    if (MagicDataSource.this.n) {
                        MagicDataSource.this.u();
                    }
                    MagicDataSource.this.g.addAll(list);
                    MagicDataSource.this.h.addAll(arrayList);
                    MagicDataSource.this.j = this.c;
                    FetchDataCallbackObject.this.a(list);
                }
            });
        }

        /*
         * Enabled aggressive block sorting
         */
        @Override
        public void a(List<T> list, boolean bl) {
            ThreadUtils.a();
            if (MagicDataSource.this.g.size() == 0) {
                MagicDataSource magicDataSource = MagicDataSource.this;
                DataState dataState = list != null ? DataState.e : DataState.d;
                magicDataSource.l = dataState;
            } else if (MagicDataSource.this.h.size() == 0) {
                MagicDataSource.this.l = DataState.e;
            } else {
                MagicDataSource.this.l = DataState.b;
            }
            MagicDataSource.this.b.set(false);
            MagicDataSource.this.i = null;
            if (!bl) {
                MagicDataSource.this.b((T)list);
                if (MagicDataSource.this.n) {
                    MagicDataSource.this.s();
                    MagicDataSource.this.n = false;
                }
                MagicDataSource.this.b(true);
            }
        }

        public void b() {
            this.b = true;
        }

    }

    public static class HybridPaginationTracker
    extends PaginationTracker<Object>
    implements Parcelable {
        public static final Parcelable.Creator<HybridPaginationTracker> CREATOR = new Parcelable.Creator<HybridPaginationTracker>(){

            public HybridPaginationTracker a(Parcel parcel) {
                return new HybridPaginationTracker(parcel);
            }

            public HybridPaginationTracker[] a(int n) {
                return new HybridPaginationTracker[n];
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return this.a(parcel);
            }

            public /* synthetic */ Object[] newArray(int n) {
                return this.a(n);
            }
        };
        private PaginationTracker b;

        public HybridPaginationTracker(int n) {
            super(null);
            this.b = new OffsetPaginationTracker(n);
        }

        protected HybridPaginationTracker(Parcel parcel) {
            super(parcel);
            this.b = (PaginationTracker)parcel.readParcelable(PaginationTracker.class.getClassLoader());
        }

        public HybridPaginationTracker(CursorModel cursorModel) {
            super(null);
            this.b = new CursorPaginationTracker(cursorModel);
        }

        @Override
        public boolean b() {
            return this.b.b();
        }

        @Override
        public void c() {
            this.b.c();
        }

        @Override
        public Object d() {
            return this.b.d();
        }

        public int describeContents() {
            return 0;
        }

        public String toString() {
            return "HybridPaginationTracker: " + this.b.toString();
        }

        public void writeToParcel(Parcel parcel, int n) {
            parcel.writeParcelable((Parcelable)this.b, n);
        }

    }

    public static class OffsetPaginationTracker
    extends PaginationTracker<Integer>
    implements Parcelable {
        public static final Parcelable.Creator<OffsetPaginationTracker> CREATOR = new Parcelable.Creator<OffsetPaginationTracker>(){

            public OffsetPaginationTracker a(Parcel parcel) {
                return new OffsetPaginationTracker(parcel);
            }

            public OffsetPaginationTracker[] a(int n) {
                return new OffsetPaginationTracker[n];
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return this.a(parcel);
            }

            public /* synthetic */ Object[] newArray(int n) {
                return this.a(n);
            }
        };

        public OffsetPaginationTracker() {
            super(0);
        }

        protected OffsetPaginationTracker(Parcel parcel) {
            super(parcel.readInt());
        }

        public OffsetPaginationTracker(Integer n) {
            super(n);
        }

        public Integer a() {
            return (Integer)this.a;
        }

        @Override
        public boolean b() {
            if ((Integer)this.a != -1) {
                return true;
            }
            return false;
        }

        @Override
        public void c() {
            this.a = 0;
        }

        @Override
        public /* synthetic */ Object d() {
            return this.a();
        }

        public int describeContents() {
            return 0;
        }

        public String toString() {
            return "OffsetPaginationTracker {offset=" + this.a() + ", hasNext=" + this.b() + "}";
        }

        public void writeToParcel(Parcel parcel, int n) {
            parcel.writeInt(((Integer)this.a).intValue());
        }

    }

    public static abstract class PaginationTracker<S>
    implements Parcelable {
        S a;

        public PaginationTracker(S s) {
            this.a = s;
        }

        public abstract boolean b();

        public abstract void c();

        public abstract S d();
    }

    public static enum PaginationType {
        a,
        b;
        

        private PaginationType() {
        }
    }

}

