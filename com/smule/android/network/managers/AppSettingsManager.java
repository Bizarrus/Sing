package com.smule.android.network.managers;

import android.content.Context;
import android.content.SharedPreferences;
import com.facebook.share.internal.ShareConstants;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.JsonNode;
import com.smule.android.logging.Log;
import com.smule.android.logging.MagicCrittercism;
import com.smule.android.network.api.AppSettingsAPI;
import com.smule.android.network.api.AppSettingsAPI.GetSettingsRequest;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.utils.JsonUtils;
import com.smule.android.utils.NotificationCenter;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;

public class AppSettingsManager {
    protected static AppSettingsManager f6772a;
    private static final String f6773d = AppSettingsManager.class.getSimpleName();
    protected Context f6774b;
    private AppSettingsAPI f6775c = ((AppSettingsAPI) MagicNetwork.m7789a().m7817a(AppSettingsAPI.class));
    private List<String> f6776e = new ArrayList();
    private Map<String, Map<String, JsonNode>> f6777f = null;
    private boolean f6778g = false;
    private String f6779h = "{}";
    private Map<String, Map<String, JsonNode>> f6780i = null;
    private Map<String, Map<String, JsonNode>> f6781j = new HashMap();
    private long f6782k = 0;
    private long f6783l = 0;
    private boolean f6784m = false;
    private AtomicBoolean f6785n = new AtomicBoolean(false);
    private final LinkedList<Runnable> f6786o = new LinkedList();
    private ArrayList<String> f6787p = new ArrayList();
    private long f6788q = -1;
    private OnSettingsUpdatedListener f6789r;

    public void m7879a(OnSettingsUpdatedListener onSettingsUpdatedListener) {
        this.f6789r = onSettingsUpdatedListener;
    }

    public static synchronized AppSettingsManager m7855a() {
        AppSettingsManager appSettingsManager;
        synchronized (AppSettingsManager.class) {
            if (f6772a == null) {
                f6772a = new AppSettingsManager();
                f6772a.f6774b = MagicNetwork.m7804d().getApplicationContext();
                f6772a.m7858a(MagicNetwork.m7804d().getAppSettingIDs());
                f6772a.m7868i();
            }
            appSettingsManager = f6772a;
        }
        return appSettingsManager;
    }

    public static synchronized AppSettingsManager m7856a(Context context) {
        AppSettingsManager appSettingsManager;
        synchronized (AppSettingsManager.class) {
            if (f6772a == null) {
                f6772a = new AppSettingsManager();
                f6772a.m7858a(MagicNetwork.m7804d().getAppSettingIDs());
                f6772a.f6774b = context;
                f6772a.m7868i();
            }
            f6772a.f6774b = context;
            appSettingsManager = f6772a;
        }
        return appSettingsManager;
    }

    private void m7860a(Map<String, Map<String, JsonNode>> map, String str) throws IOException {
        JsonNode jsonNode = ((JsonNode) JsonUtils.a().readValue(str, JsonNode.class)).get(ShareConstants.WEB_DIALOG_PARAM_DATA);
        if (!(jsonNode == null || jsonNode.get("settings") == null)) {
            jsonNode = jsonNode.get("settings");
        }
        m7859a((Map) map, jsonNode);
    }

    private void m7865b(Map<String, Map<String, JsonNode>> map, String str) throws IOException {
        m7859a((Map) map, (JsonNode) JsonUtils.a().readValue(str, JsonNode.class));
    }

    private void m7868i() {
        SharedPreferences sharedPreferences = this.f6774b.getSharedPreferences("APP_SETTINGS", 0);
        String string = sharedPreferences.getString("LAST_SETTINGS_RESPONSE_BODY", null);
        long j = sharedPreferences.getLong("CACHED_SETTINGS_ACCOUNT_ID", -1);
        if (string != null) {
            try {
                Map hashMap = new HashMap();
                m7860a(hashMap, string);
                Map hashMap2 = new HashMap();
                m7860a(hashMap2, string);
                synchronized (this) {
                    this.f6777f = hashMap;
                    this.f6778g = true;
                    this.f6779h = string;
                    this.f6780i = hashMap2;
                    if (this.f6789r != null) {
                        this.f6789r.a();
                    }
                    this.f6788q = j;
                }
                Log.m7770b(f6773d, "Restoring application settings for account:" + this.f6788q + ":" + string);
                return;
            } catch (Throwable e) {
                Log.m7775d(f6773d, "Error parsing json response from backup: " + string, e);
                return;
            }
        }
        Log.m7770b(f6773d, "no cached settings");
    }

    private AppSettingsManager() {
    }

    private void m7864b(String str) {
        NotificationCenter.a().b(str, new Object[0]);
    }

    private void m7858a(Collection<String> collection) {
        this.f6776e.clear();
        if (collection != null) {
            this.f6776e.addAll(collection);
        }
        if (!collection.contains("appFamily")) {
            this.f6776e.add("appFamily");
        }
    }

