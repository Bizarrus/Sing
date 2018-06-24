/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.os.SystemClock
 *  android.support.annotation.NonNull
 *  android.text.TextUtils
 *  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 *  com.fasterxml.jackson.annotation.JsonProperty
 *  retrofit2.Call
 */
package com.smule.android.network.managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.logging.Log;
import com.smule.android.network.api.EntitlementsAPI;
import com.smule.android.network.core.CoreUtil;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.core.SnpRequest;
import com.smule.android.network.managers.ArrangementManager;
import com.smule.android.network.managers.StoreManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.EntityKey;
import com.smule.android.songbook.SongbookEntry;
import com.smule.android.utils.NotificationCenter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import retrofit2.Call;

public class EntitlementsManager {
    private static final String a = EntitlementsManager.class.getName();
    private static EntitlementsManager b;
    private EntitlementsAPI c = MagicNetwork.a().a(EntitlementsAPI.class);
    private Set<String> d = new HashSet<String>();
    private Set<String> e = new HashSet<String>();
    private Context f;
    private AtomicBoolean g = new AtomicBoolean(false);
    private long h = 0;
    private long i = 0;
    private boolean j;
    private final LinkedList<Runnable> k = new LinkedList();
    private boolean l = false;
    private String m;

    private EntitlementsManager() {
    }

    public static EntitlementsManager a() {
        synchronized (EntitlementsManager.class) {
            if (b == null) {
                b = new EntitlementsManager();
            }
            EntitlementsManager entitlementsManager = b;
            return entitlementsManager;
        }
    }

    public static String a(Context context) {
        return context.getSharedPreferences("ENTITLEMENTS_SETTINGS", 0).getString("version", "");
    }

    private void a(@NonNull  getEntitlementsResponse) {
        synchronized (this) {
            this.b(getEntitlementsResponse);
            this.a(true);
            this.h = SystemClock.elapsedRealtime();
            this.i = UserManager.a().f();
            return;
        }
    }

