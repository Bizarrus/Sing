package com.smule.android.network.managers;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.facebook.share.internal.ShareConstants;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smule.android.logging.Log;
import com.smule.android.network.api.StoreAPI;
import com.smule.android.network.api.StoreAPI$GetSongRequest;
import com.smule.android.network.api.StoreAPI$GetStoreRequest;
import com.smule.android.network.api.StoreAPI$ProductType;
import com.smule.android.network.api.StoreAPI$StreamFinishedRequest;
import com.smule.android.network.api.StoreAPI$StreamType;
import com.smule.android.network.core.CoreUtil;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkResponseCallback;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.models.ContestData$Reward;
import com.smule.android.network.models.ListingV2;
import com.smule.android.network.models.ListingV2$ListingComparatorByDisplayName;
import com.smule.android.network.models.ResourceV2;
import com.smule.android.network.models.SongV2;
import com.smule.android.network.models.SoundfontV2;
import com.smule.android.network.models.StoreSectionV2;
import com.smule.android.network.models.StoreSectionV2$StoreSectionComparatorByOrder;
import com.smule.android.network.models.StoreV2;
import com.smule.android.utils.JsonUtils;
import com.smule.android.utils.NotificationCenter;
import com.smule.android.utils.OperationLoader;
import com.smule.android.utils.OperationLoader.Operation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import twitter4j.HttpResponseCode;

public class StoreManager {
    private static final String f17134b = StoreManager.class.getName();
    private static boolean f17135d = false;
    private static StoreManager f17136e = null;
    public long f17137a;
    private AtomicBoolean f17138c;
    private StoreAPI f17139f;
    private StoreV2 f17140g;
    private Map<String, SongV2> f17141h;
    private Map<String, List<SongV2>> f17142i;
    private Map<String, List<SongV2>> f17143j;
    private Map<String, StoreSectionV2> f17144k;
    private Map<String, ListingV2> f17145l;
    private Map<String, ListingV2> f17146m;
    private Map<String, SoundfontV2> f17147n;
    private Map<String, String> f17148o;
    private Map<String, String> f17149p;
    private StoreSectionV2 f17150q;
    private boolean f17151r;
    private boolean f17152s;
    private boolean f17153t;
    private boolean f17154u;
    private String f17155v;
    private AtomicBoolean f17156w;
    private long f17157x;
    private StoreManagerRequiredMethodsDelegate f17158y;
    private StoreManagerOptionalMethodsDelegate f17159z;

    class C36191 implements Observer {
        final /* synthetic */ StoreManager f17104a;

        C36191(StoreManager storeManager) {
            this.f17104a = storeManager;
        }

        public void update(Observable observable, Object obj) {
            String str = (String) ((Map) obj).get(ShareConstants.ACTION);
            if ("PRODUCTS_LOADED_ACTION".equals(str)) {
                Log.c(StoreManager.f17134b, "Products Downloaded.");
                this.f17104a.m18410r();
            } else if ("ENTITLEMENTS_UPDATED_ACTION".equals(str)) {
                Log.c(StoreManager.f17134b, "Entitlements Updated.");
                this.f17104a.m18410r();
            } else if ("SONGBOOK_SYNCED_ACTION".equals(str)) {
                Log.c(StoreManager.f17134b, "Songbook sync completed");
                this.f17104a.m18410r();
            }
        }
    }

    class C36234 implements Runnable {
        final /* synthetic */ StoreManager f17113a;

        public void run() {
            this.f17113a.f17156w.getAndSet(true);
            this.f17113a.m18405m();
            this.f17113a.f17156w.getAndSet(false);
        }
    }

    class C36277 implements Runnable {
        final /* synthetic */ StoreManager f17123a;

        C36277(StoreManager storeManager) {
            this.f17123a = storeManager;
        }

        public void run() {
            if (this.f17123a.f17159z != null && this.f17123a.f17159z.mo6654b()) {
                this.f17123a.f17150q = this.f17123a.m18412t();
            }
            this.f17123a.f17151r = false;
            this.f17123a.m18436j();
        }
    }

    class C36288 implements Comparator<ListingV2> {
        final /* synthetic */ StoreManager f17124a;

