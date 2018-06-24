/*
 * Decompiled with CFR 0_123.
 */
package com.smule.android.network.core;

import com.smule.android.network.core.MagicCookieStore;
import com.smule.android.network.core.MagicNetwork;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

class MagicCookieHandler
extends CookieHandler {
    private final CookieManager a;
    private final MagicCookieStore b;

    MagicCookieHandler(MagicCookieStore magicCookieStore) {
        this.a = new CookieManager(magicCookieStore, null);
        this.b = magicCookieStore;
    }

    @Override
    public Map<String, List<String>> get(URI uRI, Map<String, List<String>> map) throws IOException {
        return this.a.get(uRI, map);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    @Override
    public void put(URI var1_1, Map<String, List<String>> var2_2) throws IOException {
        this.a.put((URI)var1_1, (Map<String, List<String>>)var2_2);
        if (!var1_1.getHost().equals(MagicNetwork.c())) {
            return;
        }
        var1_1 = var2_2.entrySet().iterator();
        block2 : do lbl-1000: // 3 sources:
        {
            if (var1_1.hasNext() == false) return;
            var2_2 = var1_1.next();
            var3_3 = (String)var2_2.getKey();
            if (var3_3 == null || !var3_3.equalsIgnoreCase("set-cookie")) ** GOTO lbl-1000
            var2_2 = ((List)var2_2.getValue()).iterator();
            do {
                if (!var2_2.hasNext()) continue block2;
                var3_3 = (String)var2_2.next();
                try {
                    var4_5 = HttpCookie.parse(var3_3).iterator();
                    if (!var4_5.hasNext()) continue;
                    var4_5 = var4_5.next();
                    this.b.a((HttpCookie)var4_5, var3_3);
                    return;
                }
                catch (IllegalArgumentException var3_4) {
                    continue;
                }
                break;
            } while (true);
            break;
        } while (true);
    }
}

