package com.smule.android.network.managers;

import android.content.Context;
import android.content.SharedPreferences;
import com.facebook.share.internal.ShareConstants;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.smule.android.logging.Log;
import com.smule.android.network.api.DeviceSettingsAPI;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.SnpRequest;
import com.smule.android.utils.JsonUtils;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class DeviceSettingsManager {
    private static final String f16589a = DeviceSettingsManager.class.getName();
    private static DeviceSettingsManager f16590b;
    private Context f16591c;
    private long f16592d = 0;
    private Map<String, Object> f16593e;
    private DeviceSettingsAPI f16594f = ((DeviceSettingsAPI) MagicNetwork.a().a(DeviceSettingsAPI.class));
    private AtomicBoolean f16595g = new AtomicBoolean(false);

    class C35521 implements Runnable {
        final /* synthetic */ DeviceSettingsManager f16587a;

        C35521(DeviceSettingsManager deviceSettingsManager) {
            this.f16587a = deviceSettingsManager;
        }

        public void run() {
            NetworkResponse a = NetworkUtils.m18104a(this.f16587a.f16594f.getDeviceSettings(new SnpRequest()));
            if (a.c()) {
                this.f16587a.m18159d().edit().putString("DEVICE_SETTINGS_JSON", a.j).apply();
                Map a2 = this.f16587a.m18152a(a.l);
                if (a2 != null) {
                    Log.b(DeviceSettingsManager.f16589a, "refreshDeviceSettings - new settings contains " + a2.size() + " items");
                    synchronized (this) {
                        this.f16587a.f16593e = a2;
                        this.f16587a.f16592d = System.currentTimeMillis();
                    }
                } else {
                    Log.e(DeviceSettingsManager.f16589a, "refreshDeviceSettings - parsing new settings failed");
                }
            } else {
                Log.d(DeviceSettingsManager.f16589a, "refreshDeviceSettings - call to getDeviceSettings did not succeed");
            }
            this.f16587a.f16595g.set(false);
        }
    }

    class C35532 extends TypeReference<Map<String, Object>> {
        final /* synthetic */ DeviceSettingsManager f16588a;

        C35532(DeviceSettingsManager deviceSettingsManager) {
            this.f16588a = deviceSettingsManager;
        }
    }

    public static synchronized DeviceSettingsManager m18151a() {
        DeviceSettingsManager deviceSettingsManager;
        synchronized (DeviceSettingsManager.class) {
            if (f16590b == null) {
                f16590b = new DeviceSettingsManager();
                f16590b.f16591c = MagicNetwork.d().getApplicationContext();
                f16590b.m18160e();
            }
            deviceSettingsManager = f16590b;
        }
        return deviceSettingsManager;
    }

    private DeviceSettingsManager() {
    }

    private SharedPreferences m18159d() {
        return this.f16591c.getSharedPreferences("DEVICE_SETTINGS_FILE", 0);
    }

    private void m18160e() {
        String string = m18159d().getString("DEVICE_SETTINGS_JSON", null);
        if (string != null) {
            try {
                Map a = m18152a(((JsonNode) JsonUtils.m18984a().readValue(string, JsonNode.class)).get(ShareConstants.WEB_DIALOG_PARAM_DATA));
                if (a != null) {
                    synchronized (this) {
                        this.f16593e = a;
                    }
                    Log.b(f16589a, "restoreSettings - done restoring settings. Settings contains " + this.f16593e.size() + " items");
                }
            } catch (Throwable e) {
                Log.d(f16589a, "restoreSettings - exception thrown restoring device settings.", e);
            }
        }
    }

    public void m18162b() {
        m18155a(false);
    }

    private void m18155a(boolean z) {
        Log.b(f16589a, "refreshDeviceSettings - begin");
        if (!z && System.currentTimeMillis() < this.f16592d + 3600000) {
            Log.b(f16589a, "refreshDeviceSettings - we have updated recently; ignoring call to refresh");
        } else if (this.f16595g.getAndSet(true)) {
            Log.b(f16589a, "refreshDeviceSettings - pending");
        } else {
            MagicNetwork.a(new C35521(this));
        }
    }

    private Map<String, Object> m18152a(JsonNode jsonNode) {
        return JsonUtils.m18993b(jsonNode, new C35532(this));
    }

    public int m18161a(String str, int i) {
        Log.b(f16589a, "getDeviceSettingValue - called with settingId: " + str);
        if (this.f16593e == null) {
            Log.d(f16589a, "getDeviceSettingValue - mSettings is null. Returning default value provided.");
        } else if (this.f16593e.size() == 0) {
            Log.c(f16589a, "getDeviceSettingValue - mSettings is empty. Returning default value provided.");
        } else if (this.f16593e.containsKey(str)) {
            try {
                i = ((Integer) this.f16593e.get(str)).intValue();
            } catch (Exception e) {
                Log.e(f16589a, "getDeviceSettingValue - exception thrown casting value. Returning default value. " + e);
            }
        } else {
            Log.d(f16589a, "getDeviceSettingValue - mSettings does not contain key " + str + ". Returning default value provided.");
        }
        return i;
    }
}
