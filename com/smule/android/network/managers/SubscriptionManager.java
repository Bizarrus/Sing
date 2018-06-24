/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.os.AsyncTask
 *  android.os.SystemClock
 *  android.support.v4.content.LocalBroadcastManager
 *  android.text.TextUtils
 *  com.facebook.appevents.AppEventsLogger
 *  com.fasterxml.jackson.core.JsonParseException
 *  com.fasterxml.jackson.core.TreeNode
 *  com.fasterxml.jackson.core.type.TypeReference
 *  com.fasterxml.jackson.databind.JsonMappingException
 *  com.fasterxml.jackson.databind.JsonNode
 *  retrofit2.Call
 */
package com.smule.android.network.managers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.facebook.appevents.AppEventsLogger;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.smule.android.ads.attribution.AdjustAttributionSettings;
import com.smule.android.logging.Log;
import com.smule.android.network.api.SubscriptionAPI;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.ServerException;
import com.smule.android.network.core.SnpRequest;
import com.smule.android.network.managers.AppSettingsManager;
import com.smule.android.network.managers.SubscriptionCheckerTask;
import com.smule.android.network.managers.SubscriptionManager;
import com.smule.android.network.models.SubscriptionPack;
import com.smule.android.utils.JsonUtils;
import com.smule.android.utils.NotificationCenter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Iterator;
import java.util.List;
import java.util.Observer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import retrofit2.Call;

