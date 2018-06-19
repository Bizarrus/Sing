package com.smule.android.network.managers;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import com.smule.android.network.api.BalanceAPI;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.SnpRequest;
import com.smule.android.utils.NotificationCenter;

public class BalanceManager {
    private static final String f6795a = BalanceManager.class.getName();
    private static BalanceManager f6796b;
    private BalanceAPI f6797c = ((BalanceAPI) MagicNetwork.m7789a().m7817a(BalanceAPI.class));
    private Context f6798d;
    private int f6799e;
    private volatile long f6800f = 0;

    public static synchronized BalanceManager m7927a() {
        BalanceManager balanceManager;
        synchronized (BalanceManager.class) {
            if (f6796b == null) {
                f6796b = new BalanceManager();
            }
            balanceManager = f6796b;
        }
        return balanceManager;
    }

    private BalanceManager() {
    }

    public void m7934a(Context context) {
        this.f6798d = context;
        this.f6799e = this.f6798d.getSharedPreferences(MagicNetwork.m7804d().getPreferencesFileName(), 0).getInt("credits", 0);
        NotificationCenter.a().a("USER_LOGGED_IN_EVENT", new 1(this));
    }

    public void m7936b() {
        m7935a(null, false);
    }

    public void m7935a(Runnable runnable, boolean z) {
        MagicNetwork.m7790a(new 3(this, z, runnable));
    }

    private boolean m7932d() {
        return System.currentTimeMillis() > this.f6800f + 300000;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void m7930a(boolean r5) {
        /*
        r4 = this;
        monitor-enter(r4);
        if (r5 == 0) goto L_0x000b;
    L_0x0003:
        r0 = r4.m7932d();	 Catch:{ all -> 0x0054 }
        if (r0 != 0) goto L_0x000b;
    L_0x0009:
        monitor-exit(r4);
        return;
    L_0x000b:
        r0 = r4.m7933e();	 Catch:{ Exception -> 0x004b }
        r1 = r4.f6798d;	 Catch:{ Exception -> 0x004b }
        r2 = com.smule.android.network.core.MagicNetwork.m7804d();	 Catch:{ Exception -> 0x004b }
        r2 = r2.getPreferencesFileName();	 Catch:{ Exception -> 0x004b }
        r3 = 0;
        r1 = r1.getSharedPreferences(r2, r3);	 Catch:{ Exception -> 0x004b }
        if (r0 < 0) goto L_0x0057;
    L_0x0020:
        r1 = r1.edit();	 Catch:{ Exception -> 0x004b }
        r2 = "credits";
        r1 = r1.putInt(r2, r0);	 Catch:{ Exception -> 0x004b }
        r1.apply();	 Catch:{ Exception -> 0x004b }
    L_0x002d:
        r1 = r4.f6799e;	 Catch:{ Exception -> 0x004b }
        if (r0 == r1) goto L_0x005f;
    L_0x0031:
        r4.f6799e = r0;	 Catch:{ Exception -> 0x004b }
        r0 = com.smule.android.utils.NotificationCenter.a();	 Catch:{ Exception -> 0x004b }
        r1 = "BALANCE_UPDATE_EVENT";
        r2 = 0;
        r2 = new java.lang.Object[r2];	 Catch:{ Exception -> 0x004b }
        r0.b(r1, r2);	 Catch:{ Exception -> 0x004b }
        r0 = "BALANCE_UPDATE_EVENT";
        r4.m7929a(r0);	 Catch:{ Exception -> 0x004b }
    L_0x0044:
        r0 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x004b }
        r4.f6800f = r0;	 Catch:{ Exception -> 0x004b }
        goto L_0x0009;
    L_0x004b:
        r0 = move-exception;
        r1 = f6795a;	 Catch:{ all -> 0x0054 }
        r2 = "Problem retrieving balance";
        com.smule.android.logging.Log.m7771b(r1, r2, r0);	 Catch:{ all -> 0x0054 }
        goto L_0x0009;
    L_0x0054:
        r0 = move-exception;
        monitor-exit(r4);
        throw r0;
    L_0x0057:
        r0 = "credits";
        r2 = 0;
        r0 = r1.getInt(r0, r2);	 Catch:{ Exception -> 0x004b }
        goto L_0x002d;
    L_0x005f:
        r0 = com.smule.android.utils.NotificationCenter.a();	 Catch:{ Exception -> 0x004b }
        r1 = "BALANCE_SAME_EVENT";
        r2 = 0;
        r2 = new java.lang.Object[r2];	 Catch:{ Exception -> 0x004b }
        r0.b(r1, r2);	 Catch:{ Exception -> 0x004b }
        r0 = "BALANCE_SAME_EVENT";
        r4.m7929a(r0);	 Catch:{ Exception -> 0x004b }
        goto L_0x0044;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.smule.android.network.managers.BalanceManager.a(boolean):void");
    }

    private void m7929a(String str) {
        Intent intent = new Intent();
        intent.setAction(str);
        LocalBroadcastManager.getInstance(this.f6798d).sendBroadcast(intent);
    }

    private int m7933e() {
        if (!NetworkUtils.a(this.f6798d)) {
            return -1;
        }
        NetworkResponse a = NetworkUtils.a(this.f6797c.getBalance(new SnpRequest()));
        if (a == null || !a.m7850c()) {
            return -1;
        }
        return a.m7844a("amount", -1);
    }
}
