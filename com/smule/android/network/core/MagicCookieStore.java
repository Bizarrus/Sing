/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.text.TextUtils
 */
package com.smule.android.network.core;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.smule.android.logging.Log;
import com.smule.android.network.core.CookieStoreImpl;
import com.smule.android.network.core.MagicNetwork;
import java.io.Serializable;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

class MagicCookieStore
implements CookieStore {
    public static final String a = MagicCookieStore.class.getSimpleName();
    private CookieStoreImpl b = new CookieStoreImpl();
    private HttpCookie c;
    private String d;

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    MagicCookieStore() {
        List<HttpCookie> list;
        String string2 = MagicNetwork.e().getString("SNP_COOKIE", "");
        if (TextUtils.isEmpty((CharSequence)string2)) return;
        try {
            list = HttpCookie.parse(string2);
            if (list == null) return;
        }
        catch (IllegalArgumentException illegalArgumentException) {
            Log.b(a, "invalid:" + string2);
            return;
        }
        if (list.isEmpty()) return;
        this.c = list.get(0);
        this.a(this.c, string2);
    }

    private void b() {
        synchronized (this) {
            this.c = null;
            MagicNetwork.e().edit().remove("SNP_COOKIE").apply();
            return;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    String a() {
        synchronized (this) {
            if (this.d == null) return null;
            return this.d;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    void a(HttpCookie httpCookie, String string2) {
        synchronized (this) {
            if (httpCookie.hasExpired()) {
                this.b();
            } else {
                void var2_2;
                this.c = httpCookie;
                this.d = var2_2;
                this.c.setPath("/");
                this.c.setSecure(false);
                this.c.setPortlist(null);
                MagicNetwork.e().edit().putString("SNP_COOKIE", (String)var2_2).apply();
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void add(URI uRI, HttpCookie httpCookie) {
        this.b.add(uRI, httpCookie);
        synchronized (this) {
            if (this.c != null && this.c.hasExpired()) {
                this.b();
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public List<HttpCookie> get(URI serializable) {
        List<HttpCookie> list = this.b.get((URI)serializable);
        synchronized (this) {
            Object object = list;
            if (serializable.getHost() != null) {
                object = list;
                if (serializable.getHost().contains("smule.com")) {
                    object = list;
                    if (this.c != null) {
                        serializable = list == null ? new ArrayList() : new ArrayList<HttpCookie>(list);
                        serializable.add(this.c);
                        object = serializable;
                    }
                }
            }
            return Collections.unmodifiableList(object);
        }
    }

    @Override
    public List<HttpCookie> getCookies() {
        return this.b.getCookies();
    }

    @Override
    public List<URI> getURIs() {
        return this.b.getURIs();
    }

    @Override
    public boolean remove(URI uRI, HttpCookie httpCookie) {
        return this.b.remove(uRI, httpCookie);
    }

    @Override
    public boolean removeAll() {
        return this.b.removeAll();
    }
}

