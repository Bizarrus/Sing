/*
 * Decompiled with CFR 0_123.
 */
package com.smule.android.network.core;

import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

final class CookieStoreImpl
implements CookieStore {
    private final Map<URI, List<HttpCookie>> a = new HashMap<URI, List<HttpCookie>>();

    CookieStoreImpl() {
    }

    private URI a(URI uRI) {
        if (uRI == null) {
            return null;
        }
        try {
            URI uRI2 = new URI("http", uRI.getHost(), null, null);
            return uRI2;
        }
        catch (URISyntaxException uRISyntaxException) {
            return uRI;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void add(URI list, HttpCookie httpCookie) {
        synchronized (this) {
            if (httpCookie == null) {
                throw new NullPointerException("cookie == null");
            }
            URI uRI = this.a((URI)((Object)list));
            list = this.a.get(uRI);
            if (list == null) {
                list = new ArrayList<HttpCookie>();
                this.a.put(uRI, list);
            } else {
                list.remove(httpCookie);
            }
            list.add(httpCookie);
            return;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    @Override
    public List<HttpCookie> get(URI var1_1) {
        // MONITORENTER : this
        if (var1_1 /* !! */  == null) {
            throw new NullPointerException("uri == null");
        }
        var2_2 = new ArrayList<Object>();
        var3_3 = this.a.get(var1_1 /* !! */ );
        if (var3_3 != null) {
            var3_3 = var3_3.iterator();
            while (var3_3.hasNext()) {
                var4_4 = (HttpCookie)var3_3.next();
                if (var4_4.hasExpired()) {
                    var3_3.remove();
                    continue;
                }
                var2_2.add(var4_4);
            }
        }
        var3_3 = this.a.entrySet().iterator();
        block4 : do lbl-1000: // 3 sources:
        {
            if (!var3_3.hasNext()) {
                var1_1 /* !! */  = Collections.unmodifiableList(var2_2);
                // MONITOREXIT : this
                return var1_1 /* !! */ ;
            }
            var4_4 = var3_3.next();
            if (var1_1 /* !! */ .equals(var4_4.getKey())) ** GOTO lbl-1000
            var4_4 = ((List)var4_4.getValue()).iterator();
            do {
                if (!var4_4.hasNext()) continue block4;
                var5_5 = (HttpCookie)var4_4.next();
                if (!HttpCookie.domainMatches(var5_5.getDomain(), var1_1 /* !! */ .getHost())) continue;
                if (var5_5.hasExpired()) {
                    var4_4.remove();
                    continue;
                }
                if (var2_2.contains(var5_5)) continue;
                var2_2.add(var5_5);
            } while (true);
            break;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public List<HttpCookie> getCookies() {
        synchronized (this) {
            ArrayList<HttpCookie> arrayList = new ArrayList();
            Iterator<List<HttpCookie>> iterator = this.a.values().iterator();
            block3 : while (iterator.hasNext()) {
                Iterator<HttpCookie> iterator2 = iterator.next().iterator();
                do {
                    if (!iterator2.hasNext()) continue block3;
                    HttpCookie httpCookie = iterator2.next();
                    if (httpCookie.hasExpired()) {
                        iterator2.remove();
                        continue;
                    }
                    if (arrayList.contains(httpCookie)) continue;
                    arrayList.add(httpCookie);
                } while (true);
                break;
            }
            return Collections.unmodifiableList(arrayList);
        }
    }

    @Override
    public List<URI> getURIs() {
        synchronized (this) {
            List list = new ArrayList<URI>(this.a.keySet());
            list.remove(null);
            list = Collections.unmodifiableList(list);
            return list;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public boolean remove(URI object, HttpCookie httpCookie) {
        synchronized (this) {
            if (httpCookie == null) {
                throw new NullPointerException("cookie == null");
            }
            if ((object = this.a.get(this.a((URI)object))) == null) return false;
            return object.remove(httpCookie);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public boolean removeAll() {
        synchronized (this) {
            boolean bl = !this.a.isEmpty();
            this.a.clear();
            return bl;
        }
    }
}