        C36288(StoreManager storeManager) {
            this.f17124a = storeManager;
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m18370a((ListingV2) obj, (ListingV2) obj2);
        }

        public int m18370a(ListingV2 listingV2, ListingV2 listingV22) {
            return listingV2.song.title.compareTo(listingV22.song.title);
        }
    }

    public interface SongResponseCallback {
        void mo6605a(SongV2 songV2);
    }

    public interface StoreManagerOptionalMethodsDelegate {
        boolean mo6653a();

        boolean mo6654b();

        int mo6655c();

        String mo6656d();

        int mo6657e();
    }

    public interface StoreManagerRequiredMethodsDelegate {
        String mo6952a();
    }

    private StoreManager() {
        this.f17138c = new AtomicBoolean(false);
        this.f17141h = new HashMap();
        this.f17142i = new HashMap();
        this.f17143j = new HashMap();
        this.f17144k = new HashMap();
        this.f17145l = new HashMap();
        this.f17146m = new HashMap();
        this.f17147n = new HashMap();
        this.f17148o = new HashMap();
        this.f17149p = new HashMap();
        this.f17151r = false;
        this.f17152s = false;
        this.f17153t = false;
        this.f17154u = false;
        this.f17137a = 3600000;
        this.f17156w = new AtomicBoolean(false);
        this.f17157x = 0;
        this.f17139f = (StoreAPI) MagicNetwork.a().a(StoreAPI.class);
        this.f17140g = new StoreV2();
        this.f17140g.songs = new ArrayList();
        this.f17140g.soundfonts = new ArrayList();
        this.f17140g.listings = new ArrayList();
        this.f17140g.storeSections = new ArrayList();
        m18414v();
    }

    public static synchronized StoreManager m18378a() {
        StoreManager storeManager;
        synchronized (StoreManager.class) {
            if (f17136e == null) {
                Log.b(f17134b, "getInstance() - creating new StoreManager instance");
                f17136e = new StoreManager();
            }
            storeManager = f17136e;
        }
        return storeManager;
    }

    public void m18418a(StoreManagerOptionalMethodsDelegate storeManagerOptionalMethodsDelegate) {
        this.f17159z = storeManagerOptionalMethodsDelegate;
    }

    public void m18424b() {
        NotificationCenter.m19011a().m19014a("SONGBOOK_UPDATED_EVENT", new C36191(this));
    }

    public void m18423a(boolean z, StoreManagerRequiredMethodsDelegate storeManagerRequiredMethodsDelegate, StoreManagerOptionalMethodsDelegate storeManagerOptionalMethodsDelegate) {
        Log.b(f17134b, "initStore started - shallow init is: " + z);
        if (storeManagerRequiredMethodsDelegate == null) {
            throw new RuntimeException("init - StoreManagerRequiredMethodsDelegate cannot be null, as it is REQUIRED");
        }
        this.f17158y = storeManagerRequiredMethodsDelegate;
        if (storeManagerOptionalMethodsDelegate != null) {
            this.f17159z = storeManagerOptionalMethodsDelegate;
        }
        long currentTimeMillis = System.currentTimeMillis();
        Log.b(f17134b, "readProductUidMap() finished at: " + (System.currentTimeMillis() - currentTimeMillis));
        m18408p();
        Log.b(f17134b, "readFirstSyncComplete() finished at: " + (System.currentTimeMillis() - currentTimeMillis));
        if (!(this.f17159z == null || this.f17138c.get())) {
            String d = this.f17159z.mo6656d();
            if (d != null) {
                m18400h(d);
            }
        }
        if (!z) {
            m18425c();
            Log.b(f17134b, "deepInitStore() finished at: " + (System.currentTimeMillis() - currentTimeMillis));
        }
        m18437k();
        Log.b(f17134b, "addBundledContent() finished at: " + (System.currentTimeMillis() - currentTimeMillis));
        Log.b(f17134b, "initStore ended, duration = " + ((long) Math.round(((float) (System.currentTimeMillis() - currentTimeMillis)) / 1000.0f)));
    }

