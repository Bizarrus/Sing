package com.smule.android.network.managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.share.internal.ShareConstants;
import com.smule.android.logging.Log;
import com.smule.android.network.api.EntitlementsAPI;
import com.smule.android.network.core.CoreUtil;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.core.SnpRequest;
import com.smule.android.network.managers.ArrangementManager.ArrangementVersionLiteListResponse;
import com.smule.android.network.models.EntityKey;
import com.smule.android.network.models.EntityKey$EntityType;
import com.smule.android.songbook.SongbookEntry.PrimaryCompType;
import com.smule.android.utils.NotificationCenter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class EntitlementsManager {
    private static final String f16601a = EntitlementsManager.class.getName();
    private static EntitlementsManager f16602b;
    private EntitlementsAPI f16603c = ((EntitlementsAPI) MagicNetwork.a().a(EntitlementsAPI.class));
    private Set<String> f16604d = new HashSet();
    private Set<String> f16605e = new HashSet();
    private Context f16606f;
    private AtomicBoolean f16607g = new AtomicBoolean(false);
    private long f16608h = 0;
    private long f16609i = 0;
    private boolean f16610j;
    private final LinkedList<Runnable> f16611k = new LinkedList();
    private boolean f16612l = false;
    private String f16613m;

    class C35541 implements Runnable {
        final /* synthetic */ EntitlementsManager f16596a;

        C35541(EntitlementsManager entitlementsManager) {
            this.f16596a = entitlementsManager;
        }

        public void run() {
            this.f16596a.m18174i();
            this.f16596a.m18177l();
        }
    }

    class C35552 implements Runnable {
        final /* synthetic */ EntitlementsManager f16597a;

        C35552(EntitlementsManager entitlementsManager) {
            this.f16597a = entitlementsManager;
        }

        public void run() {
            this.f16597a.m18174i();
        }
    }

    class C35563 implements ArrangementManager$ArrangementVersionLiteListCallback {
        final /* synthetic */ EntitlementsManager f16598a;

        C35563(EntitlementsManager entitlementsManager) {
            this.f16598a = entitlementsManager;
        }

        public void handleResponse(ArrangementVersionLiteListResponse arrangementVersionLiteListResponse) {
            if (!arrangementVersionLiteListResponse.a()) {
                Log.d(EntitlementsManager.f16601a, "Error fetching ArrangementVersionLites for My Songs: " + arrangementVersionLiteListResponse.a.b);
            }
        }
    }

    class C35574 implements Runnable {
        final /* synthetic */ GetEntitlementsCallback f16599a;
        final /* synthetic */ EntitlementsManager f16600b;

        public void run() {
            CoreUtil.m18079a(this.f16599a, this.f16600b.m18187f());
        }
    }

    public interface GetEntitlementsCallback extends ResponseInterface<GetEntitlementsResponse> {
        void handleResponse(GetEntitlementsResponse getEntitlementsResponse);
    }

    private EntitlementsManager() {
    }

    public static synchronized EntitlementsManager m18163a() {
        EntitlementsManager entitlementsManager;
        synchronized (EntitlementsManager.class) {
            if (f16602b == null) {
                f16602b = new EntitlementsManager();
            }
            entitlementsManager = f16602b;
        }
        return entitlementsManager;
    }

    public void m18178a(Context context, boolean z, boolean z2, boolean z3) {
        this.f16606f = context;
        this.f16610j = z;
        this.f16612l = z3;
        if (z2) {
            m18175j();
        }
        if (this.f16604d.isEmpty()) {
            this.f16604d.addAll(MagicNetwork.d().getBundledEntitlements());
            Log.c(f16601a, "Added bundled entitlements " + this.f16604d);
            m18167a(false);
        }
    }

    public synchronized Set<String> m18181b() {
        Set<String> hashSet;
        Object obj;
        if (this.f16604d.size() == 0 && MagicNetwork.d().getBundledContent().size() > 0) {
            m18185d();
            Log.e(f16601a, "Entitlements error!  Did the app just crash?");
        }
        String str = f16601a;
        StringBuilder append = new StringBuilder().append("getOwnedProducts() returning ");
        if (this.f16604d == null) {
            obj = AppEventsConstants.EVENT_PARAM_VALUE_NO;
        } else {
            obj = Integer.valueOf(this.f16604d.size());
        }
        Log.b(str, append.append(obj).append(" product UIDs").toString());
        hashSet = new HashSet(this.f16604d.size());
        hashSet.addAll(this.f16604d);
        return hashSet;
    }

    public synchronized Set<String> m18183c() {
        Set<String> hashSet;
        Log.b(f16601a, "getOwnedArrangements() returning " + (this.f16605e == null ? AppEventsConstants.EVENT_PARAM_VALUE_NO : Integer.valueOf(this.f16605e.size())) + " arrangement UIDs");
        hashSet = new HashSet(this.f16605e.size());
        hashSet.addAll(this.f16605e);
        return hashSet;
    }

    public synchronized boolean m18180a(String str) {
        return this.f16604d.contains(str);
    }

    public synchronized boolean m18182b(String str) {
        return this.f16605e.contains(str);
    }

    public boolean m18184c(String str) {
        return m18180a(str) || m18182b(str);
    }

    public boolean m18179a(Runnable runnable) {
        if (runnable != null) {
            synchronized (this.f16611k) {
                this.f16611k.addLast(runnable);
            }
        }
        if (m18173h()) {
            m18177l();
            return false;
        } else if (this.f16607g.getAndSet(true)) {
            return false;
        } else {
            MagicNetwork.a(new C35541(this));
            return true;
        }
    }

    public void m18185d() {
        if (!this.f16607g.getAndSet(true)) {
            MagicNetwork.a(new C35552(this));
        }
    }

    public static String m18164a(Context context) {
        return context.getSharedPreferences("ENTITLEMENTS_SETTINGS", 0).getString("version", "");
    }

    public String m18186e() {
        return this.f16613m;
    }

    private boolean m18173h() {
        long f = UserManager.a().f();
        boolean z;
        if (!(this.f16609i == 0 && f == 0) && (this.f16609i == 0 || this.f16609i != f)) {
            z = false;
        } else {
            z = true;
        }
        if (SystemClock.elapsedRealtime() - this.f16608h >= 600000 || !r0) {
            return false;
        }
        return true;
    }

    private boolean m18168a(ArrayList<String> arrayList) {
        int maxMySongArrs = MagicNetwork.d().getMaxMySongArrs();
        if (maxMySongArrs != -1 && arrayList.size() >= maxMySongArrs) {
            return true;
        }
        return false;
    }

    private void m18174i() {
        try {
            GetEntitlementsResponse f = m18187f();
            if (f != null && f.a()) {
                m18165a(f);
                ArrayList arrayList = new ArrayList();
                for (String str : this.f16605e) {
                    if (!m18168a(arrayList)) {
                        arrayList.add(str);
                    }
                }
                for (String str2 : MagicNetwork.d().getProgressedSongsUids()) {
                    if (!(!PrimaryCompType.ARR.name().equals(MagicNetwork.d().getProgressedCompType(str2)) || m18168a(arrayList) || arrayList.contains(str2))) {
                        arrayList.add(str2);
                    }
                }
                if (this.f16612l && !arrayList.isEmpty()) {
                    ArrangementManager.a().a(arrayList, new C35563(this));
                }
            }
            this.f16607g.set(false);
        } catch (Throwable th) {
            this.f16607g.set(false);
        }
    }

    private synchronized void m18165a(@NonNull GetEntitlementsResponse getEntitlementsResponse) {
        m18169b(getEntitlementsResponse);
        m18167a(true);
        this.f16608h = SystemClock.elapsedRealtime();
        this.f16609i = UserManager.a().f();
    }

    private void m18169b(@NonNull GetEntitlementsResponse getEntitlementsResponse) {
        if (getEntitlementsResponse.mProducts != null) {
            this.f16604d.clear();
            this.f16605e.clear();
            Iterator it = getEntitlementsResponse.mProducts.iterator();
            while (it.hasNext()) {
                EntityKey entityKey = (EntityKey) it.next();
                if (entityKey.entityType == EntityKey$EntityType.f17402e) {
                    this.f16604d.add(entityKey.entityId);
                } else if (entityKey.entityType == EntityKey$EntityType.ARR) {
                    this.f16605e.add(entityKey.entityId);
                }
            }
            this.f16604d.addAll(MagicNetwork.d().getBundledEntitlements());
            return;
        }
        Log.e(f16601a, "Missing products in entitlements response " + (getEntitlementsResponse.a != null ? getEntitlementsResponse.a.j : "(null)"));
    }

    private void m18167a(boolean z) {
        Editor edit = this.f16606f.getSharedPreferences("ENTITLEMENTS_SETTINGS", 0).edit();
        String join = TextUtils.join(",", this.f16604d);
        String join2 = TextUtils.join(",", this.f16605e);
        this.f16613m = m18171d(join + ":" + join2);
        edit.putString("entitlements", join).putString("arrangements", join2).putString("digest", this.f16613m).putString("version", MagicNetwork.d().getAppVersion()).apply();
        if (z) {
            m18176k();
        }
    }

    private synchronized void m18175j() {
        synchronized (this) {
            SharedPreferences sharedPreferences = this.f16606f.getSharedPreferences("ENTITLEMENTS_SETTINGS", 0);
            String string = sharedPreferences.getString("entitlements", "");
            String string2 = sharedPreferences.getString("arrangements", "");
            String string3 = sharedPreferences.getString("digest", "");
            String d = m18171d(string + ":" + string2);
            if (!(!this.f16610j || string3.equals("") || string3.equals(d))) {
                Log.e(f16601a, "Calculated digest [" + d + "] is different from stored one [" + string3 + "]. Loaded entitlements: " + string);
            }
            this.f16604d.clear();
            for (String g : TextUtils.split(string, ",")) {
                this.f16604d.add(StoreManager.m18378a().m18432g(g));
            }
            this.f16605e.clear();
            this.f16605e.addAll(Arrays.asList(TextUtils.split(string2, ",")));
            this.f16613m = d;
            Log.c(f16601a, "Entitlements loaded.");
            m18176k();
        }
    }

    private void m18176k() {
        NotificationCenter.m19011a().m19017b("SONGBOOK_UPDATED_EVENT", ShareConstants.ACTION, "ENTITLEMENTS_UPDATED_ACTION");
    }

    private String m18171d(String str) {
        String str2 = "";
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-256");
            instance.update(str.getBytes());
            return new String(instance.digest());
        } catch (NoSuchAlgorithmException e) {
            return str2;
        }
    }

    private void m18177l() {
        synchronized (this.f16611k) {
            LinkedList linkedList = new LinkedList(this.f16611k);
            this.f16611k.clear();
        }
        while (linkedList.size() > 0) {
            ((Runnable) linkedList.remove(0)).run();
        }
    }

    public GetEntitlementsResponse m18187f() {
        return GetEntitlementsResponse.a(NetworkUtils.m18104a(this.f16603c.getEntitlements(new SnpRequest())));
    }
}