    private Map<String, JsonNode> m7857a(JsonNode jsonNode) {
        Map<String, JsonNode> hashMap = new HashMap();
        Iterator fields = jsonNode.fields();
        while (fields.hasNext()) {
            Entry entry = (Entry) fields.next();
            String str = (String) entry.getKey();
            JsonNode jsonNode2 = (JsonNode) entry.getValue();
            if (!(str == null || jsonNode2 == null)) {
                hashMap.put(str, jsonNode2);
            }
        }
        return hashMap;
    }

    private boolean m7869j() {
        this.f6784m = true;
        Long valueOf = Long.valueOf(System.currentTimeMillis());
        if (m7886c()) {
            return true;
        }
        if (this.f6776e.size() <= 0) {
            Log.m7776e(f6773d, "No setting IDs set to retrieve.");
            return false;
        }
        Map hashMap = new HashMap();
        NetworkResponse a = NetworkUtils.a(this.f6775c.getSettings(new GetSettingsRequest().setSettingsIds(this.f6776e)));
        if (a.m7850c()) {
            this.f6774b.getSharedPreferences("APP_SETTINGS", 0).edit().putString("LAST_SETTINGS_RESPONSE_BODY", a.f6764j).putLong("CACHED_SETTINGS_ACCOUNT_ID", UserManager.m8111a().m8203f()).apply();
            if (a.f6756b != 0) {
                Log.m7774d(f6773d, "Bad response code from server : " + a.f6756b);
                return false;
            } else if (a.f6766l == null) {
                Log.m7774d(f6773d, "data node not found");
                return false;
            } else {
                JsonNode jsonNode = a.f6766l;
                m7859a(hashMap, jsonNode);
                Map hashMap2 = new HashMap();
                String jsonNode2 = jsonNode.toString();
                try {
                    m7865b(hashMap2, jsonNode2);
                } catch (IOException e) {
                    Log.m7776e(f6773d, "Unable to create copy:" + e);
                    hashMap2 = hashMap;
                }
                Log.m7770b(f6773d, "Read new application config settings from server : " + a.f6764j);
                synchronized (this) {
                    this.f6777f = hashMap;
                    this.f6778g = false;
                    this.f6779h = jsonNode2;
                    this.f6780i = hashMap2;
                    if (this.f6789r != null) {
                        this.f6789r.a();
                    }
                }
                this.f6782k = valueOf.longValue();
                this.f6783l = UserManager.m8111a().m8203f();
                MagicCrittercism.m7777a(this.f6774b, m7885c("crashReporting", "percentage", -1));
                m7864b("APP_SETTINGS_PARSED_EVENT");
                return true;
            }
        }
        MagicNetwork.m7795a(a);
        Log.m7776e(f6773d, "There was a problem with the fetchAllConfigSettings() call!");
        return false;
    }

    private void m7859a(Map<String, Map<String, JsonNode>> map, JsonNode jsonNode) {
        if (jsonNode != null) {
            Iterator fields = jsonNode.fields();
            while (fields.hasNext()) {
                Entry entry = (Entry) fields.next();
                map.put(entry.getKey(), m7857a((JsonNode) entry.getValue()));
            }
            m7870k();
        }
    }

    private void m7870k() {
        String a = m7876a("appFamily", "apps", null);
        if (a != null) {
            ArrayList arrayList = new ArrayList();
            try {
                JSONArray jSONArray = new JSONArray(a);
                for (int i = 0; i < jSONArray.length(); i++) {
                    String obj = jSONArray.get(i).toString();
                    if (obj != null) {
                        arrayList.add(obj);
                    }
                }
            } catch (JSONException e) {
                Log.m7776e(f6773d, "JSONException thrown parsing app family data!");
                e.printStackTrace();
            }
            this.f6787p = arrayList;
        }
    }

    public void m7884b() {
        synchronized (this) {
            this.f6777f = null;
            this.f6782k = 0;
            this.f6780i = null;
            this.f6788q = -1;
        }
        this.f6774b.getSharedPreferences("APP_SETTINGS", 0).edit().putString("LAST_SETTINGS_RESPONSE_BODY", null).putLong("CACHED_SETTINGS_ACCOUNT_ID", -1).apply();
    }

    public void m7880a(Runnable runnable) {
        if (runnable != null) {
            synchronized (this.f6786o) {
                this.f6786o.addLast(runnable);
            }
        }
        if (m7886c()) {
            Log.m7770b(f6773d, "loadSettings: already fetched");
            m7871l();
        } else if (this.f6785n.getAndSet(true)) {
            Log.m7770b(f6773d, "loadSettings: pending");
        } else {
            if (m7887d() && m7881a("appLaunch", "cacheSettings", true)) {
                Log.m7770b(f6773d, "flushCallbacks called with cached settings");
                m7871l();
            }
            MagicNetwork.m7790a(new 1(this));
        }
    }

    private static boolean m7861a(long j) {
        long f = UserManager.m8111a().m8203f();
        return (j == 0 && f == 0) || (j != 0 && j == f);
    }

