/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.content.res.Resources
 *  android.util.Log
 *  com.fasterxml.jackson.core.TreeNode
 *  com.fasterxml.jackson.databind.JsonNode
 *  retrofit2.Call
 */
package com.smule.android.network.managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.JsonNode;
import com.smule.android.logging.Log;
import com.smule.android.network.api.StoreAPI;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkResponseCallback;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.SnpRequest;
import com.smule.android.network.managers.EntitlementsManager;
import com.smule.android.network.managers.LocalizationManager;
import com.smule.android.network.managers.StoreManager;
import com.smule.android.network.models.ListingV2;
import com.smule.android.network.models.ResourceV2;
import com.smule.android.network.models.SongV2;
import com.smule.android.network.models.SoundfontV2;
import com.smule.android.network.models.StoreSectionV2;
import com.smule.android.network.models.StoreV2;
import com.smule.android.utils.JsonUtils;
import com.smule.android.utils.NotificationCenter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import retrofit2.Call;

@Deprecated
public class StoreManager {
    private static final String b = StoreManager.class.getName();
    private static boolean d = false;
    private static StoreManager e = null;
    public long a = 3600000;
    private AtomicBoolean c = new AtomicBoolean(false);
    private com.smule.android.network.api.StoreAPI f = MagicNetwork.a().a(com.smule.android.network.api.StoreAPI.class);
    private StoreV2 g = new StoreV2();
    private Map<String, SongV2> h = new HashMap<String, SongV2>();
    private Map<String, List<SongV2>> i = new HashMap<String, List<SongV2>>();
    private Map<String, List<SongV2>> j = new HashMap<String, List<SongV2>>();
    private Map<String, StoreSectionV2> k = new HashMap<String, StoreSectionV2>();
    private Map<String, com.smule.android.network.models.ListingV2> l = new HashMap<String, com.smule.android.network.models.ListingV2>();
    private Map<String, com.smule.android.network.models.ListingV2> m = new HashMap<String, com.smule.android.network.models.ListingV2>();
    private Map<String, SoundfontV2> n = new HashMap<String, SoundfontV2>();
    private Map<String, String> o = new HashMap<String, String>();
    private Map<String, String> p = new HashMap<String, String>();
    private StoreSectionV2 q;
    private boolean r = false;
    private boolean s = false;
    private boolean t = false;
    private boolean u = false;
    private String v;
    private AtomicBoolean w = new AtomicBoolean(false);
    private long x = 0;
    private  y;
    private  z;

    private StoreManager() {
        this.g.songs = new ArrayList<SongV2>();
        this.g.soundfonts = new ArrayList<SoundfontV2>();
        this.g.listings = new ArrayList<com.smule.android.network.models.ListingV2>();
        this.g.storeSections = new ArrayList<StoreSectionV2>();
        this.o();
    }

    public static StoreManager a() {
        synchronized (StoreManager.class) {
            if (e == null) {
                Log.b(b, "getInstance() - creating new StoreManager instance");
                e = new StoreManager();
            }
            StoreManager storeManager = e;
            return storeManager;
        }
    }

