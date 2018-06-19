package com.smule.android.network.core;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.URI;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

class MagicCookieHandler extends CookieHandler {
    private final CookieManager f16450a;
    private final MagicCookieStore f16451b;

    MagicCookieHandler(MagicCookieStore magicCookieStore) {
        this.f16450a = new CookieManager(magicCookieStore, null);
        this.f16451b = magicCookieStore;
    }

    public Map<String, List<String>> get(URI uri, Map<String, List<String>> map) throws IOException {
        return this.f16450a.get(uri, map);
    }

    public void put(URI uri, Map<String, List<String>> map) throws IOException {
        this.f16450a.put(uri, map);
        if (uri.getHost().equals(MagicNetwork.c())) {
            for (Entry entry : map.entrySet()) {
                String str = (String) entry.getKey();
                if (str != null && str.equalsIgnoreCase("set-cookie")) {
                    for (String str2 : (List) entry.getValue()) {
                        try {
                            Iterator it = HttpCookie.parse(str2).iterator();
                            if (it.hasNext()) {
                                this.f16451b.m18082a((HttpCookie) it.next(), str2);
                                return;
                            }
                        } catch (IllegalArgumentException e) {
                        }
                    }
                    continue;
                }
            }
        }
    }
}
