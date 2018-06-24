/*
 * Decompiled with CFR 0_123.
 */
package com.smule.android.purchases;

import com.smule.android.purchases.Purchase;
import com.smule.android.purchases.SkuDetails;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory {
    Map<String, SkuDetails> a = new HashMap<String, SkuDetails>();
    Map<String, Purchase> b = new HashMap<String, Purchase>();

    Inventory() {
    }

    public Purchase a(String string2) {
        return this.b.get(string2);
    }

    void a(Purchase purchase) {
        this.b.put(purchase.c(), purchase);
    }

    void a(SkuDetails skuDetails) {
        this.a.put(skuDetails.a(), skuDetails);
    }

    List<String> b(String string2) {
        ArrayList<String> arrayList = new ArrayList<String>();
        for (Purchase purchase : this.b.values()) {
            if (!purchase.a().equals(string2)) continue;
            arrayList.add(purchase.c());
        }
        return arrayList;
    }
}

