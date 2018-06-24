/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 *  com.fasterxml.jackson.annotation.JsonProperty
 *  com.fasterxml.jackson.core.type.TypeReference
 *  com.fasterxml.jackson.databind.JsonNode
 */
package com.smule.android.ads;

import android.content.Context;
import android.content.SharedPreferences;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.managers.AppSettingsManager;
import com.smule.android.utils.JsonUtils;
import java.util.HashMap;
import java.util.Map;

public class AdsSettingsManager {
    public static final String a = AdsSettingsManager.class.getName();
    private static AdsSettingsManager e;
    Map<String, > b;
    Map<String, AdEventCounter> c = new HashMap<String, AdEventCounter>();
     d = new Object(0, 5){
        @JsonProperty(value="frequency")
        int mFrequency;
        @JsonProperty(value="timeout")
        int mTimeout = 5;
        {
            this.mFrequency = n;
            this.mTimeout = n2;
        }

        public int a() {
            return this.mTimeout * 1000;
        }
    };

    public static AdsSettingsManager a() {
        if (e == null) {
            e = new AdsSettingsManager();
        }
        return e;
    }

    private SharedPreferences b() {
        return MagicNetwork.d().getApplicationContext().getSharedPreferences("ADS_SETTINGS", 0);
    }

    /*
     * Enabled aggressive block sorting
     */
    private AdEventCounter e(String string2) {
        AdEventCounter adEventCounter = this.c != null ? this.c.get(string2) : null;
        AdEventCounter adEventCounter2 = adEventCounter;
        if (adEventCounter != null) return adEventCounter2;
        return new AdEventCounter(string2);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a(String string2) {
        if (this.b != null || (string2 = AppSettingsManager.a().a(string2 + ".ads", "zones", (JsonNode)null)) == null) {
            return;
        }
        try {
            this.b = JsonUtils.b((JsonNode)string2, new TypeReference<Map<String, >>(){});
            return;
        }
        catch (Exception exception) {
            return;
        }
    }

    public boolean b(String string2) {
        return this.e(string2).b();
    }

    public void c(String string2) {
        this.e(string2).a();
    }

    /*
     * Enabled aggressive block sorting
     */
    public  d(String object) {
        object = this.b != null ? this.b.get(object) : null;
        Object object2 = object;
        if (object != null) return object2;
        return this.d;
    }

    class AdEventCounter {
        String a;
        int b;
        int c;
        private final String e;

        public AdEventCounter(String string2) {
            this.e = "-attempts";
            this.a = string2;
            this.b = AdsSettingsManager.this.d((String)string2).mFrequency;
            this.c = AdsSettingsManager.this.b().getInt(this.c(), 0);
        }

        private String c() {
            return this.a + "-attempts";
        }

        private void d() {
            SharedPreferences.Editor editor = AdsSettingsManager.this.b().edit();
            editor.putInt(this.c(), this.c);
            editor.apply();
        }

        public void a() {
            this.c = 0;
            this.d();
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public boolean b() {
            boolean bl = true;
            ++this.c;
            this.d();
            if (this.b < 1) {
                Log.e(AdsSettingsManager.a, "Frequency " + this.b + " was invalid for " + this.a);
                return false;
            }
            if (this.c < this.b) return false;
            return bl;
        }
    }

}