    public void m18419a(OperationLoader operationLoader, boolean z, StoreManagerRequiredMethodsDelegate storeManagerRequiredMethodsDelegate) {
        m18420a(operationLoader, z, storeManagerRequiredMethodsDelegate, null);
    }

    public void m18420a(OperationLoader operationLoader, final boolean z, final StoreManagerRequiredMethodsDelegate storeManagerRequiredMethodsDelegate, final StoreManagerOptionalMethodsDelegate storeManagerOptionalMethodsDelegate) {
        operationLoader.a("StoreManager.loadStore", null, new Operation(this) {
            final /* synthetic */ StoreManager f17110d;

            public void m18369a(final OperationLoader operationLoader) {
                new Thread(new Runnable(this) {
                    final /* synthetic */ C36212 f17106b;

                    public void run() {
                        StoreManager.m18378a().m18423a(z, storeManagerRequiredMethodsDelegate, storeManagerOptionalMethodsDelegate);
                        operationLoader.c(this.f17106b.g);
                    }
                }).start();
            }
        }).a();
    }

    public void m18425c() {
        if (this.f17152s) {
            Log.b(f17134b, "deepInitStore - already in progress; ignoring duplicate call");
        } else if (this.f17153t) {
            Log.d(f17134b, "deepInitStore - already completed; ignoring call");
        } else {
            this.f17152s = true;
            String n = m18406n();
            if (n != null) {
                NetworkResponse networkResponse = new NetworkResponse(n);
                if (networkResponse.b == 0 && networkResponse.l != null) {
                    m18380a(networkResponse.l.get("store"));
                    this.f17154u = true;
                }
            }
            this.f17152s = false;
            m18436j();
        }
    }

    public boolean m18427d() {
        return this.f17153t;
    }

    public boolean m18429e() {
        return this.f17154u;
    }

    private void m18391b(SongV2 songV2) {
        if (!this.f17142i.containsKey(songV2.artist)) {
            this.f17142i.put(songV2.artist, new ArrayList());
        }
        ((List) this.f17142i.get(songV2.artist)).add(songV2);
    }

    private void m18395c(SongV2 songV2) {
        if (!this.f17143j.containsKey(songV2.genre)) {
            this.f17143j.put(songV2.genre, new ArrayList());
        }
        ((List) this.f17143j.get(songV2.genre)).add(songV2);
    }

    public synchronized SongV2 m18416a(String str) {
        Object obj;
        obj = (SongV2) this.f17141h.get(str);
        if (obj == null) {
            SongV2 songV2 = new SongV2();
            songV2.songId = str;
            obj = m18397d(songV2);
            if (obj != null) {
                this.f17141h.put(str, obj);
                m18391b((SongV2) obj);
                m18395c((SongV2) obj);
            }
        }
        return (SongV2) LocalizationManager.a().a("store", obj);
    }

    public static SongV2 m18389b(String str) throws IOException {
        return (SongV2) LocalizationManager.a().a("store", (SongV2) JsonUtils.m18984a().treeToValue((JsonNode) JsonUtils.m18984a().readValue(str, JsonNode.class), SongV2.class));
    }

    public static ListingV2 m18393c(String str) throws IOException {
        return (ListingV2) LocalizationManager.a().a("store", (ListingV2) JsonUtils.m18984a().treeToValue((JsonNode) JsonUtils.m18984a().readValue(str, JsonNode.class), ListingV2.class));
    }

    public synchronized SoundfontV2 m18426d(String str) {
        return (SoundfontV2) LocalizationManager.a().a("store", this.f17147n.get(str));
    }

    public synchronized List<StoreSectionV2> m18431f() {
        List<StoreSectionV2> arrayList;
        arrayList = new ArrayList(this.f17140g.storeSections);
        if (this.f17159z != null && this.f17159z.mo6653a()) {
            arrayList.add(m18413u());
        }
        if (this.f17159z != null && this.f17159z.mo6654b()) {
            if (this.f17150q == null) {
                this.f17150q = m18412t();
            } else {
                m18411s();
            }
            if (this.f17150q.listings.size() > 0) {
                arrayList.add(this.f17150q);
            }
        }
        Collections.sort(arrayList, new StoreSectionV2$StoreSectionComparatorByOrder());
        return arrayList;
    }

