package com.smule.android.network.core;

import android.text.TextUtils;
import com.smule.android.logging.Log;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class MagicCookieStore implements CookieStore {
    public static final String f16452a = MagicCookieStore.class.getSimpleName();
    private CookieStoreImpl f16453b = new CookieStoreImpl();
    private HttpCookie f16454c;
    private String f16455d;

    MagicCookieStore() {
        String string = MagicNetwork.e().getString("SNP_COOKIE", "");
        if (!TextUtils.isEmpty(string)) {
            try {
                List parse = HttpCookie.parse(string);
                if (parse != null && !parse.isEmpty()) {
                    this.f16454c = (HttpCookie) parse.get(0);
                    m18082a(this.f16454c, string);
                }
            } catch (IllegalArgumentException e) {
                Log.b(f16452a, "invalid:" + string);
            }
        }
    }

    public void add(URI uri, HttpCookie httpCookie) {
        this.f16453b.add(uri, httpCookie);
        synchronized (this) {
            if (this.f16454c != null && this.f16454c.hasExpired()) {
                m18080b();
            }
        }
    }

    public List<HttpCookie> get(URI uri) {
        List list = this.f16453b.get(uri);
        synchronized (this) {
            if (!(uri.getHost() == null || !uri.getHost().contains("smule.com") || this.f16454c == null)) {
                if (list == null) {
                    list = new ArrayList();
                } else {
                    Object arrayList = new ArrayList(list);
                }
                list.add(this.f16454c);
            }
        }
        return Collections.unmodifiableList(list);
    }

    public List<HttpCookie> getCookies() {
        return this.f16453b.getCookies();
    }

    public List<URI> getURIs() {
        return this.f16453b.getURIs();
    }

    public boolean remove(URI uri, HttpCookie httpCookie) {
        return this.f16453b.remove(uri, httpCookie);
    }

    public boolean removeAll() {
        return this.f16453b.removeAll();
    }

    synchronized void m18082a(HttpCookie httpCookie, String str) {
        if (httpCookie.hasExpired()) {
            m18080b();
        } else {
            this.f16454c = httpCookie;
            this.f16455d = str;
            this.f16454c.setPath("/");
            this.f16454c.setSecure(false);
            this.f16454c.setPortlist(null);
            MagicNetwork.e().edit().putString("SNP_COOKIE", str).apply();
        }
    }

    private synchronized void m18080b() {
        this.f16454c = null;
        MagicNetwork.e().edit().remove("SNP_COOKIE").apply();
    }

    synchronized String m18081a() {
        String str;
        if (this.f16455d != null) {
            str = this.f16455d;
        } else {
            str = null;
        }
        return str;
    }
}
