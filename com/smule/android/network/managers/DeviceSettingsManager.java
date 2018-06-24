/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.support.annotation.NonNull
 *  com.fasterxml.jackson.core.type.TypeReference
 *  com.fasterxml.jackson.databind.JsonNode
 *  retrofit2.Call
 */
package com.smule.android.network.managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.smule.android.logging.Log;
import com.smule.android.network.api.DeviceSettingsAPI;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.SnpRequest;
import com.smule.android.network.managers.DeviceSettingsFetcher;
import com.smule.android.network.managers.UserManager;
import com.smule.android.utils.JsonUtils;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import retrofit2.Call;

public class DeviceSettingsManager
implements DeviceSettingsFetcher {
    private static final String a = DeviceSettingsManager.class.getName();
    private static DeviceSettingsManager b;
    private Context c;
    private long d = 0;
    private long e = 0;
    private Map<String, Object> f;
    private DeviceSettingsAPI g;
    private AtomicBoolean h = new AtomicBoolean(false);

    private DeviceSettingsManager() {
        this(MagicNetwork.a().a(DeviceSettingsAPI.class), MagicNetwork.d().getApplicationContext());
    }

    public DeviceSettingsManager(@NonNull DeviceSettingsAPI deviceSettingsAPI, @NonNull Context context) {
        this.g = deviceSettingsAPI;
        this.c = context;
    }

    private Map<String, Object> a(JsonNode jsonNode) {
        return JsonUtils.b(jsonNode, new TypeReference<Map<String, Object>>(){});
    }

    private void a(boolean bl) {
        Log.b(a, "refreshDeviceSettings - begin");
        final long l = UserManager.a().f();
        if (!bl && System.currentTimeMillis() < this.d + 3600000 && this.e == l) {
            Log.b(a, "refreshDeviceSettings - we have updated recently; ignoring call to refresh");
            return;
        }
        if (this.h.getAndSet(true)) {
            Log.b(a, "refreshDeviceSettings - pending");
            return;
        }
        MagicNetwork.a(new Runnable(){

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            @Override
            public void run() {
                Object object = NetworkUtils.a(DeviceSettingsManager.this.g.getDeviceSettings(new SnpRequest()));
                if (object.c()) {
                    DeviceSettingsManager.this.d().edit().putString("DEVICE_SETTINGS_JSON", object.j).apply();
                    object = DeviceSettingsManager.this.a(object.l);
                    if (object != null) {
                        Log.b(a, "refreshDeviceSettings - new settings contains " + object.size() + " items");
                        synchronized (this) {
                            DeviceSettingsManager.this.f = (Map)object;
                            DeviceSettingsManager.this.d = System.currentTimeMillis();
                            DeviceSettingsManager.this.e = l;
                        }
                    } else {
                        Log.e(a, "refreshDeviceSettings - parsing new settings failed");
                    }
                } else {
                    Log.d(a, "refreshDeviceSettings - call to getDeviceSettings did not succeed");
                }
                DeviceSettingsManager.this.h.set(false);
            }
        });
    }

    @NonNull
    public static DeviceSettingsFetcher b() {
        synchronized (DeviceSettingsManager.class) {
            if (b == null) {
                b = new DeviceSettingsManager();
                b.e();
            }
            DeviceSettingsManager deviceSettingsManager = b;
            return deviceSettingsManager;
        }
    }

    private SharedPreferences d() {
        return this.c.getSharedPreferences("DEVICE_SETTINGS_FILE", 0);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    private void e() {
        Map<String, Object> map = this.d().getString("DEVICE_SETTINGS_JSON", null);
        if (map == null) {
            return;
        }
        try {
            map = this.a(((JsonNode)JsonUtils.a().readValue((String)((Object)map), JsonNode.class)).get("data"));
            if (map == null) return;
            // MONITORENTER : this
            this.f = map;
        }
        catch (Exception exception) {
            Log.d(a, "restoreSettings - exception thrown restoring device settings.", exception);
            return;
        }
        Log.b(a, "restoreSettings - done restoring settings. Settings contains " + this.f.size() + " items");
    }

    @Override
    public Integer a(String object) {
        Log.b(a, "getDeviceSettingValue - called with settingId: " + (String)object);
        if (this.f == null) {
            Log.d(a, "getDeviceSettingValue - mSettings is null.");
            return null;
        }
        if (this.f.size() == 0) {
            Log.c(a, "getDeviceSettingValue - mSettings is empty.");
            return null;
        }
        if (!this.f.containsKey(object)) {
            Log.c(a, "getDeviceSettingValue - mSettings does not contain key " + (String)object);
            return null;
        }
        try {
            object = (Integer)this.f.get(object);
            return object;
        }
        catch (Exception exception) {
            Log.d(a, "getDeviceSettingValue - exception thrown casting value.", exception);
            return null;
        }
    }

    @Override
    public void a() {
        this.a(false);
    }

}