    public synchronized ListingV2 m18428e(String str) {
        ListingV2 listingV2;
        if (this.f17145l == null || this.f17145l.size() == 0) {
            Log.e(f17134b, "findListingsByProductUid - mListingsByProduct is empty; was the StoreManager not initialized properly?");
            listingV2 = null;
        } else {
            listingV2 = (ListingV2) this.f17145l.get(str);
            if (listingV2 == null) {
                Log.c(f17134b, "findListingByProductUid - returning null ListingV2 for product id: " + str);
            }
        }
        return listingV2;
    }

    public List<ListingV2> m18433g() {
        HashMap hashMap = new HashMap();
        synchronized (this) {
            hashMap.putAll(this.f17146m);
        }
        return new ArrayList(hashMap.values());
    }

    public boolean m18434h() {
        return this.f17157x + this.f17137a < System.currentTimeMillis();
    }

    public void m18421a(final Runnable runnable) {
        if (m18434h()) {
            MagicNetwork.a(new Runnable(this) {
                final /* synthetic */ StoreManager f17112b;

                public void run() {
                    if (!this.f17112b.m18434h() || this.f17112b.f17156w.getAndSet(true)) {
                        if (runnable != null) {
                            runnable.run();
                        }
                        this.f17112b.f17156w.set(false);
                        return;
                    }
                    this.f17112b.m18405m();
                    this.f17112b.f17156w.set(false);
                    if (runnable != null) {
                        runnable.run();
                    }
                }
            });
        } else if (runnable != null) {
            runnable.run();
        }
    }

    private void m18405m() {
        final long currentTimeMillis = System.currentTimeMillis();
        final NetworkResponse a = NetworkUtils.m18104a(this.f17139f.getStore(m18407o(), new StoreAPI$GetStoreRequest().setStoreId(this.f17158y.mo6952a())));
        Log.b(f17134b, "Fetching store snapshot " + a.b + " Etag " + a.o + " old etag " + this.f17155v);
        if (a.b == HttpResponseCode.NOT_MODIFIED || (this.f17155v != null && this.f17155v.equals(a.o))) {
            this.f17157x = System.currentTimeMillis();
            boolean z = false;
            if (!f17135d) {
                z = true;
            }
            m18387a(z, null, true, currentTimeMillis);
        } else if (!a.c() || a.l == null) {
            Log.d(f17134b, "Store snapshot update failed");
        } else {
            this.f17157x = System.currentTimeMillis();
            Log.c(f17134b, "new snapshot available, beginning sync.");
            LocalizationManager.a().a("store", new Runnable(this) {
                final /* synthetic */ StoreManager f17116c;

                public void run() {
                    this.f17116c.m18380a(a.l.get("store"));
                    this.f17116c.m18400h(a.j);
                    this.f17116c.m18387a(true, a.o, true, currentTimeMillis);
                }
            });
        }
    }

    private void m18387a(boolean z, String str, boolean z2, long j) {
        if (z2) {
            m18409q();
            if (str != null) {
                m18402j(str);
            }
            if (z) {
                NotificationCenter.m19011a().m19017b("SONGBOOK_UPDATED_EVENT", ShareConstants.ACTION, "SONGBOOK_SYNCED_ACTION");
                f17135d = true;
            }
        } else if (!this.f17138c.get()) {
            MagicNetwork.d().showConnectionError();
        }
        Log.c(f17134b, "snapshot finished after " + Math.round(((float) (System.currentTimeMillis() - j)) / 1000.0f) + " seconds, success=" + Boolean.valueOf(z2));
    }

    private SongV2 m18397d(SongV2 songV2) {
        String i = m18401i(songV2.songId);
        if (i == null) {
            return null;
        }
        Log.b(f17134b, "Restoring song " + songV2.songId + " from saved json");
        songV2.a((SongV2) JsonUtils.m18985a(new NetworkResponse(i).l.get("song"), SongV2.class));
        return songV2;
    }