    static /* synthetic */ StoreSectionV2 a(StoreManager storeManager, StoreSectionV2 storeSectionV2) {
        storeManager.q = storeSectionV2;
        return storeSectionV2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void a(JsonNode object) {
        int n = 0;
        synchronized (this) {
            Object object22;
            int n2 = Thread.currentThread().getPriority();
            Thread.currentThread().setPriority(1);
            this.g = JsonUtils.a((JsonNode)object, StoreV2.class);
            this.h.clear();
            this.i.clear();
            this.j.clear();
            for (Object object22 : this.g.songs) {
                this.h.put(object22.songId, (SongV2)object22);
                this.b((SongV2)object22);
                this.c((SongV2)object22);
            }
            this.n.clear();
            for (Object object22 : this.g.soundfonts) {
                this.n.put(object22.soundfontId, (SoundfontV2)((Object)((SongV2)object22)));
            }
            this.l.clear();
            this.m.clear();
            for (Object object22 : this.g.listings) {
                boolean bl = object22.price == 0;
                object22.isFree = bl;
                if (object22.productType.equals("SONG")) {
                    object22.song = this.a(object22.productId);
                } else if (object22.productType.equals("SOUNDFONT")) {
                    object22.soundfont = this.d(object22.productId);
                }
                this.m.put(object22.listingId, (com.smule.android.network.models.ListingV2)object22);
                if (!this.l.containsKey(object22.productId)) {
                    this.l.put(object22.productId, (com.smule.android.network.models.ListingV2)object22);
                    continue;
                }
                if (this.l.get((Object)object22.productId).modes.isEmpty()) {
                    Log.b(b, "Filtering listing " + this.l.get((Object)object22.productId).listingId + " in classic mode in favor of join mode listing " + object22.listingId);
                    this.m.remove(this.l.get((Object)object22.productId).listingId);
                    this.l.put(object22.productId, (com.smule.android.network.models.ListingV2)object22);
                    continue;
                }
                Log.b(b, "Filtering listing " + object22.listingId + " in classic mode in favor of join mode listing " + this.l.get((Object)object22.productId).listingId);
                this.m.remove(object22.listingId);
            }
            this.k.clear();
            int n3 = this.z != null ? Math.max(this.z.d(), 1) : 1;
            this.g.storeSections = new ArrayList<StoreSectionV2>();
            object22 = object.get("storeSections").iterator();
            do {
                if (!object22.hasNext()) {
                    this.t = true;
                    Thread.currentThread().setPriority(n2);
                    return;
                }
                object = (JsonNode)object22.next();
                StoreSectionV2 storeSectionV2 = JsonUtils.a(object.get("section"), StoreSectionV2.class);
                storeSectionV2.order = n;
                storeSectionV2.listings = new ArrayList<com.smule.android.network.models.ListingV2>(storeSectionV2.listingIds.size());
                for (String string2 : storeSectionV2.listingIds) {
                    if (!this.m.containsKey(string2)) continue;
                    storeSectionV2.listings.add(this.m.get(string2));
                }
                this.k.put(storeSectionV2.sectionId, storeSectionV2);
                object = object.get("parentSectionId");
                object = object != null ? object.asText() : null;
                StoreSectionV2 storeSectionV22 = object != null ? this.k.get(object) : null;
                if (storeSectionV22 == null) {
                    this.g.storeSections.add(storeSectionV2);
                    Log.b(b, "Section added " + storeSectionV2.sectionId + " listings " + storeSectionV2.listings.size());
                } else {
                    storeSectionV22.subSections.add(storeSectionV2);
                    Log.b(b, (String)object + ": Subsection added " + storeSectionV2.sectionId + " listings " + storeSectionV2.listings.size());
                }
                n += n3;
            } while (true);
        }
    }

    static /* synthetic */ void a(StoreManager storeManager) {
        storeManager.m();
    }

    static /* synthetic */ void a(StoreManager storeManager, JsonNode jsonNode) {
        storeManager.a(jsonNode);
    }

    static /* synthetic */ void a(StoreManager storeManager, String string2) {
        storeManager.h(string2);
    }

    static /* synthetic */ void a(StoreManager storeManager, boolean bl, String string2, boolean bl2, long l) {
        storeManager.a(bl, string2, bl2, l);
    }

    private void a(String string2, String string3) {
        MagicNetwork.d().getApplicationContext().getSharedPreferences("STORE_SETTINGS", 0).edit().putString("song_response_" + string2, string3).apply();
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(Set<String> collection) {
        Object object = new HashSet<String>();
        object.addAll(collection);
        if (object == null || object.size() == 0) {
            Log.b(b, "refreshOwnedSongsSection - getMySongsUids returned no UIDs; aborting refresh of owned songs section");
        }
        ArrayList<com.smule.android.network.models.ListingV2> arrayList = new ArrayList<com.smule.android.network.models.ListingV2>();
        object = object.iterator();
        while (object.hasNext()) {
            String string2 = (String)object.next();
            SongV2 songV2 = this.a(string2);
            if (songV2 != null) {
                com.smule.android.network.models.ListingV2 listingV2 = this.e(songV2.songId);
                if (listingV2 == null) {
                    Log.e(b, "Couldn't find listings for entitlement, creating it: " + string2);
                    arrayList.add(new com.smule.android.network.models.ListingV2(songV2));
                } else {
                    arrayList.add(listingV2);
                }
                Log.c(b, "refreshOwnedSongsSection - adding " + songV2.title);
                continue;
            }
            Log.e(b, "Couldn't find song for entitlement!" + string2);
        }
        this.q.listings = arrayList;
        if (this.q.listings != null && this.q.listings.size() > 0) {
            Collections.sort(this.q.listings, new Comparator<com.smule.android.network.models.ListingV2>(){

                public int a(com.smule.android.network.models.ListingV2 listingV2, com.smule.android.network.models.ListingV2 listingV22) {
                    if (listingV2 == null || listingV22 == null || listingV2.song == null || listingV22.song == null) {
                        Log.d(com.smule.android.network.models.ListingV2.b(), "ListingComparatorByDisplayName - null elements passed in. Returning 0.");
                        return 0;
                    }
                    return listingV2.song.title.replaceAll("\\p{Punct}", "").toLowerCase().compareTo(listingV22.song.title.replaceAll("\\p{Punct}", "").toLowerCase());
                }

                public /* synthetic */ int compare(Object object, Object object2) {
                    return this.a((com.smule.android.network.models.ListingV2)object, (com.smule.android.network.models.ListingV2)object2);
                }
            });
            this.q.listingIds = com.smule.android.network.models.ListingV2.a(this.q.listings);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(boolean bl, String string2, boolean bl2, long l) {
        if (bl2) {
            this.l();
            if (string2 != null) {
                this.j(string2);
            }
            if (bl) {
                NotificationCenter.a().b("SONGBOOK_UPDATED_EVENT", "ACTION", "SONGBOOK_SYNCED_ACTION");
                d = true;
            }
        } else if (!this.c.get()) {
            MagicNetwork.d().showConnectionError();
        }
        int n = Math.round((float)(System.currentTimeMillis() - l) / 1000.0f);
        Log.c(b, "snapshot finished after " + n + " seconds, success=" + Boolean.valueOf(bl2));
    }

    static /* synthetic */ boolean a(StoreManager storeManager, boolean bl) {
        storeManager.r = bl;
        return bl;
    }

    public static SongV2 b(String object) throws IOException {
        object = (JsonNode)JsonUtils.a().readValue((String)object, JsonNode.class);
        object = (SongV2)JsonUtils.a().treeToValue((TreeNode)object, SongV2.class);
        return (SongV2)LocalizationManager.a().a("store", object);
    }

    static /* synthetic */ AtomicBoolean b(StoreManager storeManager) {
        return storeManager.w;
    }

    private void b(SongV2 songV2) {
        if (!this.i.containsKey(songV2.artist)) {
            this.i.put(songV2.artist, new ArrayList());
        }
        this.i.get(songV2.artist).add(songV2);
    }

    private void b(String string2, String string3) {
        MagicNetwork.d().getApplicationContext().getSharedPreferences("STORE_SETTINGS", 0).edit().putString("song_signature_" + string2, string3).apply();
    }

    public static com.smule.android.network.models.ListingV2 c(String object) throws IOException {
        object = (JsonNode)JsonUtils.a().readValue((String)object, JsonNode.class);
        object = (com.smule.android.network.models.ListingV2)JsonUtils.a().treeToValue((TreeNode)object, com.smule.android.network.models.ListingV2.class);
        return (com.smule.android.network.models.ListingV2)LocalizationManager.a().a("store", object);
    }

    static /* synthetic */ void c(StoreManager storeManager) {
        storeManager.h();
    }

    private void c(SongV2 songV2) {
        if (!this.j.containsKey(songV2.genre)) {
            this.j.put(songV2.genre, new ArrayList());
        }
        this.j.get(songV2.genre).add(songV2);
    }

    static /* synthetic */  d(StoreManager storeManager) {
        return storeManager.z;
    }

    private SongV2 d(SongV2 songV2) {
        String string2 = this.i(songV2.songId);
        if (string2 != null) {
            Log.b(b, "Restoring song " + songV2.songId + " from saved json");
            songV2.a(JsonUtils.a(new NetworkResponse((String)string2).l.get("song"), SongV2.class));
            return songV2;
        }
        return null;
    }

    static /* synthetic */ StoreSectionV2 e(StoreManager storeManager) {
        return storeManager.n();
    }

    static /* synthetic */ com.smule.android.network.api.StoreAPI f(StoreManager storeManager) {
        return storeManager.f;
    }

    static /* synthetic */ String g() {
        return b;
    }

    private void h() {
        long l = System.currentTimeMillis();
        NetworkResponse networkResponse = NetworkUtils.a(this.f.getStore(this.j(), new SnpRequest(){
            public String storeId;

            public StoreAPI setStoreId(String string2) {
                this.storeId = string2;
                return this;
            }
        }.setStoreId(this.y.a())));
        Log.b(b, "Fetching store snapshot " + networkResponse.b + " Etag " + networkResponse.o + " old etag " + this.v);
        if (networkResponse.b == 304 || this.v != null && this.v.equals(networkResponse.o)) {
            this.x = System.currentTimeMillis();
            boolean bl = false;
            if (!d) {
                bl = true;
            }
            this.a(bl, null, true, l);
            return;
        }
        if (networkResponse.c() && networkResponse.l != null) {
            this.x = System.currentTimeMillis();
            Log.c(b, "new snapshot available, beginning sync.");
            LocalizationManager.a().a("store", new Runnable(this, networkResponse, l){
                final /* synthetic */ NetworkResponse a;
                final /* synthetic */ long b;
                final /* synthetic */ StoreManager c;
                {
                    this.c = storeManager;
                    this.a = networkResponse;
                    this.b = l;
                }

                public void run() {
                    StoreManager.a(this.c, this.a.l.get("store"));
                    StoreManager.a(this.c, this.a.j);
                    StoreManager.a(this.c, true, this.a.o, true, this.b);
                }
            });
            return;
        }
        Log.d(b, "Store snapshot update failed");
    }

    private void h(String string2) {
        if (string2 == null) {
            Log.b(b, "saveStore - called with null responseBody");
            return;
        }
        MagicNetwork.d().getApplicationContext().getSharedPreferences("STORE_SETTINGS", 0).edit().putString("store_response", string2).apply();
    }

    private String i() {
        return MagicNetwork.d().getApplicationContext().getSharedPreferences("STORE_SETTINGS", 0).getString("store_response", null);
    }

    private String i(String string2) {
        return MagicNetwork.d().getApplicationContext().getSharedPreferences("STORE_SETTINGS", 0).getString("song_response_" + string2, null);
    }

    private String j() {
        this.v = MagicNetwork.d().getApplicationContext().getSharedPreferences("STORE_SETTINGS", 0).getString("store_signature", null);
        return this.v;
    }

    private void j(String string2) {
        MagicNetwork.d().getApplicationContext().getSharedPreferences("STORE_SETTINGS", 0).edit().putString("store_signature", string2).apply();
        this.v = string2;
    }

    private String k(String string2) {
        return MagicNetwork.d().getApplicationContext().getSharedPreferences("STORE_SETTINGS", 0).getString("song_signature_" + string2, null);
    }

    private void k() {
        SharedPreferences sharedPreferences = MagicNetwork.d().getApplicationContext().getSharedPreferences("STORE_SETTINGS", 0);
        this.c.set(sharedPreferences.getBoolean("STORE_SETTINGS", false));
    }

    private void l() {
        MagicNetwork.d().getApplicationContext().getSharedPreferences("STORE_SETTINGS", 0).edit().putBoolean("STORE_SETTINGS", true).apply();
        this.c.set(true);
    }

    private void m() {
        if (this.r) {
            return;
        }
        this.r = true;
        new Thread(new Runnable(this){
            final /* synthetic */ StoreManager a;
            {
                this.a = storeManager;
            }

            public void run() {
                if (StoreManager.d(this.a) != null && StoreManager.d(this.a).a()) {
                    StoreManager.a(this.a, StoreManager.e(this.a));
                }
                StoreManager.a(this.a, false);
                this.a.e();
            }
        }).start();
    }

    /*
     * Enabled aggressive block sorting
     */
    private StoreSectionV2 n() {
        Log.c(b, "create mySongs section");
        Set<String> set = EntitlementsManager.a().b();
        if (set == null || set.size() == 0) {
            Log.b(b, "createOwnedSongsSection - getMySongsUids returned no UIDs; aborting creation of owned songs section");
        }
        Context context = MagicNetwork.d().getApplicationContext();
        StoreSectionV2 storeSectionV2 = new StoreSectionV2();
        int n = context.getResources().getIdentifier("my_songs", "string", context.getPackageName());
        storeSectionV2.displayName = n == 0 ? "My Songs" : context.getString(n);
        storeSectionV2.order = this.z != null ? this.z.b() : -1;
        storeSectionV2.sectionId = "my_songs";
        this.q = storeSectionV2;
        this.a(set);
        return storeSectionV2;
    }

    private void o() {
        Object object = JsonUtils.a();
        this.p = new HashMap<String, String>();
        this.o = new HashMap<String, String>();
        try {
            Object object2 = MagicNetwork.d().getApplicationContext();
            int n = object2.getResources().getIdentifier("product_uid_map", "raw", object2.getPackageName());
            object = object.readTree(object2.getResources().openRawResource(n)).fields();
            while (object.hasNext()) {
                Object object3 = (Map.Entry)object.next();
                object2 = (String)object3.getKey();
                object3 = ((JsonNode)object3.getValue()).textValue();
                this.o.put((String)object2, (String)object3);
                this.p.put((String)object3, (String)object2);
            }
        }
        catch (Exception exception) {
            Log.d(b, "Failed to read product_uid_map.json", exception);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @Deprecated
    public SongV2 a(SongV2 songV2) {
        Object object;
        Object object2 = null;
        if (songV2.eTag == null || songV2.eTag.length() == 0) {
            this.d(songV2);
        }
        NetworkResponse networkResponse = NetworkUtils.a(this.f.getSong(this.k(songV2.songId), new SnpRequest(){
            public String songId;

            public StoreAPI setSongId(String string2) {
                this.songId = string2;
                return this;
            }
        }.setSongId(songV2.songId)));
        if (networkResponse.b == 304) {
            object = songV2;
        } else {
            object = object2;
            if (networkResponse.b != 0) {
                MagicNetwork.a(networkResponse);
                object = object2;
            }
        }
        if ((object2 = networkResponse.f()) != null) {
            object = JsonUtils.a(object2.get("song"), SongV2.class);
            object.totalPlayCount = JsonUtils.a(object2.get("totalPlays"), Integer.class);
            object2 = new HashSet();
            Iterator<ResourceV2> iterator = object.resources.iterator();
            while (iterator.hasNext()) {
                ResourceV2 resourceV2 = iterator.next();
                if (object2.contains(resourceV2.uid)) {
                    iterator.remove();
                    continue;
                }
                object2.add(resourceV2.uid);
            }
            object2 = this.a(object.songId);
            if (object2 != null) {
                object2.a((SongV2)object);
                object = object2;
            } else {
                Log.e(b, "No existing product found for the song download " + songV2.songId);
            }
            this.b(songV2.songId, networkResponse.o);
            this.a(songV2.songId, networkResponse.j);
        }
        return LocalizationManager.a().a("store", object);
    }

    public SongV2 a(String object) {
        synchronized (this) {
            SongV2 songV2;
            block6 : {
                SongV2 songV22;
                songV2 = songV22 = this.h.get(object);
                if (songV22 != null) break block6;
                songV2 = new SongV2();
                songV2.songId = object;
                songV2 = songV22 = this.d(songV2);
                if (songV22 == null) break block6;
                this.h.put((String)object, songV22);
                this.b(songV22);
                this.c(songV22);
                songV2 = songV22;
            }
            object = LocalizationManager.a().a("store", songV2);
            return object;
        }
    }

    public void a(String string2, String string3, String string4, Integer n, StoreAPI streamType, StoreAPI.ProductType productType, NetworkResponseCallback networkResponseCallback, String string5) {
        MagicNetwork.a(new Runnable(this, networkResponseCallback, string2, productType, string3, streamType, string4, n, string5){
            final /* synthetic */ NetworkResponseCallback a;
            final /* synthetic */ String b;
            final /* synthetic */ StoreAPI.ProductType c;
            final /* synthetic */ String d;
            final /* synthetic */ StoreAPI e;
            final /* synthetic */ String f;
            final /* synthetic */ Integer g;
            final /* synthetic */ String h;
            final /* synthetic */ StoreManager i;
            {
                this.i = storeManager;
                this.a = networkResponseCallback;
                this.b = string2;
                this.c = productType;
                this.d = string3;
                this.e = streamType;
                this.f = string4;
                this.g = n;
                this.h = string5;
            }

            public void run() {
                com.smule.android.network.core.CoreUtil.a(this.a, NetworkUtils.a(StoreManager.f(this.i).streamFinished(new SnpRequest(){
                    public Map<String, Object> currencyPrice;
                    public String productId;
                    public String productType;
                    public String seedPerformanceKey;
                    public String songId;
                    public String type;

                    private com.smule.android.network.api.StoreAPI$StreamFinishedRequest setCurrency(String string2, Integer n) {
                        HashMap<String, Object> hashMap = new HashMap<String, Object>();
                        hashMap.put("currencyUid", string2);
                        hashMap.put("price", n);
                        this.currencyPrice = hashMap;
                        return this;
                    }

                    public com.smule.android.network.api.StoreAPI$StreamFinishedRequest setProductId(String string2) {
                        this.productId = string2;
                        return this;
                    }

                    public com.smule.android.network.api.StoreAPI$StreamFinishedRequest setProductType(StoreAPI.ProductType productType) {
                        this.productType = productType.toString();
                        return this;
                    }

                    public com.smule.android.network.api.StoreAPI$StreamFinishedRequest setSeedPerformanceKey(String string2) {
                        this.seedPerformanceKey = string2;
                        return this;
                    }

                    public com.smule.android.network.api.StoreAPI$StreamFinishedRequest setSongId(String string2) {
                        this.songId = string2;
                        return this;
                    }

                    public com.smule.android.network.api.StoreAPI$StreamFinishedRequest setStreamTypeAndCurrency(StoreAPI streamType, String string2, Integer n) {
                        if (streamType != null) {
                            this.type = streamType.getValue();
                            if (streamType == StoreAPI.PAID) {
                                this.setCurrency(string2, n);
                            }
                        }
                        return this;
                    }
                }.setProductId(this.b).setProductType(this.c).setSongId(this.d).setStreamTypeAndCurrency(this.e, this.f, this.g).setSeedPerformanceKey(this.h))));
            }
        });
    }

    public void a(boolean bl,  object,  storeManagerOptionalMethodsDelegate) {
        Log.b(b, "initStore started - shallow init is: " + bl);
        if (object == null) {
            throw new RuntimeException("init - StoreManagerRequiredMethodsDelegate cannot be null, as it is REQUIRED");
        }
        this.y = object;
        if (storeManagerOptionalMethodsDelegate != null) {
            this.z = storeManagerOptionalMethodsDelegate;
        }
        long l = System.currentTimeMillis();
        Log.b(b, "readProductUidMap() finished at: " + (System.currentTimeMillis() - l));
        this.k();
        Log.b(b, "readFirstSyncComplete() finished at: " + (System.currentTimeMillis() - l));
        if (this.z != null && !this.c.get() && (object = this.z.c()) != null) {
            this.h((String)object);
        }
        if (!bl) {
            this.b();
            Log.b(b, "deepInitStore() finished at: " + (System.currentTimeMillis() - l));
        }
        this.f();
        Log.b(b, "addBundledContent() finished at: " + (System.currentTimeMillis() - l));
        l = Math.round((float)(System.currentTimeMillis() - l) / 1000.0f);
        Log.b(b, "initStore ended, duration = " + l);
    }

    public void b() {
        if (this.s) {
            Log.b(b, "deepInitStore - already in progress; ignoring duplicate call");
            return;
        }
        if (this.t) {
            Log.d(b, "deepInitStore - already completed; ignoring call");
            return;
        }
        this.s = true;
        Object object = this.i();
        if (object != null) {
            object = new NetworkResponse((String)object);
            if (object.b == 0 && object.l != null) {
                this.a(object.l.get("store"));
                this.u = true;
            }
        }
        this.s = false;
        this.e();
    }

    public boolean c() {
        return this.u;
    }

    public SoundfontV2 d(String object) {
        synchronized (this) {
            object = LocalizationManager.a().a("store", this.n.get(object));
            return object;
        }
    }

    public boolean d() {
        if (this.x + this.a < System.currentTimeMillis()) {
            return true;
        }
        return false;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public com.smule.android.network.models.ListingV2 e(String string2) {
        synchronized (this) {
            com.smule.android.network.models.ListingV2 listingV2;
            block5 : {
                if (this.l != null && this.l.size() != 0) break block5;
                Log.e(b, "findListingsByProductUid - mListingsByProduct is empty; was the StoreManager not initialized properly?");
                return null;
            }
            com.smule.android.network.models.ListingV2 listingV22 = listingV2 = this.l.get(string2);
            if (listingV2 != null) return listingV22;
            Log.c(b, "findListingByProductUid - returning null ListingV2 for product id: " + string2);
            return listingV2;
        }
    }

    public void e() {
        NotificationCenter.a().b("SONGBOOK_UPDATED_EVENT", "ACTION", "PRODUCTS_SORTED_ACTION");
    }

    public SongV2 f(String string2) {
        SongV2 songV2 = this.a(string2);
        if (songV2 != null) {
            return this.a(songV2);
        }
        if (EntitlementsManager.a().c(string2)) {
            songV2 = new SongV2();
            songV2.songId = string2;
            songV2.eTag = "NULL";
            return this.a(songV2);
        }
        return null;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public boolean f() {
        boolean bl;
        Iterator<String> iterator;
        // MONITORENTER : this
        try {
            for (Object object : MagicNetwork.d().getBundledContent()) {
                if (object == null) continue;
                try {
                    Object object2 = ((JsonNode)JsonUtils.a().readValue((String)object, JsonNode.class)).get("data");
                    if (object2 == null || (object2 = object2.get("song")) == null) continue;
                    object2 = StoreManager.b(object2.toString());
                    SongV2 songV2 = this.a(object2.songId);
                    if (songV2 != null && songV2.resources.size() != 0) continue;
                    this.a(object2.songId, (String)object);
                    this.h.put(object2.songId, (SongV2)object2);
                    this.g.songs.add((SongV2)object2);
                }
                catch (IOException iOException) {
                    android.util.Log.e((String)b, (String)("Error parsing json response from bundled content: " + (String)object), (Throwable)iOException);
                }
            }
            iterator = MagicNetwork.d().getBundledListings().iterator();
        }
        catch (Exception exception) {
            Log.d(b, "Error parsing bundled content! " + exception, exception);
            bl = false;
            // MONITOREXIT : this
            return bl;
        }
        while (iterator.hasNext()) {
            Object object;
            object = StoreManager.c(iterator.next());
            if (this.m.get(object.listingId) != null) continue;
            bl = object.price == 0;
            object.isFree = bl;
            object.song = this.a(object.productId);
            this.m.put(object.listingId, (com.smule.android.network.models.ListingV2)object);
            this.l.put(object.productId, (com.smule.android.network.models.ListingV2)object);
        }
        return true;
    }

    public String g(String string2) {
        String string3 = this.o.get(string2);
        if (string3 == null) {
            return string2;
        }
        return string3;
    }

}

