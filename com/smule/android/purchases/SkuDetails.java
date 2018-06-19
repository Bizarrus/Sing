package com.smule.android.purchases;

import org.json.JSONException;
import org.json.JSONObject;

public class SkuDetails {
    String f17581a;
    String f17582b;
    String f17583c;
    String f17584d;
    Integer f17585e;
    String f17586f;
    String f17587g;
    String f17588h;
    String f17589i;

    public SkuDetails(String str, String str2) throws JSONException {
        this.f17581a = str;
        this.f17589i = str2;
        JSONObject jSONObject = new JSONObject(this.f17589i);
        this.f17582b = jSONObject.optString("productId");
        this.f17583c = jSONObject.optString("type");
        this.f17584d = jSONObject.optString("price");
        this.f17585e = Integer.valueOf(jSONObject.optInt("price_amount_micros"));
        this.f17586f = jSONObject.optString("price_currency_code");
        this.f17587g = jSONObject.optString("title");
        this.f17588h = jSONObject.optString("description");
    }

    public String m18680a() {
        return this.f17582b;
    }

    public String m18681b() {
        return this.f17584d;
    }

    public String toString() {
        return "SkuDetails:" + this.f17589i;
    }
}
