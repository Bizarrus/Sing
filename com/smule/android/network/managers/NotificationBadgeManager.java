/*
 * Decompiled with CFR 0_123.
 */
package com.smule.android.network.managers;

import com.smule.android.utils.NotificationCenter;
import com.smule.android.utils.StringUtils;
import java.util.Map;

public class NotificationBadgeManager {
    private static NotificationBadgeManager a;
    private int b;
    private int c;

    public static NotificationBadgeManager a() {
        synchronized (NotificationBadgeManager.class) {
            if (a == null) {
                a = new NotificationBadgeManager();
            }
            NotificationBadgeManager notificationBadgeManager = a;
            return notificationBadgeManager;
        }
    }

    public void a(int n, int n2) {
        this.b = n;
        this.c = n2;
        NotificationCenter.a().b("NOTIFICATION_LOGIN_COUNT_RECEIVED_EVENT", new Object[0]);
    }

    public void a(Map<String, String> map) {
        this.b = StringUtils.b(map.get("ac"), 0);
        this.c = StringUtils.b(map.get("pc"), 0);
        NotificationCenter.a().b("NOTIFICATION_PUSH_COUNT_RECEIVED_EVENT", new Object[0]);
    }

    public int b() {
        return this.c;
    }

    public int c() {
        return this.b;
    }

    public void d() {
        this.b = 0;
    }

    public void e() {
        this.c = 0;
    }
}

