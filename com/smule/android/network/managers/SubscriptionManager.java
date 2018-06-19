package com.smule.android.network.managers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.PointerIconCompat;
import android.text.TextUtils;
import com.facebook.appevents.AppEventsLogger;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.smule.android.ads.attribution.AdjustAttributionSettings;
import com.smule.android.logging.Log;
import com.smule.android.network.api.SubscriptionAPI;
import com.smule.android.network.api.SubscriptionAPI.ReportSubscriptionRequest;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.ServerException;
import com.smule.android.network.core.SnpRequest;
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
import java.util.concurrent.Executors;

public class SubscriptionManager implements SubscriptionCheckerTask$SubscriptionCheckerListener {
    private static final String f6823a = SubscriptionManager.class.getName();
    private static SubscriptionManager f6824b;
    private Context f6825c;
    private SubscriptionAPI f6826d = ((SubscriptionAPI) MagicNetwork.m7789a().m7817a(SubscriptionAPI.class));
    private List<SubscriptionPack> f6827e;
    private boolean f6828f = true;
    private boolean f6829g = false;
    private String f6830h;
    private long f6831i = 0;
    private String f6832j = "subscriptions";
    private String f6833k = null;
    private long f6834l = 0;
    private List<BadSubscription> f6835m = new ArrayList();

    public static synchronized SubscriptionManager m8066a() {
        SubscriptionManager subscriptionManager;
        synchronized (SubscriptionManager.class) {
            if (f6824b == null) {
                f6824b = new SubscriptionManager();
            }
            subscriptionManager = f6824b;
        }
        return subscriptionManager;
    }

    public void m8081a(Context context) {
        this.f6825c = context;
        NotificationCenter.a().a("APP_SETTINGS_LOADED_EVENT", new 1(this));
        NotificationCenter.a().a("SUBSCRIPTION_PURCHASED", new 2(this));
        Observer 3 = new 3(this);
        NotificationCenter.a().a("USER_LOGGED_IN_EVENT", 3);
        NotificationCenter.a().a("USER_RE_LOGGED_IN_EVENT", 3);
        m8075k();
        m8079o();
    }

    public void m8083a(String str) {
        this.f6833k = str;
    }

    private SubscriptionManager() {
    }

    public boolean m8087b() {
        boolean z;
        boolean z2 = false;
        boolean z3 = this.f6831i != 0;
        if (((float) System.currentTimeMillis()) / 1000.0f < ((float) this.f6831i)) {
            z = true;
        } else {
            z = false;
        }
        if (z3 && !z) {
            z2 = true;
        }
        if (z2 && this.f6828f && !this.f6829g) {
            Log.m7772c(f6823a, "subscription expired, updating status...");
            this.f6829g = true;
            m8091f();
        }
        return z;
    }

    public boolean m8088c() {
        if (this.f6827e == null) {
            return false;
        }
        for (SubscriptionPack subscriptionPack : this.f6827e) {
            if (subscriptionPack.trial) {
                return true;
            }
        }
        return false;
    }

    public boolean m8089d() {
        return m8087b() && "TRIAL".equalsIgnoreCase(this.f6830h);
    }

    public void m8084a(String str, int i, String str2) {
        SubscriptionPack b = m8086b(str);
        if (b != null) {
            b.f6896a = i;
            b.f6897b = str2;
        }
    }

    public SubscriptionPack m8086b(String str) {
        for (SubscriptionPack subscriptionPack : m8090e()) {
            if (subscriptionPack.sku.equals(str)) {
                return subscriptionPack;
            }
        }
        return null;
    }

    public List<SubscriptionPack> m8090e() {
        if (this.f6827e == null || this.f6827e.size() == 0) {
            m8074j();
        }
        return this.f6827e;
    }