    public Future<?> m18417a(final String str, final SongResponseCallback songResponseCallback) {
        Handler handler;
        if (Looper.myLooper() != null) {
            handler = new Handler(Looper.myLooper());
        } else {
            handler = null;
        }
        return MagicNetwork.a(new Runnable(this) {
            final /* synthetic */ StoreManager f17122d;

            public void run() {
                final SongV2 f = this.f17122d.m18430f(str);
                if (songResponseCallback != null) {
                    if (handler != null) {
                        handler.post(new Runnable(this) {
                            final /* synthetic */ C36266 f17118b;

                            public void run() {
                                songResponseCallback.mo6605a(f);
                            }
                        });
                    } else {
                        songResponseCallback.mo6605a(f);
                    }
                }
            }
        });
    }

    public SongV2 m18430f(String str) {
        SongV2 a = m18416a(str);
        if (a != null) {
            return m18415a(a);
        }
        if (!EntitlementsManager.m18163a().m18184c(str)) {
            return null;
        }
        a = new SongV2();
        a.songId = str;
        a.eTag = "NULL";
        return m18415a(a);
    }

    public SongV2 m18415a(SongV2 songV2) {
        Object obj = null;
        if (songV2.eTag == null || songV2.eTag.length() == 0) {
            m18397d(songV2);
        }
        NetworkResponse a = NetworkUtils.m18104a(this.f17139f.getSong(m18403k(songV2.songId), new StoreAPI$GetSongRequest().setSongId(songV2.songId)));
        if (a.b == HttpResponseCode.NOT_MODIFIED) {
            obj = songV2;
        } else if (a.b != 0) {
            MagicNetwork.a(a);
        }
        JsonNode f = a.f();
        if (f != null) {
            obj = (SongV2) JsonUtils.m18985a(f.get("song"), SongV2.class);
            obj.totalPlayCount = ((Integer) JsonUtils.m18985a(f.get("totalPlays"), Integer.class)).intValue();
            Set hashSet = new HashSet();
            Iterator it = obj.resources.iterator();
            while (it.hasNext()) {
                ResourceV2 resourceV2 = (ResourceV2) it.next();
                if (hashSet.contains(resourceV2.uid)) {
                    it.remove();
                } else {
                    hashSet.add(resourceV2.uid);
                }
            }
            SongV2 a2 = m18416a(obj.songId);
            if (a2 != null) {
                a2.a(obj);
                obj = a2;
            } else {
                Log.e(f17134b, "No existing product found for the song download " + songV2.songId);
            }
            m18392b(songV2.songId, a.o);
            m18385a(songV2.songId, a.j);
        }
        return (SongV2) LocalizationManager.a().a("store", obj);
    }

    public String m18435i() {
        return this.f17155v;
    }

