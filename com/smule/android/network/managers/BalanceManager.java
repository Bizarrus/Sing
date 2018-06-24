/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.support.v4.content.LocalBroadcastManager
 *  retrofit2.Call
 */
package com.smule.android.network.managers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.LocalBroadcastManager;
import com.smule.android.logging.Log;
import com.smule.android.network.api.BalanceAPI;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.SnpRequest;
import com.smule.android.network.managers.BalanceManager;
import com.smule.android.utils.NotificationCenter;
import java.util.Observer;
import retrofit2.Call;

public class BalanceManager {
    private static final String a = BalanceManager.class.getName();
    private static BalanceManager b;
    private BalanceAPI c = MagicNetwork.a().a(BalanceAPI.class);
    private Context d;
    private int e;
    private volatile long f = 0;

    private BalanceManager() {
    }

    public static BalanceManager a() {
        synchronized (BalanceManager.class) {
            if (b == null) {
                b = new BalanceManager();
            }
            BalanceManager balanceManager = b;
            return balanceManager;
        }
    }

    static /* synthetic */ void a(BalanceManager balanceManager, boolean bl) {
        balanceManager.a(bl);
    }

    private void a(String string2) {
        Intent intent = new Intent();
        intent.setAction(string2);
        LocalBroadcastManager.getInstance((Context)this.d).sendBroadcast(intent);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void a(boolean bl) {
        synchronized (this) {
            if (!bl || (bl = this.d())) {
                block10 : {
                    block9 : {
                        try {
                            int n = this.e();
                            SharedPreferences sharedPreferences = this.d.getSharedPreferences(MagicNetwork.d().getPreferencesFileName(), 0);
                            if (n >= 0) {
                                sharedPreferences.edit().putInt("credits", n).apply();
                            } else {
                                n = sharedPreferences.getInt("credits", 0);
                            }
                            if (n == this.e) break block9;
                            this.e = n;
                            NotificationCenter.a().b("BALANCE_UPDATE_EVENT", new Object[0]);
                            this.a("BALANCE_UPDATE_EVENT");
                            break block10;
                        }
                        catch (Exception exception) {
                            Log.b(a, "Problem retrieving balance", exception);
                        }
                    }
                    NotificationCenter.a().b("BALANCE_SAME_EVENT", new Object[0]);
                    this.a("BALANCE_SAME_EVENT");
                }
                this.f = System.currentTimeMillis();
            }
            return;
        }
    }

    static /* synthetic */ String c() {
        return a;
    }

    private boolean d() {
        if (System.currentTimeMillis() > this.f + 300000) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    private int e() {
        NetworkResponse networkResponse;
        if (!NetworkUtils.a(this.d) || (networkResponse = NetworkUtils.a(this.c.getBalance(new SnpRequest()))) == null || !networkResponse.c()) {
            return -1;
        }
        return networkResponse.a("amount", -1);
    }

    public void a(Context object) {
        this.d = object;
        this.e = this.d.getSharedPreferences(MagicNetwork.d().getPreferencesFileName(), 0).getInt("credits", 0);
        object = new Observer(this){
            final /* synthetic */ BalanceManager a;
            {
                this.a = balanceManager;
            }

            public void update(java.util.Observable observable, Object object) {
                if ("USER_EXISTENCE_TYPE_EXISTING".equals((String)object)) {
                    Log.c(BalanceManager.c(), "user logged into existing account. Updating balance.");
                    this.a.b();
                }
            }
        };
        NotificationCenter.a().a("USER_LOGGED_IN_EVENT", (Observer)object);
    }

    public void a(Runnable runnable, boolean bl) {
        MagicNetwork.a(new Runnable(this, bl, runnable){
            final /* synthetic */ boolean a;
            final /* synthetic */ Runnable b;
            final /* synthetic */ BalanceManager c;
            {
                this.c = balanceManager;
                this.a = bl;
                this.b = runnable;
            }

            public void run() {
                BalanceManager.a(this.c, this.a);
                if (this.b != null) {
                    this.b.run();
                }
            }
        });
    }

    public void b() {
        this.a(null, false);
    }
}