    private boolean m8074j() {
        String a = AppSettingsManager.m7855a().m7876a(MagicNetwork.m7804d().getSettingsAppName() + "." + this.f6832j, "definitions", this.f6833k);
        if (a == null || a.length() == 0) {
            Log.m7770b(f6823a, "No definition value configured for A/B group subscriptions");
            return false;
        }
        this.f6827e = new ArrayList();
        try {
            Object obj = (m8077m() || m8093h()) ? null : 1;
            JsonNode jsonNode = (JsonNode) JsonUtils.a().readValue(a, JsonNode.class);
            if (jsonNode != null) {
                Iterator it = jsonNode.iterator();
                while (it.hasNext()) {
                    SubscriptionPack subscriptionPack = (SubscriptionPack) JsonUtils.a().treeToValue((JsonNode) it.next(), SubscriptionPack.class);
                    if (obj == null) {
                        subscriptionPack.trial = false;
                    }
                    this.f6827e.add(subscriptionPack);
                    Log.m7770b(f6823a, "Subscription Pack:" + subscriptionPack.sku + " parsed from settings.");
                }
            }
            return true;
        } catch (JsonParseException e) {
            Log.m7776e(f6823a, "JSONParseException thrown parsing subscription packs JSON");
            return false;
        } catch (JsonMappingException e2) {
            Log.m7776e(f6823a, "JSONMappingException thrown parsing subscription packs JSON");
            return false;
        } catch (IOException e3) {
            Log.m7776e(f6823a, "IOException thrown parsing subscription packs JSON");
            return false;
        }
    }

    public void m8091f() {
        new SubscriptionCheckerTask(this).executeOnExecutor(Executors.newSingleThreadExecutor(), new Void[0]);
    }

    private void m8069b(SubscriptionStatusResponse subscriptionStatusResponse) {
        if (!(subscriptionStatusResponse == null || subscriptionStatusResponse.a == null)) {
            if (subscriptionStatusResponse.a.m7850c()) {
                this.f6830h = subscriptionStatusResponse.d;
                if (subscriptionStatusResponse.b != null) {
                    m8080a(subscriptionStatusResponse.c, this.f6830h, subscriptionStatusResponse.e);
                } else {
                    Log.m7772c(f6823a, "No subscription found for this player.");
                    m8080a(0, this.f6830h, subscriptionStatusResponse.e);
                }
                this.f6834l = SystemClock.elapsedRealtime();
            } else {
                m8080a(this.f6831i, this.f6830h, subscriptionStatusResponse.e);
            }
        }
        this.f6829g = false;
    }

    public void m8080a(long j, String str, boolean z) {
        this.f6831i = j;
        this.f6825c.getSharedPreferences("SUBSCRIPTION_PREFERENCES", 0).edit().putLong("PURCHASE_EXPIRATION_TIME", this.f6831i).putString("PURCHASE_STATUS", str).putBoolean("SKIP_TRIAL", z).apply();
        m8072c("SUBSCRIPTION_UPDATED");
        NotificationCenter.a().b("SUBSCRIPTION_UPDATED_NOTIFICATION", new Object[0]);
        Log.m7772c(f6823a, "Subscription updated with expiration time: " + this.f6831i);
        if (this.f6829g) {
            if (!m8087b()) {
                this.f6828f = false;
            }
            Log.m7772c(f6823a, "Expiration update completed, Will " + (this.f6828f ? "" : "not ") + "check status on expiration.");
        }
    }

    private void m8075k() {
        SharedPreferences sharedPreferences = this.f6825c.getSharedPreferences("SUBSCRIPTION_PREFERENCES", 0);
        this.f6831i = sharedPreferences.getLong("PURCHASE_EXPIRATION_TIME", 0);
        this.f6830h = sharedPreferences.getString("PURCHASE_STATUS", null);
    }

    public void mo4040a(SubscriptionStatusResponse subscriptionStatusResponse) {
        m8069b(subscriptionStatusResponse);
    }

    public SubscriptionStatusResponse m8092g() throws ServerException, IOException {
        return new SubscriptionStatusResponse(this, NetworkUtils.a(this.f6826d.fetchSubscriptionStatus(new SnpRequest())));
    }

    public boolean m8093h() {
        return this.f6825c.getSharedPreferences("SUBSCRIPTION_PREFERENCES", 0).getBoolean("SKIP_TRIAL", false);
    }

    private void m8076l() {
        this.f6825c.getSharedPreferences("SUBSCRIPTION_PREFERENCES", 0).edit().putBoolean("PURCHASE_SUBSCRIPTION", true).apply();
    }

    private boolean m8077m() {
        return this.f6825c.getSharedPreferences("SUBSCRIPTION_PREFERENCES", 0).getBoolean("PURCHASE_SUBSCRIPTION", false);
    }