    private synchronized void m18380a(JsonNode jsonNode) {
        int i = 0;
        synchronized (this) {
            int max;
            int priority = Thread.currentThread().getPriority();
            Thread.currentThread().setPriority(1);
            this.f17140g = (StoreV2) JsonUtils.m18985a(jsonNode, StoreV2.class);
            this.f17141h.clear();
            this.f17142i.clear();
            this.f17143j.clear();
            for (SongV2 songV2 : this.f17140g.songs) {
                this.f17141h.put(songV2.songId, songV2);
                m18391b(songV2);
                m18395c(songV2);
            }
            this.f17147n.clear();
            for (SoundfontV2 soundfontV2 : this.f17140g.soundfonts) {
                this.f17147n.put(soundfontV2.soundfontId, soundfontV2);
            }
            this.f17145l.clear();
            this.f17146m.clear();
            for (ListingV2 listingV2 : this.f17140g.listings) {
                listingV2.isFree = listingV2.price == 0;
                if (listingV2.productType.equals(ContestData$Reward.TYPE_SONG)) {
                    listingV2.song = m18416a(listingV2.productId);
                } else if (listingV2.productType.equals("SOUNDFONT")) {
                    listingV2.soundfont = m18426d(listingV2.productId);
                }
                this.f17146m.put(listingV2.listingId, listingV2);
                if (!this.f17145l.containsKey(listingV2.productId)) {
                    this.f17145l.put(listingV2.productId, listingV2);
                } else if (((ListingV2) this.f17145l.get(listingV2.productId)).modes.isEmpty()) {
                    Log.b(f17134b, "Filtering listing " + ((ListingV2) this.f17145l.get(listingV2.productId)).listingId + " in classic mode in favor of join mode listing " + listingV2.listingId);
                    this.f17146m.remove(((ListingV2) this.f17145l.get(listingV2.productId)).listingId);
                    this.f17145l.put(listingV2.productId, listingV2);
                } else {
                    Log.b(f17134b, "Filtering listing " + listingV2.listingId + " in classic mode in favor of join mode listing " + ((ListingV2) this.f17145l.get(listingV2.productId)).listingId);
                    this.f17146m.remove(listingV2.listingId);
                }
            }
            this.f17144k.clear();
            if (this.f17159z != null) {
                max = Math.max(this.f17159z.mo6657e(), 1);
            } else {
                max = 1;
            }
            this.f17140g.storeSections = new ArrayList();
            Iterator it = jsonNode.get("storeSections").iterator();
            while (it.hasNext()) {
                StoreSectionV2 storeSectionV2;
                JsonNode jsonNode2 = (JsonNode) it.next();
                StoreSectionV2 storeSectionV22 = (StoreSectionV2) JsonUtils.m18985a(jsonNode2.get("section"), StoreSectionV2.class);
                storeSectionV22.order = i;
                int i2 = i + max;
                storeSectionV22.listings = new ArrayList(storeSectionV22.listingIds.size());
                for (String str : storeSectionV22.listingIds) {
                    if (this.f17146m.containsKey(str)) {
                        storeSectionV22.listings.add(this.f17146m.get(str));
                    }
                }
                this.f17144k.put(storeSectionV22.sectionId, storeSectionV22);
                jsonNode2 = jsonNode2.get("parentSectionId");
                String str2 = jsonNode2 != null ? jsonNode2.asText() : null;
                if (str2 != null) {
                    storeSectionV2 = (StoreSectionV2) this.f17144k.get(str2);
                } else {
                    storeSectionV2 = null;
                }
                if (storeSectionV2 == null) {
                    this.f17140g.storeSections.add(storeSectionV22);
                    Log.b(f17134b, "Section added " + storeSectionV22.sectionId + " listings " + storeSectionV22.listings.size());
                } else {
                    storeSectionV2.subSections.add(storeSectionV22);
                    Log.b(f17134b, str2 + ": Subsection added " + storeSectionV22.sectionId + " listings " + storeSectionV22.listings.size());
                }
                i = i2;
            }
            this.f17153t = true;
            Thread.currentThread().setPriority(priority);
        }
    }

    private String m18406n() {
        return MagicNetwork.d().getApplicationContext().getSharedPreferences("STORE_SETTINGS", 0).getString("store_response", null);
    }

    private void m18400h(String str) {
        if (str == null) {
            Log.b(f17134b, "saveStore - called with null responseBody");
        } else {
            MagicNetwork.d().getApplicationContext().getSharedPreferences("STORE_SETTINGS", 0).edit().putString("store_response", str).apply();
        }
    }

    private String m18401i(String str) {
        return MagicNetwork.d().getApplicationContext().getSharedPreferences("STORE_SETTINGS", 0).getString("song_response_" + str, null);
    }

    private void m18385a(String str, String str2) {
        MagicNetwork.d().getApplicationContext().getSharedPreferences("STORE_SETTINGS", 0).edit().putString("song_response_" + str, str2).apply();
    }

    private String m18407o() {
        this.f17155v = MagicNetwork.d().getApplicationContext().getSharedPreferences("STORE_SETTINGS", 0).getString("store_signature", null);
        return this.f17155v;
    }

    private void m18402j(String str) {
        MagicNetwork.d().getApplicationContext().getSharedPreferences("STORE_SETTINGS", 0).edit().putString("store_signature", str).apply();
        this.f17155v = str;
    }

    private String m18403k(String str) {
        return MagicNetwork.d().getApplicationContext().getSharedPreferences("STORE_SETTINGS", 0).getString("song_signature_" + str, null);
    }

    private void m18392b(String str, String str2) {
        MagicNetwork.d().getApplicationContext().getSharedPreferences("STORE_SETTINGS", 0).edit().putString("song_signature_" + str, str2).apply();
    }

