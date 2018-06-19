package com.smule.android.network.core;

import com.mopub.common.Constants;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

final class CookieStoreImpl implements CookieStore {
    private final Map<URI, List<HttpCookie>> f16446a = new HashMap();

    CookieStoreImpl() {
    }

    public synchronized void add(URI uri, HttpCookie httpCookie) {
        if (httpCookie == null) {
            throw new NullPointerException("cookie == null");
        }
        URI a = m18078a(uri);
        List list = (List) this.f16446a.get(a);
        if (list == null) {
            list = new ArrayList();
            this.f16446a.put(a, list);
        } else {
            list.remove(httpCookie);
        }
        list.add(httpCookie);
    }

    private URI m18078a(URI uri) {
        if (uri == null) {
            return null;
        }
        try {
            return new URI(Constants.HTTP, uri.getHost(), null, null);
        } catch (URISyntaxException e) {
            return uri;
        }
    }

    public synchronized List<HttpCookie> get(URI uri) {
        List arrayList;
        if (uri == null) {
            throw new NullPointerException("uri == null");
        }
        Iterator it;
        HttpCookie httpCookie;
        arrayList = new ArrayList();
        List list = (List) this.f16446a.get(uri);
        if (list != null) {
            it = list.iterator();
            while (it.hasNext()) {
                httpCookie = (HttpCookie) it.next();
                if (httpCookie.hasExpired()) {
                    it.remove();
                } else {
                    arrayList.add(httpCookie);
                }
            }
        }
        for (Entry entry : this.f16446a.entrySet()) {
            if (!uri.equals(entry.getKey())) {
                Iterator it2 = ((List) entry.getValue()).iterator();
                while (it2.hasNext()) {
                    httpCookie = (HttpCookie) it2.next();
                    if (HttpCookie.domainMatches(httpCookie.getDomain(), uri.getHost())) {
                        if (httpCookie.hasExpired()) {
                            it2.remove();
                        } else if (!arrayList.contains(httpCookie)) {
                            arrayList.add(httpCookie);
                        }
                    }
                }
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    public synchronized List<HttpCookie> getCookies() {
        List arrayList;
        arrayList = new ArrayList();
        for (List it : this.f16446a.values()) {
            Iterator it2 = it.iterator();
            while (it2.hasNext()) {
                HttpCookie httpCookie = (HttpCookie) it2.next();
                if (httpCookie.hasExpired()) {
                    it2.remove();
                } else if (!arrayList.contains(httpCookie)) {
                    arrayList.add(httpCookie);
                }
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    public synchronized List<URI> getURIs() {
        List arrayList;
        arrayList = new ArrayList(this.f16446a.keySet());
        arrayList.remove(null);
        return Collections.unmodifiableList(arrayList);
    }

    public synchronized boolean remove(URI uri, HttpCookie httpCookie) {
        boolean remove;
        if (httpCookie == null) {
            throw new NullPointerException("cookie == null");
        }
        List list = (List) this.f16446a.get(m18078a(uri));
        if (list != null) {
            remove = list.remove(httpCookie);
        } else {
            remove = false;
        }
        return remove;
    }

    public synchronized boolean removeAll() {
        boolean z;
        z = !this.f16446a.isEmpty();
        this.f16446a.clear();
        return z;
    }
}