    public boolean m8085a(String str, String str2, long j, String str3) {
        NetworkResponse c = m8071c(str, str3);
        if (c == null) {
            return false;
        }
        m8076l();
        SubscriptionStatusResponse subscriptionStatusResponse = new SubscriptionStatusResponse(this, c);
        for (SubscriptionPack subscriptionPack : this.f6827e) {
            if (subscriptionPack.sku.equals(str)) {
                this.f6830h = subscriptionPack.trial ? "TRIAL" : "PAID";
            }
        }
        m8080a(subscriptionStatusResponse.c, this.f6830h, subscriptionStatusResponse.e);
        SubscriptionPack subscriptionPack2 = m8086b(str);
        if (!(subscriptionPack2 == null || subscriptionPack2.f6897b == null)) {
            float f = ((float) subscriptionPack2.f6896a) / 1000000.0f;
            Log.m7770b(f6823a, "Logging FBAppEvent.purchase(" + f + " " + subscriptionPack2.f6897b + ")");
            AppEventsLogger.newLogger(this.f6825c).logPurchase(new BigDecimal((double) f), Currency.getInstance(subscriptionPack2.f6897b));
        }
        m8078n();
        return true;
    }

    private void m8078n() {
        SharedPreferences sharedPreferences = this.f6825c.getSharedPreferences("SUBSCRIPTION_PREFERENCES", 0);
        if (!sharedPreferences.getBoolean("SUBSCRIPTION_ADJUST_ATTRIBUTION_LOGGED", false)) {
            AdjustAttributionSettings.m7655f();
            sharedPreferences.edit().putBoolean("SUBSCRIPTION_ADJUST_ATTRIBUTION_LOGGED", true).apply();
        }
    }

    private void m8079o() {
        Object string = this.f6825c.getSharedPreferences("SUBSCRIPTION_PREFERENCES", 0).getString("BAD_SUBSCRIPTIONS", "");
        if (!TextUtils.isEmpty(string)) {
            this.f6835m = JsonUtils.a(string, new 5(this));
        }
    }

    private void m8067a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            Log.m7776e(f6823a, "attempt to save invalid sub:" + str + " " + str2);
            return;
        }
        BadSubscription badSubscription = new BadSubscription();
        badSubscription.a = str;
        badSubscription.b = str2;
        this.f6835m.add(badSubscription);
        Object a = JsonUtils.a(this.f6835m);
        if (!TextUtils.isEmpty(a)) {
            this.f6825c.getSharedPreferences("SUBSCRIPTION_PREFERENCES", 0).edit().putString("BAD_SUBSCRIPTIONS", a).apply();
        }
    }

    private boolean m8070b(String str, String str2) {
        if (this.f6835m == null || this.f6835m.isEmpty()) {
            return false;
        }
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        for (BadSubscription badSubscription : this.f6835m) {
            if (!TextUtils.isEmpty(badSubscription.a) && !TextUtils.isEmpty(badSubscription.b) && badSubscription.a.equals(str) && badSubscription.b.equals(str2)) {
                return true;
            }
        }
        return false;
    }

    private NetworkResponse m8071c(String str, String str2) {
        Log.m7772c(f6823a, "reporting subscription: " + str2);
        if (m8070b(str, str2)) {
            Log.m7772c(f6823a, "already tagged as bad");
            return null;
        }
        for (int i = 0; i < 4; i++) {
            if (i > 0) {
                Log.m7774d(f6823a, "Re-trying the subscription report API call to server. Try #" + (i + 1));
            }
            NetworkResponse a = NetworkUtils.a(this.f6826d.reportSubscription(new ReportSubscriptionRequest().setSku(str).setReceipt(str2)));
            if (a == null) {
                try {
                    Log.m7776e(f6823a, "Error updating subscription, retrying");
                    Thread.sleep(600);
                } catch (InterruptedException e) {
                    Log.m7776e(f6823a, "InterruptedException!" + e.toString());
                }
            } else if (a.f6756b == PointerIconCompat.TYPE_ALL_SCROLL) {
                Log.m7776e(f6823a, "bad subscription: " + a.f6764j);
                m8067a(str, str2);
                break;
            } else if (a.f6756b == 1005 || a.f6756b == 10) {
                Log.m7776e(f6823a, "Error updating subscription, retrying: " + a.f6764j);
            } else if (a.f6756b == 0) {
                Log.m7776e(f6823a, "Subscription report succeeded.  Persisting subscription.");
                return a;
            } else {
                MagicNetwork.m7795a(a);
                Log.m7776e(f6823a, "Error updating subscription, retrying");
            }
        }
        Log.m7776e(f6823a, "report subscription failed for:" + str + " receipt: " + str2);
        return null;
    }

    @Deprecated
    private void m8072c(String str) {
        Intent intent = new Intent();
        intent.setAction(str);
        LocalBroadcastManager.getInstance(this.f6825c).sendBroadcast(intent);
        NotificationCenter.a().b(str, new Object[0]);
    }
}