public class SubscriptionManager
implements SubscriptionCheckerTask {
    private static final String a = SubscriptionManager.class.getName();
    private static SubscriptionManager b;
    private Context c;
    private com.smule.android.network.api.SubscriptionAPI d = MagicNetwork.a().a(com.smule.android.network.api.SubscriptionAPI.class);
    private List<SubscriptionPack> e;
    private boolean f = true;
    private boolean g = false;
    private String h;
    private long i = 0;
    private String j = "subscriptions";
    private String k = null;
    private long l = 0;
    private List<> m = new ArrayList<>();

    private SubscriptionManager() {
    }

    static /* synthetic */ long a(SubscriptionManager subscriptionManager, long l) {
        subscriptionManager.l = l;
        return l;
    }

    public static SubscriptionManager a() {
        synchronized (SubscriptionManager.class) {
            if (b == null) {
                b = new SubscriptionManager();
            }
            SubscriptionManager subscriptionManager = b;
            return subscriptionManager;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(String string2, String string3) {
        if (TextUtils.isEmpty((CharSequence)string2) || TextUtils.isEmpty((CharSequence)string3)) {
            Log.e(a, "attempt to save invalid sub:" + string2 + " " + string3);
            return;
        } else {
            Object object = new Object(){
                public String a;
                public String b;
            };
            object.a = string2;
            object.b = string3;
            this.m.add(object);
            string2 = JsonUtils.a(this.m);
            if (TextUtils.isEmpty((CharSequence)string2)) return;
            {
                this.c.getSharedPreferences("SUBSCRIPTION_PREFERENCES", 0).edit().putString("BAD_SUBSCRIPTIONS", string2).apply();
                return;
            }
        }
    }

    static /* synthetic */ boolean a(SubscriptionManager subscriptionManager) {
        return subscriptionManager.j();
    }

    /*
     * Enabled aggressive block sorting
     */
    private void b( subscriptionStatusResponse) {
        if (subscriptionStatusResponse != null && subscriptionStatusResponse.a != null) {
            if (subscriptionStatusResponse.a.c()) {
                this.h = subscriptionStatusResponse.d;
                if (subscriptionStatusResponse.b != null) {
                    this.a(subscriptionStatusResponse.c, this.h, subscriptionStatusResponse.e);
                } else {
                    Log.c(a, "No subscription found for this player.");
                    this.a(0, this.h, subscriptionStatusResponse.e);
                }
                this.l = SystemClock.elapsedRealtime();
            } else {
                this.a(this.i, this.h, subscriptionStatusResponse.e);
            }
        }
        this.g = false;
    }

    private boolean b(String string2, String string3) {
        if (this.m == null || this.m.isEmpty()) {
            return false;
        }
        if (TextUtils.isEmpty((CharSequence)string2) || TextUtils.isEmpty((CharSequence)string3)) {
            return false;
        }
        for ( badSubscription : this.m) {
            if (TextUtils.isEmpty((CharSequence)badSubscription.a) || TextUtils.isEmpty((CharSequence)badSubscription.b) || !badSubscription.a.equals(string2) || !badSubscription.b.equals(string3)) continue;
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private NetworkResponse c(String string2, String string3) {
        Log.c(a, "reporting subscription: " + string3);
        if (this.b(string2, string3)) {
            Log.c(a, "already tagged as bad");
            return null;
        }
        int n = 0;
        do {
            block14 : {
                block12 : {
                    NetworkResponse networkResponse;
                    block13 : {
                        block11 : {
                            if (n >= 4) break block11;
                            if (n > 0) {
                                Log.d(a, "Re-trying the subscription report API call to server. Try #" + (n + 1));
                            }
                            if ((networkResponse = NetworkUtils.a(this.d.reportSubscription(new SnpRequest(){
                                public String receipt;
                                public String sku;

                                public SubscriptionAPI setReceipt(String string2) {
                                    this.receipt = "{\"orders\":[" + string2 + "]}";
                                    return this;
                                }

                                public SubscriptionAPI setSku(String string2) {
                                    this.sku = string2;
                                    return this;
                                }
                            }.setSku(string2).setReceipt(string3)))) == null) break block12;
                            if (networkResponse.b != 1013) break block13;
                            Log.e(a, "bad subscription: " + networkResponse.j);
                            this.a(string2, string3);
                        }
                        Log.e(a, "report subscription failed for:" + string2 + " receipt: " + string3);
                        return null;
                    }
                    if (networkResponse.b == 1005 || networkResponse.b == 10) {
                        Log.e(a, "Error updating subscription, retrying: " + networkResponse.j);
                    } else {
                        if (networkResponse.b == 0) {
                            Log.e(a, "Subscription report succeeded.  Persisting subscription.");
                            return networkResponse;
                        }
                        MagicNetwork.a(networkResponse);
                        Log.e(a, "Error updating subscription, retrying");
                    }
                    break block14;
                }
                try {
                    Log.e(a, "Error updating subscription, retrying");
                    Thread.sleep(600);
                }
                catch (InterruptedException interruptedException) {
                    Log.e(a, "InterruptedException!" + interruptedException.toString());
                }
            }
            ++n;
        } while (true);
    }

    @Deprecated
    private void c(String string2) {
        Intent intent = new Intent();
        intent.setAction(string2);
        LocalBroadcastManager.getInstance((Context)this.c).sendBroadcast(intent);
        NotificationCenter.a().b(string2, new Object[0]);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private boolean j() {
        Object object = AppSettingsManager.a().a(MagicNetwork.d().getSettingsAppName() + "." + this.j, "definitions", this.k);
        if (object == null || object.length() == 0) {
            Log.b(a, "No definition value configured for A/B group subscriptions");
            return false;
        }
        this.e = new ArrayList<SubscriptionPack>();
        try {
            boolean bl = !this.m() && !this.h();
            object = (JsonNode)JsonUtils.a().readValue((String)object, JsonNode.class);
            if (object == null) return true;
            object = object.iterator();
            while (object.hasNext()) {
                Object object2 = (JsonNode)object.next();
                object2 = (SubscriptionPack)JsonUtils.a().treeToValue((TreeNode)object2, SubscriptionPack.class);
                if (!bl) {
                    object2.trial = false;
                }
                this.e.add((SubscriptionPack)object2);
                Log.b(a, "Subscription Pack:" + object2.sku + " parsed from settings.");
            }
            return true;
        }
        catch (JsonParseException jsonParseException) {
            Log.e(a, "JSONParseException thrown parsing subscription packs JSON");
            return false;
        }
        catch (JsonMappingException jsonMappingException) {
            Log.e(a, "JSONMappingException thrown parsing subscription packs JSON");
            return false;
        }
        catch (IOException iOException) {
            Log.e(a, "IOException thrown parsing subscription packs JSON");
            return false;
        }
    }

    private void k() {
        SharedPreferences sharedPreferences = this.c.getSharedPreferences("SUBSCRIPTION_PREFERENCES", 0);
        this.i = sharedPreferences.getLong("PURCHASE_EXPIRATION_TIME", 0);
        this.h = sharedPreferences.getString("PURCHASE_STATUS", null);
    }

    private void l() {
        this.c.getSharedPreferences("SUBSCRIPTION_PREFERENCES", 0).edit().putBoolean("PURCHASE_SUBSCRIPTION", true).apply();
    }

    private boolean m() {
        return this.c.getSharedPreferences("SUBSCRIPTION_PREFERENCES", 0).getBoolean("PURCHASE_SUBSCRIPTION", false);
    }

    private void n() {
        SharedPreferences sharedPreferences = this.c.getSharedPreferences("SUBSCRIPTION_PREFERENCES", 0);
        if (!sharedPreferences.getBoolean("SUBSCRIPTION_ADJUST_ATTRIBUTION_LOGGED", false)) {
            AdjustAttributionSettings.h();
            sharedPreferences.edit().putBoolean("SUBSCRIPTION_ADJUST_ATTRIBUTION_LOGGED", true).apply();
        }
    }

    private void o() {
        String string2 = this.c.getSharedPreferences("SUBSCRIPTION_PREFERENCES", 0).getString("BAD_SUBSCRIPTIONS", "");
        if (TextUtils.isEmpty((CharSequence)string2)) {
            return;
        }
        this.m = JsonUtils.a(string2, new TypeReference<List<>>(this){
            final /* synthetic */ SubscriptionManager a;
            {
                this.a = subscriptionManager;
            }
        });
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(long l, String string2, boolean bl) {
        this.i = l;
        this.c.getSharedPreferences("SUBSCRIPTION_PREFERENCES", 0).edit().putLong("PURCHASE_EXPIRATION_TIME", this.i).putString("PURCHASE_STATUS", string2).putBoolean("SKIP_TRIAL", bl).apply();
        this.c("SUBSCRIPTION_UPDATED");
        NotificationCenter.a().b("SUBSCRIPTION_UPDATED_NOTIFICATION", new Object[0]);
        Log.c(a, "Subscription updated with expiration time: " + this.i);
        if (this.g) {
            if (!this.b()) {
                this.f = false;
            }
            String string3 = a;
            StringBuilder stringBuilder = new StringBuilder().append("Expiration update completed, Will ");
            string2 = this.f ? "" : "not ";
            Log.c(string3, stringBuilder.append(string2).append("check status on expiration.").toString());
        }
    }

    public void a(Context object) {
        this.c = object;
        object = new Observer(this){
            final /* synthetic */ SubscriptionManager a;
            {
                this.a = subscriptionManager;
            }

            public void update(java.util.Observable observable, Object object) {
                SubscriptionManager.a(this.a);
            }
        };
        NotificationCenter.a().a("APP_SETTINGS_LOADED_EVENT", (Observer)object);
        object = new Observer(this){
            final /* synthetic */ SubscriptionManager a;
            {
                this.a = subscriptionManager;
            }

            public void update(java.util.Observable observable, Object object) {
                SubscriptionManager.a(this.a, SystemClock.elapsedRealtime());
            }
        };
        NotificationCenter.a().a("SUBSCRIPTION_PURCHASED", (Observer)object);
        object = new Observer(this){
            final /* synthetic */ SubscriptionManager a;
            {
                this.a = subscriptionManager;
            }

            /*
             * Enabled aggressive block sorting
             */
            public void update(java.util.Observable observable, Object object) {
                boolean bl = object instanceof Boolean && (Boolean)object != false;
                if (MagicNetwork.d().supportsGuestSubscriptions()) return;
                if (com.smule.android.network.managers.UserManager.a().y() && !bl) {
                    SubscriptionManager.a().f();
                }
            }
        };
        NotificationCenter.a().a("USER_LOGGED_IN_EVENT", (Observer)object);
        NotificationCenter.a().a("USER_RE_LOGGED_IN_EVENT", (Observer)object);
        this.k();
        this.o();
    }

    @Override
    public void a( subscriptionStatusResponse) {
        this.b(subscriptionStatusResponse);
    }

    public void a(String string2) {
        this.k = string2;
    }

    public void a(String object, int n, String string2) {
        if ((object = this.b((String)object)) != null) {
            object.a = n;
            object.b = string2;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean a(String object, String object2, long l, String object3) {
        object2 = this.c((String)object, (String)object3);
        if (object2 == null) {
            return false;
        }
        this.l();
        object3 = new Object((NetworkResponse)((Object)object2)){
            final NetworkResponse a;
            Boolean b;
            long c;
            String d;
            boolean e;
            {
                this.a = networkResponse;
                if (this.a == null) return;
                if (this.a.j == null) return;
                try {
                    SubscriptionManager.this = (JsonNode)JsonUtils.a().readValue(this.a.j, JsonNode.class);
                    if (!SubscriptionManager.this.has("data")) return;
                    if ((subscriptionManager = SubscriptionManager.this.get("data")).has("isActive")) {
                        this.b = SubscriptionManager.this.get("isActive").booleanValue();
                    }
                    if (SubscriptionManager.this.has("expireAt")) {
                        this.c = SubscriptionManager.this.get("expireAt").longValue();
                    }
                    if (SubscriptionManager.this.has("status")) {
                        this.d = SubscriptionManager.this.get("status").textValue();
                    }
                    boolean bl = SubscriptionManager.this.has("skipTrial") && SubscriptionManager.this.get("skipTrial").booleanValue();
                    this.e = bl;
                    return;
                }
                catch (Exception exception) {
                    Log.e(a, "Error parsing SubscriptionStatusResponse!");
                    return;
                }
            }
        };
        Iterator<SubscriptionPack> iterator = this.e.iterator();
        while (iterator.hasNext()) {
            object2 = iterator.next();
            if (!object2.sku.equals(object)) continue;
            object2 = object2.trial ? "TRIAL" : "PAID";
            this.h = object2;
        }
        this.a(object3.c, this.h, object3.e);
        object = this.b((String)object);
        if (object != null && object.b != null) {
            float f = (float)object.a / 1000000.0f;
            Log.b(a, "Logging FBAppEvent.purchase(" + f + " " + object.b + ")");
            AppEventsLogger.newLogger((Context)this.c).logPurchase(new BigDecimal(f), Currency.getInstance(object.b));
        }
        this.n();
        return true;
    }

    public SubscriptionPack b(String string2) {
        for (SubscriptionPack subscriptionPack : this.e()) {
            if (!subscriptionPack.sku.equals(string2)) continue;
            return subscriptionPack;
        }
        return null;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean b() {
        boolean bl = false;
        boolean bl2 = this.i != 0;
        boolean bl3 = (float)System.currentTimeMillis() / 1000.0f < (float)this.i;
        boolean bl4 = bl;
        if (bl2) {
            bl4 = bl;
            if (!bl3) {
                bl4 = true;
            }
        }
        if (bl4) {
            if (!this.f) return bl3;
            if (!this.g) {
                Log.c(a, "subscription expired, updating status...");
                this.g = true;
                this.f();
            }
        }
        return bl3;
    }

    public boolean c() {
        if (this.e == null) {
            return false;
        }
        Iterator<SubscriptionPack> iterator = this.e.iterator();
        while (iterator.hasNext()) {
            if (!iterator.next().trial) continue;
            return true;
        }
        return false;
    }

    public boolean d() {
        if (this.b() && "TRIAL".equalsIgnoreCase(this.h)) {
            return true;
        }
        return false;
    }

    public List<SubscriptionPack> e() {
        if (this.e == null || this.e.size() == 0) {
            this.j();
        }
        return this.e;
    }

    public void f() {
        new SubscriptionCheckerTask(this).executeOnExecutor((Executor)Executors.newSingleThreadExecutor(), (Object[])new Void[0]);
    }

    public  g() throws ServerException, IOException {
        return new ;
    }

    public boolean h() {
        return this.c.getSharedPreferences("SUBSCRIPTION_PREFERENCES", 0).getBoolean("SKIP_TRIAL", false);
    }

}