    private void m18408p() {
        this.f17138c.set(MagicNetwork.d().getApplicationContext().getSharedPreferences("STORE_SETTINGS", 0).getBoolean("STORE_SETTINGS", false));
    }

    private void m18409q() {
        MagicNetwork.d().getApplicationContext().getSharedPreferences("STORE_SETTINGS", 0).edit().putBoolean("STORE_SETTINGS", true).apply();
        this.f17138c.set(true);
    }

    private void m18410r() {
        if (!this.f17151r) {
            this.f17151r = true;
            new Thread(new C36277(this)).start();
        }
    }

    private void m18411s() {
        m18386a(EntitlementsManager.m18163a().m18181b());
    }

    private void m18386a(Set<String> set) {
        Set<String> hashSet = new HashSet();
        hashSet.addAll(set);
        if (hashSet == null || hashSet.size() == 0) {
            Log.b(f17134b, "refreshOwnedSongsSection - getMySongsUids returned no UIDs; aborting refresh of owned songs section");
        }
        List arrayList = new ArrayList();
        for (String str : hashSet) {
            SongV2 a = m18416a(str);
            if (a != null) {
                ListingV2 e = m18428e(a.songId);
                if (e == null) {
                    Log.e(f17134b, "Couldn't find listings for entitlement, creating it: " + str);
                    arrayList.add(new ListingV2(a));
                } else {
                    arrayList.add(e);
                }
                Log.c(f17134b, "refreshOwnedSongsSection - adding " + a.title);
            } else {
                Log.e(f17134b, "Couldn't find song for entitlement!" + str);
            }
        }
        this.f17150q.listings = arrayList;
        if (this.f17150q.listings != null && this.f17150q.listings.size() > 0) {
            Collections.sort(this.f17150q.listings, new ListingV2$ListingComparatorByDisplayName());
            this.f17150q.listingIds = ListingV2.a(this.f17150q.listings);
        }
    }

    private StoreSectionV2 m18412t() {
        Log.c(f17134b, "create mySongs section");
        Set b = EntitlementsManager.m18163a().m18181b();
        if (b == null || b.size() == 0) {
            Log.b(f17134b, "createOwnedSongsSection - getMySongsUids returned no UIDs; aborting creation of owned songs section");
        }
        Context applicationContext = MagicNetwork.d().getApplicationContext();
        StoreSectionV2 storeSectionV2 = new StoreSectionV2();
        int identifier = applicationContext.getResources().getIdentifier("my_songs", "string", applicationContext.getPackageName());
        if (identifier == 0) {
            storeSectionV2.displayName = "My Songs";
        } else {
            storeSectionV2.displayName = applicationContext.getString(identifier);
        }
        if (this.f17159z != null) {
            storeSectionV2.order = this.f17159z.mo6655c();
        } else {
            storeSectionV2.order = -1;
        }
        storeSectionV2.sectionId = "my_songs";
        this.f17150q = storeSectionV2;
        m18386a(b);
        return storeSectionV2;
    }

    private StoreSectionV2 m18413u() {
        Log.b(f17134b, "mySongs begin");
        StoreSectionV2 storeSectionV2 = new StoreSectionV2();
        if (this.f17150q == null) {
            Log.e(f17134b, "Unexpected mOwnedSongs is null, creating it");
            this.f17150q = m18412t();
        } else {
            m18411s();
        }
        storeSectionV2.displayName = this.f17150q.displayName;
        storeSectionV2.listings = new ArrayList(this.f17150q.listings);
        storeSectionV2.order = this.f17150q.order;
        storeSectionV2.sectionId = this.f17150q.sectionId;
        for (String str : MagicNetwork.d().getProgressedSongsUids()) {
            if (!EntitlementsManager.m18163a().m18184c(str)) {
                SongV2 a = m18416a(str);
                if (a != null) {
                    ListingV2 e = m18428e(str);
                    if (e == null) {
                        storeSectionV2.listings.add(new ListingV2(a));
                    } else {
                        storeSectionV2.listings.add(e);
                    }
                } else {
                    Log.e(f17134b, "Failed to find product for progressed song " + str);
                }
            }
        }
        Collections.sort(storeSectionV2.listings, new C36288(this));
        Log.a(f17134b, "createMySongsSection() done; size = " + storeSectionV2.listings.size());
        return storeSectionV2;
    }

