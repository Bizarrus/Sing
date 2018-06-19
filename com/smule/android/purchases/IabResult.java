package com.smule.android.purchases;

public class IabResult {
    int f17564a;
    String f17565b;

    public IabResult(int i, String str) {
        this.f17564a = i;
        if (str == null || str.trim().length() == 0) {
            this.f17565b = IabHelper.m18632a(i);
        } else {
            this.f17565b = str + " (response: " + IabHelper.m18632a(i) + ")";
        }
    }

    public int m18658a() {
        return this.f17564a;
    }

    public String m18659b() {
        return this.f17565b;
    }

    public boolean m18660c() {
        return this.f17564a == 0;
    }

    public boolean m18661d() {
        return !m18660c();
    }

    public String toString() {
        return "IabResult: " + m18659b();
    }
}
