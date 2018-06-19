package com.smule.android.purchases;

import com.facebook.accountkit.internal.AccountKitGraphConstants;
import org.json.JSONException;
import org.json.JSONObject;

public class Purchase {
    String f17568a;
    String f17569b;
    String f17570c;
    String f17571d;
    long f17572e;
    int f17573f;
    String f17574g;
    String f17575h;
    String f17576i;
    String f17577j;

    public Purchase(String str, String str2, String str3) throws JSONException {
        this.f17568a = str;
        this.f17576i = str2;
        JSONObject jSONObject = new JSONObject(this.f17576i);
        this.f17569b = jSONObject.optString("orderId");
        this.f17570c = jSONObject.optString("packageName");
        this.f17571d = jSONObject.optString("productId");
        this.f17572e = jSONObject.optLong("purchaseTime");
        this.f17573f = jSONObject.optInt("purchaseState");
        this.f17574g = jSONObject.optString("developerPayload");
        this.f17575h = jSONObject.optString(AccountKitGraphConstants.TOKEN_RESPONSE_TYPE, jSONObject.optString("purchaseToken"));
        this.f17577j = str3;
    }

    public String m18666a() {
        return this.f17568a;
    }

    public String m18667b() {
        return this.f17569b;
    }

    public String m18668c() {
        return this.f17571d;
    }

    public long m18669d() {
        return this.f17572e;
    }

    public int m18670e() {
        return this.f17573f;
    }

    public String m18671f() {
        return this.f17574g;
    }

    public String m18672g() {
        return this.f17575h;
    }

    public String m18673h() {
        return this.f17576i;
    }

    public String m18674i() {
        return this.f17577j;
    }

    public String toString() {
        return "PurchaseInfo(type:" + this.f17568a + "):" + this.f17576i;
    }
}
