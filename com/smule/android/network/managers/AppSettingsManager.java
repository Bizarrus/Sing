/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.support.annotation.NonNull
 *  android.support.annotation.Nullable
 *  com.fasterxml.jackson.core.TreeNode
 *  com.fasterxml.jackson.databind.JsonNode
 *  org.json.JSONArray
 *  org.json.JSONException
 *  retrofit2.Call
 */
package com.smule.android.network.managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.JsonNode;
import com.smule.android.logging.Log;
import com.smule.android.logging.MagicCrittercism;
import com.smule.android.network.api.AppSettingsAPI;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.SnpRequest;
import com.smule.android.network.managers.AppSettingsManager;
import com.smule.android.network.managers.AppSettingsModel;
import com.smule.android.network.managers.UserManager;
import com.smule.android.utils.JsonUtils;
import com.smule.android.utils.NotificationCenter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;
import retrofit2.Call;

public class AppSettingsManager
implements AppSettingsModel {
    protected static AppSettingsManager a;
    private static final String d;
    protected Context b;
    private com.smule.android.network.api.AppSettingsAPI c = MagicNetwork.a().a(com.smule.android.network.api.AppSettingsAPI.class);
    private List<String> e = new ArrayList<String>();
    private Map<String, Map<String, JsonNode>> f = null;
    private boolean g = false;
    private String h = "{}";
    private Map<String, Map<String, JsonNode>> i = null;
    private Map<String, Map<String, JsonNode>> j = new HashMap<String, Map<String, JsonNode>>();
    private long k = 0;
    private long l = 0;
    private boolean m = false;
    private AtomicBoolean n = new AtomicBoolean(false);
    private final LinkedList<Runnable> o = new LinkedList();
    private ArrayList<String> p = new ArrayList();
    private long q = -1;
    @Nullable
    private AppSettingsModel r;

    static {
        d = AppSettingsManager.class.getSimpleName();
    }

    private AppSettingsManager() {
    }

    public static AppSettingsManager a() {
        synchronized (AppSettingsManager.class) {
            if (a == null) {
                a = new AppSettingsManager();
                AppSettingsManager.a.b = MagicNetwork.d().getApplicationContext();
                a.a(MagicNetwork.d().getAppSettingIDs());
                a.i();
            }
            AppSettingsManager appSettingsManager = a;
            return appSettingsManager;
        }
    }

    public static AppSettingsManager a(Context object) {
        synchronized (AppSettingsManager.class) {
            if (a == null) {
                a = new AppSettingsManager();
                a.a(MagicNetwork.d().getAppSettingIDs());
                AppSettingsManager.a.b = object;
                a.i();
            }
            AppSettingsManager.a.b = object;
            object = a;
            return object;
        }
    }

    private Map<String, JsonNode> a(JsonNode object) {
        HashMap<String, JsonNode> hashMap = new HashMap<String, JsonNode>();
        object = object.fields();
        while (object.hasNext()) {
            Map.Entry entry = (Map.Entry)object.next();
            String string2 = (String)entry.getKey();
            entry = (JsonNode)entry.getValue();
            if (string2 == null || entry == null) continue;
            hashMap.put(string2, (JsonNode)entry);
        }
        return hashMap;
    }

    private void a(@Nullable Collection<String> collection) {
        this.e.clear();
        if (collection != null) {
            this.e.addAll(collection);
        }
        if (!collection.contains("appFamily")) {
            this.e.add("appFamily");
        }
    }

    private void a(Map<String, Map<String, JsonNode>> map, JsonNode object) {
        if (object == null) {
            return;
        }
        object = object.fields();
        while (object.hasNext()) {
            Map.Entry entry = (Map.Entry)object.next();
            map.put((String)entry.getKey(), this.a((JsonNode)entry.getValue()));
        }
        this.k();
    }

    private void a(Map<String, Map<String, JsonNode>> map, String string2) throws IOException {
        JsonNode jsonNode = ((JsonNode)JsonUtils.a().readValue(string2, JsonNode.class)).get("data");
        string2 = jsonNode;
        if (jsonNode != null) {
            string2 = jsonNode;
            if (jsonNode.get("settings") != null) {
                string2 = jsonNode.get("settings");
            }
        }
        this.a(map, (JsonNode)string2);
    }

    private static boolean a(long l) {
        long l2 = UserManager.a().f();
        if (l == 0 && l2 == 0 || l != 0 && l == l2) {
            return true;
        }
        return false;
    }

    static /* synthetic */ boolean a(AppSettingsManager appSettingsManager) {
        return appSettingsManager.j();
    }

    static /* synthetic */ AtomicBoolean b(AppSettingsManager appSettingsManager) {
        return appSettingsManager.n;
    }

    private void b(String string2) {
        NotificationCenter.a().b(string2, new Object[0]);
    }

    private void b(Map<String, Map<String, JsonNode>> map, String string2) throws IOException {
        this.a(map, (JsonNode)JsonUtils.a().readValue(string2, JsonNode.class));
    }

    static /* synthetic */ void c(AppSettingsManager appSettingsManager) {
        appSettingsManager.l();
    }

    static /* synthetic */ String h() {
        return d;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void i() {
        Object object = this.b.getSharedPreferences("APP_SETTINGS", 0);
        String string2 = object.getString("LAST_SETTINGS_RESPONSE_BODY", null);
        long l = object.getLong("CACHED_SETTINGS_ACCOUNT_ID", -1);
        if (string2 == null) {
            Log.b(d, "no cached settings");
            return;
        }
        try {
            object = new HashMap();
            this.a((Map<String, Map<String, JsonNode>>)object, string2);
            HashMap<String, Map<String, JsonNode>> hashMap = new HashMap<String, Map<String, JsonNode>>();
            this.a(hashMap, string2);
            synchronized (this) {
                this.f = object;
                this.g = true;
                this.h = string2;
                this.i = hashMap;
                if (this.r != null) {
                    this.r.a();
                }
                this.q = l;
            }
        }
        catch (IOException iOException) {
            Log.d(d, "Error parsing json response from backup: " + string2, iOException);
            return;
        }
        Log.b(d, "Restoring application settings for account:" + this.q + ":" + string2);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private boolean j() {
        this.m = true;
        long l = System.currentTimeMillis();
        if (this.c()) {
            return true;
        }
        if (this.e.size() <= 0) {
            Log.e(d, "No setting IDs set to retrieve.");
            return false;
        }
        HashMap<String, Map<String, JsonNode>> hashMap = new HashMap<String, Map<String, JsonNode>>();
        NetworkResponse networkResponse = NetworkUtils.a(this.c.getSettings(new SnpRequest(){
            public List<String> settingsIds;

            public AppSettingsAPI setSettingsIds(List<String> list) {
                this.settingsIds = list;
                return this;
            }
        }.setSettingsIds(this.e)));
        if (!networkResponse.c()) {
            MagicNetwork.a(networkResponse);
            Log.e(d, "There was a problem with the fetchAllConfigSettings() call!");
            return false;
        }
        this.b.getSharedPreferences("APP_SETTINGS", 0).edit().putString("LAST_SETTINGS_RESPONSE_BODY", networkResponse.j).putLong("CACHED_SETTINGS_ACCOUNT_ID", UserManager.a().f()).apply();
        if (networkResponse.b != 0) {
            Log.d(d, "Bad response code from server : " + networkResponse.b);
            return false;
        }
        if (networkResponse.l == null) {
            Log.d(d, "data node not found");
            return false;
        }
        Object object = networkResponse.l;
        this.a(hashMap, (JsonNode)object);
        HashMap<String, Map<String, JsonNode>> hashMap2 = new HashMap<String, Map<String, JsonNode>>();
        object = object.toString();
        try {
            this.b(hashMap2, (String)object);
        }
        catch (IOException iOException) {
            Log.e(d, "Unable to create copy:" + iOException);
            hashMap2 = hashMap;
        }
        Log.b(d, "Read new application config settings from server : " + networkResponse.j);
        synchronized (this) {
            this.f = hashMap;
            this.g = false;
            this.h = object;
            this.i = hashMap2;
            if (this.r != null) {
                this.r.a();
            }
        }
        this.k = l;
        this.l = UserManager.a().f();
        MagicCrittercism.a(this.b, this.b("crashReporting", "percentage", -1));
        this.b("APP_SETTINGS_PARSED_EVENT");
        return true;
    }

    private void k() {
        ArrayList<String> arrayList;
        block6 : {
            int n;
            String string2 = this.a("appFamily", "apps", (String)null);
            if (string2 == null) {
                return;
            }
            arrayList = new ArrayList<String>();
            try {
                string2 = new JSONArray(string2);
                n = 0;
            }
            catch (JSONException jSONException) {
                Log.e(d, "JSONException thrown parsing app family data!");
                jSONException.printStackTrace();
            }
            do {
                block7 : {
                    if (n >= string2.length()) break block6;
                    String string3 = string2.get(n).toString();
                    if (string3 == null) break block7;
                    arrayList.add(string3);
                }
                ++n;
            } while (true);
        }
        this.p = arrayList;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void l() {
        LinkedList<Runnable> linkedList = this.o;
        synchronized (linkedList) {
            while (this.o.size() > 0) {
                this.o.remove(0).run();
            }
            return;
        }
    }

    @Override
    public double a(@NonNull String string2, @NonNull String string3, double d) {
        if ((string2 = this.a(string2, string3)) == null || !string2.isValueNode()) {
            return d;
        }
        return string2.asDouble(d);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public JsonNode a(@NonNull String string2, @NonNull String string3) {
        synchronized (this) {
            Map<String, JsonNode> map;
            if (!this.e.contains(string2)) {
                Log.c(d, "SettingsId was not requested:" + string2);
                return null;
            }
            Map<String, JsonNode> map2 = map = this.a(string2);
            if (map == null) {
                map2 = this.j.get(string2);
            }
            if (map2 != null) void var2_2;
            return map2.get(var2_2);
            return null;
        }
    }

    @Nullable
    @Override
    public JsonNode a(@NonNull String string2, @NonNull String string3, @Nullable JsonNode jsonNode) {
        JsonNode jsonNode2 = this.a(string2, string3);
        if (jsonNode2 == null) {
            Log.b(d, "no value for settingid: " + string2 + " key: " + string3);
            return jsonNode;
        }
        return jsonNode2;
    }

    @Nullable
    @Override
    public String a(@NonNull String string2, @NonNull String string3, int n) {
        return this.a(string2, string3, MagicNetwork.d().getApplicationContext().getString(n));
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Nullable
    @Override
    public String a(@NonNull String string2, @NonNull String string3, String string4) {
        JsonNode jsonNode = this.a(string2, string3);
        if (jsonNode == null) {
            Log.b(d, "no value for settingid: " + string2 + " key: " + string3);
            return string4;
        }
        string3 = jsonNode.toString();
        if (string3.length() == 0) return string4;
        string2 = string3;
        if (!string3.startsWith("\"")) return string2;
        string2 = string3;
        if (!string3.endsWith("\"")) return string2;
        return string3.substring(1, string3.length() - 1);
    }

    @Nullable
    public <V> List<V> a(@NonNull String string2, @NonNull String string3, List<V> list) {
        JsonNode jsonNode = this.a(string2, string3);
        if (jsonNode == null || !jsonNode.isArray()) {
            return list;
        }
        try {
            List list2 = (List)JsonUtils.a().treeToValue((TreeNode)jsonNode, List.class);
            return list2;
        }
        catch (IOException iOException) {
            Log.d(d, "Failed to parse value for settingId=" + string2 + " key=" + string3 + " value=" + (Object)jsonNode + ". Returning default");
            return list;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public Map<String, JsonNode> a(@NonNull String string2) {
        Map<String, JsonNode> map = null;
        synchronized (this) {
            if (this.m || this.i != null) {
                if (this.i == null) return map;
                return this.i.get(string2);
            }
            Log.e(d, "Reading AppConfig settings before loading them!");
            return map;
        }
    }

    public void a(@Nullable AppSettingsModel onSettingsUpdatedListener) {
        this.r = onSettingsUpdatedListener;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a(@Nullable Runnable runnable) {
        if (runnable != null) {
            LinkedList<Runnable> linkedList = this.o;
            synchronized (linkedList) {
                this.o.addLast(runnable);
            }
        }
        if (this.c()) {
            Log.b(d, "loadSettings: already fetched");
            this.l();
            return;
        }
        if (this.n.getAndSet(true)) {
            Log.b(d, "loadSettings: pending");
            return;
        }
        if (this.d() && this.a("appLaunch", "cacheSettings", true)) {
            Log.b(d, "flushCallbacks called with cached settings");
            this.l();
        }
        MagicNetwork.a(new Runnable(this){
            final /* synthetic */ AppSettingsManager a;
            {
                this.a = appSettingsManager;
            }

            public void run() {
                try {
                    boolean bl = AppSettingsManager.a(this.a);
                    Log.b(AppSettingsManager.h(), "fetchAllConfigSettings done:" + bl);
                    return;
                }
                finally {
                    AppSettingsManager.b(this.a).set(false);
                    AppSettingsManager.c(this.a);
                }
            }
        });
    }

    @Override
    public boolean a(@NonNull String string2, @NonNull String string3, boolean bl) {
        if ((string2 = this.a(string2, string3)) == null || !string2.isValueNode()) {
            return bl;
        }
        return string2.asBoolean(bl);
    }

    @Override
    public int b(@NonNull String string2, @NonNull String string3, int n) {
        if ((string2 = this.a(string2, string3)) == null || !string2.isValueNode()) {
            return n;
        }
        return string2.asInt(n);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void b() {
        synchronized (this) {
            this.f = null;
            this.k = 0;
            this.i = null;
            this.q = -1;
        }
        this.b.getSharedPreferences("APP_SETTINGS", 0).edit().putString("LAST_SETTINGS_RESPONSE_BODY", null).putLong("CACHED_SETTINGS_ACCOUNT_ID", -1).apply();
    }

    public boolean c() {
        if (System.currentTimeMillis() < this.k + 3600000 && AppSettingsManager.a(this.l)) {
            return true;
        }
        return false;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean d() {
        synchronized (this) {
            if (this.e() && this.k != 0) {
                if (AppSettingsManager.a(this.l)) return true;
            }
            if (this.q == -1) return false;
            boolean bl = AppSettingsManager.a(this.q);
            if (!bl) return false;
            return true;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean e() {
        synchronized (this) {
            Map<String, Map<String, JsonNode>> map = this.i;
            if (map == null) return false;
            return true;
        }
    }

    public ArrayList<String> f() {
        if (this.p == null || this.p.size() == 0) {
            Log.e(d, "Apps in family not loaded yet; adding defaults to app family set");
            Object object = MagicNetwork.d().getAppsInFamily();
            if (object != null) {
                object = object.iterator();
                while (object.hasNext()) {
                    String string2 = (String)object.next();
                    this.p.add(string2);
                }
            }
        }
        return this.p;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void g() {
        synchronized (this) {
            Map<String, Map<String, JsonNode>> map = this.f;
            if (map != null) {
                try {
                    map = new HashMap<String, Map<String, JsonNode>>();
                    if (this.g) {
                        this.a(map, this.h);
                    } else {
                        this.b(map, this.h);
                    }
                    this.i = map;
                    if (this.r != null) {
                        this.r.a();
                    }
                }
                catch (IOException iOException) {
                    Log.e(d, "IOException:" + iOException);
                }
            }
            return;
        }
    }
}

