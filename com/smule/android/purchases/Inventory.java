package com.smule.android.purchases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory {
    Map<String, SkuDetails> f17566a = new HashMap();
    Map<String, Purchase> f17567b = new HashMap();

    Inventory() {
    }

    public Purchase m18662a(String str) {
        return (Purchase) this.f17567b.get(str);
    }

    List<String> m18665b(String str) {
        List<String> arrayList = new ArrayList();
        for (Purchase purchase : this.f17567b.values()) {
            if (purchase.m18666a().equals(str)) {
                arrayList.add(purchase.m18668c());
            }
        }
        return arrayList;
    }

    void m18664a(SkuDetails skuDetails) {
        this.f17566a.put(skuDetails.m18680a(), skuDetails);
    }

    void m18663a(Purchase purchase) {
        this.f17567b.put(purchase.m18668c(), purchase);
    }
}
