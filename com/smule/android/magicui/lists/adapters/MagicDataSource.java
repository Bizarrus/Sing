package com.smule.android.magicui.lists.adapters;

import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.support.v4.util.LruCache;
import com.smule.android.logging.Log;
import com.smule.android.utils.ThreadUtils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class MagicDataSource<T> {
    private static final long f15865a = TimeUnit.MINUTES.toSeconds(5);
    protected static final LruCache<String, CacheItem> f15866d = new LruCache(50);
    private final String f15867b;
    private final AtomicBoolean f15868c;
    protected String f15869e;
    protected ArrayList<T> f15870f;
    protected ArrayList<T> f15871g;
    protected FetchDataCallbackObject f15872h;
    protected volatile int f15873i;
    WeakReference<DataSourceObserver> f15874j;
    private volatile DataState f15875k;
    private volatile boolean f15876l;
    private volatile boolean f15877m;

    public interface DataSourceObserver {
        void mo6251a(MagicDataSource magicDataSource);

        void mo6252a(MagicDataSource magicDataSource, List<Object> list);

        void mo6253b(MagicDataSource magicDataSource);

        void mo6254c(MagicDataSource magicDataSource);

        void mo6255d(MagicDataSource magicDataSource);
    }

    public static class DataSourceObserverObject implements DataSourceObserver {
        public void mo6254c(MagicDataSource magicDataSource) {
        }

        public void mo6255d(MagicDataSource magicDataSource) {
        }

        public void mo6252a(MagicDataSource magicDataSource, List<Object> list) {
        }

        public void mo6251a(MagicDataSource magicDataSource) {
        }

        public void mo6253b(MagicDataSource magicDataSource) {
        }
    }

    class C35371 implements Runnable {
        final /* synthetic */ MagicDataSource f16425a;

        C35371(MagicDataSource magicDataSource) {
            this.f16425a = magicDataSource;
        }

        public void run() {
            this.f16425a.m17647a(false);
        }
    }

    protected static class CacheItem {
        protected String f16426a;
        protected long f16427b;
        protected long f16428c;
        public Parcel f16429d = Parcel.obtain();

        protected CacheItem(String str, long j, long j2) {
            this.f16426a = str;
            this.f16427b = j;
            this.f16428c = j2;
        }
    }

    public enum DataState {
        NONE,
        HAS_DATA,
        LOADING_FIRST_PAGE,
        LOADING_FIRST_PAGE_FAILED,
        FIRST_PAGE_EMPTY
    }

    public interface FetchDataCallback<T1> {
        void mo6256a();

        void mo6257a(List<T1> list, int i);
    }

    public class FetchDataCallbackObject implements FetchDataCallback<T> {
        final /* synthetic */ MagicDataSource f16441a;
        private boolean f16442b = false;

        class C35392 implements Runnable {
            final /* synthetic */ FetchDataCallbackObject f16440a;

            C35392(FetchDataCallbackObject fetchDataCallbackObject) {
                this.f16440a = fetchDataCallbackObject;
            }

            public void run() {
                if (!this.f16440a.f16442b) {
                    this.f16440a.f16441a.f15876l = true;
                    this.f16440a.m18063a(null);
                }
            }
        }

        public FetchDataCallbackObject(MagicDataSource magicDataSource) {
            this.f16441a = magicDataSource;
        }

        public void m18068b() {
            this.f16442b = true;
        }

        public void mo6257a(final List<T> list, final int i) {
            if (!this.f16442b) {
                final List a = this.f16441a.mo6791a((List) list);
                new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                    final /* synthetic */ FetchDataCallbackObject f16439d;

                    public void run() {
                        if (!this.f16439d.f16442b) {
                            if (this.f16439d.f16441a.f15868c.get()) {
                                if (this.f16439d.f16441a.f15877m) {
                                    this.f16439d.f16441a.m17670t();
                                }
                                this.f16439d.f16441a.f15870f.addAll(list);
                                this.f16439d.f16441a.f15871g.addAll(a);
                                this.f16439d.f16441a.f15873i = i;
                                this.f16439d.m18063a(list);
                                return;
                            }
                            Log.b(this.f16439d.f16441a.f15867b, "onDataFetch bailed, load was possibly cancelled");
                        }
                    }
                });
            }
        }

        public void mo6256a() {
            if (!this.f16442b) {
                new Handler(Looper.getMainLooper()).post(new C35392(this));
            }
        }

        private void m18063a(List<T> list) {
            m18067a((List) list, false);
        }

        public void m18067a(List<T> list, boolean z) {
            ThreadUtils.m19057a();
            if (this.f16441a.f15870f.size() == 0) {
                this.f16441a.f15875k = list != null ? DataState.FIRST_PAGE_EMPTY : DataState.LOADING_FIRST_PAGE_FAILED;
            } else if (this.f16441a.f15871g.size() == 0) {
                this.f16441a.f15875k = DataState.FIRST_PAGE_EMPTY;
            } else {
                this.f16441a.f15875k = DataState.HAS_DATA;
            }
            this.f16441a.f15868c.set(false);
            this.f16441a.f15872h = null;
            if (!z) {
                this.f16441a.mo6792b((List) list);
                if (this.f16441a.f15877m) {
                    this.f16441a.m17668r();
                    this.f16441a.f15877m = false;
                }
                this.f16441a.m17647a(true);
            }
        }
    }

    public abstract Future<?> mo6243a(int i, int i2, FetchDataCallback<T> fetchDataCallback);

    public static void m17632a(String str) {
        if (f15866d != null) {
            f15866d.remove(str);
        }
    }

    public MagicDataSource() {
        this(null);
    }

    public MagicDataSource(String str) {
        this.f15867b = MagicDataSource.class.getName();
        this.f15870f = new ArrayList();
        this.f15871g = new ArrayList();
        this.f15868c = new AtomicBoolean(false);
        this.f15875k = DataState.NONE;
        this.f15873i = 0;
        this.f15876l = false;
        m17653b(str);
        mo6265e();
    }

    public void m17653b(String str) {
        this.f15869e = str;
    }

    protected boolean mo6265e() {
        if (this.f15869e == null) {
            return false;
        }
        CacheItem cacheItem = (CacheItem) f15866d.get(this.f15869e);
        if (cacheItem == null) {
            return false;
        }
        boolean z;
        synchronized (this.f15869e) {
            if (m17648a(cacheItem)) {
                f15866d.remove(this.f15869e);
                z = false;
            } else {
                cacheItem.f16429d.setDataPosition(0);
                mo6685b(cacheItem.f16429d);
                this.f15871g = mo6791a(this.f15870f);
                z = true;
            }
        }
        if (!z) {
            return z;
        }
        this.f15872h = new FetchDataCallbackObject(this);
        this.f15872h.m18067a(this.f15870f, true);
        return z;
    }

    protected long mo6245c() {
        return f15865a;
    }

    protected boolean m17648a(CacheItem cacheItem) {
        return System.currentTimeMillis() - cacheItem.f16427b > mo6245c() * 1000;
    }

    protected synchronized void mo6266f() {
        if (!(this.f15869e == null || mo6245c() == 0)) {
            CacheItem cacheItem = (CacheItem) f15866d.get(this.f15869e);
            if (cacheItem == null) {
                cacheItem = new CacheItem(this.f15869e, System.currentTimeMillis(), mo6245c() * 1000);
                f15866d.put(this.f15869e, cacheItem);
            }
            cacheItem.f16429d.setDataPosition(0);
            mo6684a(cacheItem.f16429d);
            m17658h();
        }
    }

    protected void mo6684a(Parcel parcel) {
        parcel.writeInt(this.f15870f.size());
        if (this.f15870f.size() > 0) {
            parcel.writeSerializable(this.f15870f.get(0).getClass());
            parcel.writeList(this.f15870f);
        }
        parcel.writeInt(this.f15873i);
    }

    protected void mo6685b(Parcel parcel) {
        int readInt = parcel.readInt();
        if (readInt == 0) {
            this.f15870f.clear();
        } else {
            Class cls = (Class) parcel.readSerializable();
            this.f15870f = new ArrayList(readInt);
            parcel.readList(this.f15870f, cls.getClassLoader());
        }
        this.f15873i = parcel.readInt();
    }

    protected void mo6927g() {
        if (this.f15869e != null) {
            f15866d.remove(this.f15869e);
        }
    }

    protected void m17658h() {
        for (CacheItem cacheItem : f15866d.snapshot().values()) {
            if (System.currentTimeMillis() - cacheItem.f16427b > cacheItem.f16428c) {
                f15866d.remove(cacheItem.f16426a);
            }
        }
    }

    public int mo6242a() {
        return 10;
    }

    public int l_() {
        return 2;
    }

    public DataState m17659i() {
        return this.f15875k;
    }

    public ArrayList<T> mo6791a(List<T> list) {
        return new ArrayList(list);
    }

    public synchronized void m17660j() {
        ThreadUtils.m19057a();
        if (this.f15868c.get()) {
            this.f15872h.m18068b();
            this.f15872h = null;
            this.f15868c.set(false);
        }
    }

    public final int m17661k() {
        return this.f15871g != null ? this.f15871g.size() : 0;
    }

    public final boolean m17662l() {
        return (this.f15873i == -1 || this.f15876l) ? false : true;
    }

    public final void m17663m() {
        this.f15876l = false;
    }

    protected final void m17664n() {
        m17660j();
        this.f15877m = true;
        mo6690q();
        m17636b(true);
    }

    public final void m17665o() {
        m17636b(false);
    }

    private synchronized void m17636b(boolean z) {
        int i = 0;
        synchronized (this) {
            ThreadUtils.m19057a();
            if ((z || m17662l()) && this.f15868c.compareAndSet(false, true)) {
                if ((!this.f15877m && this.f15870f.size() == 0) || this.f15875k == DataState.LOADING_FIRST_PAGE_FAILED) {
                    this.f15875k = DataState.LOADING_FIRST_PAGE;
                    new Handler(Looper.getMainLooper()).post(new C35371(this));
                }
                this.f15872h = new FetchDataCallbackObject(this);
                mo6264d();
                if (!z) {
                    i = this.f15873i;
                }
                mo6243a(i, mo6242a(), this.f15872h);
            }
        }
    }

    public final T m17641a(int i) {
        return this.f15871g.get(i);
    }

    public boolean mo6688a(Object obj) {
        boolean remove = this.f15871g.remove(obj);
        this.f15870f.remove(obj);
        if (this.f15871g.isEmpty()) {
            this.f15875k = DataState.FIRST_PAGE_EMPTY;
        }
        return remove;
    }

    public void m17652b(T t) {
        int indexOf = this.f15870f.indexOf(t);
        if (indexOf != -1) {
            this.f15870f.set(indexOf, t);
        }
        indexOf = this.f15871g.indexOf(t);
        if (indexOf != -1) {
            this.f15871g.set(indexOf, t);
        }
    }

    public void mo6687a(int i, T t) {
        this.f15870f.add(i, t);
        this.f15871g = mo6791a(this.f15870f);
        if (!this.f15871g.isEmpty()) {
            this.f15875k = DataState.HAS_DATA;
        }
    }

    final void m17650b(int i) {
        if (((m17661k() - i) - 1) - l_() <= 0 && !this.f15876l) {
            m17665o();
        }
    }

    public void m17646a(DataSourceObserver dataSourceObserver) {
        if (dataSourceObserver == null) {
            this.f15874j = null;
            return;
        }
        this.f15874j = new WeakReference(dataSourceObserver);
        m17663m();
    }

    public void m17666p() {
        m17647a(true);
    }

    public void m17647a(boolean z) {
        Log.b(this.f15867b, "notifyDataSetChanged");
        if (z) {
            mo6266f();
        }
        if (this.f15874j != null && this.f15874j.get() != null) {
            ((DataSourceObserver) this.f15874j.get()).mo6254c(this);
        }
    }

    private void mo6264d() {
        Log.b(this.f15867b, "notifyFetchDataStarted");
        if (this.f15874j != null && this.f15874j.get() != null) {
            ((DataSourceObserver) this.f15874j.get()).mo6255d(this);
        }
    }

    private void mo6792b(List<T> list) {
        Log.b(this.f15867b, "notifyFetchDataFinished " + list);
        if (this.f15874j != null && this.f15874j.get() != null) {
            ((DataSourceObserver) this.f15874j.get()).mo6252a(this, list);
        }
    }

    public void mo6690q() {
        Log.b(this.f15867b, "notifyRefreshStarted");
        if (this.f15874j != null && this.f15874j.get() != null) {
            ((DataSourceObserver) this.f15874j.get()).mo6251a(this);
        }
    }

    public void m17668r() {
        Log.b(this.f15867b, "notifyRefreshFinished");
        if (this.f15874j != null && this.f15874j.get() != null) {
            ((DataSourceObserver) this.f15874j.get()).mo6253b(this);
        }
    }

    public boolean m17669s() {
        return this.f15877m;
    }

    public void m17670t() {
        mo6927g();
        this.f15870f.clear();
        this.f15871g.clear();
    }

    public boolean m17671u() {
        return this.f15868c.get();
    }
}