    public void m18436j() {
        NotificationCenter.m19011a().m19017b("SONGBOOK_UPDATED_EVENT", ShareConstants.ACTION, "PRODUCTS_SORTED_ACTION");
    }

    public synchronized boolean m18437k() {
        boolean z;
        try {
            for (String str : MagicNetwork.d().getBundledContent()) {
                if (str != null) {
                    try {
                        JsonNode jsonNode = ((JsonNode) JsonUtils.m18984a().readValue(str, JsonNode.class)).get(ShareConstants.WEB_DIALOG_PARAM_DATA);
                        if (jsonNode != null) {
                            jsonNode = jsonNode.get("song");
                            if (jsonNode != null) {
                                SongV2 b = m18389b(jsonNode.toString());
                                SongV2 a = m18416a(b.songId);
                                if (a == null || a.resources.size() == 0) {
                                    m18385a(b.songId, str);
                                    this.f17141h.put(b.songId, b);
                                    this.f17140g.songs.add(b);
                                }
                            }
                        }
                    } catch (Throwable e) {
                        android.util.Log.e(f17134b, "Error parsing json response from bundled content: " + str, e);
                    }
                }
            }
            for (String str2 : MagicNetwork.d().getBundledListings()) {
                ListingV2 c = m18393c(str2);
                if (((ListingV2) this.f17146m.get(c.listingId)) == null) {
                    c.isFree = c.price == 0;
                    c.song = m18416a(c.productId);
                    this.f17146m.put(c.listingId, c);
                    this.f17145l.put(c.productId, c);
                }
            }
            z = true;
        } catch (Throwable e2) {
            Log.d(f17134b, "Error parsing bundled content! " + e2, e2);
            z = false;
        }
        return z;
    }

    private void m18414v() {
        ObjectMapper a = JsonUtils.m18984a();
        this.f17149p = new HashMap();
        this.f17148o = new HashMap();
        try {
            Context applicationContext = MagicNetwork.d().getApplicationContext();
            Iterator fields = a.readTree(applicationContext.getResources().openRawResource(applicationContext.getResources().getIdentifier("product_uid_map", "raw", applicationContext.getPackageName()))).fields();
            while (fields.hasNext()) {
                Entry entry = (Entry) fields.next();
                String str = (String) entry.getKey();
                String textValue = ((JsonNode) entry.getValue()).textValue();
                this.f17148o.put(str, textValue);
                this.f17149p.put(textValue, str);
            }
        } catch (Throwable e) {
            Log.d(f17134b, "Failed to read product_uid_map.json", e);
        }
    }

    public String m18432g(String str) {
        String str2 = (String) this.f17148o.get(str);
        return str2 == null ? str : str2;
    }

    public void m18422a(String str, String str2, String str3, Integer num, StoreAPI$StreamType storeAPI$StreamType, StoreAPI$ProductType storeAPI$ProductType, NetworkResponseCallback networkResponseCallback, String str4) {
        final NetworkResponseCallback networkResponseCallback2 = networkResponseCallback;
        final String str5 = str;
        final StoreAPI$ProductType storeAPI$ProductType2 = storeAPI$ProductType;
        final String str6 = str2;
        final StoreAPI$StreamType storeAPI$StreamType2 = storeAPI$StreamType;
        final String str7 = str3;
        final Integer num2 = num;
        final String str8 = str4;
        MagicNetwork.a(new Runnable(this) {
            final /* synthetic */ StoreManager f17133i;

            public void run() {
                CoreUtil.m18079a(networkResponseCallback2, NetworkUtils.m18104a(this.f17133i.f17139f.streamFinished(new StoreAPI$StreamFinishedRequest().setProductId(str5).setProductType(storeAPI$ProductType2).setSongId(str6).setStreamTypeAndCurrency(storeAPI$StreamType2, str7, num2).setSeedPerformanceKey(str8))));
            }
        });
    }
}
