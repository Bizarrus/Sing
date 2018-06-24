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

public class Purchase {
    String a;
    String b;
    String c;
    String d;
    long e;
    int f;
    String g;
    String h;
    String i;
    String j;

    public Purchase(String string2, String string3, String string4) throws JSONException {
        this.a = string2;
        this.i = string3;
        string2 = new JSONObject(this.i);
        this.b = string2.optString("orderId");
        this.c = string2.optString("packageName");
        this.d = string2.optString("productId");
        this.e = string2.optLong("purchaseTime");
        this.f = string2.optInt("purchaseState");
        this.g = string2.optString("developerPayload");
        this.h = string2.optString("token", string2.optString("purchaseToken"));
        this.j = string4;
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.d;
    }

    public long d() {
        return this.e;
    }

    public int e() {
        return this.f;
    }

    public String f() {
        return this.g;
    }

    public String g() {
        return this.h;
    }

    public String h() {
        return this.i;
    }

    public String i() {
        return this.j;
    }

    public String toString() {
        return "PurchaseInfo(type:" + this.a + "):" + this.i;
    }
}

