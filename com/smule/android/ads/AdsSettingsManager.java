package com.smule.android.ads;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.managers.AppSettingsManager;
import com.smule.android.utils.JsonUtils;
import java.util.HashMap;
import java.util.Map;

public class AdsSettingsManager {
    public static final String f15600a = AdsSettingsManager.class.getName();
    private static AdsSettingsManager f15601e;
    Map<String, AdSettings> f15602b;
    Map<String, AdEventCounter> f15603c = new HashMap();
    AdSettings f15604d = new AdSettings(0, 5);

    class C34881 extends TypeReference<Map<String, AdSettings>> {
        final /* synthetic */ AdsSettingsManager f15594a;

        C34881(AdsSettingsManager adsSettingsManager) {
            this.f15594a = adsSettingsManager;
        }
    }

    class AdEventCounter {
        String f15595a;
        int f15596b;
        int f15597c;
        final /* synthetic */ AdsSettingsManager f15598d;
        private final String f15599e = "-attempts";

        public AdEventCounter(AdsSettingsManager adsSettingsManager, String str) {
            this.f15598d = adsSettingsManager;
            this.f15595a = str;
            this.f15596b = adsSettingsManager.m17425d(str).mFrequency;
            this.f15597c = adsSettingsManager.m17420b().getInt(m17414c(), 0);
        }

        private String m17414c() {
            return this.f15595a + "-attempts";
        }

        public void m17416a() {
            this.f15597c = 0;
            m17415d();
        }

        public boolean m17417b() {
            boolean z = true;
            this.f15597c++;
            m17415d();
            if (this.f15596b < 1) {
                Log.e(AdsSettingsManager.f15600a, "Frequency " + this.f15596b + " was invalid for " + this.f15595a);
                return false;
            }
            if (this.f15597c < this.f15596b) {
                z = false;
            }
            return z;
        }

        private void m17415d() {
            Editor edit = this.f15598d.m17420b().edit();
            edit.putInt(m17414c(), this.f15597c);
            edit.apply();
        }
    }

    public static AdsSettingsManager m17419a() {
        if (f15601e == null) {
            f15601e = new AdsSettingsManager();
        }
        return f15601e;
    }

    public void m17422a(String str) {
        if (this.f15602b == null) {
            JsonNode a = AppSettingsManager.a().a(str + ".ads", "zones", null);
            if (a != null) {
                try {
                    this.f15602b = JsonUtils.m18993b(a, new C34881(this));
                } catch (Exception e) {
                }
            }
        }
    }

    public boolean m17423b(String str) {
        return m17421e(str).m17417b();
    }

    public void m17424c(String str) {
        m17421e(str).m17416a();
    }

    public AdSettings m17425d(String str) {
        AdSettings adSettings = this.f15602b != null ? (AdSettings) this.f15602b.get(str) : null;
        if (adSettings == null) {
            return this.f15604d;
        }
        return adSettings;
    }

    private AdEventCounter m17421e(String str) {
        AdEventCounter adEventCounter = this.f15603c != null ? (AdEventCounter) this.f15603c.get(str) : null;
        if (adEventCounter == null) {
            return new AdEventCounter(this, str);
        }
        return adEventCounter;
    }

    private SharedPreferences m17420b() {
        return MagicNetwork.d().getApplicationContext().getSharedPreferences("ADS_SETTINGS", 0);
    }
}
