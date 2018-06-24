/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package com.smule.android.purchases;

import org.json.JSONException;
import org.json.JSONObject;

public class SkuDetails {
    String a;
    String b;
    String c;
    String d;
    Integer e;
    String f;
    String g;
    String h;
    String i;
    String j;
    String k;
    String l;
    String m;

    public SkuDetails(String string2, String string3) throws JSONException {
        this.a = string2;
        this.i = string3;
        string2 = new JSONObject(this.i);
        this.b = string2.optString("productId");
        this.c = string2.optString("type");
        this.d = string2.optString("price");
        this.e = string2.optInt("price_amount_micros");
        this.f = string2.optString("price_currency_code");
        this.g = string2.optString("title");
        this.h = string2.optString("description");
        this.j = string2.optString("introductoryPrice", null);
        this.k = string2.optString("introductoryPriceAmountMicros", null);
        this.l = string2.optString("introductoryPricePeriod", null);
        this.m = string2.optString("introductoryPriceCycles", null);
    }

    public String a() {
        return this.b;
    }

    public String b() {
        return this.d;
    }

    public String c() {
        return this.j;
    }

    public String toString() {
        return "SkuDetails:" + this.i;
    }
}