    private void a(boolean bl) {
        SharedPreferences.Editor editor = this.f.getSharedPreferences("ENTITLEMENTS_SETTINGS", 0).edit();
        String string2 = TextUtils.join((CharSequence)",", this.d);
        String string3 = TextUtils.join((CharSequence)",", this.e);
        this.m = this.d(string2 + ":" + string3);
        editor.putString("entitlements", string2).putString("arrangements", string3).putString("digest", this.m).putString("version", MagicNetwork.d().getAppVersion()).apply();
        if (bl) {
            this.j();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private boolean a(ArrayList<String> arrayList) {
        int n = MagicNetwork.d().getMaxMySongArrs();
        if (n == -1 || arrayList.size() < n) {
            return false;
        }
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void b(@NonNull  object) {
        if (object.mProducts == null) {
            String string2 = a;
            StringBuilder stringBuilder = new StringBuilder().append("Missing products in entitlements response ");
            object = object.a != null ? object.a.j : "(null)";
            Log.e(string2, stringBuilder.append((String)object).toString());
            return;
        }
        this.d.clear();
        this.e.clear();
        object = object.mProducts.iterator();
        do {
            if (!object.hasNext()) {
                this.d.addAll(MagicNetwork.d().getBundledEntitlements());
                return;
            }
            EntityKey entityKey = (EntityKey)object.next();
            if (entityKey.entityType == EntityKey.EntityType.e) {
                this.d.add(entityKey.entityId);
                continue;
            }
            if (entityKey.entityType != EntityKey.EntityType.p) continue;
            this.e.add(entityKey.entityId);
        } while (true);
    }

    private String d(String string2) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(string2.getBytes());
            string2 = new String(messageDigest.digest());
            return string2;
        }
        catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            return "";
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private boolean g() {
        long l = UserManager.a().f();
        boolean bl = this.i == 0 && l == 0 || this.i != 0 && this.i == l;
        if (SystemClock.elapsedRealtime() - this.h >= 600000) return false;
        if (bl) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void h() {
        try {
            Object object = this.e();
            if (object == null) return;
            if (!object.a()) return;
            this.a(object);
            object = new ArrayList();
            for (String string2 : this.e) {
                if (this.a((ArrayList<String>)object)) continue;
                object.add(string2);
            }
            for (String string2 : MagicNetwork.d().getProgressedSongsUids()) {
                if (!SongbookEntry.b.name().equals(MagicNetwork.d().getProgressedCompType(string2)) || this.a((ArrayList<String>)object) || object.contains(string2)) continue;
                object.add(string2);
            }
            if (!this.l) return;
            if (object.isEmpty()) return;
            ArrangementManager.a().a((List<String>)object, new ArrangementManager(){

                @Override
                public void handleResponse(ArrangementManager.ArrangementVersionLiteListResponse arrangementVersionLiteListResponse) {
                    if (!arrangementVersionLiteListResponse.a()) {
                        Log.d(a, "Error fetching ArrangementVersionLites for My Songs: " + arrangementVersionLiteListResponse.a.b);
                    }
                }
            });
            return;
        }
        finally {
            this.g.set(false);
        }
    }

    private void i() {
        int n = 0;
        synchronized (this) {
            Object object = this.f.getSharedPreferences("ENTITLEMENTS_SETTINGS", 0);
            String[] arrstring = object.getString("entitlements", "");
            String string2 = object.getString("arrangements", "");
            String string3 = object.getString("digest", "");
            object = this.d((String)arrstring + ":" + string2);
            if (this.j && !string3.equals("") && !string3.equals(object)) {
                Log.e(a, "Calculated digest [" + (String)object + "] is different from stored one [" + string3 + "]. Loaded entitlements: " + (String)arrstring);
            }
            this.d.clear();
            arrstring = TextUtils.split((String)arrstring, (String)",");
            int n2 = arrstring.length;
            while (n < n2) {
                string3 = arrstring[n];
                this.d.add(StoreManager.a().g(string3));
                ++n;
            }
            this.e.clear();
            this.e.addAll(Arrays.asList(TextUtils.split((String)string2, (String)",")));
            this.m = object;
            Log.c(a, "Entitlements loaded.");
            this.j();
            return;
        }
    }

    private void j() {
        NotificationCenter.a().b("SONGBOOK_UPDATED_EVENT", "ACTION", "ENTITLEMENTS_UPDATED_ACTION");
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void k() {
        LinkedList<Runnable> linkedList;
        LinkedList<Runnable> linkedList2 = this.k;
        synchronized (linkedList2) {
            linkedList = new LinkedList<Runnable>(this.k);
            this.k.clear();
        }
        while (linkedList.size() > 0) {
            linkedList.remove(0).run();
        }
        return;
    }

    public void a(Context context, boolean bl, boolean bl2, boolean bl3) {
        this.f = context;
        this.j = bl;
        this.l = bl3;
        if (bl2) {
            this.i();
        }
        if (this.d.isEmpty()) {
            this.d.addAll(MagicNetwork.d().getBundledEntitlements());
            Log.c(a, "Added bundled entitlements " + this.d);
            this.a(false);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public boolean a(Runnable runnable) {
        if (runnable != null) {
            LinkedList<Runnable> linkedList = this.k;
            synchronized (linkedList) {
                this.k.addLast(runnable);
            }
        }
        if (this.g()) {
            this.k();
            return false;
        } else {
            if (this.g.getAndSet(true)) return false;
            {
                MagicNetwork.a(new Runnable(){

                    @Override
                    public void run() {
                        EntitlementsManager.this.h();
                        EntitlementsManager.this.k();
                    }
                });
                return true;
            }
        }
    }

    public boolean a(String string2) {
        synchronized (this) {
            boolean bl = this.d.contains(string2);
            return bl;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public Set<String> b() {
        synchronized (this) {
            HashSet<String> hashSet;
            if (this.d.size() == 0 && MagicNetwork.d().getBundledContent().size() > 0) {
                this.c();
                Log.e(a, "Entitlements error!  Did the app just crash?");
            }
            String string2 = a;
            StringBuilder stringBuilder = new StringBuilder().append("getOwnedProducts() returning ");
            if (this.d == null) {
                hashSet = "0";
            } else {
                int n = this.d.size();
                hashSet = n;
            }
            Log.b(string2, stringBuilder.append(hashSet).append(" product UIDs").toString());
            hashSet = new HashSet<String>(this.d.size());
            hashSet.addAll(this.d);
            return hashSet;
        }
    }

    public boolean b(String string2) {
        synchronized (this) {
            boolean bl = this.e.contains(string2);
            return bl;
        }
    }

    public void c() {
        if (this.g.getAndSet(true)) {
            return;
        }
        MagicNetwork.a(new Runnable(){

            @Override
            public void run() {
                EntitlementsManager.this.h();
            }
        });
    }

    public boolean c(String string2) {
        if (this.a(string2) || this.b(string2)) {
            return true;
        }
        return false;
    }

    public String d() {
        return this.m;
    }

    public  e() {
        return .a(NetworkUtils.a(this.c.getEntitlements(new SnpRequest())));
    }

    public static interface GetEntitlementsCallback
    extends ResponseInterface<> {
        @Override
        public void handleResponse( var1);
    }

}