    public boolean m7886c() {
        return System.currentTimeMillis() < this.f6782k + 3600000 && m7861a(this.f6783l);
    }

    public synchronized boolean m7887d() {
        boolean z;
        z = (m7888e() && this.f6782k != 0 && m7861a(this.f6783l)) || (this.f6788q != -1 && m7861a(this.f6788q));
        return z;
    }

    private void m7871l() {
        synchronized (this.f6786o) {
            while (this.f6786o.size() > 0) {
                ((Runnable) this.f6786o.remove(0)).run();
            }
        }
    }

    public synchronized boolean m7888e() {
        return this.f6780i != null;
    }

    public synchronized Map<String, JsonNode> m7878a(String str) {
        Map<String, JsonNode> map = null;
        synchronized (this) {
            if (!this.f6784m && this.f6780i == null) {
                Log.m7776e(f6773d, "Reading AppConfig settings before loading them!");
            } else if (this.f6780i != null) {
                map = (Map) this.f6780i.get(str);
            }
        }
        return map;
    }

    public synchronized JsonNode m7873a(String str, String str2) {
        JsonNode jsonNode;
        if (this.f6776e.contains(str)) {
            Map a = m7878a(str);
            if (a == null) {
                a = (Map) this.f6781j.get(str);
            }
            if (a == null) {
                jsonNode = null;
            } else {
                jsonNode = (JsonNode) a.get(str2);
            }
        } else {
            Log.m7772c(f6773d, "SettingsId was not requested:" + str);
            jsonNode = null;
        }
        return jsonNode;
    }

    public ArrayList<String> m7889f() {
        if (this.f6787p == null || this.f6787p.size() == 0) {
            Log.m7776e(f6773d, "Apps in family not loaded yet; adding defaults to app family set");
            HashSet appsInFamily = MagicNetwork.m7804d().getAppsInFamily();
            if (appsInFamily != null) {
                Iterator it = appsInFamily.iterator();
                while (it.hasNext()) {
                    this.f6787p.add((String) it.next());
                }
            }
        }
        return this.f6787p;
    }

    public JsonNode m7874a(String str, String str2, JsonNode jsonNode) {
        JsonNode a = m7873a(str, str2);
        if (a != null) {
            return a;
        }
        Log.m7770b(f6773d, "no value for settingid: " + str + " key: " + str2);
        return jsonNode;
    }

    public String m7875a(String str, String str2, int i) {
        return m7876a(str, str2, MagicNetwork.m7804d().getApplicationContext().getString(i));
    }

    public String m7876a(String str, String str2, String str3) {
        JsonNode a = m7873a(str, str2);
        if (a == null) {
            Log.m7770b(f6773d, "no value for settingid: " + str + " key: " + str2);
            return str3;
        }
        String obj = a.toString();
        if (obj.length() == 0) {
            return str3;
        }
        if (obj.startsWith("\"") && obj.endsWith("\"")) {
            obj = obj.substring(1, obj.length() - 1);
        }
        return obj;
    }

    public String m7882b(String str, String str2, int i) {
        return m7883b(str, str2, MagicNetwork.m7804d().getApplicationContext().getString(i));
    }

    public String m7883b(String str, String str2, String str3) {
        String a = m7876a(str, str2, str3);
        if (a == null || !a.contains(EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR)) {
            return a;
        }
        MagicCrittercism.m7780a(new Exception("Value not translated: " + str2));
        return str3;
    }

    public int m7885c(String str, String str2, int i) {
        JsonNode a = m7873a(str, str2);
        return (a == null || !a.isValueNode()) ? i : a.asInt(i);
    }

    public <V> List<V> m7877a(String str, String str2, List<V> list) {
        TreeNode a = m7873a(str, str2);
        if (a == null || !a.isArray()) {
            return list;
        }
        try {
            return (List) JsonUtils.a().treeToValue(a, List.class);
        } catch (IOException e) {
            Log.m7774d(f6773d, "Failed to parse value for settingId=" + str + " key=" + str2 + " value=" + a + ". Returning default");
            return list;
        }
    }

    public double m7872a(String str, String str2, double d) {
        JsonNode a = m7873a(str, str2);
        return (a == null || !a.isValueNode()) ? d : a.asDouble(d);
    }

    public boolean m7881a(String str, String str2, boolean z) {
        JsonNode a = m7873a(str, str2);
        return (a == null || !a.isValueNode()) ? z : a.asBoolean(z);
    }

    public synchronized void m7890g() {
        if (this.f6777f != null) {
            try {
                Map hashMap = new HashMap();
                if (this.f6778g) {
                    m7860a(hashMap, this.f6779h);
                } else {
                    m7865b(hashMap, this.f6779h);
                }
                this.f6780i = hashMap;
                if (this.f6789r != null) {
                    this.f6789r.a();
                }
            } catch (IOException e) {
                Log.m7776e(f6773d, "IOException:" + e);
            }
        }
    }
}
